package com.felix.log4j2.controller;


import com.felix.log4j2.config.WechatMpProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Log4j2Controller {

     private static final Logger notifyLog = LogManager.getLogger("payNotify");
     private static final Logger createLog = LogManager.getLogger("payCreate");
     private static final Logger cancelLog = LogManager.getLogger("payCancel");
     private static final Logger refundLog = LogManager.getLogger("payRefund");

     @Autowired
     private WechatMpProperties properties;


     @GetMapping("/test")
     public void test(){
          notifyLog.error("===>>"+properties.getPayNotify());
          createLog.error("===>>"+properties.getPayCreate());
          cancelLog.error("===>>"+properties.getPayCancel());
          refundLog.error("===>>"+properties.getPayRefund());
     }
}
