package com.gsww.entity.entity;

import java.io.Serializable;

/**
 * 投诉信息表(RflComplaint)实体类
 *
 * @author makejava
 * @since 2020-05-07 10:19:46
 */
public class RflComplaint implements Serializable {
    private static final long serialVersionUID = 390500559703303615L;
    /**
    * 主键
    */
    private String pkId;
    /**
    * 投诉标识（国家互动交流平台办件编号）
    */
    private String complaintId;
    /**
    * 投诉标题
    */
    private String complaintTitle;
    /**
    * 投诉内容
    */
    private String complaintContent;
    /**
    * 投诉类型（投诉、建议、意见建议箱）
    */
    private String complaintType;
    /**
    * 投诉人（加密,可以通过私钥解密）
    */
    private String complaintUser;
    /**
    * 投诉人电话（加密,可以通过私钥解密）
    */
    private String complaintPhone;
    /**
    * 企业信用代码
    */
    private String companyCode;
    /**
    * 企业类型
    */
    private String companyType;
    /**
    * 国办处理意见（国办审核意见）
    */
    private String handleOpinion;
    /**
    * 事项编号
    */
    private String serviceCode;
    /**
    * 事项名称
    */
    private String serviceName;
    /**
    * 是否与办件相关，1是，2否
    */
    private String projectFlag;
    /**
    * 办件编号
    */
    private String projectNo;
    /**
    * 办件类型（个人、法人）
    */
    private String projectType;
    /**
    * 办件提交时间，yyyy-MM-dd HH:mm:ss
    */
    private String projectSubmitdate;
    /**
    * 是否退回办件，1：是；0：否
    */
    private String returnFlag;
    /**
    * 退回处理意见
    */
    private String returnOpinion;
    /**
    * 区划编码
    */
    private String regionCode;
    /**
    * 区划名称
    */
    private String regionName;
    /**
    * 任务状态，0：未生成；1：已生成
    */
    private String taskState;


    public String getPkId() {
        return pkId;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId;
    }

    public String getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(String complaintId) {
        this.complaintId = complaintId;
    }

    public String getComplaintTitle() {
        return complaintTitle;
    }

    public void setComplaintTitle(String complaintTitle) {
        this.complaintTitle = complaintTitle;
    }

    public String getComplaintContent() {
        return complaintContent;
    }

    public void setComplaintContent(String complaintContent) {
        this.complaintContent = complaintContent;
    }

    public String getComplaintType() {
        return complaintType;
    }

    public void setComplaintType(String complaintType) {
        this.complaintType = complaintType;
    }

    public String getComplaintUser() {
        return complaintUser;
    }

    public void setComplaintUser(String complaintUser) {
        this.complaintUser = complaintUser;
    }

    public String getComplaintPhone() {
        return complaintPhone;
    }

    public void setComplaintPhone(String complaintPhone) {
        this.complaintPhone = complaintPhone;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getHandleOpinion() {
        return handleOpinion;
    }

    public void setHandleOpinion(String handleOpinion) {
        this.handleOpinion = handleOpinion;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getProjectFlag() {
        return projectFlag;
    }

    public void setProjectFlag(String projectFlag) {
        this.projectFlag = projectFlag;
    }

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getProjectSubmitdate() {
        return projectSubmitdate;
    }

    public void setProjectSubmitdate(String projectSubmitdate) {
        this.projectSubmitdate = projectSubmitdate;
    }

    public String getReturnFlag() {
        return returnFlag;
    }

    public void setReturnFlag(String returnFlag) {
        this.returnFlag = returnFlag;
    }

    public String getReturnOpinion() {
        return returnOpinion;
    }

    public void setReturnOpinion(String returnOpinion) {
        this.returnOpinion = returnOpinion;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getTaskState() {
        return taskState;
    }

    public void setTaskState(String taskState) {
        this.taskState = taskState;
    }

}