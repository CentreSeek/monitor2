package com.yjjk.monitor.mapper;

import com.yjjk.monitor.entity.VO.monitor.MonitorBloodVO;
import com.yjjk.monitor.entity.VO.monitor.MonitorHeartRateVO;
import com.yjjk.monitor.entity.history.BloodHistoryData;
import com.yjjk.monitor.entity.pojo.RecordBlood;
import com.yjjk.monitor.my.mapper.MyMapper;

import java.util.List;

public interface RecordBloodMapper extends MyMapper<RecordBlood> {

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
}