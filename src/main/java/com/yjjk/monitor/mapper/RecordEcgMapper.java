package com.yjjk.monitor.mapper;

import com.yjjk.monitor.entity.BO.history.GetRecordsBO;
import com.yjjk.monitor.entity.VO.history.RecordsHistory;
import com.yjjk.monitor.entity.VO.monitor.MonitorRespiratoryRateVO;
import com.yjjk.monitor.entity.VO.monitor.MonitorHeartRateVO;
import com.yjjk.monitor.entity.export.history.HistoryExportEcgVO;
import com.yjjk.monitor.entity.export.history.HistoryExportSleepingVO;
import com.yjjk.monitor.entity.history.EcgHistoryData;
import com.yjjk.monitor.my.mapper.MyMapper;
import com.yjjk.monitor.entity.pojo.RecordEcg;

import java.util.List;

public interface RecordEcgMapper extends MyMapper<RecordEcg> {

    /**
     * 获取导出records
     * @param departmentId
     * @param date
     * @return
     */
    List<HistoryExportEcgVO> getExportList(Integer departmentId, String date,Integer recordId);
    /**
     * 获取某设备的数据
     * @param machineId
     * @return
     */
    List<EcgHistoryData> getHistories(Integer machineId);

    /**
     * 获取心率数据
     * @param machineId
     * @param recordId
     * @return
     */
    MonitorHeartRateVO getHeartRate(Integer machineId, Integer recordId);

    /**
     * 获取呼吸率数据
     * @param machineId
     * @param recordId
     * @return
     */
    MonitorRespiratoryRateVO getRespiratoryRate(Integer machineId, Integer recordId);


    /**
     * records-体温历史记录
     * @param getRecordsBO
     * @return
     */
    List<RecordsHistory> getHistoryRecords(GetRecordsBO getRecordsBO);
}