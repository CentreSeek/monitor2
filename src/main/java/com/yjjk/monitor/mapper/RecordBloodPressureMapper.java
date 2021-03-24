package com.yjjk.monitor.mapper;

import com.yjjk.monitor.entity.VO.StaticsRecordVO;
import com.yjjk.monitor.entity.VO.monitor.MonitorBloodPressureVO;
import com.yjjk.monitor.entity.VO.monitor.MonitorBloodVO;
import com.yjjk.monitor.entity.history.BloodPressureHistoryData;
import com.yjjk.monitor.entity.history.SleepingHistoryData;
import com.yjjk.monitor.entity.pojo.RecordBloodPressure;
import com.yjjk.monitor.my.mapper.MyMapper;

import java.util.List;

public interface RecordBloodPressureMapper extends MyMapper<RecordBloodPressure> {

    /**
     * 统计数据
     *
     * @param departmentId
     * @param start
     * @param end
     * @param getDeleteDepartment
     * @return
     */
    List<StaticsRecordVO> getRecordsPeriod(Integer departmentId, String start, String end, String getDeleteDepartment);

    /**
     * 获取某设备的数据
     *
     * @param machineId
     * @return
     */
    List<BloodPressureHistoryData> getHistories(Integer machineId);

    /**
     * 获取血氧数据
     *
     * @param machineId
     * @param recordId
     * @return
     */
    MonitorBloodPressureVO getBloodPressure(Integer machineId, Integer recordId);


    /**
     * 获取电量
     *
     * @param recordId
     * @return
     */
    Integer getBattery(Integer recordId);
}