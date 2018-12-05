package com.xiao.data.entity;

import java.io.Serializable;
/**
 * 角色权限类
 * @author XiaoJinRong
 * @times 2018-12-05 09:41:39
 * @version 1.0
 */
public class RolePermission implements Serializable{

    /**  */
    private static final long serialVersionUID = 1L;

    /**  */
    private String id;

    /**  */
    private String permissionId;

    /**  */
    private String roleId;

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id=id;
    }

    public String getPermissionId(){
        return permissionId;
    }

    public void setPermissionId(String permissionId){
        this.permissionId=permissionId;
    }

    public String getRoleId(){
        return roleId;
    }

    public void setRoleId(String roleId){
        this.roleId=roleId;
    }

}