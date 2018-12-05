package com.xiao.data.controller;

import com.alibaba.fastjson.JSON;
import com.xiao.data.entity.UserRole;
import com.xiao.data.service.UserRoleServiceAPI;
import javax.annotation.Resource;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * 用户角色Controller
 * @author XiaoJinRong
 * @times 2018-12-05 09:41:39
 * @version 1.0
 */
@RestController
@RequestMapping("/userRole")
public class UserRoleControl {

    /** 用户角色服务接口 */
    @Resource
    public UserRoleServiceAPI userRoleServiceAPI;

    /** 
     * 根据主键ID获取对象
     * @param id 主键ID
     * @return 
     */
    @GetMapping("/get/{id}")
    public String get(@PathVariable("id") String id){
        UserRole userRole = userRoleServiceAPI.get(id);
        return JSON.toJSONString(userRole);
    }

    /** 
     * 查询对象
     * @param userRole 对象
     * @return 
     */
    @GetMapping("/query")
    public String query(UserRole userRole){
        List<UserRole> userRoles = userRoleServiceAPI.query(userRole);
        return JSON.toJSONString(userRoles);
    }

    /** 
     * 保存对象
     * @param userRole userRole对象
     * @return 
     */
    @PostMapping("/save")
    public String save(UserRole userRole){
        int count = userRoleServiceAPI.save(userRole);
        return "保存"+count;
    }

    /** 
     * 更新对象
     * @param userRole userRole对象
     * @return 
     */
    @PostMapping("/update")
    public String update(UserRole userRole){
        int count = userRoleServiceAPI.update(userRole);
        return "更新"+count;
    }

    /** 
     * 删除对象
     * @param id 主键ID
     * @return 
     */
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id){
        int count = userRoleServiceAPI.remove(id);
        return "删除"+count;
    }

}