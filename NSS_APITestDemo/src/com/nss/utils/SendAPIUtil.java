package com.nss.utils;

import com.nss.main.GroupAPIDemo;
import com.nss.pojo.Result;
import java.util.Map;

public class SendAPIUtil {
    public static final String BASE_URL = "http://172.16.127.100:8080/nss-cloud2-api/api/v2";
    public static final String BASE_URL1 = "http://172.16.127.188:8088/nss-cloud2-api/api/v2";
    public static Result sendAPI(String rel_url
            ,Map<String,Object> reqMap , Map<String,Object> headerMap
            ,String token){
        String url = BASE_URL+rel_url;

        Result result = PostSendUtil.excute(url,reqMap,headerMap,token);

        return result;
    }
    public static Result sendAPI1(String rel_url
            ,Map<String,Object> reqMap , Map<String,Object> headerMap
            ,String token){
        String url = BASE_URL1+rel_url;

        Result result = PostSendUtil.excute(url,reqMap,headerMap,token);

        return result;
    }



}
