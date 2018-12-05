package com.xiao.data.controller;

import com.alibaba.fastjson.JSON;
import com.xiao.data.entity.Role;
import com.xiao.data.service.RoleServiceAPI;
import javax.annotation.Resource;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * 角色Controller
 * @author XiaoJinRong
 * @times 2018-12-05 09:41:39
 * @version 1.0
 */
@RestController
@RequestMapping("/role")
public class RoleControl {

    /** 角色服务接口 */
    @Resource
    public RoleServiceAPI roleServiceAPI;

    /** 
     * 根据主键ID获取对象
     * @param id 主键ID
     * @return 
     */
    @GetMapping("/get/{id}")
    public String get(@PathVariable("id") String id){
        Role role = roleServiceAPI.get(id);
        return JSON.toJSONString(role);
    }

    /** 
     * 查询对象
     * @param role 对象
     * @return 
     */
    @GetMapping("/query")
    public String query(Role role){
        List<Role> roles = roleServiceAPI.query(role);
        return JSON.toJSONString(roles);
    }

    /** 
     * 保存对象
     * @param role role对象
     * @return 
     */
    @PostMapping("/save")
    public String save(Role role){
        int count = roleServiceAPI.save(role);
        return "保存"+count;
    }

    /** 
     * 更新对象
     * @param role role对象
     * @return 
     */
    @PostMapping("/update")
    public String update(Role role){
        int count = roleServiceAPI.update(role);
        return "更新"+count;
    }

    /** 
     * 删除对象
     * @param id 主键ID
     * @return 
     */
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id){
        int count = roleServiceAPI.remove(id);
        return "删除"+count;
    }

}