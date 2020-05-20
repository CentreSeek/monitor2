package com.yjjk.monitor.mapper;
import com.yjjk.monitor.entity.pojo.HospitalDepartment;
import com.yjjk.monitor.my.mapper.MyMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface HospitalDepartmentMapper extends MyMapper<HospitalDepartment> {

    /**
     * 查询医院信息详情
     * @param departmentId
     * @return
     */
    List<HospitalDepartment> selectDetail(Integer departmentId);

    /**
     * 查询科室信息
     * @return
     */
    List<HospitalDepartment> selectDepartments();
}