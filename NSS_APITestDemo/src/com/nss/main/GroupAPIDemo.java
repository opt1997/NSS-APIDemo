/**
 * 演示了：
 * 调用"创建小组"的API创建3个小组，分别是：
 *  ID为"2601"，组名为"group2601"的小组。
 *  ID为"2602"，组名为"group2602"的小组。
 *  ID为"2603"，组名为"group2603"的小组。
 * 调用"更新小组"的API，把ID为"2601"的小组的组名变为"group1062"。
 * ("更新小组"的API的核心入参从"创建小组"API的返回参数中获取。)
 * 调用"更新小组配置"的API，更新ID为"2602"和"2603"的小组属性。
 * 调用"删除小组"的API，删除ID为"2602"和"2603"的小组。
 * 正常执行时最终打印的是：
 *      已获得token:57e364f7-0998-4bec-a3c3-29d507b5f71e
 *      create返回参数:
 *      {"code":0,"message":"Normal done","data":{"groupId":"2601"}}
 *      create返回参数:
 *      {"code":0,"message":"Normal done","data":{"groupId":"2602"}}
 *      create返回参数:
 *      {"code":0,"message":"Normal done","data":{"groupId":"2603"}}
 *      update返回参数:
 *      {"code":0,"message":"Normal done"}
 *      config返回参数:
 *      {"code":0,"message":"Normal done"}
 *      delete返回参数:
 *      {"code":0,"message":"Normal done"}
 */
package com.nss.main;

import com.nss.pojo.Result;
import com.nss.utils.SendAPIUtil;
import com.nss.utils.TakeTokenUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAPIDemo {

    public static String token = null;
    public static int tempId = 625;//创建分组的默认序号

    public static void main(String[] args) {
        //获取token
        token = TakeTokenUtil.getToken("46d44df3", "a7316346-12c8-46a5-b535-ab0409c20620");
        String groupID = createGroup(token);//创建1个分组
        //再创建2个组放到List中
        List<String> groupIDList = new ArrayList<>();//存放分组Id，便于集体配置或删除
        groupIDList.add(createGroup(token));
        groupIDList.add(createGroup(token));
        updateGroup(token, groupID);//修改分组
        updateGroupconfig(token, groupIDList);//修改分组配置
        delectGroup(token, groupIDList);//删除分组

    }

    /**
     * @description 创建小组
     * @param token
     * @return
     */
    public static String createGroup(String token){
        Result createGroupResult = new Result();//API的返回结果
        String url ="/group/create";//设置API的相对url

        Map<String,Object> HeaderMap=new HashMap<>();//创建Header参数map
        Map<String,Object> reqMap=new HashMap<>(); //创建请求参数map
        reqMap.put("groupId",""+tempId);
        reqMap.put("groupName","group"+tempId++);
        //把请求发送给服务器，然后接收返回Result对象并打印到控制台
        createGroupResult = SendAPIUtil.sendAPI(url,reqMap,HeaderMap,token);
        if(createGroupResult.getData()!=null){
            return createGroupResult.getData().getGroupId();
        }
        return "数据异常，请查看code代码";
    }

    /**
     * @description 更新小组
     * @param token
     * @param groupID 待更新的小组的ID
     * @return
     */
    public static void updateGroup(String token, String groupID) {
        String url ="/group/update";
        //创建Header参数map
        Map<String,Object> HeaderMap=new HashMap<>();
        //创建请求参数map
        Map<String,Object> reqMap=new HashMap<>();
        reqMap.put("groupId",groupID);
        reqMap.put("groupName","group1062");
        //把请求发送给服务器，然后接收返回Result对象并打印到控制台
        SendAPIUtil.sendAPI(url,reqMap,HeaderMap,token);
    }

    /**
     * @description 删除小组
     * @param token
     * @param groupIDList 待删除的列表（里面存放需要删除的小组的ID）
     * @return
     */
    public static void delectGroup(String token, List<String> groupIDList) {
        String url ="/group/delete";
        //创建Header参数map
        Map<String,Object> HeaderMap=new HashMap<>();
        //创建请求参数map
        Map<String,Object> reqMap=new HashMap<>();
        reqMap.put("groupIdList",groupIDList);
        //把请求发送给服务器，然后接收返回Result对象并打印到控制台
        SendAPIUtil.sendAPI(url,reqMap,HeaderMap,token);
    }

    /**
     * @description 更新小组配置
     * @param token
     * @param groupIDList 待更新的列表（里面存放需要更新配置的小组的ID）
     * @return
     */
    public static void updateGroupconfig(String token, List<String> groupIDList) {
        String url ="/group/config";
        //创建Header参数map
        Map<String,Object> HeaderMap=new HashMap<>();
        //创建请求参数map
        Map<String,Object> reqMap=new HashMap<>();
        reqMap.put("groupIdList",groupIDList);
        reqMap.put("dayBeginTime","08:08:08");
        reqMap.put("dayEndTime","09:08:08");
        List<Integer> List = new ArrayList<Integer>();
        List.add(0);
        reqMap.put("weekdays",List);
        reqMap.put("openFlag",1);
        //把请求发送给服务器，然后接收返回Result对象并打印到控制台
        SendAPIUtil.sendAPI(url,reqMap,HeaderMap,token);
    }

}



