package demo2;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/*
    数据源工具类
 */
public class DataSourceUtils {

    private static DataSource ds;

    //在静态代码块中创建连接池
    static{
        //创建属性对象
        Properties info = new Properties();
        //从类路径下加载输入流对象
        try (InputStream in = DataSourceUtils.class.getResourceAsStream("/druid.properties");){
            //加载属性
                info.load(in);
                //创建数据源
                ds = DruidDataSourceFactory.createDataSource(info);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /*
            得到一个数据源
         */
        public static DataSource getDataSource(){
            return ds;
        }

        /*
            得到一个连接对象
         */
        public static Connection getConnection() throws SQLException {
            return ds.getConnection();
        }

        /*
            关闭连接
         */
        public static void close(Connection conn, Statement stme,ResultSet rs){
            if (rs != null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (stme != null){
                try {
                    stme.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }

        public static void close(Connection conn,Statement stem){
            close(conn,stem,null);
        }
}
