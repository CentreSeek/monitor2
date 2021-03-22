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


    /**
     * record
     */
    @ApiModelProperty(value = "record:使用状态 0：启用中 1：未启用")
    private Integer recordState;
    @ApiModelProperty(value = "record:病单ID")
    private Integer recordId;

    /**
     * param
     */
    @ApiModelProperty(value = "param：收缩压")
    private Integer sys;
    @ApiModelProperty(value = "param：扩张压")
    private Integer dia;
    private String bloodPressure;
    @ApiModelProperty(value = "param：时间戳")
    private String timestamp;
}
