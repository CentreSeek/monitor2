package com.yjjk.monitor.constant;

import com.alibaba.fastjson.JSON;
import com.yjjk.monitor.entity.ListVO;

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
public enum MonitorEnum {
    TEMPERATURE(0, "体温"),
    HEART_RATE(1, "心率"),
    RESPIRATORY_RATE(2, "呼吸率"),
    BLOOD_OXYGEN(3, "血氧"),
    ;

    private Integer type;

    private String name;

    MonitorEnum(Integer type, String name) {
        this.type = type;
        this.name = name;
    }

    public Integer getType() {
        return this.type;
    }

    public static String getName(Integer type) {
        for (MonitorEnum r : MonitorEnum.values()) {
            if (type.equals(r.getType())) {
                return r.name;
            }
        }
        return null;
    }

    public static String getJson() {
        Map<Object, Object> map = new HashMap<>();
        for (MonitorEnum r : MonitorEnum.values()) {
            map.put(r.type, r.name);
        }
        String s = JSON.toJSONString(map);
        return s;
    }

    public static List getList() {
        List<Map<Integer, String>> list = new ArrayList<>();
        for (MonitorEnum r : MonitorEnum.values()) {
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
