package com.yjjk.monitor.entity.pojo;

import javax.persistence.*;

@Table(name = "hospital_info")
public class HospitalInfo {
    @Column(name = "hospital_id")
    private Integer hospitalId;

    /**
     * 医院名字
     */
    @Id
    @Column(name = "hospital_name")
    private String hospitalName;

    /**
     * @return hospital_id
     */
    public Integer getHospitalId() {
        return hospitalId;
    }

    /**
     * @param hospitalId
     */
    public void setHospitalId(Integer hospitalId) {
        this.hospitalId = hospitalId;
    }

    /**
     * 获取医院名字
     *
     * @return hospital_name - 医院名字
     */
    public String getHospitalName() {
        return hospitalName;
    }

    /**
     * 设置医院名字
     *
     * @param hospitalName 医院名字
     */
    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }
}