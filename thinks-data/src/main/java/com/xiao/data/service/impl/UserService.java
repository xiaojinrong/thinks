package com.xiao.data.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.xiao.data.service.UserServiceAPI;
import com.xiao.data.dao.UserDao;
import com.xiao.data.service.BaseService;
import java.util.List;
import com.xiao.data.entity.User;
/**
 * 用户业务逻辑接口实现类
 * @author XiaoJinRong
 * @times 2018-12-05 09:41:39
 * @version 1.0
 */
@Service("userServiceAPI")
public class UserService extends BaseService implements UserServiceAPI{

    /** 用户数据接口 */
    @Resource
    public UserDao userDao;

    /** 
     * 查询对象
     * @param id 主键ID
     * @return 
     */
    public User get(String id){
        return userDao.get(id);
    }

    /** 
     * 查询对象
     * @param user 对象
     * @return 
     */
    public List<User> query(User user){
        return userDao.query(user);
    }

    /** 
     * 保存对象
     * @param user 对象
     * @return 
     */
    public int save(User user){
        return userDao.save(user);
    }

    /** 
     * 更新对象
     * @param user 对象
     * @return 
     */
    public int update(User user){
        return userDao.update(user);
    }

    /** 
     * 删除对象
     * @param id 主键ID
     * @return 
     */
    public int remove(String id){
        return userDao.remove(id);
    }

    /** 
     * 批量删除对象
     * @param ids 主键ID
     * @return 
     */
    public int removeBatch(List<String> ids){
        return userDao.removeBatch(ids);
    }

}