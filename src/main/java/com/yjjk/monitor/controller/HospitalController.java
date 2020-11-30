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
import com.yjjk.monitor.configer.ErrorCodeEnum;
import com.yjjk.monitor.entity.BO.hospital.AddRoomBO;
import com.yjjk.monitor.entity.ListVO;
import com.yjjk.monitor.entity.hospital.Room;
import com.yjjk.monitor.entity.pojo.HospitalBed;
import com.yjjk.monitor.entity.pojo.HospitalDepartment;
import com.yjjk.monitor.entity.pojo.HospitalRoom;
import com.yjjk.monitor.utility.ResultUtil;
import com.yjjk.monitor.utility.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Iterator;
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
    public CommonResult selectDetail(@RequestParam(value = "departmentId", required = false) Integer departmentId,
                                     @ApiParam(value = "是否需要查询  未关联  科室") @RequestParam(value = "flag", required = false, defaultValue = "false") Boolean flag) {
        /********************** 参数初始化 **********************/
        List<HospitalDepartment> list = super.hospitalService.selectDetail(departmentId);
        if (!flag) {
            Iterator<HospitalDepartment> iterator = list.iterator();
            while (iterator.hasNext()) {
                if (iterator.next().getDepartmentId() == 0) {
                    iterator.remove();
                }
            }
        }
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

    @ApiOperation("page床位管理：编辑房间")
    @RequestMapping(value = {"/room"}, method = {RequestMethod.PUT})
    @Transactional(rollbackFor = Exception.class)
    public CommonResult updateRoom(@ApiParam(value = "房间信息：") @RequestBody Room room) {
        boolean b1 = super.hospitalService.canDeleteRoom(room);
        if (!b1) {
            return ResultUtil.returnError(ErrorCodeEnum.HOSPITAL_CANNOT_UPDATE_ROOM_INFORMATION);
        }
//        department = super.hospitalService.deleteHospital(department);
        boolean b = super.hospitalService.updateRoom(room);
        if (!b) {
            return ResultUtil.returnError(ErrorCodeEnum.HOSPITAL_ROOM_NAME);
        }
        String beds = super.hospitalService.updateBeds(room);
        if (!StringUtils.isNullorEmpty(beds)) {
            return ResultUtil.returnError("374", beds);
        }
        return ResultUtil.returnSuccess(true);
    }

    @ApiOperation("page床位管理：删除房间")
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = {"/room"}, method = {RequestMethod.DELETE})
    public CommonResult deleteRoom(@ApiParam(value = "房间信息：") @RequestParam("roomId") Integer roomId) {
        boolean b1 = super.hospitalService.canDeleteRoom(roomId);
        if (!b1) {
            return ResultUtil.returnError(ErrorCodeEnum.HOSPITAL_CANNOT_UPDATE_ROOM_INFORMATION);
        }
//        department = super.hospitalService.deleteHospital(department);
        boolean b = super.hospitalService.deleteRoom(roomId);
        return ResultUtil.returnSuccess(b);
    }

    @ApiOperation("page床位管理：新增房间")
    @PostMapping(value = "/room")
    @Transactional(rollbackFor = Exception.class)
    public CommonResult addRoom(@ApiParam(value = "房间信息：") @RequestBody AddRoomBO addRoomBO) {
//        department = super.hospitalService.deleteHospital(department);
        addRoomBO.getRoom();
        addRoomBO.setDepartmentId(addRoomBO.getDepartmentId());
        String beds = super.hospitalService.checkBedsName(addRoomBO.getRoom());
        if (beds != null) {
            return ResultUtil.returnError("374", beds);
        }
        boolean b = super.hospitalService.addRoom(addRoomBO.getRoom(), addRoomBO.getDepartmentId());
        if (!b) {
            return ResultUtil.returnError(ErrorCodeEnum.HOSPITAL_ROOM_NAME);
        }
        return ResultUtil.returnSuccess(b);
    }

    @ApiOperation("page床位管理：新增科室")
    @RequestMapping(value = {"/department"}, method = {RequestMethod.POST})
    @Transactional(rollbackFor = Exception.class)
    public CommonResult addDepartment(@ApiParam(value = "name", required = true) @RequestParam(value = "name") String name) {
        boolean b = hospitalService.addDepartment(name);
        if (!b) {
            return ResultUtil.returnError(ErrorCodeEnum.HOSPITAL_DEPARTMENT_NAME);
        }
        return ResultUtil.returnSuccess(b);
    }

//    public static void main(String[] args) {
//        AddRoomBO a = new AddRoomBO();
//        Room r = new Room();
//        r.setName("fda");
//        r.setRoomId(null);
//        a.setRoom(r);
//        a.setDepartmentId(1);
//        System.out.println(JSON.toJSONString(a));
//    }

    @ApiOperation("page床位管理：编辑科室")
    @RequestMapping(value = {"/department"}, method = {RequestMethod.PUT})
    @Transactional(rollbackFor = Exception.class)
    public CommonResult updateDepartmentName(@ApiParam(value = "departmentId", required = true) @RequestParam(value = "departmentId") Integer departmentId,
                                             @ApiParam(value = "name", required = true) @RequestParam(value = "name") String name) {
        boolean b = hospitalService.updateDepartment(departmentId, name);
        if (!b) {
            return ResultUtil.returnError(ErrorCodeEnum.HOSPITAL_DEPARTMENT_NAME);
        }
        return ResultUtil.returnSuccess(b);
    }


    @ApiOperation("page床位管理：删除科室")
    @RequestMapping(value = {"/department"}, method = {RequestMethod.DELETE})
    @Transactional(rollbackFor = Exception.class)
    public CommonResult deleteDepartment(@ApiParam(value = "departmentId", required = true) @RequestParam(value = "departmentId") Integer departmentId) {
        boolean b1 = super.hospitalService.canDeleteHospital(departmentId);
        if (!b1) {
            return ResultUtil.returnError(ErrorCodeEnum.HOSPITAL_CANNOT_UPDATE_HOSPITAL_INFORMATION);
        }
        super.hospitalService.deleteHospital(departmentId);
        return ResultUtil.returnSuccess(b1);
    }

}
