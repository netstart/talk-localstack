package com.github.talk.localstack.s3;

import com.github.talk.localstack.configuration.S3LocalStackConfig;

public  class AmazonS3 {


    public static void main(String [] args) {
        com.amazonaws.services.s3.AmazonS3 s3 = new S3LocalStackConfig().s3Localstack();

        final String bucketName = "ORDER";
        final String fullPathName = "now/1.json";
        final String content = "{id: 1, lastname: Passos}";

        //salvar
        s3.putObject(bucketName, fullPathName, content);


        // pegar
        final String objectAsString = s3.getObjectAsString(bucketName, fullPathName);

        System.out.println("Objeto recuperado: " + objectAsString);
    }


}
