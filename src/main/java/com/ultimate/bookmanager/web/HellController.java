package com.ultimate.bookmanager.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HellController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
