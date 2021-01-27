package com.yjjk.monitor.entity.BO.monitor.rule;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(chain = true)
@ApiModel("设置规则")
public class MonitorRuleBOData {
    @Valid
    @ApiModelProperty(value = "设置规则：数据")
    private List<MonitorRuleBO> list;
    @ApiModelProperty(value = "设置规则：科室id（默认规则为-1）")
    private Integer departmentId;
    @ApiModelProperty(value = "设置规则：病人id")
    private Integer patientId;

    public static void main(String[] args) {
        MonitorRuleBOData d = new MonitorRuleBOData();
        MonitorRuleBO a0 = new MonitorRuleBO();
        MonitorRuleBO a1 = new MonitorRuleBO();
        MonitorRuleBO a2 = new MonitorRuleBO();
        MonitorRuleBO a3 = new MonitorRuleBO();
        a0.setType(0).setParamOne(10.0).setParamTwo(10.0).setParamThree(10.0);
        a1.setType(1).setParamOne(10.0).setParamTwo(10.0).setParamThree(10.0);
        a2.setType(2).setParamOne(10.0).setParamTwo(10.0).setParamThree(10.0);
        a3.setType(3).setParamOne(10.0).setParamTwo(10.0).setParamThree(10.0);
        List l = new ArrayList();
        l.add(a0);
        l.add(a1);
        l.add(a2);
        l.add(a3);
        d.setList(l);
        d.setDepartmentId(1);
        String s = JSON.toJSONString(d);
        System.out.println(s);
    }
}