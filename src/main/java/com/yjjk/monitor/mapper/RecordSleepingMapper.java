package com.yjjk.monitor.mapper;

import com.yjjk.monitor.entity.BO.history.GetRecordsBO;
import com.yjjk.monitor.entity.VO.history.RecordsHistory;
import com.yjjk.monitor.entity.VO.monitor.MonitorBaseVO;
import com.yjjk.monitor.entity.VO.monitor.MonitorHeartRateVO;
import com.yjjk.monitor.entity.VO.monitor.MonitorRespiratoryRateVO;
import com.yjjk.monitor.entity.export.history.HistoryExportSleepingVO;
import com.yjjk.monitor.entity.export.history.HistoryExportTemperatureVO;
import com.yjjk.monitor.entity.history.BloodHistoryData;
import com.yjjk.monitor.entity.history.SleepingHistoryData;
import com.yjjk.monitor.my.mapper.MyMapper;
import com.yjjk.monitor.entity.pojo.RecordSleeping;

import java.util.List;

public interface RecordSleepingMapper extends MyMapper<RecordSleeping> {


    /**
     * 获取导出records
     * @param departmentId
     * @param date
     * @return
     */
    List<HistoryExportSleepingVO> getExportList(Integer departmentId, String date,Integer recordId);
    /**
     * 获取某设备的数据
     * @param machineId
     * @return
     */
    List<SleepingHistoryData> getHistories(Integer machineId);

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
     * 获取离床数据
     * @param recordId
     * @return
     */
    MonitorBaseVO getSleeping(Integer recordId);

    /**
     * 获取睡眠床带电量
     * @param recordId
     * @return
     */
    Integer getBattery(Integer recordId);


    /**
     * records-体温历史记录
     * @param getRecordsBO
     * @return
     */
    List<RecordsHistory> getHistoryRecords(GetRecordsBO getRecordsBO);
}