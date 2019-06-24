package com.felix.logaop.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.StrUtil;

@RestController
public class AopController {

    @GetMapping("/test")
    public Dict test(String who) {
        System.out.println(who);
        return Dict.create().set("who", StrUtil.isBlank(who) ? "me" : who);
    }



}
