package com.yjjk.monitor.entity.VO.monitor;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @program: monitor2
 * @description: 血氧
 * @author: CentreS
 * @create: 2020-05-11 17:31:55
 **/
@Data
@Accessors(chain = true)
@ApiModel("血氧")
public class MonitorBloodVO {


    /**
     * record
     */
    @ApiModelProperty(value = "record:使用状态 0：启用中 1：未启用")
    private Integer recordState;
    @ApiModelProperty(value = "record:病单ID")
    private Integer recordId;
    @ApiModelProperty(value = "record:设备ID")
    private Integer machineId;
    @ApiModelProperty(value = "record:设备编号")
    private String machineNo;
    @ApiModelProperty(value = "record:设备sn")
    private String machineSn;
//    @ApiModelProperty(value = "record:监护设备类型")
//    private Integer machineType;

    /**
     * param
     */
    @ApiModelProperty(value = "param：血氧")
    private String bloodOxygen;
    @ApiModelProperty(value = "param：PI")
    private Integer pi;
    @ApiModelProperty(value = "param：预警 0：正常 1：橙色 2：红色")
    private Integer bloodOxygenAlert;
    @ApiModelProperty(value = "param：使用时间")
    private String useTimes;

    @ApiModelProperty(value = "param：设备电量")
    private Integer battery;
}
