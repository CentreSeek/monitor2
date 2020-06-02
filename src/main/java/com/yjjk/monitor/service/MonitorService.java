/**
 * Copyright (C), 2019, 义金(杭州)健康科技有限公司
 * FileName: PatientRecordService
 * Author:   CentreS
 * Date:     2019/7/22 11:41
 * Description: 历史记录
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.monitor.service;

import com.yjjk.monitor.configer.CommonResult;
import com.yjjk.monitor.entity.VO.monitor.MonitorBaseVO;
import com.yjjk.monitor.entity.VO.monitor.MonitorMachineListVO;
import com.yjjk.monitor.entity.VO.monitor.MonitorVO;
import com.yjjk.monitor.entity.log.ManageLog;
import com.yjjk.monitor.entity.pojo.RecordBase;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author CentreS
 * @Description: 历史记录
 * @create 2019/7/22
 */
@Service
public interface MonitorService {

    /**
     * 设置设备状态
     *
     * @param monitorVO
     * @return
     */
    MonitorVO setMachineState(MonitorVO monitorVO);

    /**
     * 设置数值状态
     *
     * @param monitorVO
     * @param departmentId
     * @return
     */
    MonitorVO setMonitorRule(MonitorVO monitorVO, Integer departmentId);

    /**
     * 实时监控信息
     *
     * @param departmentId
     * @return
     */
    List<MonitorBaseVO> getMonitors(Integer departmentId,Integer start,Integer end);

    MonitorBaseVO getTemperature(MonitorBaseVO monitorBaseVO, Integer recordId);

    MonitorBaseVO getHeartRate(MonitorBaseVO monitorBaseVO, Integer ecgRecordId, Integer bloodRecordId, Integer sleepingRecordId);

    MonitorBaseVO getRespiratory(MonitorBaseVO monitorBaseVO, Integer ecgRecordId, Integer sleepingRecordId);

    MonitorBaseVO getBloodOxygen(MonitorBaseVO monitorBaseVO, Integer recordId);

    List<MonitorBaseVO> getSleeping(List<MonitorBaseVO> list);

    List<MonitorMachineListVO> getMachinesInfo(RecordBase recordBase);


    /**
     * 停止监测
     *
     * @param baseId
     * @param type
     * @param token
     * @return
     * @throws Exception
     */
    CommonResult stopMachine(Integer baseId, Integer type, String token) throws Exception;

    CommonResult stopTemperatureMachine(Integer recordId) throws Exception;

    CommonResult stopEcgMachine(Integer recordId, Integer bedId) throws Exception;

    CommonResult stopBloodMachine(Integer recordId) throws Exception;

    CommonResult stopSleepingMachine(Integer recordId) throws Exception;

    /**
     * 更换床位
     *
     * @param oldBedId
     * @param newBedId
     * @param token
     * @return
     */
    CommonResult changeBed(Integer oldBedId, Integer newBedId, String token);

    /**
     * 更换设备
     *
     * @param baseId
     * @param type
     * @param machineId
     * @param token
     * @return
     */
    CommonResult changeMachine(Integer baseId, Integer type, Integer machineId, String token) throws Exception;

    CommonResult changeTemperatureMachine(Integer baseId, Integer machineId) throws Exception;

    CommonResult changeEcgMachine(Integer baseId, Integer machineId) throws Exception;

    CommonResult changeBloodMachine(Integer baseId, Integer machineId) throws Exception;

    CommonResult changeSleepingMachine(Integer baseId, Integer machineId) throws Exception;

    /**
     * 启用设备
     *
     * @param type
     * @param machineId
     * @param bedId
     * @param patientId
     * @param token
     * @return
     */
    CommonResult startMachine(Integer type, Integer machineId, Integer bedId, Integer patientId, String token) throws Exception;

    CommonResult startTemperatureMachine(Integer baseId, Integer machineId) throws Exception;

    CommonResult startEcgMachine(Integer baseId, Integer machineId) throws Exception;

    CommonResult startBloodMachine(Integer baseId, Integer machineId) throws Exception;

    CommonResult startSleepingMachine(Integer baseId, Integer machineId) throws Exception;

    /**
     * 持久化监测数据
     *
     * @param type
     * @param recordId
     * @return
     */
    boolean cacheMonitorHistory(Integer type, Integer recordId);

    /**
     * 记录操作信息
     *
     * @param baseId
     * @param log
     * @return
     */
    boolean cacheManageLog(Integer baseId, ManageLog log);

    /**
     * 更新设备状态
     *
     * @param machineId
     * @param usageState
     * @return
     */
    Integer changeMachineState(Integer machineId, Integer usageState);

    /**
     * 清理过期数据
     *
     * @param machineId
     * @return
     */
    Integer deletePastData(Integer type, Integer machineId);


}
