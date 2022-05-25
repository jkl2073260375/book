package com.book.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class JdbcUtils {
    private static DruidDataSource dataSource;
    private static final ThreadLocal<Connection> conns = new ThreadLocal<Connection>();
    static{
        try {
             Properties properties = new Properties();
             InputStream is = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
             properties.load(is);
             dataSource= (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection(){
        Connection conn=conns.get();
        if(conn==null){
            try{
                conn=dataSource.getConnection();
                conns.set(conn);
                conn.setAutoCommit(false);
               }catch(Exception e){
                   e.printStackTrace();
            }
        }
        return conn;

    }
    public static void commitAndClose(){
        Connection conn = conns.get();
        if(conn!=null){
            try {
                conn.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                try {
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        // 一定要执行 remove 操作，否则就会出错。（因为 Tomcat 服务器底层使用了线程池技术）
        conns.remove();
    }
    public static void rollbackAndClose(){
        Connection conn = conns.get();
        if(conn!=null){
            try {
                conn.rollback();
            } catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                try {
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        conns.remove();
    }
//    public static void closeConnection(Connection conn){
//         if(conn!=null){
//             try{
//                 conn.close();
//             }catch (Exception e){
//                 e.printStackTrace();
//             }
//         }
//    }

}
