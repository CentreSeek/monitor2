package com.yjjk.monitor.entity.report;

import lombok.Getter;
import lombok.Setter;

/**
 * 频域分析
 */
@Getter
@Setter
public class FrequencyDomainAnalysis {
	
	/**
	 *HRV频域信号总功率，VLF、LF、HF的总和
	 */
	private float  tp;
	
	/**
	 *HRV频域0.0033~0.04Hz超低频段功率
	 */
	private float  vlf;
	
	/**
	 *HRV频域0.04~0.15Hz低频段功率
	 */
	private float  lf;
	
	/**
	 *HRV频域0.15~0.4Hz高频段功率
	 */
	private float  hf;
	
	/**
	 *lf的标化
	 */
	private float  lfNorm;
	
	/**
	 *hf的标化
	 */
	private float hfNorm;
	
	/**
	 *HRV频域LF/HF
	 */
	private float  ratioLHF;	
	
	/**
	 *HRV频域输出功率谱
	 */
	private float[]  powerData;
	

}
