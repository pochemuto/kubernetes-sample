package com.pochemuto.test.simple;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfig {
    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create(
                MongoClientSettings.builder()
                        .applyToClusterSettings(builder ->
                                builder.hosts(List.of(new ServerAddress("mongo", 27018)))
                                .requiredReplicaSetName("rs01")
                        )
                        .applyToConnectionPoolSettings(builder ->
                                builder.maxConnectionIdleTime(5000, TimeUnit.MILLISECONDS))
                        .credential(MongoCredential.createCredential("user", "main",
                                "demon-funk-bittern".toCharArray()))
                        .build()
        );
    }
}
