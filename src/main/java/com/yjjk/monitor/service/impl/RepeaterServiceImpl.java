/**
 * Copyright (C), 2019, 寻央大人留书
 * FileName: RepeaterServiceImpl
 * Author:   CentreS
 * Date:     2019/8/23 9:57
 * Description: 中继器管理模块
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.monitor.service.impl;

import com.yjjk.monitor.constant.HospitalConstant;
import com.yjjk.monitor.constant.RepeaterEnum;
import com.yjjk.monitor.entity.BO.repeater.DistributionBO;
import com.yjjk.monitor.entity.BO.repeater.DistributionBORoom;
import com.yjjk.monitor.entity.ListVO;
import com.yjjk.monitor.entity.VO.repeater.RoomsRepeaterVO;
import com.yjjk.monitor.entity.VO.repeater.RoomsRepeaterVORooms;
import com.yjjk.monitor.entity.pojo.MachineTypeInfo;
import com.yjjk.monitor.entity.pojo.ZsRepeaterInfo;
import com.yjjk.monitor.service.BaseService;
import com.yjjk.monitor.service.RepeaterService;
import com.yjjk.monitor.utility.NetUtils;
import com.yjjk.monitor.utility.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CentreS
 * @Description: 中继器管理模块
 * @create 2019/8/23
 */
@Service
public class RepeaterServiceImpl extends BaseService implements RepeaterService {

    @Override
    public List<ZsRepeaterInfo> setBandingStatus(List<ZsRepeaterInfo> list) {
        if (StringUtils.isNullorEmpty(list)) {
            return list;
        }
        for (ZsRepeaterInfo temp : list) {
            if (temp.getRoomId() == HospitalConstant.ID_DEFAULT_NULL) {
                temp.setBindStatus(RepeaterEnum.UNBIND_ROOM.getType());
            }
            if (temp.getDepartmentId() == HospitalConstant.ID_DEFAULT_NULL) {
                temp.setBindStatus(RepeaterEnum.UNBIND_DEPARTMENT.getType());
            }
            if (temp.getBindStatus() == null) {
                temp.setBindStatus(RepeaterEnum.BIND.getType());
            }
        }
        return list;
    }

    @Override
    public List<ListVO> getUnbindRepeaters(Integer departmentId) {
        return zsRepeaterInfoMapper.getUnBindRepeaters(departmentId);
    }

    @Override
    public boolean distribution(DistributionBO distributionBO) {
        List<DistributionBORoom> list = distributionBO.getList();
        for (DistributionBORoom distributionBORoom : list) {
            List<Integer> repeaterIds = distributionBORoom.getRepeaterIds();
            for (Integer repeaterId : repeaterIds) {
                ZsRepeaterInfo pojo = new ZsRepeaterInfo();
                pojo.setId(repeaterId)
                        .setDepartmentId(distributionBO.getDepartmentId())
                        .setRoomId(distributionBORoom.getRoomId());
                zsRepeaterInfoMapper.bindRepeater(pojo);
            }
        }
        return true;
    }

    @Override
    public RoomsRepeaterVO getRooms(Integer departmentId) {
        RoomsRepeaterVO result = new RoomsRepeaterVO();
        List<RoomsRepeaterVORooms> rooms = zsRepeaterInfoMapper.getRooms(departmentId);
//        List<ListVO> unBindRepeaters = zsRepeaterInfoMapper.getUnBindRepeaters(departmentId);
        result.setRooms(rooms);
//                .setCanBindRepeaterCount(unBindRepeaters.size());
        return result;
    }

    @Override
    public boolean addRepeater() throws Exception {
        Map map = new HashMap();
        NetUtils.doPost(machineConfig.getRepeater(), map);
//        BackgroundResult backgroundResult = JSON.parseObject(s, BackgroundResult.class);
//        logger.debug(s);
//        if (s != null && backgroundResult.getCode().equals("200")) {
//            return true;
//        }
        return true;
    }

    @Override
    public List<MachineTypeInfo> selectMachineTypes() {
        return super.machineTypeInfoMapper.selectMachineTypes();
    }

    @Override
    public List<MachineTypeInfo> selectMachineNums(Integer id) {
        return super.machineTypeInfoMapper.selectMachineNums(id);
    }

    @Override
    public int insertSelective(ZsRepeaterInfo repeater) {
        ZsRepeaterInfo zsRepeaterInfo = super.zsRepeaterInfoMapper.selectByMac(repeater.getMac());
        if (zsRepeaterInfo != null) {
            return 0;
        }
        zsRepeaterInfo = super.zsRepeaterInfoMapper.selectByIP(repeater.getIp());
        if (zsRepeaterInfo != null) {
            return 0;
        }
//        ZsRepeaterInfo pojo = new ZsRepeaterInfo();
//        pojo.setRoomId(repeater.getRoomId());
//        int existRepeater = super.zsRepeaterInfoMapper.isExistRepeater(pojo);
//        if (existRepeater > 0) {
//            return 0;
//        }
        return super.zsRepeaterInfoMapper.insertSelective(repeater);
    }

    @Override
    public int startRepeater(ZsRepeaterInfo repeaterInfo) {
        return super.zsRepeaterInfoMapper.updateByPrimaryKeySelective(repeaterInfo.setLinkStatus(1).setRemark("").setFailCount(0));
    }

    @Override
    public int stopRepeater(Integer id, String remark) {
        return super.zsRepeaterInfoMapper.updateByPrimaryKeySelective(new ZsRepeaterInfo().setLinkStatus(2).setId(id).setRemark(remark).setFailCount(0));
    }

    @Override
    public List<ZsRepeaterInfo> selectRepeaters(ZsRepeaterInfo record) {
        return super.zsRepeaterInfoMapper.selectRepeaters(record);
    }

    @Override
    public int selectRepeaterCount(ZsRepeaterInfo record) {
        return super.zsRepeaterInfoMapper.selectRepeaterCount(record);
    }
}
