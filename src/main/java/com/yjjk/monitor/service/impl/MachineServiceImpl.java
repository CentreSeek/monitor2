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
import com.yjjk.monitor.entity.pojo.ZsMachineInfo;
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
        logger.info("启用设备-硬件服务器返回值：     " + s);
        BackgroundResult backgroundResult = JSON.parseObject(s, BackgroundResult.class);
        if (backgroundResult == null || !"200".equals(backgroundResult.getCode())) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultUtil.returnError(ErrorCodeEnum.ERROR_CONNECT_ECG);
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
            return ResultUtil.returnError(ErrorCodeEnum.ERROR_CONNECT_ECG);
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
            return ResultUtil.returnError(ErrorCodeEnum.ERROR_CONNECT_ECG);
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
    public List<MachineTypeListVO> getMonitorTypeList() {
        List<MachineTypeInfo> temperatureMachineName = super.machineTypeInfoMapper.getTemperatureMachineName();
        List<MachineTypeListVO> result = new ArrayList<>();
        for (int i = 0; i < temperatureMachineName.size(); i++) {
            MachineTypeListVO temp = new MachineTypeListVO();
            temp.setMachineTypeId(temperatureMachineName.get(i).getId())
                    .setValue(MachineEnum.getName(temperatureMachineName.get(i).getTypeCode()))
                    .setId(temperatureMachineName.get(i).getTypeCode());
            result.add(temp);
        }
        return result;
    }

    @Override
    public List<MachineTypeInfo> getMachineModel(Integer machineTypeId) {
        return super.machineTypeInfoMapper.getMachineModel(machineTypeId);
    }


    @Override
    public CommonResult searchMachine(Map<String, Object> map) {
        List<SearchMachineVO> list = super.ZsMachineInfoMapper.searchRepeaterBaseInfo((Integer) map.get("departmentId"));
        ZsTemperatureInfo temperatureInfo = super.zsTemperatureInfoMapper.getByMachineId((Integer) map.get("machineId"));
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
        if (temperatureInfo == null) {
            searchMachineVOBase.setStatus(2);
            return ResultUtil.returnSuccess(searchMachineVOBase, "没有搜索到设备");
        } else {
            for (int i = 0; i < list.size(); i++) {
                if (temperatureInfo.getRepeaterId().equals(list.get(i).getRepeaterId())) {
                    flag = true;
                    // 5m内数据为有效数据
                    if (DateUtil.timeDifferentLong(temperatureInfo.getCreateTime(), DateUtil.getCurrentTime()) <= 5) {
                        list.get(i).setRepeaterStatus(SearchMachineConstant.FIND);
                        list.get(i).setLastRecordTime(temperatureInfo.getCreateTime());
                        searchMachineVOBase.setStatus(0);
                        searchMachineVOBase.setRoomName(list.get(i).getRoomName());
                        searchMachineVOBase.setLastRecordTime(temperatureInfo.getCreateTime());
                    } else {
                        list.get(i).setRepeaterStatus(SearchMachineConstant.NOT_FIND);
                        list.get(i).setLastRecordTime(temperatureInfo.getCreateTime());
                        searchMachineVOBase.setLastRecordTime(temperatureInfo.getCreateTime());
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
