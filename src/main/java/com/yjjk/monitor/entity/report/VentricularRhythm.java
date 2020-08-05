package com.yjjk.monitor.entity.report;

import lombok.Getter;
import lombok.Setter;

/**
 * 室性节律
 */
@Getter
@Setter
public class VentricularRhythm {
	
	/**
	 * 室早总数
	 */
	private Integer totalNumberOfVentricularPrematureBeats;
	
	/**
	 * 单发房早
	 */
	private Integer SingleHairRoomBreakfast;
	
	/**
	 * 成对室早
	 */
	private Integer pairedVentricularPremature;
	
	/**
	 * 二联律
	 */
	private Integer DyadicLaw;
	
	/**
	 * 三联律
	 */
	private Integer Trilogy;
	
	/**
	 * 室速
	 */
	private Integer ventricularTachycardia;
}
