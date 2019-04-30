package itheima;

/*
    使用PreparedStatement执行DML操作
 */

import org.junit.Test;
import utils.JdbcUtils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Demo1Jdbc {

    //向学生表中添加1条记录代码
    @Test
    public void insert(){
        //1.创建连接对象
        Connection connection = JdbcUtils.genConnection();

        //2.创建预编译语句对象,中间会有占位符
        try {
           PreparedStatement ps = connection.prepareStatement("insert into student values(null,?,?,?)");

           //3.替换占位符
            ps.setString(1,"钢铁侠");
            ps.setBoolean(2,false);
            //java.sql.Date类中静态方法将字符串转成日期类型
            ps.setDate(3, Date.valueOf("1985-11-20"));

            //4.执行更新的操作
            int i = ps.executeUpdate();
            System.out.println("影响了" + i + "行");

            //关闭连接
            JdbcUtils.close(connection,ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //2.将id为2的用户,姓名更新为"嫦娥",性别换成男
    @Test
    public void update() throws SQLException {
        //1.创建连接对象
        Connection connection = JdbcUtils.genConnection();

        //2. 创建预编译语句对象，中间会有占位符
       PreparedStatement ps = connection.prepareStatement("update student set name = ?,gender = ? where id=?");

       //3.替换占位符
        ps.setString(1,"嫦娥");
        ps.setBoolean(2,true);
        ps.setInt(3,2);

        //4.执行更新的操作
       int i = ps.executeUpdate();
        System.out.println("影响的行数:" + i);

        //关闭连接
        JdbcUtils.close(connection,ps);
    }


    //将id为4的学员删除
    @Test
    public void delete() throws SQLException {
        //1.创建连接
        Connection connection = JdbcUtils.genConnection();

        //2.创建预编译语句对象，中间会有占位符
       PreparedStatement ps = connection.prepareStatement("delete from student where id = ?");

       //3.替换占位符
        ps.setInt(1,4);

        //4.执行删除操作
        int i = ps.executeUpdate();
        System.out.println("影响的行数:" + i);

        //5.关闭连接
        JdbcUtils.close(connection,ps);

    }





}
