package com.github.talk.localstack.sqs;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.amazonaws.services.sqs.model.SendMessageResult;
import com.github.talk.localstack.configuration.LocalStackConfig;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class AmazonSQS {

    public static void main(String[] str) throws ExecutionException, InterruptedException {
        final LocalStackConfig localStack = new LocalStackConfig();

        final AmazonSQSAsync amazonSQSAsync = localStack.amazonSQSAsync();

        // enviar mensagem
        final SendMessageResult sendMessageResult = amazonSQSAsync.sendMessage(LocalStackConfig.AMAZON_SQS_ENDPOINT, "{id: 1, name: Clayton}");

        // ler mensagem
         Future<ReceiveMessageResult> receiveMessageResultFuture = amazonSQSAsync.receiveMessageAsync(LocalStackConfig.AMAZON_SQS_ENDPOINT);
         List<Message> messages = receiveMessageResultFuture.get().getMessages();
        for (Message msg : messages) {
            System.out.println(msg.getBody());
            amazonSQSAsync.deleteMessage(LocalStackConfig.AMAZON_SQS_ENDPOINT, msg.getReceiptHandle());
        }



        System.exit(0);
    }

}
