/**
 * Copyright (C), 2019, 义金(杭州)健康科技有限公司
 * FileName: BaseService
 * Author:   CentreS
 * Date:     2019-06-20 16:34
 * Description: base service
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.monitor.service;


import com.github.pagehelper.PageInfo;
import com.yjjk.monitor.entity.VO.PagedGridResult;
import com.yjjk.monitor.constant.config.ConnectRepeater;
import com.yjjk.monitor.constant.config.MachineConfig;
import com.yjjk.monitor.mapper.HospitalBedMapper;
import com.yjjk.monitor.mapper.HospitalDepartmentMapper;
import com.yjjk.monitor.mapper.HospitalRoomMapper;
import com.yjjk.monitor.mapper.LoginStateMapper;
import com.yjjk.monitor.mapper.MachineTypeInfoMapper;
import com.yjjk.monitor.mapper.ManagerInfoMapper;
import com.yjjk.monitor.mapper.MonitorRuleMapper;
import com.yjjk.monitor.mapper.PatientInfoMapper;
import com.yjjk.monitor.mapper.RecordBaseMapper;
import com.yjjk.monitor.mapper.RecordBloodMapper;
import com.yjjk.monitor.mapper.RecordEcgMapper;
import com.yjjk.monitor.mapper.RecordSleepingMapper;
import com.yjjk.monitor.mapper.RecordTemperatureMapper;
import com.yjjk.monitor.mapper.ZsBloodOxygenInfoMapper;
import com.yjjk.monitor.mapper.ZsHealthInfoMapper;
import com.yjjk.monitor.mapper.ZsMachineInfoMapper;
import com.yjjk.monitor.mapper.ZsPatientRecordMapper;
import com.yjjk.monitor.mapper.ZsRepeaterInfoMapper;
import com.yjjk.monitor.mapper.ZsSleepingBeltInfoMapper;
import com.yjjk.monitor.mapper.ZsTemperatureInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author CentreS
 * @Description: base service
 * @create 2019-06-20
 */
public class BaseService {
    protected static Logger logger = LoggerFactory.getLogger(BaseService.class);
    @Autowired
    protected HospitalBedMapper hospitalBedMapper;
    @Autowired
    protected HospitalDepartmentMapper hospitalDepartmentMapper;
    @Autowired
    protected ZsMachineInfoMapper zsMachineInfoMapper;
    @Autowired
    protected ManagerInfoMapper managerInfoMapper;
    @Autowired
    protected PatientInfoMapper patientInfoMapper;
    @Autowired
    protected ZsPatientRecordMapper zsPatientRecordMapper;
    @Autowired
    protected HospitalRoomMapper hospitalRoomMapper;
    @Autowired
    protected ZsTemperatureInfoMapper zsTemperatureInfoMapper;
    @Autowired
    protected ZsBloodOxygenInfoMapper zsBloodOxygenInfoMapper;
    @Autowired
    protected ZsHealthInfoMapper zsHealthInfoMapper;
    @Autowired
    protected ZsSleepingBeltInfoMapper zsSleepingBeltInfoMapper;
    @Autowired
    protected LoginStateMapper loginStateMapper;
    @Autowired
    protected MachineTypeInfoMapper machineTypeInfoMapper;
    @Autowired
    protected ZsRepeaterInfoMapper zsRepeaterInfoMapper;
    @Autowired
    protected RecordBaseMapper recordBaseMapper;
    @Autowired
    protected RecordBloodMapper recordBloodMapper;
    @Autowired
    protected RecordEcgMapper recordEcgMapper;
    @Autowired
    protected RecordSleepingMapper recordSleepingMapper;
    @Autowired
    protected RecordTemperatureMapper recordTemperatureMapper;
    @Autowired
    protected MonitorRuleMapper monitorRuleMapper;

    @Autowired
    protected MachineConfig machineConfig;
    @Autowired
    protected ConnectRepeater connectRepeater;

    protected PagedGridResult setterPagedGrid(List<?> list, Integer page) {
        return setterPagedGrid(list, page, null);
    }
    protected PagedGridResult setterPagedGrid(List<?> list, Integer page, Integer count) {
        PageInfo<?> pageList = new PageInfo<>(list);
        PagedGridResult grid = new PagedGridResult();
        grid.setPage(page);
        grid.setRows(list);
        grid.setTotal(pageList.getPages());
        if (count != null) {
            grid.setRecords(count);
        } else {
            grid.setRecords(pageList.getTotal());
        }
        return grid;
    }
}
