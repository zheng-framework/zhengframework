package com.github.zhengframework.jdbc;

import com.github.zhengframework.core.ModuleProvider;
import com.google.inject.Module;

public class CommonsDBUtilsModuleProvider implements ModuleProvider {

  @Override
  public Module getModule() {
    return new CommonsDBUtilsModule();
  }
}
