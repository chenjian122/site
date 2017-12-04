package com.sinspark.site.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cj
 * @create 2017-12-04 15:46
 **/
@RestController
public class HelloWorldController {

    @GetMapping("/doHello")
    public String doHello(){
        return "Hello Jenkins and Docker";
    }

}
