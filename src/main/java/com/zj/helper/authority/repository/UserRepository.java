package com.zj.helper.authority.repository;

import com.zj.helper.authority.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Copyright (C), 2018-2020, Zhangjian.
 *
 * com.zj.helper.authority.repository.UserRepository
 * <p>
 * User映射实体相关操作.
 * </p>
 *
 * @author zhangjian  2018/5/21  23:39
 */
public interface UserRepository extends JpaRepository<User,Long> {

}
