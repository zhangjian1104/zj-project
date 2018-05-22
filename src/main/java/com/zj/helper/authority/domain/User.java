package com.zj.helper.authority.domain;

import com.zj.helper.core.dao.AbstractBaseDomain;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
@EqualsAndHashCode(callSuper=true)
@Builder
@Entity
@Table(name = "t_auth_user")
public class User extends AbstractBaseDomain {

  /**
   * 用户名
   */
  @Column(name = "o_name", length = 40, nullable = false)
  String name;

  /**
   * 用户密码
   */
  @Column(name = "o_password", length = 40, nullable = false)
  String password;

  /**
   * 用户邮件地址
   */
  @Column(name = "o_email", length = 100)
  String email;

  /**
   * 用户电话号码
   */
  @Column(name = "o_telephone", length = 40)
  String telephone;

  /**
   * 是否有效
   */
  @Column(name = "o_valid", nullable = false)
  Boolean isValid;

  /**
   * 登陆时间
   */
  @Column(name = "o_login_time")
  LocalDateTime loginTime;

  /**
   * 登陆IP
   */
  @Column(name = "o_login_ip", length = 40)
  String loginIp;

}
