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
import com.yjjk.monitor.entity.ListVO;
import com.yjjk.monitor.entity.VO.SearchMachineVOBase;
import com.yjjk.monitor.entity.export.machine.MachineExportVO;
import com.yjjk.monitor.entity.pojo.MachineTypeInfo;
import com.yjjk.monitor.entity.pojo.ZsMachineInfo;
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
    @ApiOperation(value = "page设备管理：新增设备")
    @RequestMapping(value = "/machine", method = RequestMethod.POST)
    public CommonResult addMachine(ZsMachineInfo machineInfo) {
        /********************** 参数初始化 **********************/
        int count = super.machineService.selectByMachineNum(machineInfo.getMachineNum());
        int count2 = super.machineService.selectByMachineNo(machineInfo.getMachineNo());
        if (count > 0 || count2 > 0) {
            return ResultUtil.returnError(ErrorCodeEnum.MACHINE_EXIST_ERROR);
        }
        int i = super.machineService.insertByMachineNum(machineInfo);
        if (i == 0) {
            return ResultUtil.returnError(ErrorCodeEnum.MACHINE_INSERT_ERROR);
        } else {
            try {
                boolean b = super.machineService.connectionService(machineInfo.getMachineNum());
                if (!b) {
                    return ResultUtil.returnError(ErrorCodeEnum.MACHINE_NET_ERROR);
                }
            } catch (Exception e) {
                e.printStackTrace();
                return ResultUtil.returnError(ErrorCodeEnum.MACHINE_NET_ERROR);
            }
        }
        return ResultUtil.returnSuccess(i);
    }

    /**
     * 停用设备
     *
     * @param machineId
     * @param remark
     */
    @ApiOperation(value = "page设备管理：停用设备")
    @RequestMapping(value = "/machine", method = RequestMethod.DELETE)
    public CommonResult updateMachine(@RequestParam(value = "machineId") Integer machineId,
                                      @RequestParam(value = "remark", required = false) String remark) {
        /********************** 参数初始化 **********************/
        ZsMachineInfo zsMachineInfo = super.machineService.selectByPrimaryKey(machineId);
        // 使用中设备
        if (zsMachineInfo.getUsageState() == 2) {
            return ResultUtil.returnError(ErrorCodeEnum.MACHINE_USING_ERROR);
        }
        int i = super.machineService.deleteMachine(machineId, remark);
        if (i == 0) {
            return ResultUtil.returnError(ErrorCodeEnum.MACHINE_STOP_ERROR);
        }
        return ResultUtil.returnSuccess(i);
    }

    /**
     * 获取设备信息
     *
     * @param usageState
     * @param currentPage
     * @param pageSize
     */
    @ApiOperation(value = "page设备管理：获取设备信息")
    @RequestMapping(value = "/machine", method = RequestMethod.GET)
    public CommonResult getMachine(@ApiParam(value = "0-未使用 1-已删除 2-使用+未使用") @RequestParam(value = "usageState", required = false) Integer usageState,
                                   @ApiParam(value = "machineTypeId") @RequestParam(value = "machineTypeId", required = false) Integer machineTypeId,
                                   @RequestParam(value = "departmentId", required = false) Integer departmentId,
                                   @RequestParam(value = "machineNum", required = false) String machineNum,
                                   @RequestParam(value = "page", required = false) Integer currentPage,
                                   @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        /********************** 参数初始化 **********************/
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
                return ResultUtil.returnError(ErrorCodeEnum.PARAM_ERROR);
            }
        }

        machineInfo.setMachineTypeId(machineTypeId);
        machineInfo.setDepartmentId(departmentId);
        machineInfo.setMachineNum(StringUtils.getLikeName(machineNum));
        if (!StringUtils.isNullorEmpty(currentPage) && !StringUtils.isNullorEmpty(pageSize)) {
            // 查询总条数
            int totalCount = super.machineService.selectCount(machineInfo);
            // 分页必须信息
            int startLine = (currentPage - 1) * (pageSize);
            // 计算总页数
            int totalPage = (totalCount + pageSize - 1) / pageSize;
            machineInfo.setStartLine(startLine);
            machineInfo.setPageSize(pageSize);
            map.put("total", totalPage);
            map.put("page", currentPage);
            map.put("records", totalCount);
        }

        List<ZsMachineInfo> list = super.machineService.selectByUsageState(machineInfo);
        map.put("list", list == null ? "" : list);
        return ResultUtil.returnSuccess(map);
    }

    @ApiOperation(value = "page监控模块-list：获取设备信息")
    @RequestMapping(value = "/machineList", method = RequestMethod.GET)
    public CommonResult getMachineList(@ApiParam(value = "模糊查询") @RequestParam(value = "name", required = false) String name,
                                       @RequestParam(value = "departmentId", required = false) Integer departmentId,
                                       @ApiParam(value = "设备类型：名称", required = true) @RequestParam(value = "machineTypeId") Integer machineTypeId) {
        /********************** 参数初始化 **********************/
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("departmentId", departmentId);
        map.put("machineTypeId", machineTypeId);
        List<ListVO> list = super.machineService.selectUsageListByTypeId(map);
        return ResultUtil.returnSuccess(list);
    }

    @ApiOperation(value = "page设备模块-list：获取设备信息")
    @RequestMapping(value = "/machineListNo", method = RequestMethod.GET)
    public CommonResult getMachineListMachineModel(
            @ApiParam(value = "设备类型：名称", required = true) @RequestParam(value = "machineTypeId") Integer machineTypeId) {
        /********************** 参数初始化 **********************/
        Map<String, Object> map = new HashMap<>();
        map.put("machineTypeId", machineTypeId);
        List<ListVO> list = super.machineService.selectUsageListByTypeIdMachineModel(map);
        return ResultUtil.returnSuccess(list);
    }

    /**
     * 设备导出
     *
     * @param usageState
     * @param departmentId
     * @param request
     * @param response
     */
    @ApiOperation(value = "page设备管理：设备信息导出")
    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void export(@RequestParam(value = "usageState", required = false) Integer usageState,
                       @RequestParam(value = "departmentId", required = false) Integer departmentId,
                       @ApiParam(value = "machineTypeId") @RequestParam(value = "machineTypeId", required = false) Integer machineTypeId,
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
        machineInfo.setMachineTypeId(machineTypeId);
        List<MachineExportVO> list = super.machineService.export(machineInfo);
        try {
            ExcelUtils.exportExcel(response, list, "MachineList", MachineConstant.EXPORT);
        } catch (IOException e) {
            e.printStackTrace();
            ResultUtil.returnError(ErrorCodeEnum.UNKNOWN_ERROR);
        }
    }

    @ApiOperation(value = "page设备管理：获取所有设备")
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
            e.printStackTrace();
            return ResultUtil.returnError(ErrorCodeEnum.UNKNOWN_ERROR);
        }
    }

    @ApiOperation(value = "page设备管理：查找设备")
    @RequestMapping(value = "/searchMachine", method = RequestMethod.GET)
    public CommonResult<SearchMachineVOBase> searchMachine(@RequestParam Integer departmentId,
                                                           @ApiParam(value = "设备id", required = true) @RequestParam Integer machineId,
                                                           @ApiParam(value = "类型： 0-体温 1-心电 2-血氧 3-离床感应") @RequestParam(value = "type") Integer type) {
        try {
            /********************** 参数初始化 **********************/
            Map<String, Object> paraMap = new HashMap<>();
            paraMap.put("departmentId", departmentId);
            paraMap.put("machineId", machineId);
            paraMap.put("type", type);
            CommonResult commonResult = super.machineService.searchMachine(paraMap);
            return commonResult;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.returnError(ErrorCodeEnum.UNKNOWN_ERROR);
        }
    }

    /**
     * 获取设备类型
     *
     * @return
     */
    @ApiOperation(value = "page设备管理-list：获取设备名称")
    @RequestMapping(value = "/machineName", method = RequestMethod.GET)
    public CommonResult<List<MachineTypeInfo>> getTemperatureMachineName() {
        try {
            /********************** 参数初始化 **********************/
            List<MachineTypeInfo> list = super.machineService.getTemperatureMachineName();
            return ResultUtil.returnSuccess(list);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.returnError(ErrorCodeEnum.UNKNOWN_ERROR);
        }
    }

    /**
     * 获取设备类型
     *
     * @return
     */
    @ApiOperation(value = "page设备管理-list：获取设备型号")
    @RequestMapping(value = "/machineModel", method = RequestMethod.GET)
    public CommonResult getMachineModel(@ApiParam(value = "设备类型id", required = true) @RequestParam Integer machineTypeId) {
        try {
            /********************** 参数初始化 **********************/
            List<MachineTypeInfo> list = super.machineService.getMachineModel(machineTypeId);
            return ResultUtil.returnSuccess(list);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.returnError(ErrorCodeEnum.UNKNOWN_ERROR);
        }
    }

}
