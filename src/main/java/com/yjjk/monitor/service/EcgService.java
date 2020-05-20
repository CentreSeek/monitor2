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

import com.yjjk.monitor.entity.VO.UseMachineVO;
import com.yjjk.monitor.entity.transaction.BackgroundResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author CentreS
 * @Description: 历史记录
 * @create 2019/7/22
 */
@Service
public interface EcgService {

//    List<EcgMonitorVO> updateUseMachine(List<UseMachineVO> monitorsInfo, Integer departmentId);
//
//    List<HealthHistory> getHealthHistory(Map<String,Object> map);
//
//    HealthHistoryVO parseRateHistory(List<HealthHistory> paramList, HealthHistoryVO paramHealthHistoryVO);
//    HealthHistoryVO screenHistory(HealthHistoryVO healthHistoryVO, String startTime, String endTime);
//
//    boolean stopEcg(ZsPatientRecord paramZsPatientRecord);

    /**
     * 清理导出心电数据
     * @return
     */
    int cleanEcgExport();

    /**
     * @Description 连接心电设备
     * @param machineId
     * @param bedId
     * @param connectionType
     * @return com.yjjk.monitor.entity.transaction.BackgroundResult
     */
    BackgroundResult connectEcgMachine(Integer machineId, Integer bedId, String connectionType) throws Exception;

    /**
     * 查询指定床位是否能启用心电设备
     * @param bedId
     * @return
     */
    boolean hasRepeater(Integer bedId);


//    /**
//     * 清理异常数据
//     * @param list
//     * @return
//     */
//    List<HealthHistory> deleteNullParam(List<HealthHistory> list);
}
