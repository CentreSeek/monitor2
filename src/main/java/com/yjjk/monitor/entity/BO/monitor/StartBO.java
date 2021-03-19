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
package com.yjjk.monitor.entity.BO.monitor;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;


/**
 * @author CentreS
 * @Description: 启用设备
 * @create 2019/7/22
 */
@Data
@Accessors(chain = true)
@ApiModel("启用设备")
public class StartBO {
    @NotNull
    @ApiModelProperty(value = "启用类型： 0-体温 1-心电 2-血氧 3-离床感应 4-血压", required = true)
    private Integer type;
    @NotNull
    @ApiModelProperty(value = "设备id", required = true)
    private Integer machineId;
    @NotNull
    @ApiModelProperty(value = "床位id", required = true)
    private Integer bedId;
    @NotNull
    @ApiModelProperty(value = "患者姓名", required = true)
    private String patientName;
    @NotNull
    @ApiModelProperty(value = "病历号", required = true)
    private String caseNum;
    @NotNull
    @ApiModelProperty(value = "护理级别 特级-0 一级-1 二级-2 三级-3", required = true)
    private Integer levelOfNursing;
}
