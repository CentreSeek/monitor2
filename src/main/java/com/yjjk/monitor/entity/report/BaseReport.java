package com.yjjk.monitor.entity.report;

import java.util.List;

import com.yjjk.monitor.entity.report.data.HeartRateData;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class BaseReport {
	
    /**("基本信息")
     * 
     */
    private UserInfo userInfo;
    
    /**
     * 心率
     */
    private HeartRate heartRate;
    
    /**
     * 室上性节律
     */
    private SupraventricularRhythm supraventricularRhythm;
    
    /**
     * 室性节律
     */
    private VentricularRhythm ventricularRhythm;
    
    /**
     * 房扑\房颤
     */
    private AtrialFibrillation atrialFibrillation;
    
    /**
     * 时域分析
     */
    private TimeDomainAnalysis timeDomainAnalysis;
    
    /**
     * 频域分析
     */
    private FrequencyDomainAnalysis frequencyDomainAnalysis;
    
    /**
     * 散点图
     */
    private ScatterPlot scatterPlot;

    /**("高低心率图")
     * 
     */
    private HeartRateChart heartRateChart;

    /**("小时统计")
     * 
     */
    private List<HeartRateData> heartRateData;
    
    /**
     * 建议
     */
    private String suggest;
}
