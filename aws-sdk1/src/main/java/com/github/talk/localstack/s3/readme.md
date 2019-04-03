##### Create a bucket
```
aws --endpoint-url=http://localhost:4572 s3 mb s3://order
```
##### List buckets
```
aws --endpoint-url=http://localhost:4572 s3 ls
```

##### Copy a file over
```
aws --endpoint-url=http://localhost:4572 s3 cp /tmp/mongo.log s3://order
```


##### List files
```
aws --endpoint-url=http://localhost:4572 s3 ls s3://order


```

##### Delete this fileg
```
aws --endpoint-url=http://localhost:4572 s3 rm s3://order/mongo.log
```
