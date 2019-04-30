package demo2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Demo2ClassLoader {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //得到A类的类加载器
        ClassLoader a = A.class.getClassLoader();
        //得到B类的类加载器
        ClassLoader b = B.class.getClassLoader();
        System.out.println("A的类加载器:" + a);
        System.out.println("B的类加载器:" + b);
        System.out.println("是否是同一个类加载器:" + (a == b));


        //直接调用
        A a1 = new A();
        a1.sayHi("张三");

        //通过反射
        Class clz = A.class;
        //得到方法对象
        Method sayHi = clz.getMethod("sayHi", String.class);
        //通过反射调用sayHi方法
        sayHi.invoke(a1, "李四");


    }

}


class A {

    public void sayHi(String name){
        System.out.println("Hello," + name + "!");
    }

}

class B {

}