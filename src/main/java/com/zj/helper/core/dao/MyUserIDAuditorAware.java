package com.zj.helper.core.dao;

import com.sun.xml.internal.bind.v2.TODO;
import java.util.Optional;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

/**
 * com.zj.helper.core.dao.MyUserIDAuditorAware
 * <p>
 * 自定义获取用户登录信息的JPA审计实现. 获取登陆用户主键。可扩展为用户登录信息实体。 未来引入安全框架后该方法废弃。
 * </p>
 *
 * @author zhangjian  2018/5/22  13:34
 */
@Component("MyUserIDAuditorAware")
public class MyUserIDAuditorAware implements AuditorAware<Long> {

  /**
   * 获取登陆用户主键ID
   *
   * @return 用户主键ID
   */
  @Override
  public Optional<Long> getCurrentAuditor() {
    //TODO : 该方法在确定安全框架后进行重写
    return Optional.of(1001L);
  }
}
