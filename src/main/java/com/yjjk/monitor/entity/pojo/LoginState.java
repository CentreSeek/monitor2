package com.yjjk.monitor.entity.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Accessors(chain = true)
@Table(name = "login_state")
public class LoginState {
    /**
     * token
     */
    @Id
    private String token;

    /**
     * 上次登录的IP
     */
    private String ip;

    /**
     * 管理员id
     */
    @Column(name = "manager_id")
    private Integer managerId;

    /**
     * 登出时间
     */
    @Column(name = "login_out")
    private String loginOut;

    /**
     * 0：登入 1：登出
     */
    private Integer status;

}