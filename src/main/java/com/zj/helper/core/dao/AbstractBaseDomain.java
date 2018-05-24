package com.zj.helper.core.dao;

import java.time.LocalDateTime;
import lombok.Data;

/**
 * Copyright (C), 2018-2020, Zhangjian.
 *
 * com.zj.helper.core.dao.AbstractBaseDomain
 * <p>
 * 数据库映射类基类.
 *
 * 封装主键、数据创建时间、创建者、修改时间、修改者。
 * </p>
 *
 * @author zhangjian  2018/5/21  22:44
 */
@Data
public abstract class AbstractBaseDomain {

  /**
   * 主键 ： 16位数字
   */
  Long id;


  /**
   * 创建时间
   */
  LocalDateTime createTime;

  /**
   * 创建者Id
   */
  Long createBy;

  /**
   * 修改时间
   */
  LocalDateTime updateTime;

  /**
   * 修改者Id
   */
  Long updateBy;
}
