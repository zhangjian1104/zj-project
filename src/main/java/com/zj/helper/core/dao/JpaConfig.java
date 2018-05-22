package com.zj.helper.core.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * com.zj.helper.core.dao.JpaConfig
 * <p>
 * spring data jpa 的设置类.
 * </p>
 *
 * @author zhangjian  2018/5/22  16:53
 */
@Configuration
@EnableJpaAuditing(auditorAwareRef = "MyUserIDAuditorAware")
public class JpaConfig {
  
  @Bean
  public AuditorAware<Long> auditorProvider() {
    return new MyUserIDAuditorAware();
  }
}
