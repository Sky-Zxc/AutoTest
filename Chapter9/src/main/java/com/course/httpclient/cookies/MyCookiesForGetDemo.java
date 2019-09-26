package com.course.httpclient.cookies;

import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesForGetDemo {

    private String url;
    private ResourceBundle bundle;
    public CookieStore cookieStore;

    //在执行之前先读取配置文件
    @BeforeTest
    public void beforeTest(){
        bundle= ResourceBundle.getBundle("application", Locale.CHINA);
        url=bundle.getString("test.url");
    }

    @Test
    public void testGetCookies() throws IOException {
        String result;

        //从配置文件中拼接测试的url；
        String uri=bundle.getString("getCookies.uri");
        String testUrl=this.url+uri;
        System.out.println(testUrl);


        //保存cookie
        CookieStore cookieStore= new BasicCookieStore();
        //创建httpClient
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
        HttpGet get=new HttpGet(testUrl);
        CloseableHttpResponse httpResponse = httpClient.execute(get);
        //获取cookies
        List<Cookie> cookieList=cookieStore.getCookies();
        for (Cookie cookie:cookieList){
            String name= cookie.getName();
            String value= cookie.getValue();
            System.out.println("cookie name = "+name+"; cookie value="+value);
        }

        result= EntityUtils.toString(httpResponse.getEntity(),"utf-8");
        System.out.println(result);


    }

    @Test//(dependsOnMethods = {"testGetCookies"})
    public void testGetWithCookies() throws IOException {

        //从配置文件中拼接测试的url；
        String uri=bundle.getString( "test.get.with.cookies" );
        String testUrl=this.url+uri;
        System.out.println(testUrl);




        CloseableHttpClient httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();

        HttpGet get = new HttpGet(url);
        get.setConfig( RequestConfig.custom().setCookieSpec("myCookieSpec").build());

        CloseableHttpResponse httpResponse= httpClient.execute( get );

        //获取返回码
        int status=httpResponse.getStatusLine().getStatusCode();
        System.out.println(status);

        if (status==200){
            String result= EntityUtils.toString( httpResponse.getEntity(),"utf-8" );
            System.out.println(result);
        }else {
            System.out.println("访问失败");
        }

    }
}
