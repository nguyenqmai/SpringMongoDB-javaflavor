package com.yapr.dataservice.javaflavor.persistence;

import com.yapr.dataservice.javaflavor.model.MediaItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.Collection;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Repository
public class MediaRepositoryExtraImpl implements MediaRepositoryExtra {

    private final MongoOperations operations;


    @Autowired
    public MediaRepositoryExtraImpl(MongoOperations operations) {

        Assert.notNull(operations);
        this.operations = operations;
    }

    @Override
    public MediaItem findByOwnerId(Long ownerId) {
        Query query = query(where("ownerId").is(ownerId));
        return operations.findOne(query, MediaItem.class);
    }

    @Override
    public Collection<String> getDataCollectionNames() {
        return this.operations.getCollectionNames();
    }
}