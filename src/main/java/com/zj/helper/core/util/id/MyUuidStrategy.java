package com.zj.helper.core.util.id;

import java.io.Serializable;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

/**
 * Copyright (C), 2018-2020, Zhangjian.
 *
 * com.zj.helper.core.util.id.MyUuidStrategy
 * <p>
 * JPA 自定义Uuid主键生成策略.
 * </p>
 *
 * @author zhangjian  2018/5/21  22:22
 */
public class MyUuidStrategy implements IdentifierGenerator {

  /**
   * Implementors should provide a public default constructor.
   */
  public MyUuidStrategy() {
  }

  /**
   * 通过Uuid，自动生成16位数字主键
   * @param session The session from which the request originates
   * @param object  the entity or collection (idbag) for which the id is being generated
   * @return 16位数字主键
   * @throws HibernateException Indicates trouble generating the identifier
   */
  @Override
  public Serializable generate(SharedSessionContractImplementor session, Object object)
      throws HibernateException {
    return UuidUtil.getNewUUId();
  }
}
