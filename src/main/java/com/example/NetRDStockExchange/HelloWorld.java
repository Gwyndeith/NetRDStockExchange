package com.example.NetRDStockExchange;

import org.springframework.web.bind.annotation.SessionAttributes;

@SessionAttributes("message")
public class HelloWorld {

    private String message;
    private String name;

    public HelloWorld(String message, String name) {
        this.message = message;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Hello! " + message + " " + name + ".";
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public String getName() {
        return name;
    }
}
