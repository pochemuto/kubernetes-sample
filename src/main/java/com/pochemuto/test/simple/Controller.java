package com.pochemuto.test.simple;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @GetMapping({"add/{name}", "add"})
    public String add(@PathVariable(required = false) Optional<String> name) {
        String person = name.orElse("nobody");
        return person + " was added";
    }
}
