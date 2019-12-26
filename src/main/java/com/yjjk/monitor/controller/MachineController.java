/**
 * Copyright (C), 2019, 义金(杭州)健康科技有限公司
 * FileName: MachineController
 * Author:   CentreS
 * Date:     2019/7/18 11:34
 * Description: 设备管理
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.monitor.controller;

import com.yjjk.monitor.configer.CommonResult;
import com.yjjk.monitor.configer.ErrorCodeEnum;
import com.yjjk.monitor.constant.MachineConstant;
import com.yjjk.monitor.entity.VO.SearchMachineVOBase;
import com.yjjk.monitor.entity.ZsMachineInfo;
import com.yjjk.monitor.entity.ZsMachineTypeInfo;
import com.yjjk.monitor.entity.export.MachineExportVO;
import com.yjjk.monitor.utility.ExcelUtils;
import com.yjjk.monitor.utility.ResultUtil;
import com.yjjk.monitor.utility.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CentreS
 * @Description: 设备管理
 * @create 2019/7/18
 */
@RestController
@RequestMapping("/machine")
@Api(tags = {"设备管理"})
public class MachineController extends BaseController {

    /**
     * 新增设备
     *
     * @param machineInfo
     */
    @ApiOperation(value = "新增设备")
    @RequestMapping(value = "/machine", method = RequestMethod.POST)
    public CommonResult addMachine(ZsMachineInfo machineInfo) {
        /********************** 参数初始化 **********************/
        try {
            boolean b = super.machineService.connectionService(machineInfo.getMachineNum());
            if (!b) {
                return ResultUtil.returnError("网络堵塞，设备绑定失败，请稍后再试");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.returnError("网络堵塞，设备绑定失败，请稍后再试");
        }
        int count = super.machineService.selectByMachineNum(machineInfo.getMachineNum());
        int count2 = super.machineService.selectByMachineNo(machineInfo.getMachineNo());
        if (count > 0 || count2 > 0) {
            return ResultUtil.returnError("该设备信息已存在，请核实后录入");
        }
        int i = super.machineService.insertByMachineNum(machineInfo);
        if (i == 0) {
            return ResultUtil.returnError("设备新增失败");
        }
        return ResultUtil.returnSuccess(i);
    }

    /**
     * 停用设备
     *
     * @param machineId
     * @param remark
     * @param request
     * @param response
     */
    @RequestMapping(value = "/machine", method = RequestMethod.DELETE)
    public void updateMachine(@RequestParam(value = "machineId") Integer machineId,
                              @RequestParam(value = "remark", required = false) String remark,
                              HttpServletRequest request, HttpServletResponse response) {
        /********************** 参数初始化 **********************/
        long startTime = System.currentTimeMillis();
        boolean resultCode = false;
        String message = "";
        ZsMachineInfo zsMachineInfo = super.machineService.selectByPrimaryKey(machineId);
        // 使用中设备
        if (zsMachineInfo.getUsageState() == 2) {
            message = "停用失败,设备正在使用中";
            returnResult(startTime, request, response, resultCode, message, "");
            return;
        }
        int i = super.machineService.deleteMachine(machineId, remark);
        if (i == 0) {
            message = "设备停用失败";
            returnResult(startTime, request, response, resultCode, message, i);
            return;
        }
        message = "设备停用成功";
        resultCode = true;
        returnResult(startTime, request, response, resultCode, message, i);
    }

    /**
     * 获取设备信息
     *
     * @param usageState
     * @param currentPage
     * @param pageSize
     * @param request
     * @param response
     */
    @ApiOperation(value = "获取设备信息")
    @RequestMapping(value = "/machine", method = RequestMethod.GET)
    public void updateMachine(@RequestParam(value = "usageState", required = false) Integer usageState,
                              @RequestParam(value = "departmentId", required = false) Integer departmentId,
                              @RequestParam(value = "currentPage", required = false) Integer currentPage,
                              @RequestParam(value = "pageSize", required = false) Integer pageSize,
                              HttpServletRequest request, HttpServletResponse response) {
        /********************** 参数初始化 **********************/
        long startTime = System.currentTimeMillis();
        boolean resultCode = false;
        String message = "";
        Map<String, Object> map = new HashMap<>();
        ZsMachineInfo machineInfo = new ZsMachineInfo();
        // 设备检索条件
        if (usageState != null) {
            if (usageState == 0) {
                machineInfo.setUnUsedStatus("unUsed");
            } else if (usageState == 1) {
                machineInfo.setDeleteStatus("delete");
            } else if (usageState == 2) {
                machineInfo.setNormalStatus("normal");
            } else if (usageState > 2) {
                message = "参数错误";
                returnResult(startTime, request, response, resultCode, message, map);
                return;
            }
        }

        machineInfo.setDepartmentId(departmentId);
        if (!StringUtils.isNullorEmpty(currentPage) && !StringUtils.isNullorEmpty(pageSize)) {
            // 查询总条数
            int totalCount = super.machineService.selectCount(machineInfo);
            // 分页必须信息
            int startLine = (currentPage - 1) * (pageSize);
            // 计算总页数
            int totalPage = (totalCount + pageSize - 1) / pageSize;
            machineInfo.setStartLine(startLine);
            machineInfo.setPageSize(pageSize);
            map.put("totalPage", totalPage);
            map.put("currentPage", currentPage);
        }

        List<ZsMachineInfo> list = super.machineService.selectByUsageState(machineInfo);
        map.put("list", list == null ? "" : list);
        message = "查询成功";
        resultCode = true;
        returnResult(startTime, request, response, resultCode, message, map);
    }

    /**
     * 设备导出
     *
     * @param usageState
     * @param departmentId
     * @param request
     * @param response
     */
    @ApiOperation(value = "设备信息导出")
    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void export(@RequestParam(value = "usageState", required = false) Integer usageState,
                       @RequestParam(value = "departmentId", required = false) Integer departmentId,
                       HttpServletRequest request, HttpServletResponse response) {
        /********************** 参数初始化 **********************/
        ZsMachineInfo machineInfo = new ZsMachineInfo();
        // 设备检索条件
        if (usageState != null) {
            if (usageState == 0) {
                machineInfo.setUnUsedStatus("unUsed");
            } else if (usageState == 1) {
                machineInfo.setDeleteStatus("delete");
            } else if (usageState == 2) {
                machineInfo.setNormalStatus("normal");
            } else if (usageState > 2) {
                ResultUtil.returnError(ErrorCodeEnum.PARAM_ERROR);
            }
        }
        machineInfo.setDepartmentId(departmentId);
        List<MachineExportVO> list = super.machineService.export(machineInfo);
        try {
            ExcelUtils.exportExcel(response, list, "MachineList", MachineConstant.EXPORT);
        } catch (IOException e) {
            e.printStackTrace();
            ResultUtil.returnError(ErrorCodeEnum.UNKNOWN_ERROR);
        }
    }

    @ApiOperation(value = "获取所有设备")
    @RequestMapping(value = "/selectAllMachine", method = RequestMethod.GET)
    public CommonResult selectAllMachine(@RequestParam Integer departmentId,
                                         @ApiParam(value = "设备类型id", required = true) @RequestParam Integer machineTypeId) {
        try {
            /********************** 参数初始化 **********************/
            Map<String, Object> paraMap = new HashMap<>();
            paraMap.put("departmentId", departmentId);
            paraMap.put("machineType", machineTypeId);
            List<ZsMachineInfo> list = super.machineService.selectAllMachines(paraMap);
            return ResultUtil.returnSuccess(list);
        } catch (Exception e) {
            return ResultUtil.returnError(ErrorCodeEnum.UNKNOWN_ERROR);
        }
    }

    @ApiOperation(value = "查找设备")
    @RequestMapping(value = "/searchMachine", method = RequestMethod.GET)
    public CommonResult<SearchMachineVOBase> searchMachine(@RequestParam Integer departmentId,
                                                           @ApiParam(value = "设备id", required = true) @RequestParam Integer machineId) {
        try {
            /********************** 参数初始化 **********************/
            Map<String, Object> paraMap = new HashMap<>();
            paraMap.put("departmentId", departmentId);
            paraMap.put("machineId", machineId);
            CommonResult commonResult = super.machineService.searchMachine(paraMap);
            return commonResult;
        } catch (Exception e) {
            return ResultUtil.returnError(ErrorCodeEnum.UNKNOWN_ERROR);
        }
    }

    /**
     * 获取设备名称
     *
     * @return
     */
    @ApiOperation(value = "获取设备名称")
    @RequestMapping(value = "/temperatureMachineName", method = RequestMethod.GET)
    public CommonResult getTemperatureMachineName() {
        try {
            /********************** 参数初始化 **********************/
            List<ZsMachineTypeInfo> list = super.machineService.getTemperatureMachineName();
            return ResultUtil.returnSuccess(list);
        } catch (Exception e) {
            return ResultUtil.returnError(ErrorCodeEnum.UNKNOWN_ERROR);
        }

    }
}
