package com.yjjk.monitor.mapper;

import com.yjjk.monitor.entity.VO.monitor.MonitorBaseVO;
import com.yjjk.monitor.my.mapper.MyMapper;
import com.yjjk.monitor.entity.pojo.RecordBase;

import java.util.List;

public interface RecordBaseMapper extends MyMapper<RecordBase> {

    /**
     * 获取所有
     * @param departmentId
     * @param start
     * @param end
     * @return
     */
    List<MonitorBaseVO> getAllBaseRecords(Integer departmentId,Integer start,Integer end);
}