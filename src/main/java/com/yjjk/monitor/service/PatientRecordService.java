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

import com.yjjk.monitor.entity.ZsPatientRecord;
import com.yjjk.monitor.entity.json.TemperatureHistory;
import com.yjjk.monitor.entity.vo.PatientTemperature;
import com.yjjk.monitor.entity.vo.RecordHistory;
import com.yjjk.monitor.entity.vo.UseMachine;

import java.util.List;
import java.util.Map;

/**
 * @Description: 历史记录
 * @author CentreS
 * @create 2019/7/22
 */
public interface PatientRecordService {

    /**
     * 新增历史记录
     * @param patientRecord
     * @return
     */
    int addPatientRecord(ZsPatientRecord patientRecord);

    /**
     * 更新历史记录信息
     * @param patientRecord
     * @return
     */
    int updateByPrimaryKey(ZsPatientRecord patientRecord);

    /**
     * 查询历史记录
     * @param recordId
     * @return
     */
    ZsPatientRecord selectByPrimaryKey(Long recordId);

    /**
     * 获取监控列表
     * @return
     */
    List<UseMachine> getMonitorsInfo(Integer departmentId);

//    /**
//     * 获取实时监控信息
//     * @return
//     */
//    @Deprecated
//    List<PatientTemperature> getMinitorsTemperature(Integer departmentId);

    /**
     * 获取历史记录
     * @return
     */
    List<RecordHistory> getRecordHistory(RecordHistory recordHistory);

    /**
     * 获取历史记录总数
     * @param recordHistory
     * @return
     */
    int getRecordHistoryCount(RecordHistory recordHistory);

    /**
     * 停止监测
     * @return
     */
    int stopMonitoring(Integer patientId, Integer machineId);

    /**
     * 获取实时体温记录
     * @param paraMap
     * @return
     */
    List<TemperatureHistory> getCurrentTemperatureRecord(Map<String,Object> paraMap);

    /**
     * 更换设备
     * @param machineId1 停用的设备
     * @param machineId2 启用的设备
     * @return
     */
    boolean changeMachine(Integer machineId1, Integer machineId2);

    /**
     * 查询当前病床已绑定病人的数量
     * @param bedId
     * @return
     */
    int selectByBedId(Integer bedId);

}
