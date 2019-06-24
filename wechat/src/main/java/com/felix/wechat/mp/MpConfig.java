package com.felix.wechat.mp;
/*
 * @Author felix
 * @Description //TODO $end$
 * @Date 14:31
 */

import com.felix.wechat.wxhandler.UnsubscribeHandler;
import com.felix.wechat.wxhandler.WxHandler;
import lombok.Data;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class MpConfig {


    @Autowired
    WechatAccountConfig wechatAccountConfig;
    @Autowired
    WxHandler wxHandler;

    @Autowired
    UnsubscribeHandler unsubscribeHandler;
    @Bean
    @ConditionalOnMissingBean
    public WxMpConfigStorage configStorage() {
        WxMpInMemoryConfigStorage configStorage = new WxMpInMemoryConfigStorage();
        configStorage.setAppId(wechatAccountConfig.getMpAppId());
        configStorage.setSecret(wechatAccountConfig.getMpAppSecret());
        configStorage.setToken(wechatAccountConfig.getMpToken());
        return configStorage;
    }

    @Bean
    @ConditionalOnMissingBean
    public WxMpService wxMpService(WxMpConfigStorage configStorage) {

        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(configStorage);
        return wxMpService;
    }

    @Bean
    public WxMpMessageRouter router(WxMpService wxMpService) {

        final WxMpMessageRouter newRouter = new WxMpMessageRouter(wxMpService);
        newRouter.rule().handler(this.wxHandler).next();
        // 取消关注事件

        newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT).event(WxConsts.EventType.UNSUBSCRIBE)
                .handler(this.getUnsubscribeHandler()).end();

        return newRouter;
    }




}
