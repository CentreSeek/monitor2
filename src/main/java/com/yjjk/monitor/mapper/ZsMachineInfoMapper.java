package com.yjjk.monitor.mapper;


import com.yjjk.monitor.entity.ListVO;
import com.yjjk.monitor.entity.VO.SearchMachineVO;
import com.yjjk.monitor.entity.export.machine.MachineExport;
import com.yjjk.monitor.entity.pojo.MachineTypeInfo;
import com.yjjk.monitor.entity.pojo.ZsMachineInfo;
import com.yjjk.monitor.my.mapper.MyMapper;

import java.util.List;
import java.util.Map;

public interface ZsMachineInfoMapper extends MyMapper<ZsMachineInfo> {

    /**
     * 解绑
     *
     * @param departmentId
     * @param roomId
     * @return
     */
    Integer unbind(Integer departmentId, Integer roomId);

    /**
     * 使用主键更新
     *
     * @param pojo
     * @return
     */
    Integer updateByMachineId(ZsMachineInfo pojo);

    /**
     * 获取无科室设备列表
     *
     * @return
     */
    List<ListVO> getNullDepartmentMachines();

    /**
     * 获取使用中设备id list
     *
     * @return
     */
    List<Integer> getList();

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
    int selectMachineCount(ZsMachineInfo machineInfo);

    /**
     * select---分页查询设备信息(usageSate)
     * @param machineInfo
     * @return
     */
    List<ZsMachineInfo> selectByUsageState(ZsMachineInfo machineInfo);
    List<ListVO> selectUsageListByTypeId(Map paraMap);
    List<ListVO> selectUsageListByTypeIdMachineModel(Map paraMap);

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
    int selectByMachineNum(String machineNum, Integer machineId);

    /**
     * 使用设备编号查询设备数量
     *
     * @param machineNo
     * @return
     */
    int selectCountByMachineNo(String machineNo, Integer machineId);

    int selectCountByMachineMac(String machineMac, Integer machineId);

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
    List<MachineTypeInfo> getTemperatureMachineName();

    /**
     * insert---批量插入设备
     *
     * @param machineInfo
     * @return
     */
    int insertByMachineNum(ZsMachineInfo machineInfo);
    ZsMachineInfo getByMachineId(Integer machineId);

    /**
     * 获取使用中设备数量
     *
     * @param departmentId
     * @param roomId
     * @return
     */
    Integer getUsingMachinesCount(Integer departmentId, Integer roomId);
}