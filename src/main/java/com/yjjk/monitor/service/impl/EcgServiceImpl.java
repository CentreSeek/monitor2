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
import com.yjjk.monitor.entity.transaction.BackgroundResult;
import com.yjjk.monitor.entity.transaction.BackgroundSend;
import com.yjjk.monitor.service.BaseService;
import com.yjjk.monitor.service.EcgService;
import com.yjjk.monitor.utility.DateUtil;
import com.yjjk.monitor.utility.NetUtils;
import com.yjjk.monitor.utility.StringUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.net.ConnectException;

/**
 * @author CentreS
 * @Description: 历史记录
 * @create 2019/7/22
 */
@Service
public class EcgServiceImpl extends BaseService implements EcgService {

//    @Override
//    public List<UseMachineVO> getMonitorsInfo(Integer departmentId) {
//        List<UseMachineVO> monitorsInfoForEcg = this.ZsPatientRecordMapper.getMonitorsInfoForEcg(departmentId);
//        return monitorsInfoForEcg;
//    }

//    @Override
//    public List<EcgMonitorVO> updateUseMachine(List<UseMachineVO> monitorsInfo, Integer departmentId) {
//        List<EcgMonitorVO> list = new ArrayList();
//        List<ZsHealthInfo> healthInfo = this.zsHealthInfoMapper.getHealthInfo(departmentId);
//        for (int i = 0; i < monitorsInfo.size(); i++) {
//            EcgMonitorVO ecgMonitorVO = ReflectUtils.transformToBean(monitorsInfo.get(i), EcgMonitorVO.class);
//
//            ecgMonitorVO.setRecordState(Integer.valueOf(2));
//            if (ecgMonitorVO.getMachineId() != null) {
//                // 初始化使用时间为0：使用中
//                ecgMonitorVO.setRecordState(Integer.valueOf(0));
//                ecgMonitorVO.setUseTimes(DateUtil.timeDifferent((monitorsInfo.get(i)).getStartTime(), (monitorsInfo.get(i)).getEndTime()));
//                for (int j = 0; j < healthInfo.size(); j++) {
//                    if (ecgMonitorVO.getMachineId().intValue() == Integer.parseInt((healthInfo.get(j)).getMachineId())) {
//                        // 只展示当前记录开始后的时间
//                        if (healthInfo.get(j).getTimestamp() < DateUtil.getTimeLong(monitorsInfo.get(i).getStartTime())) {
//                            break;
//                        }
//                        ecgMonitorVO.setHeartRate((healthInfo.get(j)).getHeartRate()).setPattery((monitorsInfo.get(i)).getPattery().toString());
//                        ecgMonitorVO.setRespiratoryRate((healthInfo.get(j)).getRespiratoryRate());
//                        if (DateUtil.getCurrentTimeLong().longValue() - (healthInfo.get(j)).getTimestamp().longValue() >= 1000 * 3 * 60) {
//                            ecgMonitorVO.setRecordState(Integer.valueOf(1));
//                        } else {
//                            ecgMonitorVO.setRecordState(Integer.valueOf(0));
//                        }
//                        // 心率、呼吸率预警
//                        if (((healthInfo.get(j)).getHeartRate().doubleValue() > 100.0D) || ((healthInfo.get(j)).getHeartRate().doubleValue() < 50.0D)) {
//                            ecgMonitorVO.setHeartRateStatus(Integer.valueOf(1));
//                        } else {
//                            ecgMonitorVO.setHeartRateStatus(Integer.valueOf(0));
//                        }
//                        if (((healthInfo.get(j)).getRespiratoryRate().doubleValue() > 24.0D) || ((healthInfo.get(j)).getRespiratoryRate().doubleValue() < 12.0D)) {
//                            ecgMonitorVO.setRespiratoryRateStatus(Integer.valueOf(1));
//                        } else {
//                            ecgMonitorVO.setRespiratoryRateStatus(Integer.valueOf(0));
//                        }
//                        // 如果为三分钟前的数据则判定为 1：连接异常
//                        break;
//                    } else if (j == healthInfo.size() - 1) {
//                        ecgMonitorVO.setRecordState(Integer.valueOf(1));
//                    }
//                }
//                if (StringUtils.isNullorEmpty(healthInfo)) {
//                    ecgMonitorVO.setRecordState(Integer.valueOf(1));
//                }
//            }
//            list.add(ecgMonitorVO);
//        }
//        return list;
//    }

//    @Override
//    public List<HealthHistory> getHealthHistory(Map<String, Object> map) {
//        return this.zsHealthInfoMapper.getHealthHistory(map);
//    }
//
//    @Override
//    public HealthHistoryVO parseRateHistory(List<HealthHistory> list, HealthHistoryVO healthHistoryVO) {
//        // 初始化心率信息
//        Double heartRateMax = Double.valueOf(0.0D);
//        String heartRateMaxTime = "";
//        Double heartRateMin = Double.valueOf(1000.0D);
//        String heartRateMinTime = "";
//        BigDecimal heartRateAvg = new BigDecimal("0");
//        // 初始化呼吸率信息
//        Double respiratoryRateMax = Double.valueOf(0.0D);
//        String respiratoryRateMaxTime = "";
//        Double respiratoryRateMin = Double.valueOf(1000.0D);
//        String respiratoryRateMinTime = "";
//        BigDecimal respiratoryRateAvg = new BigDecimal("0");
////        List<HealthHistory> paraList = new ArrayList();
////        int count = 0;
//        if (StringUtils.isNullorEmpty(list)) {
//            heartRateMax = null;
//            heartRateMin = null;
//            respiratoryRateMax = null;
//            respiratoryRateMin = null;
//        } else {
//            for (HealthHistory tmp : list) {
//                // 求平均值
//                heartRateAvg = CalculateUtils.avg(heartRateAvg, new BigDecimal(tmp.getHeartRate()));
//                respiratoryRateAvg = CalculateUtils.avg(respiratoryRateAvg, new BigDecimal(tmp.getRespiratoryRate()));
//                // 心率最大值和最小值
//                if (Double.parseDouble(tmp.getHeartRate()) > heartRateMax.doubleValue()) {
//                    heartRateMax = Double.valueOf(Double.parseDouble(tmp.getHeartRate()));
//                    heartRateMaxTime = tmp.getDateTime();
//                }
//                if (Double.parseDouble(tmp.getHeartRate()) < heartRateMin.doubleValue()) {
//                    heartRateMin = Double.valueOf(Double.parseDouble(tmp.getHeartRate()));
//                    heartRateMinTime = tmp.getDateTime();
//                }
//
//                // 呼吸率最大值和最小值
//                if (Double.parseDouble(tmp.getRespiratoryRate()) > respiratoryRateMax.doubleValue()) {
//                    respiratoryRateMax = Double.valueOf(Double.parseDouble(tmp.getRespiratoryRate()));
//                    respiratoryRateMaxTime = tmp.getDateTime();
//                }
//                if (Double.parseDouble(tmp.getRespiratoryRate()) < respiratoryRateMin.doubleValue()) {
//                    respiratoryRateMin = Double.valueOf(Double.parseDouble(tmp.getRespiratoryRate()));
//                    respiratoryRateMinTime = tmp.getDateTime();
//                }
//
////                if (count == 0) {
////                    paraList.add(tmp);
////                }
////
////                count++;
////                if (count == 30) {
////                    count = 0;
////                }
//            }
//        }
//        healthHistoryVO
//                .setHighestHeartRate(heartRateMax).setHighestHeartRateTime(heartRateMaxTime)
//                .setLowestHeartRate(heartRateMin).setLowestHeartRateTime(heartRateMinTime)
//                .setAvgHeartRate(Double.valueOf(heartRateAvg.setScale(1, RoundingMode.HALF_UP).doubleValue()))
//                .setHighestRespiratoryRate(respiratoryRateMax).setHighestRespiratoryRateTime(respiratoryRateMaxTime)
//                .setLowestRespiratoryRate(respiratoryRateMin).setLowestRespiratoryRateTime(respiratoryRateMinTime)
//                .setAvgRespiratoryRate(Double.valueOf(respiratoryRateAvg.setScale(1, RoundingMode.HALF_UP).doubleValue()))
//                .setList(list);
//        return healthHistoryVO;
//    }
//
//    @Override
//    public HealthHistoryVO screenHistory(HealthHistoryVO healthHistoryVO, String startTime, String endTime) {
//        // 初始化时间信息
//        Long start = DateUtil.getTimeLong(startTime);
//        Long end = DateUtil.getTimeLong(endTime);
//        Long timeLong;
//        // 初始化心率信息
//        Double heartRateMax = Double.valueOf(0.0D);
//        String heartRateMaxTime = "";
//        Double heartRateMin = Double.valueOf(1000.0D);
//        String heartRateMinTime = "";
//        BigDecimal heartRateAvg = new BigDecimal("0");
//        // 初始化呼吸率信息
//        Double respiratoryRateMax = Double.valueOf(0.0D);
//        String respiratoryRateMaxTime = "";
//        Double respiratoryRateMin = Double.valueOf(1000.0D);
//        String respiratoryRateMinTime = "";
//        BigDecimal respiratoryRateAvg = new BigDecimal("0");
//        // 临时数组
//        List<HeartRateApp> heartRateApps = new ArrayList<>();
//        List<RespiratoryRateApp> respiratoryRateApps = new ArrayList<>();
//        List<HealthHistory> tempList = healthHistoryVO.getList();
//        Collections.reverse(tempList);
//        int count = 0;
//        List<HealthHistory> paraList = new ArrayList();
//        for (HealthHistory temp : tempList) {
//            timeLong = DateUtil.getTimeLong(temp.getDateTime());
//            if (timeLong >= start && timeLong <= end) {
//                // 求平均值
//                heartRateAvg = CalculateUtils.avg(heartRateAvg, new BigDecimal(temp.getHeartRate()));
//                respiratoryRateAvg = CalculateUtils.avg(respiratoryRateAvg, new BigDecimal(temp.getRespiratoryRate()));
//                // 心率最大值和最小值
//                if (Double.parseDouble(temp.getHeartRate()) > heartRateMax.doubleValue()) {
//                    heartRateMax = Double.valueOf(Double.parseDouble(temp.getHeartRate()));
//                    heartRateMaxTime = temp.getDateTime();
//                }
//                if (Double.parseDouble(temp.getHeartRate()) < heartRateMin.doubleValue()) {
//                    heartRateMin = Double.valueOf(Double.parseDouble(temp.getHeartRate()));
//                    heartRateMinTime = temp.getDateTime();
//                }
//
//                // 呼吸率最大值和最小值
//                if (Double.parseDouble(temp.getRespiratoryRate()) > respiratoryRateMax.doubleValue()) {
//                    respiratoryRateMax = Double.valueOf(Double.parseDouble(temp.getRespiratoryRate()));
//                    respiratoryRateMaxTime = temp.getDateTime();
//                }
//                if (Double.parseDouble(temp.getRespiratoryRate()) < respiratoryRateMin.doubleValue()) {
//                    respiratoryRateMin = Double.valueOf(Double.parseDouble(temp.getRespiratoryRate()));
//                    respiratoryRateMinTime = temp.getDateTime();
//                }
//                if (count == 0) {
//                    HeartRateApp heartRateApp = new HeartRateApp();
//                    RespiratoryRateApp respiratoryRateApp = new RespiratoryRateApp();
//                    heartRateApp.setDateTime(temp.getDateTime()).setHeartRate(Double.parseDouble(temp.getHeartRate()));
//                    respiratoryRateApp.setDateTime(temp.getDateTime()).setRespiratoryRate(Double.parseDouble(temp.getRespiratoryRate()));
//                    heartRateApps.add(heartRateApp);
//                    respiratoryRateApps.add(respiratoryRateApp);
//                    paraList.add(temp);
//
//                }
//                count++;
//                if (count == 5) {
//                    count = 0;
//                }
//            }
//        }
//        healthHistoryVO
//                .setHighestHeartRate(heartRateMax == 0.0D ? null : heartRateMax).setHighestHeartRateTime(heartRateMaxTime)
//                .setLowestHeartRate(heartRateMin == 1000.0D ? null : heartRateMin).setLowestHeartRateTime(heartRateMinTime)
//                .setAvgHeartRate(Double.valueOf(heartRateAvg.setScale(1, RoundingMode.HALF_UP).doubleValue()))
//                .setHighestRespiratoryRate(respiratoryRateMax == 0.0D ? null : respiratoryRateMax).setHighestRespiratoryRateTime(respiratoryRateMaxTime)
//                .setLowestRespiratoryRate(respiratoryRateMin == 1000.0D ? null : respiratoryRateMin).setLowestRespiratoryRateTime(respiratoryRateMinTime)
//                .setAvgRespiratoryRate(Double.valueOf(respiratoryRateAvg.setScale(1, RoundingMode.HALF_UP).doubleValue()))
//                .setHeartRateAppList(heartRateApps).setRespiratoryRateAppList(respiratoryRateApps);
//        return healthHistoryVO;
//    }
//
//    @Override
//    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
//    public boolean stopEcg(ZsPatientRecord zsPatientRecord) {
//        String endTime = DateUtil.getCurrentTime();
//        Map<String, Object> map = new HashMap<>();
//        map.put("endTime", endTime);
//        map.put("recordId", zsPatientRecord.getRecordId());
//        List<HealthHistory> healthList = this.zsHealthInfoMapper.getHealthHistory(map);
//
//        ZsPatientRecord paraPatientRecord = new ZsPatientRecord();
//        if (healthList != null) {
//            paraPatientRecord.setRateHistory(JSON.toJSONString(healthList));
//        }
//        paraPatientRecord.setRecordId(zsPatientRecord.getRecordId());
//        paraPatientRecord.setUsageState(Integer.valueOf(1));
//        paraPatientRecord.setEndTime(endTime);
//        int z = this.ZsPatientRecordMapper.updateSelectiveByRecordId(paraPatientRecord);
//
//        ZsMachineInfo machineInfo = new ZsMachineInfo();
//
//        machineInfo.setMachineId(zsPatientRecord.getMachineId()).setUsageState(Integer.valueOf(0));
//        int j = this.ZsMachineInfoMapper.updateByPrimaryKeySelective(machineInfo);
//
//        String date = DateUtil.getOneMonthAgo();
//        List<String> list = this.zsEcgInfoMapper.getExportHealth(date);
//        ExcelUtils.writeToTxt(list, "\\ExportData\\EcgExport");
//        int k = this.zsEcgInfoMapper.deleteByMachineId(zsPatientRecord.getMachineId());
//        if ((z == 0) || (j == 0)) {
//            return false;
//        }
//        return true;
//    }

