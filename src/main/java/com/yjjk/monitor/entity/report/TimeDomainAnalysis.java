package com.yjjk.monitor.entity.report;

import lombok.Getter;
import lombok.Setter;

/**
 * 时域分析
 */
@Getter
@Setter
public class TimeDomainAnalysis {
	
	/**
	 * HRV时域均值
	 */
	private float mean ;
	
	/**
	 * HRV时域总体标准差
	 */
	private float  sdnn;
	
	/**
	 * HRV时域均值标准差
	 */
	private float  sdann;
	
	/**
	 * HRV时域标准差均值
	 */
	private float sdnni;
	
	/**
	 * HRV时域插值均方的平方
	 */
	private float rmsssd;
	
	/**
	 * HRV时域相邻间期大于50ms的百分比
	 */
	private float pnn50;
	
	/**
	 * HRV时域三角指数
	 */
	private float triangularIndex;
	
	/**
	 * HRV时域归一化后间期数
	 */
	private int[] intervalStatistics;

}
