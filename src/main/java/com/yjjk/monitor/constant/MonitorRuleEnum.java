package com.yjjk.monitor.constant;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: YjjkErp
 * @description: 备案进度
 * @author: CentreS
 * @create: 2019-11-25 20:56:45
 **/
public enum MonitorRuleEnum {
    OPEN(0, "开"),
    CLOSE(1, "关"),
    ALERT_WHITE(0, "白"),
    ALERT_ORANGE(1, "橙"),
    ALERT_RED(2, "红"),

    TEMPERATURE_ALERT_L(32, "L"),
    TEMPERATURE_ALERT_H(42, "H"),
    HEART_ALERT_L(40, "L"),
    HEART_ALERT_H(300, "H"),
    RESPIRATORY_ALERT_L(5, "L"),
    RESPIRATORY_ALERT_H(61, "H"),

    TEMPERATURE(0, "体温"),
    HEART_RATE(1, "心率"),
    RESPIRATORY_RATE(2, "呼吸率"),
    BLOOD_OXYGEN(3, "血氧"),
    BLOOD_PRESSURE_DIA(4, "舒张压"),
    BLOOD_PRESSURE_SYS(5, "收缩压"),


    ;

    private Integer type;

    private String name;

    MonitorRuleEnum(Integer type, String name) {
        this.type = type;
        this.name = name;
    }

    public Integer getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }

    public static String getName(Integer type) {
        for (MonitorRuleEnum r : MonitorRuleEnum.values()) {
            if (type.equals(r.getType())) {
                return r.name;
            }
        }
        return null;
    }

    public static String getJson() {
        Map<Object, Object> map = new HashMap<>();
        for (MonitorRuleEnum r : MonitorRuleEnum.values()) {
            map.put(r.type, r.name);
        }
        String s = JSON.toJSONString(map);
        return s;
    }

    public static List getList() {
        List<Map<Integer, String>> list = new ArrayList<>();
        for (MonitorRuleEnum r : MonitorRuleEnum.values()) {
            Map<Integer, String> map = new HashMap<>();
            map.put(r.type, r.name);
            list.add(map);
        }
        return list;
    }

    @Override
    public String toString() {
        return "{" +
                "type=" + type +
                ", name='" + name + '\'' +
                '}';
    }

}
