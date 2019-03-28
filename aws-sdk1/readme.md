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

##### Criar fila no SQS
```
aws sqs create-queue --endpoint-url=http://localhost:4576  --queue-name ORDER --region sa-east-1
```


##### Enviar menssagem para o SQS

```
aws sqs send-message \
--endpoint-url http://localhost:4576 \
--queue-url http://localhost:4576/queue/ORDER  \
--message-body '{"eventType": "ORDER_STATE_CHANGE","parameters":{"ORDER_ID":"1234","CURRENT_ORDER_STATE": "CANCELLED"}}' \
--message-attributes '{"tenant-identifier":{"DataType": "String","StringValue": "br"}}'
```


##### Receber menssagem da fila

```
aws sqs receive-message --endpoint-url http://localhost:4576 --queue-url http://localhost:4576/queue/ORDER 
```


#### Apagar a menssagem
```
aws --endpoint-url=http://localhost:4576 sqs delete-message --queue-url http://localhost:4576/queue/ORDER --receipt-handle 'código_que_veio_no_comando_de_receive_message'
```
