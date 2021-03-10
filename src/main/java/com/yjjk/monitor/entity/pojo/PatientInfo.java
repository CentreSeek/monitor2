package com.yjjk.monitor.entity.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Accessors(chain = true)
@Table(name = "patient_info")
public class PatientInfo {
    /**
     * 病人id
     */
    @Id
    @Column(name = "patient_id")
    private Integer patientId;

    /**
     * 名字
     */
    private String name;

    /**
     * 病历号
     */
    @Column(name = "case_num")
    private String caseNum;

    @Column(name = "create_time")
    private String createTime;

    /**
     * 0：正常 1：删除
     */
    private Integer status;

    /**
     * 监控规则
     */
    @Column(name = "monitor_rule")
    private String monitorRule;

    /**
     * 护理级别 特级-0 一级-1 二级-2 三级-3
     */
    @Column(name = "level_of_nursing")
    private Integer levelOfNursing;

}