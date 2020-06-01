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

import com.yjjk.monitor.configer.CommonResult;
import com.yjjk.monitor.entity.BO.PageBO;
import com.yjjk.monitor.entity.BO.history.GetRecordsBO;
import com.yjjk.monitor.entity.VO.PagedGridResult;
import com.yjjk.monitor.entity.VO.history.RecordsHistory;
import com.yjjk.monitor.entity.VO.monitor.MonitorBaseVO;
import com.yjjk.monitor.entity.VO.monitor.MonitorVO;
import com.yjjk.monitor.entity.history.BaseData;
import com.yjjk.monitor.entity.history.TemperatureHistory;
import com.yjjk.monitor.entity.log.ManageLog;
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
     * 单人导出过滤体温数据
     * @param dataList
     * @param temperature
     * @return
     */
    TemperatureHistory filterTemperatureData(TemperatureHistory dataList,Double temperature) ;
    /**
     * 批量导出
     * @param response
     * @param type
     * @param dataList
     * @throws IOException
     */
    void export(HttpServletResponse response,Integer type, List dataList) throws IOException;
    /**
     * records-获取历史记录
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
     * @return
     */
    Object getHistoryData(Integer type, Integer recordId);

    /**
     * 获取实时历史数据
     * @param type
     * @param baseId
     * @return
     */
    List<BaseData> getMonitorData(Integer type, Integer baseId);

    /**
     * 获取导出数据
     * @param type
     * @param departmentId
     * @param date
     * @param timeList
     * @return
     */
    List getExportHistoryList(Integer type, Integer departmentId,String date,List<String> timeList);

    List getPrivateExportHistoryList(Integer type,Integer recordId,Object data);

}
