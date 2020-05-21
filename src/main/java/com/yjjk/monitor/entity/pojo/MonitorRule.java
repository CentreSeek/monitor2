package com.yjjk.monitor.entity.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Accessors(chain = true)
@Table(name = "monitor_rule")
public class MonitorRule {
    /**
     * 科室id：默认规则为-1
     */
    @Column(name = "department_id")
    private Integer departmentId;

    /**
     * 0：体温 1：心率 2：呼吸率 3：血氧
     */
    private Integer type;

    /**
     * 参数1
     */
    @Column(name = "param_one")
    private Double paramOne;

    /**
     * 参数2
     */
    @Column(name = "param_two")
    private Double paramTwo;

    /**
     * 参数3
     */
    @Column(name = "param_three")
    private Double paramThree;

    /**
     * 高低预警：0 开，1 关
     */
    @Column(name = "alert_flag")
    private Integer alertFlag;

    /**
     * 偏低预警阈值
     */
    @Column(name = "low_alert")
    private Double lowAlert;

    /**
     * 偏高预警阈值
     */
    @Column(name = "high_alert")
    private Double highAlert;

    /**
     * 修改人id
     */
    @Column(name = "manager_id")
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