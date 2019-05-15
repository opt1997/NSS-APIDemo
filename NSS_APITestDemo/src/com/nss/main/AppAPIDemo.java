package com.nss.main;

import com.nss.utils.SendAPIUtil;
import com.nss.utils.TakeTokenUtil;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;

import java.util.HashMap;
import java.util.Map;

public class AppAPIDemo {
    public static String token = null;
    public static void main(String[] args) {
        System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.Jdk14Logger");
        token = TakeTokenUtil.getToken("46d44df3", "a7316346-12c8-46a5-b535-ab0409c20620");
       /* remote_open_gateAPP(token);
        update_short_numAPP(token,"339","15915951599","qaq","1935");
        video_platform_login_statusAPP(token);
        get_veri_codeAPP(token);
        get_pendingAPP(token);*/
    }

    public static void remote_open_gateAPP(String token){
        String url ="/app/remote_open_gate";//设置API的相对url
        Map<String,Object> HeaderMap=new HashMap<>();//创建Header参数map
        HeaderMap.put("secretCode","");
        Map<String,Object> reqMap=new HashMap<>();//创建请求参数map
        reqMap.put("mobile","12345678910");
        reqMap.put("videoChatFlag",1);
        SendAPIUtil.sendAPI(url,reqMap,HeaderMap,token);
    }

    public static void video_platform_login_statusAPP(String token){
        String url ="/app/video_platform_login_status";//设置API的相对url
        Map<String,Object> HeaderMap=new HashMap<>();//创建Header参数map

        Map<String,Object> reqMap=new HashMap<>();//创建请求参数map
        //reqMap.put("deviceId",deviceId);
        reqMap.put("mobile","36477777777");
        reqMap.put("login",1);
        SendAPIUtil.sendAPI(url,reqMap,HeaderMap,token);
    }

    public static void update_short_numAPP(String token,String memberId,String mobile,String dialLabel,String shortNum){
        String url ="/app/update_short_num";//设置API的相对url
        Map<String,Object> HeaderMap=new HashMap<>();//创建Header参数map
        String secretCode="";
        HeaderMap.put("secretCode",secretCode);
        Map<String,Object> reqMap=new HashMap<>();//创建请求参数map
        reqMap.put("memberId",memberId);
        reqMap.put("mobile",mobile);
        reqMap.put("dialLabel",dialLabel);
        reqMap.put("shortNum",shortNum);
        SendAPIUtil.sendAPI(url,reqMap,HeaderMap,token);
    }

    public static void get_veri_codeAPP(String token){
        String url ="/app/get_veri_code";//设置API的相对url
        Map<String,Object> HeaderMap=new HashMap<>();//创建Header参数map
        Map<String,Object> reqMap=new HashMap<>();//创建请求参数map
        reqMap.put("mobile","12345678910");
        SendAPIUtil.sendAPI(url,reqMap,HeaderMap,token);
    }

    public static void get_pendingAPP(String token){
        String url ="/app/get_pending";//设置API的相对url
        Map<String,Object> HeaderMap=new HashMap<>();//创建Header参数map
        HeaderMap.put("secretCode","");
        Map<String,Object> reqMap=new HashMap<>();//创建请求参数map
        reqMap.put("mobile","12345678910");
        SendAPIUtil.sendAPI(url,reqMap,HeaderMap,token);
    }

}

