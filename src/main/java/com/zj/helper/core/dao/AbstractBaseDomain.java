package com.zj.helper.core.dao;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractBaseDomain {

  /**
   * 主键 ： 16位数字
   */
  @Id
  @GenericGenerator(name = "uuid-sys",
      strategy = "com.zj.helper.core.util.id.MyUuidStrategy")
  @GeneratedValue(generator = "uuid-sys")
  @Column(name = "o_id", length = 16)
  Long id;


  /**
   * 创建时间
   */
  @CreatedDate
  @Column(name = "o_create_time", updatable = false)
  LocalDateTime createTime;

  /**
   * 创建者Id
   */
  @CreatedBy
  @Column(name = "o_create_by", updatable = false)
  Long createBy;

  /**
   * 修改时间
   */
  @LastModifiedDate
  @Column(name = "o_update_time")
  LocalDateTime updateTime;

  /**
   * 修改者Id
   */
  @LastModifiedBy
  @Column(name = "o_update_by")
  Long updateBy;
}
