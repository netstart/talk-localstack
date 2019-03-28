Portas  serviços disponíveis atualmente (versão 0.9)

```
Running on http://0.0.0.0:8080/​

API Gateway                     (http 4567) ​
Kinesis                         (http 4568) ​
DynamoDB                        (http 4569) ​
DynamoDB Streams                (http 4570) ​
Elasticsearch                   (http 4571) ​
S3                              (http 4572) ​
Firehose                        (http 4573) ​
Lambda                          (http 4574) ​
SNS                             (http 4575) ​
SQS                             (http 4576) ​
Redshift                        (http 4577) ​
ES (Elasticsearch Service)      (http 4578) ​
SES                             (http 4579) ​
Route53                         (htto 4580) ​
CloudFormation                  (http 4581) ​
CloudWatch                      (http 4582) ​
SSM                             (http 4583) ​
SecretsManager                  (http 4584) ​
StepFunctions                   (http 4585) ​
CloudWatch Logs                 (http 4586) ​
STS                             (http 4592) ​
IAM                             (http 4593)

```

```
sudo apt install awscli

aws configure

docker run -p 8080:8080 -p 4572:4572 -p 4576:4576 -p 4569:4569 localstack/localstack

```

[SQS](src/com/github/talk/localstack/sqs/readme.md)




#### Referências
https://lobster1234.github.io/2017/04/05/working-with-localstack-command-line/

https://github.com/fabianoo/spring-localstack

https://github.com/smartupio/localstack-spring-boot