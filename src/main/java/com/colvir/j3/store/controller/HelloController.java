package com.colvir.j3.store.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

@RestController
@Api( tags = "Hello test")
public class HelloController {

    @RequestMapping(method = RequestMethod.GET,value = "/hello")
    public String firstPage() {
        return "Hello World";
    }

}
