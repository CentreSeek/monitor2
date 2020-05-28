/**
 * Copyright (C), 2019, 义金(杭州)健康科技有限公司
 * FileName: PatientTemperature
 * Author:   CentreS
 * Date:     2019/7/22 16:47
 * Description: 监控病人体温
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.monitor.entity.VO.monitor;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author CentreS
 * @Description: 监控病人体温
 * @create 2019/7/22
 */
@Data
@Accessors(chain = true)
@ApiModel("list")
public class MonitorMachineListVO {

    @ApiModelProperty(value = "类型： 0-体温 1-心电 2-血氧 3-离床感应")
    private Integer type;
    @ApiModelProperty(value = "类型： 0-体温 1-心电 2-血氧 3-离床感应")
    private String typeName;
    @ApiModelProperty(value = "record:设备ID")
    private Integer machineId;
    @ApiModelProperty(value = "record:设备编号")
    private String machineNo;
    @ApiModelProperty(value = "record:设备sn")
    private String machineSn;
    @ApiModelProperty(value = "record:0-未使用 1-使用中")
    private Integer usageState;

    public static final int USAGE_UN_USED = 0;
    public static final int USAGE_USED = 1;

}
