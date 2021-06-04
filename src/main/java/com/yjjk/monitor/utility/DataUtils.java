package com.yjjk.monitor.utility;


import com.alibaba.fastjson.JSON;
import com.yjjk.monitor.constant.EcgConstant;
import com.yjjk.monitor.entity.history.BaseData;
import com.yjjk.monitor.entity.pojo.ZsEcgInfo;

import java.math.BigDecimal;
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

    public static int[] parseData(List<ZsEcgInfo> data) {
        if (StringUtils.isNullorEmpty(data)) {
            return new int[0];
        }
        Integer count = Math.toIntExact(data.get(data.size() - 1).getTimestamp() - data.get(0).getTimestamp()) / 1000;
        List<Integer> result = new ArrayList<>(count);
        Long startTime = data.get(0).getTimestamp();
        Integer dataIndex = 0;
        for (int i = 0; i < count; i++) {
            Long endTime = startTime + 1000;
            if (data.get(dataIndex).getTimestamp() >= startTime && data.get(dataIndex).getTimestamp() < endTime) {
                result.addAll(transDoubleArrToIntegerArr(data.get(dataIndex).getEcg()));
                dataIndex++;
            } else {
                result.addAll(EcgConstant.getECG_NULL_DATA());
            }
            startTime += 1000;
        }
        result.toArray(new Integer[result.size()]);
        return result.stream().mapToInt(Integer::valueOf).toArray();
    }

    static List<Integer> transDoubleArrToIntegerArr(String data) {
        List<Double> dataArr = JSON.parseArray(data, Double.class);
        return transDoubleArrToIntegerArr(dataArr);
    }

    static List<Integer> transDoubleArrToIntegerArr(List<Double> data) {
        List<Integer> result = new ArrayList<>(data.size());
        for (Double datum : data) {
            result.add(datum.intValue());
        }
        return result;
    }

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

    public static <T extends BaseData> List<T> getTimesData(List<List<T>> data, String date) {
        List<T> list = concatList(data);
        List<T> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            result.add(list.get(i));
        }
        return result;
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

    public static Double transFahrenheit(Double temperature) {
        BigDecimal a = new BigDecimal(temperature);
        BigDecimal add = a.multiply(new BigDecimal(9)).divide(new BigDecimal(5)).add(new BigDecimal(32));
        return add.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 将map转化为二维数组
     *
     * @return
     */
    public static String[][] map2Arr(Map<String, Integer> map) {
        String[][] arr = new String[map.size()][2];
        int count = 0;
        for (String s : map.keySet()) {
            arr[count][0] = s;
            arr[count][1] = map.get(s).toString();
            count++;
        }
        return arr;
    }
}
