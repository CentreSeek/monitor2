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
import com.yjjk.monitor.entity.VO.monitor.StaticsRecordDepartmentVO;
import com.yjjk.monitor.utility.DataUtils;
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

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
    public CommonResult<String[][]> useMachines(@ApiParam(value = "科室id") @RequestParam(value = "departmentId", required = false) Integer departmentId,
                                                @ApiParam(value = "起始日期", example = "2020-08-05", required = true) @RequestParam(value = "start") String start,
                                                @ApiParam(value = "终止日期", example = "2020-08-06", required = true) @RequestParam(value = "end") String end,
                                                @NotNull @ApiParam(value = "启用类型： 0-体温 1-心电 2-血氧 3-离床感应", required = true) @RequestParam(value = "type") Integer type) {
        Map<String, Integer> machineStatics = super.staticsService.getMachineStatics(departmentId, type, start, end);
        return ResultUtil.returnSuccess(DataUtils.map2Arr(machineStatics));
    }

    @ApiOperation("科室使用人数排行")
    @RequestMapping(value = "/department", method = RequestMethod.GET)
    public CommonResult<List<StaticsRecordDepartmentVO>> usePeoples(@ApiParam(value = "起始日期", example = "2020-08-05", required = true) @RequestParam(value = "start") String start,
                                                                    @ApiParam(value = "终止日期", example = "2020-08-06", required = true) @RequestParam(value = "end") String end,
                                                                    @NotNull @ApiParam(value = "启用类型： 0-体温 1-心电 2-血氧 3-离床感应", required = true) @RequestParam(value = "type") Integer type) {
        Map<String, Integer> map = super.staticsService.usePeoples(type, start, end);
        List<StaticsRecordDepartmentVO> list = new ArrayList<>();
        for (String s : map.keySet()) {
            StaticsRecordDepartmentVO pojo = new StaticsRecordDepartmentVO();
            pojo.setDepartmentName(s);
            pojo.setEquipmentQuantity(map.get(s));
            list.add(pojo);
        }
        Collections.sort(list);
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setRank(i + 1);
        }
        return ResultUtil.returnSuccess(list);
    }

    @ApiOperation("最近30天不同监测时长分布")
    @RequestMapping(value = "/monitor", method = RequestMethod.GET)
    public CommonResult<String[][]> monitorPeriods(@ApiParam(value = "科室id") @RequestParam(value = "departmentId", required = false) Integer departmentId,
                                                   @NotNull @ApiParam(value = "启用类型： 0-体温 1-心电 2-血氧 3-离床感应", required = true) @RequestParam(value = "type") Integer type) {
        Map<String, Integer> map = super.staticsService.monitorPeriods(departmentId, type);
        return ResultUtil.returnSuccess(DataUtils.map2Arr(map));
    }


}
