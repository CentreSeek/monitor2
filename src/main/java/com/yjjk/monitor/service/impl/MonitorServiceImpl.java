/**
 * Copyright (C), 2019, 义金(杭州)健康科技有限公司
 * FileName: PatientRecordServiceImpl
 * Author:   CentreS
 * Date:     2019/7/22 11:41
 * Description: 历史记录
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.monitor.service.impl;

import com.alibaba.fastjson.JSON;
import com.yjjk.monitor.configer.CommonResult;
import com.yjjk.monitor.configer.ErrorCodeEnum;
import com.yjjk.monitor.constant.BatteryConstant;
import com.yjjk.monitor.constant.MachineConstant;
import com.yjjk.monitor.constant.MachineEnum;
import com.yjjk.monitor.constant.MonitorEnum;
import com.yjjk.monitor.constant.MonitorRuleEnum;
import com.yjjk.monitor.constant.RecordBaseEnum;
import com.yjjk.monitor.entity.VO.monitor.MachinesInfoVO;
import com.yjjk.monitor.entity.VO.monitor.MonitorBaseVO;
import com.yjjk.monitor.entity.VO.monitor.MonitorBloodVO;
import com.yjjk.monitor.entity.VO.monitor.MonitorHeartRateVO;
import com.yjjk.monitor.entity.VO.monitor.MonitorMachineListVO;
import com.yjjk.monitor.entity.VO.monitor.MonitorRespiratoryRateVO;
import com.yjjk.monitor.entity.VO.monitor.MonitorTemperatureVO;
import com.yjjk.monitor.entity.VO.monitor.MonitorVO;
import com.yjjk.monitor.entity.history.BloodHistory;
import com.yjjk.monitor.entity.history.BloodHistoryData;
import com.yjjk.monitor.entity.history.EcgHistory;
import com.yjjk.monitor.entity.history.EcgHistoryData;
import com.yjjk.monitor.entity.history.SleepingHistory;
import com.yjjk.monitor.entity.history.SleepingHistoryData;
import com.yjjk.monitor.entity.history.TemperatureHistory;
import com.yjjk.monitor.entity.history.TemperatureHistoryData;
import com.yjjk.monitor.entity.log.ManageLog;
import com.yjjk.monitor.entity.pojo.ManagerInfo;
import com.yjjk.monitor.entity.pojo.MonitorRule;
import com.yjjk.monitor.entity.pojo.RecordBase;
import com.yjjk.monitor.entity.pojo.RecordBlood;
import com.yjjk.monitor.entity.pojo.RecordEcg;
import com.yjjk.monitor.entity.pojo.RecordSleeping;
import com.yjjk.monitor.entity.pojo.RecordTemperature;
import com.yjjk.monitor.entity.pojo.ZsBloodOxygenInfo;
import com.yjjk.monitor.entity.pojo.ZsHealthInfo;
import com.yjjk.monitor.entity.pojo.ZsMachineInfo;
import com.yjjk.monitor.entity.pojo.ZsSleepingBeltInfo;
import com.yjjk.monitor.entity.pojo.ZsTemperatureInfo;
import com.yjjk.monitor.entity.transaction.BackgroundResult;
import com.yjjk.monitor.entity.transaction.BackgroundSend;
import com.yjjk.monitor.service.BaseService;
import com.yjjk.monitor.service.EcgService;
import com.yjjk.monitor.service.MachineService;
import com.yjjk.monitor.service.MonitorService;
import com.yjjk.monitor.utility.DateUtil;
import com.yjjk.monitor.utility.MonitorUtils;
import com.yjjk.monitor.utility.ResultUtil;
import com.yjjk.monitor.utility.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author CentreS
 * @Description: 监控模块
 * @create 2019/7/22
 */
@Service
public class MonitorServiceImpl extends BaseService implements MonitorService {

