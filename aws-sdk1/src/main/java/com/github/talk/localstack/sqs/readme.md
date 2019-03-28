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


