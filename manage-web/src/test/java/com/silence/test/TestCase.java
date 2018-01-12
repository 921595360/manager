package com.silence.test;

/**
 * Created by silence on 2018/1/8.
 */
public class TestCase {
    public static void main(String[] args) {
        try{
//            MyClassLoader myClassLoader = new MyClassLoader("D:\\repo\\projects\\manager\\manage-web\\target\\classes","loader1");
//            myClassLoader.getClasssNames();

            MyClassLoader myClassLoader = null;
            for (int i = 0; i < 1000; i++) {
                myClassLoader=new MyClassLoader("D:\\repo\\projects\\manager\\manage-web\\target\\test-classes","loader1");
                Class<?> aClass = myClassLoader.loadClass("com.silence.test.User");
                System.out.println(aClass.newInstance());
                System.gc();
            }
        }catch (Exception e){

        }


    }

}
