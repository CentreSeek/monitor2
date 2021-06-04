package com.yjjk.monitor.entity.VO.monitor;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @program: monitor2
 * @description: 血压
 * @author: CentreS
 * @create: 2020-05-11 17:31:55
 **/
@Data
@Accessors(chain = true)
@ApiModel("血压")
public class MonitorBloodPressureVO {

    @ApiModelProperty(value = "record:使用状态 0：启用中 1：未启用 2：启用待测量")
    private Integer recordState;
    @ApiModelProperty(value = "record:病单ID")
    private Integer recordId;
    @ApiModelProperty(value = "record:使用状态 首次测量时间")
    private String firstStart;
    @ApiModelProperty(value = "param：预警 0：正常 1：橙色 2：红色")
    private Integer bloodPressureAlert;
    @ApiModelProperty(value = "异常预警：0-正常 1-异常预警")
    private Integer alert;

    @ApiModelProperty(value = "param：收缩压")
    private Integer sys;
    @ApiModelProperty(value = "param：扩张压")
    private Integer dia;

    private String bloodPressure;
    @ApiModelProperty(value = "param：时间戳")
    private String timestamp;
    @ApiModelProperty(value = "param：使用时间")
    private Integer timeDays;
    private Integer timeHours;
    private Integer timeMinutes;
}
