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
import com.yjjk.monitor.entity.pojo.MachineTypeInfo;
import com.yjjk.monitor.entity.pojo.ZsRepeaterInfo;
import com.yjjk.monitor.utility.DateUtil;
import com.yjjk.monitor.utility.ResultUtil;
import com.yjjk.monitor.utility.StringUtils;
import io.swagger.annotations.Api;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CentreS
 * @Description: 中继器管理模块
 * @create 2019/8/23
 */
@RestController
@RequestMapping("repeater")
@Api(tags = {"路由管理模块"})
public class RepeaterController extends BaseController {


    /**
     * 查询设备信息
     */
    @RequestMapping(value = "/machineType", method = RequestMethod.GET)
    public CommonResult getMachineType() {
        /********************** 参数初始化 **********************/
        List<MachineTypeInfo> list = super.repeaterService.selectMachineTypes();
        return ResultUtil.returnSuccess(list);
    }

    /**
     * 查询设备型号
     *
     * @param id
     */
    @RequestMapping(value = "/machineNum", method = RequestMethod.GET)
    public CommonResult getMachineNum(@RequestParam(value = "id") Integer id) {
        /********************** 参数初始化 **********************/

        List<MachineTypeInfo> list = super.repeaterService.selectMachineNums(id);
        return ResultUtil.returnSuccess(list);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @RequestMapping(value = "/repeater", method = RequestMethod.POST)
    public CommonResult addRepeater(@RequestParam(value = "machineTypeId") Integer machineTypeId,
                                    @RequestParam(value = "mac") String mac,
                                    @RequestParam(value = "departmentId") Integer departmentId,
                                    @RequestParam(value = "roomId") Integer roomId,
                                    @RequestParam(value = "ip") String ip) {
        /********************** 参数初始化 **********************/
        try {
            int i = super.repeaterService.insertSelective(new ZsRepeaterInfo().setMachineTypeId(machineTypeId).setMac(mac).setDepartmentId(departmentId).setRoomId(roomId).setIp(ip));
            if (i == 0) {
                return ResultUtil.returnError(ErrorCodeEnum.REPEATER_ADD_ERROR);
            }
            boolean b = super.repeaterService.addRepeater();
            if (!b) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return ResultUtil.returnError(ErrorCodeEnum.ERROR_CONNECT_DATA_SERVICE);
            }
            return ResultUtil.returnSuccess(i);
        } catch (Exception e) {
            return ResultUtil.returnError(ErrorCodeEnum.UNKNOWN_ERROR);
        }
    }

    /**
     * 启用路由器
     *
     * @param zsRepeaterInfo
     * @param request
     * @param response
     */
    @RequestMapping(value = "/start", method = RequestMethod.PUT)
    public CommonResult startRepeater(ZsRepeaterInfo zsRepeaterInfo,
                                      HttpServletRequest request, HttpServletResponse response) {
        /********************** 参数初始化 **********************/
        if (StringUtils.isNullorEmpty(zsRepeaterInfo) || StringUtils.isNullorEmpty(zsRepeaterInfo.getId())) {
            return ResultUtil.returnError(ErrorCodeEnum.PARAM_ERROR);
        }

        int i = super.repeaterService.startRepeater(zsRepeaterInfo);
        return ResultUtil.returnSuccess(i);
    }


    /**
     * 停用路由器
     *
     * @param id
     * @param request
     * @param response
     */
    @RequestMapping(value = "/stop", method = RequestMethod.PUT)
    public CommonResult stopRepeater(@RequestParam(value = "id") Integer id,
                                     @RequestParam(value = "remark") String remark,
                                     HttpServletRequest request, HttpServletResponse response) {

        int i = super.repeaterService.stopRepeater(id, DateUtil.getCurrentTime() + remark);
        return ResultUtil.returnSuccess(i);
    }

    @RequestMapping(value = "/repeater", method = RequestMethod.GET)
    public CommonResult getRepeaters(@RequestParam(value = "departmentId", required = false) Integer departmentId,
                                     @RequestParam(value = "page", required = false) Integer currentPage,
                                     @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                     HttpServletRequest request, HttpServletResponse response) {
        /********************** 参数初始化 **********************/
        Map<String, Object> map = new HashMap<>();
        ZsRepeaterInfo repeaterInfo = new ZsRepeaterInfo();

        /********************** 分页 **********************/
        repeaterInfo.setDepartmentId(departmentId);
        if (!StringUtils.isNullorEmpty(currentPage) && !StringUtils.isNullorEmpty(pageSize)) {
            if (currentPage <= 0 || pageSize <= 0) {
                return ResultUtil.returnError(ErrorCodeEnum.PAGE_INFO_ERROR);
            }
            // 查询总条数
            int totalCount = super.repeaterService.selectRepeaterCount(repeaterInfo);
            // 分页必须信息
            int startLine = (currentPage - 1) * (pageSize);
            // 计算总页数
            int totalPage = (totalCount + pageSize - 1) / pageSize;
            repeaterInfo.setStartLine(startLine).setPageSize(pageSize);
            map.put("total", totalPage);
            map.put("page", currentPage);
            map.put("records", totalCount);
        }

        List<ZsRepeaterInfo> list = super.repeaterService.selectRepeaters(repeaterInfo);
        map.put("list", list);
        return ResultUtil.returnSuccess(map);
    }

}
