package com.maximKachan.classloader;

public class CustomClassLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException,
            IllegalAccessException {

        CustomClassLoader classLoader2 = new CustomClassLoader();
        String fileName = "com.maximKachan.classloader.Hello";
        System.out.println(fileName);
        Class<?> hello = classLoader2.findClass(fileName);
        System.out.println(hello);
        Object hello1 = hello.newInstance();
        System.out.println(hello1);

    }
}
