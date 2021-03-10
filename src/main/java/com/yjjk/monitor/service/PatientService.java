/**
 * Copyright (C), 2019, 义金(杭州)健康科技有限公司
 * FileName: PatientService
 * Author:   CentreS
 * Date:     2019/7/22 10:30
 * Description: 病人模块
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.monitor.service;

import com.yjjk.monitor.entity.pojo.PatientInfo;

/**
 * @author CentreS
 * @Description: 病人模块
 * @create 2019/7/22
 */
public interface PatientService {


    /**
     * 使用病历号查询病人
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
    int updateName(PatientInfo record);

    /**
     * 使用病人id获取病人资料
     *
     * @param patientId
     * @return
     */
    PatientInfo getByPrimaryKey(Integer patientId);

    /**
     * 查询患者信息并返回id
     *
     * @param name
     * @param caseNum
     * @param levelOfNursing
     * @param bedId
     * @return
     */
    Integer checkPatient(String name, String caseNum,Integer levelOfNursing, Integer bedId);

    /**
     * 获取当前床位病人信息
     *
     * @param bedId
     * @return
     */
    PatientInfo getPatientInfo(Integer bedId);

}
