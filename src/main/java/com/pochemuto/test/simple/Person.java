package com.pochemuto.test.simple;

import java.lang.annotation.Documented;

import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Value
@Document(collection = "test")
public class Person {
    @Id
    String name;
}
