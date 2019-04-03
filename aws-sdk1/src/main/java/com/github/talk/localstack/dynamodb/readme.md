
##### Create table on DynamoDB/localstack

```
aws dynamodb create-table  --endpoint-url=http://localhost:4569 \
    --table-name order \
    --attribute-definitions \
        AttributeName=id,AttributeType=S \
    --key-schema AttributeName=id,KeyType=HASH \
    --provisioned-throughput ReadCapacityUnits=1,WriteCapacityUnits=1
```

```
aws dynamodb create-table  --endpoint-url=http://localhost:4569 \
    --table-name order \
    --attribute-definitions \
        AttributeName=id,AttributeType=S \
        AttributeName=track_date,AttributeType=S \
    --key-schema AttributeName=id,KeyType=HASH AttributeName=track_date,KeyType=RANGE \
    --provisioned-throughput ReadCapacityUnits=1,WriteCapacityUnits=1
```


##### List tables
```
aws dynamodb list-tables --endpoint-url=http://localhost:4569 
```

##### Put item on order

```
aws dynamodb put-item --endpoint-url=http://localhost:4569  \
--table-name order  \
--item '{"id":{"S": "1234-br"}, "track_date":{"S":"2019-02-20T17:18:59.703474Z"}}'
```

##### Scan table
```
aws dynamodb scan --endpoint-url=http://localhost:4569  --table-name order
```

##### Get Iten
```
aws dynamodb get-item --endpoint-url=http://localhost:4569 \
--table-name order  \
--key '{"id":{"S":"1234-br"}, "track_date": {"S": "2019-02-20T17:20:59.703474Z" }}'
```

##### Query
```
aws dynamodb query --endpoint-url=http://localhost:4569  \
--table-name order \
--projection-expression "id, track_date" \
--key-condition-expression "id = :value" \
--expression-attribute-values '{":value" : {"S":"1234-br"}}'

```

##### Delete(drop) table
```
aws dynamodb delete-table  --endpoint-url http://localhost:4569 --table-name order
```
