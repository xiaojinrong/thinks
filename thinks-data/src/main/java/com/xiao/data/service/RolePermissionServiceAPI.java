package com.xiao.data.service;

import com.xiao.data.service.BaseServiceAPI;
import java.util.List;
import com.xiao.data.entity.RolePermission;
/**
 * 角色权限业务逻辑接口
 * @author XiaoJinRong
 * @times 2018-12-05 09:41:39
 * @version 1.0
 */
public interface RolePermissionServiceAPI extends BaseServiceAPI {

    /** 
     * 查询对象
     * @param id 主键ID
     * @return 
     */
     RolePermission get(String id);

    /** 
     * 查询对象
     * @param rolePermission 对象
     * @return 
     */
     List<RolePermission> query(RolePermission rolePermission);

    /** 
     * 保存对象
     * @param rolePermission 对象
     * @return 
     */
     int save(RolePermission rolePermission);

    /** 
     * 更新对象
     * @param rolePermission 对象
     * @return 
     */
     int update(RolePermission rolePermission);

    /** 
     * 删除对象
     * @param id 主键ID
     * @return 
     */
     int remove(String id);

    /** 
     * 批量删除对象
     * @param ids 主键ID
     * @return 
     */
     int removeBatch(List<String> ids);

}