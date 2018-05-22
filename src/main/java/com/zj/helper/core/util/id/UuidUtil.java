package com.zj.helper.core.util.id;

import java.util.UUID;

/**
 * Copyright (C), 2018-2020, Zhangjian.
 *
 * com.zj.helper.core.util.id.UuidUtil
 * <p>
 * 创建自定义uuid.
 * </p>
 *
 * @author zhangjian 创建时间   2018/5/21  22:13
 */
public class UuidUtil {

  /**
   * 生成16位数字的UUID
   *
   * @return 16位数字的唯一值
   */
  public static Long getNewUUId() {
    int machineId = 1;//最大支持1-9个集群机器部署
    int hashCodeV = UUID.randomUUID().toString().hashCode();
    if (hashCodeV < 0) {//有可能是负数
      hashCodeV = -hashCodeV;
    }
    // 0 代表前面补充0
    // 4 代表长度为4
    // d 代表参数为正数型
    return Long.valueOf(machineId + String.format("%015d", hashCodeV));
  }
}
