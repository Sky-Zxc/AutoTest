package com.course.httpclient.demo;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;

public class MyHttpClient {

    @Test
    public void test1() throws IOException {

        String result;   //用来存放返回的结果

        //测试逻辑代码书写
        HttpGet get= new HttpGet("http://www.baidu.com");
        HttpClient client= HttpClientBuilder.create().build() ;//获取DefaultHttpClient请求
        HttpResponse response=client.execute(get);
        //获取整个响应的全体内容,再转化为字符串；
        result= EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);

    }
}
