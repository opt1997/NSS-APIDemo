/**
 * 正常执行时最终打印的是：
 * 已获得token:accessToken: 85eef56a-4081-4082-b3dc-36e934f7ffae
 * 85eef56a-4081-4082-b3dc-36e934f7ffae
 * delete_all返回参数:
 * {"code":0,"message":"Normal done"}
 * create返回参数:
 * {"code":0,"message":"Normal done","data":{"memberId":"301"}}
 * create返回参数:
 * {"code":0,"message":"Normal done","data":{"memberId":"302"}}
 * create返回参数:
 * {"code":0,"message":"Normal done","data":{"memberId":"303"}}
 * update返回参数:
 * {"code":0,"message":"Normal done"}
 * batch返回参数:
 * {"code":0,"message":"Normal done"}
 * bind_group返回参数:
 * {"code":0,"message":"Normal done"}
 * unbind_group返回参数:
 * {"code":0,"message":"Normal done"}
 * bind_group_by_tag返回参数:
 * {"code":0,"message":"Normal done"}
 * get_device_member_status返回参数:
 * {"code":0,"message":"Normal done","data":{"memberList":[]}}
 * bind_devices返回参数:
 * {"code":0,"message":"Normal done"}
 * unbind_devices返回参数:
 * {"code":0,"message":"Normal done"}
 * delete返回参数:
 * {"code":0,"message":"Normal done"}
 */
package com.nss.main;

