/**
 * Copyright (C), 2019, 义金(杭州)健康科技有限公司
 * FileName: PatientRecordService
 * Author:   CentreS
 * Date:     2019/7/22 11:41
 * Description: 历史记录
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.monitor.service;

import com.yjjk.monitor.entity.BO.PageBO;
import com.yjjk.monitor.entity.BO.history.GetRecordsBO;
import com.yjjk.monitor.entity.VO.PagedGridResult;
import com.yjjk.monitor.entity.export.history.export.HistoryExportTemperatureVOO;
import com.yjjk.monitor.entity.history.BaseData;
import com.yjjk.monitor.entity.history.TemperatureHistory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author CentreS
 * @Description: 历史记录
 * @create 2019/7/22
 */
@Service
public interface HistoryService {

    /**
     * mit16心电文件导出
     *
     * @param timestamp
     */
    String ecgExport(String timestamp,Integer baseId);

    /**
     * 单人导出过滤体温数据
     *
     * @param dataList
     * @param temperature
     * @return
     */
    TemperatureHistory filterTemperatureData(TemperatureHistory dataList, Double temperature);

    /**
     * 批量导出
     *
     * @param response
     * @param type
     * @param dataList
     * @param language
     * @param temType
     * @throws IOException
     */
    void export(HttpServletResponse response, Integer type, List dataList, Integer language, Integer temType) throws IOException;

    /**
     * records-获取历史记录
     *
     * @param pageBO
     * @param bo
     * @return
     */
    PagedGridResult getHistory(PageBO pageBO, GetRecordsBO bo);

    /**
     * 获取持久化监测数据
     *
     * @param type
     * @param recordId
     * @param temType
     * @return
     */
    Object getHistoryData(Integer type, Integer recordId, Integer temType);

    Object getHistoryData(Integer type, Integer recordId);

    /**
     * 获取实时历史数据
     *
     * @param type
     * @param baseId
     * @param temType
     * @return
     */
    List<BaseData> getMonitorData(Integer type, Integer baseId, Integer temType);

    /**
     * 获取导出数据
     *
     * @param type
     * @param departmentId
     * @param date
     * @param timeList
     * @return
     */
    List getExportHistoryList(Integer type, Integer departmentId, String date, List<String> timeList);

    List getPrivateExportHistoryList(Integer type, Integer recordId, Object data);

    /**
     * 转换华氏度
     *
     * @param data
     * @return
     */
    List<HistoryExportTemperatureVOO> transFahrenheit(List<HistoryExportTemperatureVOO> data);

}
