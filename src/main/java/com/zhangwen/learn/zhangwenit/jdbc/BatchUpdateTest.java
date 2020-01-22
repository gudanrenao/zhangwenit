package com.zhangwen.learn.zhangwenit.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @Description 批量更新
 * @Author ZWen
 * @Date 2019/12/12 10:22 PM
 * @Version 1.0
 **/
public class BatchUpdateTest extends JdbcTest {

    public static void main(String[] args) {
        batch();
    }

    private static void batch() {
        try (Connection connection = connection()) {
            try (Statement statement = connection.createStatement()) {
                connection.setAutoCommit(false);
                statement.addBatch("update test_transaction set name = '00100' where id = 1");
                statement.addBatch("update test_transaction set name = '00200' where id = 2000");
                statement.addBatch("update test_transaction set name = '00300' where id = 3");
                int[] ints = statement.executeBatch();
                for (int i = 0; i < ints.length; i++) {
                    System.out.println(i + "-----" + ints[i]);
                }
                connection.commit();
            }
        } catch (SQLException e) {

        }
    }
}