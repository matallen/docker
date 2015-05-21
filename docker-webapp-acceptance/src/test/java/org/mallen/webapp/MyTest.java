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
  public void test1() throws IOException{
//    System.out.println("TESTING...");
    
//    System.out.println("host="+System.getProperty("docker.containers.docker-webapp.ports.8080/tcp.host"));
//    System.out.println("port="+System.getProperty("docker.containers.docker-webapp.ports.8080/tcp.port"));
//    System.out.println("docker-webapp.url="+System.getProperty("docker-webapp.url"));
    String BASE=System.getProperty("docker-webapp.url")!=null?System.getProperty("docker-webapp.url"):DEFAULT_URL;
    String url=BASE+"/docker-webapp/version";
//    System.out.println("URL     ="+url);
    String response=new Http().get(url);
//    System.out.println("RESPONSE="+response);
    
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