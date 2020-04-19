package com.github.zhengframework.jpa;

import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.persistence.spi.PersistenceUnitInfo;
import org.hibernate.boot.registry.BootstrapServiceRegistry;
import org.hibernate.boot.registry.BootstrapServiceRegistryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateEntityManagerFactoryProvider implements EntityManagerFactoryProvider {

  @Override
  public EntityManagerFactory get(PersistenceUnitInfo persistenceUnitInfo) {
    final BootstrapServiceRegistryBuilder builder = new BootstrapServiceRegistryBuilder();
    BootstrapServiceRegistry bootstrapServiceRegistry = builder.build();
    Configuration configuration = new Configuration();
    Properties properties = persistenceUnitInfo.getProperties();
    if (properties.getProperty("javax.persistence.jdbc.driver") != null) {
      properties.put("hibernate.connection.driver_class",
          properties.getProperty("javax.persistence.jdbc.driver"));
    }
    if (properties.getProperty("javax.persistence.jdbc.url") != null) {
      properties
          .put("hibernate.connection.url", properties.getProperty("javax.persistence.jdbc.url"));
    }
    if (properties.getProperty("javax.persistence.jdbc.user") != null) {
      properties.put("hibernate.connection.username",
          properties.getProperty("javax.persistence.jdbc.user"));
    }
    if (properties.getProperty("javax.persistence.jdbc.password") != null) {
      properties.put("hibernate.connection.password",
          properties.getProperty("javax.persistence.jdbc.password"));
    } else {
      properties.put("hibernate.connection.password", "");
    }

    configuration.addProperties(properties);
    for (String managedClassName : persistenceUnitInfo.getManagedClassNames()) {
      try {
        configuration.addAnnotatedClass(Class.forName(managedClassName));
      } catch (ClassNotFoundException e) {
        throw new RuntimeException(e);
      }
    }
    ServiceRegistry registry = new StandardServiceRegistryBuilder(bootstrapServiceRegistry)
        .applySettings(configuration.getProperties())
        .build();
    return configuration.buildSessionFactory(registry);
  }
}
