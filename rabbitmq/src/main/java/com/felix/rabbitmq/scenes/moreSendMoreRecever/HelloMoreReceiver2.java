package com.felix.rabbitmq.scenes.moreSendMoreRecever;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.stereotype.Component;


@Component(value = "msmr2")
public class HelloMoreReceiver2 {
    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver2  : " + hello);
    }

}
