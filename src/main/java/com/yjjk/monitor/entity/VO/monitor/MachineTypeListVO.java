package com.yjjk.monitor.entity.VO.monitor;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @program: monitor2
 * @description: 设备概况
 * @author: CentreS
 * @create: 2020-05-11 16:54:39
 **/
@Data
@Accessors(chain = true)
@ApiModel("typeList")
public class MachineTypeListVO {

    @ApiModelProperty(value = "设备概况：typeId")
    private Integer machineTypeId;
    @ApiModelProperty(value = "设备概况：设备类型名称")
    private String typeName;
    @ApiModelProperty(value = "设备概况：0-体温 1-心电 2-血氧 3-离床感应")
    private Integer id;
    @ApiModelProperty(value = "设备概况：床位信息")
    private String value;
    @ApiModelProperty(value = "设备概况：设备数量")
    private Integer machineCount;
}
