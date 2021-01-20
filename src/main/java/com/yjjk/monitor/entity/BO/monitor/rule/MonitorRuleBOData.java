package com.yjjk.monitor.entity.BO.monitor.rule;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@ApiModel("设置规则")
public class MonitorRuleBOData {
    private List<MonitorRuleBO> list;

}