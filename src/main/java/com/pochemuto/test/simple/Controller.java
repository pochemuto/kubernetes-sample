package com.pochemuto.test.simple;

import java.time.Instant;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
        return System.getenv("HOSTNAME");
    }

    @GetMapping({"add/{name}", "add"})
    public String add(@PathVariable(required = false) Optional<String> name) {
        log.info("Got name: " + name);
        var person = new Person(name.orElse("nobody"), Instant.now());
        repository.save(person);
        return person.id() + " was added at " + person.updated();
    }
}
