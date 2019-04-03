package com.github.talk.localstack.configuration;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

public class S3LocalStackConfig {

    /**
     * Para testes o caminho completo foi necessário, em produção não precisa informar nada suficiente
     */
    private static final String AMAZON_S3_ENDPOINT = "http://localhost:4572";


    public static final String AMAZON_REGION = "sa-east-1";


    /**
     * Normalmente isto é suficiente
     */
    public AmazonS3 s3() {
        return AmazonS3ClientBuilder.defaultClient();
    }


    /**
     * Talvez você precise customizar algum parâmetro
     */
    public AmazonS3 s3Localstack() {
        return AmazonS3ClientBuilder.standard()
//            .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(AMAZON_S3_ENDPOINT, AMAZON_REGION))
            .withPathStyleAccessEnabled(true)
            .build();
    }
}
