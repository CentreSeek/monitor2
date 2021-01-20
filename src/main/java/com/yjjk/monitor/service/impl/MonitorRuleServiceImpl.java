/**
 * Copyright (C), 2019, 寻央大人留书
 * FileName: TemperatureAlertServiceImpl
 * Author:   CentreS
 * Date:     2019/9/24 9:24
 * Description: 体温预警规则
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.monitor.service.impl;

import com.alibaba.fastjson.JSON;
import com.yjjk.monitor.entity.BO.monitor.patientRule.MonitorPatientRuleBOData;
import com.yjjk.monitor.entity.BO.monitor.rule.MonitorRuleBO;
import com.yjjk.monitor.entity.pojo.ManagerInfo;
import com.yjjk.monitor.entity.pojo.MonitorRule;
import com.yjjk.monitor.service.BaseService;
import com.yjjk.monitor.service.MonitorRuleService;
import com.yjjk.monitor.utility.DateUtil;
import com.yjjk.monitor.utility.ReflectUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author CentreS
 * @Description: 体温预警规则
 * @create 2019/9/24
 */
@Service
public class MonitorRuleServiceImpl extends BaseService implements MonitorRuleService {
    @Override
    public List<MonitorRule> getMonitorRule(Integer departmentId) {
        Example example = new Example(MonitorRule.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("departmentId", departmentId);
        return super.monitorRuleMapper.selectByExample(example);
    }

    @Override
    public boolean setMonitorRule(List<MonitorRuleBO> list, String token) {
        ManagerInfo managerInfo = super.managerInfoMapper.selectByToken(token);
        for (int i = 0; i < list.size(); i++) {
            MonitorRule monitorRule = ReflectUtils.transformToBean(list.get(i), MonitorRule.class);
            monitorRule.setManagerId(managerInfo.getId())
                    .setChangeTime(DateUtil.getCurrentTime());
            super.monitorRuleMapper.setRule(monitorRule);
        }
        return true;
    }

    @Override
    public boolean setPatientRule(MonitorPatientRuleBOData data) {
        String s = JSON.toJSONString(data.getList());
        super.monitorRuleMapper.setPatientRule(data.getPatientId(), s);
        return true;
    }


//    @Override
//    public List<TemperatureBoundVO> getDefaultAlert(Integer departmentId) {
//        List<TemperatureBoundVO> list = new ArrayList<>();
//        TemperatureBound defaultTemperature = super.temperatureBoundMapper.selectByPrimaryKey(TemperatureConstant.DEFAULT_DEPARTMENT_ID);
//        TemperatureBoundVO temperatureBoundVO = ReflectUtils.transformToBean(defaultTemperature,
//                TemperatureBoundVO.class);
//        list.add(temperatureBoundVO.setList(TemperatureConstant.TEMPERATURE_LIST).setType(TemperatureConstant.ALERT_TYPE_DEFAULT).setFahrenheitList(TemperatureConstant.FAHRENHEIT_LIST));
//        TemperatureBound departmentTemperature = super.temperatureBoundMapper.selectByPrimaryKey(departmentId);
//        if (departmentTemperature == null) {
//            departmentTemperature = defaultTemperature;
//        }
//        TemperatureBoundVO temperatureBoundVO1 = ReflectUtils.transformToBean(departmentTemperature, TemperatureBoundVO.class);
//        list.add(temperatureBoundVO1.setList(TemperatureConstant.TEMPERATURE_LIST).setType(TemperatureConstant.ALERT_TYPE_DEPARTMENT));
//        list.add(transFahrenheit(temperatureBoundVO1));
//        list.add(transFahrenheit(temperatureBoundVO).setType(TemperatureConstant.ALERT_TYPE_FAHRENHEIT));
//        return list;
//    }
//
//    @Override
//    public TemperatureBoundVO transFahrenheit(TemperatureBoundVO pojo) {
//        Double highAlert = null;
//        Double highTemperature = null;
//        Double lowAlert = null;
//        Double lowTemperature = null;
//        Double normalTemperature = null;
//        for (int i = 0; i < TemperatureConstant.FAHRENHEIT_LIST.length; i++) {
//            if (TemperatureConstant.FAHRENHEIT_LIST[i][1].equals(pojo.getHighAlert())) {
//                highAlert = TemperatureConstant.FAHRENHEIT_LIST[i][0];
//            }
//            if (TemperatureConstant.FAHRENHEIT_LIST[i][1].equals(pojo.getHighTemperature())) {
//                highTemperature = TemperatureConstant.FAHRENHEIT_LIST[i][0];
//            }
//            if (TemperatureConstant.FAHRENHEIT_LIST[i][1].equals(pojo.getLowAlert())) {
//                lowAlert = TemperatureConstant.FAHRENHEIT_LIST[i][0];
//            }
//            if (TemperatureConstant.FAHRENHEIT_LIST[i][1].equals(pojo.getLowTemperature())) {
//                lowTemperature = TemperatureConstant.FAHRENHEIT_LIST[i][0];
//            }
//            if (TemperatureConstant.FAHRENHEIT_LIST[i][1].equals(pojo.getNormalTemperature())) {
//                normalTemperature = TemperatureConstant.FAHRENHEIT_LIST[i][0];
//            }
//        }
//
//        TemperatureBoundVO temp = new TemperatureBoundVO();
//        temp.setHighAlert(highAlert)
//                .setHighTemperature(highTemperature)
//                .setLowAlert(lowAlert)
//                .setLowTemperature(lowTemperature)
//                .setNormalTemperature(normalTemperature)
//                .setTemperatureAlert(pojo.getTemperatureAlert())
//                .setDepartmentId(pojo.getDepartmentId())
//                .setFahrenheitList(TemperatureConstant.FAHRENHEIT_LIST)
//                .setType(TemperatureConstant.ALERT_TYPE_FAHRENHEIT);
//        return temp;
//    }
//
//
//    @Override
//    public Integer setTemperatureBound(TemperatureBoundParam param) {
//        TemperatureBound zsTemperatureBound =
//                super.temperatureBoundMapper.selectByPrimaryKey(param.getDepartmentId());
//        int i = 0;
//        if (zsTemperatureBound != null) {
//            i = super.temperatureBoundMapper.updateByPrimaryKeySelective(ReflectUtils.transformToBean(param,
//                    TemperatureBound.class).setChangeTime(DateUtil.getCurrentTime()));
//        } else {
//            i = super.temperatureBoundMapper.insertSelective(ReflectUtils.transformToBean(param,
//                    TemperatureBound.class));
//        }
//        return i;
//    }
//
//    @Override
//    public List<UseMachineVO> updateUseMachine(List<UseMachineVO> monitorsInfo, Integer departmentId) {
//        TemperatureBound temperatureBound = super.temperatureBoundMapper.selectByPrimaryKey(departmentId);
//        if (temperatureBound == null) {
//            // 获取默认规则
//            temperatureBound = super.temperatureBoundMapper.selectByPrimaryKey(TemperatureConstant.DEFAULT_DEPARTMENT_ID);
//        }
//        for (int i = 0; i < monitorsInfo.size(); i++) {
//            if (monitorsInfo.get(i).getTemperature() != null) {
//                Double temperature = Double.parseDouble(monitorsInfo.get(i).getTemperature());
//                monitorsInfo.get(i).setFahrenheit(String.valueOf(MathUtils.centigrade2Fahrenheit(temperature, 1)));
//                /** 设置体温状态 */
//                if (temperature <= temperatureBound.getLowTemperature()) {
//                    monitorsInfo.get(i).setTemperatureStatus(TemperatureConstant.LOW_TEMPERATURE);
//                } else if (temperature <= temperatureBound.getNormalTemperature()) {
//                    monitorsInfo.get(i).setTemperatureStatus(TemperatureConstant.NORMAL_TEMPERATURE);
//                } else if (temperature <= temperatureBound.getHighTemperature()) {
//                    monitorsInfo.get(i).setTemperatureStatus(TemperatureConstant.HIGHER_TEMPERATURE);
//                } else if (temperature > temperatureBound.getHighTemperature()) {
//                    monitorsInfo.get(i).setTemperatureStatus(TemperatureConstant.HIGH_TEMPERATURE);
//                }
//                /** 设置体温预警 */
//                if (temperatureBound.getTemperatureAlert().equals(TemperatureConstant.ALERT_STATUS_CLOSE)) {
//                    break;
//                }
//                if (temperature <= temperatureBound.getLowAlert()) {
//                    monitorsInfo.get(i).setTemperatureAlert(TemperatureConstant.TEMPERATURE_ALERT_LOW);
//                } else if (temperature >= temperatureBound.getHighAlert()) {
//                    monitorsInfo.get(i).setTemperatureAlert(TemperatureConstant.TEMPERATURE_ALERT_HIGH);
//                } else {
//                    monitorsInfo.get(i).setTemperatureAlert(TemperatureConstant.TEMPERATURE_ALERT_NORMAL);
//                }
//            }
//        }
//        return monitorsInfo;
//    }
}
