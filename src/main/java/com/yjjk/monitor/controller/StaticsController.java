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
import com.yjjk.monitor.utility.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author CentreS
 * @Description: 统计管理
 * @create 2019/8/23
 */
@RestController
@RequestMapping("statics")
@Api(tags = {"统计管理"})
public class StaticsController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StaticsController.class);

    @ApiOperation("使用设备数量")
    @RequestMapping(value = "/machine", method = RequestMethod.GET)
    public CommonResult<Map<String, Integer>> useMachines(@ApiParam(value = "科室id") @RequestParam(value = "departmentId", required = false) Integer departmentId,
                                                          @ApiParam(value = "起始日期", example = "2020-08-05") @RequestParam(value = "start") String start,
                                                          @ApiParam(value = "终止日期", example = "2020-08-06") @RequestParam(value = "end") String end,
                                                          @ApiParam(value = "启用类型： 0-体温 1-心电 2-血氧 3-离床感应") @RequestParam(value = "type") Integer type) {
        Map<String, Integer> machineStatics = super.staticsService.getMachineStatics(departmentId, type, start, end);
        return ResultUtil.returnSuccess(machineStatics);
    }

    @ApiOperation("科室使用人数排行")
    @RequestMapping(value = "/department", method = RequestMethod.GET)
    public CommonResult<Map<String, Integer>> usePeoples(@ApiParam(value = "起始日期", example = "2020-08-05") @RequestParam(value = "start") String start,
                                                         @ApiParam(value = "终止日期", example = "2020-08-06") @RequestParam(value = "end") String end,
                                                         @ApiParam(value = "启用类型： 0-体温 1-心电 2-血氧 3-离床感应") @RequestParam(value = "type") Integer type) {
        Map<String, Integer> stringIntegerMap = super.staticsService.usePeoples(type, start, end);
        return ResultUtil.returnSuccess(stringIntegerMap);
    }

    @ApiOperation("最近30天不同监测时长分布")
    @RequestMapping(value = "/monitor", method = RequestMethod.GET)
    public CommonResult<Map<String, Integer>> monitorPeriods(@ApiParam(value = "科室id") @RequestParam(value = "departmentId", required = false) Integer departmentId,
                                                             @ApiParam(value = "启用类型： 0-体温 1-心电 2-血氧 3-离床感应") @RequestParam(value = "type") Integer type) {
        Map<String, Integer> stringIntegerMap = super.staticsService.monitorPeriods(departmentId, type);
        return ResultUtil.returnSuccess(stringIntegerMap);
    }


}
