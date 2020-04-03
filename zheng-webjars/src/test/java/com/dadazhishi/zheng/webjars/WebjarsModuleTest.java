package com.dadazhishi.zheng.webjars;

import static org.junit.Assert.assertEquals;

import com.dadazhishi.zheng.configuration.Configuration;
import com.dadazhishi.zheng.configuration.ConfigurationBuilder;
import com.dadazhishi.zheng.service.ZhengApplication;
import com.dadazhishi.zheng.web.WebConfig;
import com.dadazhishi.zheng.web.WebModule;
import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import org.junit.Test;

public class WebjarsModuleTest {

  @Test
  public void testDefaultPath() throws IOException {

    Configuration configuration = ConfigurationBuilder.create().with("classpath:app.properties")
        .build();
    ZhengApplication application = ZhengApplication
        .create(configuration,
            new WebModule(),
            new WebjarsModule()
        );
    application.start();
    WebConfig webConfig = application.getInjector().getInstance(WebConfig.class);
    WebjarsConfig webjarsConfig = application.getInjector().getInstance(WebjarsConfig.class);
    System.out.println(webConfig);
    System.out.println(webjarsConfig);
    try {
      OkHttpClient okHttpClient = new Builder()
          .build();

      String bootstrap1 = okHttpClient.newCall(new Request.Builder()
          .url("http://localhost:" + webConfig.getPort()
              + webjarsConfig.getPath() + "/bootstrap/3.1.0/js/bootstrap.js")
          .get().build()).execute().body().string();
      String bootstrap2 = okHttpClient.newCall(new Request.Builder()
          .url("http://localhost:" + webConfig.getPort()
              + webjarsConfig.getPath() + "/bootstrap/js/bootstrap.js")
          .get().build()).execute().body().string();
      assertEquals(bootstrap1, bootstrap2);
      String bootstrap3 = okHttpClient.newCall(new Request.Builder()
          .url("http://localhost:" + webConfig.getPort()
              + webjarsConfig.getPath() + "/bootstrap/bootstrap.js")
          .get().build()).execute().body().string();
      assertEquals(bootstrap1, bootstrap3);
    } finally {
      application.stop();
    }
  }

  @Test
  public void testDisableCache() throws IOException {

    Configuration configuration = ConfigurationBuilder.create()
        .with("classpath:app_cache.properties")
        .build();
    ZhengApplication application = ZhengApplication
        .create(configuration,
            new WebModule(),
            new WebjarsModule()
        );
    application.start();
    WebConfig webConfig = application.getInjector().getInstance(WebConfig.class);
    WebjarsConfig webjarsConfig = application.getInjector().getInstance(WebjarsConfig.class);
    System.out.println(webConfig);
    System.out.println(webjarsConfig);
    try {
      OkHttpClient okHttpClient = new Builder()
          .build();

      String bootstrap1 = okHttpClient.newCall(new Request.Builder()
          .url("http://localhost:" + webConfig.getPort()
              + webjarsConfig.getPath() + "/bootstrap/3.1.0/js/bootstrap.js")
          .get().build()).execute().body().string();
      String bootstrap2 = okHttpClient.newCall(new Request.Builder()
          .url("http://localhost:" + webConfig.getPort()
              + webjarsConfig.getPath() + "/bootstrap/js/bootstrap.js")
          .get().build()).execute().body().string();
      assertEquals(bootstrap1, bootstrap2);
      String bootstrap3 = okHttpClient.newCall(new Request.Builder()
          .url("http://localhost:" + webConfig.getPort()
              + webjarsConfig.getPath() + "/bootstrap/bootstrap.js")
          .get().build()).execute().body().string();
      assertEquals(bootstrap1, bootstrap3);
    } finally {
      application.stop();
    }
  }

  @Test
  public void testPath() throws IOException {
    Configuration configuration = ConfigurationBuilder.create()
        .with("classpath:app_path.properties")
        .build();
    ZhengApplication application = ZhengApplication
        .create(configuration,
            new WebModule(),
            new WebjarsModule()
        );
    application.start();
    WebConfig webConfig = application.getInjector().getInstance(WebConfig.class);
    WebjarsConfig webjarsConfig = application.getInjector().getInstance(WebjarsConfig.class);
    System.out.println(webConfig);
    System.out.println(webjarsConfig);
    try {
      OkHttpClient okHttpClient = new Builder()
          .build();

      String bootstrap1 = okHttpClient.newCall(new Request.Builder()
          .url("http://localhost:" + webConfig.getPort()
              + webjarsConfig.getPath() + "/bootstrap/3.1.0/js/bootstrap.js")
          .get().build()).execute().body().string();
      String bootstrap2 = okHttpClient.newCall(new Request.Builder()
          .url("http://localhost:" + webConfig.getPort()
              + webjarsConfig.getPath() + "/bootstrap/js/bootstrap.js")
          .get().build()).execute().body().string();
      assertEquals(bootstrap1, bootstrap2);
      String bootstrap3 = okHttpClient.newCall(new Request.Builder()
          .url("http://localhost:" + webConfig.getPort()
              + webjarsConfig.getPath() + "/bootstrap/bootstrap.js")
          .get().build()).execute().body().string();
      assertEquals(bootstrap1, bootstrap3);
    } finally {
      application.stop();
    }
  }
}