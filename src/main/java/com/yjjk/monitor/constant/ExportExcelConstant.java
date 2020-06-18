package com.yjjk.monitor.constant;

/**
 * @program: monitor2
 * @description:
 * @author: CentreS
 * @create: 2020-05-22 20:37:59
 **/
public class ExportExcelConstant {

    public static final String[] TEMPERATURE_TITLE = {"姓名", "住院号", "科室", "房号", "床位", "时间点", "体温"};
    public static final String[] TEMPERATURE_TITLE_EN = {"full name", "Inpatient No", "Department", "room number", "bed number", "time", "Temp(℃)"};
    public static final String[] TEMPERATURE_TITLE_EN_F = {"full name", "Inpatient No", "Department", "room number", "bed number", "time", "Temp(℉)"};
    public static final String[] ECG_TITLE = {"姓名", "住院号", "科室", "房号", "床位", "时间点", "心率", "呼吸率"};
    public static final String[] ECG_TITLE_EN = {"full name", "Inpatient No", "Department", "room number", "bed number", "time", "HR", "RR"};
    public static final String[] BLOOD_TITLE = {"姓名", "住院号", "科室", "房号", "床位", "时间点", "血氧", "PI", "心率"};
    public static final String[] BLOOD_TITLE_EN = {"full name", "Inpatient No", "Department", "room number", "bed number", "time", "SaO2", "Perfusion index IP", "RR"};
    public static final String[] SLEEPING_TITLE = {"姓名", "住院号", "科室", "房号", "床位", "时间点", "离床状态", "心率", "呼吸率"};
    public static final String[] SLEEPING_TITLE_EN = {"full name", "Inpatient No", "Department", "room number", "bed number", "time point", "HR", "RR"};

    public static final int CN = 0;
    public static final int EN = 1;
    public static final int CENTIGRADE = 0;
    public static final int FAHRENHEIT = 1;


}

