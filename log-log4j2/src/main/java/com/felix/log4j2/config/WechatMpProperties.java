package com.felix.log4j2.config;




import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 微信公众号相关配置
 *
 * @author teruo
 */

@Getter
@Setter
@ConfigurationProperties(prefix = "business.config.wechat.mp")
@Configuration
public class WechatMpProperties {


  private String wechat;

  private String lockTicket;


  private String token;


  private String commit;


  private String refundTicket;


  private String insuranceCreate;


  private String insuranceCancel;


  private String payNotify;


  private String serviceNotice;


  private String  payCreate;


  private String  payCancel;

  private String   payRefund;

}
