/**
 * Copyright (C), 2019, 义金(杭州)健康科技有限公司
 * FileName: HospitalServiceImpl
 * Author:   CentreS
 * Date:     2019/7/18 17:19
 * Description: 医院信息
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.monitor.service.impl;

import com.yjjk.monitor.constant.CommonConstant;
import com.yjjk.monitor.constant.ExportConstant;
import com.yjjk.monitor.entity.ListVO;
import com.yjjk.monitor.entity.hospital.Bed;
import com.yjjk.monitor.entity.hospital.Room;
import com.yjjk.monitor.entity.pojo.HospitalBed;
import com.yjjk.monitor.entity.pojo.HospitalDepartment;
import com.yjjk.monitor.entity.pojo.HospitalRoom;
import com.yjjk.monitor.service.BaseService;
import com.yjjk.monitor.service.HospitalService;
import com.yjjk.monitor.utility.ExcelUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author CentreS
 * @Description: 医院信息
 * @create 2019/7/18
 */
@Service
public class HospitalServiceImpl extends BaseService implements HospitalService {


    @Override
    public String checkBedsName(Room room) {
        List<Bed> beds = room.getBeds();
        StringBuilder result = new StringBuilder();
        for (Bed bed : beds) {
            boolean usingBedCount = checkBedName(bed, room.getDepartmentId());
            if (!usingBedCount) {
                result.append(bed.getName()).append(",");
            }
        }
        if (result.length() == 0) {
            return null;
        }
        return result.toString();
    }

    @Override
    public boolean checkBedName(Bed bed, Integer departmentId) {
        Integer usingBedCount = hospitalBedMapper.getUsingBedCount(departmentId, bed.getName(), bed.getBedId());
        if (usingBedCount > 0) {
            return false;
        }
        return true;
    }

    @Override
    public boolean updateDepartment(Integer departmentId, String name) {
        Integer count = hospitalDepartmentMapper.checkDepartmentName(name);
        if (count != 0) {
            return false;
        }
        HospitalDepartment pojo = new HospitalDepartment();
        pojo.setDepartmentId(departmentId);
        pojo.setName(name);
        hospitalDepartmentMapper.updateByPrimaryKeySelective(pojo);
        return true;
    }

    @Override
    public boolean addDepartment(String name, Integer managerId) {
        Integer count = hospitalDepartmentMapper.checkDepartmentName(name);
        if (count != 0) {
            return false;
        }
        HospitalDepartment pojo = new HospitalDepartment();
        pojo.setName(name);
        hospitalDepartmentMapper.insertSelective(pojo);
        monitorRuleMapper.insertRules(pojo.getDepartmentId(), managerId);
        return true;
    }

