/**
 * Copyright (C), 2019, 义金(杭州)健康科技有限公司
 * FileName: MachineServiceImpl
 * Author:   CentreS
 * Date:     2019/7/18 13:49
 * Description: 设备管理
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.monitor.service.impl;

import com.alibaba.fastjson.JSON;
import com.yjjk.monitor.configer.CommonResult;
import com.yjjk.monitor.configer.ErrorCodeEnum;
import com.yjjk.monitor.constant.MachineConstant;
import com.yjjk.monitor.constant.MachineEnum;
import com.yjjk.monitor.constant.SearchMachineConstant;
import com.yjjk.monitor.entity.ListVO;
import com.yjjk.monitor.entity.VO.SearchMachineVO;
import com.yjjk.monitor.entity.VO.SearchMachineVOBase;
import com.yjjk.monitor.entity.VO.monitor.MachineTypeListVO;
import com.yjjk.monitor.entity.export.machine.MachineExport;
import com.yjjk.monitor.entity.export.machine.MachineExportVO;
import com.yjjk.monitor.entity.pojo.MachineTypeInfo;
import com.yjjk.monitor.entity.pojo.ZsBloodOxygenInfo;
import com.yjjk.monitor.entity.pojo.ZsHealthInfo;
import com.yjjk.monitor.entity.pojo.ZsMachineInfo;
import com.yjjk.monitor.entity.pojo.ZsSleepingBeltInfo;
import com.yjjk.monitor.entity.pojo.ZsTemperatureInfo;
import com.yjjk.monitor.entity.transaction.BackgroundResult;
import com.yjjk.monitor.entity.transaction.BackgroundSend;
import com.yjjk.monitor.service.BaseService;
import com.yjjk.monitor.service.MachineService;
import com.yjjk.monitor.service.MonitorService;
import com.yjjk.monitor.utility.DateUtil;
import com.yjjk.monitor.utility.NetUtils;
import com.yjjk.monitor.utility.ResultUtil;
import com.yjjk.monitor.utility.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CentreS
 * @Description: 设备管理
 * @create 2019/7/18
 */
@Service
public class MachineServiceImpl extends BaseService implements MachineService {

    @Override
    public CommonResult startMachine(Integer machineId, String connectionType) throws Exception {
        // 连接设备
        BackgroundSend backgroundSend = new BackgroundSend();
        ZsMachineInfo machineInfo = super.ZsMachineInfoMapper.getByMachineId(machineId);
        backgroundSend.setDeviceId(machineInfo.getMachineNum());
        backgroundSend.setData(connectionType);
        String s = NetUtils.doPost(connectRepeater.getStart(), backgroundSend);
        if (s == "500") {
            throw new ConnectException();
        }
        logger.info("启用设备-硬件服务器返回值：     " + s);
        BackgroundResult backgroundResult = JSON.parseObject(s, BackgroundResult.class);
        if (backgroundResult == null || !"200".equals(backgroundResult.getCode())) {
            throw new ConnectException();
        }
        return ResultUtil.returnSuccess();
    }

    @Resource
    MonitorService monitorService;

    @Override
    public CommonResult changeMachine(Integer oldMachineId, Integer newMachineId) throws Exception {
        // 连接设备
        BackgroundSend backgroundSend = new BackgroundSend();
        ZsMachineInfo machineInfo = super.ZsMachineInfoMapper.getByMachineId(oldMachineId);
        backgroundSend.setDeviceId(machineInfo.getMachineNum());
        backgroundSend.setData(BackgroundSend.DATA_LOSE_CONNECTION);
        String s = NetUtils.doPost(connectRepeater.getStart(), backgroundSend);
        logger.info("停用设备-硬件服务器返回值：     " + s);
        BackgroundResult backgroundResult = JSON.parseObject(s, BackgroundResult.class);
        if (backgroundResult == null || !"200".equals(backgroundResult.getCode())) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultUtil.returnError(ErrorCodeEnum.ERROR_CONNECT_DATA_SERVICE);
        }
        monitorService.changeMachineState(oldMachineId, MachineConstant.USAGE_STATE_NORMAL);

