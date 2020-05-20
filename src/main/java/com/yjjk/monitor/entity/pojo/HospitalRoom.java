package com.yjjk.monitor.entity.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Data
@Table(name = "hospital_room")
public class HospitalRoom {
    /**
     * 病房id
     */
    @Id
    @Column(name = "room_id")
    private Integer roomId;

    /**
     * 科室id
     */
    @Column(name = "department_id")
    private Integer departmentId;

    /**
     * 病房名称（编号）
     */
    private String name;

    @Column(name = "create_time")
    private String createTime;

    /**
     * 0：正常 1：删除
     */
    private Integer status;
    private List<HospitalBed> beds;

}