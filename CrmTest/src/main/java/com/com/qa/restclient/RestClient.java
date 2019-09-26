package com.com.qa.restclient;


import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class RestClient {
    private String url;
    private ResourceBundle bundle;
    public CookieStore cookieStore;

    //在执行之前先读取配置文件
    @BeforeTest
    public void beforeTest(){
        bundle= ResourceBundle.getBundle("application", Locale.CHINA);
        url=bundle.getString("test.url");
    }


    public void get(String url)throws ClientProtocolException,IOException{
        //创建一个可关闭的httpClient对象
        CloseableHttpClient httpClient= HttpClients.createDefault ();

        //创建一个httpGet的请求对象
        HttpGet httpGet= new HttpGet ( url );

        //执行请求，赋值给httpresponse对象接收
        CloseableHttpResponse httpResponse= httpClient.execute ( httpGet );

        //拿到http响应码，例如和200,404,500去比较
        int responseStatusCode= httpResponse.getStatusLine ().getStatusCode ();
        System.out.println ("response status code--->"+responseStatusCode );

        //把响应内容存储在字符串对象
        String responseString= EntityUtils.toString ( httpResponse.getEntity (),"utf-8" );

        //创建Json对象，把上面字符串序列化成Json对象
        JSONObject responseJson = new JSONObject ( responseString );
        System.out.println("respon json from API-->" + responseJson);
    }

}
