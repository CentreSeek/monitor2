package com.yjjk.monitor.entity.pojo;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "hospital_bed")
public class HospitalBed {
    /**
     * 病床id
     */
    @Id
    @Column(name = "bed_id")
    private Integer bedId;

    /**
     * 房间id
     */
    @Column(name = "room_id")
    private Integer roomId;

    /**
     * 床位名
     */
    private String name;

    @Column(name = "create_time")
    private String createTime;

    /**
     * 0：正常 1：删除
     */
    private Integer status;


}