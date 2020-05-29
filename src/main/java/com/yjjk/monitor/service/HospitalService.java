/**
 * Copyright (C), 2019, 义金(杭州)健康科技有限公司
 * FileName: HospitalService
 * Author:   CentreS
 * Date:     2019/7/18 17:19
 * Description: 医院信息
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.monitor.service;

import com.yjjk.monitor.entity.ListVO;
import com.yjjk.monitor.entity.pojo.HospitalBed;
import com.yjjk.monitor.entity.pojo.HospitalDepartment;
import com.yjjk.monitor.entity.pojo.HospitalRoom;

import java.util.List;
import java.util.Map;

/**
 * @Description: 医院信息
 * @author CentreS
 * @create 2019/7/18
 */
public interface HospitalService {

    /**
     * 查询医院信息详情
     * @param departmentId
     * @return
     */
    List<HospitalDepartment> selectDetail(Integer departmentId);

    /**
     * 定期并清理temperatureInfo表数据
     * @param dateOfOneMonthAgo
     * @return
     */
    int temperatureInfoTask(String dateOfOneMonthAgo);

    /**
     * 保存定期并清理temperatureInfo表数据
     * @param dateOfOneMonthAgo
     * @return
     */
    int temperatureInfoPersistent(String dateOfOneMonthAgo);

    /**
     * 查询科室信息
     * @return
     */
    List<HospitalDepartment> selectDepartments();

    /**
     * 查询房间信息
     * @return
     */
    List<HospitalRoom> selectRooms(Integer departmentId);

    /**
     * 查找空床
     * @param paramMap
     * @return
     */
    List<HospitalBed> selectEmptyBeds(Map<String, Object> paramMap);

    /**
     * 获取未启用相同设备record
     * @param departmentId
     * @param type
     * @return
     */
    List<HospitalBed> selectMonitorEmptyBeds(Integer departmentId,Integer type);

    /**
     * 获取床位数量数据
     * @param departmentId
     * @return
     */
    int getBedCount(Integer departmentId);

    /**
     * 监控页面-筛选床位
     * @param departmentId
     * @param bedId
     * @return
     */
    List<ListVO> getMonitorBedList(Integer departmentId, Integer bedId);
}
