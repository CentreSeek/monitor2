package com.yjjk.monitor.mapper;


import com.yjjk.monitor.entity.pojo.PatientInfo;
import com.yjjk.monitor.my.mapper.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface PatientInfoMapper  extends MyMapper<PatientInfo> {

    /**
     * select---使用病历号查询病人
     *
     * @param caseNum
     * @return
     */
    PatientInfo selectByCaseNum(String caseNum);

    /**
     * 更新病人姓名
     *
     * @param record
     * @return
     */
    @Update("update patient_info " +
            "set `name` = #{name,jdbcType=VARCHAR},department_id = #{departmentId,jdbcType=INTEGER}, room_id = #{roomId,jdbcType=INTEGER}, bed_id = #{bedId,jdbcType=INTEGER} " +
            "where patient_id = #{patientId,jdbcType=INTEGER}")
    int updateName(PatientInfo record);

    /**
     * and查询
     *
     * @param patientInfo
     * @return
     */
    List<PatientInfo> selectByTemplate(PatientInfo patientInfo);
}
