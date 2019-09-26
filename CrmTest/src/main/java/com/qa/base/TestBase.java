package com.qa.base;

import java.util.Locale;
import java.util.ResourceBundle;

public class TestBase {


    public String url;
    public ResourceBundle bundle;

    public void beforeTest(){
        bundle= ResourceBundle.getBundle("application", Locale.CHINA);
        url=bundle.getString("test.url");
    }
}
