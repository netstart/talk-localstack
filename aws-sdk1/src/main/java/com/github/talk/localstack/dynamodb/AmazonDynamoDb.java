package com.github.talk.localstack.dynamodb;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsync;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.github.talk.localstack.configuration.DynamoDBLocalStackConfig;
import com.github.talk.localstack.dynamodb.entity.OrderEntity;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Antes crie a tabela order
 *
 *
 * aws dynamodb create-table  --endpoint-url=http://localhost:4569 \
 *     --table-name order \
 *     --attribute-definitions \
 *         AttributeName=id,AttributeType=S \
 *     --key-schema AttributeName=id,KeyType=HASH \
 *     --provisioned-throughput ReadCapacityUnits=1,WriteCapacityUnits=1
 */
public class AmazonDynamoDb {


    public static void main(String args[]) {
        AmazonDynamoDBAsync ddb = DynamoDBLocalStackConfig.dynamoDBAsync();

        DynamoDBMapper mapper = new DynamoDBMapper(ddb);

        final DynamoDBScanExpression dynamoDBScanExpression = new DynamoDBScanExpression();

        final PaginatedScanList<OrderEntity> list = mapper.scan(OrderEntity.class, dynamoDBScanExpression);

        OrderEntity order = list.get(0);
        System.out.println(String.format("query id: %s date: %s", order.getId(), order.getTrackDate()));
    }



    public List<OrderEntity> findByTwoTrackDate(Class<OrderEntity> clazz, final ZonedDateTime initialTrackDate,
                                                   final ZonedDateTime finalTrackDate) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZZ");

        Map<String, AttributeValue> keys = new HashMap<>();
        keys.put(":initialTrackDate", new AttributeValue().withS(dateFormatter.format(initialTrackDate)));
        keys.put(":finalTrackDate", new AttributeValue().withS(dateFormatter.format(finalTrackDate)));

        DynamoDBScanExpression scan = new DynamoDBScanExpression();
        scan.withFilterExpression("track_date between :initialTrackDate and :finalTrackDate");
        scan.withExpressionAttributeValues(keys);

        AmazonDynamoDBAsync ddb = DynamoDBLocalStackConfig.dynamoDB();
        DynamoDBMapper mapper = new DynamoDBMapper(ddb);
        return mapper.scan(clazz, scan);

    }


}
