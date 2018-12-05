package com.xiao.data.entity;

import java.io.Serializable;
/**
 * 角色类
 * @author XiaoJinRong
 * @times 2018-12-05 09:41:39
 * @version 1.0
 */
public class Role implements Serializable{

    /**  */
    private static final long serialVersionUID = 1L;

    /**  */
    private String roleId;

    /**  */
    private String roleName;

    /**  */
    private String createdTime;

    /**  */
    private String updateTime;

    public String getRoleId(){
        return roleId;
    }

    public void setRoleId(String roleId){
        this.roleId=roleId;
    }

    public String getRoleName(){
        return roleName;
    }

    public void setRoleName(String roleName){
        this.roleName=roleName;
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