package com.book.dao;

import com.book.pojo.Book;

import java.util.List;

public interface BookDao {
    public int addBook(Book book);
    public int deleteBookById(Integer id);
    public int updateBookById(Book book);
    public Book queryBookById(Integer id);
    public List<Book> queryBooks();

    public Integer queryForPageTotalCount();
    public Integer queryForPageTotalCount(int min,int max);
    public List<Book> queryForPageItems(int begin, int pageSize);
    public List<Book> queryForPageItems(int begin, int pageSize,int min,int max);
}
