package com.github.talk.localstack.configuration;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import com.amazonaws.services.sqs.buffered.AmazonSQSBufferedAsyncClient;

public class LocalStackConfig {

    /**
     * Para testes o caminho completo foi necessário, em produção apenas o nome da fila 'ORDER' foi suficiente
     */
    public static final String AMAZON_SQS_ENDPOINT = "http://localhost:4576/queue/ORDER";

    public static final String AMAZON_REGION = "sa-east-1";


    /**
     * Para produção isto basta
     */
    public AmazonSQSAsync amazonSQS() {
        return new AmazonSQSBufferedAsyncClient(AmazonSQSAsyncClientBuilder.defaultClient());
    }

    /**
     * Para testes, precisamos definir algumas coisas manualmente
     */
    public AmazonSQSAsync amazonSQSAsync() {
        return AmazonSQSAsyncClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(AMAZON_SQS_ENDPOINT, AMAZON_REGION))
                .build();
    }

}
