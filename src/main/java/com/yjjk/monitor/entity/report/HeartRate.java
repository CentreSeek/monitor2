package com.yjjk.monitor.entity.report;

import lombok.Getter;
import lombok.Setter;

/**
 * 心率
 */
@Getter
@Setter
public class HeartRate {
	
	/**
	 * 总心搏
	 */
	private Integer totalHeartBeat;
	
	/**
	 * 心动过缓
	 */	
	private Integer bradycardia;
	
	/**
	 * 心率不齐
	 */
	private Integer irregularHeartRate;
	
	/**
	 * 心动过速
	 */
	private Integer tachycardia;
	
	/**
	 * 逸搏
	 */
	private Integer escape;
	
	/**
	 * 停搏
	 */
	private Integer Asystole;
	
	/**
	 * RR最长期
	 */
	private double RRLongestTerm;
	
	/**
	 * RR间期
	 */
	private Integer RRInterval;
	
	/**
	 * 最高心率
	 */
	private Integer maximumHeartRate;
	
	/**
	 * 平均心率
	 */
	private Integer meanHeartRate;
	
	/**
	 * 最低心率
	 */
	private Integer minimumHeartRate;
	
	/**
	 * 最高心率发生时间
	 */
	private String highestTime;
	
	/**
	 * 最低心率发生时间
	 */
	private String minimumTime;
	
}
