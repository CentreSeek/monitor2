/**
 * Copyright (C), 2019, 义金(杭州)健康科技有限公司
 * FileName: PatientRecordServiceImpl
 * Author:   CentreS
 * Date:     2019/7/22 11:41
 * Description: 历史记录
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.monitor.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.vivalnk.sdk.Mit16Util;
import com.yjjk.monitor.constant.ExportExcelConstant;
import com.yjjk.monitor.constant.MachineConstant;
import com.yjjk.monitor.constant.MonitorConstant;
import com.yjjk.monitor.constant.SleepEnum;
import com.yjjk.monitor.entity.BO.PageBO;
import com.yjjk.monitor.entity.BO.history.GetRecordsBO;
import com.yjjk.monitor.entity.VO.PagedGridResult;
import com.yjjk.monitor.entity.VO.history.RecordsHistory;
import com.yjjk.monitor.entity.export.history.HistoryExportBloodVO;
import com.yjjk.monitor.entity.export.history.HistoryExportEcgVO;
import com.yjjk.monitor.entity.export.history.HistoryExportSleepingVO;
import com.yjjk.monitor.entity.export.history.HistoryExportTemperatureVO;
import com.yjjk.monitor.entity.export.history.export.HistoryExportBloodVOO;
import com.yjjk.monitor.entity.export.history.export.HistoryExportEcgVOO;
import com.yjjk.monitor.entity.export.history.export.HistoryExportSleepingVOO;
import com.yjjk.monitor.entity.export.history.export.HistoryExportTemperatureVOO;
import com.yjjk.monitor.entity.history.BaseData;
import com.yjjk.monitor.entity.history.BloodHistory;
import com.yjjk.monitor.entity.history.BloodHistoryData;
import com.yjjk.monitor.entity.history.EcgHistory;
import com.yjjk.monitor.entity.history.EcgHistoryData;
import com.yjjk.monitor.entity.history.SleepingHistory;
import com.yjjk.monitor.entity.history.SleepingHistoryData;
import com.yjjk.monitor.entity.history.TemperatureHistory;
import com.yjjk.monitor.entity.history.TemperatureHistoryData;
import com.yjjk.monitor.entity.pojo.PatientInfo;
import com.yjjk.monitor.entity.pojo.RecordBase;
import com.yjjk.monitor.entity.pojo.RecordBlood;
import com.yjjk.monitor.entity.pojo.RecordEcg;
import com.yjjk.monitor.entity.pojo.RecordSleeping;
import com.yjjk.monitor.entity.pojo.RecordTemperature;
import com.yjjk.monitor.entity.pojo.ZsEcgInfo;
import com.yjjk.monitor.entity.pojo.ZsMachineInfo;
import com.yjjk.monitor.service.BaseService;
import com.yjjk.monitor.service.HistoryService;
import com.yjjk.monitor.utility.DataUtils;
import com.yjjk.monitor.utility.DateUtil;
import com.yjjk.monitor.utility.ExcelUtils;
import com.yjjk.monitor.utility.FileNameUtils;
import com.yjjk.monitor.utility.StringUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author CentreS
 * @Description: 监控模块
 * @create 2019/7/22
 */
@Service
public class HistoryServiceImpl extends BaseService implements HistoryService {

    @Override
    public String ecgExport(String timestamp, Integer baseId) {
        RecordBase recordBase = recordBaseMapper.selectByPrimaryKey(baseId);
        RecordEcg recordEcg = recordEcgMapper.selectByPrimaryKeyNoneHistory(recordBase.getRecordEcgId());
        ZsMachineInfo machineInfo = zsMachineInfoMapper.getByMachineId(recordEcg.getMachineId());
        PatientInfo patientInfo = patientInfoMapper.selectByPrimaryKey(recordBase.getPatientId());
        List<ZsEcgInfo> ecgs = zsEcgInfoMapper.getEcgs(machineInfo.getMachineId(), DateUtil.getDateTimeLong(timestamp + " 00:00:00"), DateUtil.getDateTimeLong(timestamp + " 24:00:00"));
        int[] ints = DataUtils.parseData(ecgs);
        if (!StringUtils.isNullorEmpty(ecgs)) {
            return Mit16Util.writeMit16File(patientInfo.getCaseNum(), ecgs.get(0).getTimestamp(), ints);
        }
        return "";
    }

