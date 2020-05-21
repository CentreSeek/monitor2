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
import com.yjjk.monitor.entity.BO.PageBO;
import com.yjjk.monitor.entity.BO.history.GetRecordsBO;
import com.yjjk.monitor.entity.VO.PagedGridResult;
import com.yjjk.monitor.entity.VO.history.RecordsHistory;
import com.yjjk.monitor.utility.ResultUtil;
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
import java.util.List;

/**
 * @author CentreS
 * @Description: 历史记录模块
 * @create 2019/8/23
 */
@RestController
@RequestMapping("history")
public class HistoryController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HistoryController.class);

    @ApiOperation("获取所有历史记录")
    @RequestMapping(value = "/record", method = RequestMethod.GET)
    public CommonResult<PagedGridResult<RecordsHistory>> getRecordHistory(@Valid PageBO pageBO, @Valid GetRecordsBO bo) {
        try {
            PagedGridResult history = super.historyService.getHistory(pageBO, bo);
            return ResultUtil.returnSuccess(history);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.returnError(ErrorCodeEnum.UNKNOWN_ERROR);
        }
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

}
