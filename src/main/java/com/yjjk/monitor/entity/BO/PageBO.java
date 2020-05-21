package com.yjjk.monitor.entity.BO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * @program: YjjkErp
 * @description: 分页
 * @author: CentreS
 * @create: 2019-11-26 14:56:24
 **/
@Data
@Accessors(chain = true)
@ApiModel("分页")
public class PageBO {
    @NotNull
    @ApiModelProperty(required = true)
    private Integer page;
    @NotNull
    @ApiModelProperty(required = true)
    private Integer pageSize;

}
