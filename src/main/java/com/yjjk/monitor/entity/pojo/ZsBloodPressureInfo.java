package com.yjjk.monitor.entity.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@Data
@Accessors(chain = true)
@Table(name = "zs_blood_pressure_info")
public class ZsBloodPressureInfo {
    /**
     * id
     */
    private Integer id;

    /**
     * 设备ID
     */
    @Column(name = "machine_id")
    private String machineId;

    /**
     * 血压
     */
    @Column(name = "blood_pressure")
    private Integer bloodPressure;

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
     * 路由器id
     */
    @Column(name = "repeater_id")
    private Integer repeaterId;
}