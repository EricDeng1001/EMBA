package com.ymzstudio.emba.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
@Getter
@Setter
public class ApplicationProperties {

    private String domain;

    private String name;

    private String author;

}
