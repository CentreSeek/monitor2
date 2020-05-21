package com.yjjk.monitor.entity.pojo;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
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

}