package com.yjjk.monitor.mapper;

import com.yjjk.monitor.entity.VO.monitor.MonitorBaseVO;
import com.yjjk.monitor.my.mapper.MyMapper;
import com.yjjk.monitor.entity.pojo.RecordBase;

import java.util.List;

public interface RecordBaseMapper extends MyMapper<RecordBase> {

    List<MonitorBaseVO> getAllBaseRecords(Integer departmentId);
}