    @Autowired
    MachineService machineService;

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public MonitorVO setMachineState(MonitorVO monitorVO) {
        List<MonitorBaseVO> monitorVOList = monitorVO.getMonitorVOList();
        List<MachinesInfoVO> machinesInfoVOList = new ArrayList<>();
        for (int i = 0; i < monitorVOList.size(); i++) {
            if (monitorVOList.get(i).getRecordTemperatureId() != -1) {
                if (monitorVOList.get(i).getMonitorTemperatureVO().getBattery() <= 10) {
                    MachinesInfoVO temp = new MachinesInfoVO();
                    temp.setMachineType(MachineEnum.TEMPERATURE.getType())
                            .setMachineTypeName(MachineEnum.TEMPERATURE.getValue())
                            .setBedName(monitorVOList.get(i).getBedName())
                            .setText(BatteryConstant.ALERT_TEMPERATURE);
                    machinesInfoVOList.add(temp);
                }
                if (monitorVOList.get(i).getMonitorTemperatureVO().getBoxBatteryStatus() == BatteryConstant.BOX_STATUS_LOW) {
                    MachinesInfoVO temp = new MachinesInfoVO();
                    temp.setMachineType(MachineEnum.TEMPERATURE.getType())
                            .setMachineTypeName(MachineEnum.TEMPERATURE.getValue())
                            .setBedName(monitorVOList.get(i).getBedName())
                            .setText(BatteryConstant.ALERT_TEMPERATURE_BOX);
                    machinesInfoVOList.add(temp);
                }
            }
            if (monitorVOList.get(i).getRecordEcgId() != -1) {
                if (monitorVOList.get(i).getMonitorHeartRateVO().getBattery() <= 10) {
                    MachinesInfoVO temp = new MachinesInfoVO();
                    temp.setMachineType(MachineEnum.ECG.getType())
                            .setMachineTypeName(MachineEnum.ECG.getValue())
                            .setBedName(monitorVOList.get(i).getBedName())
                            .setText(BatteryConstant.ALERT_ECG);
                    machinesInfoVOList.add(temp);
                }
            }
            if (monitorVOList.get(i).getRecordBloodId() != -1) {
                if (monitorVOList.get(i).getMonitorBloodVO().getBattery() <= 20) {
                    MachinesInfoVO temp = new MachinesInfoVO();
                    temp.setMachineType(MachineEnum.BLOOD.getType())
                            .setMachineTypeName(MachineEnum.BLOOD.getValue())
                            .setBedName(monitorVOList.get(i).getBedName())
                            .setText(BatteryConstant.ALERT_BLOOD);
                    machinesInfoVOList.add(temp);
                }
            }
            if (monitorVOList.get(i).getRecordSleepingId() != -1) {
                Integer battery = super.recordSleepingMapper.getBattery(monitorVOList.get(i).getBaseId());
                if (battery <= 10) {
                    MachinesInfoVO temp = new MachinesInfoVO();
                    temp.setMachineType(MachineEnum.SLEEPING.getType())
                            .setMachineTypeName(MachineEnum.SLEEPING.getValue())
                            .setBedName(monitorVOList.get(i).getBedName())
                            .setText(BatteryConstant.ALERT_SLEEPING);
                    machinesInfoVOList.add(temp);
                }
            }

        }
        return monitorVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public MonitorVO setMonitorRule(MonitorVO monitorVO, Integer departmentId) {
        List<MonitorRule> rule = super.monitorRuleMapper.getRule(departmentId, null, MonitorRuleEnum.OPEN.getType());
        List<MonitorBaseVO> monitorVOList = monitorVO.getMonitorVOList();
        MonitorRule tRule = null;
        MonitorRule hRule = null;
        MonitorRule rRule = null;
        MonitorRule bRule = null;
        for (int i = 0; i < rule.size(); i++) {
            if (rule.get(i).getType().equals(MonitorEnum.TEMPERATURE.getType())) {
                tRule = rule.get(i);
                break;
            }
            if (rule.get(i).getType().equals(MonitorEnum.HEART_RATE.getType())) {
                hRule = rule.get(i);
                break;
            }
            if (rule.get(i).getType().equals(MonitorEnum.RESPIRATORY_RATE.getType())) {
                rRule = rule.get(i);
                break;
            }
            if (rule.get(i).getType().equals(MonitorEnum.BLOOD_OXYGEN.getType())) {
                bRule = rule.get(i);
                break;
            }
        }

        for (int i = 0; i < monitorVOList.size(); i++) {
            // 启用并且有数据
            if (monitorVOList.get(i).getMonitorTemperatureVO() != null && monitorVOList.get(i).getMonitorTemperatureVO().getTemperature() != null) {
                Double temperature = Double.parseDouble(monitorVOList.get(i).getMonitorTemperatureVO().getTemperature());
                if (temperature < tRule.getParamTwo() || (temperature > tRule.getParamOne() && temperature < tRule.getParamThree())) {
                    monitorVOList.get(i).getMonitorTemperatureVO().setTemperatureAlert(MonitorRuleEnum.ALERT_ORANGE.getType());
                } else if (temperature > tRule.getParamThree()) {
                    monitorVOList.get(i).getMonitorTemperatureVO().setTemperatureAlert(MonitorRuleEnum.ALERT_RED.getType());
                } else {
                    monitorVOList.get(i).getMonitorTemperatureVO().setTemperatureAlert(MonitorRuleEnum.ALERT_WHITE.getType());
                }
            }
            if (monitorVOList.get(i).getMonitorHeartRateVO() != null && monitorVOList.get(i).getMonitorHeartRateVO().getHeart() != null) {
                Double heart = Double.parseDouble(monitorVOList.get(i).getMonitorHeartRateVO().getHeart());
                if (heart < hRule.getParamTwo() || (heart > hRule.getParamOne() && heart < hRule.getParamThree())) {
                    monitorVOList.get(i).getMonitorHeartRateVO().setHeartAlert(MonitorRuleEnum.ALERT_ORANGE.getType());
                } else if (heart > hRule.getParamThree()) {
                    monitorVOList.get(i).getMonitorHeartRateVO().setHeartAlert(MonitorRuleEnum.ALERT_RED.getType());
                } else {
                    monitorVOList.get(i).getMonitorHeartRateVO().setHeartAlert(MonitorRuleEnum.ALERT_WHITE.getType());
                }
            }
            if (monitorVOList.get(i).getMonitorRespiratoryRateVO() != null && monitorVOList.get(i).getMonitorRespiratoryRateVO().getRespiratory() != null) {
                Double respiratoryRate = Double.parseDouble(monitorVOList.get(i).getMonitorRespiratoryRateVO().getRespiratory());
                if (respiratoryRate < rRule.getParamTwo() || (respiratoryRate > rRule.getParamOne() && respiratoryRate < rRule.getParamThree())) {
                    monitorVOList.get(i).getMonitorRespiratoryRateVO().setRespiratoryAlert(MonitorRuleEnum.ALERT_ORANGE.getType());
                } else if (respiratoryRate > rRule.getParamThree()) {
                    monitorVOList.get(i).getMonitorRespiratoryRateVO().setRespiratoryAlert(MonitorRuleEnum.ALERT_RED.getType());
                } else {
                    monitorVOList.get(i).getMonitorRespiratoryRateVO().setRespiratoryAlert(MonitorRuleEnum.ALERT_WHITE.getType());
                }
            }
            if (monitorVOList.get(i).getMonitorBloodVO() != null && monitorVOList.get(i).getMonitorBloodVO().getBloodOxygen() != null) {
                Double temperature = Double.parseDouble(monitorVOList.get(i).getMonitorBloodVO().getBloodOxygen());
                if (temperature < bRule.getParamTwo() || (temperature > bRule.getParamOne() && temperature < bRule.getParamThree())) {
                    monitorVOList.get(i).getMonitorBloodVO().setBloodOxygenAlert(MonitorRuleEnum.ALERT_ORANGE.getType());
                } else if (temperature > bRule.getParamThree()) {
                    monitorVOList.get(i).getMonitorBloodVO().setBloodOxygenAlert(MonitorRuleEnum.ALERT_RED.getType());
                } else {
                    monitorVOList.get(i).getMonitorBloodVO().setBloodOxygenAlert(MonitorRuleEnum.ALERT_WHITE.getType());
                }
            }
        }
        monitorVO.setMonitorVOList(monitorVOList);
        return monitorVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public List<MonitorBaseVO> getMonitors(Integer departmentId) {
        List<MonitorBaseVO> allBaseRecords = super.recordBaseMapper.getAllBaseRecords(departmentId);
        for (int i = 0; i < allBaseRecords.size(); i++) {
            Integer baseId = allBaseRecords.get(i).getBaseId();
            RecordBase recordBase = super.recordBaseMapper.selectByPrimaryKey(baseId);
            MonitorBaseVO temp = allBaseRecords.get(i);
            temp.setRecordTemperatureId(recordBase.getRecordTemperatureId())
                    .setRecordEcgId(recordBase.getRecordEcgId())
                    .setRecordBloodId(recordBase.getRecordBloodId())
                    .setRecordSleepingId(recordBase.getRecordSleepingId())
                    .setMachineList(new ArrayList<>());
            temp = getTemperature(temp, recordBase.getRecordTemperatureId());
            temp = getHeartRate(temp, recordBase.getRecordEcgId(), recordBase.getRecordBloodId(), recordBase.getRecordSleepingId());
            temp = getRespiratory(temp, recordBase.getRecordEcgId(), recordBase.getRecordSleepingId());
            temp = getBloodOxygen(temp, recordBase.getRecordBloodId());
            temp.setMachineList(getMachinesInfo(recordBase));
            allBaseRecords.set(i, temp);
        }
        // 获取睡眠数据
        allBaseRecords = getSleeping(allBaseRecords);
        //

        return allBaseRecords;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public MonitorBaseVO getTemperature(MonitorBaseVO monitorBaseVO, Integer recordId) {
        MonitorTemperatureVO data = new MonitorTemperatureVO();
        data.setRecordState(RecordBaseEnum.USAGE_STATE_UN_USE.getType());
        if (recordId != null && recordId != -1) {
            RecordTemperature recordTemperature = super.recordTemperatureMapper.selectByPrimaryKey(recordId);
            data = super.recordTemperatureMapper.getTemperature(recordTemperature.getMachineId(), recordId);
        }
        monitorBaseVO.setMonitorTemperatureVO(data);
        return monitorBaseVO;
    }

    @Override
    public MonitorBaseVO getHeartRate(MonitorBaseVO monitorBaseVO, Integer ecgRecordId, Integer bloodRecordId, Integer sleepingRecordId) {
        MonitorHeartRateVO data = new MonitorHeartRateVO();
        data.setRecordState(RecordBaseEnum.USAGE_STATE_UN_USE.getType());
        if (ecgRecordId != null && ecgRecordId != -1) {
            RecordEcg recordEcg = super.recordEcgMapper.selectByPrimaryKey(ecgRecordId);
            data = super.recordEcgMapper.getHeartRate(recordEcg.getMachineId(), ecgRecordId);
        }
        if (bloodRecordId != null && bloodRecordId != -1) {
            RecordBlood recordEcg = super.recordBloodMapper.selectByPrimaryKey(bloodRecordId);
            data = super.recordBloodMapper.getHeartRate(recordEcg.getMachineId(), ecgRecordId);
        }
        if (sleepingRecordId != null && sleepingRecordId != -1) {
            RecordSleeping recordSleeping = super.recordSleepingMapper.selectByPrimaryKey(sleepingRecordId);
            data = super.recordSleepingMapper.getHeartRate(recordSleeping.getMachineId(), ecgRecordId);
        }
        monitorBaseVO.setMonitorHeartRateVO(data);
        return monitorBaseVO;

    }

    @Override
    public MonitorBaseVO getRespiratory(MonitorBaseVO monitorBaseVO, Integer ecgRecordId, Integer sleepingRecordId) {
        MonitorRespiratoryRateVO data = new MonitorRespiratoryRateVO();
        data.setRecordState(RecordBaseEnum.USAGE_STATE_UN_USE.getType());
        if (ecgRecordId != null && ecgRecordId != -1) {
            RecordEcg recordEcg = super.recordEcgMapper.selectByPrimaryKey(ecgRecordId);
            data = super.recordEcgMapper.getRespiratoryRate(recordEcg.getMachineId(), ecgRecordId);
        }
        if (sleepingRecordId != null && sleepingRecordId != -1) {
            RecordSleeping recordSleeping = super.recordSleepingMapper.selectByPrimaryKey(sleepingRecordId);
            data = super.recordSleepingMapper.getRespiratoryRate(recordSleeping.getMachineId(), ecgRecordId);
        }
        monitorBaseVO.setMonitorRespiratoryRateVO(data);
        return monitorBaseVO;
    }

    @Override
    public MonitorBaseVO getBloodOxygen(MonitorBaseVO monitorBaseVO, Integer recordId) {
        MonitorBloodVO data = new MonitorBloodVO();
        data.setRecordState(RecordBaseEnum.USAGE_STATE_UN_USE.getType());
        if (recordId != null && recordId != -1) {
            RecordBlood recordBlood = super.recordBloodMapper.selectByPrimaryKey(recordId);
            data = super.recordBloodMapper.getBloodOxygen(recordBlood.getMachineId(), recordId);
        }
        monitorBaseVO.setMonitorBloodVO(data);
        return monitorBaseVO;
    }

    @Override
    public List<MonitorBaseVO> getSleeping(List<MonitorBaseVO> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getRecordSleepingId() != -1) {
                // 启用离床感应
                MonitorBaseVO sleeping = super.recordSleepingMapper.getSleeping(list.get(i).getRecordSleepingId());
                list.get(i).setSleepingUsage(0)
                        .setSleepingUsage(MonitorUtils.getSleepingState(sleeping.getSleepingState()))
                        .setSleepingLeaveTimes(sleeping.getSleepingLeaveTimes());
            } else {
                list.get(i).setSleepingUsage(1);
            }

        }
        return list;
    }

    @Override
    public List<MonitorMachineListVO> getMachinesInfo(RecordBase recordBase) {
        List<MonitorMachineListVO> list = new ArrayList<>();
        // temperature
        MonitorMachineListVO pojo1 = new MonitorMachineListVO();
        if (recordBase.getRecordTemperatureId() != -1) {
            RecordTemperature recordTemperature = super.recordTemperatureMapper.selectByPrimaryKey(recordBase.getRecordTemperatureId());
            ZsMachineInfo byMachineId = super.ZsMachineInfoMapper.getByMachineId(recordTemperature.getMachineId());
            pojo1.setType(MachineEnum.TEMPERATURE.getType())
                    .setMachineId(recordBase.getRecordTemperatureId())
                    .setMachineNo(byMachineId.getMachineNo())
                    .setMachineSn(byMachineId.getMachineNum());
        } else {
            pojo1.setType(MachineEnum.TEMPERATURE.getType())
                    .setMachineId(recordBase.getRecordTemperatureId());
        }
        list.add(pojo1);
        // ecg
        MonitorMachineListVO pojo2 = new MonitorMachineListVO();
        if (recordBase.getRecordEcgId() != -1) {
            RecordEcg recordEcg = super.recordEcgMapper.selectByPrimaryKey(recordBase.getRecordEcgId());
            ZsMachineInfo byMachineId = super.ZsMachineInfoMapper.getByMachineId(recordEcg.getMachineId());
            pojo2.setType(MachineEnum.ECG.getType())
                    .setMachineId(recordBase.getRecordEcgId())
                    .setMachineNo(byMachineId.getMachineNo())
                    .setMachineSn(byMachineId.getMachineNum());
        } else {
            pojo2.setType(MachineEnum.ECG.getType())
                    .setMachineId(recordBase.getRecordEcgId());
        }
        list.add(pojo2);
        // blood
        MonitorMachineListVO pojo3 = new MonitorMachineListVO();
        if (recordBase.getRecordBloodId() != -1) {
            RecordBlood recordBlood = super.recordBloodMapper.selectByPrimaryKey(recordBase.getRecordBloodId());
            ZsMachineInfo byMachineId = super.ZsMachineInfoMapper.getByMachineId(recordBlood.getMachineId());
            pojo3.setType(MachineEnum.BLOOD.getType())
                    .setMachineId(recordBase.getRecordBloodId())
                    .setMachineNo(byMachineId.getMachineNo())
                    .setMachineSn(byMachineId.getMachineNum());
        } else {
            pojo3.setType(MachineEnum.BLOOD.getType())
                    .setMachineId(recordBase.getRecordBloodId());
        }
        list.add(pojo3);
        // sleeping
        MonitorMachineListVO pojo4 = new MonitorMachineListVO();
        if (recordBase.getRecordSleepingId() != -1) {
            RecordSleeping recordSleeping = super.recordSleepingMapper.selectByPrimaryKey(recordBase.getRecordSleepingId());
            ZsMachineInfo byMachineId = super.ZsMachineInfoMapper.getByMachineId(recordSleeping.getMachineId());
            pojo4.setType(MachineEnum.SLEEPING.getType())
                    .setMachineId(recordBase.getRecordSleepingId())
                    .setMachineNo(byMachineId.getMachineNo())
                    .setMachineSn(byMachineId.getMachineNum());
        } else {
            pojo4.setType(MachineEnum.SLEEPING.getType())
                    .setMachineId(recordBase.getRecordSleepingId());
        }
        list.add(pojo4);
        return list;
    }

    @Override
    public CommonResult stopMachine(Integer baseId, Integer type, String token) throws Exception {
        RecordBase recordBase = super.recordBaseMapper.selectByPrimaryKey(baseId);
        // 管理日志
        ManagerInfo managerInfo = super.loginStateMapper.selectByToken(token);
        ManageLog log = new ManageLog(managerInfo.getId(), "stopMachine-" + MachineEnum.getName(type), DateUtil.getCurrentTime());
        cacheManageLog(recordBase.getId(), log);
        // 更新RecordBase信息
        if (recordBase.getRecordTemperatureId() == -1
                && recordBase.getRecordEcgId() == -1
                && recordBase.getRecordSleepingId() == -1
                && recordBase.getRecordBloodId() == -1) {
            RecordBase param = new RecordBase();
            param.setId(baseId).setUsageStatus(RecordBaseEnum.USAGE_STATE_UN_USE.getType());
            super.recordBaseMapper.updateByPrimaryKeySelective(param);
        }

        switch (type) {
            case MachineConstant.TEMPERATURE:
                return stopTemperatureMachine(recordBase.getRecordTemperatureId());
            case MachineConstant.ECG:
                return stopEcgMachine(recordBase.getRecordEcgId(), recordBase.getBedId());
            case MachineConstant.BLOOD:
                return stopBloodMachine(recordBase.getRecordBloodId());
            case MachineConstant.SLEEPING:
                return stopSleepingMachine(recordBase.getRecordSleepingId());
            default:
                return ResultUtil.returnError(ErrorCodeEnum.ERROR_MACHINE_TYPE);
        }
    }

    @Override
    public CommonResult stopTemperatureMachine(Integer recordId) throws Exception {
        if (recordId == -1) {
            ResultUtil.returnError(ErrorCodeEnum.ERROR_MACHINE_STOP);
        }
        RecordTemperature recordTemperature = super.recordTemperatureMapper.selectByPrimaryKey(recordId);
        cacheMonitorHistory(MachineEnum.TEMPERATURE.getType(), recordId);
        recordTemperature.setRecordStatus(RecordBaseEnum.USAGE_STATE_UN_USE.getType()).setEndTime(DateUtil.getCurrentTime()).setUpdatedTime(DateUtil.getCurrentTime());
        super.recordTemperatureMapper.updateByPrimaryKeySelective(recordTemperature);
        deletePastData(MachineEnum.TEMPERATURE.getType(), recordTemperature.getMachineId());
        return machineService.startMachine(recordTemperature.getMachineId(), BackgroundSend.DATA_LOSE_CONNECTION);
    }

    @Override
    public CommonResult stopEcgMachine(Integer recordId, Integer bedId) throws Exception {
        if (recordId == -1) {
            ResultUtil.returnError(ErrorCodeEnum.ERROR_MACHINE_STOP);
        }
        // 连接心电设备
        RecordEcg recordEcg = super.recordEcgMapper.selectByPrimaryKey(recordId);
        BackgroundResult backgroundResult = null;
        try {
            ecgService.connectEcgMachine(recordEcg.getMachineId(), bedId, BackgroundSend.DATA_LOSE_CONNECTION);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.returnError("无法连接中继器服务器");
        }
        if (backgroundResult == null || !"200".equals(backgroundResult.getCode())) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultUtil.returnError(ErrorCodeEnum.ERROR_CONNECT_ECG);
        }
        cacheMonitorHistory(MachineEnum.ECG.getType(), recordId);
        recordEcg.setRecordStatus(RecordBaseEnum.USAGE_STATE_UN_USE.getType()).setEndTime(DateUtil.getCurrentTime()).setUpdatedTime(DateUtil.getCurrentTime());
        super.recordEcgMapper.updateByPrimaryKeySelective(recordEcg);
        deletePastData(MachineEnum.ECG.getType(), recordEcg.getMachineId());
        return machineService.startMachine(recordEcg.getMachineId(), BackgroundSend.DATA_LOSE_CONNECTION);
    }

