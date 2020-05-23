package com.yjjk.monitor.utility;


import com.yjjk.monitor.entity.history.BaseData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: monitor2
 * @description: 数据处理
 * @author: CentreS
 * @create: 2020-05-22 14:22:53
 **/
public class DataUtils {

    public static <T extends BaseData> List<T> getTimesData(List<List<T>> data, List<String> time, String date) {
        List<T> list = concatList(data);
        List<T> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < time.size(); j++) {
                Long dateTimeLong = DateUtil.getDateTimeLong(date + " " + time.get(j));
                if (list.get(i).getTimestamp() >= dateTimeLong - 60000 && list.get(i).getTimestamp() <= dateTimeLong + 60000) {
                    result.add(list.get(i));
                    break;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(DateUtil.getDateTime(1590148695099L));;
    }

    /**
     * 处理历史记录
     *
     * @param up
     * @param down
     * @return
     */
    public static List<BaseData> baseConcatData(List up, List down) {
        Map<Long, BaseData> upMap = getDataCoordinateList(up);
        Map<Long, BaseData> downMap = getDataCoordinateList(down);
        Map<Long, BaseData> longBaseDataMap = concatCoordinateList(upMap, downMap);
        return getDataBaseList(longBaseDataMap);
    }

    /**
     * 转化为返回前端数据
     *
     * @param map
     * @return
     */
    public static List<BaseData> getDataBaseList(Map<Long, BaseData> map) {
        List<BaseData> result = new ArrayList<>();
        for (Map.Entry<Long, BaseData> entry : map.entrySet()) {
            result.add(entry.getValue());
        }
        Collections.sort(result);
        return result;
    }


    /**
     * 合并坐标数据
     *
     * @param up
     * @param down
     * @return
     */
    public static Map<Long, BaseData> concatCoordinateList(Map<Long, BaseData> up, Map<Long, BaseData> down) {
        for (Map.Entry<Long, BaseData> entry : up.entrySet()) {
            down.put(entry.getKey(), entry.getValue());
        }
        return down;
    }

    /**
     * 坐标化数据
     *
     * @param list
     * @return
     */
    public static <T extends BaseData> Map<Long, BaseData> getDataCoordinateList(List<T> list) {
        if (list == null) {
            return new HashMap<>();
        }
        Map map = new HashMap();
        for (BaseData o : list) {
            map.put(getCoordinate(o.getTimestamp()), o);
        }
        return map;
    }

    /**
     * 将时间戳转化为精度为分的坐标
     *
     * @param timestamp
     * @return
     */
    public static Long getCoordinate(Long timestamp) {
        return timestamp / 60000 * 60000;
    }

    /**
     * 融合数组
     * List@param
     *
     * @return
     */
    public static <T extends BaseData> List concatList(List<List<T>> list) {
        List para = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            para.addAll(list.get(i));
        }
        return para;
    }

}
