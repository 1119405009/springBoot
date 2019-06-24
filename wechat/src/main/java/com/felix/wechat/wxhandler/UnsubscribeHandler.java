package com.felix.wechat.wxhandler;
/*
 * @Author felix
 * @Description //TODO $end$
 * @Date 15:57
 */


import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class UnsubscribeHandler  implements WxMpMessageHandler {
    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage, Map<String, Object> map, WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {
        log.info(wxMpXmlMessage.toString());
        log.info("map",map);
        log.info("wxMpService", wxMpService.getUserService().toString());
        log.info("wxSessionManager", wxSessionManager);
        return null;
    }
}
