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
 * @Description: 监控病人体温
 * @author CentreS
 * @create 2019/7/22
 */
@Data
@Accessors(chain = true)
@ApiModel("list")
public class MonitorMachineListVO {

    @ApiModelProperty(value = "类型： 0-体温 1-心电 2-血氧 3-离床感应")
    private Integer type;
    @ApiModelProperty(value = "record:设备ID")
    private Integer machineId;
    @ApiModelProperty(value = "record:设备编号")
    private String machineNo;
    @ApiModelProperty(value = "record:设备sn")
    private String machineSn;

}
