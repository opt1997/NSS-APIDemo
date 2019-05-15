package com.nss.pojo;

import java.util.List;

public class Data {
    private String groupId;
    private String groupName;
    private String memberId;
    private String memberName;
    private List errorList;
    private String deviceId;
    private String  orgId;
    private List<String >memberList;
    private String fileId;
    private String ctrlNo;
    private List ctrlList;
    private String errorFile;
    private List visitorList;
    private List syncStatusList;
    private int totalCount;
    private int syncStatus;
    private String noticeId;

    public String getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId;
    }

    public int getSyncStatus() {
        return syncStatus;
    }

    public void setSyncStatus(int syncStatus) {
        this.syncStatus = syncStatus;
    }

    public List getVisitorList() {
        return visitorList;
    }

    public void setVisitorList(List visitorList) {
        this.visitorList = visitorList;
    }

    public List getSyncStatusList() {
        return syncStatusList;
    }

    public void setSyncStatusList(List syncStatusList) {
        this.syncStatusList = syncStatusList;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public String getErrorFile() {
        return errorFile;
    }

    public void setErrorFile(String errorFile) {
        this.errorFile = errorFile;
    }

    public List getCtrlList() {
        return ctrlList;
    }

    public void setCtrlList(List ctrlList) {
        this.ctrlList = ctrlList;
    }

    public String getCtrlNo() {
        return ctrlNo;
    }

    public void setCtrlNo(String ctrlNo) {
        this.ctrlNo = ctrlNo;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public List<String> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<String> memberList) {
        this.memberList = memberList;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public List getErrorList() {
        return errorList;
    }

    public void setErrorList(List errorList) {
        this.errorList = errorList;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
}
