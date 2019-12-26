package com.yjjk.monitor.entity.VO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @program: monitor2.0
 * @description: 查找设备
 * @author: CentreS
 * @create: 2019-12-20 14:04:33
 **/
@Data
@Accessors(chain = true)
@ApiModel("查找设备")
public class SearchMachineVOBase {
    @ApiModelProperty(value = "0：找到 1：找到，非实时 2:未找到")
    private Integer status;
    @ApiModelProperty(value = "房间名称")
    private String roomName;
    @ApiModelProperty(value = "最近一条记录时间")
    private String lastRecordTime;

    @ApiModelProperty(value = "数据")
    private List<SearchMachineVO> list;
}
