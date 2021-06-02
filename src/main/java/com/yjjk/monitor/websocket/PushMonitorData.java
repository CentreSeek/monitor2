package com.yjjk.monitor.websocket;

import com.alibaba.fastjson.JSON;
import com.yjjk.monitor.configer.CommonResult;
import com.yjjk.monitor.entity.VO.monitor.MachineTypeListVO;
import com.yjjk.monitor.entity.VO.monitor.MonitorBaseVO;
import com.yjjk.monitor.entity.VO.monitor.MonitorVO;
import com.yjjk.monitor.entity.websocket.MonitorParam;
import com.yjjk.monitor.service.HospitalService;
import com.yjjk.monitor.service.MachineService;
import com.yjjk.monitor.service.MonitorService;
import com.yjjk.monitor.utility.ResultUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @program: monitor2
 * @description:
 * @author: CentreS
 * @create: 2021-05-27 09:28:31
 **/
@Component
@Configuration      // 1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling
public class PushMonitorData {
    @Lazy
    @Resource
    protected MachineService machineService;
    @Lazy
    @Resource
    protected HospitalService hospitalService;
    @Lazy
    @Resource
    protected MonitorService monitorService;

//    private static PushMonitorData pushMonitorData;
//
//
//    @PostConstruct
//    public void init() {
//        pushMonitorData = this;
//    }

    @Scheduled(cron = "*/20 * * * * ?")
//    @Scheduled(fixedDelay = 20000)
    public void pushMonitorInfo() {
        CopyOnWriteArraySet<WebSocketServer> webSocketSet = WebSocketServer.getWebSocketSet();
        webSocketSet.forEach(c -> {
            try {
                MonitorParam param = c.getParam();
                CommonResult<MonitorVO> monitors = getMonitors(param.getDepartmentId(), param.getStart(), param.getEnd());
                String result = JSON.toJSONString(monitors);
                c.sendMessage(result);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public CommonResult<MonitorVO> getMonitors(Integer departmentId,
                                               Integer start,
                                               Integer end) {
        MonitorVO monitorVO = new MonitorVO();
        List<MonitorBaseVO> monitors = monitorService.getMonitors(departmentId, start, end);
        List<MachineTypeListVO> list = machineService.getMonitorTypeList(departmentId);
        monitorVO.setMonitorVOList(monitors).setMachineTypeList(list);
        monitorVO = monitorService.setMonitorRule(monitorVO, departmentId);
        monitorVO = monitorService.setMachineState(monitorVO);
        monitorVO.setBedCount(hospitalService.getBedCount(departmentId, start, end));
        return ResultUtil.returnSuccess(monitorVO);
    }
}
