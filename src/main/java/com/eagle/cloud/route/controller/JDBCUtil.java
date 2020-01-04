package com.eagle.cloud.route.controller;

/**
 * @Author:FWJ
 * @Date:2019/10/31
 * @Description:IntelliJ IDEA
 * @version:1.0
 */

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * JDBC工具类，使用Durid连接池
 */
public class JDBCUtil {
    private static DataSource ds;
    /**
     * 获取连接池对象
     */
    static {
        try {
            Properties pro = new Properties();
            InputStream is = JDBCUtil.class.getClassLoader().getResourceAsStream("druid.properties");
//            System.out.println("找到配置文件，is = "+is);
            pro.load(is);
//            System.out.println("加载配置文件,pro="+pro);
            ds = DruidDataSourceFactory.createDataSource(pro);
//            System.out.println("连接池就位，ds="+ds);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static DataSource getDataSource(){

        return ds;
    }

    /**
     * 获取Connection对象
     */
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
