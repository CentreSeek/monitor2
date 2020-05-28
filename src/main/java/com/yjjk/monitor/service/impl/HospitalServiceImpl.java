/**
 * Copyright (C), 2019, 义金(杭州)健康科技有限公司
 * FileName: HospitalServiceImpl
 * Author:   CentreS
 * Date:     2019/7/18 17:19
 * Description: 医院信息
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.monitor.service.impl;

import com.yjjk.monitor.constant.ExportConstant;
import com.yjjk.monitor.entity.pojo.HospitalBed;
import com.yjjk.monitor.entity.pojo.HospitalDepartment;
import com.yjjk.monitor.entity.pojo.HospitalRoom;
import com.yjjk.monitor.service.BaseService;
import com.yjjk.monitor.service.HospitalService;
import com.yjjk.monitor.utility.ExcelUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author CentreS
 * @Description: 医院信息
 * @create 2019/7/18
 */
@Service
public class HospitalServiceImpl extends BaseService implements HospitalService {


    @Override
    public List<HospitalDepartment> selectDetail(Integer departmentId) {
        return super.HospitalDepartmentMapper.selectDetail(departmentId);
    }

    @Override
    public int temperatureInfoTask(String dateOfOneMonthAgo) {
        temperatureInfoPersistent(dateOfOneMonthAgo);
        return super.zsTemperatureInfoMapper.temperatureInfoTask(dateOfOneMonthAgo);
    }

    @Override
    public int temperatureInfoPersistent(String dateOfOneMonthAgo) {
        List<String> exportTemperatures = super.zsTemperatureInfoMapper.getExportTemperatures(dateOfOneMonthAgo);
        ExcelUtils.writeToTxt(exportTemperatures, ExportConstant.TEMPERATURE_EXPORT_PATH);
        return exportTemperatures.size();
    }

    @Override
    public List<HospitalDepartment> selectDepartments() {
        return super.HospitalDepartmentMapper.selectDepartments();
    }

    @Override
    public List<HospitalRoom> selectRooms(Integer departmentId) {
        return super.HospitalRoomMapper.selectRooms(departmentId);
    }

    @Override
    public List<HospitalBed> selectEmptyBeds(Map<String, Object> paraMap) {
        return this.HospitalBedMapper.selectEmptyBeds(paraMap);
    }

    @Override
    public List<HospitalBed> selectMonitorEmptyBeds(Integer departmentId, Integer type) {
        return super.HospitalBedMapper.selectMonitorEmptyBeds(departmentId,type);
    }

    @Override
    public int getBedCount(Integer departmentId) {
        return super.HospitalBedMapper.getBedCount(departmentId);
    }
}
