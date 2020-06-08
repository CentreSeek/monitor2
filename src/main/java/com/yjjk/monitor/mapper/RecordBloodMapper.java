package com.yjjk.monitor.mapper;

import com.yjjk.monitor.entity.BO.history.GetRecordsBO;
import com.yjjk.monitor.entity.VO.history.RecordsHistory;
import com.yjjk.monitor.entity.VO.monitor.MonitorBloodVO;
import com.yjjk.monitor.entity.VO.monitor.MonitorHeartRateVO;
import com.yjjk.monitor.entity.export.history.HistoryExportBloodVO;
import com.yjjk.monitor.entity.history.BloodHistoryData;
import com.yjjk.monitor.entity.pojo.RecordBlood;
import com.yjjk.monitor.my.mapper.MyMapper;

import java.util.List;

public interface RecordBloodMapper extends MyMapper<RecordBlood> {
    /**
     * 获取导出records
     * @param departmentId
     * @param date
     * @return
     */
    List<HistoryExportBloodVO> getExportList(Integer departmentId, String date,Integer recordId);
    /**
     * 获取某设备的数据
     *
     * @param machineId
     * @return
     */
    List<BloodHistoryData> getHistories(Integer machineId);

    /**
     * 获取血氧数据
     * @param machineId
     * @param recordId
     * @return
     */
    MonitorBloodVO getBloodOxygen(Integer machineId, Integer recordId);

    /**
     * 获取心率数据
     * @param machineId
     * @param recordId
     * @return
     */
    MonitorHeartRateVO getHeartRate(Integer machineId, Integer recordId);


    /**
     * records-体温历史记录
     * @param getRecordsBO
     * @return
     */
    List<RecordsHistory> getHistoryRecords(GetRecordsBO getRecordsBO);

}