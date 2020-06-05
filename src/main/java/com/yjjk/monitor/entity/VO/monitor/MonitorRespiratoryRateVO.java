package com.yjjk.monitor.entity.VO.monitor;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @program: monitor2
 * @description: 呼吸率
 * @author: CentreS
 * @create: 2020-05-11 17:32:34
 **/
@Data
@Accessors(chain = true)
@ApiModel("呼吸率")
public class MonitorRespiratoryRateVO {

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
    @ApiModelProperty(value = "param：呼吸率")
    private String respiratory;
    @ApiModelProperty(value = "param：预警 0：正常 1：橙色 2：红色")
    private Integer respiratoryAlert;
    @ApiModelProperty(value = "param：使用时间(min)")
    private String useTimes;
    @ApiModelProperty(value = "param：设备电量")
    private Integer battery;

    @ApiModelProperty(value = "异常预警：0-正常 1-异常预警")
    private Integer alert;
}
