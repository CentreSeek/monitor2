package com.yjjk.monitor.mapper;

import com.yjjk.monitor.entity.VO.monitor.MonitorBaseVO;
import com.yjjk.monitor.entity.VO.monitor.MonitorHeartRateVO;
import com.yjjk.monitor.entity.VO.monitor.MonitorRespiratoryRateVO;
import com.yjjk.monitor.entity.history.BloodHistoryData;
import com.yjjk.monitor.entity.history.SleepingHistoryData;
import com.yjjk.monitor.my.mapper.MyMapper;
import com.yjjk.monitor.entity.pojo.RecordSleeping;

import java.util.List;

public interface RecordSleepingMapper extends MyMapper<RecordSleeping> {

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
}