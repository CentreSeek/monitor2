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
import com.yjjk.monitor.constant.MachineConstant;
import com.yjjk.monitor.entity.BO.PageBO;
import com.yjjk.monitor.entity.BO.history.GetRecordsBO;
import com.yjjk.monitor.entity.VO.PagedGridResult;
import com.yjjk.monitor.entity.VO.history.RecordsHistory;
import com.yjjk.monitor.entity.history.BaseData;
import com.yjjk.monitor.entity.history.BloodHistory;
import com.yjjk.monitor.entity.history.EcgHistory;
import com.yjjk.monitor.entity.history.SleepingHistory;
import com.yjjk.monitor.entity.history.TemperatureHistory;
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

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author CentreS
 * @Description: 历史记录模块
 * @create 2019/8/23
 */
@RestController
@RequestMapping("history")
@Api(tags = {"历史记录模块"})
public class HistoryController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HistoryController.class);

    @ApiOperation("获取所有历史记录")
    @RequestMapping(value = "/records", method = RequestMethod.GET)
    public CommonResult<PagedGridResult<RecordsHistory>> getRecordHistory(@Valid PageBO pageBO, @Valid GetRecordsBO bo) {
        try {
            PagedGridResult history = super.historyService.getHistory(pageBO, bo);
            return ResultUtil.returnSuccess(history);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.returnError(ErrorCodeEnum.UNKNOWN_ERROR);
        }
    }

    @ApiOperation("page历史记录：查询历史记录")
    @RequestMapping(value = "/historyData", method = RequestMethod.GET)
    public CommonResult getHistoryData(@ApiParam(value = "启用类型： 0-体温 1-心电 2-血氧 3-离床感应") @RequestParam(value = "type") Integer type,
                                       @RequestParam(value = "recordId") Integer recordId) {
        /********************** 参数初始化 **********************/
        try {
            Object monitorHistory = null;
            if (recordId != -1) {
                monitorHistory = super.historyService.getHistoryData(type, recordId);
            }
            return ResultUtil.returnSuccess(monitorHistory);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.returnError(ErrorCodeEnum.UNKNOWN_ERROR);
        }
    }

    @ApiOperation("page监控页面：查询历史记录")
    @RequestMapping(value = "/monitorHistoryData", method = RequestMethod.GET)
    public CommonResult getMonitorHistoryData(@ApiParam(value = "查询类型： 0-体温 1-心率/呼吸率 2-血氧/PI") @RequestParam(value = "type") Integer type,
                                              @RequestParam(value = "baseId") Integer baseId) {
        /********************** 参数初始化 **********************/
        try {
            List<BaseData> list = super.historyService.getMonitorData(type, baseId);
            Collections.sort(list);
            return ResultUtil.returnSuccess(list);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.returnError(ErrorCodeEnum.UNKNOWN_ERROR);
        }
    }

    @ApiOperation("page历史记录：时间点拍批量导出")
    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void export(@ApiParam(value = "时间点：格式  00:00:00", required = true) @RequestParam(value = "timeList") List<String> timeList,
                       @ApiParam(value = "导出日期：格式  0000-00-00", required = true) @RequestParam(value = "date") String date,
                       @RequestParam(value = "departmentId") Integer departmentId,
                       @ApiParam(value = "启用类型： 0-体温 1-心电 2-血氧 3-离床感应") @RequestParam(value = "type") Integer type,
//                       @ApiParam(value = "语言 0：中文 1：英文", required = true) @RequestParam(value = "language") Integer language,
                       HttpServletResponse response) {
        try {
            List list = super.historyService.getExportHistoryList(type, departmentId, date, timeList);
            super.historyService.export(response, type, list);
        } catch (IOException e) {
            e.printStackTrace();
            ResultUtil.returnError(ErrorCodeEnum.UNKNOWN_ERROR);
        }
    }

    @ApiOperation("page历史记录：单人导出")
    @RequestMapping(value = "/privateExport", method = RequestMethod.GET)
    public void privateExport(@ApiParam(value = "筛选规则，筛选大于该摄氏度的体温") @RequestParam(value = "temperature", required = false) Double temperature,
                              @ApiParam(value = "recordId", required = true) @RequestParam(value = "recordId") Integer recordId,
                              @ApiParam(value = "启用类型： 0-体温 1-心电 2-血氧 3-离床感应") @RequestParam(value = "type") Integer type,
//                              @ApiParam(value = "语言 0：中文 1：英文", required = true) @RequestParam(value = "language") Integer language,
                              HttpServletResponse response) throws IOException {
        Object historyData = super.historyService.getHistoryData(type, recordId);
        List list = new ArrayList();
        if (type == MachineConstant.TEMPERATURE) {
            TemperatureHistory temperatureHistory = (TemperatureHistory) super.historyService.filterTemperatureData((TemperatureHistory) historyData, temperature);
            list = temperatureHistory.getHistory();

        }
        if (type == MachineConstant.ECG) {
            EcgHistory ecgHistory = (EcgHistory) historyData;
            list = ecgHistory.getHistory();
        }
        if (type == MachineConstant.BLOOD) {
            BloodHistory bloodHistory = (BloodHistory) historyData;
            list = bloodHistory.getHistory();
        }
        if (type == MachineConstant.SLEEPING) {
            SleepingHistory sleepingHistory = (SleepingHistory) historyData;
            list = sleepingHistory.getHistory();
        }
        super.historyService.export(response, type, list);

    }

}
