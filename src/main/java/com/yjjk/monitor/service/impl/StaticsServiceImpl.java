/**
 * Copyright (C), 2019, 义金(杭州)健康科技有限公司
 * FileName: PatientRecordServiceImpl
 * Author:   CentreS
 * Date:     2019/7/22 11:41
 * Description: 历史记录
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.monitor.service.impl;

import com.yjjk.monitor.constant.MachineEnum;
import com.yjjk.monitor.entity.VO.StaticsRecordVO;
import com.yjjk.monitor.entity.pojo.HospitalDepartment;
import com.yjjk.monitor.service.BaseService;
import com.yjjk.monitor.service.StaticsService;
import com.yjjk.monitor.utility.DateUtil;
import com.yjjk.monitor.utility.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CentreS
 * @Description: 监控模块
 * @create 2019/7/22
 */
@Service
public class StaticsServiceImpl extends BaseService implements StaticsService {

    @Override
    public Map<String, Integer> getMachineStatics(Integer departmentId, Integer type, String start, String end) {
        start += " 00:00:00";
        end = DateUtil.modifyDateTime(end + " 00:00:00", Calendar.DATE, 1);
        Map<String, Integer> map = cutDate(start, end);
        List<StaticsRecordVO> recordsPeriod = new ArrayList<>();
        if (type.equals(MachineEnum.TEMPERATURE.getType())) {
            recordsPeriod = super.recordTemperatureMapper.getRecordsPeriod(departmentId, start, end);
        }
        if (type.equals(MachineEnum.ECG.getType())) {
            recordsPeriod = super.recordEcgMapper.getRecordsPeriod(departmentId, start, end);
        }
        if (type.equals(MachineEnum.BLOOD.getType())) {
            recordsPeriod = super.recordBloodMapper.getRecordsPeriod(departmentId, start, end);
        }
        if (type.equals(MachineEnum.SLEEPING.getType())) {
            recordsPeriod = super.recordSleepingMapper.getRecordsPeriod(departmentId, start, end);
        }
        for (StaticsRecordVO staticsRecordVO : recordsPeriod) {
            String tempDate = parseDate(staticsRecordVO.getStartTime());
            map.put(tempDate, map.get(tempDate) + 1);
        }
        return map;
    }

    @Override
    public Map<String, Integer> usePeoples(Integer type, String start, String end) {
        start += " 00:00:00";
        end = DateUtil.modifyDateTime(end + " 00:00:00", Calendar.DATE, 1);
        List<HospitalDepartment> hospitalDepartments = super.hospitalDepartmentMapper.selectDepartments();

        Map<String, Integer> map = new LinkedHashMap<>();
        for (HospitalDepartment hospitalDepartment : hospitalDepartments) {
            map.put(hospitalDepartment.getName(), 0);
        }
        List<StaticsRecordVO> recordsPeriod = new ArrayList<>();
        if (type.equals(MachineEnum.TEMPERATURE.getType())) {
            recordsPeriod = super.recordTemperatureMapper.getRecordsPeriod(null, start, end);
        }
        if (type.equals(MachineEnum.ECG.getType())) {
            recordsPeriod = super.recordEcgMapper.getRecordsPeriod(null, start, end);
        }
        if (type.equals(MachineEnum.BLOOD.getType())) {
            recordsPeriod = super.recordBloodMapper.getRecordsPeriod(null, start, end);
        }
        if (type.equals(MachineEnum.SLEEPING.getType())) {
            recordsPeriod = super.recordSleepingMapper.getRecordsPeriod(null, start, end);
        }
        for (StaticsRecordVO staticsRecordVO : recordsPeriod) {
            String departmentName = staticsRecordVO.getDepartmentName();
            map.put(departmentName, map.get(departmentName) + 1);

        }
        return map;
    }

    @Override
    public Map<String, Integer> monitorPeriods(Integer departmentId, Integer type) {
        String end = DateUtil.getCurrentTime();
        String start = DateUtil.modifyDateTime(end, Calendar.DATE, -30);
        Map<String, Integer> map = setMonitorPeriods();
        List<StaticsRecordVO> recordsPeriod = new ArrayList<>();
        if (type.equals(MachineEnum.TEMPERATURE.getType())) {
            recordsPeriod = super.recordTemperatureMapper.getRecordsPeriod(departmentId, start, end);
        }
        if (type.equals(MachineEnum.ECG.getType())) {
            recordsPeriod = super.recordEcgMapper.getRecordsPeriod(departmentId, start, end);
        }
        if (type.equals(MachineEnum.BLOOD.getType())) {
            recordsPeriod = super.recordBloodMapper.getRecordsPeriod(departmentId, start, end);
        }
        if (type.equals(MachineEnum.SLEEPING.getType())) {
            recordsPeriod = super.recordSleepingMapper.getRecordsPeriod(departmentId, start, end);
        }
        for (StaticsRecordVO staticsRecordVO : recordsPeriod) {
            if (StringUtils.isNullorEmpty(staticsRecordVO.getEndTime())) {
                staticsRecordVO.setEndTime(end);
            }
            Integer differ = DateUtil.timeDifferent(staticsRecordVO.getStartTime(), staticsRecordVO.getEndTime(), DateUtil.DAY);
            if (differ <= 3) {
                map.put("0-2天", map.get("0-2天") + 1);
            } else if (differ <= 6) {
                map.put("2-5天", map.get("2-5天") + 1);
            } else if (differ <= 11) {
                map.put("5-10天", map.get("5-10天") + 1);
            } else if (differ <= 16) {
                map.put("10-15天", map.get("10-15天") + 1);
            } else if (differ <= 21) {
                map.put("15-20天", map.get("15-20天") + 1);
            } else if (differ <= 26) {
                map.put("20-25天", map.get("20-25天") + 1);
            } else {
                map.put(">25天", map.get(">25天") + 1);
            }
        }
        if (recordsPeriod.size() != 0) {
            for (String s : map.keySet()) {
                map.put(s, map.get(s) * 100 / recordsPeriod.size());
            }
        }
        return map;
    }

    public static Map<String, Integer> cutDate(String start, String end) {
        String temp = start;
        Map<String, Integer> map = new LinkedHashMap<>();
        while (!temp.equals(end)) {
            String[] s = temp.split(" ");
            map.put(s[0], 0);
            temp = DateUtil.modifyDateTime(temp, Calendar.DATE, 1);
        }
        return map;
    }

//    public static void main(String[] args) {
//        Map<String, Integer> stringIntegerMap = cutDate("2020-08-01 00:00:00", "2020-08-10 00:00:00");
//        System.out.println(stringIntegerMap.toString());
//    }

    public static String parseDate(String dateTime) {
        String[] s = dateTime.split(" ");
        return s[0];
    }

    public static Map<String, Integer> setMonitorPeriods() {
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("0-2天", 0);
        map.put("2-5天", 0);
        map.put("5-10天", 0);
        map.put("10-15天", 0);
        map.put("15-20天", 0);
        map.put("20-25天", 0);
        map.put(">25天", 0);
        return map;
    }

}
