package com.xiao.data.controller;

import com.alibaba.fastjson.JSON;
import com.xiao.data.entity.RolePermission;
import com.xiao.data.service.RolePermissionServiceAPI;
import javax.annotation.Resource;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * 角色权限Controller
 * @author XiaoJinRong
 * @times 2018-12-05 09:41:39
 * @version 1.0
 */
@RestController
@RequestMapping("/rolePermission")
public class RolePermissionControl {

    /** 角色权限服务接口 */
    @Resource
    public RolePermissionServiceAPI rolePermissionServiceAPI;

    /** 
     * 根据主键ID获取对象
     * @param id 主键ID
     * @return 
     */
    @GetMapping("/get/{id}")
    public String get(@PathVariable("id") String id){
        RolePermission rolePermission = rolePermissionServiceAPI.get(id);
        return JSON.toJSONString(rolePermission);
    }

    /** 
     * 查询对象
     * @param rolePermission 对象
     * @return 
     */
    @GetMapping("/query")
    public String query(RolePermission rolePermission){
        List<RolePermission> rolePermissions = rolePermissionServiceAPI.query(rolePermission);
        return JSON.toJSONString(rolePermissions);
    }

    /** 
     * 保存对象
     * @param rolePermission rolePermission对象
     * @return 
     */
    @PostMapping("/save")
    public String save(RolePermission rolePermission){
        int count = rolePermissionServiceAPI.save(rolePermission);
        return "保存"+count;
    }

    /** 
     * 更新对象
     * @param rolePermission rolePermission对象
     * @return 
     */
    @PostMapping("/update")
    public String update(RolePermission rolePermission){
        int count = rolePermissionServiceAPI.update(rolePermission);
        return "更新"+count;
    }

    /** 
     * 删除对象
     * @param id 主键ID
     * @return 
     */
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id){
        int count = rolePermissionServiceAPI.remove(id);
        return "删除"+count;
    }

}