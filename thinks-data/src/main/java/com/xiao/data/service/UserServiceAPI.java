package com.xiao.data.service;

import com.xiao.data.service.BaseServiceAPI;
import java.util.List;
import com.xiao.data.entity.User;
/**
 * 用户业务逻辑接口
 * @author XiaoJinRong
 * @times 2018-12-05 09:41:39
 * @version 1.0
 */
public interface UserServiceAPI extends BaseServiceAPI {

    /** 
     * 查询对象
     * @param id 主键ID
     * @return 
     */
     User get(String id);

    /** 
     * 查询对象
     * @param user 对象
     * @return 
     */
     List<User> query(User user);

    /** 
     * 保存对象
     * @param user 对象
     * @return 
     */
     int save(User user);

    /** 
     * 更新对象
     * @param user 对象
     * @return 
     */
     int update(User user);

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