package com.zj.helper.authority.repository;

import static org.junit.Assert.*;

import com.zj.helper.authority.domain.User;
import com.zj.helper.core.util.id.UuidUtil;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Copyright (C), 2018-2020, Zhangjian.
 *
 * com.zj.helper.authority.repository.UserRepositoryTest
 * <p>
 * 类的简述.
 * </p>
 *
 * @author zhangjian  2018/5/21  23:41
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

  @Autowired
  UserRepository userRepository;

  @Before
  public void setUp()  {
  }

  @After
  public void tearDown()  {
  }

  @Test
  public void testInsertData() {
    List<User> users = new ArrayList<>();
    for (int i = 0; i < 2; i++) {
      User user = User.builder()
          .name("test" + (i + 1))
          .password("password" + i)
          .email("email" + i)
          .telephone("telephone"+(100-i))
          .loginIp("10.11.11."+(100-i))
          .loginTime(LocalDateTime.now())          
          .build();
      user.setCreateBy(UuidUtil.getNewUUId());
      user.setUpdateBy(UuidUtil.getNewUUId());
//      user.setIsValid(false);
            
      users.add(user);
      System.out.println(user.toString());
    }
    userRepository.saveAll(users);
  }
}