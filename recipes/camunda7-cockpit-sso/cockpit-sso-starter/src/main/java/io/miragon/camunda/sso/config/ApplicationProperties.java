package io.miragon.camunda.sso.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Setter
@Getter
@ConfigurationProperties(prefix = "application")
public class ApplicationProperties {
    private String webAppRole;
    private String registration;
}
