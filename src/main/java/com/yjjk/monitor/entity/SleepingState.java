package com.yjjk.monitor.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @program: monitor2
 * @description:
 * @author: CentreS
 * @create: 2020-06-05 15:48:43
 **/
@Data
@Accessors(chain = true)
@ApiModel("list")
public class SleepingState {

    @ApiModelProperty(value = "machineId")
    private Integer machineId;
    @ApiModelProperty(value = "state")
    private Integer state;
    @ApiModelProperty(value = "time")
    private String time;

}
