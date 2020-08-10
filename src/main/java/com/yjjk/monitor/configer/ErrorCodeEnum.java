/**
 * Copyright (C), 2019, 寻央大人留书
 * FileName: ErrorCodeEnum
 * Author:   CentreS
 * Date:     2019/8/20 14:49
 * Description: 错误代码枚举类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.monitor.configer;

/**
 * @Description: 错误代码枚举类
 * @author CentreS
 * @create 2019/8/20
 */

/**
 * 错误代码枚举类
 */
public enum ErrorCodeEnum {
    SUCCESS("200", "success"),


    PARAM_EMPTY("301", "必要参数为空"),
    PARAM_ERROR("302", "参数错误"),
    TOKEN_ERROR("303", "token错误"),
    PAGE_INFO_ERROR("304", "分页参数错误"),
    DATA_PARSE_ERROR("305", "数据解析异常"),
    /********** repeater **********/
    REPEATER_ADD_ERROR("310", "路由新增失败"),

//    ERROR_RECORD("321", "记录错误"),
    /********** MachineController **********/
    MACHINE_INSERT_ERROR("340", "设备新增失败"),
    MACHINE_EXIST_ERROR("341", "该设备信息已存在，请核实后录入"),
    MACHINE_NET_ERROR("342", "网络堵塞，设备绑定失败，请稍后再试"),
    MACHINE_USING_ERROR("343", "停用失败,设备正在使用中"),
    MACHINE_STOP_ERROR("344", "设备停用失败"),
    /********** ManagerController **********/
    MANAGER_EXIST_ERROR("350", "新增失败，该账户已存在"),
    MANAGER_INSERT_ERROR("351", "用户新增失败"),
    MANAGER_UPDATE_ERROR("352", "管理员更新失败"),
    MANAGER_ROLE_ERROR("354", "角色信息错误"),
    MANAGER_NOT_EXIST("355", "账户不存在"),
    MANAGER_PASSWORD_ERROR("356", "密码错误"),
    MANAGER_LOGIN_OUT_ERROR("357", "登出失败"),
    MANAGER_REFUSE("358", "权限验证失败"),
    /********** MonitorController **********/
    EXIST_RECORD("360", "该用户已在其他床位启用监护"),
    ERROR_MACHINE_TYPE("361", "设备类型有误"),
    ERROR_CONNECT_DATA_SERVICE("363","数据服务器连接失败"),
    ERROR_MACHINE_STOP("364", "停用失败，设备类型错误"),
    UNKNOWN_ERROR("500", "系统繁忙...");

    private String code;

    private String desc;

    ErrorCodeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return this.code;
    }


    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return "[" + this.code + "]" + this.desc;
    }
}
