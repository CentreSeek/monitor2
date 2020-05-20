package com.yjjk.monitor.entity.pojo;

import javax.persistence.*;

@Table(name = "zs_rawdata_info")
public class ZsRawdataInfo {
    @Column(name = "dataId")
    private Integer dataid;

    /**
     * 原始数据数量
     */
    @Column(name = "dataNum")
    private String datanum;

    /**
     * 创建时间
     */
    @Column(name = "createTime")
    private String createtime;

    /**
     * @return dataId
     */
    public Integer getDataid() {
        return dataid;
    }

    /**
     * @param dataid
     */
    public void setDataid(Integer dataid) {
        this.dataid = dataid;
    }

    /**
     * 获取原始数据数量
     *
     * @return dataNum - 原始数据数量
     */
    public String getDatanum() {
        return datanum;
    }

    /**
     * 设置原始数据数量
     *
     * @param datanum 原始数据数量
     */
    public void setDatanum(String datanum) {
        this.datanum = datanum;
    }

    /**
     * 获取创建时间
     *
     * @return createTime - 创建时间
     */
    public String getCreatetime() {
        return createtime;
    }

    /**
     * 设置创建时间
     *
     * @param createtime 创建时间
     */
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
}