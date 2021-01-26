package com.yjjk.monitor.entity.BO.monitor.rule;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@ApiModel("设置规则")
public class MonitorRuleBOData {
    @ApiModelProperty(value = "设置规则：数据")
    private List<MonitorRuleBO> list;
    @ApiModelProperty(value = "设置规则：科室id（默认规则为-1）")
    private Integer departmentId;
    @ApiModelProperty(value = "设置规则：病人id")
    private Integer patientId;

}