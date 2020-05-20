package com.yjjk.monitor.entity.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @program: monitor2.0
 * @description:
 * @author: CentreS
 * @create: 2020-03-26 11:29:36
 **/
@Component
@ConfigurationProperties(prefix = "connect")
@Data
public class ConnectRepeater {

    private String url;
    private String start;
}
