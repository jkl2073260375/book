package com.book.service;

import com.book.pojo.Book;
import com.book.pojo.Page;

import java.util.List;

public interface BookService {
    public void addBook(Book book);
    public void deleteBookById(Integer id);
    public int updateBook(Book book) ;
    public Book queryBookById(Integer id) ;
    public List<Book> queryBooks() ;
    Page<Book> page(int pageNo, int pageSize);

    Page<Book> pageByPrice(int pageNo, int pageSize,int min,int max);
}
