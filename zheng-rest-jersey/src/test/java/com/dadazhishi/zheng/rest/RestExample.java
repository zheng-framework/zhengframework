package com.dadazhishi.zheng.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import com.dadazhishi.zheng.configuration.Configuration;
import com.dadazhishi.zheng.configuration.ConfigurationBuilder;
import com.dadazhishi.zheng.rest.jersey.RestModule;
import com.dadazhishi.zheng.service.ZhengApplication;
import com.dadazhishi.zheng.web.WebConfig;
import com.dadazhishi.zheng.web.WebModule;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RestExample {

  WebConfig webConfig;
  ZhengApplication application;

  @After
  public void stop() {
    application.stop();
  }

  @Before
  public void setup() {
    Configuration configuration = ConfigurationBuilder.create()
        .withURI("classpath:app.properties")
        .build();
    System.out.println(configuration.asMap());
    application = ZhengApplication
        .create(configuration,
            new WebModule(),
            new RestModule(),
            new MyModule()
        );
    webConfig = application.getInjector().getInstance(WebConfig.class);
    application.start();
  }

  @Test
  public void start() throws Exception {
    Client client = ClientBuilder.newClient();
    WebTarget target = client.target("http://localhost:" + webConfig.getPort())
        .path("/");
    String response = target.path(TestResource.PATH).request().get().readEntity(String.class);
      System.out.println(response);
      assertEquals(TestResource.MESSAGE, response);
  }

  @Test
  public void testInject() throws Exception {
    Client client = ClientBuilder.newClient();
    WebTarget target = client.target("http://localhost:" + webConfig.getPort())
        .path(webConfig.getContextPath());
    String response1 = target.path(TestResource.PATH + "/inject").request().get()
        .readEntity(String.class);
      System.out.println(response1);
      String response2 = target.path(TestResource.PATH + "/inject").request().get()
          .readEntity(String.class);
      System.out.println(response2);
      assertNotEquals(response1, response2);
  }

}