package com.xiao.data.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.xiao.data.service.RoleServiceAPI;
import com.xiao.data.dao.RoleDao;
import com.xiao.data.service.BaseService;
import java.util.List;
import com.xiao.data.entity.Role;
/**
 * 角色业务逻辑接口实现类
 * @author XiaoJinRong
 * @times 2018-12-05 09:41:39
 * @version 1.0
 */
@Service("roleServiceAPI")
public class RoleService extends BaseService implements RoleServiceAPI{

    /** 角色数据接口 */
    @Resource
    public RoleDao roleDao;

    /** 
     * 查询对象
     * @param id 主键ID
     * @return 
     */
    public Role get(String id){
        return roleDao.get(id);
    }

    /** 
     * 查询对象
     * @param role 对象
     * @return 
     */
    public List<Role> query(Role role){
        return roleDao.query(role);
    }

    /** 
     * 保存对象
     * @param role 对象
     * @return 
     */
    public int save(Role role){
        return roleDao.save(role);
    }

    /** 
     * 更新对象
     * @param role 对象
     * @return 
     */
    public int update(Role role){
        return roleDao.update(role);
    }

    /** 
     * 删除对象
     * @param id 主键ID
     * @return 
     */
    public int remove(String id){
        return roleDao.remove(id);
    }

    /** 
     * 批量删除对象
     * @param ids 主键ID
     * @return 
     */
    public int removeBatch(List<String> ids){
        return roleDao.removeBatch(ids);
    }

}