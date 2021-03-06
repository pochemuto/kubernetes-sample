package com.pochemuto.test.simple;

import java.time.Instant;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class Controller {
    private final TestRepository repository;

    @GetMapping("ping")
    public String ping() {
        return "Hello, Nastya";
    }

    @GetMapping({"add/{name}", "add"})
    public String add(@PathVariable(required = false) Optional<String> name) {
        log.info("Got name: " + name);
        var person = new Person(name.orElse("unknown"), Instant.now());
        repository.save(person);
        return person.id() + " was added at " + person.updated();
    }
}
