package com.base.test;

import org.apache.http.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpUtil {
    public static String doPost(String url, Header[] headers, Map<String, Object> paramMaps) {
        String result = null;
        HttpPost httpPost = new HttpPost(url);
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
        try {
            httpPost.setHeaders(headers);
            List<NameValuePair> pairList = new ArrayList<> ();
            for (Map.Entry<String, Object> entry : paramMaps.entrySet()) {
                String key = entry.getKey();
                String value = (String) entry.getValue();
                pairList.add(new BasicNameValuePair (key, value));
            }
            UrlEncodedFormEntity urlEntity = new UrlEncodedFormEntity(pairList, "UTF-8");
            httpPost.setEntity(urlEntity);
            HttpResponse resp = closeableHttpClient.execute(httpPost);
            if (resp.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result = EntityUtils.toString(resp.getEntity(), "UTF-8").trim();
            }
        } catch (Exception e) {
            throw new RuntimeException("HTTP post failed.", e);
        } finally {
            httpPost.abort();
            try {
                closeableHttpClient.close();
            } catch (Exception e2) {
                throw new RuntimeException("Close CloseableHttpClient failed.", e2);
            }
        }

        return result;
    }

    // 使用代理服务器IP 请求出去
    public static String doHttpsPost(String url, Header[] headers, Map<String, Object> paramMaps) {
        // 设置代理
        HttpHost proxy = new HttpHost( 47.99.84.2,443 , "http");
        RequestConfig defaultRequestConfig = RequestConfig.custom().setProxy(proxy).build();


        String result = null;
        CloseableHttpClient httpClient = null;
        HttpPost httpPost = new HttpPost(url);
        try {
        　　　　httpPost.setHeaders(headers);
　　　　　　　　　httpPost.setEntity ( );
　　　　　　　　　httpClient = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig).build();
　　　　　　　　　HttpResponse resp = httpClient.execute(httpPost);
　　　　　　　　　result = EntityUtils.toString(resp.getEntity(), "UTF-8").trim();
        } catch (Exception e) {
            throw new RuntimeException("HTTP Post failed.", e);
        } finally {
　　　　　　　　httpPost.abort();
　　　　　　　　try {
　　　　　　　　　　　　httpClient.close();
　　　　　　　　} catch (IOException e) {
　　　　　　　　　　　　throw new RuntimeException("Close CoseabledHttpClient failed.", e);
　　　　　　　　}
        }
        return result;
    }

}
