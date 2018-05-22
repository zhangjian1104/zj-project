package com.zj.helper.authority.service.impl;

import static org.junit.Assert.*;

import com.zj.helper.authority.domain.User;
import com.zj.helper.authority.service.UserService;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

/**
 * com.zj.helper.authority.service.impl.UserServcieImplTest
 * <p>
 * 类的简述.
 * </p>
 *
 * @author zhangjian  2018/5/22  22:44
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServcieImplTest {

  @Autowired
  UserService userService;

  @Before
  public void setUp() {
  }

  @After
  public void tearDown() {
  }

  @Test
  public void testFindValidUserAll() {
    List<User> users = userService.findValidUserAll();
    Assert.notNull(users, "没有任何有效用户信息");
    System.out.println(users.size());
    for (User user : users
        ) {
      System.out.println(user.toString());
    }
  }

  @Test
  public void testFindNoValidUserAll() {
    List<User> users = userService.findNoValidUserAll();
    Assert.notNull(users, "没有任何无效用户信息");
    System.out.println(users.size());
    for (User user : users
        ) {
      System.out.println(user.toString());
    }
  }
}