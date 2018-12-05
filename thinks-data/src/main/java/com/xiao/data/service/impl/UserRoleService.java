package com.xiao.data.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.xiao.data.service.UserRoleServiceAPI;
import com.xiao.data.dao.UserRoleDao;
import com.xiao.data.service.BaseService;
import java.util.List;
import com.xiao.data.entity.UserRole;
/**
 * 用户角色业务逻辑接口实现类
 * @author XiaoJinRong
 * @times 2018-12-05 09:41:39
 * @version 1.0
 */
@Service("userRoleServiceAPI")
public class UserRoleService extends BaseService implements UserRoleServiceAPI{

    /** 用户角色数据接口 */
    @Resource
    public UserRoleDao userRoleDao;

    /** 
     * 查询对象
     * @param id 主键ID
     * @return 
     */
    public UserRole get(String id){
        return userRoleDao.get(id);
    }

    /** 
     * 查询对象
     * @param userRole 对象
     * @return 
     */
    public List<UserRole> query(UserRole userRole){
        return userRoleDao.query(userRole);
    }

    /** 
     * 保存对象
     * @param userRole 对象
     * @return 
     */
    public int save(UserRole userRole){
        return userRoleDao.save(userRole);
    }

    /** 
     * 更新对象
     * @param userRole 对象
     * @return 
     */
    public int update(UserRole userRole){
        return userRoleDao.update(userRole);
    }

    /** 
     * 删除对象
     * @param id 主键ID
     * @return 
     */
    public int remove(String id){
        return userRoleDao.remove(id);
    }

    /** 
     * 批量删除对象
     * @param ids 主键ID
     * @return 
     */
    public int removeBatch(List<String> ids){
        return userRoleDao.removeBatch(ids);
    }

}