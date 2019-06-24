package com.felix.exceptioninterceptor.controller;


import com.felix.exceptioninterceptor.exception.UserNotExistException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @GetMapping("/{id:\\d+}")
    public void get(@PathVariable String id) {
        throw new UserNotExistException(id);
    }

    @GetMapping("test")
    public void  getTest() throws Exception{
        throw new Exception("");
    }
}
