package com.felix.rabbitmq.scenes.entityTransfer;

import com.felix.rabbitmq.model.Msg;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class MsgSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(Msg msg) {
        System.out.println("msg send : " + msg.toString());
        this.rabbitTemplate.convertAndSend("msgQueue", msg);
    }
}
