package com.yjjk.monitor.mapper;

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

    List<HospitalBed> selectEmptyBeds(Map<String, Object> paramMap);
}