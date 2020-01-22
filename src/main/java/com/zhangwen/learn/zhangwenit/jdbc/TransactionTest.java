package com.zhangwen.learn.zhangwenit.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

/**
 * @Description
 * @Author ZWen
 * @Date 2019/12/12 10:03 PM
 * @Version 1.0
 **/
public class TransactionTest extends JdbcTest {

    public static void main(String[] args) {
//        demo1();
        savePoint();
    }


    private static void demo1() {
        try (Connection connection = connection()) {
            connection.setAutoCommit(false);
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate("update test_transaction set name = '1' where id = 1");
                statement.executeUpdate("update test_transaction111 set name = '2' where id = 2");
                connection.commit();
            } catch (Exception e) {
                e.printStackTrace();
                connection.rollback();
            }
        } catch (SQLException e) {
        }
    }


    /**
     * 保存点
     */
    private static void savePoint() {

        try (Connection connection = connection()) {
            connection.setAutoCommit(false);
            Savepoint savepoint = null;
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate("update test_transaction set name = '100' where id = 1");
                savepoint = connection.setSavepoint();
                try {
                    statement.executeUpdate("update test_transaction set name = '200' where id = 2");
                    statement.executeUpdate("update test_transaction222 set name = '200' where id = 2");
                } catch (Exception e) {
                    //保存点之后的操作会回滚

                    connection.rollback(savepoint);
                }
                //当不在需要保存点时，必须释放
                connection.releaseSavepoint(savepoint);
                //如果回滚到保存点，保存点之前的操作可以正常提交到数据库
                connection.commit();
            } catch (Exception e) {
                e.printStackTrace();
                connection.rollback();
            }
        } catch (SQLException e) {
        }
    }
}