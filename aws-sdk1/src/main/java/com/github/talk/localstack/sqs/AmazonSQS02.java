package com.github.talk.localstack.sqs;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.MessageAttributeValue;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.github.talk.localstack.configuration.LocalStackConfig;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class AmazonSQS02 {

    public static void main(String[] str) throws ExecutionException, InterruptedException {
        final LocalStackConfig localStack = new LocalStackConfig();

        final AmazonSQSAsync amazonSQSAsync = localStack.amazonSQSAsync();


        //cria atributo da msg
        final HashMap<String, MessageAttributeValue> attributes = new HashMap<>();
        attributes.put("tennant", new MessageAttributeValue().withStringValue("br").withDataType("String"));

        //criando  msg
        final SendMessageRequest
            sendMessageRequest =
            new SendMessageRequest().withMessageBody("{id 2, lastname: Passos}")
                .withMessageAttributes(attributes)
                .withQueueUrl(LocalStackConfig.AMAZON_SQS_ENDPOINT);

        //envia msg
        amazonSQSAsync.sendMessage(sendMessageRequest);


        // ler mensagem sem os atributos
        final Future<ReceiveMessageResult> receiveMessageResultFuture = amazonSQSAsync.receiveMessageAsync(LocalStackConfig.AMAZON_SQS_ENDPOINT);
        List<Message> messages = receiveMessageResultFuture.get().getMessages();
        for (Message msg : messages) {
            System.out.println(msg.getBody());
            System.out.println(msg.getAttributes());
            System.out.println(msg.getMessageAttributes()); //este são os que eu adicionei ao enviar a msg
            amazonSQSAsync.deleteMessage(LocalStackConfig.AMAZON_SQS_ENDPOINT, msg.getReceiptHandle());
        }


        System.exit(0);
    }

}
