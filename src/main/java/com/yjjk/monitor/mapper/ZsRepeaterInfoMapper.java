package com.yjjk.monitor.mapper;

import com.yjjk.monitor.entity.pojo.ZsRepeaterInfo;
import com.yjjk.monitor.my.mapper.MyMapper;

import java.util.List;

public interface ZsRepeaterInfoMapper extends MyMapper<ZsRepeaterInfo> {

    /**
     * 分页查询路由信息
     *
     * @param record
     * @return
     */
    List<ZsRepeaterInfo> selectRepeaters(ZsRepeaterInfo record);

    /**
     * 查询路由总数
     *
     * @param record
     * @return
     */
    Integer selectRepeaterCount(ZsRepeaterInfo record);

    /**
     * @param bedId
     * @return int
     * @Description 查询repeaterId
     */
    Integer selectByBedId(Integer bedId);

    /**
     * 查询该床位连接的中继器数量
     *
     * @param bedId
     * @return
     */
    Integer hasRepeaterCount(Integer bedId);

    /**
     * 获取当前房间绑定的路由总数
     *
     * @param zsRepeaterInfo
     * @return int
     */
    Integer isExistRepeater(ZsRepeaterInfo zsRepeaterInfo);

    ZsRepeaterInfo selectByMac(String mac);

    ZsRepeaterInfo selectByIP(String ip);
}