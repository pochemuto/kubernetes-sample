package com.pochemuto.test.simple;

import java.time.Instant;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class Controller {
    private final TestRepository repository;

    @GetMapping({"add/{name}", "add"})
    public String add(@PathVariable(required = false) Optional<String> name) {
        var person = new Person(name.orElse("nobody"), Instant.now());
        repository.save(person);
        return person.id() + " was added at " + person.updated();
    }
}
