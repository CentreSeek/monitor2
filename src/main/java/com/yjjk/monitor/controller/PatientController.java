/**
 * Copyright (C), 2019, 义金(杭州)健康科技有限公司
 * FileName: PatientController
 * Author:   CentreS
 * Date:     2019/7/19 9:20
 * Description: 病人管理模块
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.monitor.controller;

import com.alibaba.fastjson.JSON;
import com.yjjk.monitor.configer.CommonResult;
import com.yjjk.monitor.configer.ErrorCodeEnum;
import com.yjjk.monitor.constant.MachineConstant;
import com.yjjk.monitor.constant.PatientRecordConstant;
import com.yjjk.monitor.constant.TemperatureConstant;
import com.yjjk.monitor.entity.VO.RecordHistory;
import com.yjjk.monitor.entity.VO.RecordHistory2Excel;
import com.yjjk.monitor.entity.VO.TemperatureBoundVO;
import com.yjjk.monitor.entity.VO.UseMachineVO;
import com.yjjk.monitor.entity.ZsMachineInfo;
import com.yjjk.monitor.entity.ZsManagerInfo;
import com.yjjk.monitor.entity.ZsPatientInfo;
import com.yjjk.monitor.entity.ZsPatientRecord;
import com.yjjk.monitor.entity.json.TemperatureHistory;
import com.yjjk.monitor.entity.param.TemperatureBound;
import com.yjjk.monitor.utility.DateUtil;
import com.yjjk.monitor.utility.MathUtils;
import com.yjjk.monitor.utility.ResultUtil;
import com.yjjk.monitor.utility.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CentreS
 * @Description: 病人管理模块
 * @create 2019/7/19
 */
@Api(tags = {"监控管理模块"})
@RestController
@RequestMapping("patient")
public class PatientController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PatientController.class);

