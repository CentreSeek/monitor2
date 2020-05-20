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
public class RecordBloodHistory {

    private String bloodOxygen;
    private String heartRate;
    private String pi;
    private Long timestamp;

    RecordBloodHistory(String bloodOxygen, String heartRate, String pi, Long timestamp) {
        this.bloodOxygen = bloodOxygen;
        this.heartRate = heartRate;
        this.pi = pi;
        this.timestamp = timestamp;
    }

}
