package com.course.httpclient.cookies;

import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesForPost {

    private String url;
    private ResourceBundle bundle;
    public CookieStore store;

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

    @Test(dependsOnMethods = {"testGetCookies"})
    public void testPostMethod() throws  IOException{
        String uri= bundle.getString ( "test.post.with.cookies" );
        //拼接最终的测试地址
        String testUrl=this.url+uri;

        //声明一个Client对象，用来进行方法的执行
        CloseableHttpClient httpClient= HttpClients.custom ().setDefaultCookieStore ( store ).build ();

        //声明一个方法，这个方法就是post方法
        HttpPost httpPost=new HttpPost ( testUrl );

        //添加参数
        JSONObject param= new JSONObject (  );
        param.put ( "name","Lily" );
        param.put ( "age","18" );

        //设置请求头信息,设置header

        httpPost.setHeader ( "content-type","application/json" );

        //将参数信息添加到方法中

        StringEntity entity = new StringEntity (param.toString (), "UTF-8");
        httpPost.setEntity ( entity );

        //声明一个对象来进行响应结果的存储

        String result;

        //设置cookies信息
       // httpClient.setCookieStore(this.store);


        //执行post方法
        CloseableHttpResponse httpResponse= httpClient.execute( httpPost );


        //获取响应结果

        result = EntityUtils.toString ( httpResponse.getEntity (), "utf-8");
        System.out.println (result );

        //处理结果，就是判断返回结果是否符合预期
        //将返回的响应结果字符串转化为json对象

        JSONObject resultJson= new JSONObject ( result );


        //获取到结果值
       String success=(String) resultJson.get ( "Lily" );
       String status= (String)resultJson.get ( "status" );

        //具体的判断返回结果的值
        Assert.assertEquals ( "success",success );
        Assert.assertEquals ( "1",status );


    }
}





