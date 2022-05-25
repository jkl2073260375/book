package com.book.test;

import com.book.utils.JdbcUtils;
import org.junit.Test;

public class JdbcUtilsTest {
  @Test
  public void testJdbcUtils(){
    System.out.println(JdbcUtils.getConnection());
  }

}
