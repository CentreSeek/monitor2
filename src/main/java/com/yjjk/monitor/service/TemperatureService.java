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

/**
 * @author CentreS
 * @Description: 历史记录
 * @create 2019/7/22
 */
@Service
public interface TemperatureService {

//    /**
//     * 新增历史记录
//     *
//     * @param patientRecord
//     * @return
//     */
//    int addPatientRecord(ZsPatientRecord patientRecord);
//
//    /**
//     * 更新历史记录信息
//     *
//     * @param patientRecord
//     * @return
//     */
//    int updateByPrimaryKey(ZsPatientRecord patientRecord);
//
//    /**
//     * 查询历史记录
//     *
//     * @param recordId
//     * @return
//     */
//    ZsPatientRecord selectByPrimaryKey(Long recordId);
//
//    /**
//     * 获取监控列表
//     *
//     * @param departmentId
//     * @return
//     */
//    List<UseMachineVO> getMonitorsInfo(Integer departmentId);
//
//    /**
//     * 添加体温数据
//     * @param list
//     * @param departmentId
//     * @return
//     */
//    List<UseMachineVO> updateTemperature(List<UseMachineVO> list, Integer departmentId);
//
//    /**
//     * 通过病床信息筛选
//     *
//     * @param list
//     * @param start
//     * @param end
//     * @return
//     */
//    List<UseMachineVO> selectiveByBedId(List<UseMachineVO> list, Integer start, Integer end);
//
//    /**
//     * 筛选使用中的设备
//     *
//     * @param list
//     * @return
//     */
//    List<UseMachineVO> isUsed(List<UseMachineVO> list);
//
////    /**
////     * 获取实时监控信息
////     * @return
////     */
////    @Deprecated
////    List<PatientTemperature> getMinitorsTemperature(Integer departmentId);
//
//    /**
//     * 获取历史记录
//     *
//     * @return
//     */
//    List<RecordHistory> getRecordHistory(RecordHistory recordHistory);
//
//    /**
//     * 获取历史记录总数
//     *
//     * @param recordHistory
//     * @return
//     */
//    int getRecordHistoryCount(RecordHistory recordHistory);
//
//    /**
//     * 停止监测
//     *
//     * @return
//     */
//    int stopMonitoring(ZsPatientRecord patientRecord);
//
//    /**
//     * 获取实时体温记录
//     *
//     * @param paraMap
//     * @return
//     */
//    List<TemperatureHistory> getCurrentTemperatureRecord(Map<String, Object> paraMap);
//
//    /**
//     * 更换设备
//     *
//     * @param machineId1 停用的设备
//     * @param machineId2 启用的设备
//     * @return
//     */
//    boolean changeMachine(Integer machineId1, Integer machineId2);
//
//    /**
//     * 查询当前病床已绑定病人的数量
//     *
//     * @param bedId
//     * @return
//     */
//    int selectByBedId(Integer bedId);
//
//    /**
//     * 使用patientId查询record信息
//     *
//     * @param patientId
//     * @return
//     */
//    ZsPatientRecord selectByPatientId(Integer patientId);
//
//    /**
//     * 体温记录导出
//     *
//     * @param paraMap
//     * @return
//     */
//    List<RecordHistory2Excel> getExportList(Map<String, Object> paraMap);
//
//    /**
//     * 获取个人体温记录
//     * @param paraMap
//     * @return
//     */
//    List<RecordHistory2Excel> getPrivateExport(Map<String, Object> paraMap);
//
//    /**
//     * 处理体温数据
//     * @param list
//     * @param paraMap
//     * @param machineId
//     * @return
//     */
//    Map<String, Object> parseTemperature(List<TemperatureHistory> list, Map<String, Object> paraMap, Integer machineId);
//
//
//    /**
//     * 通过病床信息获取使用中的record
//     * @param bedId
//     * @return
//     */
//    List<ZsPatientRecord> getUsageByBedId(Integer bedId);
}