    @Override
    public boolean getDepartmentCount(Integer departmentId) {
        Example example = new Example(HospitalDepartment.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("status", CommonConstant.STATUS_0);
        criteria.andNotEqualTo("departmentId", 0);
        List<HospitalDepartment> hd = hospitalDepartmentMapper.selectByExample(example);
        if (hd.size() > 0) {
            return false;
        }
        return true;
    }

    @Override
    public boolean canDeleteHospital(Integer departmentId) {
        boolean canDelete = true;
        Integer usingMachinesCount = super.zsMachineInfoMapper.getUsingMachinesCount(departmentId, null);
        if (usingMachinesCount != null && usingMachinesCount != 0) {
            canDelete = false;
        }
        return canDelete;
    }

    @Override
    public boolean canDeleteRoom(Integer roomId) {
        boolean canDelete = true;
        Integer usingMachinesCount = super.zsMachineInfoMapper.getUsingMachinesCount(null, roomId);
        if (usingMachinesCount != null && usingMachinesCount != 0) {
            canDelete = false;
        }
        return canDelete;
    }

    @Override
    public boolean canDeleteRoom(Room room) {
        Example example = new Example(HospitalBed.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("roomId", room.getRoomId());
        criteria.andEqualTo("status", CommonConstant.STATUS_0);
        List<HospitalBed> hospitalBeds = hospitalBedMapper.selectByExample(example);
        Set<Integer> deleteBedIds = new HashSet();
        for (HospitalBed hospitalBed : hospitalBeds) {
            deleteBedIds.add(hospitalBed.getBedId());
        }
        // 更新床位
        List<Bed> beds = room.getBeds();
        for (Bed bed : beds) {
            if (bed.getBedId() != null && deleteBedIds.contains(bed.getBedId())) {
                deleteBedIds.remove(bed.getBedId());
            }
        }
        int cantDeleteCount = 0;
        for (Integer deleteBedId : deleteBedIds) {
            cantDeleteCount += hospitalBedMapper.getRecordBedCounts(deleteBedId);
        }
        if (cantDeleteCount > 0) {
            return false;
        }
        return true;
    }

    @Override
    public void deleteHospital(Integer departmentId) {
        List<HospitalDepartment> hospitalDepartments = hospitalDepartmentMapper.selectDetail(departmentId);
        HospitalDepartment department = hospitalDepartments.get(0);
        // 解绑设备
        zsMachineInfoMapper.unbind(departmentId, null);
        // 解绑路由
        zsRepeaterInfoMapper.unbind(departmentId, null);
        // 删除管理员
        managerInfoMapper.unbind(departmentId);
        int status = CommonConstant.STATUS_0;
        List<HospitalRoom> rooms = department.getRooms();
        if (rooms == null) {
            department.setRooms(new ArrayList<>());
        }
        for (HospitalRoom room : rooms) {
            if (room.getStatus() == null) {
                room.setStatus(CommonConstant.STATUS_0);
            }
            if (room.getStatus() == CommonConstant.STATUS_1) {
                status = room.getStatus();
            }
            hospitalRoomMapper.updateByPrimaryKeySelective(room);
            room.setStatus(status);
            List<HospitalBed> beds = room.getBeds();
            if (beds == null) {
                room.setBeds(new ArrayList<>());
            }
            for (HospitalBed bed : beds) {
                if (bed.getStatus() == null) {
                    bed.setStatus(CommonConstant.STATUS_0);
                }
                if (bed.getStatus() != CommonConstant.STATUS_1) {
                    bed.setStatus(status);
                }
                hospitalBedMapper.updateByPrimaryKeySelective(bed);
            }
        }
        // 删除科室
        HospitalDepartment hospitalDepartment = new HospitalDepartment();
        hospitalDepartment.setDepartmentId(departmentId);
        hospitalDepartment.setStatus(CommonConstant.STATUS_1);
        hospitalDepartmentMapper.updateByPrimaryKeySelective(hospitalDepartment);
    }


//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public boolean updateHospital(Department department) {
//        // 更新房间
//        List<Room> rooms = department.getRooms();
//        for (Room room : rooms) {
//            HospitalRoom tempRoom = new HospitalRoom();
//            tempRoom.setDepartmentId(department.getDepartmentId());
//            tempRoom.setName(room.getName());
//            if (room.getRoomId() == null) {
//                hospitalRoomMapper.insertSelective(tempRoom);
//                room.setRoomId(tempRoom.getRoomId());
//            }
//            tempRoom.setRoomId(room.getRoomId());
//            tempRoom.setStatus(room.getStatus());
//            hospitalRoomMapper.updateByPrimaryKeySelective(tempRoom);
//            if (CommonConstant.STATUS_1 == room.getStatus()) {
//                ZsMachineInfo pojo = new ZsMachineInfo();
//                pojo.setDepartmentId(HospitalConstant.ID_DEFAULT_NULL);
//                pojo.setMachineId()
//                // 解绑设备
//                zsMachineInfoMapper.updateByMachineId(pojo);
//                // 解绑路由
//                zsRepeaterInfoMapper.unbind(null, room.getRoomId());
//            }
//            // 更新床位
//            List<Bed> beds = room.getBeds();
//            for (Bed bed : beds) {
//                HospitalBed tempBed = new HospitalBed();
//                tempBed.setRoomId(room.getRoomId());
//                tempBed.setName(bed.getName());
//                if (bed.getBedId() == null) {
//                    hospitalBedMapper.insertSelective(tempBed);
//                    bed.setBedId(tempBed.getBedId());
//                }
//                tempBed.setBedId(bed.getBedId());
//                tempBed.setStatus(bed.getStatus());
//                hospitalBedMapper.updateByPrimaryKeySelective(tempBed);
//            }
//        }
//        return true;
//    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateRoom(Room room) {
        // 房间查重
        Example example = new Example(HospitalRoom.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("departmentId", room.getDepartmentId());
        criteria.andEqualTo("status", CommonConstant.STATUS_0);
        List<HospitalRoom> hospitalRooms = hospitalRoomMapper.selectByExample(example);
        for (HospitalRoom hospitalRoom : hospitalRooms) {
            if (hospitalRoom.getName().equals(room.getName()) && hospitalRoom.getRoomId() != room.getRoomId()) {
                return false;
            }
        }
        //更新房间
        HospitalRoom hr = new HospitalRoom();
        hr.setRoomId(room.getRoomId());
        hr.setName(room.getName());
        int i = hospitalRoomMapper.updateByPrimaryKeySelective(hr);
//        zsMachineInfoMapper.unbind(null, room.getRoomId());
        return true;
    }

    @Override
    public String updateBeds(Room room) {
        Example example = new Example(HospitalBed.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("roomId", room.getRoomId());
        criteria.andEqualTo("status", CommonConstant.STATUS_0);
        List<HospitalBed> hospitalBeds = hospitalBedMapper.selectByExample(example);
        Set<Integer> deleteBedIds = new HashSet();
        for (HospitalBed hospitalBed : hospitalBeds) {
            deleteBedIds.add(hospitalBed.getBedId());
        }
        StringBuilder result = new StringBuilder();
        // 更新床位
        List<Bed> beds = room.getBeds();
        for (Bed bed : beds) {
            boolean b = checkBedName(bed, room.getDepartmentId());
            if (!b) {
                result.append(bed.getName()).append(",");
                if (bed.getBedId() != null && deleteBedIds.contains(bed.getBedId())) {
                    deleteBedIds.remove(bed.getBedId());
                }
                continue;
            }
            HospitalBed tempBed = new HospitalBed();
            tempBed.setRoomId(room.getRoomId());
            tempBed.setName(bed.getName());
            if (bed.getBedId() == null) {
                // 新增
                hospitalBedMapper.insertSelective(tempBed);
                bed.setBedId(tempBed.getBedId());
            } else {
                // 更新
                if (deleteBedIds.contains(bed.getBedId())) {
                    deleteBedIds.remove(bed.getBedId());
                }
                tempBed.setBedId(bed.getBedId());
                hospitalBedMapper.updateByPrimaryKeySelective(tempBed);
            }
        }

        // 删除床位
        for (Integer deleteBedId : deleteBedIds) {
            HospitalBed tempBed = new HospitalBed();
            tempBed.setBedId(deleteBedId);
            tempBed.setStatus(CommonConstant.STATUS_1);
            hospitalBedMapper.updateByPrimaryKeySelective(tempBed);
        }
        if (result.length() != 0) {
            return result.toString();
        }
        return null;
    }

    @Override
    public boolean addRoom(Room room, Integer departmentId) {
        // 房间查重
        Example example = new Example(HospitalRoom.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("departmentId", departmentId);
        criteria.andEqualTo("status", CommonConstant.STATUS_0);
        List<HospitalRoom> hospitalRooms = hospitalRoomMapper.selectByExample(example);
        for (HospitalRoom hospitalRoom : hospitalRooms) {
            if (hospitalRoom.getName().equals(room.getName())) {
                return false;
            }
        }

        HospitalRoom tempRoom = new HospitalRoom();
        tempRoom.setDepartmentId(departmentId);
        tempRoom.setName(room.getName());
        hospitalRoomMapper.insertSelective(tempRoom);
        // 更新床位
        List<Bed> beds = room.getBeds();
        if (beds.size() != 0) {
            for (Bed bed : beds) {
                HospitalBed tempBed = new HospitalBed();
                tempBed.setRoomId(tempRoom.getRoomId());
                tempBed.setName(bed.getName());
                hospitalBedMapper.insertSelective(tempBed);
            }
        }
        return true;
    }

    @Override
    public boolean deleteRoom(Integer roomId) {
        // 更新房间
        HospitalRoom tempRoom = new HospitalRoom();
        tempRoom.setRoomId(roomId);
        tempRoom.setStatus(CommonConstant.STATUS_1);
        hospitalRoomMapper.updateByPrimaryKeySelective(tempRoom);
        // 解绑设备
//        zsMachineInfoMapper.unbind(null, roomId);
        // 解绑路由
        zsRepeaterInfoMapper.unbind(null, roomId);
        // 删除床位
        hospitalBedMapper.deleteRoom(roomId);
        return true;
    }

    @Override
    public String getHospitalName() {
        return super.hospitalDepartmentMapper.getHospitalName();
    }

    @Override
    public List<HospitalDepartment> selectDetail(Integer departmentId) {
        return super.hospitalDepartmentMapper.selectDetail(departmentId);
    }

    @Override
    public int temperatureInfoTask(String dateOfOneMonthAgo) {
        temperatureInfoPersistent(dateOfOneMonthAgo);
        return super.zsTemperatureInfoMapper.temperatureInfoTask(dateOfOneMonthAgo);
    }

    @Override
    public int temperatureInfoPersistent(String dateOfOneMonthAgo) {
        List<String> exportTemperatures = super.zsTemperatureInfoMapper.getExportTemperatures(dateOfOneMonthAgo);
        ExcelUtils.writeToTxt(exportTemperatures, ExportConstant.TEMPERATURE_EXPORT_PATH);
        return exportTemperatures.size();
    }

    @Override
    public List<HospitalDepartment> selectDepartments() {
        return super.hospitalDepartmentMapper.selectDepartments();
    }

    @Override
    public List<HospitalRoom> selectRooms(Integer departmentId) {
        return super.hospitalRoomMapper.selectRooms(departmentId);
    }

    @Override
    public List<HospitalBed> selectEmptyBeds(Map<String, Object> paraMap) {
        return this.hospitalBedMapper.selectEmptyBeds(paraMap);
    }

    @Override
    public List<HospitalBed> selectMonitorEmptyBeds(Integer departmentId, Integer type) {
        return super.hospitalBedMapper.selectMonitorEmptyBeds(departmentId, type);
    }

    @Override
    public int getBedCount(Integer departmentId, Integer start, Integer end) {
        return super.hospitalBedMapper.getBedCount(departmentId, start, end);
    }

    @Override
    public List<ListVO> getMonitorBedList(Integer departmentId, Integer bedId) {
        return super.hospitalBedMapper.getMonitorBedList(departmentId, bedId);
    }
}
