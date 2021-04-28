package com.pochemuto.test.simple;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "test")
public record Person(
        @Id String id,
        Instant updated
) {
}
