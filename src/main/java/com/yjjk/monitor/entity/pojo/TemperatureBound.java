package com.yjjk.monitor.entity.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Accessors(chain = true)
@Table(name = "temperature_bound")
public class TemperatureBound {
    /**
     * 科室id：默认规则为-1
     */
    @Id
    @Column(name = "department_id")
    private Integer departmentId;

    /**
     * 低温阈值
     */
    @Column(name = "low_temperature")
    private Double lowTemperature;

    /**
     * 常温阈值
     */
    @Column(name = "normal_temperature")
    private Double normalTemperature;

    /**
     * 高温阈值
     */
    @Column(name = "high_temperature")
    private Double highTemperature;

    /**
     * 高低温预警：0 开，1 关
     */
    @Column(name = "temperature_alert")
    private Integer temperatureAlert;

    /**
     * 低温预警阈值
     */
    @Column(name = "low_alert")
    private Double lowAlert;

    /**
     * 高温预警阈值
     */
    @Column(name = "high_alert")
    private Double highAlert;

    /**
     * 修改人id
     */
    @Column(name = "id")
    private Integer managerId;

    /**
     * 修改时间
     */
    @Column(name = "change_time")
    private String changeTime;

    /**
     * 0：正常 1：删除
     */
    private Integer status;

}