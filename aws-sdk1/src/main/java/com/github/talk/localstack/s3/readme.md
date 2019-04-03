##### Create a bucket
```
aws --endpoint-url=http://localhost:4572 s3 mb s3://ORDER
```
##### List buckets
```
aws --endpoint-url=http://localhost:4572 s3 ls
```

##### Copy a file over
```
aws --endpoint-url=http://localhost:4572 s3 cp /tmp/mongo.log s3://ORDER
```


##### List files
```
aws --endpoint-url=http://localhost:4572 s3 ls s3://ORDER


```

##### Delete this fileg
```
aws --endpoint-url=http://localhost:4572 s3 rm s3://ORDER/mongo.log
```