import com.nss.pojo.Result;
import com.nss.utils.SendAPIUtil;
import com.nss.utils.TakeTokenUtil;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberAPIDemo {
    public static String token = null;
    public static int tempId = 401;//创建分组的默认序号

    public static void main(String[] args) {
        System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.Jdk14Logger");
        token = TakeTokenUtil.getToken("46d44df3", "a7316346-12c8-46a5-b535-ab0409c20620");//获取token
        delete_allMember(token);//清空人员（逻辑删除）
        List<String> deviceIdList = new ArrayList<>();//存放设备Id
        List<String> memberIDList = new ArrayList<>();//存放人员Id
        deviceIdList.add("10003");
        String memberID = createMember(token);//创建1个人员
        memberIDList.add(createMember(token));//创建2个人员并放入List中
        memberIDList.add(createMember(token));
        updateMember(token, memberID);//修改人员
        batchMember(token,"ff8081816a38f6b4016a902d7dec2b2e");//批量导入人员照片
        bind_groupMember(token, memberIDList);//增加人员分组
        unbind_groupMember(token, memberIDList);//移除人员分组
        bind_group_by_tagMember(token, memberIDList);//增加/删除人员分组（通过标签）
        get_device_member_statusMember(token,"10003");//获取人员下发状态
        bind_devicesMember(token, memberIDList,deviceIdList);//人员绑定至设备
        unbind_devicesMember(token, memberIDList,deviceIdList);//人员解绑设备
        //delete_by_searchMember(token);//删除符合条件的人员（逻辑删除）
        deleteMember(token,memberIDList);//删除人员
    }

    /**
     * @description 人员解绑设备
     * @param token
     * @param memberIDList 列表存放待解绑人员们的ID
     * @param deviceIdList
     */
    public static void unbind_devicesMember(String token, List<String> memberIDList,List<String> deviceIdList) {
        String url ="/member/unbind_devices";

        Map<String,Object> headerMap=new HashMap<>();//创建Header参数map
        Map<String,Object> reqMap=new HashMap<>();//创建请求参数map
        reqMap.put("memberIdList",memberIDList);
        reqMap.put("deviceIdList",deviceIdList);
        //把请求发送给服务器，然后接收返回Result对象并打印到控制台
        SendAPIUtil.sendAPI(url,reqMap,headerMap,token);
    }
    /**
     * @description 人员绑定至设备
     * @param token
     * @param memberIDList 列表存放人员们的ID
     * @param  deviceIdList 被绑定设备的Id
     */
    public static void bind_devicesMember(String token, List<String> memberIDList,List<String> deviceIdList) {
        String url ="/member/bind_devices";
        Map<String,Object> headerMap=new HashMap<>();//创建Header参数map
        Map<String,Object> reqMap=new HashMap<>();//创建请求参数map
        reqMap.put("memberIdList",memberIDList);
        reqMap.put("deviceIdList",deviceIdList);
        //把请求发送给服务器，然后接收返回Result对象并打印到控制台
        SendAPIUtil.sendAPI(url,reqMap,headerMap,token);
    }
    /**
     * @description 添加人员
     * @param token
     * @return 返回人员的ID
     */
    public static String createMember(String token){
        Result createMemberResult = new Result();//API的返回结果
        String url ="/member/create";//设置API的相对url
        List<String> tagNameList = new ArrayList<>();//存放tag
        tagNameList.add("1");
        tagNameList.add("2");
        Map<String,Object> headerMap=new HashMap<>();//创建Header参数map
        Map<String,Object> reqMap=new HashMap<>();//创建请求参数map
        reqMap.put("memberId",""+tempId);
        reqMap.put("memberName","group"+tempId++);
        reqMap.put("tagName",tagNameList);
        reqMap.put("mobile",tempId+"77777777");
        //把请求发送给服务器，然后接收返回Result对象并打印到控制台
        createMemberResult = SendAPIUtil.sendAPI(url,reqMap,headerMap,token);
        if(createMemberResult.getData()!=null){
            return createMemberResult.getData().getMemberId();
        }
        return "数据异常，请查看code代码";
    }
    /**
     * @description 更新人员
     * @param token
     * @param memberID 待更新的人员的ID
     * @return
     */
    public static void updateMember(String token, String memberID) {
        String url ="/member/update";

        Map<String,Object> headerMap=new HashMap<>();//创建Header参数map
        Map<String,Object> reqMap=new HashMap<>();//创建请求参数map
        reqMap.put("memberId",""+memberID);
        reqMap.put("memberName","group"+Integer.valueOf(memberID)*2);
        List<String> tagNameList = new ArrayList<>();//存放tag
        tagNameList.add("3");
        tagNameList.add("4");
        reqMap.put("tagName",tagNameList);
        //把请求发送给服务器，执行结果打印到控制台
        SendAPIUtil.sendAPI(url,reqMap,headerMap,token);
    }
    /**
     * @description 这里的fileId需要通过在网页上上传文件才能获得文件的id
     * 上传文件的程序样例可以查看：NSS_UploadDemo(上传的文件格式需是.zip，里面的图片需是.jpg)
     * @param token
     * @param fileId
     */
    public static void batchMember(String token,String fileId) {
        String url ="/member/batch";

        Map<String,Object> headerMap=new HashMap<>();//创建Header参数map
        Map<String,Object> reqMap=new HashMap<>();//创建请求参数map
        reqMap.put("imageZipFileId",fileId);
        List<String> tagNameList = new ArrayList<>();//存放tag
        tagNameList.add("1");
        tagNameList.add("2");
        reqMap.put("tagName",tagNameList);
        //把请求发送给服务器，执行结果打印到控制台
        SendAPIUtil.sendAPI(url,reqMap,headerMap,token);
    }
    /**
     * @description 删除人员组
     * @param token
     * @param memberIDList 列表存放待删除的人员们的ID
     * @return
     */
    public static void deleteMember(String token,List<String> memberIDList) {
        String url ="/member/delete";

        Map<String,Object> headerMap=new HashMap<>();//创建Header参数map
        Map<String,Object> reqMap=new HashMap<>();//创建请求参数map
        reqMap.put("memberIdList",memberIDList);
        //把请求发送给服务器，执行结果打印到控制台
        SendAPIUtil.sendAPI(url,reqMap,headerMap,token);
    }
    /**
     * @description 增加人员分组
     * @param token
     * @param memberIDList 列表存放增加人员们的ID
     * @return
     */
    public static void bind_groupMember(String token, List<String> memberIDList) {
        String url ="/member/bind_group";

        Map<String,Object> headerMap=new HashMap<>();//创建Header参数map
        Map<String,Object> reqMap=new HashMap<>();//创建请求参数map
        reqMap.put("memberIdList",memberIDList);
        reqMap.put("groupId","100");
        //把请求发送给服务器，执行结果打印到控制台
        SendAPIUtil.sendAPI(url,reqMap,headerMap,token);
    }
    /**
     * @description 逻辑删除所有人员
     * @param token
     * @return
     */
    public static void delete_allMember(String token) {
        String url ="/member/delete_all";

        Map<String,Object> headerMap=new HashMap<>();//创建Header参数map
        Map<String,Object> reqMap=new HashMap<>();//创建请求参数map

        //把请求发送给服务器，执行结果打印到控制台
        SendAPIUtil.sendAPI(url,reqMap,headerMap,token);
    }
    /**
     * @description 移除人员分组
     * @param token
     * @param memberIDList 待移除人员们的ID
     * @return
     */
    public static void unbind_groupMember(String token , List<String> memberIDList) {
        String url ="/member/unbind_group";

        Map<String,Object> headerMap=new HashMap<>();//创建Header参数map
        Map<String,Object> reqMap=new HashMap<>();//创建请求参数map
        reqMap.put("memberIdList",memberIDList);
        reqMap.put("groupId","100");
        //把请求发送给服务器，执行结果打印到控制台
        SendAPIUtil.sendAPI(url,reqMap,headerMap,token);
    }
    /**
     * @description 增加/删除人员分组（通过标签）
     * @param token
     * @param memberIDList 列表存放增加/删除人员们的ID
     * @return
     */
    public static void bind_group_by_tagMember(String token, List<String> memberIDList) {
        String url ="/member/bind_group_by_tag";

        Map<String,Object> headerMap=new HashMap<>();//创建Header参数map
        Map<String,Object> reqMap=new HashMap<>();//创建请求参数map
        reqMap.put("tagIdList",memberIDList);
        reqMap.put("groupId","100");
        reqMap.put("optionFlag","1");
        //把请求发送给服务器，结果打印到控制台
        SendAPIUtil.sendAPI(url,reqMap,headerMap,token);
    }
    /**
     * @description 获取人员下发状态
     * @param token
     * @param deviceCode 设备序列号
     * @return
     */
    public static void get_device_member_statusMember(String token,String deviceCode) {
        String url ="/member/get_device_member_status";

        Map<String,Object> headerMap=new HashMap<>();//创建Header参数map
        Map<String,Object> reqMap=new HashMap<>();//创建请求参数map
        reqMap.put("deviceCode",deviceCode);
        //把请求发送给服务器，结果打印到控制台
        SendAPIUtil.sendAPI(url,reqMap,headerMap,token);
    }

    public static void delete_by_searchMember(String token) {
        String url ="/member/delete_by_search";

        Map<String,Object> headerMap=new HashMap<>();//创建Header参数map
        Map<String,Object> reqMap=new HashMap<>();//创建请求参数map

        //把请求发送给服务器，执行结果打印到控制台
        SendAPIUtil.sendAPI(url,reqMap,headerMap,token);
    }

}



