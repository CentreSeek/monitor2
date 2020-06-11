package com.yjjk.monitor.entity.pojo;

import lombok.Data;
import org.junit.experimental.theories.DataPoints;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Table(name = "zs_blood_oxygen_info")
public class ZsBloodOxygenInfo {
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
     * 设备编号
     */
    @Column(name = "machine_num")
    private String machineNum;

    /**
     * 血氧 范围：35~100%，无效值：127
     */
    @Column(name = "blood_oxygen")
    private Integer bloodOxygen;

    /**
     * 脉率 范围：25~250bmp，无效值：255
     */
    @Column(name = "pulse_rate")
    private Integer pulseRate;

    /**
     * 设备剩余电量
     */
    private Integer battery;

    /**
     * 血氧状态	0:正常测量；1:探头脱落；2无手指；4无脉搏信号；8找到脉搏峰；16血氧计算中
     */
    @Column(name = "blood_oxygen_status")
    private String bloodOxygenStatus;

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
     * 信号强度1~200%，无效值：0，表示脉搏搏动强度
     */
    private Double pi;

    /**
     * 路由器id
     */
    @Column(name = "repeater_id")
    private Integer repeaterId;

}