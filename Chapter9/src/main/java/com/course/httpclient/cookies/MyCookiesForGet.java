package com.course.httpclient.cookies;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesForGet {

    private String url;
    private ResourceBundle bundle;


    //在执行之前先读取配置文件
    @BeforeTest
    public void beforeTest(){
        bundle= ResourceBundle.getBundle("application", Locale.CHINA);
        url=bundle.getString("test.url");
    }

    @Test
    public void testGetCookies() throws IOException{
        String result;

        //从配置文件中拼接测试的url；
        String uri=bundle.getString("getCookies.uri");
        String testUrl=this.url+uri;
        System.out.println(testUrl);

        //测试逻辑代码书写
        HttpGet get=new HttpGet(testUrl);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse = httpClient.execute(get);

        /*
        try {
            System.out.println(httpResponse.getStatusLine());
            HttpEntity entity = httpResponse.getEntity();
            EntityUtils.consume(entity);
        }finally {
            httpResponse.close();
        }
        */

        result=EntityUtils.toString(httpResponse.getEntity(),"utf-8");
        System.out.println(result);



    }

}
