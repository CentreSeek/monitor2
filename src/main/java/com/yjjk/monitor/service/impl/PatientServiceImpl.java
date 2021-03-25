/**
 * Copyright (C), 2019, 义金(杭州)健康科技有限公司
 * FileName: PatientServiceImpl
 * Author:   CentreS
 * Date:     2019/7/22 10:31
 * Description: 病人管理模块
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.monitor.service.impl;

import com.yjjk.monitor.constant.RecordBaseEnum;
import com.yjjk.monitor.entity.pojo.PatientInfo;
import com.yjjk.monitor.entity.pojo.RecordBase;
import com.yjjk.monitor.service.BaseService;
import com.yjjk.monitor.service.PatientRecordService;
import com.yjjk.monitor.service.PatientService;
import com.yjjk.monitor.utility.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author CentreS
 * @Description: 病人管理模块
 * @create 2019/7/22
 */
@Service
public class PatientServiceImpl extends BaseService implements PatientService {

    @Resource
    PatientRecordService patientRecordService;

    @Override
    public PatientInfo selectByCaseNum(String caseNum) {
        return super.patientInfoMapper.selectByCaseNum(caseNum);
    }

    @Override
    public int updateName(PatientInfo record) {
        return super.patientInfoMapper.updateName(record);
    }

    @Override
    public PatientInfo getByPrimaryKey(Integer patientId) {
        return super.patientInfoMapper.selectByPrimaryKey(patientId);
    }

    @Override
    public Integer checkPatient(String name, String caseNum, Integer levelOfNursing, Integer bedId) {
        PatientInfo zsPatientInfo = super.patientInfoMapper.selectByCaseNum(caseNum);
        if (zsPatientInfo == null) {
            // null：新增病人
            zsPatientInfo = new PatientInfo();
            zsPatientInfo.setName(name).setCaseNum(caseNum).setLevelOfNursing(levelOfNursing);
            super.patientInfoMapper.insertSelective(zsPatientInfo);
            zsPatientInfo = super.patientInfoMapper.selectByCaseNum(caseNum);
        } else {
            Example example = new Example(RecordBase.class);
            Example.Criteria criterion = example.createCriteria();
            criterion.andEqualTo("patientId", zsPatientInfo.getPatientId());
            criterion.andEqualTo("usageStatus", RecordBaseEnum.USAGE_STATE_USE.getType());
            List<RecordBase> recordBases = super.recordBaseMapper.selectByExample(example);
            // 如果病人已在其他床位启用设备 null
            if (!StringUtils.isNullorEmpty(recordBases)) {
                for (int i = 0; i < recordBases.size(); i++) {
                    if (!recordBases.get(i).getBedId().equals(bedId)) {
                        return null;
                    }
                }
            }
            // 更新患者姓名
            if (!zsPatientInfo.getName().equals(name) && !zsPatientInfo.getName().equals(StringUtils.replaceNameX(name))) {
                zsPatientInfo.setName(name);
            }
            zsPatientInfo.setLevelOfNursing(levelOfNursing);
            super.patientInfoMapper.updateByPrimaryKeySelective(zsPatientInfo);
        }
        return zsPatientInfo.getPatientId();
    }

    @Override
    public PatientInfo getPatientInfo(Integer bedId) {
        Example example = new Example(RecordBase.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("bedId", bedId);
        criteria.andEqualTo("usageStatus", RecordBaseEnum.USAGE_STATE_USE.getType());
        RecordBase recordBase = super.recordBaseMapper.selectOneByExample(example);
        if (recordBase == null) {
            return null;
        }
        PatientInfo patientInfo = super.patientInfoMapper.selectByPrimaryKey(recordBase.getPatientId());
        return patientInfo;
    }

}
