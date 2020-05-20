/**
 * Copyright (C), 2019, 义金(杭州)健康科技有限公司
 * FileName: HospitalController
 * Author:   CentreS
 * Date:     2019/7/18 16:52
 * Description: 医院信息管理
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.monitor.controller;

import com.yjjk.monitor.configer.CommonResult;
import com.yjjk.monitor.entity.pojo.HospitalBed;
import com.yjjk.monitor.entity.pojo.HospitalDepartment;
import com.yjjk.monitor.entity.pojo.HospitalRoom;
import com.yjjk.monitor.utility.ResultUtil;
import io.swagger.annotations.ApiOperation;
import javafx.geometry.HorizontalDirection;
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
 * @Description: 医院信息管理
 * @create 2019/7/18
 */
@RestController
@RequestMapping("/hospital")
public class HospitalController extends BaseController {


    /**
     * 查询科室信息
     * @param departmentId
     */
    @RequestMapping(value = "/department", method = RequestMethod.GET)
    public CommonResult selectDetail(@RequestParam(value = "departmentId", required = false) Integer departmentId) {
        /********************** 参数初始化 **********************/
        List<HospitalDepartment> list = super.hospitalService.selectDetail(departmentId);
        return ResultUtil.returnSuccess(list);
    }

    /**
     * 查询科室信息
     */
    @RequestMapping(value = "/departments", method = RequestMethod.GET)
    public CommonResult getDepartments() {
        /********************** 参数初始化 **********************/
        List<HospitalDepartment> list = super.hospitalService.selectDepartments();
        return ResultUtil.returnSuccess(list);
    }

    /**
     * 查询房间信息
     * @param departmentId
     */
    @RequestMapping(value = "/rooms", method = RequestMethod.GET)
    public CommonResult getRooms(@RequestParam(value = "departmentId") Integer departmentId) {
        /********************** 参数初始化 **********************/
        List<HospitalRoom> list = super.hospitalService.selectRooms(departmentId);
        return ResultUtil.returnSuccess(list);
    }

    @ApiOperation("获取空床位")
    @RequestMapping(value = {"/emptyBeds"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public CommonResult<List<HospitalBed>> getTemperatureEmptyBeds(@RequestParam("departmentId") Integer departmentId) {
        Map<String, Object> paraMap = new HashMap();
        paraMap.put("departmentId", departmentId);
        List<HospitalBed> HospitalBeds = this.hospitalService.selectEmptyBeds(paraMap);
        return ResultUtil.returnSuccess(HospitalBeds);
    }
}
