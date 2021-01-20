package com.yjjk.monitor.entity.BO.monitor.patientRule;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@ApiModel("病人规则：设置规则")
public class MonitorPatientRuleBOData {
    private List<MonitorPatientRuleBO> list;
    private Integer patientId;
}