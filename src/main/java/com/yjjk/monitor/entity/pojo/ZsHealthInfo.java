package com.yjjk.monitor.entity.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "zs_health_info")
public class ZsHealthInfo {
    /**
     * 体温记录id
     */
    @Id
    @Column(name = "temperature_id")
    private Integer temperatureId;

    /**
     * 设备编号
     */
    @Column(name = "machine_id")
    private String machineId;

    /**
     * 心率
     */
    @Column(name = "heart_rate")
    private BigDecimal heartRate;

    /**
     * 呼吸率
     */
    @Column(name = "respiratory_rate")
    private BigDecimal respiratoryRate;

    /**
     * 记录时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 时间戳
     */
    private Long timestamp;

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
     * 获取设备编号
     *
     * @return machine_id - 设备编号
     */
    public String getMachineId() {
        return machineId;
    }

    /**
     * 设置设备编号
     *
     * @param machineId 设备编号
     */
    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }

    /**
     * 获取心率
     *
     * @return heart_rate - 心率
     */
    public BigDecimal getHeartRate() {
        return heartRate;
    }

    /**
     * 设置心率
     *
     * @param heartRate 心率
     */
    public void setHeartRate(BigDecimal heartRate) {
        this.heartRate = heartRate;
    }

    /**
     * 获取呼吸率
     *
     * @return respiratory_rate - 呼吸率
     */
    public BigDecimal getRespiratoryRate() {
        return respiratoryRate;
    }

    /**
     * 设置呼吸率
     *
     * @param respiratoryRate 呼吸率
     */
    public void setRespiratoryRate(BigDecimal respiratoryRate) {
        this.respiratoryRate = respiratoryRate;
    }

    /**
     * 获取记录时间
     *
     * @return create_time - 记录时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置记录时间
     *
     * @param createTime 记录时间
     */
    public void setCreateTime(Date createTime) {
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
}