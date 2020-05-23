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
public enum RecordBaseEnum {
    USAGE_STATE_USE(0, "使用中"),
    USAGE_STATE_UN_USE(1, "未使用"),
    MACHINE_UN_USE(-1, "未使用"),

    MACHINE_STATE_UN_USED(0, "未使用"),
    MACHINE_STATE_USED(1, "使用中");

    private Integer type;

    private String name;

    RecordBaseEnum(Integer type, String name) {
        this.type = type;
        this.name = name;
    }

    public Integer getType() {
        return this.type;
    }

    public static String getName(Integer type) {
        for (RecordBaseEnum r : RecordBaseEnum.values()) {
            if (type.equals(r.getType())) {
                return r.name;
            }
        }
        return null;
    }

    public static String getJson() {
        Map<Object, Object> map = new HashMap<>();
        for (RecordBaseEnum r : RecordBaseEnum.values()) {
            map.put(r.type, r.name);
        }
        String s = JSON.toJSONString(map);
        return s;
    }

    public static List getList() {
        List<Map<Integer, String>> list = new ArrayList<>();
        for (RecordBaseEnum r : RecordBaseEnum.values()) {
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
