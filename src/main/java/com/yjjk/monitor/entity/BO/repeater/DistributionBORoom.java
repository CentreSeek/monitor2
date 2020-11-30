package com.yjjk.monitor.entity.BO.repeater;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @program: monitor2
 * @description:
 * @author: CentreS
 * @create: 2020-11-30 15:08:35
 **/
@Data
@Accessors(chain = true)
@ApiModel("分配路由")
public class DistributionBORoom {

    @ApiModelProperty()
    private Integer roomId;
    @ApiModelProperty("路由id")
    private List<Integer> repeaterIds;
}
