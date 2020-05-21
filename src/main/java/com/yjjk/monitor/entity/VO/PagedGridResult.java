package com.yjjk.monitor.entity.VO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@ApiModel("分页通用插件")
public class PagedGridResult<T> {

    @ApiModelProperty(value = "当前页数")
    private int page;
    @ApiModelProperty(value = "总页数")
    private int total;
    @ApiModelProperty(value = "总记录数")
    private long records;
    @ApiModelProperty("data")
    private List<T> rows;

}