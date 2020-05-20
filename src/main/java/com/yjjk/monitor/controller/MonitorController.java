/**
 * Copyright (C), 2019, 寻央大人留书
 * FileName: RepeaterController
 * Author:   CentreS
 * Date:     2019/8/23 9:49
 * Description: 中继器管理模块
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.monitor.controller;

import com.yjjk.monitor.configer.CommonResult;
import com.yjjk.monitor.configer.ErrorCodeEnum;
import com.yjjk.monitor.constant.MachineEnum;
import com.yjjk.monitor.entity.BO.monitor.StartBO;
import com.yjjk.monitor.entity.VO.RecordHistory;
import com.yjjk.monitor.entity.VO.TemperatureBoundVO;
import com.yjjk.monitor.entity.VO.monitor.MonitorBaseVO;
import com.yjjk.monitor.entity.VO.monitor.MonitorVO;
import com.yjjk.monitor.entity.pojo.PatientInfo;
import com.yjjk.monitor.entity.pojo.TemperatureBound;
import com.yjjk.monitor.utility.ResultUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * @author CentreS
 * @Description: 监控管理模块
 * @create 2019/8/23
 */
@RestController
@RequestMapping("monitor")
public class MonitorController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MonitorController.class);

    /****************************** 启用设备 ******************************/

    @ApiOperation(value = "获取病人信息")
    @RequestMapping(value = "/patient", method = RequestMethod.GET)
    public synchronized CommonResult checkPatient(@ApiParam(value = "床位号", required = true) @RequestParam("bedId") Integer bedId) {
        try {
            PatientInfo patientInfo = super.patientService.getPatientInfo(bedId);
            return ResultUtil.returnSuccess(patientInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.returnError(ErrorCodeEnum.UNKNOWN_ERROR);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @ApiOperation(value = "启用设备")
    @RequestMapping(value = "/start", method = RequestMethod.POST)
    public synchronized CommonResult startTemperatureMachine(StartBO startBO, String token) {
        try {
            // 获取患者id (检验、查询\新增)
            Integer patientId = super.patientService.checkPatient(startBO.getPatientName(), startBO.getCaseNum(), startBO.getBedId());
            if (patientId == null) {
                ResultUtil.returnError(ErrorCodeEnum.EXIST_RECORD);
            }
            return super.monitorService.startMachine(startBO.getType(), startBO.getMachineId(), startBO.getBedId(), patientId, token);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.returnError(ErrorCodeEnum.UNKNOWN_ERROR);
        }
    }


    /****************************** 更换设备、床位 ******************************/
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @ApiOperation("更换设备")
    @RequestMapping(value = "/changeMachine", method = RequestMethod.PUT)
    public CommonResult changeTemperatureMachine(@RequestParam(value = "baseId") Integer baseId,
                                                 @ApiParam(value = "启用类型： 0-体温 1-心电 2-血氧 3-离床感应") Integer type,
                                                 @RequestParam(value = "machineId") Integer machineId, String token) {
        try {
            return super.monitorService.changeMachine(baseId, type, machineId, token);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.returnError(ErrorCodeEnum.UNKNOWN_ERROR);
        }
    }


    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @ApiOperation("更换床位")
    @RequestMapping(value = {"/bed"}, method = {org.springframework.web.bind.annotation.RequestMethod.PUT})
    public synchronized CommonResult changeBed(@ApiParam(value = "新床位号", required = true) @RequestParam("newBedId") Integer newBedId,
                                               @ApiParam(value = "现床位号", required = true) @RequestParam("currentBedId") Integer currentBedId, String token) {
        try {
            return super.monitorService.changeBed(currentBedId, newBedId, token);
        } catch (Exception e) {
            LOGGER.error("业务异常信息：[{}]", e.getMessage(), e);
            return ResultUtil.returnError(ErrorCodeEnum.UNKNOWN_ERROR);
        }
    }

    /****************************** 停止监测 ******************************/
    @ApiOperation("停止监测")
    @RequestMapping(value = "/record", method = RequestMethod.PUT)
    public CommonResult stopRecord(@RequestParam(value = "baseId") Integer baseId,
                                   @ApiParam(value = "启用类型： 0-体温 1-心电 2-血氧 3-离床感应") Integer type, String token) {
        /********************** 参数初始化 **********************/
        try {
            return super.monitorService.stopMachine(baseId, type, token);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.returnError(ErrorCodeEnum.UNKNOWN_ERROR);
        }
    }


    /**
     * 获取监控信息
     */
    @ApiOperation("获取监控信息")
    @RequestMapping(value = "/monitor", method = RequestMethod.GET)
    public CommonResult<MonitorVO> getMonitors(@ApiParam(value = "科室id", required = true) @RequestParam(value = "departmentId") Integer departmentId) {
        try {
            MonitorVO monitorVO = new MonitorVO();
            List<MonitorBaseVO> monitors = super.monitorService.getMonitors(departmentId);
            monitorVO.setMonitorVOList(monitors).setMachineTypeList(MachineEnum.getListVO());
            monitorVO = super.monitorService.setMonitorRule(monitorVO, departmentId);
            monitorVO = super.monitorService.setMachineState(monitorVO);
            return ResultUtil.returnSuccess(monitorVO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.returnError(ErrorCodeEnum.UNKNOWN_ERROR);
        }
    }


    /**
     * 查询历史记录
     *
     * @param recordHistory
     */
    @RequestMapping(value = "/record", method = RequestMethod.GET)
    public CommonResult getRecordHistory(RecordHistory recordHistory) {
        try {
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.returnError(ErrorCodeEnum.UNKNOWN_ERROR);
        }
        return ResultUtil.returnSuccess("");
    }

    /**
     * 查询体温历史记录
     *
     * @param recordId
     */
    @RequestMapping(value = "/temperature", method = RequestMethod.GET)
    public CommonResult getTemperatureHistory(@RequestParam(value = "recordId") Long recordId) {
        /********************** 参数初始化 **********************/
        try {
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.returnError(ErrorCodeEnum.UNKNOWN_ERROR);
        }
        return ResultUtil.returnSuccess("");
    }

    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void export(@RequestParam(value = "timeList") List<String> timeList,
                       @ApiParam(value = "语言 0：中文 1：英文", required = true) @RequestParam(value = "language") Integer language,
                       @RequestParam(value = "token") String token,
                       HttpServletResponse response) throws IOException {
    }

    @RequestMapping(value = "/privateExport", method = RequestMethod.GET)
    public void privateExport(@ApiParam(value = "筛选规则，筛选大于该摄氏度的体温") @RequestParam(value = "temperature", required = false) Double temperature,
                              @ApiParam(value = "语言 0：中文 1：英文", required = true) @RequestParam(value = "language") Integer language,
                              @ApiParam(value = "recordId", required = true) @RequestParam(value = "recordId") Integer recordId,
                              HttpServletResponse response) throws IOException {
    }

    //    @ApiOperation("设置体温监测规则")
    @RequestMapping(value = "/bound", method = RequestMethod.PUT)
    public CommonResult setTemperatureAlert(@Valid TemperatureBound param) {
        try {

        } catch (Exception e) {
            LOGGER.error("业务异常信息：[{}]", e.getMessage(), e);
            return ResultUtil.returnError(ErrorCodeEnum.UNKNOWN_ERROR);
        }
        return ResultUtil.returnSuccess("");
    }

    //    @ApiOperation("获取默认体温监测规则")
    @RequestMapping(value = "/bound", method = RequestMethod.GET)
    public CommonResult<List<TemperatureBoundVO>> getDefaultAlert(@RequestParam(value = "departmentId") Integer departmentId) {
        try {
        } catch (Exception e) {
            LOGGER.error("业务异常信息：[{}]", e.getMessage(), e);
            return ResultUtil.returnError(ErrorCodeEnum.UNKNOWN_ERROR);
        }
        return ResultUtil.returnSuccess(null);
    }


}
