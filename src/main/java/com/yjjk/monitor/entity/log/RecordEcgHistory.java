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
public class RecordEcgHistory {

    private String heartRate;
    private String respiratoryRate;
    private Long timestamp;

    RecordEcgHistory(String heartRate, String respiratoryRate, Long timestamp) {
        this.heartRate = heartRate;
        this.respiratoryRate = respiratoryRate;
        this.timestamp = timestamp;
    }

}
