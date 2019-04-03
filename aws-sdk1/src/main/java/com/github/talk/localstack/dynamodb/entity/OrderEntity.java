package com.github.talk.localstack.dynamodb.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "order")
public class OrderEntity {

    private String id;

    private String trackDate;

    /**
     * Constructor mandatory by AWS-SDK for entity creation.
     * @See DynamoDBMapperTableModel.unconvert
     */
    public OrderEntity() {
    }

    public OrderEntity(final String id, final String trackDate) {
        this.id = id;
        this.trackDate = trackDate;
    }

    @DynamoDBHashKey(attributeName = "id")
    public String getId() {
        return id;
    }

    @DynamoDBRangeKey(attributeName = "track_date")
    public String getTrackDate() {
        return trackDate;
    }

    public OrderEntity id(final String id) {
        this.id = id;
        return this;
    }

    public OrderEntity trackDate(final String trackDate) {
        this.trackDate = trackDate;
        return this;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public void setTrackDate(final String trackDate) {
        this.trackDate = trackDate;
    }


}
