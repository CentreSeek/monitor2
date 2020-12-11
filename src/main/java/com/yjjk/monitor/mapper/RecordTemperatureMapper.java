package com.yjjk.monitor.mapper;

import com.yjjk.monitor.entity.BO.history.GetRecordsBO;
import com.yjjk.monitor.entity.VO.StaticsRecordVO;
import com.yjjk.monitor.entity.VO.history.RecordsHistory;
import com.yjjk.monitor.entity.VO.monitor.MonitorTemperatureVO;
import com.yjjk.monitor.entity.export.history.HistoryExportTemperatureVO;
import com.yjjk.monitor.entity.history.TemperatureHistoryData;
import com.yjjk.monitor.my.mapper.MyMapper;
import com.yjjk.monitor.entity.pojo.RecordTemperature;

import java.util.List;

public interface RecordTemperatureMapper extends MyMapper<RecordTemperature> {
    /**
     * 统计数据
     * @param departmentId
     * @param start
     * @param end
     * @param getDeleteDepartment
     * @return
     */
    List<StaticsRecordVO> getRecordsPeriod(Integer departmentId, String start, String end,String getDeleteDepartment);
    /**
     * 获取某设备的数据
     * @param machineId
     * @return
     */
    List<TemperatureHistoryData> getHistories(Integer machineId);

    /**
     * 获取体温数据
     * @param machineId
     * @param recordId
     * @return
     */
    MonitorTemperatureVO getTemperature(Integer machineId, Integer recordId);

    /**
     * records-体温历史记录
     * @param getRecordsBO
     * @return
     */
    List<RecordsHistory> getHistoryRecords(GetRecordsBO getRecordsBO);

    /**
     * 获取体温导出records
     * @param departmentId
     * @param date
     * @param recordId
     * @return
     */
    List<HistoryExportTemperatureVO> getExportList(Integer departmentId,String date,Integer recordId);
}