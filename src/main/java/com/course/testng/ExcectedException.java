package com.course.testng;

/*
在我们期望结果为某一个异常的时候：
例如：传入了某些不合法的参数，程序抛出了异常
也就是预期结果为异常
 */

import org.testng.annotations.Test;




public class ExcectedException {
    //这是一个测试结果会失败的异常测试
    @Test(expectedExceptions = RuntimeException.class)
    public void runTimeExceptionFailed(){
        System.out.println("这是一个失败的异常测试");
    }

    //这是一个成功的异常测试
    @Test(expectedExceptions = RuntimeException.class)
    public void runTimeExceptionSuccess(){
        System.out.println("这是我的异常测试");
        throw new RuntimeException();
    }
}
