package com.yjjk.monitor.mapper;

import com.yjjk.monitor.entity.ZsMachineTypeInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ZsMachineTypeInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ZsMachineTypeInfo record);

    int insertSelective(ZsMachineTypeInfo record);

    ZsMachineTypeInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ZsMachineTypeInfo record);

    int updateByPrimaryKey(ZsMachineTypeInfo record);

    /**
     * 查询设备名称
     * @return
     */
    List<ZsMachineTypeInfo> selectMachineTypes();

    /**
     * 查询设备型号
     * @param id
     * @return
     */
    List<ZsMachineTypeInfo> selectMachineNums(Integer id);

    /**
     * 获取体温设备名称
     * @return
     */
    List<ZsMachineTypeInfo> getTemperatureMachineName();
}