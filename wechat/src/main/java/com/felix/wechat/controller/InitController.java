package com.felix.wechat.controller;


/*
 * @Author felix
 * @Description //TODO $
 * @Date 9:08
 */
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;
import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 */

@RestController
public class InitController {

    @Autowired
    WxMpService wxMpService;

    @Autowired
    WxMpConfigStorage wxMpConfigStorage;


    @Autowired
    WxMpMessageRouter  wxMpMessageRouter;

    /**
     * https://github.com/Wechat-Group/WxJava/wiki/MP_Quick-Start
     * @param response
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping
    public  String  initMethod(HttpServletResponse response,HttpServletRequest request) throws  Exception {

        // 设置编码utf-8否则乱码
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        String signature = request.getParameter("signature");
        String nonce = request.getParameter("nonce");
        String timestamp = request.getParameter("timestamp");

        if (!wxMpService.checkSignature(timestamp, nonce, signature)) {
            return "非法请求";
        }
        String echostr = request.getParameter("echostr");
        if (StringUtils.isNotBlank(echostr)) {
            // 说明是一个仅仅用来验证的请求，回显echostr
            return echostr;
        }

        String encryptType = StringUtils.isBlank(request.getParameter("encrypt_type")) ?
                "raw" :
                request.getParameter("encrypt_type");

        if ("raw".equals(encryptType)) {
            // 明文传输的消息
            WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(request.getInputStream());
            WxMpXmlOutMessage outMessage = wxMpMessageRouter.route(inMessage);
            if(outMessage == null) {
                //为null，说明路由配置有问题，需要注意
                return "路由配置有问题";
            }

            return outMessage.toXml();
        }
        if ("aes".equals(encryptType)) {
            // 是aes加密的消息
            String msgSignature = request.getParameter("msg_signature");
            WxMpXmlMessage inMessage = WxMpXmlMessage.fromEncryptedXml(request.getInputStream(), wxMpConfigStorage, timestamp, nonce, msgSignature);
            WxMpXmlOutMessage outMessage = wxMpMessageRouter.route(inMessage);
            if(outMessage == null) {
                //为null，说明路由配置有问题，需要注意
                return "路由配置有问题";
            }
            response.getWriter().write(outMessage.toEncryptedXml(wxMpConfigStorage));
            return outMessage.toEncryptedXml(wxMpConfigStorage);
        }
        return "不可识别的加密类型";
    }



    }








