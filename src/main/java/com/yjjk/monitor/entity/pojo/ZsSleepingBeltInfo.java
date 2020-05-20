package com.yjjk.monitor.entity.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "zs_sleeping_belt_info")
public class ZsSleepingBeltInfo {
    /**
     * 体温记录id
     */
    @Id
    private Integer id;

    /**
     * 设备ID
     */
    @Column(name = "machine_id")
    private String machineId;

    /**
     * 心率
     */
    @Column(name = "heart_rate")
    private String heartRate;

    /**
     * 呼吸率
     */
    @Column(name = "respiratory_rate")
    private String respiratoryRate;

    /**
     * 睡眠状态 离床:0，入睡:1 ，清醒(浅睡):2，在床状态:3 
     */
    @Column(name = "sleep_state")
    private Integer sleepState;

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
     * 温度 摄氏度 127为无效数据
     */
    private String temperature;

    /**
     * 湿度 百分比 127为无效数据
     */
    private String humidity;

    /**
     * 路由器id
     */
    @Column(name = "repeater_id")
    private Integer repeaterId;

    /**
     * 获取体温记录id
     *
     * @return id - 体温记录id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置体温记录id
     *
     * @param id 体温记录id
     */
    public void setId(Integer id) {
        this.id = id;
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
     * 获取心率
     *
     * @return heart_rate - 心率
     */
    public String getHeartRate() {
        return heartRate;
    }

    /**
     * 设置心率
     *
     * @param heartRate 心率
     */
    public void setHeartRate(String heartRate) {
        this.heartRate = heartRate;
    }

    /**
     * 获取呼吸率
     *
     * @return respiratory_rate - 呼吸率
     */
    public String getRespiratoryRate() {
        return respiratoryRate;
    }

    /**
     * 设置呼吸率
     *
     * @param respiratoryRate 呼吸率
     */
    public void setRespiratoryRate(String respiratoryRate) {
        this.respiratoryRate = respiratoryRate;
    }

    /**
     * 获取睡眠状态 离床:0，入睡:1 ，清醒(浅睡):2，在床状态:3 
     *
     * @return sleep_state - 睡眠状态 离床:0，入睡:1 ，清醒(浅睡):2，在床状态:3 
     */
    public Integer getSleepState() {
        return sleepState;
    }

    /**
     * 设置睡眠状态 离床:0，入睡:1 ，清醒(浅睡):2，在床状态:3 
     *
     * @param sleepState 睡眠状态 离床:0，入睡:1 ，清醒(浅睡):2，在床状态:3 
     */
    public void setSleepState(Integer sleepState) {
        this.sleepState = sleepState;
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

    /**
     * 获取温度 摄氏度 127为无效数据
     *
     * @return temperature - 温度 摄氏度 127为无效数据
     */
    public String getTemperature() {
        return temperature;
    }

    /**
     * 设置温度 摄氏度 127为无效数据
     *
     * @param temperature 温度 摄氏度 127为无效数据
     */
    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    /**
     * 获取湿度 百分比 127为无效数据
     *
     * @return humidity - 湿度 百分比 127为无效数据
     */
    public String getHumidity() {
        return humidity;
    }

    /**
     * 设置湿度 百分比 127为无效数据
     *
     * @param humidity 湿度 百分比 127为无效数据
     */
    public void setHumidity(String humidity) {
        this.humidity = humidity;
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