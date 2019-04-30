package demo2;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

/*
    使用druid连接池
 */
public class DemoDruid {

    public static void main(String[] args) throws Exception {
        //创建属性类
        Properties info = new Properties();
        //创建文件输入流
        FileInputStream in = new FileInputStream("D:\\javaProject\\jdbc\\PreparedStatement\\src\\druid.properties");
        //从类路径下加载文件,并且得到输入流
        //从输入流中加载配置文件
       // InputStream in = DemoDruid.class.getResourceAsStream("/druid.properties");
        //读取配置文件
        info.load(in);
        //得到数据源对象
        DataSource ds = DruidDataSourceFactory.createDataSource(info);
        for (int i = 0; i < 11; i++){
            //从连接池得到一个连接对象
            Connection conn = ds.getConnection();
            System.out.println("第" + (i + 1) + "个连接对象:" + conn);
            if (i == 5){
                conn.close();
            }
        }
    }
}
