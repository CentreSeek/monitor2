package com.yjjk.monitor.entity.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

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
    private BigDecimal bloodOxygen;

    /**
     * 脉率 范围：25~250bmp，无效值：255
     */
    @Column(name = "pulse_rate")
    private BigDecimal pulseRate;

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
    private Date createTime;

    /**
     * 时间戳
     */
    private Long timestamp;

    /**
     * 信号强度1~200%，无效值：0，表示脉搏搏动强度
     */
    private Integer rssi;

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
     * 获取设备编号
     *
     * @return machine_num - 设备编号
     */
    public String getMachineNum() {
        return machineNum;
    }

    /**
     * 设置设备编号
     *
     * @param machineNum 设备编号
     */
    public void setMachineNum(String machineNum) {
        this.machineNum = machineNum;
    }

    /**
     * 获取血氧 范围：35~100%，无效值：127
     *
     * @return blood_oxygen - 血氧 范围：35~100%，无效值：127
     */
    public BigDecimal getBloodOxygen() {
        return bloodOxygen;
    }

    /**
     * 设置血氧 范围：35~100%，无效值：127
     *
     * @param bloodOxygen 血氧 范围：35~100%，无效值：127
     */
    public void setBloodOxygen(BigDecimal bloodOxygen) {
        this.bloodOxygen = bloodOxygen;
    }

    /**
     * 获取脉率 范围：25~250bmp，无效值：255
     *
     * @return pulse_rate - 脉率 范围：25~250bmp，无效值：255
     */
    public BigDecimal getPulseRate() {
        return pulseRate;
    }

    /**
     * 设置脉率 范围：25~250bmp，无效值：255
     *
     * @param pulseRate 脉率 范围：25~250bmp，无效值：255
     */
    public void setPulseRate(BigDecimal pulseRate) {
        this.pulseRate = pulseRate;
    }

    /**
     * 获取设备剩余电量
     *
     * @return battery - 设备剩余电量
     */
    public Integer getBattery() {
        return battery;
    }

    /**
     * 设置设备剩余电量
     *
     * @param battery 设备剩余电量
     */
    public void setBattery(Integer battery) {
        this.battery = battery;
    }

    /**
     * 获取血氧状态	0:正常测量；1:探头脱落；2无手指；4无脉搏信号；8找到脉搏峰；16血氧计算中
     *
     * @return blood_oxygen_status - 血氧状态	0:正常测量；1:探头脱落；2无手指；4无脉搏信号；8找到脉搏峰；16血氧计算中
     */
    public String getBloodOxygenStatus() {
        return bloodOxygenStatus;
    }

    /**
     * 设置血氧状态	0:正常测量；1:探头脱落；2无手指；4无脉搏信号；8找到脉搏峰；16血氧计算中
     *
     * @param bloodOxygenStatus 血氧状态	0:正常测量；1:探头脱落；2无手指；4无脉搏信号；8找到脉搏峰；16血氧计算中
     */
    public void setBloodOxygenStatus(String bloodOxygenStatus) {
        this.bloodOxygenStatus = bloodOxygenStatus;
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
     * 获取信号强度1~200%，无效值：0，表示脉搏搏动强度
     *
     * @return rssi - 信号强度1~200%，无效值：0，表示脉搏搏动强度
     */
    public Integer getRssi() {
        return rssi;
    }

    /**
     * 设置信号强度1~200%，无效值：0，表示脉搏搏动强度
     *
     * @param rssi 信号强度1~200%，无效值：0，表示脉搏搏动强度
     */
    public void setRssi(Integer rssi) {
        this.rssi = rssi;
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