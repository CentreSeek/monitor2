package com.yjjk.monitor.mapper;

import com.yjjk.monitor.my.mapper.MyMapper;
import com.yjjk.monitor.entity.pojo.ZsSleepingBeltInfo;

public interface ZsSleepingBeltInfoMapper extends MyMapper<ZsSleepingBeltInfo> {

    /**
     * 获取某设备最新一条体温数据
     * @param machineId
     * @return
     */
    ZsSleepingBeltInfo getByMachineId(Integer machineId);
}