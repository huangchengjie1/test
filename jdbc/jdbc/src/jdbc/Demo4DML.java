package jdbc;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Demo4DML {

    //向学生表中添加4条记录
    @Test
    public void insert() {
        //创建连接对象
        try (Connection conn = DriverManager.getConnection("jdbc:mysql:///day18", "root", "123456");
             //创建语句
             Statement stmt = conn.createStatement();

        ) {
            //执行SQL语句
            int i = stmt.executeUpdate("insert into student values(null,'孙悟空',1,'1993-03-24'),(null,'孙悟天',1,'1993-03-24'),(null,'白骨精',0,'1993-03-24'),(null,'嫦娥',0,'1993-03-24')");
            System.out.println("行记录添加" + i);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //释放资源(放在try())
    }

    //修改id为2的学生,修改其姓名,性别和生日
    @Test
    public void update() {
        //创建连接对象
        try (Connection conn = DriverManager.getConnection("jdbc:mysql:///day18", "root", "123456");
             //创建语句
             Statement stmt = conn.createStatement();

        ) {
            //执行SQL语句
            int i = stmt.executeUpdate("update student set name = '蜘蛛侠',gender = 0,birthday = '2000-02-04' where id=2");
            System.out.println(i + "行记录更新");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ////释放资源(放在try())
    }


    //删除id为4的学生
    @Test
    public void delete() {
        //创建连接对象
        try (Connection conn = DriverManager.getConnection("jdbc:mysql:///day18", "root", "123456");
             //创建语句
             Statement stmt = conn.createStatement();

        ) {
            //执行SQL语句
            int i = stmt.executeUpdate("delete from student where id = 4");
            System.out.println(i + "行记录删除");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //释放资源(放在try())

    }

}