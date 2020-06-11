package com.yjjk.monitor.entity.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
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
    private Integer heartRate;

    /**
     * 呼吸率
     */
    @Column(name = "respiratory_rate")
    private Integer respiratoryRate;

    /**
     * 睡眠状态 离床:0，入睡:1 ，清醒(浅睡):2，在床状态:3 
     */
    @Column(name = "sleep_state")
    private Integer sleepState;

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

}