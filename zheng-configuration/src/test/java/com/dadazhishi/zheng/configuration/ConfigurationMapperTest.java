package com.dadazhishi.zheng.configuration;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import org.junit.Test;

public class ConfigurationMapperTest {

  @Test
  public void resolve() throws IOException {
    PropertiesConfigurationSource propertiesConfigurationSource =new PropertiesConfigurationSource
        (PropertiesConfigurationSourceTest.class.getResourceAsStream("/food.properties"));

    Configuration configuration = propertiesConfigurationSource.getConfiguration();
    ConfigurationMapper mapper = new ConfigurationMapper();

    Food food = mapper.resolve(configuration, Food.class);

    assertEquals(1, food.getApples().size());
    assertEquals(3, food.getBananas().size());

  }
}