    @Override
    public TemperatureHistory filterTemperatureData(TemperatureHistory dataList, Double temperature) {
        List<List<TemperatureHistoryData>> history = dataList.getHistory();
        for (int i = 0; i < history.size(); i++) {
            if (history.get(i) == null) {
                break;
            }
            Iterator<TemperatureHistoryData> iterator = history.get(i).iterator();
            while (iterator.hasNext()) {
                TemperatureHistoryData next = iterator.next();
                if (next.getTemperature() < temperature) {
                    iterator.remove();
                }
            }
        }
        dataList.setHistory(history);
        return dataList;
    }

    @Override
    public void export(HttpServletResponse response, Integer type, List dataList, Integer language, Integer temType) throws IOException {
        String[] tital = null;
        if (type == MachineConstant.TEMPERATURE) {
            if (language == ExportExcelConstant.CN) {
                tital = ExportExcelConstant.TEMPERATURE_TITLE;
            } else if (temType == ExportExcelConstant.CENTIGRADE) {
                tital = ExportExcelConstant.TEMPERATURE_TITLE_EN;
            } else {
                tital = ExportExcelConstant.TEMPERATURE_TITLE_EN_F;
                dataList = transFahrenheit((List<HistoryExportTemperatureVOO>) dataList);
            }
        }
        if (type == MachineConstant.ECG) {
            if (language == ExportExcelConstant.CN) {
                tital = ExportExcelConstant.ECG_TITLE;
            } else {
                tital = ExportExcelConstant.ECG_TITLE_EN;
            }
        }
        if (type == MachineConstant.BLOOD) {
            if (language == ExportExcelConstant.CN) {
                tital = ExportExcelConstant.BLOOD_TITLE;
            } else {
                tital = ExportExcelConstant.BLOOD_TITLE_EN;
            }
        }
        if (type == MachineConstant.SLEEPING) {
            if (language == ExportExcelConstant.CN) {
                tital = ExportExcelConstant.SLEEPING_TITLE;
            } else {
                tital = ExportExcelConstant.SLEEPING_TITLE_EN;
            }
        }
        ExcelUtils.exportExcel(response, dataList, FileNameUtils.getHistoryFileName(), tital);
    }

    @Override
    public PagedGridResult getHistory(PageBO pageBO, GetRecordsBO bo) {
        bo.setPatientName(StringUtils.getLikeName(bo.getPatientName()));
        PageHelper.startPage(pageBO.getPage(), pageBO.getPageSize());
        List<RecordsHistory> list = new ArrayList<>();
        switch (bo.getType()) {
            case MachineConstant.TEMPERATURE:
                list = super.recordTemperatureMapper.getHistoryRecords(bo);
                break;
            case MachineConstant.ECG:
                list = super.recordEcgMapper.getHistoryRecords(bo);
                break;
            case MachineConstant.BLOOD:
                list = super.recordBloodMapper.getHistoryRecords(bo);
                break;
            case MachineConstant.SLEEPING:
                list = super.recordSleepingMapper.getHistoryRecords(bo);
                break;
            default:
                break;
        }
        // 患者姓名脱敏
//        for (RecordsHistory recordsHistory : list) {
//            recordsHistory.setPatientName(StringUtils.replaceNameX(recordsHistory.getPatientName()));
//        }
        return setterPagedGrid(list, pageBO.getPage());
    }


