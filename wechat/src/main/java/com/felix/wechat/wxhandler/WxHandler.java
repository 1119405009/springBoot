package com.felix.wechat.wxhandler;
/*
 * @Author felix
 * @Description //TODO $end$
 * @Date 15:33
 */


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.felix.wechat.utils.GsonUtil;
import com.felix.wechat.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;

import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.AbstractSet;
import java.util.Map;

@Slf4j
@Component
public class WxHandler implements WxMpMessageHandler {

    @Autowired
    ObjectMapper objectMapper;
    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage, Map<String, Object> map, WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {
        log.info("\n接收到请求消息，内容：{}", wxMpXmlMessage);
        return null;
    }
}
