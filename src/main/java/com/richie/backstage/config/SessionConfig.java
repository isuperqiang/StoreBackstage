package com.richie.backstage.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author richie on 2018.04.23
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 24 * 3600)
public class SessionConfig {

}