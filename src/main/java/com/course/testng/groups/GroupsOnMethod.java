package com.course.testng.groups;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

public class GroupsOnMethod {

    @Test(groups = "server")
    public void test1(){
        System.out.println("这是服务端组的测试方法1");
    }

    @Test(groups = "server")
    public void test2(){
        System.out.println("这是服务端组的测试方法2");
    }

    @Test(groups = "client")
    public void test3(){
        System.out.println("这是<客户端组>的测试方法1");
    }
    @Test(groups = "client")
    public void test4(){
        System.out.println("这是<客户端组>的测试方法2");
    }

    @BeforeGroups(groups = "server")
    public void beforeGroupsOnServer(){
        System.out.println("这是服务端最运行之前运行的方法");
    }
    @AfterGroups(groups = "server")
    public void afterGroupOnSever(){
        System.out.println("这是服务端组运行之后运行的方法！！！");
    }

    @BeforeGroups(groups = "client")
    public void beforeGroupsOnClient(){
        System.out.println("这是<客户端组>最运行之前运行的方法");
    }
    @AfterGroups(groups = "client")
    public void afterGroupOnClient(){
        System.out.println("这是<客户端组>运行之后运行的方法！！！");
    }
}
