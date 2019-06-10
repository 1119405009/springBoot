package com.felix.rabbitmq.scenes.entityTransfer;

import com.felix.rabbitmq.model.Msg;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "msgQueue")
public class MsgReceiver {

    @RabbitHandler
    public void process(Msg msg) throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("user receive  : " + "id->"+msg.getId()+", content->"+msg.getContent());
    }

}
