package com.silence.test;

/**
 * Created by silence on 2018/1/9.
 */
public class User {

    static{
        System.out.println("加载实例类");
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("销毁实例对象");
    }
}
