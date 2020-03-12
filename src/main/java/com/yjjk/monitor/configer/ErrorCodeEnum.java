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
    UPDATE_ERROR("305", "更新失败"),

    NON_RECORD("320", "无法找到病护记录"),
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
    MANAGER_DELETE_ERROR("353", "删除失败"),
    MANAGER_ROLE_ERROR("354", "角色信息错误"),
    MANAGER_NOT_EXIST("355", "账户不存在"),
    MANAGER_PASSWORD_ERROR("356", "密码错误"),
    MANAGER_LOGIN_OUT_ERROR("357", "登出失败"),
    MANAGER_REFUSE("358", "权限验证失败"),
    /********** PatientController **********/
    PATIENT_EXIST_ERROR("360", "该病例已存在"),
    ADD_PATIENT_ERROR("361", "添加用户失败"),
    ADD_RECORD_ERROR("362", "添加记录失败"),
    EXIST_RECORD("363", "该用户已在其他床位启用监护"),
    PATIENT_GET_RECORD_ERROR("364", "获取历史记录失败"),
    PATIENT_RECORD_NOT_FIND("365", "未找到该记录"),
    PATIENT_STOP_ERROR("366", "停用失败"),
    TEMPERATURE_BOUND_DEPARTMENT_ERROR("367", "不能修改默认规则"),
    PATIENT_CHANGE_BED_ERROR("368", "目标床位已有病人，请确认床位信息输入正确"),


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
