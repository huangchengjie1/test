package demo2;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/*
    创建连接池
 */
public class Demo1C3p0 {
    public static void main(String[] args) throws SQLException {
        //创建数据源对象,默认的构造方法:使用默认的配置
      //  ComboPooledDataSource ds = new ComboPooledDataSource();

        //使用命名配置
        ComboPooledDataSource ds = new ComboPooledDataSource("JavaEE91");
        for (int i = 0; i < 11; i++){
            //从连接池得到一个连接对象
            Connection conn = ds.getConnection();
            System.out.println("第" + (i + 1) + "个连接对象:" + conn);
            //释放其中一个
            if (i==5){
                conn.close();  //调用代理对象的方法
            }
        }
    }
}
