package com.felix.rabbitmq.controller;


import com.felix.rabbitmq.scenes.callback.CallBackSender;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/rabbit7")
@Api(value = "带callback的消息",description = "带callback的消息")
public class CallbackController {

    @Autowired
    private CallBackSender callBackSender;

    @RequestMapping(value = "/callback",method = RequestMethod.GET)
    public void callbak() {
        callBackSender.send();
    }
}
