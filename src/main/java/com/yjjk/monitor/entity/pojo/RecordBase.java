package com.yjjk.monitor.entity.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Accessors(chain = true)
@Table(name = "record_base")
public class RecordBase {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 病人id
     */
    @Column(name = "patient_id")
    private Integer patientId;

    /**
     * 床位id (hospital_bed)
     */
    @Column(name = "bed_id")
    private Integer bedId;

    /**
     * 起始时间
     */
    @Column(name = "start_time")
    private String startTime;

    /**
     * 结束时间
     */
    @Column(name = "end_time")
    private String endTime;

    /**
     * (record_temperature)
     */
    @Column(name = "record_temperature_id")
    private Integer recordTemperatureId;

    /**
     * (record_ecg)
     */
    @Column(name = "record_ecg_id")
    private Integer recordEcgId;

    /**
     * (record_blood)
     */
    @Column(name = "record_blood_id")
    private Integer recordBloodId;

    /**
     * (record_sleeping)
     */
    @Column(name = "record_sleeping_id")
    private Integer recordSleepingId;

    /**
     * (record_blood_pressure)
     */
    @Column(name = "record_blood_pressure_id")
    private Integer recordBloodPressureId;
    /**
     * 0：使用中 1：未使用
     */
    @Column(name = "usage_status")
    private Integer usageStatus;

    /**
     * 设备使用状态 0-未使用 1-使用中
     */
    @Column(name = "machine_temperature_state")
    private Integer machineTemperatureState;

    /**
     * 设备使用状态 0-未使用 1-使用中
     */
    @Column(name = "machine_ecg_state")
    private Integer machineEcgState;

    /**
     * 设备使用状态 0-未使用 1-使用中
     */
    @Column(name = "machine_blood_state")
    private Integer machineBloodState;

    /**
     * 设备使用状态 0-未使用 1-使用中
     */
    @Column(name = "machine_sleeping_state")
    private Integer machineSleepingState;

    /**
     * 设备使用状态 0-未使用 1-使用中
     */
    @Column(name = "machine_blood_pressure_state")
    private Integer machineBloodPressureState;

    /**
     * 状态 0：正常 1：删除
     */
    private Integer status;

    /**
     * 创建时间
     */
    @Column(name = "created_time")
    private String createdTime;

    /**
     * 更新时间
     */
    @Column(name = "updated_time")
    private String updatedTime;

    /**
     * 操作日志(换床、换设备)
     */
    private String log;
}