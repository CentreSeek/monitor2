package com.yjjk.monitor.entity.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.zip.CheckedInputStream;

@Accessors(chain = true)
@Data
@Table(name = "record_temperature")
public class RecordTemperature {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    /**
     * (record_base) (record_base)
     */
    @Column(name = "base_id")
    private Integer baseId;

    /**
     * (zs_machine_info) (zs_machine_info)
     */
    @Column(name = "machine_id")
    private Integer machineId;

    /**
     * 记录状态 记录状态0：记录中 1：结束
     */
    @Column(name = "record_status")
    private Integer recordStatus;

    /**
     * 启用时间
     */
    @Column(name = "start_time")
    private String startTime;

    /**
     * 结束时间
     */
    @Column(name = "end_time")
    private String endTime;

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
     * 历史记录
     */
    private String history;

}