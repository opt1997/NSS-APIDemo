package com.nss.main;

import com.nss.pojo.Result;
import com.nss.utils.SendAPIUtil;
import com.nss.utils.TakeTokenUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeviceAPIDemo {
    public static String token = null;
    public static String token1 = null;
    public static String orgId = "";
    public static int tempId = 300;

    public static void main(String[] args) {
        System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.Jdk14Logger");
        token = TakeTokenUtil.getToken("46d44df3", "a7316346-12c8-46a5-b535-ab0409c20620");
        String deviceId1 = createDevice(token);//创建设备
        List<String> deviceIdList1 = new ArrayList<String>();
        deviceIdList1.add(deviceId1);
        remote_ctrlDevice(token,deviceId1);//远程控制设备
        get_remote_ctrl_resultDevice(token,deviceId1);//获取设备配置（含配置分类信息）
        updateDevice(token,deviceId1);//更新设备
        update_confDevice(token,deviceId1);//设备上传配置信息
        deleteDevice(token,deviceIdList1);//删除设备
        //以下接口使用172.16.127.188:8088服务器
        token1 = TakeTokenUtil.getToken1("86e2ceaf", "33c59981-cf6b-4693-9078-8ae233071e69");//获取token
        String deviceId ="2c90ff3c6abfef01016abff34630003d";
        List<String> deviceIdList = new ArrayList<String>();
        deviceIdList.add(deviceId);
        fetch_confDevice(token1,deviceId,orgId);//设备配置获取(设备用，无配置分类信息)
        set_device_access_passwdDevice(token1,deviceIdList);//get_device_conf_listDevice
        get_device_conf_listDevice(token1,deviceId);//修改设备固定密码
    }

    /**
     * @description 创建设备
     * @param token
     * @return
     */
    public static String createDevice(String token){
        String url ="/device/create";
        Result result = new Result();
        Map<String,Object> headerMap=new HashMap<>();//创建Header参数map
        String authKey = "";
        headerMap.put("authKey",authKey);
        Map<String,Object> reqMap=new HashMap<>();//创建请求参数map
        //reqMap.put("deviceId",tempId);
        reqMap.put("deviceCode",""+tempId);
        reqMap.put("deviceName","d"+tempId++);
        reqMap.put("deviceType",0);
        reqMap.put("position","NJ");
        List<String> tagNameList = new ArrayList<String>();
        tagNameList.add("1");
        reqMap.put("tagName",tagNameList);
        reqMap.put("memo",2);
        reqMap.put("extraData1","..1");
        //把请求发送给服务器，然后接收返回Result对象并打印到控制台
        result = SendAPIUtil.sendAPI(url, reqMap, headerMap, token);
        orgId = result.getData().getOrgId();
        return result.getData().getDeviceId();

    }

    /**
     * @description 更新设备
     * @param token
     * @param deviceId
     */
    public static void updateDevice(String token,String deviceId){
        String url ="/device/update";

        Map<String,Object> headerMap=new HashMap<>();//创建Header参数map
        Map<String,Object> reqMap=new HashMap<>();//创建请求参数map
        reqMap.put("deviceId",deviceId);
        reqMap.put("deviceName","d"+tempId++);
        reqMap.put("deviceType",0);
        //把请求发送给服务器，并打印到控制台
        SendAPIUtil.sendAPI(url,reqMap,headerMap,token);

    }

    /**
     * @description 删除设备
     * @param token
     * @param deviceIdList
     */
    public static void deleteDevice(String token,List<String> deviceIdList){
        String url ="/device/delete";

        Map<String,Object> headerMap=new HashMap<>();//创建Header参数map
        Map<String,Object> reqMap=new HashMap<>();//创建请求参数map
        reqMap.put("deviceIdList",deviceIdList);
        //把请求发送给服务器，然后接收返回Result对象并打印到控制台
        SendAPIUtil.sendAPI(url,reqMap,headerMap,token);
    }

    /**
     * @description 设备配置获取(设备用，无配置分类信息)
     * @param token
     * @param deviceId
     * @param orgId
     */
    public static void fetch_confDevice(String token,String deviceId,String orgId){
        String url ="/device/fetch_conf";

        Map<String,Object> headerMap=new HashMap<>();//创建Header参数map
        //  headerMap.put("orgId",orgId);
        Map<String,Object> reqMap=new HashMap<>();//创建请求参数map
        reqMap.put("deviceId",deviceId);
        //把请求发送给服务器，然后接收返回Result对象并打印到控制台
        SendAPIUtil.sendAPI1(url,reqMap,headerMap,token);
    }

    /**
     * @description 获取设备配置（含配置分类信息）
     * @param token
     * @param deviceId
     */
    public static void get_device_conf_listDevice(String token,String deviceId){
        String url ="/device/get_device_conf_list";
        Map<String,Object> headerMap=new HashMap<>();//创建Header参数map
        Map<String,Object> reqMap=new HashMap<>();//创建请求参数map
        reqMap.put("deviceId",deviceId);
        //把请求发送给服务器，然后接收返回Result对象并打印到控制台
        SendAPIUtil.sendAPI1(url,reqMap,headerMap,token);
    }

    /**
     * @description 设备上传配置信息
     * @param token
     * @param deviceId
     */
    public static void update_confDevice(String token,String deviceId){
        String url ="/device/update_conf";
        Map<String,Object> headerMap=new HashMap<>();//创建Header参数map
        Map<String,Object> reqMap=new HashMap<>();//创建请求参数map
        reqMap.put("deviceId",deviceId);
        List<String> confList = new ArrayList<String>();
        reqMap.put("confList",confList);
        reqMap.put("category","配置类型1");
        reqMap.put("categoryOrder",1);
        reqMap.put("label","配置项名称1");
        reqMap.put("k","1");
        reqMap.put("v","下发成功");
        reqMap.put("type",1);
        reqMap.put("defaultValue",deviceId);
        reqMap.put("desc","配置项描述1");
        reqMap.put("sortOrder",1);
        //把请求发送给服务器，然后接收返回Result对象并打印到控制台
        SendAPIUtil.sendAPI(url,reqMap,headerMap,token);
    }

    /**
     * @description 远程控制设备
     * @param token
     * @param deviceId
     */
    public static void remote_ctrlDevice(String token,String deviceId){
        String url ="/device/remote_ctrl";
        Map<String,Object> headerMap=new HashMap<>();//创建Header参数map
        Map<String,Object> reqMap=new HashMap<>();//创建请求参数map
        reqMap.put("deviceId",deviceId);
        reqMap.put("ctrlCode",3);

        //把请求发送给服务器，然后接收返回Result对象并打印到控制台
        SendAPIUtil.sendAPI(url,reqMap,headerMap,token);
    }

    /**
     * @description 获取远程控制设备结果
     * @param token
     * @param deviceId
     */
    public static void get_remote_ctrl_resultDevice(String token,String deviceId){
        String url ="/device/get_remote_ctrl_result";
        Map<String,Object> headerMap=new HashMap<>();//创建Header参数map
        Map<String,Object> reqMap=new HashMap<>();//创建请求参数map
        reqMap.put("deviceId",deviceId);
        //把请求发送给服务器，然后接收返回Result对象并打印到控制台
        SendAPIUtil.sendAPI(url,reqMap,headerMap,token);
    }

    /**
     * @description 修改设备固定密码
     * @param token
     * @param deviceIdList
     */
    public static void set_device_access_passwdDevice(String token,List<String> deviceIdList){
        String url ="/device/set_device_access_passwd";
        Map<String,Object> headerMap=new HashMap<>();//创建Header参数map
        Map<String,Object> reqMap=new HashMap<>();//创建请求参数map
        reqMap.put("deviceIdList",deviceIdList);
        reqMap.put("passwd","123456");
        //把请求发送给服务器，然后接收返回Result对象并打印到控制台
        SendAPIUtil.sendAPI1(url,reqMap,headerMap,token);
    }

}
