package com.yjjk.monitor.entity.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "zs_ecg_info")
public class ZsEcgInfo {
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
     * 记录时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 时间戳
     */
    private Long timestamp;

    /**
     * 心电
     */
    private String ecg;

//    private Integer useTimeMinutes;

}