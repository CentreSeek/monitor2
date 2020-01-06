package com.yjjk.monitor.mapper;


import com.yjjk.monitor.entity.VO.SearchMachineVO;
import com.yjjk.monitor.entity.ZsMachineInfo;
import com.yjjk.monitor.entity.ZsMachineTypeInfo;
import com.yjjk.monitor.entity.export.MachineExport;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ZsMachineInfoMapper {
    int deleteByPrimaryKey(Integer machineId);

    int insert(ZsMachineInfo record);

    int insertSelective(ZsMachineInfo record);

    ZsMachineInfo selectByPrimaryKey(Integer machineId);

    int updateByPrimaryKeySelective(ZsMachineInfo record);

    int updateByPrimaryKey(ZsMachineInfo record);

    /**
     * insert---批量插入设备
     * @param machineInfo
     * @return
     */
    int insertByMachineNums(ZsMachineInfo machineInfo);

    /**
     * select---查询设备总数(machineId, usageState)
     * @param machineInfo
     * @return
     */
    int selectCount(ZsMachineInfo machineInfo);

    /**
     * select---分页查询设备信息(usageSate)
     * @param machineInfo
     * @return
     */
    List<ZsMachineInfo> selectByUsageState(ZsMachineInfo machineInfo);

    /**
     * 设备导出
     * @param machineInfo
     * @return
     */
    List<MachineExport> export(ZsMachineInfo machineInfo);

    /**
     * 使用SN编号查询设备数量
     * @param machineNum
     * @return
     */
    int selectByMachineNum(String machineNum);

    /**
     * 使用设备编号查询设备数量
     * @param machineNum
     * @return
     */
    int selectCountByMachineNo(String machineNum);

    /**
     * 查询设备类型id
     * @param machineModel
     * @return
     */
    int selectByMachineModelId(String machineModel);

    /**
     * 获取所有设备
     *
     * @param map
     * @return
     */
    List<ZsMachineInfo> selectAllMachines(Map<String, Object> map);
    /**
     * 查找设备基础信息
     * @param departmentId
     * @return
     */
    List<SearchMachineVO> searchRepeaterBaseInfo(Integer departmentId);

    /**
     * 获取体温设备名称
     * @return
     */
    List<ZsMachineTypeInfo> getTemperatureMachineName();

    /**
     * insert---批量插入设备
     *
     * @param machineInfo
     * @return
     */
    int insertByMachineNum(ZsMachineInfo machineInfo);
}