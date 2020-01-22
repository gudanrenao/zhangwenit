package com.zhangwen.learn.zhangwenit.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

/**
 * @Description
 * @Author ZWen
 * @Date 2019/12/12 9:39 AM
 * @Version 1.0
 **/
public class DatabaseMetaDataTest extends JdbcTest {

    public static void main(String[] args) throws Exception {
        try (Connection connection = connection()) {
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            ResultSet resultSet = databaseMetaData.getTables(null, null, null, new String[]{"TABLE"});
            while (resultSet.next()) {
                ResultSetMetaData metaData = resultSet.getMetaData();

                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    String columnName = metaData.getColumnName(i);
                    Object value = resultSet.getObject(columnName);
                    System.out.println(columnName + " is " + value);
                }
                System.out.println("==============================");
            }
        }
    }
}