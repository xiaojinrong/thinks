package com.xiao.data.dao;

import org.apache.ibatis.annotations.Mapper;
import com.xiao.data.entity.Permission;
import com.xiao.data.dao.BaseDaoAPI;
/**
 * 权限数据接口
 * @author XiaoJinRong
 * @times 2018-12-05 09:41:39
 * @version 1.0
 */
@Mapper
public interface PermissionDao extends BaseDaoAPI<Permission> {

}