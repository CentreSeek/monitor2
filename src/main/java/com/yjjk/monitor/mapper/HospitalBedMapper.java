package com.yjjk.monitor.mapper;

import com.yjjk.monitor.entity.ListVO;
import com.yjjk.monitor.entity.pojo.HospitalBed;
import com.yjjk.monitor.my.mapper.MyMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface HospitalBedMapper extends MyMapper<HospitalBed> {
//    int deleteByPrimaryKey(Integer bedId);
//
//    int insert(HospitalBedMapper record);
//
//    int insertSelective(HospitalBedMapper record);
//
//    HospitalBedMapper selectByPrimaryKey(Integer bedId);
//
//    int updateByPrimaryKeySelective(HospitalBedMapper record);
//
//    int updateByPrimaryKey(HospitalBedMapper record);

    /**
     * 获取无recordBase床位
     *
     * @param paramMap
     * @return
     */
    List<HospitalBed> selectEmptyBeds(Map<String, Object> paramMap);

    /**
     * 获取未启用相同设备record
     *
     * @param departmentId
     * @param type
     * @return
     */
    List<HospitalBed> selectMonitorEmptyBeds(Integer departmentId, Integer type);

    /**
     * 获取床位数量数据
     *
     * @param departmentId
     * @return
     */
    int getBedCount(Integer departmentId);

    /**
     * 监控页面-筛选床位
     * @param departmentId
     * @param bedId
     * @return
     */
    List<ListVO> getMonitorBedList(Integer departmentId, Integer bedId);
}