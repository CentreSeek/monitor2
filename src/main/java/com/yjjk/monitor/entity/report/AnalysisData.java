package com.yjjk.monitor.entity.report;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * 时域分析和频域分析所需参数
 * 
 *
 */
@Getter
@Setter
public class AnalysisData {
	
	/**
	 * 时域频域分析所需参数
	 */
	private List<Integer> timeDomainAnalysisList;
	
	/**
	 * 时域频域分析所需参数(rrInterval)
	 */
	private List<Integer> frequencyDomainAnalysisList;

}
