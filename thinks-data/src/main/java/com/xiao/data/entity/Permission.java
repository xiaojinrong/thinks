package com.xiao.data.entity;

import java.io.Serializable;
/**
 * 权限类
 * @author XiaoJinRong
 * @times 2018-12-05 09:41:39
 * @version 1.0
 */
public class Permission implements Serializable{

    /**  */
    private static final long serialVersionUID = 1L;

    /**  */
    private String permissionId;

    /** 授权标识 */
    private String dataCode;

    /** 资源名称 */
    private String dataName;

    /** 资源URL */
    private String dataUrl;

    /** 0：菜单，1：按钮 */
    private Integer dataType;

    /** 父级资源 */
    private String dataPid;

    /** 资源排序 */
    private Integer dataOrder;

    /** 备注 */
    private String dataDescription;

    /**  */
    private String createdTime;

    /**  */
    private String updateTime;

    public String getPermissionId(){
        return permissionId;
    }

    public void setPermissionId(String permissionId){
        this.permissionId=permissionId;
    }

    public String getDataCode(){
        return dataCode;
    }

    public void setDataCode(String dataCode){
        this.dataCode=dataCode;
    }

    public String getDataName(){
        return dataName;
    }

    public void setDataName(String dataName){
        this.dataName=dataName;
    }

    public String getDataUrl(){
        return dataUrl;
    }

    public void setDataUrl(String dataUrl){
        this.dataUrl=dataUrl;
    }

    public Integer getDataType(){
        return dataType;
    }

    public void setDataType(Integer dataType){
        this.dataType=dataType;
    }

    public String getDataPid(){
        return dataPid;
    }

    public void setDataPid(String dataPid){
        this.dataPid=dataPid;
    }

    public Integer getDataOrder(){
        return dataOrder;
    }

    public void setDataOrder(Integer dataOrder){
        this.dataOrder=dataOrder;
    }

    public String getDataDescription(){
        return dataDescription;
    }

    public void setDataDescription(String dataDescription){
        this.dataDescription=dataDescription;
    }

    public String getCreatedTime(){
        return createdTime;
    }

    public void setCreatedTime(String createdTime){
        this.createdTime=createdTime;
    }

    public String getUpdateTime(){
        return updateTime;
    }

    public void setUpdateTime(String updateTime){
        this.updateTime=updateTime;
    }

}