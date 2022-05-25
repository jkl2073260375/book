package com.book.dao.impl;

import com.book.dao.BookDao;
import com.book.pojo.Book;

import java.util.List;

public class BookDaoImpl extends BaseDao implements BookDao {
    @Override
    public int addBook(Book book) {
       String sql="insert into t_book(`name`,`author`,`price`,`sales`,`stock`,`img_path`) values(?,?,?,?,?,?)";
       return update(sql,book.getName(),book.getAuthor(),book.getPrice(),
               book.getSales(),book.getStock(),book.getImgPath());
    }

    @Override
    public int deleteBookById(Integer id) {
        String sql="delete from t_book where `id`=?";
        return update(sql,id);
    }

    @Override
    public int updateBookById(Book book) {
        String sql="update t_book set `name`=?,`author`=?,`price`=?," +
                "`sales`=?,`stock`=?,`img_path`=? where `id` = ?";
        return update(sql,book.getName(),book.getAuthor(),book.getPrice(),
                book.getSales(),book.getStock(),book.getImgPath(),book.getId());
    }

    @Override
    public Book queryBookById(Integer id) {
        String sql="select `id`,`name`,`author`,`price`,`sales`,`stock`,`img_Path` imgPath  from t_book where `id`=?";
        return queryForOne(Book.class,sql,id);
    }

    @Override
    public List<Book> queryBooks() {
        String sql="select `id`,`name`,`author`,`price`,`sales`,`stock`,`img_Path` imgPath from t_book";
        return queryForList(Book.class,sql);
    }

    @Override
    public Integer queryForPageTotalCount() {
        String sql="select count(*) from t_book";
        Number i = (Number) queryForSingleValue(sql);
        return i.intValue();
    }

    @Override
    public Integer queryForPageTotalCount(int min, int max) {
        String sql="select count(*) from  t_book where `price` between ? and ?";
        Number i = (Number) queryForSingleValue(sql, min, max);
        return i.intValue();
    }

    @Override
    public List<Book> queryForPageItems(int begin, int pageSize) {
        String sql="select `id`,`name`,`author`,`price`,`sales`,`stock`,`img_Path` imgPath from t_book limit ?,?";
        List<Book> books = queryForList(Book.class, sql, begin, pageSize);
        return books;
    }

    @Override
    public List<Book> queryForPageItems(int begin, int pageSize, int min, int max) {
        String sql="select `id`,`name`,`author`,`price`,`sales`,`stock`,`img_Path` imgPath " +
                "from t_book where `price` between ? and ? order by `price` limit ?,?";
        List<Book> books = queryForList(Book.class, sql, min,max,begin, pageSize);
        return books;
    }
}
