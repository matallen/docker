package org.mallen.webapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.junit.Assert;
import org.junit.Test;

public class MyTest{
  
  private static String DEFAULT_URL="http://localhost:46500";
  
  class Http{
    public String get(String url) throws IOException{
      URLConnection cnn=new URL(url).openConnection();
      BufferedReader in=new BufferedReader(new InputStreamReader(cnn.getInputStream()));
      String inputLine;
      StringBuffer out=new StringBuffer();
      while ((inputLine=in.readLine())!=null)
        out.append(inputLine);
      in.close();
      return out.toString();
    }
  }
  
  @Test
  public void test1() throws IOException, InterruptedException{
//    System.out.println("TESTING...");
    
//    System.out.println("host="+System.getProperty("docker.containers.docker-webapp.ports.8080/tcp.host"));
//    System.out.println("port="+System.getProperty("docker.containers.docker-webapp.ports.8080/tcp.port"));
//    System.out.println("docker-webapp.url="+System.getProperty("docker-webapp.url"));
    System.out.println("Sleeping 5 seconds");
    Thread.sleep(5000l); // because i'm getting intermittant failures in Jenkins and im wondering if its container startup time
    String dockerWebAppUrl=System.getProperty("docker-webapp.url");
    String BASE;
    if (dockerWebAppUrl==null){
      System.out.println("Could not find 'dockerWebAppUrl' system property, reverting to default values");
      BASE=DEFAULT_URL;
    }else{
      BASE=dockerWebAppUrl;
    }
    
    String url=BASE+"/docker-webapp/version";
//    System.out.println("URL     ="+url);
    System.out.println("Acceptance: Calling to "+url);
    String response=new Http().get(url);
//    System.out.println("RESPONSE="+response);
    System.out.println("Acceptance: response was "+(response==null?"NULL! (FAILURE)":"not null (SUCCESS)"));
    Assert.assertNotNull(response);
    
//    for(Entry<String, String> e:System.getenv().entrySet()){
//      System.out.println("ENV: "+String.format("%-30s = %s", e.getKey(), e.getValue()));
//    }
//    
//    for(Entry<Object, Object> e:System.getProperties().entrySet()){
//      System.out.println("PROPERTY: "+String.format("%-30s = %s", e.getKey(), e.getValue()));
//    }
    
  }
}