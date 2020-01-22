package com.zhangwen.learn.zhangwenit.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

/**
 * @Description
 * @Author ZWen
 * @Date 2019/12/12 9:39 AM
 * @Version 1.0
 **/
public class ResultSetMetaDataTest extends JdbcTest {

    public static void main(String[] args) throws Exception {
        try (Connection connection = connection()) {
            try (Statement statement = connection.createStatement()) {
                String sql = "select * from test_transaction";
                try (ResultSet resultSet = statement.executeQuery(sql)) {
                    ResultSetMetaData metaData = resultSet.getMetaData();
                    for (int i = 1; i <= metaData.getColumnCount(); i++) {
                        String columnName = metaData.getColumnName(i);
                        int columnDisplaySize = metaData.getColumnDisplaySize(i);
                        System.out.println(columnName + " size is " + columnDisplaySize);
                    }
                }
            }
        }
    }
}