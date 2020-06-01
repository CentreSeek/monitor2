package com.yjjk.monitor.mapper;

import com.yjjk.monitor.entity.VO.monitor.MachineTypeListVO;
import com.yjjk.monitor.entity.pojo.MachineTypeInfo;
import com.yjjk.monitor.my.mapper.MyMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface MachineTypeInfoMapper extends MyMapper<MachineTypeInfo> {

    @Override
    int insert(MachineTypeInfo record);

    /**
     * 查询设备名称
     * @return
     */
    List<MachineTypeInfo> selectMachineTypes();

    /**
     * 查询设备型号
     * @param id
     * @return
     */
    List<MachineTypeInfo> selectMachineNums(Integer id);

    /**
     * 获取体温设备名称
     * @return
     */
    List<MachineTypeInfo> getTemperatureMachineName();

    /**
     * 获取设备型号
     * @param machineTypeId
     * @return
     */
    List<MachineTypeInfo> getMachineModel(Integer machineTypeId);

    /**
     * 获取监控页设备概况
     * @return
     */
    List<MachineTypeListVO> getMonitorMachineTypes(Integer departmentId);

    /**
     * 获取监控页设备概况-设备数量
     * @param departmentId
     * @param machineTypeId
     * @return
     */
    Integer getMonitorMachineTypesCount(Integer departmentId,Integer machineTypeId);
}