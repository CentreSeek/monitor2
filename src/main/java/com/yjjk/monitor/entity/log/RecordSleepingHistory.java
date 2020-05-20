package com.yjjk.monitor.entity.log;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @program: monitor2
 * @description: 操作日志
 * @author: CentreS
 * @create: 2020-05-18 09:25:50
 **/
@Data
@Accessors(chain = true)
public class RecordSleepingHistory {

    private String heartRate;
    private String respiratoryRate;
    private Integer sleepState;
    private Long timestamp;

    RecordSleepingHistory(String heartRate, String respiratoryRate, Integer sleepState, Long timestamp) {
        this.heartRate = heartRate;
        this.respiratoryRate = respiratoryRate;
        this.sleepState = sleepState;
        this.timestamp = timestamp;
    }

}
