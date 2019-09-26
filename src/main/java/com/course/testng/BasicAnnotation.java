package com.course.testng;

import org.testng.annotations.*;

public class BasicAnnotation {

    //最基本的注解用来把方法标记为测试的一部分
    @Test
    public void testCase1(){
        System.out.println("这是测试用例1");

    }
    @Test
    public void testCase2(){
        System.out.println("这是测试用例2");
    }

    @BeforeMethod
    public void beforeMethond(){
        System.out.println("这是在测试方法之前运行的");
    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("这是在测试方法之后运行的");
    }

    @BeforeClass//静态方法，变量赋值等
    public void beforeClass(){
        System.out.println("这是在类运行之前运行的方法");
    }
    @AfterClass
    public void afterClass(){
        System.out.println("这是在类运行之后运行的方法");
    }
    @BeforeSuite   //测试套件
    public void beforeSuite(){
        System.out.println("BeforeSuite测试套件");
    }

    @AfterSuite  //测试套件
    public void afterSuite(){
        System.out.println("AfterSuite测试套件");
    }

}
