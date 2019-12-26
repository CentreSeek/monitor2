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
import com.yjjk.monitor.constant.EcgConstant;
import com.yjjk.monitor.constant.SearchMachineConstant;
import com.yjjk.monitor.entity.VO.SearchMachineVO;
import com.yjjk.monitor.entity.VO.SearchMachineVOBase;
import com.yjjk.monitor.entity.ZsMachineInfo;
import com.yjjk.monitor.entity.ZsMachineTypeInfo;
import com.yjjk.monitor.entity.ZsTemperatureInfo;
import com.yjjk.monitor.entity.export.MachineExport;
import com.yjjk.monitor.entity.export.MachineExportVO;
import com.yjjk.monitor.entity.transaction.BackgroundResult;
import com.yjjk.monitor.service.BaseService;
import com.yjjk.monitor.service.MachineService;
import com.yjjk.monitor.utility.DateUtil;
import com.yjjk.monitor.utility.NetUtils;
import com.yjjk.monitor.utility.ResultUtil;
import com.yjjk.monitor.utility.StringUtils;
import org.springframework.stereotype.Service;

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
        return this.ZsMachineInfoMapper.insertByMachineNum(machineInfo);
    }
    @Override
    public int selectCount(ZsMachineInfo machineInfo) {
        return super.ZsMachineInfoMapper.selectCount(machineInfo);
    }

    @Override
    public List<ZsMachineInfo> selectByUsageState(ZsMachineInfo machineInfo) {
        return super.ZsMachineInfoMapper.selectByUsageState(machineInfo);
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
        return super.ZsMachineInfoMapper.selectByPrimaryKey(machineId);
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
    public List<ZsMachineInfo> selectAllMachines(Map<String, Object> map) {
        return super.ZsMachineInfoMapper.selectAllMachines(map);
    }

    @Override
    public List<ZsMachineTypeInfo> getTemperatureMachineName() {
        return this.zsMachineTypeInfoMapper.getTemperatureMachineName();
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
                    // 5秒内数据为有效数据
                    if (DateUtil.timeDifferentLong(temperatureInfo.getCreateTime(), DateUtil.getCurrentTime()) > 1000 * 5) {
                        list.get(i).setRepeaterStatus(SearchMachineConstant.FIND);
                        searchMachineVOBase.setStatus(0);
                        searchMachineVOBase.setRoomName(list.get(i).getRoomName());
                    } else {
                        list.get(i).setRepeaterStatus(SearchMachineConstant.NOT_FIND);
                        list.get(i).setLastRecordTime(temperatureInfo.getCreateTime());
                        searchMachineVOBase.setLastRecordTime(temperatureInfo.getCreateTime());
                        searchMachineVOBase.setRoomName(list.get(i).getRoomName());
                        return ResultUtil.returnSuccess(searchMachineVOBase, "没有搜索到设备");
                    }
                }else if (i == list.size()-1 && !flag){
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
        String s = NetUtils.doPost(EcgConstant.ADD_TEMPERATURE_MACHINE, map);
        BackgroundResult backgroundResult = JSON.parseObject(s, BackgroundResult.class);
        logger.debug(s);
        if (s != null && backgroundResult.getCode().equals("200")) {
            return true;
        }
        return false;
    }
}
