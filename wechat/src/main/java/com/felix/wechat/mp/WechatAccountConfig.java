package com.felix.wechat.mp;
/*
 * @Author felix
 * @Description //TODO $end$
 * @Date 14:23
 */

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {
    /*公众号appid*/
    private String mpAppId;
    /*公众号secret*/
    private String mpAppSecret;

    private  String mpToken;

}
