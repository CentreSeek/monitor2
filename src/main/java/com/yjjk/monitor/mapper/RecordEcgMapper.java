package com.yjjk.monitor.mapper;

import com.yjjk.monitor.entity.VO.monitor.MonitorRespiratoryRateVO;
import com.yjjk.monitor.entity.VO.monitor.MonitorHeartRateVO;
import com.yjjk.monitor.entity.history.EcgHistoryData;
import com.yjjk.monitor.my.mapper.MyMapper;
import com.yjjk.monitor.entity.pojo.RecordEcg;

import java.util.List;

public interface RecordEcgMapper extends MyMapper<RecordEcg> {

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
}