package com.xiao.data.controller;

import com.alibaba.fastjson.JSON;
import com.xiao.data.entity.Permission;
import com.xiao.data.service.PermissionServiceAPI;
import javax.annotation.Resource;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * 权限Controller
 * @author XiaoJinRong
 * @times 2018-12-05 09:41:39
 * @version 1.0
 */
@RestController
@RequestMapping("/permission")
public class PermissionControl {

    /** 权限服务接口 */
    @Resource
    public PermissionServiceAPI permissionServiceAPI;

    /** 
     * 根据主键ID获取对象
     * @param id 主键ID
     * @return 
     */
    @GetMapping("/get/{id}")
    public String get(@PathVariable("id") String id){
        Permission permission = permissionServiceAPI.get(id);
        return JSON.toJSONString(permission);
    }

    /** 
     * 查询对象
     * @param permission 对象
     * @return 
     */
    @GetMapping("/query")
    public String query(Permission permission){
        List<Permission> permissions = permissionServiceAPI.query(permission);
        return JSON.toJSONString(permissions);
    }

    /** 
     * 保存对象
     * @param permission permission对象
     * @return 
     */
    @PostMapping("/save")
    public String save(Permission permission){
        int count = permissionServiceAPI.save(permission);
        return "保存"+count;
    }

    /** 
     * 更新对象
     * @param permission permission对象
     * @return 
     */
    @PostMapping("/update")
    public String update(Permission permission){
        int count = permissionServiceAPI.update(permission);
        return "更新"+count;
    }

    /** 
     * 删除对象
     * @param id 主键ID
     * @return 
     */
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id){
        int count = permissionServiceAPI.remove(id);
        return "删除"+count;
    }

}