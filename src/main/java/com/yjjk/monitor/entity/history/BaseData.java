package com.yjjk.monitor.entity.history;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

/**
 * @program: monitor2
 * @description:
 * @author: CentreS
 * @create: 2020-05-22 14:42:35
 **/
@Data
@Accessors(chain = true)
public class BaseData implements Comparable<BaseData>  {
    @ApiModelProperty(value = "时间戳")
    private Long timestamp;

    @Override
    public int compareTo(@NotNull BaseData o) {
        long a = timestamp - o.timestamp;
        return (int) a;
    }
}
