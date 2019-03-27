package com.lfkimura.iot.evaluation.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
@EntityScan(
        basePackages = {"com.lfkimura.iot.evaluation.domain", "com.lfkimura.iot.evaluation.repository"},
        basePackageClasses = {Jsr310JpaConverters.class})
public class JpaConfig {
}
