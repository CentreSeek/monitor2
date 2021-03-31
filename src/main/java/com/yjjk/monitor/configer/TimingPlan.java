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
import com.yjjk.monitor.controller.BaseController;
import com.yjjk.monitor.entity.pojo.ZsEcgInfo;
import com.yjjk.monitor.mapper.ZsEcgInfoMapper;
import com.yjjk.monitor.service.EcgService;
import com.yjjk.monitor.service.HospitalService;
import com.yjjk.monitor.utility.DateUtil;
import com.yjjk.monitor.websocket.WebSocketServer;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@Configuration      // 1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling /** 2.开启定时任务 */
/**
 * @author CentreS
 * @Description: 定时批量过期预约
 * @create 2019-06-21
 */
public class TimingPlan{

    protected static Logger logger = Logger.getLogger(BaseController.class);
    @Resource
    private ZsEcgInfoMapper zsEcgInfoMapper;
    @Resource
    private EcgService ecgService;
    /**
     * 实时心电数据推送
     */
    @Scheduled(cron = "*/1 * * * * ?")
    private void pushEcgInfo() {
        CopyOnWriteArraySet<WebSocketServer> webSocketSet =
                WebSocketServer.getWebSocketSet();
//        int i = 0;
        webSocketSet.forEach(c -> {
            try {
                Map<String, Object> paraMap = new HashMap<>();
                Long currentTime = System.currentTimeMillis();
                if(c.getTimeStamp() == 0){
                    c.setTimeStamp(currentTime);
                }
                paraMap.put("machineId", c.getMachineId());
                paraMap.put("timestamp", c.getTimeStamp());
                List<ZsEcgInfo> newEcg = zsEcgInfoMapper.getNewEcg(paraMap);
                for (int i = 0; i < newEcg.size(); i++) {
                    if ((currentTime - newEcg.get(i).getTimestamp()) < 15 * 1000) {
                        c.getQueue().offer(JSONArray.toJSONString(newEcg.get(i)));
                        c.setTimeStamp(newEcg.get(i).getTimestamp());
                    }
                }
                if (!c.getQueue().isEmpty()) {
                    c.sendMessage(c.getQueue().poll());
                } else {
                    c.sendMessage("");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
