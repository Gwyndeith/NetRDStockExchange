package com.example.NetRDStockExchange;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@SessionAttributes("message")
@RestController
public class HelloWorldController {

    @RequestMapping(method = RequestMethod.GET, path = "/hello/{name}")
    public String helloWorld(@PathVariable String name) {
        return String.format("Hello %s!" , name);
    }

//    @RequestMapping(method = RequestMethod.GET, path = "/hello")
//    public String helloWorld() {
//        return "Hello World!";
//    }

    @RequestMapping(method = RequestMethod.GET, path = "/hello")
    public String helloWorld(@PathParam("message") String message, @PathParam("name") String name, ModelMap modelMap) {

        modelMap.put("message", message);

        return new HelloWorld(message, name).toString();

    }
}
