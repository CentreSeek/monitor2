package com.yjjk.monitor.entity.VO.monitor;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @program: monitor2
 * @description: 设备概况
 * @author: CentreS
 * @create: 2020-05-11 16:54:39
 **/
@Data
@Accessors(chain = true)
@ApiModel("设备概况")
public class MachinesInfoVO {

    @ApiModelProperty(value = "设备概况：设备类型 0：体温贴 1：心电贴 2：血氧 3：离床 4：体温盒")
    private Integer machineType;
    @ApiModelProperty(value = "设备概况：名称")
    private String machineTypeName;
    @ApiModelProperty(value = "设备概况：床位信息")
    private String bedName;
    @ApiModelProperty(value = "设备概况：文本提示")
    private String text;
//    @ApiModelProperty(value = "设备概况：时间")
//    private String datetime;
}
