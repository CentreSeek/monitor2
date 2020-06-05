package com.yjjk.monitor.mapper;

import com.yjjk.monitor.my.mapper.MyMapper;
import com.yjjk.monitor.entity.pojo.ZsBloodOxygenInfo;

public interface ZsBloodOxygenInfoMapper extends MyMapper<ZsBloodOxygenInfo> {


    /**
     * 获取某设备最新一条体温数据
     * @param machineId
     * @return
     */
    ZsBloodOxygenInfo getByMachineId(Integer machineId);
}