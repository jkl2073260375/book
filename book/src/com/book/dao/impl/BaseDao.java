package com.book.dao.impl;

import com.book.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.util.List;

public abstract class BaseDao {
   private QueryRunner queryRunner=new QueryRunner();
   public int update(String sql,Object...args){
       Connection conn= JdbcUtils.getConnection();
       try {
           int update = queryRunner.update(conn, sql, args);
           return update;
       } catch (Exception e) {
           e.printStackTrace();
           throw new RuntimeException(e);
       }
   }
   public <T>T queryForOne(Class<T> clazz,String sql,Object...args) {
       Connection conn = JdbcUtils.getConnection();
       try {
           return queryRunner.query(conn, sql, new BeanHandler<T>(clazz), args);
       } catch (Exception e) {
           e.printStackTrace();
           throw new RuntimeException(e);
       }
   }
    public <T> List<T> queryForList(Class<T> clazz, String sql, Object...args){
        Connection conn=JdbcUtils.getConnection();
        try{
            return queryRunner.query(conn,sql,new BeanListHandler<T>(clazz),args);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    public Object queryForSingleValue(String sql,Object...args){
       Connection conn=JdbcUtils.getConnection();
        try{
            return queryRunner.query(conn,sql,new ScalarHandler(),args);
        }catch (Exception e){
           e.printStackTrace();
           throw new RuntimeException(e);
        }
    }
}
