package com.yjjk.monitor.mapper;

import com.yjjk.monitor.entity.VO.monitor.MonitorTemperatureVO;
import com.yjjk.monitor.entity.history.TemperatureHistoryData;
import com.yjjk.monitor.my.mapper.MyMapper;
import com.yjjk.monitor.entity.pojo.RecordTemperature;

import java.util.List;

public interface RecordTemperatureMapper extends MyMapper<RecordTemperature> {

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
}