//    /**
//     * 启用设备
//     *
//     * @param bedId
//     * @param machineId
//     * @param name
//     * @param caseNum
//     * @param request
//     * @param response
//     */
//    @RequestMapping(value = "/patient", method = RequestMethod.POST)
//    public synchronized void addMachine(@RequestParam(value = "bedId") Integer bedId,
//                                        @RequestParam(value = "machineId") Integer machineId,
//                                        @RequestParam(value = "name") String name,
//                                        @RequestParam(value = "caseNum") String caseNum,
//                                        @RequestParam(value = "managerId") Integer managerId,
//                                        HttpServletRequest request, HttpServletResponse response) {
//        /********************** 参数初始化 **********************/
//        long startTime = System.currentTimeMillis();
//        boolean resultCode = false;
//        String message = "";
//
//        int patientCount = patientRecordService.selectByBedId(bedId);
//        if (!StringUtils.isNullorEmpty(patientCount)) {
//            message = "该病床已绑定病人";
//            returnResult(startTime, request, response, resultCode, message, "");
//            return;
//        }
//        ZsManagerInfo managerInfo = super.managerService.getManagerInfo(managerId);
//        ZsPatientInfo zsPatientInfo1 = super.patientService.selectByCaseNum(caseNum);
//        if (zsPatientInfo1 != null) {
//            ZsPatientRecord zsPatientRecord = super.patientRecordService.selectByPatientId(zsPatientInfo1.getPatientId());
//            if (zsPatientRecord != null) {
//                message = "该病人已在其他病床启用设备";
//                returnResult(startTime, request, response, resultCode, message, "");
//                return;
//            } else {
//                zsPatientInfo1.setName(name);
//                // 更新病人信息
//                super.patientService.updateName(zsPatientInfo1);
//            }
//        }
//        ZsPatientInfo zsPatientInfo = super.patientService.addPatient(name, caseNum, bedId, managerInfo.getDepartmentId());
//        if (StringUtils.isNullorEmpty(zsPatientInfo)) {
//            message = "新增病人信息失败";
//            returnResult(startTime, request, response, resultCode, message, "");
//            return;
//        }
//        ZsPatientRecord patientRecord = new ZsPatientRecord();
//        patientRecord.setStartTime(DateUtil.getCurrentTime()).setMachineId(machineId).setPatientId(zsPatientInfo.getPatientId()).setBedId(bedId);
//        int i = super.patientRecordService.addPatientRecord(patientRecord);
//        if (i == 0) {
//            message = "新增使用信息失败";
//            returnResult(startTime, request, response, resultCode, message, "");
//            return;
//        }
//        message = "成功";
//        resultCode = true;
//        returnResult(startTime, request, response, resultCode, message, "");
//    }

    /**
     * 启用设备
     *
     * @param bedId
     * @param machineId
     * @param name
     * @param caseNum
     */
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation(value = "启用设备")
    @RequestMapping(value = "/patient", method = RequestMethod.POST)
    public synchronized CommonResult startMachine(@RequestParam(value = "bedId") Integer bedId,
                                                  @RequestParam(value = "machineId") Integer machineId,
                                                  @RequestParam(value = "name") @Size(max = 8) String name,
                                                  @RequestParam(value = "caseNum") String caseNum,
                                                  @RequestParam(value = "managerId") Integer managerId) {
        /********************** 参数初始化 **********************/
        ZsManagerInfo managerInfo = this.managerService.getManagerInfo(managerId);
        ZsPatientInfo zsPatientInfo = this.patientService.selectByCaseNum(caseNum);
        List<ZsPatientRecord> usageByBedId = super.patientRecordService.getUsageByBedId(bedId);
        if (!StringUtils.isNullorEmpty(usageByBedId)) {
            return ResultUtil.returnError(ErrorCodeEnum.PATIENT_CHANGE_BED_ERROR);
        }
        if (zsPatientInfo != null) {
            ZsPatientRecord zsPatientRecord = this.patientRecordService.selectByPatientId(zsPatientInfo.getPatientId());
            if (zsPatientRecord != null && zsPatientRecord.getUsageState() == PatientRecordConstant.USAGE_STATE_USED) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return ResultUtil.returnError(ErrorCodeEnum.EXIST_RECORD);
            }
            zsPatientInfo.setName(name);
            zsPatientInfo.setBedId(bedId);

            this.patientService.updateName(zsPatientInfo);
        } else {
            zsPatientInfo = this.patientService.addPatient(name, caseNum, bedId, managerInfo.getDepartmentId());
        }
        if (StringUtils.isNullorEmpty(zsPatientInfo)) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultUtil.returnError(ErrorCodeEnum.ADD_PATIENT_ERROR);
        }

        ZsPatientRecord patientRecord = new ZsPatientRecord();
        patientRecord.setBedId(bedId).setStartTime(DateUtil.getCurrentTime()).setMachineId(machineId).
                setPatientId(zsPatientInfo.getPatientId());
        int i = 0;
        i = this.patientRecordService.addPatientRecord(patientRecord);
        if (i == 0) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultUtil.returnError(ErrorCodeEnum.ADD_RECORD_ERROR);
        }
        ZsMachineInfo machineInfo = new ZsMachineInfo();
        machineInfo.setMachineId(machineId).setUsageState(MachineConstant.USAGE_STATE_USED);
        super.machineService.updateByMachineId(machineInfo);
        return ResultUtil.returnSuccess("启用成功");
    }

    /**
     * 检索病人信息
     *
     * @param name
     * @param caseNum
     */
    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public synchronized CommonResult checkPatient(@RequestParam(value = "name") String name,
                                                  @RequestParam(value = "caseNum") String caseNum) {
        /********************** 参数初始化 **********************/
        ZsPatientInfo zsPatientInfo1 = super.patientService.selectByCaseNum(caseNum);
        if (zsPatientInfo1 != null && zsPatientInfo1.getName() != name) {
            return ResultUtil.returnError(ErrorCodeEnum.PATIENT_EXIST_ERROR);
        }
        return ResultUtil.returnSuccess("");
    }


    /**
     * 更换设备
     *
     * @param recordId
     * @param machineId
     */
    @ApiOperation("更换设备")
    @RequestMapping(value = "/changeMachine", method = RequestMethod.PUT)
    public CommonResult changeMachine(@RequestParam(value = "recordId") Long recordId,
                                      @RequestParam(value = "machineId") Integer machineId,
                                      @RequestParam(value = "managerId") Integer managerId) {
        /********************** 参数初始化 **********************/
        try {
            ZsPatientRecord patientRecord = super.patientRecordService.selectByPrimaryKey(recordId);
            if (StringUtils.isNullorEmpty(patientRecord)) {
                return ResultUtil.returnError(ErrorCodeEnum.PATIENT_GET_RECORD_ERROR);
            }
            stopRecord(recordId);
            ZsPatientInfo patientInfo = patientService.getByPrimaryKey(patientRecord.getPatientId());
            startMachine(patientRecord.getBedId(), machineId, patientInfo.getName(), patientInfo.getCaseNum(), managerId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultUtil.returnSuccess("");
    }

    /**
     * 停止检测
     *
     * @param recordId
     */
    @RequestMapping(value = "/record", method = RequestMethod.PUT)
    public CommonResult stopRecord(@RequestParam(value = "recordId") Long recordId) {
        /********************** 参数初始化 **********************/

        ZsPatientRecord patientRecord = super.patientRecordService.selectByPrimaryKey(recordId);
        if (StringUtils.isNullorEmpty(patientRecord)) {
            return ResultUtil.returnError(ErrorCodeEnum.PATIENT_RECORD_NOT_FIND);
        }

        int i = super.patientRecordService.stopMonitoring(patientRecord);
        if (i == 0) {
            return ResultUtil.returnError(ErrorCodeEnum.PATIENT_STOP_ERROR);
        }
        return ResultUtil.returnSuccess(i);
    }

    /**
     * 获取监控信息
     */
    @ApiOperation("获取监控信息")
    @RequestMapping(value = "/monitor", method = RequestMethod.GET)
    public CommonResult<List<UseMachineVO>> getMinitors(@ApiParam(value = "科室id", required = true) @RequestParam(value = "departmentId") Integer departmentId,
                                                        @ApiParam(value = "使用中设备0：使用中 1：未使用") @RequestParam(value = "used", required = false) Integer used,
                                                        @ApiParam(value = "起始床位id") @RequestParam(value = "start", required = false) Integer start,
                                                        @ApiParam(value = "结束床位id") @RequestParam(value = "end", required = false) Integer end) {
        /********************** 参数初始化 **********************/
//        ZsManagerInfo managerInfo = super.managerService.getManagerInfo(managerId);
//        Integer departmentId = null;
//        if (managerInfo.getRole() == 2) {
//            departmentId = managerInfo.getDepartmentId();
//        }

        // 监控信息
//        List<UseMachineVO> monitorsInfo = super.patientRecordService.getMonitorsInfo(departmentId);
        List<UseMachineVO> monitorsInfo = this.patientRecordService.getMonitorsInfo(departmentId);
        monitorsInfo = this.patientRecordService.updateTemperature(monitorsInfo, departmentId);

        // 根据病床id筛选监控信息
        monitorsInfo = super.patientRecordService.selectiveByBedId(monitorsInfo, start == null ? 0 : start, end == null ? Integer.MAX_VALUE : end);
        // 设备是否为使用中设备
        if (used != null && used == 0) {
            monitorsInfo = super.patientRecordService.isUsed(monitorsInfo);
        }
        // 姓名隐私
        for (int i = 0; i < monitorsInfo.size(); i++) {
            monitorsInfo.get(i).setPatientName(StringUtils.replaceNameX(monitorsInfo.get(i).getPatientName()));
        }
        // 设置温度帖盒子信息，默认为NORMAL，低电量赋值为LOW
        monitorsInfo = super.boxService.setBoxesInfo(monitorsInfo);
        // 设置体温规则
        monitorsInfo = super.temperatureBoundService.updateUseMachine(monitorsInfo, departmentId);
        return ResultUtil.returnSuccess(monitorsInfo);
    }

//    /**
//     * 获取实时监控信息
//     *
//     * @param request
//     * @param response
//     */
//    @RequestMapping(value = "/current", method = RequestMethod.GET)
//    public void getCurrentInfo(@RequestParam(value = "managerId") Integer managerId,
//                               HttpServletRequest request, HttpServletResponse response) {
//        /********************** 参数初始化 **********************/
//        long startTime = System.currentTimeMillis();
//        boolean resultCode = false;
//        String message = "";
//
//        ZsManagerInfo managerInfo = super.managerService.getManagerInfo(managerId);
//        if (managerInfo == null){
//            message = "未查询到管理员个人信息";
//            returnResult(startTime, request, response, resultCode, message, "");
//            return;
//        }
//        Integer departmentId = null;
//        if (managerInfo.getRole() == 2) {
//            departmentId = managerInfo.getDepartmentId();
//        }
//        List<PatientTemperature> monitorsTemperature = super.patientRecordService.getMinitorsTemperature(departmentId);
//        if (StringUtils.isNullorEmpty(monitorsTemperature)) {
//            message = "更新失败";
//            returnResult(startTime, request, response, resultCode, message, "");
//            return;
//        }
//        message = "更新成功";
//        resultCode = true;
//        returnResult(startTime, request, response, resultCode, message, monitorsTemperature);
//    }

    /**
     * 查询历史记录
     *
     * @param recordHistory
     */
    @RequestMapping(value = "/record", method = RequestMethod.GET)
    public CommonResult getRecordHistory(RecordHistory recordHistory) {
        /********************** 参数初始化 **********************/
        Map<String, Object> map = new HashMap<>();

        if (!StringUtils.isNullorEmpty(recordHistory.getCurrentPage()) && !StringUtils.isNullorEmpty(recordHistory.getPageSize())) {
            if (recordHistory.getCurrentPage() <= 0) {
                return ResultUtil.returnError(ErrorCodeEnum.PAGE_INFO_ERROR);
            }
            int currentPage = recordHistory.getCurrentPage();
            int pageSize = recordHistory.getPageSize();
            // 查询总条数
            int totalCount = super.patientRecordService.getRecordHistoryCount(recordHistory);
            // 分页必须信息
            int startLine = (currentPage - 1) * (pageSize);
            // 计算总页数
            int totalPage = (totalCount + pageSize - 1) / pageSize;
            recordHistory.setStartLine(startLine);
            map.put("totalPage", totalPage);
            map.put("currentPage", currentPage);
        }

        List<RecordHistory> list = super.patientRecordService.getRecordHistory(recordHistory);
        map.put("list", list == null ? "" : list);
        return ResultUtil.returnSuccess(map);
    }

    /**
     * 查询体温历史记录
     *
     * @param recordId
     */
    @RequestMapping(value = "/temperature", method = RequestMethod.GET)
    public CommonResult getTemperatureHistory(@RequestParam(value = "recordId") Long recordId) {
        /********************** 参数初始化 **********************/
        Map<String, Object> reqMap = new HashMap<>(2);
        Map<String, Object> paraMap = new HashMap<>();

        ZsPatientRecord patientRecord = super.patientRecordService.selectByPrimaryKey(recordId);
        if (StringUtils.isNullorEmpty(patientRecord)) {
            return ResultUtil.returnError(ErrorCodeEnum.PATIENT_RECORD_NOT_FIND);
        }
        String endTime = patientRecord.getEndTime();
        if (StringUtils.isNullorEmpty(endTime)) {
            // 实时查询体温记录
            paraMap.put("endTime", DateUtil.getCurrentTime());
        } else {
            // 查询历史体温记录
            paraMap.put("endTime", endTime);
        }
        paraMap.put("patientId", patientRecord.getPatientId());
        paraMap.put("times", DateUtil.timeDifferentLong(patientRecord.getStartTime(), (String) paraMap.get("endTime")));

        List<TemperatureHistory> list = super.patientRecordService.getCurrentTemperatureRecord(paraMap);
        if (StringUtils.isNullorEmpty(patientRecord.getTemperatureHistory())) {
            reqMap.put("useTimes", DateUtil.timeDifferent(patientRecord.getStartTime()));
        } else {
            list = JSON.parseArray(patientRecord.getTemperatureHistory(), TemperatureHistory.class);
            reqMap.put("useTimes", DateUtil.timeDifferent(patientRecord.getStartTime(), patientRecord.getEndTime()));
        }
        reqMap = super.patientRecordService.parseTemperature(list, reqMap, patientRecord.getMachineId());
        reqMap.put("list", StringUtils.isNullorEmpty(list) ? "" : list);
        reqMap.put("startTime", StringUtils.isNullorEmpty(list) ? "" : DateUtil.integerForward(list.get(0).getDateTime()));
        reqMap.put("endTime", StringUtils.isNullorEmpty(list) ? "" : DateUtil.integerForward(list.get(list.size() - 1).getDateTime()));
        return ResultUtil.returnSuccess(reqMap);
    }

    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void export(@RequestParam(value = "timeList") List<String> timeList,
                       @ApiParam(value = "语言 0：中文 1：英文", required = true) @RequestParam(value = "language") Integer language,
                       @RequestParam(value = "token") String token,
                       HttpServletResponse response) throws IOException {
        if (timeList.size() == 0) {
            return;
        }
        Map<String, Object> paraMap = new HashMap<>();
        List<RecordHistory2Excel> list = new ArrayList<>();
        for (int i = 0; i < timeList.size(); i++) {
            paraMap.put("time", timeList.get(i));
            paraMap.put("threshold", DateUtil.getTwoMinutePast(timeList.get(i)));
            paraMap.put("token", token);
            list.addAll(super.patientRecordService.getExportList(paraMap));
            paraMap.clear();
        }

        HSSFWorkbook wb = new HSSFWorkbook();

        HSSFSheet sheet = wb.createSheet("sheet1");

        HSSFRow row = null;
        // 创建第一个单元格
        row = sheet.createRow(0);
        row.setHeight((short) (26.25 * 20));
        // 选择导出对象版本
        if (language == 0) {
            // 为第一行单元格设值
            row.createCell(0).setCellValue("温度监测平台使用日志");

            /*
             * 为标题设计空间
             * firstRow从第1行开始
             * lastRow从第0行结束
             *
             * 从第1个单元格开始
             * 从第3个单元格结束
             */
            CellRangeAddress rowRegion = new CellRangeAddress(0, 0, 0, 6);
            sheet.addMergedRegion(rowRegion);

            /*
             * 动态获取数据库列 sql语句 select COLUMN_NAME from INFORMATION_SCHEMA.Columns where table_name='user' and table_schema='test'
             * 第一个table_name 表名字
             * 第二个table_name 数据库名称
             * */
            row = sheet.createRow(1);
            //设置行高
            row.setHeight((short) (22.50 * 20));
            //为单元格设值
            row.createCell(0).setCellValue("姓名");
            row.createCell(1).setCellValue("住院号");
            row.createCell(2).setCellValue("科室");
            row.createCell(3).setCellValue("房号");
            row.createCell(4).setCellValue("床位");
            row.createCell(5).setCellValue("时间点");
            row.createCell(6).setCellValue("体温(℃)");
            for (int i = 0; i < list.size(); i++) {
                row = sheet.createRow(i + 2);
                RecordHistory2Excel record = list.get(i);
                row.createCell(0).setCellValue(record.getPatientName());
                row.createCell(1).setCellValue(record.getCaseNum());
                row.createCell(2).setCellValue(record.getDepartmentName());
                row.createCell(3).setCellValue(record.getRoom());
                row.createCell(4).setCellValue(record.getBed());
                if (!StringUtils.isNullorEmpty(record.getTime())) {
                    row.createCell(5).setCellValue(record.getTime());
                }
                if (!StringUtils.isNullorEmpty(record.getTemperature())) {
                    row.createCell(6).setCellValue(record.getTemperature());
                }
            }
        } else if (language == 1) {
            row.createCell(0).setCellValue("Thermometry log");
            CellRangeAddress rowRegion = new CellRangeAddress(0, 0, 0, 6);
            sheet.addMergedRegion(rowRegion);
            row = sheet.createRow(1);
            row.setHeight((short) (22.50 * 20));
            row.createCell(0).setCellValue("name");
            row.createCell(1).setCellValue("hospital number");
            row.createCell(2).setCellValue("department");
            row.createCell(3).setCellValue("room number");
            row.createCell(4).setCellValue("bed number");
            row.createCell(5).setCellValue("time");
            row.createCell(6).setCellValue("date(℉)");
            for (int i = 0; i < list.size(); i++) {
                row = sheet.createRow(i + 2);
                RecordHistory2Excel record = list.get(i);
                row.createCell(0).setCellValue(record.getPatientName());
                row.createCell(1).setCellValue(record.getCaseNum());
                row.createCell(2).setCellValue(record.getDepartmentName());
                row.createCell(3).setCellValue(record.getRoom());
                row.createCell(4).setCellValue(record.getBed());
                row.createCell(5).setCellValue(record.getTime() == null ? null : record.getTime());
                if (!StringUtils.isNullorEmpty(record.getTemperature())) {
                    row.createCell(6).setCellValue(MathUtils.centigrade2Fahrenheit(Double.parseDouble(record.getTemperature()), 1));
                }
            }
        }
        sheet.setDefaultRowHeight((short) (16.5 * 20));
        //列宽自适应
        for (int i = 0; i <= 13; i++) {
            sheet.autoSizeColumn(i);
        }
//        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setContentType("application/octet-stream;charset=utf-8");
        OutputStream os = response.getOutputStream();
        //默认Excel名称
        response.setHeader("Content-disposition", "attachment;filename=" + DateUtil.getHistoryFileName() + ".xls");
        wb.write(os);
        wb.close();
//        os.flush();
//        os.close();
    }

    @RequestMapping(value = "/privateExport", method = RequestMethod.GET)
    public void privateExport(@ApiParam(value = "筛选规则，筛选大于该摄氏度的体温") @RequestParam(value = "temperature", required = false) Double temperature,
                              @ApiParam(value = "语言 0：中文 1：英文", required = true) @RequestParam(value = "language") Integer language,
                              @ApiParam(value = "recordId", required = true) @RequestParam(value = "recordId") Integer recordId,
                              HttpServletResponse response) throws IOException {
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("temperature", temperature);
        paraMap.put("recordId", recordId);
        List<RecordHistory2Excel> list = super.patientRecordService.getPrivateExport(paraMap);

        HSSFWorkbook wb = new HSSFWorkbook();

        HSSFSheet sheet = wb.createSheet("sheet1");

        HSSFRow row = null;
        // 创建第一个单元格
        row = sheet.createRow(0);
        row.setHeight((short) (26.25 * 20));
        // 选择导出对象版本
        if (language == 0) {
            // 为第一行单元格设值
            row.createCell(0).setCellValue("温度监测平台使用日志");

            /*
             * 为标题设计空间
             * firstRow从第1行开始
             * lastRow从第0行结束
             *
             * 从第1个单元格开始
             * 从第3个单元格结束
             */
            CellRangeAddress rowRegion = new CellRangeAddress(0, 0, 0, 6);
            sheet.addMergedRegion(rowRegion);

            /*
             * 动态获取数据库列 sql语句 select COLUMN_NAME from INFORMATION_SCHEMA.Columns where table_name='user' and table_schema='test'
             * 第一个table_name 表名字
             * 第二个table_name 数据库名称
             * */
            row = sheet.createRow(1);
            //设置行高
            row.setHeight((short) (22.50 * 20));
            //为单元格设值
            row.createCell(0).setCellValue("姓名");
            row.createCell(1).setCellValue("住院号");
            row.createCell(2).setCellValue("科室");
            row.createCell(3).setCellValue("房号");
            row.createCell(4).setCellValue("床位");
            row.createCell(5).setCellValue("时间点");
            row.createCell(6).setCellValue("体温(℃)");
            if (!StringUtils.isNullorEmpty(list)) {
                for (int i = 0; i < list.size(); i++) {
                    row = sheet.createRow(i + 2);
                    RecordHistory2Excel record = list.get(i);
                    row.createCell(0).setCellValue(record.getPatientName());
                    row.createCell(1).setCellValue(record.getCaseNum());
                    row.createCell(2).setCellValue(record.getDepartmentName());
                    row.createCell(3).setCellValue(record.getRoom());
                    row.createCell(4).setCellValue(record.getBed());
                    if (!StringUtils.isNullorEmpty(record.getTime())) {
                        row.createCell(5).setCellValue(record.getTime());
                    }
                    if (!StringUtils.isNullorEmpty(record.getTemperature())) {
                        row.createCell(6).setCellValue(record.getTemperature());
                    }
                }
            }

        } else if (language == 1) {
            row.createCell(0).setCellValue("Thermometry log");
            CellRangeAddress rowRegion = new CellRangeAddress(0, 0, 0, 6);
            sheet.addMergedRegion(rowRegion);
            row = sheet.createRow(1);
            row.setHeight((short) (22.50 * 20));
            row.createCell(0).setCellValue("name");
            row.createCell(1).setCellValue("hospital number");
            row.createCell(2).setCellValue("department");
            row.createCell(3).setCellValue("room number");
            row.createCell(4).setCellValue("bed number");
            row.createCell(5).setCellValue("time");
            row.createCell(6).setCellValue("date(℉)");
            if (!StringUtils.isNullorEmpty(list)) {
                for (int i = 0; i < list.size(); i++) {
                    row = sheet.createRow(i + 2);
                    RecordHistory2Excel record = list.get(i);
                    row.createCell(0).setCellValue(record.getPatientName());
                    row.createCell(1).setCellValue(record.getCaseNum());
                    row.createCell(2).setCellValue(record.getDepartmentName());
                    row.createCell(3).setCellValue(record.getRoom());
                    row.createCell(4).setCellValue(record.getBed());
                    row.createCell(5).setCellValue(record.getTime() == null ? null : record.getTime());
                    if (!StringUtils.isNullorEmpty(record.getTemperature())) {
                        row.createCell(6).setCellValue(MathUtils.centigrade2Fahrenheit(Double.parseDouble(record.getTemperature()), 1));
                    }
                }
            }
        }
        sheet.setDefaultRowHeight((short) (16.5 * 20));
        //列宽自适应
        for (int i = 0; i <= 13; i++) {
            sheet.autoSizeColumn(i);
        }
//        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setContentType("application/octet-stream;charset=utf-8");
        OutputStream os = response.getOutputStream();
        //默认Excel名称
        response.setHeader("Content-disposition", "attachment;filename=" + DateUtil.getHistoryFileName() + ".xls");
        wb.write(os);
        wb.close();
//        os.flush();
//        os.close();
    }

    @ApiOperation("设置体温监测规则")
    @RequestMapping(value = "/bound", method = RequestMethod.PUT)
    public CommonResult setTemperatureAlert(@Valid TemperatureBound param) {
        try {

            /********************** 参数初始化 **********************/
            if (param.getDepartmentId().equals(TemperatureConstant.DEFAULT_DEPARTMENT_ID)) {
                return ResultUtil.returnError(ErrorCodeEnum.TEMPERATURE_BOUND_DEPARTMENT_ERROR);
            }
            Integer i = super.temperatureBoundService.setTemperatureBound(param);
            return ResultUtil.returnSuccess(i);
        } catch (Exception e) {
            LOGGER.error("业务异常信息：[{}]", e.getMessage(), e);
            return ResultUtil.returnError(ErrorCodeEnum.UNKNOWN_ERROR);
        }
    }

    @ApiOperation("获取默认体温监测规则")
    @RequestMapping(value = "/bound", method = RequestMethod.GET)
    public CommonResult<List<TemperatureBoundVO>> getDefaultAlert(@RequestParam(value = "departmentId") Integer departmentId) {
        try {
            /********************** 参数初始化 **********************/
            List<TemperatureBoundVO> defaultAlert = super.temperatureBoundService.getDefaultAlert(departmentId);
            return ResultUtil.returnSuccess(defaultAlert);
        } catch (Exception e) {
            LOGGER.error("业务异常信息：[{}]", e.getMessage(), e);
            return ResultUtil.returnError(ErrorCodeEnum.UNKNOWN_ERROR);
        }
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @ApiOperation("更换床位")
    @RequestMapping(value = {"/bed"}, method = {org.springframework.web.bind.annotation.RequestMethod.PUT})
    public synchronized CommonResult changeBed(@ApiParam(value = "新床位号", required = true) @RequestParam("newBedId") Integer newBedId,
                                               @ApiParam(value = "现床位号", required = true) @RequestParam("currentBedId") Integer currentBedId) {
        try {
            List<ZsPatientRecord> targetBed = super.patientRecordService.getUsageByBedId(newBedId);
            if (!StringUtils.isNullorEmpty(targetBed)) {
                return ResultUtil.returnError(ErrorCodeEnum.PATIENT_CHANGE_BED_ERROR);
            }
            List<ZsPatientRecord> list = super.patientRecordService.getUsageByBedId(currentBedId);
            if (StringUtils.isNullorEmpty(list)) {
                return ResultUtil.returnError(ErrorCodeEnum.NON_RECORD);
            }
            ZsPatientInfo zsPatientInfo = new ZsPatientInfo();
            zsPatientInfo.setBedId(newBedId);
            this.patientService.updateName(zsPatientInfo);

            ZsPatientRecord zsPatientRecord = new ZsPatientRecord();
            zsPatientRecord.setBedId(newBedId);
            zsPatientRecord.setRecordId(list.get(0).getRecordId());
            int j = this.patientRecordService.updateByPrimaryKey(zsPatientRecord);
            if (j == 0) {
                return ResultUtil.returnError(ErrorCodeEnum.UPDATE_ERROR);
            }
            return ResultUtil.returnSuccess("");
        } catch (Exception e) {
            LOGGER.error("业务异常信息：[{}]", e.getMessage(), e);
            return ResultUtil.returnError(ErrorCodeEnum.UNKNOWN_ERROR);
        }
    }

}
