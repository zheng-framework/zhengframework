package com.github.zhengframework.jpa;

import java.util.HashMap;
import org.junit.Test;

public class HibernateTest {

  @Test
  public void test() throws Exception {
    HashMap<String, String> map = new HashMap<>();
    map.put("zheng.jpa.driverClassName", "org.h2.Driver");
    map.put("zheng.jpa.url", "jdbc:h2:mem:test");
    map.put("zheng.jpa.username", "sa");
    map.put("zheng.jpa.managedClassPackages", "com.github.zhengframework.jpa.a");
    map.put("zheng.jpa.properties.hibernate_dialect", "org.hibernate.dialect.H2Dialect");
    map.put("zheng.jpa.properties.hibernate_hbm2ddl_auto", "create");
    JpaModuleTest.test(map);
  }

  @Test
  public void testGroup() throws Exception {
    HashMap<String, String> map = new HashMap<>();
    map.put("zheng.jpa.group", "true");

    map.put("zheng.jpa.a.driverClassName", "org.h2.Driver");
    map.put("zheng.jpa.a.url", "jdbc:h2:mem:test_a");
    map.put("zheng.jpa.a.username", "sa");
    map.put("zheng.jpa.a.managedClassPackages", "com.github.zhengframework.jpa.a");
    map.put("zheng.jpa.a.properties.hibernate_dialect", "org.hibernate.dialect.H2Dialect");
    map.put("zheng.jpa.a.properties.hibernate_hbm2ddl_auto", "create");
    map.put("zheng.jpa.a.extraModuleClass", "com.github.zhengframework.jpa.ExposedPrivateModule1");

    map.put("zheng.jpa.b.driverClassName", "org.h2.Driver");
    map.put("zheng.jpa.b.url", "jdbc:h2:mem:test_b");
    map.put("zheng.jpa.b.username", "sa");
    map.put("zheng.jpa.b.managedClassPackages", "com.github.zhengframework.jpa.b");
    map.put("zheng.jpa.b.properties.hibernate_dialect", "org.hibernate.dialect.H2Dialect");
    map.put("zheng.jpa.b.properties.hibernate_hbm2ddl_auto", "create");
    map.put("zheng.jpa.b.extraModuleClass", "com.github.zhengframework.jpa.ExposedPrivateModule2");
    JpaMultiModuleTest.test(map);
  }
}