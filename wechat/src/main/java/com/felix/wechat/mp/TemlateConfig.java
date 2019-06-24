package com.felix.wechat.mp;
/*
 * @Author felix
 * @Description //TODO $end$
 * @Date 14:56
 */

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class TemlateConfig {

    private  String mpTemlateRefund;

}
