package com.yjjk.monitor.entity.VO.monitor;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.yjjk.monitor.entity.ListVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: monitor2
 * @description: 监控页面
 * @author: CentreS
 * @create: 2020-05-11 16:54:39
 **/
@Data
@Accessors(chain = true)
@ApiModel("监控页面")
public class MonitorVO {

    @ApiModelProperty(value = "monitor：监控信息列表")
    private List<MonitorBaseVO> monitorVOList;
    @ApiModelProperty(value = "设备概况：设备类型")
    private List<MachineTypeListVO> machineTypeList;
    @ApiModelProperty(value = "设备概况：设备电量异常")
    private List<MachinesInfoVO> machinesInfoVOList;
    @ApiModelProperty(value = "床位总数")
    private Integer bedCount;
}