    @Override
    public CommonResult stopBloodMachine(Integer recordId) throws Exception {
        if (recordId == -1) {
            ResultUtil.returnError(ErrorCodeEnum.ERROR_MACHINE_STOP);
        }
        RecordBlood recordBlood = super.recordBloodMapper.selectByPrimaryKey(recordId);
        cacheMonitorHistory(MachineEnum.BLOOD.getType(), recordId);
        recordBlood.setRecordStatus(RecordBaseEnum.USAGE_STATE_UN_USE.getType()).setEndTime(DateUtil.getCurrentTime()).setUpdatedTime(DateUtil.getCurrentTime());
        super.recordBloodMapper.updateByPrimaryKeySelective(recordBlood);
        deletePastData(MachineEnum.BLOOD.getType(), recordBlood.getMachineId());
        return machineService.startMachine(recordBlood.getMachineId(), BackgroundSend.DATA_LOSE_CONNECTION);
    }

    @Override
    public CommonResult stopSleepingMachine(Integer recordId) throws Exception {
        if (recordId == -1) {
            ResultUtil.returnError(ErrorCodeEnum.ERROR_MACHINE_STOP);
        }
        RecordSleeping recordSleeping = super.recordSleepingMapper.selectByPrimaryKey(recordId);
        cacheMonitorHistory(MachineEnum.SLEEPING.getType(), recordId);
        recordSleeping.setRecordStatus(RecordBaseEnum.USAGE_STATE_UN_USE.getType()).setEndTime(DateUtil.getCurrentTime()).setUpdatedTime(DateUtil.getCurrentTime());
        super.recordSleepingMapper.updateByPrimaryKeySelective(recordSleeping);
        deletePastData(MachineEnum.SLEEPING.getType(), recordSleeping.getMachineId());
        return machineService.startMachine(recordSleeping.getMachineId(), BackgroundSend.DATA_LOSE_CONNECTION);
    }

