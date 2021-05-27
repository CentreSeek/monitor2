/**
 * Copyright (C), 2019, 义金(杭州)健康科技有限公司
 * FileName: SaticScheduleTask
 * Author:   CentreS
 * Date:     2019-06-21 17:55
 * Description: 定时批量过期预约
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.monitor.configer;

import com.alibaba.fastjson.JSONArray;
import com.vivalnk.sdk.Mit16Util;
import com.yjjk.monitor.controller.BaseController;
import com.yjjk.monitor.entity.pojo.PatientInfo;
import com.yjjk.monitor.entity.pojo.RecordBase;
import com.yjjk.monitor.entity.pojo.RecordEcg;
import com.yjjk.monitor.entity.pojo.ZsEcgInfo;
import com.yjjk.monitor.entity.pojo.ZsMachineInfo;
import com.yjjk.monitor.service.BaseService;
import com.yjjk.monitor.utility.DataUtils;
import com.yjjk.monitor.utility.DateUtil;
import com.yjjk.monitor.utility.StringUtils;
import com.yjjk.monitor.websocket.WebSocketServer;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@Configuration      // 1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling
/** 2.开启定时任务 */
/**
 * @author CentreS
 * @Description: 定时批量过期预约
 * @create 2019-06-21
 */
public class TimingPlan extends BaseService {

    protected static Logger logger = Logger.getLogger(BaseController.class);
//    @Resource
//    private ZsEcgInfoMapper zsEcgInfoMapper;
//    @Resource
//    private EcgService ecgService;

    /**
     * 实时心电数据推送
     */
//    @Scheduled(cron = "*/1 * * * * ?")
//    private void pushEcgInfo() {
//        CopyOnWriteArraySet<WebSocketServer> webSocketSet =
//                WebSocketServer.getWebSocketSet();
////        int i = 0;
//        webSocketSet.forEach(c -> {
//            try {
//                Map<String, Object> paraMap = new HashMap<>();
//                Long currentTime = System.currentTimeMillis();
//                if (c.getTimeStamp() == 0) {
//                    c.setTimeStamp(currentTime);
//                }
//                paraMap.put("machineId", c.getMachineId());
//                paraMap.put("timestamp", c.getTimeStamp());
//                List<ZsEcgInfo> newEcg = zsEcgInfoMapper.getNewEcg(paraMap);
//                for (int i = 0; i < newEcg.size(); i++) {
//                    if ((currentTime - newEcg.get(i).getTimestamp()) < 15 * 1000) {
//                        c.getQueue().offer(JSONArray.toJSONString(newEcg.get(i)));
//                        c.setTimeStamp(newEcg.get(i).getTimestamp());
//                    }
//                }
//                if (!c.getQueue().isEmpty()) {
//                    c.sendMessage(c.getQueue().poll());
//                } else {
//                    c.sendMessage("");
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//    }

//    @Scheduled(cron = "0 0 0 * * ? ")
////    @Scheduled(cron = "0 * * * * ? ")
//    private void exportMit16() {
//        System.out.println("执行定时任务=====================================================");
//        String lastDay = DateUtil.format(DateUtil.modifyDateTime(DateUtil.getCurrentTime(), Calendar.DATE, -1), "yyyy-MM-dd");
//        List<RecordEcg> records = recordEcgMapper.getExportEcgRecordIds(lastDay);
//        for (RecordEcg recordEcg : records) {
//            RecordBase recordBase = recordBaseMapper.selectByPrimaryKey(recordEcg.getBaseId());
//            ZsMachineInfo machineInfo = zsMachineInfoMapper.getByMachineId(recordEcg.getMachineId());
//            PatientInfo patientInfo = patientInfoMapper.selectByPrimaryKey(recordBase.getPatientId());
//            List<ZsEcgInfo> ecgs = zsEcgInfoMapper.getEcgs(machineInfo.getMachineId(), DateUtil.getDateTimeLong(lastDay + " 00:00:00"), DateUtil.getDateTimeLong(lastDay + " 24:00:00"));
//            int[] ints = DataUtils.parseData(ecgs);
//            if (!StringUtils.isNullorEmpty(ecgs)) {
//                Mit16Util.writeMit16File(patientInfo.getCaseNum(), ecgs.get(0).getTimestamp(), ints);
//            }
////            CompressDownloadUtil.compressEcgAsZip(path, response);
//        }
//        logger.info("执行定时任务，导出：" + records.size() + "条");
//    }


}
