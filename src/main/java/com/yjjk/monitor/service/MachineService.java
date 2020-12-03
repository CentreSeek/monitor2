/**
 * Copyright (C), 2019, 义金(杭州)健康科技有限公司
 * FileName: MachineService
 * Author:   CentreS
 * Date:     2019/7/18 13:49
 * Description: 设备管理
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.monitor.service;

import com.yjjk.monitor.configer.CommonResult;
import com.yjjk.monitor.controller.BaseController;
import com.yjjk.monitor.entity.ListVO;
import com.yjjk.monitor.entity.VO.monitor.MachineTypeListVO;
import com.yjjk.monitor.entity.export.machine.MachineExportVO;
import com.yjjk.monitor.entity.pojo.MachineTypeInfo;
import com.yjjk.monitor.entity.pojo.ZsMachineInfo;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

/**
 * @author CentreS
 * @Description: 设备管理
 * @create 2019/7/18
 */
public interface MachineService {

    /**
     * 分配设备
     * @param departmentId
     * @param machineIds
     * @return
     */
    boolean updateDepartmentOfMachines(Integer departmentId, List<Integer> machineIds);
    /**
     * 获取无科室设备列表
     * @return
     */
    List<ListVO> getNullDepartmentMachines();

    /**
     * 启用设备-通知数据采集服务器
     *
     * @param machineId
     * @param connectionType
     * @return
     * @throws Exception
     */
    CommonResult startMachine(Integer machineId, String connectionType) throws Exception;

    /**
     * 更换设备-通知数据采集服务器
     * @param oldMachineId
     * @param newMachineId
     * @return
     * @throws Exception
     */
    CommonResult changeMachine(Integer oldMachineId, Integer newMachineId) throws Exception;

    /**
     * 新增设备
     *
     * @param machineInfo
     * @return
     */
    int insertSelective(ZsMachineInfo machineInfo);

    /**
     * 删除设备
     *
     * @param machineId
     * @param remark
     * @return
     */
    int deleteMachine(Integer machineId, String remark);

    /**
     * 批量插入设备
     *
     * @param machineInfo
     * @return
     */
    int insertByMachineNums(ZsMachineInfo machineInfo);

    /**
     * 批量插入设备
     *
     * @param machineInfo
     * @return
     */
    int insertByMachineNum(ZsMachineInfo machineInfo);


    /**
     * 查询设备总数(machineId, usageState)
     *
     * @param machineInfo
     * @return
     */
    int selectCount(ZsMachineInfo machineInfo);

    /**
     * 根据设备的使用状态查询
     *
     * @param machineInfo
     * @return
     */
    List<ZsMachineInfo> selectByUsageState(ZsMachineInfo machineInfo);

    List<ListVO> selectUsageListByTypeId(Map<String, Object> paraMap);
    List<ListVO> selectUsageListByTypeIdMachineModel(Map<String, Object> paraMap);

    /**
     * 设备导出
     *
     * @param machineInfo
     * @return
     */
    List<MachineExportVO> export(ZsMachineInfo machineInfo,Integer language);

    /**
     * 更新设备信息
     *
     * @param machineInfo
     * @return
     */
    int updateByMachineId(ZsMachineInfo machineInfo);

    /**
     * 查找设备信息
     *
     * @param machineId
     * @return
     */
    ZsMachineInfo selectByPrimaryKey(Integer machineId);

    /**
     * 使用SN编号查询设备数量
     *
     * @param machineNum
     * @return
     */
    int selectByMachineNum(String machineNum);

    int selectByMachineNum(String machineNum, Integer machineId);

    /**
     * 使用设备编号查询设备数量
     *
     * @param machineNo
     * @return
     */
    int selectByMachineNo(String machineNo);

    int selectByMachineNo(String machineNo, Integer machineId);

    int selectByMachineMac(String machineMac);

    int selectByMachineMac(String machineMac, Integer machineId);

    /**
     * 使用设备型号查询设备信息
     *
     * @param machineModel
     * @return
     */
    Integer selectByMachineModel(String machineModel);

    /**
     * 获取所有设备
     *
     * @param map
     * @return
     */
    List<ZsMachineInfo> selectAllMachines(Map<String, Object> map);

    /**
     * 查找设备
     *
     * @param map
     * @return
     */
    CommonResult searchMachine(Map<String, Object> map);

    /**
     * 获取体温设备名称
     *
     * @return
     */
    List<MachineTypeInfo> getTemperatureMachineName();

    /**
     * 监控页面-获取设备概况
     * @param departmentId
     * @return
     */
    List<MachineTypeListVO> getMonitorTypeList(Integer departmentId);

    /**
     * 获取设备型号
     * @param machineTypeId
     * @return
     */
    List<MachineTypeInfo> getMachineModel(Integer machineTypeId);

    /**
     * 绑定ecg设备
     *
     * @param machineNum
     * @return
     * @throws Exception
     */
    boolean connectionService(String machineNum) throws Exception;

    int updateSelective(ZsMachineInfo zsMachineInfo);


}
