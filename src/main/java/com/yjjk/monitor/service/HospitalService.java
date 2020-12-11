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
import com.yjjk.monitor.entity.hospital.Bed;
import com.yjjk.monitor.entity.hospital.Room;
import com.yjjk.monitor.entity.pojo.HospitalBed;
import com.yjjk.monitor.entity.pojo.HospitalDepartment;
import com.yjjk.monitor.entity.pojo.HospitalRoom;

import java.util.List;
import java.util.Map;

/**
 * @author CentreS
 * @Description: 医院信息
 * @create 2019/7/18
 */
public interface HospitalService {

    String checkBedsName(Room room);

    boolean checkBedName(Bed bed, Integer departmentId);

    boolean updateDepartment(Integer departmentId, String name);

    boolean addDepartment(String name, Integer managerId);

    /**
     * 能否进操作
     *
     * @param departmentId
     * @return
     */
    boolean getDepartmentCount(Integer departmentId);

    boolean canDeleteHospital(Integer departmentId);

    boolean canDeleteRoom(Integer roomId);

    boolean canDeleteRoom(Room room);

    /**
     * 删除
     *
     * @param departmentId
     * @return
     */
    void deleteHospital(Integer departmentId);

    /**
     * 更新
     *
     * @param department
     * @return
     */
//    boolean updateHospital(Department department);
    boolean updateRoom(Room room);

    String updateBeds(Room room);

    boolean addRoom(Room room, Integer departmentId);

    boolean deleteRoom(Integer roomId);

    /**
     * 获取医院名称
     *
     * @return
     */
    String getHospitalName();

    /**
     * 查询医院信息详情
     *
     * @param departmentId
     * @return
     */
    List<HospitalDepartment> selectDetail(Integer departmentId);

    /**
     * 定期并清理temperatureInfo表数据
     *
     * @param dateOfOneMonthAgo
     * @return
     */
    int temperatureInfoTask(String dateOfOneMonthAgo);

    /**
     * 保存定期并清理temperatureInfo表数据
     *
     * @param dateOfOneMonthAgo
     * @return
     */
    int temperatureInfoPersistent(String dateOfOneMonthAgo);

    /**
     * 查询科室信息
     *
     * @return
     */
    List<HospitalDepartment> selectDepartments();

    /**
     * 查询房间信息
     *
     * @return
     */
    List<HospitalRoom> selectRooms(Integer departmentId);

    /**
     * 查找空床
     *
     * @param paramMap
     * @return
     */
    List<HospitalBed> selectEmptyBeds(Map<String, Object> paramMap);

    /**
     * 获取未启用相同设备record
     *
     * @param departmentId
     * @param type
     * @return
     */
    List<HospitalBed> selectMonitorEmptyBeds(Integer departmentId, Integer type);

    /**
     * 获取床位数量数据
     *
     * @param departmentId
     * @return
     */
    int getBedCount(Integer departmentId, Integer start, Integer end);

    /**
     * 监控页面-筛选床位
     *
     * @param departmentId
     * @param bedId
     * @return
     */
    List<ListVO> getMonitorBedList(Integer departmentId, Integer bedId);

}
