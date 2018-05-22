package com.zj.helper.authority.service;

import com.zj.helper.authority.domain.User;
import java.util.List;

/**
 * com.zj.helper.authority.service.UserService
 * <p>
 * 用户管理服务接口.
 * </p>
 *
 * @author zhangjian  2018/5/22  20:33
 */
public interface UserService {

  /**
   * 查询全部有效用户列表。
   * @return 全部有效用户列表
   */
  List<User> findValidUserAll();

  /**
   * 查询全部无效用户列表。
   * @return 全部无效用户列表
   */
  List<User> findNoValidUserAll();
}