        // 连接设备
        ZsMachineInfo newMachineInfo = super.ZsMachineInfoMapper.getByMachineId(newMachineId);
        backgroundSend.setDeviceId(newMachineInfo.getMachineNum());
        backgroundSend.setData(BackgroundSend.DATA_CONNECTION);
        s = NetUtils.doPost(connectRepeater.getStart(), backgroundSend);
        logger.info("启用设备-硬件服务器返回值：     " + s);
        backgroundResult = JSON.parseObject(s, BackgroundResult.class);
        if (backgroundResult == null || !"200".equals(backgroundResult.getCode())) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultUtil.returnError(ErrorCodeEnum.ERROR_CONNECT_DATA_SERVICE);
        }
        monitorService.changeMachineState(newMachineId, MachineConstant.USAGE_STATE_USED);
        return ResultUtil.returnSuccess();
    }

    @Override
    public int insertSelective(ZsMachineInfo machineInfo) {
        return super.ZsMachineInfoMapper.insertSelective(machineInfo);
    }

    @Override
    public int deleteMachine(Integer machineId, String remark) {
        ZsMachineInfo machineInfo = new ZsMachineInfo();
        machineInfo.setMachineId(machineId);
        machineInfo.setRemark(remark);
        machineInfo.setUsageState(1);
        return super.ZsMachineInfoMapper.updateByPrimaryKeySelective(machineInfo);
    }

    @Override
    public int insertByMachineNums(ZsMachineInfo machineInfo) {
        return super.ZsMachineInfoMapper.insertByMachineNums(machineInfo);
    }

    @Override
    public int insertByMachineNum(ZsMachineInfo machineInfo) {
        return this.ZsMachineInfoMapper.insertSelective(machineInfo);
    }

    @Override
    public int selectCount(ZsMachineInfo machineInfo) {
        return super.ZsMachineInfoMapper.selectMachineCount(machineInfo);
    }

    @Override
    public List<ZsMachineInfo> selectByUsageState(ZsMachineInfo machineInfo) {
        return super.ZsMachineInfoMapper.selectByUsageState(machineInfo);
    }

    @Override
    public List<ListVO> selectUsageListByTypeId(Map<String, Object> paraMap) {
        String name = (String) paraMap.get("name");
        if (!StringUtils.isNullorEmpty(name)) {
            name = StringUtils.getLikeName(name);
            paraMap.put("name", name);
        }
        return super.ZsMachineInfoMapper.selectUsageListByTypeId(paraMap);
    }

    @Override
    public List<ListVO> selectUsageListByTypeIdMachineModel(Map<String, Object> paraMap) {
        return super.ZsMachineInfoMapper.selectUsageListByTypeIdMachineModel(paraMap);
    }


    @Override
    public List<MachineExportVO> export(ZsMachineInfo machineInfo) {
        List<MachineExport> list = super.ZsMachineInfoMapper.export(machineInfo);
        List<MachineExportVO> reqList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            reqList.add(list.get(i).transBean(list.get(i)));
        }
        return reqList;
    }

    @Override
    public int updateByMachineId(ZsMachineInfo machineInfo) {
        return super.ZsMachineInfoMapper.updateByPrimaryKeySelective(machineInfo);
    }

    @Override
    public ZsMachineInfo selectByPrimaryKey(Integer machineId) {
        return super.ZsMachineInfoMapper.getByMachineId(machineId);
    }

    @Override
    public int selectByMachineNum(String machineNum) {
        return super.ZsMachineInfoMapper.selectByMachineNum(machineNum);
    }

    @Override
    public int selectByMachineNo(String machineNo) {
        return super.ZsMachineInfoMapper.selectCountByMachineNo(machineNo);
    }

    @Override
    public Integer selectByMachineModel(String machineModel) {
        return super.ZsMachineInfoMapper.selectByMachineModelId(machineModel);
    }

    @Override
    public List<ZsMachineInfo> selectAllMachines(Map<String, Object> map) {
        return super.ZsMachineInfoMapper.selectAllMachines(map);
    }

    @Override
    public List<MachineTypeInfo> getTemperatureMachineName() {
        return this.machineTypeInfoMapper.getTemperatureMachineName();
    }

    @Override
    public List<MachineTypeListVO> getMonitorTypeList(Integer departmentId) {
        List<MachineTypeListVO> temperatureMachineName = super.machineTypeInfoMapper.getMonitorMachineTypes(departmentId);
        for (int i = 0; i < temperatureMachineName.size(); i++) {
            Integer count = super.machineTypeInfoMapper.getMonitorMachineTypesCount(departmentId, temperatureMachineName.get(i).getMachineTypeId());
            temperatureMachineName.get(i).setMachineCount(count);
        }
        for (int i = 0; i < temperatureMachineName.size(); i++) {
            MachineTypeListVO temp = temperatureMachineName.get(i);
            temp.setValue(MachineEnum.getName(temperatureMachineName.get(i).getId()));
        }
        return temperatureMachineName;
    }

    @Override
    public List<MachineTypeInfo> getMachineModel(Integer machineTypeId) {
        return super.machineTypeInfoMapper.getMachineModel(machineTypeId);
    }


    @Override
    public CommonResult searchMachine(Map<String, Object> map) {
        List<SearchMachineVO> list = super.ZsMachineInfoMapper.searchRepeaterBaseInfo((Integer) map.get("departmentId"));
        Integer repeaterId = null;
        String createTIme = null;
        switch ((Integer) map.get("type")) {
            case MachineConstant.TEMPERATURE:
                ZsTemperatureInfo temperatureInfo = super.zsTemperatureInfoMapper.getByMachineId((Integer) map.get("machineId"));
                if (temperatureInfo != null) {
                    repeaterId = temperatureInfo.getRepeaterId();
                    createTIme = temperatureInfo.getCreateTime();
                }
                break;
            case MachineConstant.ECG:
                ZsHealthInfo zsHealthInfo = super.zsHealthInfoMapper.getByMachineId((Integer) map.get("machineId"));
                if (zsHealthInfo != null) {
                    repeaterId = zsHealthInfo.getRepeaterId();
                    createTIme = zsHealthInfo.getCreateTime();
                }
                break;
            case MachineConstant.BLOOD:
                ZsBloodOxygenInfo zsBloodOxygenInfo = super.zsBloodOxygenInfoMapper.getByMachineId((Integer) map.get("machineId"));
                if (zsBloodOxygenInfo != null) {
                    repeaterId = zsBloodOxygenInfo.getRepeaterId();
                    createTIme = zsBloodOxygenInfo.getCreateTime();
                }
                break;
            case MachineConstant.SLEEPING:
                ZsSleepingBeltInfo zsSleepingBeltInfo = super.zsSleepingBeltInfoMapper.getByMachineId((Integer) map.get("machineId"));
                if (zsSleepingBeltInfo != null) {
                    repeaterId = zsSleepingBeltInfo.getRepeaterId();
                    createTIme = zsSleepingBeltInfo.getCreateTime();
                }
                break;
            default:
                return ResultUtil.returnError(ErrorCodeEnum.ERROR_MACHINE_TYPE);
        }

        SearchMachineVOBase searchMachineVOBase = new SearchMachineVOBase();
        searchMachineVOBase.setStatus(1).setList(list);
        for (SearchMachineVO temp : list) {
            if (StringUtils.isNullorEmpty(temp.getRepeaterId())) {
                temp.setRepeaterStatus(SearchMachineConstant.REPEATER_NONE);
            } else {
                temp.setRepeaterStatus(SearchMachineConstant.NOT_FIND);
            }
        }
        boolean flag = false;
        if (repeaterId == null || createTIme == null) {
            searchMachineVOBase.setStatus(2);
            return ResultUtil.returnSuccess(searchMachineVOBase, "没有搜索到设备");
        } else {
            for (int i = 0; i < list.size(); i++) {
                if (repeaterId.equals(list.get(i).getRepeaterId())) {
                    flag = true;
                    // 5m内数据为有效数据
                    if (DateUtil.timeDifferentLong(createTIme, DateUtil.getCurrentTime()) <= 5) {
                        list.get(i).setRepeaterStatus(SearchMachineConstant.FIND);
                        list.get(i).setLastRecordTime(createTIme);
                        searchMachineVOBase.setStatus(0);
                        searchMachineVOBase.setRoomName(list.get(i).getRoomName());
                        searchMachineVOBase.setLastRecordTime(createTIme);
                    } else {
                        list.get(i).setRepeaterStatus(SearchMachineConstant.NOT_FIND);
                        list.get(i).setLastRecordTime(createTIme);
                        searchMachineVOBase.setLastRecordTime(createTIme);
                        searchMachineVOBase.setRoomName(list.get(i).getRoomName());
                        return ResultUtil.returnSuccess(searchMachineVOBase, "没有搜索到设备");
                    }
                } else if (i == list.size() - 1 && !flag) {
                    searchMachineVOBase.setStatus(2);
                    return ResultUtil.returnSuccess(searchMachineVOBase, "没有搜索到设备");
                }
            }
            return ResultUtil.returnSuccess(searchMachineVOBase);
        }
    }

    @Override
    public boolean connectionService(String machineNum) throws Exception {
        Map map = new HashMap();
        map.put("deviceId", machineNum);
        String s = NetUtils.doPost(machineConfig.getUrl(), map);
        BackgroundResult backgroundResult = JSON.parseObject(s, BackgroundResult.class);
        logger.debug(s);
        if (s != null && backgroundResult.getCode().equals("200")) {
            return true;
        }
        return false;
    }


}
