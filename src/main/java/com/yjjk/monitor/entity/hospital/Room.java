package com.yjjk.monitor.entity.hospital;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

/**
 * @program: monitor2
 * @description:
 * @author: CentreS
 * @create: 2020-11-30 14:38:20
 **/
@Data
@Accessors(chain = true)
public class Room {
    /**
     * 病房id
     */
    @Id
    @Column(name = "room_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "null:插入  else:更新")
    private Integer roomId;
    private Integer departmentId;

//    @Column(name = "department_id")
//    @ApiModelProperty(value = "科室id（更新必填）")
//    private Integer departmentId;

    @ApiModelProperty(value = "病房名称（必填）")
    private String name;

    //    @ApiModelProperty(value = "0：正常 1：删除（默认0）")
//    private Integer status;
    private List<Bed> beds;

}