package com.xiao.data.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.xiao.data.service.RolePermissionServiceAPI;
import com.xiao.data.dao.RolePermissionDao;
import com.xiao.data.service.BaseService;
import java.util.List;
import com.xiao.data.entity.RolePermission;
/**
 * 角色权限业务逻辑接口实现类
 * @author XiaoJinRong
 * @times 2018-12-05 09:41:39
 * @version 1.0
 */
@Service("rolePermissionServiceAPI")
public class RolePermissionService extends BaseService implements RolePermissionServiceAPI{

    /** 角色权限数据接口 */
    @Resource
    public RolePermissionDao rolePermissionDao;

    /** 
     * 查询对象
     * @param id 主键ID
     * @return 
     */
    public RolePermission get(String id){
        return rolePermissionDao.get(id);
    }

    /** 
     * 查询对象
     * @param rolePermission 对象
     * @return 
     */
    public List<RolePermission> query(RolePermission rolePermission){
        return rolePermissionDao.query(rolePermission);
    }

    /** 
     * 保存对象
     * @param rolePermission 对象
     * @return 
     */
    public int save(RolePermission rolePermission){
        return rolePermissionDao.save(rolePermission);
    }

    /** 
     * 更新对象
     * @param rolePermission 对象
     * @return 
     */
    public int update(RolePermission rolePermission){
        return rolePermissionDao.update(rolePermission);
    }

    /** 
     * 删除对象
     * @param id 主键ID
     * @return 
     */
    public int remove(String id){
        return rolePermissionDao.remove(id);
    }

    /** 
     * 批量删除对象
     * @param ids 主键ID
     * @return 
     */
    public int removeBatch(List<String> ids){
        return rolePermissionDao.removeBatch(ids);
    }

}