package com.yjjk.monitor.entity.pojo;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name = "zs_temperature_info")
public class ZsTemperatureInfo {
    /**
     * 体温记录id
     */
    @Id
    @Column(name = "temperature_id")
    private Integer temperatureId;

    /**
     * 设备ID
     */
    @Column(name = "machine_id")
    private String machineId;

    /**
     * 设备编号
     */
    @Column(name = "machine_num")
    private String machineNum;

    @Column(name = "raw_temperature")
    private BigDecimal rawTemperature;

    /**
     * 体温
     */
    private BigDecimal temperature;

    /**
     * 电量
     */
    private Integer pattery;

    /**
     * 状态
     */
    @Column(name = "temperature_status")
    private String temperatureStatus;

    /**
     * 记录时间
     */
    @Column(name = "create_time")
    private String createTime;

    /**
     * 时间戳
     */
    private Long timestamp;

    /**
     * 信号强度 越大越接近
     */
    private Integer rssi;

    /**
     * 路由器id
     */
    @Column(name = "repeater_id")
    private Integer repeaterId;

    /**
     * 获取体温记录id
     *
     * @return temperature_id - 体温记录id
     */
    public Integer getTemperatureId() {
        return temperatureId;
    }

    /**
     * 设置体温记录id
     *
     * @param temperatureId 体温记录id
     */
    public void setTemperatureId(Integer temperatureId) {
        this.temperatureId = temperatureId;
    }

    /**
     * 获取设备ID
     *
     * @return machine_id - 设备ID
     */
    public String getMachineId() {
        return machineId;
    }

    /**
     * 设置设备ID
     *
     * @param machineId 设备ID
     */
    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }

    /**
     * 获取设备编号
     *
     * @return machine_num - 设备编号
     */
    public String getMachineNum() {
        return machineNum;
    }

    /**
     * 设置设备编号
     *
     * @param machineNum 设备编号
     */
    public void setMachineNum(String machineNum) {
        this.machineNum = machineNum;
    }

    /**
     * @return raw_temperature
     */
    public BigDecimal getRawTemperature() {
        return rawTemperature;
    }

    /**
     * @param rawTemperature
     */
    public void setRawTemperature(BigDecimal rawTemperature) {
        this.rawTemperature = rawTemperature;
    }

    /**
     * 获取体温
     *
     * @return temperature - 体温
     */
    public BigDecimal getTemperature() {
        return temperature;
    }

    /**
     * 设置体温
     *
     * @param temperature 体温
     */
    public void setTemperature(BigDecimal temperature) {
        this.temperature = temperature;
    }

    /**
     * 获取电量
     *
     * @return pattery - 电量
     */
    public Integer getPattery() {
        return pattery;
    }

    /**
     * 设置电量
     *
     * @param pattery 电量
     */
    public void setPattery(Integer pattery) {
        this.pattery = pattery;
    }

    /**
     * 获取状态
     *
     * @return temperature_status - 状态
     */
    public String getTemperatureStatus() {
        return temperatureStatus;
    }

    /**
     * 设置状态
     *
     * @param temperatureStatus 状态
     */
    public void setTemperatureStatus(String temperatureStatus) {
        this.temperatureStatus = temperatureStatus;
    }

    /**
     * 获取记录时间
     *
     * @return create_time - 记录时间
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * 设置记录时间
     *
     * @param createTime 记录时间
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取时间戳
     *
     * @return timestamp - 时间戳
     */
    public Long getTimestamp() {
        return timestamp;
    }

    /**
     * 设置时间戳
     *
     * @param timestamp 时间戳
     */
    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * 获取信号强度 越大越接近
     *
     * @return rssi - 信号强度 越大越接近
     */
    public Integer getRssi() {
        return rssi;
    }

    /**
     * 设置信号强度 越大越接近
     *
     * @param rssi 信号强度 越大越接近
     */
    public void setRssi(Integer rssi) {
        this.rssi = rssi;
    }

    /**
     * 获取路由器id
     *
     * @return repeater_id - 路由器id
     */
    public Integer getRepeaterId() {
        return repeaterId;
    }

    /**
     * 设置路由器id
     *
     * @param repeaterId 路由器id
     */
    public void setRepeaterId(Integer repeaterId) {
        this.repeaterId = repeaterId;
    }
}