package com.yjjk.monitor.mapper;

import com.yjjk.monitor.entity.pojo.ZsSleepingBeltInfo;
import com.yjjk.monitor.entity.pojo.ZsTemperatureInfo;
import com.yjjk.monitor.my.mapper.MyMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ZsTemperatureInfoMapper extends MyMapper<ZsTemperatureInfo> {

    /**
     * delete---定期清理temperatureInfo表数据
     * @param dateOfOneMonthAgo
     * @return
     */
    int temperatureInfoTask(String dateOfOneMonthAgo);

    /**
     * 导出体温数据
     * @param dateOfOneMonthAgo
     * @return
     */
    List<String> getExportTemperatures(String dateOfOneMonthAgo);

    /**
     * 获取某设备最新一条体温数据
     * @param machineId
     * @return
     */
    ZsTemperatureInfo getByMachineId(Integer machineId);
}