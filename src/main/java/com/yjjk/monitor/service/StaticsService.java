/**
 * Copyright (C), 2019, 义金(杭州)健康科技有限公司
 * FileName: PatientRecordService
 * Author:   CentreS
 * Date:     2019/7/22 11:41
 * Description: 历史记录
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.monitor.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author CentreS
 * @Description: 历史记录
 * @create 2019/7/22
 */
@Service
public interface StaticsService {

    /**
     * 使用设备数量
     * @param type
     * @param departmentId,
     * @param start
     * @param end
     * @return
     */
    Map<String, Integer> getMachineStatics(Integer departmentId,Integer type, String start, String end);

    /**
     * 科室使用人数
     * @param type
     * @param start
     * @param end
     * @return
     */
    Map<String, Integer> usePeoples(Integer type, String start, String end);
    Map<String, Integer> monitorPeriods(Integer departmentId,Integer type);
}
