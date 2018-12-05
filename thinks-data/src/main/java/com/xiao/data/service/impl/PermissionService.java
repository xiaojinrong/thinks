package com.xiao.data.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.xiao.data.service.PermissionServiceAPI;
import com.xiao.data.dao.PermissionDao;
import com.xiao.data.service.BaseService;
import java.util.List;
import com.xiao.data.entity.Permission;
/**
 * 权限业务逻辑接口实现类
 * @author XiaoJinRong
 * @times 2018-12-05 09:41:39
 * @version 1.0
 */
@Service("permissionServiceAPI")
public class PermissionService extends BaseService implements PermissionServiceAPI{

    /** 权限数据接口 */
    @Resource
    public PermissionDao permissionDao;

    /** 
     * 查询对象
     * @param id 主键ID
     * @return 
     */
    public Permission get(String id){
        return permissionDao.get(id);
    }

    /** 
     * 查询对象
     * @param permission 对象
     * @return 
     */
    public List<Permission> query(Permission permission){
        return permissionDao.query(permission);
    }

    /** 
     * 保存对象
     * @param permission 对象
     * @return 
     */
    public int save(Permission permission){
        return permissionDao.save(permission);
    }

    /** 
     * 更新对象
     * @param permission 对象
     * @return 
     */
    public int update(Permission permission){
        return permissionDao.update(permission);
    }

    /** 
     * 删除对象
     * @param id 主键ID
     * @return 
     */
    public int remove(String id){
        return permissionDao.remove(id);
    }

    /** 
     * 批量删除对象
     * @param ids 主键ID
     * @return 
     */
    public int removeBatch(List<String> ids){
        return permissionDao.removeBatch(ids);
    }

}