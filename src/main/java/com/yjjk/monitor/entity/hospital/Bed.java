package com.yjjk.monitor.entity.hospital;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @program: monitor2
 * @description:
 * @author: CentreS
 * @create: 2020-11-30 14:38:12
 **/
@Data
@Accessors(chain = true)
@Table(name = "hospital_bed")
public class Bed {
    @Id
    @Column(name = "bed_id")
    @ApiModelProperty(value = "null:插入  else:更新")
    private Integer bedId;

//    @ApiModelProperty(value = "房间id（更新必填）")
//    @Column(name = "room_id")
//    private Integer roomId;

    @ApiModelProperty(value = "床位名（必填）")
    private String name;
    //    @ApiModelProperty(value = "0：正常 1：删除（默认0）")
//    private Integer status;
}
