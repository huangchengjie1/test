package com.itheima;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 耦合：
 *        指的是程序中的依赖关系。
 *        依赖关系还有很多种分类。
 *        现在看到的是类的依赖。
 * 解决依赖关系：
 *         使用反射注册驱动
 * 使用反射创建对象产生的问题：
 *         驱动类字符串在代码中写死了，如果遇到修改，又要改源码。
 * 解决写死了字符串的问题：
 *         使用配置文件
 *
 * 在项目开发中，我们应该做到：
 *        编译器不依赖，运行时才依赖
 */
public class JdbcDemo1 {
  //  大家思考：如何解决下面的连接数据库代码与数据库驱动代码耦合问题？

    public static void main(String[] args) throws Exception{
        //1.注册驱动
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        //2.获取连接
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatis", "root", "123456");
        //3.获取操作数据库的预处理对象
        PreparedStatement pstm = conn.prepareStatement("select * from USER ");
        //4.执行SQL语句
        ResultSet rs = pstm.executeQuery();

        //5.遍历结果集
        while(rs.next()) {
            System.out.println(rs.getString("username"));
        }

        //6.释放资源
        rs.close();
        pstm.close();
        conn.close();

    }
}
