package com.zhangwen.learn.zhangwenit.jdbc;

import com.zhangwen.learn.zhangwenit.transaction.entity.TestTransaction;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author ZWen
 * @Date 2019/1/27 2:44 PM
 * @Version 1.0
 **/
public class JdbcTest {

    public static void main(String[] args) throws Exception {

        execTest();
    }

    /**
     * 原生JDBC操作
     */
    public static void execTest() throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
        //加载驱动类
        Class.forName("com.mysql.jdbc.Driver");
        //获取连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/zhangwenit", "root", "123456");
        //创建语句集
        PreparedStatement prepareStatement = connection.prepareStatement("select * from test_transaction");
        //执行
        ResultSet resultSet = prepareStatement.executeQuery();
        //获取并封装结果集
        int columnCount = resultSet.getMetaData().getColumnCount();
        List<TestTransaction> list = new ArrayList<>(4);
        while (resultSet.next()) {
            Class cl = TestTransaction.class;
            Object instance = cl.getDeclaredConstructor().newInstance();
            for (int i = 1; i <= columnCount; i++) {
                String columnName = resultSet.getMetaData().getColumnName(i);
                Field field = cl.getDeclaredField(columnName);
                //强制操作private字段
                field.setAccessible(true);
//                Class<?> type = field.getType();
//                if(type == Long.class){
//                    field.set(instance,resultSet.getLong(i));
//                } else {
//                    field.set(instance,resultSet.getString(i));
//                }
                field.set(instance, resultSet.getObject(i));
            }
            list.add((TestTransaction) instance);
        }
        System.out.println(list);

        //关闭连接，释放资源
        resultSet.close();
        prepareStatement.close();
        connection.close();

    }
}