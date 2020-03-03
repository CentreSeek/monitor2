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
 *
 */
public enum ErrorCodeEnum {
    SUCCESS("200", "success"),

    ADD_PATIENT_ERROR("300","add patient failed"),
    ADD_RECORD_ERROR("300","add record failed"),
    EXIST_RECORD("300","the patient is recording on other bed"),

    PARAM_EMPTY("301", "the required param is null"),
    PARAM_ERROR("302", "parameter format error"),
    TOKEN_ERROR("333", "token error"),
    TEMPERATURE_BOUND_DEPARTMENT_ERROR("310", "cannot change the default rule"),

    NON_RECORD("320", "cannot find the record"),
    ERROR_RECORD("321", "record error"),


    UPDATE_ERROR("330", "update error"),

    UNKNOWN_ERROR("500", "system busyness");

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
