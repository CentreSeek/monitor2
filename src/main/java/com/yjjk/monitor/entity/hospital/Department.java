package com.yjjk.monitor.entity.hospital;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

/**
 * @program: monitor2
 * @description:
 * @author: CentreS
 * @create: 2020-11-30 14:38:27
 **/
public class Department {
    @Id
    @Column(name = "department_id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @ApiModelProperty(value = "null:插入  else:更新")
    private Integer departmentId;

//    @ApiModelProperty(value = "科室（必填）")
//    private String name;

    @ApiModelProperty(value = "0：正常 1：删除（默认0）")
    private Integer status;
    private List<Room> rooms;

}
