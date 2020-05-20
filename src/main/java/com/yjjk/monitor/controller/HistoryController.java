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
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CentreS
 * @Description: 历史记录模块
 * @create 2019/8/23
 */
@RestController
@RequestMapping("history")
public class HistoryController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HistoryController.class);

    /****************************** 启用设备 ******************************/
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation(value = "启用体温设备")
    @RequestMapping(value = "/startTemperature", method = RequestMethod.POST)
    public synchronized CommonResult startTemperatureMachine() {
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultUtil.returnSuccess("");
    }

}
