package com.course.testng.muliThread;

import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;
import org.testng.annotations.Test;

public class MultiThreadOnAnnotion {

    //多线程 默认为1,线程池有3个
    @Test(invocationCount = 10,threadPoolSize = 3)
    public void test(){
        System.out.println(1);
        System.out.printf("Thread Id: %s%n",Thread.currentThread().getId());
    }
}
