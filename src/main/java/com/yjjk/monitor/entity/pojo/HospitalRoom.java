package com.yjjk.monitor.entity.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @ApiModelProperty(value = "null:插入  else:更新")
    private Integer roomId;

    /**
     * 科室id
     */
    @Column(name = "department_id")
    @ApiModelProperty(value = "科室id（更新必填）")
    private Integer departmentId;

    /**
     * 病房名称（编号）
     */
    @ApiModelProperty(value = "病房名称（必填）")
    private String name;

    @Column(name = "create_time")
    private String createTime;

    /**
     * 0：正常 1：删除
     */
    @ApiModelProperty(value = "0：正常 1：删除（默认0）")
    private Integer status;
    private List<HospitalBed> beds;

}