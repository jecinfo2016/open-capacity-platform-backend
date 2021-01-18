package com.open.capacity.uaa.server.controller;

import com.alibaba.fastjson.JSONObject;
import com.open.capacity.common.auth.details.LoginAppUser;
import com.open.capacity.common.util.StringUtil;
import com.open.capacity.common.util.SysUserUtil;
import com.open.capacity.common.web.Result;
import com.open.capacity.log.annotation.LogAnnotation;
import com.open.capacity.uaa.server.model.Message;
import com.open.capacity.uaa.server.service.ValidateCodeService;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

/**
 * 短信提供
 *
 * @author zzg
 * @date 2019/09/01
 */
@RestController
@SuppressWarnings("all")
public class SmsController {

    public final static String SYSMSG_LOGIN_PWD_MSG = "验证码：{0}，3分钟内有效";

    @Autowired
    private ValidateCodeService validateCodeService;

    @Autowired
    @Qualifier("restTemplate")
    private RestTemplate loadBalanced;

    @RequestMapping("/sms/send")
    @LogAnnotation(module = "auth-server", recordRequestParam = false)
    public Result sendSms(@RequestParam(value = "mobile", required = false) String mobile,
                          @RequestParam(value = "appId", required = false, defaultValue = "12") Integer appId) {
//		String content = SmsController.SYSMSG_LOGIN_PWD_MSG.replace("{0}", StringUtil.generateRamdomNum());
//        SendMsgResult sendMsgResult = MobileMsgConfig.sendMsg(mobile, content);

        LoginAppUser loginAppUser = SysUserUtil.getLoginAppUser();
        String calidateCode = StringUtil.generateRamdomNum();
        if (!mobile.contains("+86")) {
            mobile = "+86" + mobile;
        }

        //保存消息
        Message message = new Message();
        message.setTitle("验证码短信");
        message.setContent("验证码为：" + calidateCode + ",您正在登录，若非本人操作，请勿泄露。");//固定模板
        message.setType(0);
        message.setSendTime(new Date());
        message.setMobile(mobile);

        //发送人
        if (null != loginAppUser) {
            message.setSendUser(loginAppUser.getId().intValue());
        } else{
            //默认是管理员
            message.setSendUser(1);
        }
        //应用id 12为 默认值——认证中心
        message.setAppId(appId);

        try {
            String s = loadBalanced.postForObject("http://message-center/message/save", message, String.class);
            JSONObject json = JSONObject.parseObject(s);
            Integer code = json.getInteger("resp_code");
            if (code == 0) {
                System.out.println("保存成功！");
            }
        } catch (RestClientException e) {
            e.printStackTrace();
            System.out.println("保存消息失败！");
        }

        //发送短信
        try {

            Credential cred = new Credential("AKID1GOL7srhy54vgMyBLPJk28QBCB3knFyd", "fAUdmT1ObmndS9kGbkHgwBFAwYmiV6nJ");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("sms.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            SmsClient client = new SmsClient(cred, "", clientProfile);

            String params = "{\"PhoneNumberSet\":[" + mobile + "],\"TemplateID\":\"620058\",\"Sign\":\"浙江嘉科信息科技有限公司\",\"TemplateParamSet\":[" + calidateCode + "],\"SmsSdkAppid\":\"1400378055\"}";
            SendSmsRequest req = SendSmsRequest.fromJsonString(params, SendSmsRequest.class);

            SendSmsResponse resp = client.SendSms(req);

            validateCodeService.saveImageCode(mobile, calidateCode);

        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
        return Result.succeed(calidateCode, "发送成功");
    }

}
