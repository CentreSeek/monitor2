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
import com.yjjk.monitor.entity.BO.monitor.MonitorRuleBOData;
import com.yjjk.monitor.entity.BO.monitor.StartBO;
import com.yjjk.monitor.entity.VO.monitor.MachineTypeListVO;
import com.yjjk.monitor.entity.VO.monitor.MonitorBaseVO;
import com.yjjk.monitor.entity.VO.monitor.MonitorVO;
import com.yjjk.monitor.entity.pojo.MonitorRule;
import com.yjjk.monitor.entity.pojo.PatientInfo;
import com.yjjk.monitor.utility.DateUtil;
import com.yjjk.monitor.utility.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author CentreS
 * @Description: 监控管理模块
 * @create 2019/8/23
 */
@RestController
@RequestMapping("monitor")
@Api(tags = {"监控模块"})
public class MonitorController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MonitorController.class);

    /****************************** 启用设备 ******************************/
    @ApiOperation(value = "获取病人信息")
    @RequestMapping(value = "/patient", method = RequestMethod.GET)
    public synchronized CommonResult checkPatient(@ApiParam(value = "床位号", required = true) @RequestParam("bedId") Integer bedId) {
        PatientInfo patientInfo = super.patientService.getPatientInfo(bedId);
        return ResultUtil.returnSuccess(patientInfo);
    }

//    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @ApiOperation(value = "启用设备")
    @RequestMapping(value = "/start", method = RequestMethod.POST)
    public synchronized CommonResult startMachine(@Valid StartBO startBO, HttpServletRequest request) throws Exception {
//        try {
            // 获取患者id (检验、查询\新增)
            Integer patientId = super.patientService.checkPatient(startBO.getPatientName(), startBO.getCaseNum(), startBO.getBedId());
            if (patientId == null) {
                return ResultUtil.returnError(ErrorCodeEnum.EXIST_RECORD);
            }
            return super.monitorService.startMachine(startBO.getType(), startBO.getMachineId(), startBO.getBedId(), patientId, request.getHeader("token"));
//        } catch (ConnectException c) {
//            c.printStackTrace();
//            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//            logger.error("业务异常信息：[{}]", c.getMessage(), c);
//            return ResultUtil.returnError(ErrorCodeEnum.ERROR_CONNECT_DATA_SERVICE);
//        } catch (Exception e) {
//            e.printStackTrace();
//            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//            logger.error("业务异常信息：[{}]", e.getMessage(), e);
//            return ResultUtil.returnError(ErrorCodeEnum.UNKNOWN_ERROR);
//        }
    }


    /****************************** 更换设备、床位 ******************************/
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @ApiOperation("更换设备")
    @RequestMapping(value = "/changeMachine", method = RequestMethod.PUT)
    public CommonResult changeTemperatureMachine(@RequestParam(value = "baseId") Integer baseId,
                                                 @ApiParam(value = "启用类型： 0-体温 1-心电 2-血氧 3-离床感应") Integer type,
                                                 @ApiParam(value = "新设备id") @RequestParam(value = "machineId") Integer machineId, HttpServletRequest request) {
        try {
            return super.monitorService.changeMachine(baseId, type, machineId, request.getHeader("token"));
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("业务异常信息：[{}]", e.getMessage(), e);
            return ResultUtil.returnError(ErrorCodeEnum.UNKNOWN_ERROR);
        }
    }


    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @ApiOperation("更换床位")
    @RequestMapping(value = {"/bed"}, method = {org.springframework.web.bind.annotation.RequestMethod.PUT})
    public synchronized CommonResult changeBed(@ApiParam(value = "新床位号", required = true) @RequestParam("newBedId") Integer newBedId,
                                               @ApiParam(value = "现床位号", required = true) @RequestParam("currentBedId") Integer currentBedId, HttpServletRequest request) {
        return super.monitorService.changeBed(currentBedId, newBedId, request.getHeader("token"));
    }

    /****************************** 停止监测 ******************************/
//    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @ApiOperation("停止监测")
    @RequestMapping(value = "/record", method = RequestMethod.PUT)
    public CommonResult stopRecord(@RequestParam(value = "baseId") Integer baseId,
                                   @ApiParam(value = "启用类型： 0-体温 1-心电 2-血氧 3-离床感应") @RequestParam(value = "type") Integer type, HttpServletRequest request) throws Exception {
        /********************** 参数初始化 **********************/
        return super.monitorService.stopMachine(baseId, type, request.getHeader("token"));
    }

    @ApiOperation("获取服务器时间")
    @RequestMapping(value = "/time", method = RequestMethod.GET)
    public CommonResult getTime() {
        /********************** 参数初始化 **********************/
        return ResultUtil.returnSuccess(DateUtil.getCurrentTimeLong());
    }


    /**
     * 获取监控信息
     */
    @ApiOperation("获取监控信息")
    @RequestMapping(value = "/monitor", method = RequestMethod.GET)
    public CommonResult<MonitorVO> getMonitors(@ApiParam(value = "科室id", required = true) @NotNull @RequestParam(value = "departmentId") Integer departmentId,
                                               @ApiParam(value = "起始床位id") @RequestParam(value = "start", required = false) Integer start,
                                               @ApiParam(value = "结束床位id") @RequestParam(value = "end", required = false) Integer end) {
        MonitorVO monitorVO = new MonitorVO();
        List<MonitorBaseVO> monitors = super.monitorService.getMonitors(departmentId, start, end);
        List<MachineTypeListVO> list = super.machineService.getMonitorTypeList(departmentId);
        monitorVO.setMonitorVOList(monitors).setMachineTypeList(list);
        monitorVO = super.monitorService.setMonitorRule(monitorVO, departmentId);
        monitorVO = super.monitorService.setMachineState(monitorVO);
        monitorVO.setBedCount(super.hospitalService.getBedCount(departmentId, start, end));
        return ResultUtil.returnSuccess(monitorVO);
    }

    @ApiOperation("设置监测规则")
    @RequestMapping(value = "/setRule", method = RequestMethod.POST)
//    @PostMapping("/setRule")
//    @ResponseBody
    public CommonResult setMonitorRule(@RequestBody MonitorRuleBOData data, HttpServletRequest request) {
        super.monitorRuleService.setMonitorRule(data.getList(), request.getHeader("token"));
        return ResultUtil.returnSuccess("");
    }

    @ApiOperation("获取默认监测规则")
    @RequestMapping(value = "/rule", method = RequestMethod.GET)
    public CommonResult<List<MonitorRule>> getRule(@ApiParam(value = "科室id，值为-1获取默认规则", required = true) @RequestParam(value = "departmentId") Integer departmentId) {
        List<MonitorRule> monitorRule = super.monitorRuleService.getMonitorRule(departmentId);
        return ResultUtil.returnSuccess(monitorRule);
    }
//    @Autowired
//    RecordTemperature recordTemperature;
//    @RequestMapping(value = "/test", method = RequestMethod.GET)
//    public CommonResult test() {
//        /********************** 参数初始化 **********************/
//        TemperatureHistory temperatureHistory = new TemperatureHistory();
//
//        recordTemperature.setHistory()
//        return ResultUtil.returnSuccess(list);
//    }


}
