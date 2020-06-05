package com.yjjk.monitor.entity.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Data
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
    private String createTime;

    /**
     * 时间戳
     */
    private Long timestamp;
    private Integer repeaterId;

}