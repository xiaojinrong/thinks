package com.xiao.data.dao;

import org.apache.ibatis.annotations.Mapper;
import com.xiao.data.entity.UserRole;
import com.xiao.data.dao.BaseDaoAPI;
/**
 * 用户角色数据接口
 * @author XiaoJinRong
 * @times 2018-12-05 09:41:39
 * @version 1.0
 */
@Mapper
public interface UserRoleDao extends BaseDaoAPI<UserRole> {

}