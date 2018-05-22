package com.zj.helper.authority.service.impl;

import com.zj.helper.authority.domain.User;
import com.zj.helper.authority.repository.UserRepository;
import com.zj.helper.authority.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * com.zj.helper.authority.service.impl.UserServcieImpl
 * <p>
 * 用户管理服务接口实现.
 * </p>
 *
 * @author zhangjian  2018/5/22  20:33
 */
@Service
public class UserServcieImpl implements UserService {

  final private UserRepository userRepository;

  @Autowired
  public UserServcieImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  /**
   * 查询全部有效用户列表
   *
   * @return 全部有效用户列表
   */
  @Override
  public List<User> findValidUserAll() {
    return userRepository.findByIsValid(true);
  }

  /**
   * 查询全部无效用户列表
   *
   * @return 全部无效用户列表
   */
  @Override
  public List<User> findNoValidUserAll() {
    return userRepository.findByIsValid(false);
  }
}
