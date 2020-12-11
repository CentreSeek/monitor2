package com.yjjk.monitor.mapper;

import com.yjjk.monitor.entity.pojo.MonitorRule;
import com.yjjk.monitor.my.mapper.MyMapper;

import java.util.List;

public interface MonitorRuleMapper extends MyMapper<MonitorRule> {

    /**
     * 获取规则
     *
     * @param departmentId
     * @param type
     * @param alertFlag
     * @return
     */
    List<MonitorRule> getRule(Integer departmentId, Integer type, Integer alertFlag);

    int setRule(MonitorRule monitorRule);

    /**
     * 新增科室插入初始规则
     *
     * @param departmentId
     * @param managerId
     * @return
     */
    int insertRules(Integer departmentId, Integer managerId);
}