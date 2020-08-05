package com.yjjk.monitor.entity.report;

import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @program: monitor2
 * @description: 心率图
 * @author: CentreS
 * @create: 2020-07-08 16:17:28
 **/
@Data
@Accessors(chain = true)
public class HeartRateChart {

    /**
     * ("最高心率图")
     */
    private List<HeartRateChart> higherChart;
    /**
     * ("最低心率图")
     */
    private List<HeartRateChart> lowerChart;
}
