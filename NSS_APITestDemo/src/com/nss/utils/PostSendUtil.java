package com.nss.utils;
import com.alibaba.fastjson.JSON;
import com.nss.pojo.Result;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Map;
public class PostSendUtil {
    /**
     * @discription 把此API发送给服务器
     * @param url API的URL地址
     * @param object 此API的入参
     * @param headerMap 此API的Header参数
     * @param token 此API携带的token令牌
     * @return 返回一个结果集Result对象：该对象由返回的JSON字符串转化而来
     */
    public static Result excute(String url
            , Object object, Map<String,Object> headerMap
            , String token) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();  // 获得Http客户端
        HttpPost httpPost = new HttpPost(url);// 创建Post请求
        //设置ContentType(注:如果只是传普通参数的话,ContentType不一定非要用application/json)
        httpPost.setHeader("Content-Type", "application/json;charset=utf8");
        httpPost.setHeader("accessToken", token);
        for(Map.Entry<String,Object> entry : headerMap.entrySet()){
            httpPost.setHeader(entry.getKey(),""+entry.getValue());
        }
        //设置正文实体
        StringEntity entity = new StringEntity(JSON.toJSONString(object), "UTF-8");
        httpPost.setEntity(entity);
        CloseableHttpResponse response = null; // 响应模型
        try {
            // 由客户端执行(发送)Post请求
            response = httpClient.execute(httpPost);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            //ystem.out.println(response.getStatusLine().getStatusCode());
            if(response.getStatusLine().getStatusCode()!=200){
                System.out.println("错误代码："+response.getStatusLine().getStatusCode());
                return null;
            }
            if (responseEntity != null) {
                String strEntity =  EntityUtils.toString(responseEntity);
                System.out.println(url.split("/")[url.split("/").length-1]+"返回参数:");
                JSONObject jsonObject = JSONObject.fromObject(strEntity);
                System.out.println(jsonObject);
                Result result = (Result) JSONObject.toBean(jsonObject,Result.class);
                return result;
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
