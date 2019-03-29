package com.github.talk.localstack.sqs;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.CreateQueueResult;
import com.amazonaws.services.sqs.model.Message;
import com.github.talk.localstack.configuration.LocalStackConfig;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.concurrent.ExecutionException;

import cloud.localstack.DockerTestUtils;
import cloud.localstack.docker.LocalstackDockerTestRunner;

@RunWith(LocalstackDockerTestRunner.class) // com docker
//@RunWith(LocalstackTestRunner.class)// sem docker
//@LocalstackDockerProperties(randomizePorts = true)
public class AmazonSQSServiceTest {

    // usando o DockerTestUtils precisa ter o docker instalado
    AmazonSQS amazonSQS = DockerTestUtils.getClientSQS();

    // usando o TestUtils precisa ter o python correto instalado
//    AmazonSQSAsync amazonSQSAsync = TestUtils.getClientSQSAsync();


    @Test
    public void testSQS() throws ExecutionException, InterruptedException {
        final String msgBody = "{id: 1, name: Clayton}";

        CreateQueueResult queueCreated = amazonSQS.createQueue("ORDER");
        String URL_QUEUE = queueCreated.getQueueUrl();


        final AmazonSQSService amazonSQSService = new AmazonSQSService(amazonSQS, URL_QUEUE);
        amazonSQSService.sendMessage(msgBody);
        final List<Message> messages = amazonSQSService.messages();
        for(Message msg: messages) {
            System.out.println(msg);
            Assert.assertEquals(msgBody, msg.getBody());
            amazonSQSService.deleteMessage(msg);
        }

    }


}
