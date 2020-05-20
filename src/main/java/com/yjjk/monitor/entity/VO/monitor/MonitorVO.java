package com.yjjk.monitor.entity.VO.monitor;

import com.alibaba.fastjson.JSON;
import com.yjjk.monitor.entity.ListVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: monitor2
 * @description: 监控页面
 * @author: CentreS
 * @create: 2020-05-11 16:54:39
 **/
@Data
@Accessors(chain = true)
@ApiModel("监控页面")
public class MonitorVO {

    @ApiModelProperty(value = "monitor：监控信息列表")
    private List<MonitorBaseVO> monitorVOList;
    @ApiModelProperty(value = "设备概况：设备类型")
    private List<ListVO> machineTypeList;
    @ApiModelProperty(value = "设备概况：设备电量异常")
    private List<MachinesInfoVO> machinesInfoVOList;

//    public static void main(String[] args) {
//        MonitorVO base = new MonitorVO();
//
//        /******* 监控信息 ***********/
//        List<MonitorBaseVO> l1 = new ArrayList<>();
//        MonitorBaseVO a1 = new MonitorBaseVO();
//        MonitorBloodVO b1 = new MonitorBloodVO();
//        b1.setBattery(99).setBloodOxygen("70").setBloodOxygenAlert(0).setMachineId(1).setMachineType(1).setPi(1).setRecordId(1).setRecordState(1).setUseTimes("12hour");
//        MonitorBreathRateVO b2 = new MonitorBreathRateVO();
//        b2.setBattery(99).setBreath("70").setBreathAlert(0).setMachineId(1).setMachineType(1).setRecordId(1).setRecordState(1).setUseTimes("12hour");
//        MonitorHeartRateVO b3 = new MonitorHeartRateVO();
//        b3.setBattery(99).setHeart("70").setHeartAlert(0).setMachineId(1).setMachineType(1).setRecordId(1).setRecordState(1).setUseTimes("12hour");
//        MonitorTemperatureVo b4 = new MonitorTemperatureVo();
//        b4.setBattery(99).setTemperature("70").setTemperatureAlert(0).setMachineId(1).setMachineType(1).setRecordId(1).setRecordState(1).setUseTimes("12hour");
//        a1.setBedId(1).setBedName("一号床").setCaseNum("binglihao").setErrorStatus(0).setPatientName("bingren1").setSleepingLeaveTimes("12小时").setSleepingStatus(0).setMonitorBloodVO(b1).setMonitorBreathRateVO(b2).setMonitorHeartRateVO(b3).setMonitorTemperatureVo(b4);
//        l1.add(a1);
//        MonitorBaseVO a2 = new MonitorBaseVO();
//
//
//        /************ 设备异常  ************/
//        List<MachinesInfoVO> ml = new ArrayList<>();
//        MachinesInfoVO machinesInfoVO1 = new MachinesInfoVO();
//        machinesInfoVO1.setMachineType("体温贴").setBedName("07床").setText("请更换底座电池");
//        ml.add(machinesInfoVO1);
//        MachinesInfoVO machinesInfoVO2 = new MachinesInfoVO();
//        machinesInfoVO2.setMachineType("心电贴").setBedName("11床").setText("请充电");
//        ml.add(machinesInfoVO2);
//        base.setMachinesInfoVOList(ml);
//
//        List<ListVO> l2 = new ArrayList<>();
//        ListVO m1 = new ListVO();
//        m1.setId(6).setValue("体温设备");
//        ListVO m2 = new ListVO();
//        m2.setId(7).setValue("心电设备");
//        l2.add(m1);
//        l2.add(m2);
//
//        base.setMachineTypeList(l2).setMonitorVOList(l1);
//
//        String s = JSON.toJSONString(base);
//        System.out.println(s);
//    }
}

