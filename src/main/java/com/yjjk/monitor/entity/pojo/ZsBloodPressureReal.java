package com.yjjk.monitor.entity.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@Data
@Accessors(chain = true)
@Table(name = "zs_blood_pressure_real")
public class ZsBloodPressureReal {
    /**
     * id
     */
    private Integer id;

    /**
     * 设备编号
     */
    @Column(name = "machine_id")
    private String machineId;

    /**
     * 更新心电时间
     */
    @Column(name = "update_time")
    private String updateTime;

    /**
     * 记录时间
     */
    @Column(name = "create_time")
    private String createTime;

    /**
     * 设备时间戳
     */
    private Long timestamp;

    /**
     * 路由器ID
     */
    @Column(name = "repeaterId")
    private Integer repeaterId;

    /**
     * 设备状态
     */
    private Integer status;

    /**
     * 心率
     */
    @Column(name = "hr")
    private Integer hr;
    /**
     * 收缩压
     */
    @Column(name = "sys")
    private Integer sys;
    /**
     * 舒张压
     */
    @Column(name = "dia")
    private Integer dia;
}