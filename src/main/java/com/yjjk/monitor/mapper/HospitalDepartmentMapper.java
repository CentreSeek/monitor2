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

    /**
     * 获取医院名称
     * @return
     */
    String getHospitalName();

    HospitalDepartment getByPrimaryKey(Integer departmentId);

    /**
     * 科室名称是否可用
     * @param name
     * @return
     */
    Integer checkDepartmentName(String name);
}