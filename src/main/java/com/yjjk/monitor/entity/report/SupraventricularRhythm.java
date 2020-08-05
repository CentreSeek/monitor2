package com.yjjk.monitor.entity.report;

import java.util.List;

import com.yjjk.monitor.entity.report.data.HeartRateData;

import lombok.Getter;
import lombok.Setter;

/**
 * 室上性节律
 */
@Getter
@Setter
public class SupraventricularRhythm {
	
	/**
	 * 房早总数
	 */
	private Integer TotalRoomNumber;
	/**
	 * 单发房早
	 */
	private Integer SingleHairRoomBreakfast;
	/**
	 * 成对房早
	 */
	private Integer PrematureInPairs;
	/**
	 * 二联律
	 */
	private Integer DyadicLaw;
	/**
	 * 三联律
	 */
	private Integer Trilogy;
	/**
	 * 室上速
	 */
	private Integer SupraventricularTachycardia;
}
