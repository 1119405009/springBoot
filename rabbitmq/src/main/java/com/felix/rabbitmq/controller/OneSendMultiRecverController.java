package com.felix.rabbitmq.controller;

import com.felix.rabbitmq.scenes.onesendmultirecver.HelloOneSender;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/rabbit2")
@Api(value = "单生产者-多消费者",description = "")
public class OneSendMultiRecverController {
    @Autowired
    private HelloOneSender helloSender1;

    @ApiOperation("单生产者-多消费者")
    @RequestMapping(value = "/oneToMany",method = RequestMethod.GET)
    public void oneToMany() {
        for(int i=0;i<10;i++){
            helloSender1.send("hellomsg:"+i);
        }

    }
}
