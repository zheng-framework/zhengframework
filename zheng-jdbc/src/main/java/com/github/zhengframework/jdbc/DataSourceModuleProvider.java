package com.github.zhengframework.jdbc;

import com.github.zhengframework.core.ModuleProvider;
import com.google.inject.Module;
import org.kohsuke.MetaInfServices;

@MetaInfServices
public class DataSourceModuleProvider implements ModuleProvider {

  @Override
  public Module getModule() {
    return new DataSourceModule();
  }
}
