package com.yjjk.monitor.mapper;

import com.yjjk.monitor.my.mapper.MyMapper;
import com.yjjk.monitor.entity.pojo.ZsHealthInfo;

public interface ZsHealthInfoMapper extends MyMapper<ZsHealthInfo> {

    /**
     * 获取某设备最新一条体温数据
     * @param machineId
     * @return
     */
    ZsHealthInfo getByMachineId(Integer machineId);
}