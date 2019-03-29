package com.github.talk.localstack.sqs;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.github.talk.localstack.configuration.LocalStackConfig;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class AmazonSQSService {
    private final AmazonSQS amazonSQS;
    private final String amazonSqsEndPoint;

    public AmazonSQSService(AmazonSQS amazonSQS, String amazonSQSEndpoint){
        this.amazonSQS = amazonSQS;
        this.amazonSqsEndPoint = amazonSQSEndpoint;
    }

    public void sendMessage(String msgBody){
        amazonSQS.sendMessage(LocalStackConfig.AMAZON_SQS_ENDPOINT, msgBody);
    }

    public  List<Message> messages() throws ExecutionException, InterruptedException {
        final ReceiveMessageResult receiveMessageResult = amazonSQS.receiveMessage(LocalStackConfig.AMAZON_SQS_ENDPOINT);
        return receiveMessageResult.getMessages();
    }

    public void deleteMessage(Message msg) {
        amazonSQS.deleteMessage(LocalStackConfig.AMAZON_SQS_ENDPOINT, msg.getReceiptHandle());
    }
}
