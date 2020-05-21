package com.yjjk.monitor.entity.pojo;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Table(name = "manager_info")
public class ManagerInfo {
    /**
     * 管理员id
     */
    @Id
    private Integer id;

    /**
     * 账户
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 名字
     */
    private String name;

    /**
     * 电话
     */
    private String phone;

    /**
     * 岗位
     */
    private String post;

    /**
     * 科室id
     */
    @Column(name = "department_id")
    private Integer departmentId;
    private String departmentName;


    /**
     * 性别0：女 1：男
     */
    private Integer sex;

    /**
     * 角色0：超管 1：管理员 2：本科室
     */
    private Integer role;

    /**
     * 登陆时间
     */
    @Column(name = "login_time")
    private String loginTime;

    @Column(name = "create_time")
    private String createTime;

    /**
     * 0：正常 1：删除
     */
    private Integer status;

    // 模块权限
    private List<Integer> posts;

    private String token;

}