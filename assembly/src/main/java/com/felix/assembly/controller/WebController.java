package com.felix.assembly.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/web")
public class WebController {



    @RequestMapping
    public ResponseEntity<?> hello(){
        return new ResponseEntity<Object>("hello", HttpStatus.OK);
    }
}
