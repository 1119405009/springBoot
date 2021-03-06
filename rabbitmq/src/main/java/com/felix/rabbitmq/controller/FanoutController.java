package com.felix.rabbitmq.controller;

import com.felix.rabbitmq.scenes.fanoutExChange.FanoutSender;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/rabbit6")
@Api(value = "广播模式或者订阅模式",description = "广播模式或者订阅模式")
public class FanoutController {

    @Autowired
    private FanoutSender fanoutSender;

    @RequestMapping(value = "/fanout",method = RequestMethod.GET)
    public void fanoutTest() {
        fanoutSender.send();
    }
}
