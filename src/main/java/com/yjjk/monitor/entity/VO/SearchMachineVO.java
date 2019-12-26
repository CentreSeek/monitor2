package com.yjjk.monitor.entity.VO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @program: monitor2.0
 * @description: 查找设备
 * @author: CentreS
 * @create: 2019-12-20 14:04:33
 **/
@Data
@Accessors(chain = true)
@ApiModel("查找设备list")
public class SearchMachineVO {
    @ApiModelProperty(value = "房间id")
    private Integer roomId;
    @ApiModelProperty(value = "房间名")
    private String roomName;
    @ApiModelProperty(value = "路由状态 0:无路由 1：有路由无设备 2：有路由发现设备")
    private Integer repeaterStatus;
    @ApiModelProperty(value = "路由id")
    private Integer repeaterId;
    @ApiModelProperty(value = "最近一条记录时间")
    private String lastRecordTime;
}
