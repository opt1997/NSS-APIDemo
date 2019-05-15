package com.nss.utils;

import com.alibaba.fastjson.JSON;
import com.nss.main.GroupAPIDemo;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TakeTokenUtil {
    //设置获取token的入参
    public static String getToken(String clientId,String clientSecret){

        String url = SendAPIUtil.BASE_URL+"/auth/get_token";
        Map<String,Object> map=new HashMap<>();
        map.put("clientId",clientId);
        map.put("clientSecret",clientSecret);
        return  doPostforToken(url,map);

    }
    //发送请求获取token字符串
    public static String doPostforToken(String url, Object object) {
        // 获得Http客户端
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 创建Post请求
        HttpPost httpPost = new HttpPost(url);
        // 封装正文
        StringEntity entity = new StringEntity(JSON.toJSONString(object), "UTF-8");
        // post请求是将参数放在请求体里面传过去的;这里将entity放入post请求体中
        httpPost.setEntity(entity);
        httpPost.setHeader("Content-Type", "application/json;charset=utf8");

        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Post请求
            response = httpClient.execute(httpPost);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            if(response.getStatusLine().getStatusCode()==200){
                System.out.print("已获得token:");
                if (responseEntity != null) {
                    System.out.println(response.getFirstHeader("accessToken").toString().substring(13));
                    return response.getFirstHeader("accessToken").toString().substring(13);
                }
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
