package com.github.talk.localstack.configuration;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsync;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClientBuilder;

public class DynamoDBLocalStackConfig {

    /**
     * Para testes o caminho completo foi necessário para testes com o localstack, em produção apenas o nome da tabela costuma ser suficiente
     */
    public static final String AMAZON_DYNAMODB_ENDPOINT = "http://localhost:4569";

    public static final String AMAZON_DYNAMODB_TABLE = "order";

    public static final String AMAZON_REGION = "sa-east-1";


    /**
     * Normalmente isto é suficiente
     */
    public static AmazonDynamoDBAsync dynamoDB() {
        return AmazonDynamoDBAsyncClientBuilder.defaultClient();
    }


    /**
     * Talvez você precise customizar algum parâmetro
     */
    public static AmazonDynamoDBAsync dynamoDBAsync() {
        return AmazonDynamoDBAsyncClientBuilder.standard()
            .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(AMAZON_DYNAMODB_ENDPOINT, AMAZON_REGION))
            .build();
    }
}
