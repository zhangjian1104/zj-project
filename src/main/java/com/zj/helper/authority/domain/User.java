package com.zj.helper.authority.domain;

import com.zj.helper.core.dao.AbstractBaseDomain;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * Copyright (C), 2018-2020, Zhangjian.
 *
 * com.zj.helper.authority.domain.User
 * <p>
 * 权限模块-用户表映射实体.
 * </p>
 *
 * @author zhangjian  2018/5/21  22:31
 */

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class User extends AbstractBaseDomain {

  /**
   * 用户名
   */
  String name;

  /**
   * 用户密码
   */
  String password;

  /**
   * 用户邮件地址
   */
  String email;

  /**
   * 用户电话号码
   */
  String telephone;

  /**
   * 是否有效
   */
  Boolean isValid;

  /**
   * 登陆时间
   */
  LocalDateTime loginTime;

  /**
   * 登陆IP
   */
  String loginIp;

}
