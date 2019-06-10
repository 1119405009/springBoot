package com.felix.rabbitmq.scenes.moreSendMoreRecever;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.stereotype.Component;

@Component(value = "msmr1")
public class HelloMoreReceiver1 {
    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver1  : " + hello);
    }
}
