package com.pochemuto.test.simple;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
        log.info("Total persons: {}", repository.count());
        repository.save(new Person("test"));
        log.info("saved");
    }
}