    @Override
    public CommonResult changeBed(Integer oldBedId, Integer newBedId, String token) {
        Example example = new Example(RecordBase.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("bedId", oldBedId);
        criteria.andEqualTo("usageStatus", RecordBaseEnum.USAGE_STATE_USE.getType());
        RecordBase recordBase = super.recordBaseMapper.selectOneByExample(example);

        // 管理日志
        ManagerInfo managerInfo = super.loginStateMapper.selectByToken(token);
        ManageLog log = new ManageLog(managerInfo.getId(), "changeBed-bed" + ": id = " + newBedId, DateUtil.getCurrentTime());
        cacheManageLog(recordBase.getId(), log);

        recordBase.setBedId(newBedId).setUpdatedTime(DateUtil.getCurrentTime());
        super.recordBaseMapper.updateByPrimaryKeySelective(recordBase);
        return ResultUtil.returnSuccess();
    }

    @Override
    public CommonResult changeMachine(Integer baseId, Integer type, Integer machineId, String token) throws Exception {
        RecordBase recordBase = super.recordBaseMapper.selectByPrimaryKey(baseId);
        // 管理日志
        ManagerInfo managerInfo = super.loginStateMapper.selectByToken(token);
        ManageLog log = new ManageLog(managerInfo.getId(), "changeMachine-" + MachineEnum.getName(type) + ": id = " + machineId, DateUtil.getCurrentTime());
        cacheManageLog(recordBase.getId(), log);

        switch (type) {
            case MachineConstant.TEMPERATURE:
                return changeTemperatureMachine(baseId, machineId);
            case MachineConstant.ECG:
                return changeEcgMachine(baseId, machineId);
            case MachineConstant.BLOOD:
                return changeBloodMachine(baseId, machineId);
            case MachineConstant.SLEEPING:
                return changeSleepingMachine(baseId, machineId);
            default:
                return ResultUtil.returnError(ErrorCodeEnum.ERROR_MACHINE_TYPE);
        }
    }

    @Override
    public CommonResult changeTemperatureMachine(Integer baseId, Integer machineId) throws Exception {
        RecordBase recordBase = super.recordBaseMapper.selectByPrimaryKey(baseId);
        RecordTemperature recordTemperature = super.recordTemperatureMapper.selectByPrimaryKey(recordBase.getRecordTemperatureId());
        Integer oldMachineId = recordTemperature.getMachineId();
        cacheMonitorHistory(MachineEnum.TEMPERATURE.getType(), recordTemperature.getId());
        recordTemperature.setMachineId(machineId).setUpdatedTime(DateUtil.getCurrentTime());
        super.recordTemperatureMapper.updateByPrimaryKeySelective(recordTemperature);
        return machineService.changeMachine(oldMachineId, machineId);
    }

    @Override
    public CommonResult changeEcgMachine(Integer baseId, Integer machineId) throws Exception {
        RecordBase recordBase = super.recordBaseMapper.selectByPrimaryKey(baseId);
        // 连接心电设备
        BackgroundResult backgroundResult = null;
        try {
            ecgService.connectEcgMachine(machineId, recordBase.getBedId(), BackgroundSend.DATA_LOSE_CONNECTION);
            backgroundResult = ecgService.connectEcgMachine(machineId, recordBase.getBedId(), BackgroundSend.DATA_CONNECTION);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.returnError("无法连接中继器服务器");
        }
        if (backgroundResult == null || !"200".equals(backgroundResult.getCode())) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultUtil.returnError(ErrorCodeEnum.ERROR_CONNECT_ECG);
        }
        RecordEcg recordEcg = super.recordEcgMapper.selectByPrimaryKey(recordBase.getRecordEcgId());
        Integer oldMachineId = recordEcg.getMachineId();
        cacheMonitorHistory(MachineEnum.ECG.getType(), recordEcg.getId());
        recordEcg.setMachineId(machineId).setUpdatedTime(DateUtil.getCurrentTime());
        super.recordEcgMapper.updateByPrimaryKeySelective(recordEcg);
        return machineService.changeMachine(oldMachineId, machineId);
    }

