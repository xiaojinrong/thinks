package com.xiao.data.entity;

import java.io.Serializable;
/**
 * 用户角色类
 * @author XiaoJinRong
 * @times 2018-12-05 09:41:39
 * @version 1.0
 */
public class UserRole implements Serializable{

    /**  */
    private static final long serialVersionUID = 1L;

    /** 12345 */
    private String id;

    /** 用户ID */
    private String userId;

    /** 角色ID */
    private String roleId;

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id=id;
    }

    public String getUserId(){
        return userId;
    }

    public void setUserId(String userId){
        this.userId=userId;
    }

    public String getRoleId(){
        return roleId;
    }

    public void setRoleId(String roleId){
        this.roleId=roleId;
    }

}