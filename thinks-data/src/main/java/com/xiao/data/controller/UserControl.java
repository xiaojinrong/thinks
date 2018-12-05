package com.xiao.data.controller;

import com.alibaba.fastjson.JSON;
import com.xiao.data.entity.User;
import com.xiao.data.service.UserServiceAPI;
import javax.annotation.Resource;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * 用户Controller
 * @author XiaoJinRong
 * @times 2018-12-05 09:41:39
 * @version 1.0
 */
@RestController
@RequestMapping("/user")
public class UserControl {

    /** 用户服务接口 */
    @Resource
    public UserServiceAPI userServiceAPI;

    /** 
     * 根据主键ID获取对象
     * @param id 主键ID
     * @return 
     */
    @GetMapping("/get/{id}")
    public String get(@PathVariable("id") String id){
        User user = userServiceAPI.get(id);
        return JSON.toJSONString(user);
    }

    /** 
     * 查询对象
     * @param user 对象
     * @return 
     */
    @GetMapping("/query")
    public String query(User user){
        List<User> users = userServiceAPI.query(user);
        return JSON.toJSONString(users);
    }

    /** 
     * 保存对象
     * @param user user对象
     * @return 
     */
    @PostMapping("/save")
    public String save(User user){
        int count = userServiceAPI.save(user);
        return "保存"+count;
    }

    /** 
     * 更新对象
     * @param user user对象
     * @return 
     */
    @PostMapping("/update")
    public String update(User user){
        int count = userServiceAPI.update(user);
        return "更新"+count;
    }

    /** 
     * 删除对象
     * @param id 主键ID
     * @return 
     */
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id){
        int count = userServiceAPI.remove(id);
        return "删除"+count;
    }

}