package com.yjjk.monitor.entity.pojo;

import javax.persistence.*;

@Table(name = "zs_rawdata")
public class ZsRawdata {
    @Column(name = "rawId")
    private Integer rawid;

    @Column(name = "rawData")
    private String rawdata;

    /**
     * 创建时间
     */
    @Column(name = "createTime")
    private String createtime;

    /**
     * @return rawId
     */
    public Integer getRawid() {
        return rawid;
    }

    /**
     * @param rawid
     */
    public void setRawid(Integer rawid) {
        this.rawid = rawid;
    }

    /**
     * @return rawData
     */
    public String getRawdata() {
        return rawdata;
    }

    /**
     * @param rawdata
     */
    public void setRawdata(String rawdata) {
        this.rawdata = rawdata;
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