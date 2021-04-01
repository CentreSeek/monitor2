package com.yjjk.monitor.mapper;

import com.yjjk.monitor.entity.VO.history.EcgHistoryVO;
import com.yjjk.monitor.entity.history.EcgHistory;
import com.yjjk.monitor.my.mapper.MyMapper;
import com.yjjk.monitor.entity.pojo.ZsEcgInfo;
import com.yjjk.monitor.my.mapper.MyMapper;

import java.sql.Time;
import java.util.List;
import java.util.Map;

public interface ZsEcgInfoMapper extends MyMapper<ZsEcgInfo> {

    /**
     * 清理心电数据
     * @param timestamp
     * @param machineId
     * @return
     */
    int deleteEcgData(Long timestamp, Integer machineId);

    List<ZsEcgInfo> getEcgs(Integer machineId, Long startTime, Long endTime);
    /**
     * 获取心电贴最新心电数据
     *
     * @param paraMap
     * @return
     */
    List<ZsEcgInfo> getNewEcg(Map<String, Object> paraMap);

    List<EcgHistoryVO> getEcgHistoryAsJson(Long paramLong);

    List<String> getExportHealth(String paramString);

    int deleteByMachineId(Integer paramInteger);
}