    @Override
    public CommonResult changeBloodMachine(Integer baseId, Integer machineId) throws Exception {
        RecordBase recordBase = super.recordBaseMapper.selectByPrimaryKey(baseId);
        RecordBlood recordBlood = super.recordBloodMapper.selectByPrimaryKey(recordBase.getRecordBloodId());
        Integer oldMachineId = recordBlood.getMachineId();
        cacheMonitorHistory(MachineEnum.BLOOD.getType(), recordBlood.getId());
        recordBlood.setMachineId(machineId).setUpdatedTime(DateUtil.getCurrentTime());
        super.recordBloodMapper.updateByPrimaryKeySelective(recordBlood);
        return machineService.changeMachine(oldMachineId, machineId);
    }

    @Override
    public CommonResult changeSleepingMachine(Integer baseId, Integer machineId) throws Exception {
        RecordBase recordBase = super.recordBaseMapper.selectByPrimaryKey(baseId);
        RecordSleeping recordSleeping = super.recordSleepingMapper.selectByPrimaryKey(recordBase.getRecordSleepingId());
        Integer oldMachineId = recordSleeping.getMachineId();
        cacheMonitorHistory(MachineEnum.SLEEPING.getType(), recordSleeping.getId());
        recordSleeping.setMachineId(machineId).setUpdatedTime(DateUtil.getCurrentTime());
        super.recordSleepingMapper.updateByPrimaryKeySelective(recordSleeping);
        return machineService.changeMachine(oldMachineId, machineId);
    }


    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public CommonResult startMachine(Integer type, Integer machineId, Integer bedId, Integer patientId, String token) throws Exception {
        // 查询 or 新增RecordBase
        Example example = new Example(RecordBase.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("bedId", bedId);
        criteria.andEqualTo("usageStatus", RecordBaseEnum.USAGE_STATE_USE.getType());
        RecordBase recordBase = super.recordBaseMapper.selectOneByExample(example);
        if (recordBase == null) {
            recordBase = new RecordBase();
            recordBase.setBedId(bedId);
            recordBase.setPatientId(patientId);
            recordBase.setUsageStatus(RecordBaseEnum.USAGE_STATE_USE.getType());
            recordBase.setStartTime(DateUtil.getCurrentTime());
            super.recordBaseMapper.insertSelective(recordBase);

            // 管理日志
            ManagerInfo managerInfo = super.loginStateMapper.selectByToken(token);
            ManageLog log = new ManageLog(managerInfo.getId(), "initial-bed" + ": id = " + bedId, DateUtil.getCurrentTime());
            cacheManageLog(recordBase.getId(), log);

        }
        // 管理日志
        ManagerInfo managerInfo = super.loginStateMapper.selectByToken(token);
        ManageLog log = new ManageLog(managerInfo.getId(), "startMachine-" + MachineEnum.getName(type) + ": id = " + machineId, DateUtil.getCurrentTime());
        cacheManageLog(recordBase.getId(), log);
        // 启用设备
        switch (type) {
            case MachineConstant.TEMPERATURE:
                return startTemperatureMachine(recordBase.getId(), machineId);
            case MachineConstant.ECG:
                return startEcgMachine(recordBase.getId(), machineId);
            case MachineConstant.BLOOD:
                return startBloodMachine(recordBase.getId(), machineId);
            case MachineConstant.SLEEPING:
                return startSleepingMachine(recordBase.getId(), machineId);
            default:
                return ResultUtil.returnError(ErrorCodeEnum.ERROR_MACHINE_TYPE);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public CommonResult startTemperatureMachine(Integer baseId, Integer machineId) throws Exception {
        RecordBase recordBase = super.recordBaseMapper.selectByPrimaryKey(baseId);
        if (recordBase.getRecordTemperatureId().equals(RecordBaseEnum.MACHINE_UN_USE.getType())) {
            // 未启用过则新建recordTemperature
            RecordTemperature recordTemperature = new RecordTemperature();
            recordTemperature.setBaseId(baseId).setMachineId(machineId).setStartTime(DateUtil.getCurrentTime()).setHistory(JSON.toJSONString(new TemperatureHistory()));
            super.recordTemperatureMapper.insertSelective(recordTemperature);
            recordBase.setRecordTemperatureId(recordTemperature.getId()).setUpdatedTime(DateUtil.getCurrentTime());
            super.recordBaseMapper.updateByPrimaryKeySelective(recordBase);
        } else {
            RecordTemperature recordTemperature = super.recordTemperatureMapper.selectByPrimaryKey(recordBase.getRecordTemperatureId());
            recordTemperature.setMachineId(machineId).setUpdatedTime(DateUtil.getCurrentTime()).setRecordStatus(0);
            super.recordTemperatureMapper.updateByPrimaryKeySelective(recordTemperature);
        }
        changeMachineState(machineId, MachineConstant.USAGE_STATE_USED);
        return machineService.startMachine(machineId, BackgroundSend.DATA_CONNECTION);
    }

    @Resource
    EcgService ecgService;

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public CommonResult startEcgMachine(Integer baseId, Integer machineId) throws Exception {
        RecordBase recordBase = super.recordBaseMapper.selectByPrimaryKey(baseId);
        // 心电设备能否启用
        boolean b = ecgService.hasRepeater(recordBase.getBedId());
        if (!b) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultUtil.returnError(ErrorCodeEnum.ERROR_USAGE_ECG);
        }
        // 连接心电设备
        BackgroundResult backgroundResult = null;
        try {
            ecgService.connectEcgMachine(machineId, recordBase.getBedId(), BackgroundSend.DATA_LOSE_CONNECTION);
            backgroundResult = ecgService.connectEcgMachine(machineId, recordBase.getBedId(), BackgroundSend.DATA_CONNECTION);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.returnError("无法连接中继器服务器");
        }
        if (backgroundResult == null || !"200".equals(backgroundResult.getCode())) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultUtil.returnError(ErrorCodeEnum.ERROR_CONNECT_ECG);
        }

        if (recordBase.getRecordEcgId().equals(RecordBaseEnum.MACHINE_UN_USE.getType())) {
            // 未启用过则新建record
            RecordEcg recordEcg = new RecordEcg();
            recordEcg.setBaseId(baseId).setMachineId(machineId).setStartTime(DateUtil.getCurrentTime()).setHistory(JSON.toJSONString(new EcgHistory()));
            super.recordEcgMapper.insertSelective(recordEcg);
            recordBase.setRecordEcgId(recordEcg.getId()).setUpdatedTime(DateUtil.getCurrentTime());
            super.recordBaseMapper.updateByPrimaryKeySelective(recordBase);
        } else {
            RecordEcg recordEcg = super.recordEcgMapper.selectByPrimaryKey(recordBase.getRecordEcgId());
            recordEcg.setMachineId(machineId).setUpdatedTime(DateUtil.getCurrentTime()).setRecordStatus(0);
            super.recordEcgMapper.updateByPrimaryKeySelective(recordEcg);
        }
        changeMachineState(machineId, MachineConstant.USAGE_STATE_USED);
        return machineService.startMachine(machineId, BackgroundSend.DATA_CONNECTION);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public CommonResult startBloodMachine(Integer baseId, Integer machineId) throws Exception {
        RecordBase recordBase = super.recordBaseMapper.selectByPrimaryKey(baseId);
        if (recordBase.getRecordBloodId().equals(RecordBaseEnum.MACHINE_UN_USE.getType())) {
            // 未启用过则新建record
            RecordBlood recordBlood = new RecordBlood();
            recordBlood.setBaseId(baseId).setMachineId(machineId).setStartTime(DateUtil.getCurrentTime()).setHistory(JSON.toJSONString(new BloodHistory()));
            super.recordBloodMapper.insertSelective(recordBlood);
            recordBase.setRecordBloodId(recordBlood.getId()).setUpdatedTime(DateUtil.getCurrentTime());
            super.recordBaseMapper.updateByPrimaryKeySelective(recordBase);
        } else {
            RecordBlood recordBlood = super.recordBloodMapper.selectByPrimaryKey(recordBase.getRecordBloodId());
            recordBlood.setMachineId(machineId).setUpdatedTime(DateUtil.getCurrentTime()).setRecordStatus(0);
            super.recordBloodMapper.updateByPrimaryKeySelective(recordBlood);
        }
        changeMachineState(machineId, MachineConstant.USAGE_STATE_USED);
        return machineService.startMachine(machineId, BackgroundSend.DATA_CONNECTION);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public CommonResult startSleepingMachine(Integer baseId, Integer machineId) throws Exception {
        RecordBase recordBase = super.recordBaseMapper.selectByPrimaryKey(baseId);
        if (recordBase.getRecordSleepingId().equals(RecordBaseEnum.MACHINE_UN_USE.getType())) {
            // 未启用过则新建record
            RecordSleeping recordSleeping = new RecordSleeping();
            recordSleeping.setBaseId(baseId).setMachineId(machineId).setStartTime(DateUtil.getCurrentTime()).setHistory(JSON.toJSONString(new TemperatureHistory()));
            super.recordSleepingMapper.insertSelective(recordSleeping);
            recordBase.setRecordSleepingId(recordSleeping.getId()).setUpdatedTime(DateUtil.getCurrentTime());
            super.recordBaseMapper.updateByPrimaryKeySelective(recordBase);
        } else {
            RecordSleeping recordSleeping = super.recordSleepingMapper.selectByPrimaryKey(recordBase.getRecordSleepingId());
            recordSleeping.setMachineId(machineId).setUpdatedTime(DateUtil.getCurrentTime()).setRecordStatus(0);
            super.recordSleepingMapper.updateByPrimaryKeySelective(recordSleeping);
        }
        changeMachineState(machineId, MachineConstant.USAGE_STATE_USED);
        return machineService.startMachine(machineId, BackgroundSend.DATA_CONNECTION);
    }

    @Override
    public boolean cacheMonitorHistory(Integer type, Integer recordId) {
        switch (type) {
            case MachineConstant.TEMPERATURE:
                RecordTemperature recordTemperature = super.recordTemperatureMapper.selectByPrimaryKey(recordId);
                List<TemperatureHistoryData> histories1 = super.recordTemperatureMapper.getHistories(recordTemperature.getMachineId());
                TemperatureHistory temperatureHistory = JSON.parseObject(recordTemperature.getHistory(), TemperatureHistory.class);
                temperatureHistory.getHistory().add(histories1);
                String json = JSON.toJSONString(temperatureHistory);
                recordTemperature.setHistory(json).setUpdatedTime(DateUtil.getCurrentTime());
                super.recordTemperatureMapper.updateByPrimaryKeySelective(recordTemperature);
                deletePastData(MachineEnum.TEMPERATURE.getType(), recordTemperature.getMachineId());
                return true;
            case MachineConstant.ECG:
                RecordEcg recordEcg = super.recordEcgMapper.selectByPrimaryKey(recordId);
                List<EcgHistoryData> histories2 = super.recordEcgMapper.getHistories(recordEcg.getMachineId());
                EcgHistory ecgHistory = JSON.parseObject(recordEcg.getHistory(), EcgHistory.class);
                ecgHistory.getHistory().add(histories2);
                json = JSON.toJSONString(ecgHistory);
                recordEcg.setHistory(json).setUpdatedTime(DateUtil.getCurrentTime());
                super.recordEcgMapper.updateByPrimaryKeySelective(recordEcg);
                return true;
            case MachineConstant.BLOOD:
                RecordBlood recordBlood = super.recordBloodMapper.selectByPrimaryKey(recordId);
                List<BloodHistoryData> histories3 = super.recordBloodMapper.getHistories(recordBlood.getMachineId());
                BloodHistory bloodHistory = JSON.parseObject(recordBlood.getHistory(), BloodHistory.class);
                bloodHistory.getHistory().add(histories3);
                String s = JSON.toJSONString(bloodHistory);
                recordBlood.setHistory(s).setUpdatedTime(DateUtil.getCurrentTime());
                super.recordBloodMapper.updateByPrimaryKeySelective(recordBlood);
                return true;
            case MachineConstant.SLEEPING:
                RecordSleeping recordSleeping = super.recordSleepingMapper.selectByPrimaryKey(recordId);
                List<SleepingHistoryData> histories = super.recordSleepingMapper.getHistories(recordSleeping.getMachineId());
                SleepingHistory sleepingHistory = JSON.parseObject(recordSleeping.getHistory(), SleepingHistory.class);
                sleepingHistory.getHistory().add(histories);
                json = JSON.toJSONString(sleepingHistory);
                recordSleeping.setHistory(json).setUpdatedTime(DateUtil.getCurrentTime());
                super.recordSleepingMapper.updateByPrimaryKeySelective(recordSleeping);
                return true;
            default:
                return false;
        }
    }


    @Override
    public boolean cacheManageLog(Integer baseId, ManageLog log) {
        RecordBase recordBase = super.recordBaseMapper.selectByPrimaryKey(baseId);
        List<ManageLog> logs = new ArrayList<>();
        List<ManageLog> list = JSON.parseObject(recordBase.getLog(), logs.getClass());
        if (StringUtils.isNullorEmpty(list)) {
            list = new ArrayList<>();
        }
        list.add(log);
        String json = JSON.toJSONString(list);
        recordBase.setLog(json).setUpdatedTime(DateUtil.getCurrentTime());
        super.recordBaseMapper.updateByPrimaryKeySelective(recordBase);
        return true;
    }

    @Override
    public Integer changeMachineState(Integer machineId, Integer usageState) {
        ZsMachineInfo machineInfo = new ZsMachineInfo();
        machineInfo.setMachineId(machineId).setUsageState(usageState);
        // 修改设备使用状态
        return ZsMachineInfoMapper.updateByPrimaryKeySelective(machineInfo);
    }

    @Override
    public Integer deletePastData(Integer type, Integer machineId) {
        switch (type) {
            case MachineConstant.TEMPERATURE:
                Example example = new Example(ZsTemperatureInfo.class);
                Example.Criteria criteria = example.createCriteria();
                criteria.andEqualTo("machineId", machineId);
                return super.zsTemperatureInfoMapper.deleteByExample(example);
            case MachineConstant.ECG:
                Example example1 = new Example(ZsHealthInfo.class);
                Example.Criteria criteria1 = example1.createCriteria();
                criteria1.andEqualTo("machineId", machineId);
                return super.zsHealthInfoMapper.deleteByExample(example1);
            case MachineConstant.BLOOD:
                Example example2 = new Example(ZsBloodOxygenInfo.class);
                Example.Criteria criteria2 = example2.createCriteria();
                criteria2.andEqualTo("machineId", machineId);
                return super.zsBloodOxygenInfoMapper.deleteByExample(example2);
            case MachineConstant.SLEEPING:
                Example example3 = new Example(ZsSleepingBeltInfo.class);
                Example.Criteria criteria3 = example3.createCriteria();
                criteria3.andEqualTo("machineId", machineId);
                return super.zsSleepingBeltInfoMapper.deleteByExample(example3);
            default:
                return 0;
        }
    }
}
