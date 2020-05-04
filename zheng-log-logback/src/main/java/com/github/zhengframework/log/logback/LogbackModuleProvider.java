package com.github.zhengframework.log.logback;

import com.github.zhengframework.core.ModuleProvider;
import com.google.inject.Module;
import org.kohsuke.MetaInfServices;

@MetaInfServices
public class LogbackModuleProvider implements ModuleProvider {

  @Override
  public Module getModule() {
    return new LogbackModule();
  }
}
