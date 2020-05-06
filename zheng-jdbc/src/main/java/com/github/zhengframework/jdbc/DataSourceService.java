package com.github.zhengframework.jdbc;

/*-
 * #%L
 * zheng-jdbc
 * %%
 * Copyright (C) 2020 Zheng MingHai
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import com.github.zhengframework.jdbc.wrapper.DataSourceWrapper;
import com.github.zhengframework.service.Service;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class DataSourceService implements Service {

  private Provider<DataSourceWrapper> dataSourceWrapperProvider;

  @Inject
  public DataSourceService(Provider<DataSourceWrapper> dataSourceWrapperProvider) {
    this.dataSourceWrapperProvider = dataSourceWrapperProvider;
  }

  @Override
  public int order() {
    return 0;
  }

  @Override
  public void start() throws Exception {}

  @Override
  public void stop() throws Exception {
    dataSourceWrapperProvider.get().close();
  }
}
