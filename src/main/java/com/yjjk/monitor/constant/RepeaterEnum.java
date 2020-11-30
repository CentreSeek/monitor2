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
public enum RepeaterEnum {
    BIND(0, "已关联"),
    UNBIND_ROOM(1, "未关联房间"),
    UNBIND_DEPARTMENT(2, "未关联科室")
    ;

    private Integer type;

    private String name;

    RepeaterEnum(Integer type, String name) {
        this.type = type;
        this.name = name;
    }

    public Integer getType() {
        return this.type;
    }

    public String getValue() {
        return this.name;
    }

    public static String getName(Integer type) {
        for (RepeaterEnum r : RepeaterEnum.values()) {
            if (type.equals(r.getType())) {
                return r.name;
            }
        }
        return null;
    }

    public static String getJson() {
        Map<Object, Object> map = new HashMap<>();
        for (RepeaterEnum r : RepeaterEnum.values()) {
            map.put(r.type, r.name);
        }
        String s = JSON.toJSONString(map);
        return s;
    }

    public static List getList() {
        List<Map<Integer, String>> list = new ArrayList<>();
        for (RepeaterEnum r : RepeaterEnum.values()) {
            Map<Integer, String> map = new HashMap<>();
            map.put(r.type, r.name);
            list.add(map);
        }
        return list;
    }

    public static List getListVO() {
        List<ListVO> list = new ArrayList<>();
        for (RepeaterEnum r : RepeaterEnum.values()) {
            ListVO pojo = new ListVO();
            pojo.setId(r.type).setValue(r.name);
            list.add(pojo);
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
