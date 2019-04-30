package demo2;

import java.sql.Connection;
import java.sql.SQLException;

/*
    数据源的接口
 */
public interface DataSource {

    /*
        从连接池中得到连接对象
     */
    Connection genConnection() throws SQLException;
}
