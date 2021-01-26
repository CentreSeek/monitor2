/**
 * Copyright (C), 2019, 寻央大人留书
 * FileName: TemperatureAlert
 * Author:   CentreS
 * Date:     2019/9/24 9:19
 * Description: 体温预警规则
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.monitor.service;

import com.yjjk.monitor.entity.BO.monitor.rule.MonitorRuleBO;
import com.yjjk.monitor.entity.pojo.MonitorRule;

import java.util.List;

/**
 * @author CentreS
 * @Description: 体温预警规则
 * @create 2019/9/24
 */
public interface MonitorRuleService {

    /**
     * 获取科室规则
     *
     * @param departmentId
     * @return
     */
    List<MonitorRule> getMonitorRule(Integer departmentId);

    /**
     * 设置规则
     *
     * @param list
     * @param token
     * @return
     */
    boolean setMonitorRule(List<MonitorRuleBO> list, String token,Integer departmentId);

    boolean setPatientRule(List<MonitorRuleBO> list, Integer patientId);
}
