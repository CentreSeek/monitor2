package com.yjjk.monitor.entity.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Data
@Table(name = "hospital_department")
@Accessors(chain = true)
public class HospitalDepartment {
    /**
     * 科室id
     */
    @Id
    @Column(name = "department_id")
    @GeneratedValue(generator = "JDBC")
    private Integer departmentId;

    /**
     * 名字
     */
    private String name;

    @Column(name = "create_time")
    private String createTime;

    /**
     * 0：正常 1：删除
     */
    private Integer status;
    private List<HospitalRoom> rooms;
    private String hospitalName;

    /**
     * 获取科室id
     *
     * @return department_id - 科室id
     */
    public Integer getDepartmentId() {
        return departmentId;
    }

    /**
     * 设置科室id
     *
     * @param departmentId 科室id
     */
    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * 获取名字
     *
     * @return name - 名字
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名字
     *
     * @param name 名字
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return create_time
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取0：正常 1：删除
     *
     * @return status - 0：正常 1：删除
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置0：正常 1：删除
     *
     * @param status 0：正常 1：删除
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}