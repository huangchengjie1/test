package com.itheima.factory;

import java.util.ResourceBundle;

public class BeanFactory {

    // 配置文件只加载一次
    private static ResourceBundle bundle;
    static {
        // ResourceBundle读取properties配置文件
        // 只能读取类路径下的propeties后缀的配置文件，其他配置文件读不了.
        bundle = ResourceBundle.getBundle("bean");
    }

    /**
     * 创建对象方法
     */
    public static <T> T getBean(String key,Class<T> clazz){
        try {
            // 获取配置文件指定的key对应的类全名：com.itheima.dao.impl.UserDaoImpl
            String className = bundle.getString(key);
            // 创建对象
            return (T)Class.forName(className).newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
