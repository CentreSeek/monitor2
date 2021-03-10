package com.yjjk.monitor.entity.VO.monitor;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

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
public class MonitorBaseVO {
    private MonitorBloodVO monitorBloodVO;
    private MonitorRespiratoryRateVO monitorRespiratoryRateVO;
    private MonitorHeartRateVO monitorHeartRateVO;
    private MonitorTemperatureVO monitorTemperatureVO;

    private List<MonitorMachineListVO> machineList;

    @ApiModelProperty(value = "base：病单号")
    private Integer baseId;
    /**
     * patientInfo
     */
    @ApiModelProperty(value = "patientInfo:病人id")
    private Integer patientId;
    @ApiModelProperty(value = "patientInfo:病人名")
    private String patientName;
    @ApiModelProperty(value = "patientInfo:病历号")
    private String caseNum;
    @ApiModelProperty(value = "patientInfo:护理级别 特级-0 一级-1 二级-2 三级-3")
    private Integer levelOfNursing;

    /**
     * 病房信息
     */
    @ApiModelProperty(value = "病房信息:病床ID")
    private Integer bedId;
    @ApiModelProperty(value = "病房信息:病床名")
    private String bedName;
    @ApiModelProperty(value = "病房信息：是否存在异常数据 0-未启用 1-正常 2-异常1(orange) 3-异常2(red)")
    private Integer errorStatus;
    @ApiModelProperty(value = "离床感应：使用情况 0-未使用 1-使用")
    private Integer sleepingUsage;
    @ApiModelProperty(value = "离床感应：离床状态 0-离床 1-在床")
    private Integer sleepingState;
    @ApiModelProperty(value = "离床感应：离床时间")
    private Long sleepingLeaveTimes;

    private Integer recordTemperatureId;
    private Integer recordEcgId;
    private Integer recordBloodId;
    private Integer recordSleepingId;


}
