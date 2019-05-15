/**
 * 正常执行时最终打印的是：
 * 已获得token:accessToken: 7d7ef6bc-de3e-4164-a572-544f5cd8f1dc
 * 7d7ef6bc-de3e-4164-a572-544f5cd8f1dc
 * create返回参数:
 * {"code":0,"message":"Normal done","data":{"groupId":"613"}}
 * create返回参数:
 * {"code":0,"message":"Normal done","data":{"deviceId":"245","orgId":"ff8081816a38f6b4016a6cc1f62a25c4"}}
 * create返回参数:
 * {"code":0,"message":"Normal done","data":{"groupId":"614"}}
 * create返回参数:
 * {"code":0,"message":"Normal done","data":{"deviceId":"246","orgId":"ff8081816a38f6b4016a6cc1f62a25c4"}}
 * bind_devices返回参数:
 * {"code":0,"message":"Normal done"}
 * bind_groups返回参数:
 * {"code":0,"message":"Normal done"}
 * unbind_devices返回参数:
 * {"code":0,"message":"Normal done"}
 * unbind_groups返回参数:
 * {"code":0,"message":"Normal done"}
 */
package com.nss.main;

import com.nss.pojo.Result;
import com.nss.utils.SendAPIUtil;
import com.nss.utils.TakeTokenUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PermissionAPIDemo {

    public static String token = null;
    public static void main(String[] args) {
        System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.Jdk14Logger");
        token = TakeTokenUtil.getToken("46d44df3", "a7316346-12c8-46a5-b535-ab0409c20620");

        String groupId = GroupAPIDemo.createGroup(token);
        String deviceId = DeviceAPIDemo.createDevice(token);
        List<String> groupIdList = new ArrayList<>();
        groupIdList.add(GroupAPIDemo.createGroup(token));
        List<String> deviceIdList = new ArrayList<>();
        deviceIdList.add(DeviceAPIDemo.createDevice(token));
        bind_devicesPermission(token,groupId,deviceIdList);
        bind_groupsPermission(token,deviceId,groupIdList);
        unbind_devicesPermission(token,groupId,deviceIdList);
        unbind_groupsPermission(token,deviceId,groupIdList);
    }

    /**
     * @description 设置分组权限（添加设备到分组）
     * @param token
     * @param groupId 组Id
     * @param deviceIdList 设备Id列表
     */
    public static void bind_devicesPermission(String token,String groupId,List<String> deviceIdList){
        Result bind_devicesResult = new Result();//API的返回结果
        String url ="/permission/bind_devices";//设置API的相对url
        Map<String,Object> headerMap=new HashMap<>();//创建Header参数map
        Map<String,Object> reqMap=new HashMap<>();//创建请求参数map
        reqMap.put("groupId",groupId);
        reqMap.put("deviceIdList",deviceIdList);
        SendAPIUtil.sendAPI(url,reqMap,headerMap,token);
    }
    /**
     * @description 设置分组权限（移除设备到分组）
     * @param token
     * @param groupId 组Id
     * @param deviceIdList 设备Id列表
     */
    public static void unbind_devicesPermission(String token,String groupId,List<String> deviceIdList){
        Result bind_devicesResult = new Result();//API的返回结果
        String url ="/permission/unbind_devices";//设置API的相对url
        Map<String,Object> headerMap=new HashMap<>();//创建Header参数map
        Map<String,Object> reqMap=new HashMap<>();//创建请求参数map
        reqMap.put("groupId",groupId);
        reqMap.put("deviceIdList",deviceIdList);
        SendAPIUtil.sendAPI(url,reqMap,headerMap,token);
    }
    /**
     * @description 设置设备权限（添加分组到设备）
     * @param token
     * @param deviceId 设备Id
     * @param groupIdList 分组Id列表
     */
    public static void bind_groupsPermission(String token,String deviceId,List<String> groupIdList){
        Result bind_devicesResult = new Result();//API的返回结果
        String url ="/permission/bind_groups";//设置API的相对url
        Map<String,Object> headerMap=new HashMap<>();//创建Header参数map
        Map<String,Object> reqMap=new HashMap<>();//创建请求参数map
        reqMap.put("deviceId",deviceId);
        reqMap.put("groupIdList",groupIdList);
        SendAPIUtil.sendAPI(url,reqMap,headerMap,token);
    }
    /**
     * @description 设置设备权限（移除分组到设备）
     * @param token
     * @param deviceId 设备Id
     * @param groupIdList 分组Id列表
     */
    public static void unbind_groupsPermission(String token,String deviceId,List<String> groupIdList){
        Result bind_devicesResult = new Result();//API的返回结果
        String url ="/permission/unbind_groups";//设置API的相对url
        Map<String,Object> headerMap=new HashMap<>();//创建Header参数map
        Map<String,Object> reqMap=new HashMap<>();//创建请求参数map
        reqMap.put("deviceId",deviceId);
        reqMap.put("groupIdList",groupIdList);
        SendAPIUtil.sendAPI(url,reqMap,headerMap,token);
    }

}

