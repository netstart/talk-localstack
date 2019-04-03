package com.github.talk.localstack.sqs;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.amazonaws.services.sqs.model.SendMessageResult;
import com.github.talk.localstack.configuration.SQSLocalStackConfig;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class AmazonSQS {

    public static void main(String[] str) throws ExecutionException, InterruptedException {
        final SQSLocalStackConfig localStack = new SQSLocalStackConfig();

        final AmazonSQSAsync amazonSQSAsync = localStack.amazonSQS();

        // Crie a fila antes:  aws sqs create-queue --endpoint-url=http://localhost:4576  --queue-name ORDER --region sa-east-1

        // enviar mensagem
        final SendMessageResult sendMessageResult = amazonSQSAsync.sendMessage(SQSLocalStackConfig.AMAZON_SQS_ENDPOINT, "{id: 1, name: Clayton}");

        // ler mensagem
         Future<ReceiveMessageResult> receiveMessageResultFuture = amazonSQSAsync.receiveMessageAsync(SQSLocalStackConfig.AMAZON_SQS_ENDPOINT);
         List<Message> messages = receiveMessageResultFuture.get().getMessages();
        for (Message msg : messages) {
            System.out.println(msg.getBody());

            //apaga a msg
            amazonSQSAsync.deleteMessage(SQSLocalStackConfig.AMAZON_SQS_ENDPOINT, msg.getReceiptHandle());
        }

        System.exit(0);
    }

}