    //    @Override
//    public int cleanEcgExport() {
//        String path = FileUtils.getRootPath() + "\\ExportData\\EcgExport";
//        File file = new File(path);
//        String[] list = file.list();
//        Long sevenDaysAgo = Long.valueOf(DateUtil.getCurrentTimeLong().longValue() - 30*24*60*60*1000L);
//        int count = 0;
//        try {
//            for (String s : list) {
//                String fileDate = FileNameUtils.getPrefix(s);
//                if (sevenDaysAgo.longValue() > Long.valueOf(fileDate).longValue()) {
//                    File tempFile = new File(path + "\\\\" + s);
//                    FileUtils.delFile(tempFile);
//                    count++;
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return count;
//    }
    @Override
    public int cleanEcgExport() {
        return zsEcgInfoMapper.deleteEcgData(DateUtil.getCurrentTimeLong() - 30 * 24 * 60 * 60 * 1000, null);
    }

    public static void main(String[] args) {
        BigDecimal aMonth = new BigDecimal(30);
        aMonth = aMonth.multiply(new BigDecimal(24));
        aMonth = aMonth.multiply(new BigDecimal(60));
        aMonth = aMonth.multiply(new BigDecimal(60));
        aMonth = aMonth.multiply(new BigDecimal(1000));
        BigDecimal cur = new BigDecimal(System.currentTimeMillis());
        BigDecimal result = cur.subtract(aMonth);
        System.out.println(DateUtil.getDateTime(result.longValue()));
        long l = 30 * 24 * 60 * 60 * 1000L;
        System.out.println(DateUtil.getDateTime(System.currentTimeMillis() - l));
    }

//    @Override
//    public BackgroundResult connectEcgMachine(Integer machineId, Integer bedId, String connectionType) throws Exception {
//        return connectEcgMachine(machineId, bedId, connectionType, null);
//    }
//
//    @Override
//    public BackgroundResult connectEcgMachine(Integer machineId, Integer bedId, String connectionType, Integer baseId) throws Exception {
//        // 连接心电设备
//        BackgroundSend backgroundSend = new BackgroundSend();
////        Integer repeaterId = super.zsRepeaterInfoMapper.selectByBedId(bedId);
//        Integer repeaterId = 1;
//        backgroundSend.setActionId(String.valueOf(repeaterId));
//        backgroundSend.setBaseId(baseId);
//        if (StringUtils.isNullorEmpty(repeaterId)) {
//            throw new ConnectException();
//        }
//        backgroundSend.setDeviceId(String.valueOf(machineId));
//        backgroundSend.setData(connectionType);
//        String s = NetUtils.doPost(connectRepeater.getUrl(), backgroundSend);
//        logger.info("硬件服务器返回值：     " + s);
//        if (s == null || s == "500") {
//            return null;
//        }
//        return JSON.parseObject(s, BackgroundResult.class);
//    }

    @Override
    public boolean hasRepeater(Integer bedId) {
        int i = super.zsRepeaterInfoMapper.hasRepeaterCount(bedId);
        if (i == 1) {
            return true;
        }
        return false;
    }

//    @Override
//    public List<HealthHistory> deleteNullParam(List<HealthHistory> list) {
//        List<HealthHistory> result = new ArrayList<>();
//        for (HealthHistory healthHistory : list) {
//            if (Double.parseDouble(healthHistory.getHeartRate()) != 0 && Double.parseDouble(healthHistory.getRespiratoryRate()) != 0) {
//                result.add(healthHistory);
//            }
//        }
//        return result;
//    }
}
