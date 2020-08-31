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
import com.yjjk.monitor.entity.ListVO;
import com.yjjk.monitor.entity.pojo.HospitalBed;
import com.yjjk.monitor.entity.pojo.HospitalDepartment;
import com.yjjk.monitor.entity.pojo.HospitalRoom;
import com.yjjk.monitor.utility.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
@Api(tags = {"医院模块"})
public class HospitalController extends BaseController {


    @RequestMapping(value = "/hospital", method = RequestMethod.GET)
    public CommonResult getHospitalInfo() {
        /********************** 参数初始化 **********************/
        String s = super.hospitalService.getHospitalName();
        return ResultUtil.returnSuccess(s);
    }

    /**
     * 查询科室信息
     *
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

//    @RequestMapping(value = "/bedCount", method = RequestMethod.GET)
//    public CommonResult getDepartments(@RequestParam(value = "departmentId") Integer departmentId) {
//        /********************** 参数初始化 **********************/
//        int count = super.hospitalService.getBedCount(departmentId);
//        return ResultUtil.returnSuccess(count);
//    }

    /**
     * 查询房间信息
     *
     * @param departmentId
     */
    @RequestMapping(value = "/rooms", method = RequestMethod.GET)
    public CommonResult getRooms(@RequestParam(value = "departmentId") Integer departmentId) {
        /********************** 参数初始化 **********************/
        List<HospitalRoom> list = super.hospitalService.selectRooms(departmentId);
        return ResultUtil.returnSuccess(list);
    }

    @ApiOperation("获取未启用recordBase空床位")
    @RequestMapping(value = {"/emptyBeds"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public CommonResult<List<HospitalBed>> getEmptyBeds(@RequestParam("departmentId") Integer departmentId) {
        Map<String, Object> paraMap = new HashMap();
        paraMap.put("departmentId", departmentId);
        List<HospitalBed> HospitalBeds = this.hospitalService.selectEmptyBeds(paraMap);
        return ResultUtil.returnSuccess(HospitalBeds);
    }

    @ApiOperation("获取未启用某设备的空床位")
    @RequestMapping(value = {"/monitorEmptyBeds"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public CommonResult<List<HospitalBed>> getMonitorEmptyBeds(@RequestParam("departmentId") Integer departmentId,
                                                               @ApiParam(value = "类型： 0-体温 1-心电 2-血氧 3-离床感应") @RequestParam("type") Integer type) {
        List<HospitalBed> HospitalBeds = this.hospitalService.selectMonitorEmptyBeds(departmentId, type);
        return ResultUtil.returnSuccess(HospitalBeds);
    }

    @ApiOperation("page监控模块：获取过滤床位list")
    @RequestMapping(value = {"/filterBeds"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public CommonResult<List<ListVO>> getMonitorFilterBeds(@RequestParam("departmentId") Integer departmentId,
                                                           @ApiParam(value = "筛选之后的床位") @RequestParam(value = "bedId", required = false) Integer bedId) {
        List<ListVO> HospitalBeds = this.hospitalService.getMonitorBedList(departmentId, bedId);
        return ResultUtil.returnSuccess(HospitalBeds);
    }
}