    @Override
    public Object getHistoryData(Integer type, Integer recordId, Integer temType) {
        switch (type) {
            case MachineConstant.TEMPERATURE:
                RecordTemperature recordTemperature = super.recordTemperatureMapper.selectByPrimaryKey(recordId);
                TemperatureHistory temperatureHistory = JSON.parseObject(recordTemperature.getHistory(), TemperatureHistory.class);
                if (recordTemperature.getMachineId() != -1) {
                    List<TemperatureHistoryData> histories1 = super.recordTemperatureMapper.getHistories(recordTemperature.getMachineId());
                    temperatureHistory.getHistory().add(histories1);
                }
                // 清理空数组
                Iterator<List<TemperatureHistoryData>> iterator = temperatureHistory.getHistory().iterator();
                while (iterator.hasNext()) {
                    List<TemperatureHistoryData> temp = iterator.next();
                    if (StringUtils.isNullorEmpty(temp)) {
                        iterator.remove();
                    }
                }
                // 转换华氏度
                if (temType == ExportExcelConstant.FAHRENHEIT) {
                    for (List<TemperatureHistoryData> tempLis : temperatureHistory.getHistory()) {
                        for (TemperatureHistoryData temp : tempLis) {
                            temp.setTemperature(DataUtils.transFahrenheit(temp.getTemperature()));
                        }
                    }
                }
                return temperatureHistory;
            case MachineConstant.ECG:
                RecordEcg recordEcg = super.recordEcgMapper.selectByPrimaryKey(recordId);
                List<EcgHistoryData> histories2 = super.recordEcgMapper.getHistories(recordEcg.getMachineId());
                EcgHistory ecgHistory = JSON.parseObject(recordEcg.getHistory(), EcgHistory.class);
                ecgHistory.getHistory().add(histories2);
                // 清理空数组
                Iterator<List<EcgHistoryData>> iterator2 = ecgHistory.getHistory().iterator();
                while (iterator2.hasNext()) {
                    List<EcgHistoryData> temp = iterator2.next();
                    if (StringUtils.isNullorEmpty(temp)) {
                        iterator2.remove();
                    }
                }
                return ecgHistory;
            case MachineConstant.BLOOD:
                RecordBlood recordBlood = super.recordBloodMapper.selectByPrimaryKey(recordId);
                BloodHistory bloodHistory = JSON.parseObject(recordBlood.getHistory(), BloodHistory.class);
                if (recordBlood.getMachineId() != -1) {
                    List<BloodHistoryData> histories3 = super.recordBloodMapper.getHistories(recordBlood.getMachineId());
                    bloodHistory.getHistory().add(histories3);
                }
                // 清理空数组
                Iterator<List<BloodHistoryData>> iterator3 = bloodHistory.getHistory().iterator();
                while (iterator3.hasNext()) {
                    List<BloodHistoryData> temp = iterator3.next();
                    if (StringUtils.isNullorEmpty(temp)) {
                        iterator3.remove();
                    }
                }
                return bloodHistory;
            case MachineConstant.SLEEPING:
                RecordSleeping recordSleeping = super.recordSleepingMapper.selectByPrimaryKey(recordId);
                SleepingHistory sleepingHistory = JSON.parseObject(recordSleeping.getHistory(), SleepingHistory.class);
                if (recordSleeping.getMachineId() != -1) {
                    List<SleepingHistoryData> histories = super.recordSleepingMapper.getHistories(recordSleeping.getMachineId());
                    sleepingHistory.getHistory().add(histories);
                }
                // 清理空数组
                Iterator<List<SleepingHistoryData>> iterator4 = sleepingHistory.getHistory().iterator();
                while (iterator4.hasNext()) {
                    List<SleepingHistoryData> temp = iterator4.next();
                    if (StringUtils.isNullorEmpty(temp)) {
                        iterator4.remove();
                    }
                }
                return sleepingHistory;
            default:
                return new TemperatureHistoryData();
        }
    }

    @Override
    public Object getHistoryData(Integer type, Integer recordId) {
        return getHistoryData(type, recordId, ExportExcelConstant.CENTIGRADE);
    }

