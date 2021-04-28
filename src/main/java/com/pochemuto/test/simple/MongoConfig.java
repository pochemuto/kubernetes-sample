package com.pochemuto.test.simple;

import java.util.List;

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
                                builder.hosts(List.of(
                                        new ServerAddress("rc1b-wuaj5x303k5431f7.mdb.yandexcloud.net", 27018)
                                )))
                        .applyToSslSettings(builder ->
                                builder.enabled(true))
                        .credential(MongoCredential.createCredential("user", "main",
                                "demon-funk-bittern".toCharArray()))
                        .build()
        );
    }
}
