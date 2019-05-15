/**
 * create返回参数:
 * {"code":0,"message":"Normal done"}
 * update返回参数:
 * {"code":0,"message":"Normal done"}
 * delete返回参数:
 * {"code":0,"message":"Normal done"}
 */
package com.nss.main;

import com.nss.utils.SendAPIUtil;
import com.nss.utils.TakeTokenUtil;

import java.util.HashMap;
import java.util.Map;

public class CallbackAPIDemo {
    public static String token = null;
    public static int tempId = 339;//创建分组的默认序号

    public static void main(String[] args) {
        System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.Jdk14Logger");
        //token = TakeTokenUtil.getToken("46d44df3", "a7316346-12c8-46a5-b535-ab0409c20620");
        token = "d48db8fb-2eef-44cb-8203-136c8cced949";
        createCallback(token);
        updateCallback(token);
        deleteCallback(token);
    }

    /**
     * @description 创建回调
     * @param token
     */
    public static void createCallback(String token){
        String url ="/callback/create";//设置API的相对url
        Map<String,Object> HeaderMap=new HashMap<>();//创建Header参数map
        Map<String,Object> reqMap=new HashMap<>();//创建请求参数map
        reqMap.put("callbackUrl","http://172.16.127.100:8080/nss-cloud2-api/api/v2/group/create");
        reqMap.put("callbackTag",13);
        SendAPIUtil.sendAPI(url,reqMap,HeaderMap,token);
    }
    /**
     * @description 更新回调
     * @param token
     */
    public static void updateCallback(String token){
        String url ="/callback/update";//设置API的相对url
        Map<String,Object> HeaderMap=new HashMap<>();//创建Header参数map
        Map<String,Object> reqMap=new HashMap<>();//创建请求参数map
        reqMap.put("callbackUrl","http://172.16.127.100:8080/nss-cloud2-api/api/v2/group/create");
        reqMap.put("callbackTag",13);
        SendAPIUtil.sendAPI(url,reqMap,HeaderMap,token);
    }
    /**
     * @description 删除回调
     * @param token
     */
    public static void deleteCallback(String token){
        String url ="/callback/delete";//设置API的相对url
        Map<String,Object> HeaderMap=new HashMap<>();//创建Header参数map
        Map<String,Object> reqMap=new HashMap<>();//创建请求参数map
        reqMap.put("callbackUrl","http://172.16.127.100:8080/nss-cloud2-api/api/v2/group/create");
        reqMap.put("callbackTag",13);
        SendAPIUtil.sendAPI(url,reqMap,HeaderMap,token);
    }

}
