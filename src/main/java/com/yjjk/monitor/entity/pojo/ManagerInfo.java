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

    /**
     * 获取管理员id
     *
     * @return id - 管理员id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置管理员id
     *
     * @param id 管理员id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取账户
     *
     * @return account - 账户
     */
    public String getAccount() {
        return account;
    }

    /**
     * 设置账户
     *
     * @param account 账户
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取名字
     *
     * @return name - 名字
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名字
     *
     * @param name 名字
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取电话
     *
     * @return phone - 电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置电话
     *
     * @param phone 电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取岗位
     *
     * @return post - 岗位
     */
    public String getPost() {
        return post;
    }

    /**
     * 设置岗位
     *
     * @param post 岗位
     */
    public void setPost(String post) {
        this.post = post;
    }

    /**
     * 获取科室id
     *
     * @return department_id - 科室id
     */
    public Integer getDepartmentId() {
        return departmentId;
    }

    /**
     * 设置科室id
     *
     * @param departmentId 科室id
     */
    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * 获取性别0：女 1：男
     *
     * @return sex - 性别0：女 1：男
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 设置性别0：女 1：男
     *
     * @param sex 性别0：女 1：男
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 获取角色0：超管 1：管理员 2：本科室
     *
     * @return role - 角色0：超管 1：管理员 2：本科室
     */
    public Integer getRole() {
        return role;
    }

    /**
     * 设置角色0：超管 1：管理员 2：本科室
     *
     * @param role 角色0：超管 1：管理员 2：本科室
     */
    public void setRole(Integer role) {
        this.role = role;
    }

    /**
     * 获取登陆时间
     *
     * @return login_time - 登陆时间
     */
    public String getLoginTime() {
        return loginTime;
    }

    /**
     * 设置登陆时间
     *
     * @param loginTime 登陆时间
     */
    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    /**
     * @return create_time
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取0：正常 1：删除
     *
     * @return status - 0：正常 1：删除
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置0：正常 1：删除
     *
     * @param status 0：正常 1：删除
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}