    @Override
    public List<BaseData> getMonitorData(Integer type, Integer baseId, Integer temType) {
        RecordBase recordBase = super.recordBaseMapper.selectByPrimaryKey(baseId);
        if (type == MonitorConstant.TEMPERATURE && recordBase.getRecordTemperatureId() != -1) {
            TemperatureHistory temData = (TemperatureHistory) getHistoryData(MachineConstant.TEMPERATURE, recordBase.getRecordTemperatureId(), temType);
            // 转换华氏度
//            if (temType == ExportExcelConstant.FAHRENHEIT) {
//                for (List<TemperatureHistoryData> tempLis : temData.getHistory()) {
//                    for (TemperatureHistoryData temp : tempLis) {
//                        temp.setTemperature(DataUtils.transFahrenheit(temp.getTemperature()));
//                    }
//                }
//            }
            List<BaseData> param1 = new ArrayList<>();
            for (int i = 0; i < temData.getHistory().size(); i++) {
                param1.addAll(temData.getHistory().get(i));
            }
            return param1;
        }
        if (type == MonitorConstant.BLOOD_PI && recordBase.getRecordBloodId() != -1) {
            BloodHistory bloodData = (BloodHistory) getHistoryData(MachineConstant.BLOOD, recordBase.getRecordBloodId());
            List<BaseData> param2 = new ArrayList<>();
            for (int i = 0; i < bloodData.getHistory().size(); i++) {
                param2.addAll(bloodData.getHistory().get(i));
            }
            for (int i = 0; i < param2.size(); i++) {
                BloodHistoryData temp = (BloodHistoryData) param2.get(i);
                temp.setHeartRate(null).setRespiratoryRate(null);
            }
            return param2;
        }
        if (type == MonitorConstant.HEART_RESPIRATORY_RATE) {
            List<EcgHistory> list1 = new ArrayList<>();
            List<BloodHistoryData> list2 = new ArrayList<>();
            List<SleepingHistoryData> list3 = new ArrayList<>();
            if (recordBase.getRecordEcgId() != -1) {
                EcgHistory ecgData = (EcgHistory) getHistoryData(MachineConstant.ECG, recordBase.getRecordEcgId());
                list1 = DataUtils.concatList(ecgData.getHistory());
            }
            if (recordBase.getRecordBloodId() != -1) {
                BloodHistory bloodData = (BloodHistory) getHistoryData(MachineConstant.BLOOD, recordBase.getRecordBloodId());
                list2 = DataUtils.concatList(bloodData.getHistory());
                for (int i = 0; i < list2.size(); i++) {
                    list2.get(i).setBloodOxygen(null).setPi(null);
                }
            }
            if (recordBase.getRecordSleepingId() != -1) {
                SleepingHistory sleepingData = (SleepingHistory) getHistoryData(MachineConstant.SLEEPING, recordBase.getRecordSleepingId());
                list3 = DataUtils.concatList(sleepingData.getHistory());
                for (int i = 0; i < list3.size(); i++) {
                    list3.get(i).setSleepState(null);
                }
            }
            List<BaseData> baseData = DataUtils.baseConcatData(list2, list3);
            baseData = DataUtils.baseConcatData(list1, baseData);
            return baseData;
        }

        return new ArrayList<>();
    }

