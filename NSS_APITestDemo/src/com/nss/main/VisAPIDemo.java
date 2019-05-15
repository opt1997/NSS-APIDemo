package com.nss.main;

import com.nss.utils.SendAPIUtil;
import com.nss.utils.TakeTokenUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VisAPIDemo {
    public static String token = null;
    public static void main(String[] args) {
        System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.Jdk14Logger");
        token = TakeTokenUtil.getToken("46d44df3", "a7316346-12c8-46a5-b535-ab0409c20620");
        //token = TakeTokenUtil.getToken("8cafd773", "6ef3dea8-e422-4ccb-af1d-97c9be6aaf27 ");
        get_visitor_sync_listVis(token);
      //  MemberAPIDemo.createMember(token);
        create_appointmentVis(token);

    }

    /**
     * @description 访客下发记录查询
     * @param token
     */
    public static void get_visitor_sync_listVis(String token){
        String url ="/device/get_visitor_sync_list";//设置API的相对url
        Map<String,Object> headerMap=new HashMap<>();//创建Header参数map
        Map<String,Object> reqMap=new HashMap<>();//创建请求参数map
        reqMap.put("syncStatus",1);
        SendAPIUtil.sendAPI(url,reqMap,headerMap,token);
    }
    /**
     * @description 添加来访信息
     * @param token
     */
    public static void create_appointmentVis(String token){

        String url ="/vis/create_appointment";//设置API的相对url
        Map<String,Object> headerMap=new HashMap<>();//创建Header参数map
        Map<String,Object> reqMap=new HashMap<>();//创建请求参数map
        reqMap.put("memberMobile","36477777777");
        reqMap.put("visitorMobile","36477777777");
        reqMap.put("visitorName","group363");
        reqMap.put("visitorOrgName","新辰海");
        reqMap.put("visitorFaceImgFileId","ff8081816a38f6b4016ab3f11295387a");
        reqMap.put("visitorCertNo","320123199102523641");
        List<String> list = new ArrayList<String>();
        list.add("ff8081816a38f6b4016ab52f2e5a4299");
        reqMap.put("deviceIdList",list);
        reqMap.put("visitDate","2019-05-15");
        reqMap.put("startTime","11:12:00");
        reqMap.put("duringTime","6");
        reqMap.put("timeRange","3");
        reqMap.put("memo","参观新辰海科技");
        //reqMap.put("meetingRoomName","会议室1");

        SendAPIUtil.sendAPI(url,reqMap,headerMap,token);
    }
}
