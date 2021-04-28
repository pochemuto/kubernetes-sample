package com.pochemuto.test.simple;

import java.time.Instant;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ConnectionTest implements ApplicationRunner {
    private final TestRepository repository;

    @Override
    public void run(ApplicationArguments args) {
        Faker faker = new Faker();

        log.info("Total persons: {}", repository.count());
        var person = new Person(faker.funnyName().name(), Instant.now());
        repository.save(person);
        log.info("added {}", person.id());
    }
}