    @Override
    public List getExportHistoryList(Integer type, Integer departmentId, String date, List<String> timeList) {
        List result = new ArrayList<>();
        if (type == MachineConstant.TEMPERATURE) {
            List<HistoryExportTemperatureVO> exportList = super.recordTemperatureMapper.getExportList(departmentId, date, null);
            for (int i = 0; i < exportList.size(); i++) {
                TemperatureHistory temperatureHistory = JSON.parseObject(exportList.get(i).getHistory(), TemperatureHistory.class);
                List<TemperatureHistoryData> histories1 = super.recordTemperatureMapper.getHistories(exportList.get(i).getMachineId());
                temperatureHistory.getHistory().add(histories1);
                List<TemperatureHistoryData> timesData = DataUtils.getTimesData(temperatureHistory.getHistory(), timeList, date);
                for (int j = 0; j < timesData.size(); j++) {
                    HistoryExportTemperatureVOO pojo = new HistoryExportTemperatureVOO();
                    pojo.setTemperature(String.valueOf(timesData.get(j).getTemperature()))
                            .setBed(exportList.get(i).getBed())
                            .setCaseNum(exportList.get(i).getCaseNum())
                            .setDepartmentName(exportList.get(i).getDepartmentName())
                            .setPatientName(exportList.get(i).getPatientName())
                            .setRoom(exportList.get(i).getRoom())
                            .setTime(DateUtil.getDateTime(timesData.get(j).getTimestamp()));
                    result.add(pojo);
                }
            }
        }
        if (type == MachineConstant.ECG) {
            List<HistoryExportEcgVO> exportList = super.recordEcgMapper.getExportList(departmentId, date, null);
            for (int i = 0; i < exportList.size(); i++) {
                EcgHistory ecgHistory = JSON.parseObject(exportList.get(i).getHistory(), EcgHistory.class);
                List<EcgHistoryData> histories1 = super.recordEcgMapper.getHistories(exportList.get(i).getMachineId());
                ecgHistory.getHistory().add(histories1);
                List<EcgHistoryData> timesData = DataUtils.getTimesData(ecgHistory.getHistory(), timeList, date);
                for (int j = 0; j < timesData.size(); j++) {
                    HistoryExportEcgVOO pojo = new HistoryExportEcgVOO();
                    pojo.setHeartRate(String.valueOf(timesData.get(j).getHeartRate()))
                            .setRespiratoryRate(String.valueOf(timesData.get(j).getRespiratoryRate()))
                            .setBed(exportList.get(i).getBed())
                            .setCaseNum(exportList.get(i).getCaseNum())
                            .setDepartmentName(exportList.get(i).getDepartmentName())
                            .setPatientName(exportList.get(i).getPatientName())
                            .setRoom(exportList.get(i).getRoom())
                            .setTime(DateUtil.getDateTime(timesData.get(j).getTimestamp()));
                    result.add(pojo);
                }
            }
        }
        if (type == MachineConstant.BLOOD) {
            List<HistoryExportBloodVO> exportList = super.recordBloodMapper.getExportList(departmentId, date, null);
            for (int i = 0; i < exportList.size(); i++) {
                BloodHistory bloodHistory = JSON.parseObject(exportList.get(i).getHistory(), BloodHistory.class);
                List<BloodHistoryData> histories1 = super.recordBloodMapper.getHistories(exportList.get(i).getMachineId());
                bloodHistory.getHistory().add(histories1);
                List<BloodHistoryData> timesData = DataUtils.getTimesData(bloodHistory.getHistory(), timeList, date);
                for (int j = 0; j < timesData.size(); j++) {
                    HistoryExportBloodVOO pojo = new HistoryExportBloodVOO();
                    pojo.setBlood(String.valueOf(timesData.get(j).getBloodOxygen()))
                            .setPi(String.valueOf(timesData.get(j).getPi()))
                            .setHeartRate(String.valueOf(timesData.get(j).getHeartRate()))
                            .setBed(exportList.get(i).getBed())
                            .setCaseNum(exportList.get(i).getCaseNum())
                            .setDepartmentName(exportList.get(i).getDepartmentName())
                            .setPatientName(exportList.get(i).getPatientName())
                            .setRoom(exportList.get(i).getRoom())
                            .setTime(DateUtil.getDateTime(timesData.get(j).getTimestamp()));
                    result.add(pojo);
                }
            }
        }
        if (type == MachineConstant.SLEEPING) {
            List<HistoryExportSleepingVO> exportList = super.recordSleepingMapper.getExportList(departmentId, date, null);
            for (int i = 0; i < exportList.size(); i++) {
                SleepingHistory sleepingHistory = JSON.parseObject(exportList.get(i).getHistory(), SleepingHistory.class);
                List<SleepingHistoryData> histories1 = super.recordSleepingMapper.getHistories(exportList.get(i).getMachineId());
                sleepingHistory.getHistory().add(histories1);
                List<SleepingHistoryData> timesData = DataUtils.getTimesData(sleepingHistory.getHistory(), timeList, date);
                for (int j = 0; j < timesData.size(); j++) {
                    HistoryExportSleepingVOO pojo = new HistoryExportSleepingVOO();
                    pojo.setSleepingState(SleepEnum.getName(timesData.get(j).getSleepState()))
                            .setHeartRate(String.valueOf(timesData.get(j).getHeartRate()))
                            .setRespiratoryRate(String.valueOf(timesData.get(j).getRespiratoryRate()))
                            .setBed(exportList.get(i).getBed())
                            .setCaseNum(exportList.get(i).getCaseNum())
                            .setDepartmentName(exportList.get(i).getDepartmentName())
                            .setPatientName(exportList.get(i).getPatientName())
                            .setRoom(exportList.get(i).getRoom())
                            .setTime(DateUtil.getDateTime(timesData.get(j).getTimestamp()));
                    result.add(pojo);
                }
            }
        }
        return result;
    }

