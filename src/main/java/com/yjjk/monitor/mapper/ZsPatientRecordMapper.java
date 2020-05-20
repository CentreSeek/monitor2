package com.yjjk.monitor.mapper;

import com.yjjk.monitor.entity.pojo.ZsMachineInfo;
import com.yjjk.monitor.my.mapper.MyMapper;
import org.apache.ibatis.annotations.Mapper;

public interface ZsPatientRecordMapper  {
//    int deleteByPrimaryKey(Long recordId);
//
//    int insert(ZsPatientRecord record);
//
//    int insertSelective(ZsPatientRecord record);
//
//    ZsPatientRecord selectByPrimaryKey(Long recordId);
//
//    int updateByPrimaryKeySelective(ZsPatientRecord record);
//
//    int updateByPrimaryKeyWithBLOBs(ZsPatientRecord record);
//
//    int updateByPrimaryKey(ZsPatientRecord record);
//
//    /**
//     * select---获取监控列表
//     * @return
//     */
//    List<UseMachineVO> getMonitorsInfo(Integer departmentId);
//
//    /**
//     * select---获取实时监控信息
//     * @return
//     */
//    List<PatientTemperature> getMinitorsTemperature(Integer departmentId);
//
//    /**
//     * select---获取历史记录
//     * @return
//     */
//    List<RecordHistory> getRecordHistory(RecordHistory recordHistory);
//
//    /**
//     * select---获取历史记录总数
//     * @param recordHistory
//     * @return
//     */
//    int getRecordHistoryCount(RecordHistory recordHistory);
//
//    /**
//     * select---体温历史记录
//     * @return
//     */
//    List<TemperatureHistory> selectTemperatureHistory(Map<String,Object> paraMap);
//
//    /**
//     * update---使用病人id更新历史记录表
//     * @param patientRecord
//     * @return
//     */
//    int updateSelectiveByPatientId(ZsPatientRecord patientRecord);
//
//    /**
//     * select---查询当前病床已绑定病人的数量
//     * @param bedId
//     * @return
//     */
//    int selectByBedId(Integer bedId);
//
//    /**
//     * select---使用patientId和machineId查询record信息
//     * @param paraMap
//     * @return
//     */
//    ZsPatientRecord selectByPatientAndMachine(Map<String, Object> paraMap);
//
//    /**
//     * select---使用patientId查询record信息
//     * @param patientId
//     * @return
//     */
//    ZsPatientRecord selectByPatientId(Integer patientId);
//
//    /**
//     * 体温记录导出
//     * @param paraMap
//     * @return
//     */
//    List<RecordHistory2Excel> getExportList(Map<String, Object> paraMap);
//
//    RecordHistory2Excel getPrivateExport(Integer recordId);
//
//    ZsPatientRecord selectByRecordId(Long recordId);
//
//    /**
//     * 通过病床信息获取使用中的record
//     * @param bedId
//     * @return
//     */
//    List<ZsPatientRecord> getUsageByBedId(Integer bedId);
}