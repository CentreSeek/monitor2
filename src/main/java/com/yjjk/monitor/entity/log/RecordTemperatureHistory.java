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
public class RecordTemperatureHistory {

    private String temperature;
    private Long timestamp;

    RecordTemperatureHistory(String temperature, Long timestamp) {
        this.temperature = temperature;
        this.timestamp = timestamp;
    }

}
