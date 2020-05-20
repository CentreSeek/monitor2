package com.yjjk.monitor.entity.pojo;

import javax.persistence.*;

@Table(name = "zs_machinetoreperter_info")
public class ZsMachinetoreperterInfo {
    /**
     * 设备mac
     */
    @Column(name = "device_mac")
    private String deviceMac;

    /**
     * 路由mac
     */
    @Column(name = "repeater_mac")
    private String repeaterMac;

    /**
     * 路由ip
     */
    @Column(name = "reperter_ip")
    private String reperterIp;

    /**
     * SN序列号
     */
    @Column(name = "dev_name")
    private String devName;

    @Column(name = "create_time")
    private String createTime;

    /**
     * 获取设备mac
     *
     * @return device_mac - 设备mac
     */
    public String getDeviceMac() {
        return deviceMac;
    }

    /**
     * 设置设备mac
     *
     * @param deviceMac 设备mac
     */
    public void setDeviceMac(String deviceMac) {
        this.deviceMac = deviceMac;
    }

    /**
     * 获取路由mac
     *
     * @return repeater_mac - 路由mac
     */
    public String getRepeaterMac() {
        return repeaterMac;
    }

    /**
     * 设置路由mac
     *
     * @param repeaterMac 路由mac
     */
    public void setRepeaterMac(String repeaterMac) {
        this.repeaterMac = repeaterMac;
    }

    /**
     * 获取路由ip
     *
     * @return reperter_ip - 路由ip
     */
    public String getReperterIp() {
        return reperterIp;
    }

    /**
     * 设置路由ip
     *
     * @param reperterIp 路由ip
     */
    public void setReperterIp(String reperterIp) {
        this.reperterIp = reperterIp;
    }

    /**
     * 获取SN序列号
     *
     * @return dev_name - SN序列号
     */
    public String getDevName() {
        return devName;
    }

    /**
     * 设置SN序列号
     *
     * @param devName SN序列号
     */
    public void setDevName(String devName) {
        this.devName = devName;
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
}