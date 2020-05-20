package com.yjjk.monitor.mapper;

import com.yjjk.monitor.entity.pojo.HospitalRoom;
import com.yjjk.monitor.my.mapper.MyMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface HospitalRoomMapper extends MyMapper<HospitalRoom> {

    /**
     * 查询房间信息
     * @return
     */
    List<HospitalRoom> selectRooms(Integer departmentId);

}