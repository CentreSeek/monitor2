package com.yjjk.monitor.entity.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "machine")
@Data
public class MachineConfig {
    /**
     * 新增体温贴
     */
    private String url;
    private String repeater;
}
