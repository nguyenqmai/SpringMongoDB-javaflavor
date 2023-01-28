package com.yapr.dataservice.javaflavor.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Collection;
import java.util.Collections;

@Configuration
@EnableMongoRepositories(basePackages = "com.yapr.dataservice.javaflavor")
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Value("${mongodb.db:}")
    private String mongoDbName;

    @Value("${mongodb.connection:}")
    private String mongoDbConnection;

    @Override
    protected String getDatabaseName() {
        return mongoDbName;
    }

    @Override
    public MongoClient mongoClient() {
        ConnectionString connectionString = new ConnectionString(mongoDbConnection);
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        return MongoClients.create(mongoClientSettings);
    }

    @Override
    public Collection getMappingBasePackages() {
        return Collections.singleton("com.yapr.dataservice.javaflavor.persistence");
    }
}