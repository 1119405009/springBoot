package com.felix.wechat.controller;
/*
 * @Author felix
 * @Description //TODO $end$
 * @Date 14:25
 */


import com.felix.wechat.mp.TemlateConfig;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 *微信模板控制器
 */
@Controller
public class WxMpTemplateController {

    @Autowired
    WxMpService wxMpService;

    @Autowired
    TemlateConfig temlateConfig;


    @GetMapping("/send")
    @ResponseBody
    public  String sendTemplate(){
        List<WxMpTemplateData> data = new ArrayList<WxMpTemplateData>();
        data.add(new WxMpTemplateData("first","退款通知","#FF00FF"));
        data.add(new WxMpTemplateData("reason","不适合","#FF00FF"));
        data.add(new WxMpTemplateData("refund","50.0","#FF00FF"));
        data.add(new WxMpTemplateData("remark","等待审批,处理结果稍后通知给你","#FF00FF"));
        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(
                    new WxMpTemplateMessage(
                            "oVi4454whyIiuapy4xgFhAkGrLOA",
                            temlateConfig.getMpTemlateRefund(),
                            "https://www.blogwiki.top",
                            null,
                            data
                    )
            );
        } catch (WxErrorException e) {
            e.printStackTrace();
            return "发送模板消息失败！";
        }
      return "发送模板消息成功！";
    }
}
