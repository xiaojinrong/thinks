package com.xiao.data.dao;

import org.apache.ibatis.annotations.Mapper;
import com.xiao.data.entity.RolePermission;
import com.xiao.data.dao.BaseDaoAPI;
/**
 * 角色权限数据接口
 * @author XiaoJinRong
 * @times 2018-12-05 09:41:39
 * @version 1.0
 */
@Mapper
public interface RolePermissionDao extends BaseDaoAPI<RolePermission> {

}