    @Override
    public List getPrivateExportHistoryList(Integer type, Integer recordId, Object data) {
        List result = new ArrayList<>();
        if (type == MachineConstant.TEMPERATURE) {
            List<HistoryExportTemperatureVO> exportList = super.recordTemperatureMapper.getExportList(null, null, recordId);
            TemperatureHistory temperatureHistory = (TemperatureHistory) data;
            List<TemperatureHistoryData> list = DataUtils.concatList(temperatureHistory.getHistory());
            for (int j = 0; j < list.size(); j++) {
                HistoryExportTemperatureVOO pojo = new HistoryExportTemperatureVOO();
                pojo.setTemperature(String.valueOf(list.get(j).getTemperature()))
                        .setBed(exportList.get(0).getBed())
                        .setCaseNum(exportList.get(0).getCaseNum())
                        .setDepartmentName(exportList.get(0).getDepartmentName())
                        .setPatientName(exportList.get(0).getPatientName())
                        .setRoom(exportList.get(0).getRoom())
                        .setTime(DateUtil.getDateTime(list.get(j).getTimestamp()));
                result.add(pojo);
            }
        }
        if (type == MachineConstant.ECG) {
            List<HistoryExportEcgVO> exportList = super.recordEcgMapper.getExportList(null, null, recordId);
            EcgHistory ecgHistory = (EcgHistory) data;
            List<EcgHistoryData> timesData = DataUtils.concatList(ecgHistory.getHistory());
            for (int j = 0; j < timesData.size(); j++) {
                HistoryExportEcgVOO pojo = new HistoryExportEcgVOO();
                pojo.setHeartRate(String.valueOf(timesData.get(j).getHeartRate()))
                        .setRespiratoryRate(String.valueOf(timesData.get(j).getRespiratoryRate()))
                        .setBed(exportList.get(0).getBed())
                        .setCaseNum(exportList.get(0).getCaseNum())
                        .setDepartmentName(exportList.get(0).getDepartmentName())
                        .setPatientName(exportList.get(0).getPatientName())
                        .setRoom(exportList.get(0).getRoom())
                        .setTime(DateUtil.getDateTime(timesData.get(j).getTimestamp()));
                result.add(pojo);
            }
        }
        if (type == MachineConstant.BLOOD) {
            List<HistoryExportBloodVO> exportList = super.recordBloodMapper.getExportList(null, null, recordId);
            BloodHistory bloodHistory = (BloodHistory) data;
            List<BloodHistoryData> timesData = DataUtils.concatList(bloodHistory.getHistory());
            for (int j = 0; j < timesData.size(); j++) {
                HistoryExportBloodVOO pojo = new HistoryExportBloodVOO();
                pojo.setBlood(String.valueOf(timesData.get(j).getBloodOxygen()))
                        .setPi(String.valueOf(timesData.get(j).getPi()))
                        .setHeartRate(String.valueOf(timesData.get(j).getHeartRate()))
                        .setBed(exportList.get(0).getBed())
                        .setCaseNum(exportList.get(0).getCaseNum())
                        .setDepartmentName(exportList.get(0).getDepartmentName())
                        .setPatientName(exportList.get(0).getPatientName())
                        .setRoom(exportList.get(0).getRoom())
                        .setTime(DateUtil.getDateTime(timesData.get(j).getTimestamp()));
                result.add(pojo);
            }
        }
        if (type == MachineConstant.SLEEPING) {
            List<HistoryExportSleepingVO> exportList = super.recordSleepingMapper.getExportList(null, null, recordId);
            SleepingHistory sleepingHistory = (SleepingHistory) data;
            List<SleepingHistoryData> timesData = DataUtils.concatList(sleepingHistory.getHistory());
            for (int j = 0; j < timesData.size(); j++) {
                HistoryExportSleepingVOO pojo = new HistoryExportSleepingVOO();
                pojo.setHeartRate(String.valueOf(timesData.get(j).getHeartRate()))
                        .setRespiratoryRate(String.valueOf(timesData.get(j).getRespiratoryRate()))
                        .setBed(exportList.get(0).getBed())
                        .setCaseNum(exportList.get(0).getCaseNum())
                        .setDepartmentName(exportList.get(0).getDepartmentName())
                        .setPatientName(exportList.get(0).getPatientName())
                        .setRoom(exportList.get(0).getRoom())
                        .setTime(DateUtil.getDateTime(timesData.get(j).getTimestamp()))
                        .setSleepingState(SleepEnum.getName(timesData.get(j).getSleepState()));
                result.add(pojo);
            }
        }
        return result;
    }

    @Override
    public List<HistoryExportTemperatureVOO> transFahrenheit(List<HistoryExportTemperatureVOO> data) {
        for (HistoryExportTemperatureVOO temp : data) {
            temp.setTemperature(Double.toString(DataUtils.transFahrenheit(Double.valueOf(temp.getTemperature()))));
        }
        return data;
    }
}
