package com.yjjk.monitor.entity.pojo;

import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(value = "null:插入  else:更新")
    private Integer bedId;

    /**
     * 房间id
     */
    @ApiModelProperty(value = "房间id（必填）")
    @Column(name = "room_id")
    private Integer roomId;

    /**
     * 床位名
     */
    @ApiModelProperty(value = "床位名（必填）")
    private String name;

    @Column(name = "create_time")
    private String createTime;

    /**
     * 0：正常 1：删除
     */
    @ApiModelProperty(value = "0：正常 1：删除（必填）")
    private Integer status;


}