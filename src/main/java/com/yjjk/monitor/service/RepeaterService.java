/**
 * Copyright (C), 2019, 寻央大人留书
 * FileName: RepeaterService
 * Author:   CentreS
 * Date:     2019/8/23 9:56
 * Description: 中继器管理模块
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.monitor.service;

import com.yjjk.monitor.entity.pojo.MachineTypeInfo;
import com.yjjk.monitor.entity.pojo.ZsRepeaterInfo;

import java.util.List;

/**
 * @Description: 中继器管理模块
 * @author CentreS
 * @create 2019/8/23
 */
public interface RepeaterService {


    /**
     * 新增路由
     * @return
     * @throws Exception
     */
    boolean addRepeater() throws Exception;
    /**
     * 查询设备名称
     * @return
     */
    List<MachineTypeInfo> selectMachineTypes();

    /**
     * 查询设备型号
     * @param id
     * @return
     */
    List<MachineTypeInfo> selectMachineNums(Integer id);


    /**
     * 新增路由
     * @param repeater
     * @return
     */
    int insertSelective(ZsRepeaterInfo repeater);

    /**
     * 启用路由
     * @param record
     * @return
     */
    int startRepeater(ZsRepeaterInfo record);

    /**
     * 停用路由
     * @param id
     * @return
     */
    int stopRepeater(Integer id, String remark);


    /**
     * 分页查询路由信息
     * @param record
     * @return
     */
    List<ZsRepeaterInfo> selectRepeaters(ZsRepeaterInfo record);

    /**
     * 查询路由总数
     * @param record
     * @return
     */
    int selectRepeaterCount(ZsRepeaterInfo record);
}
