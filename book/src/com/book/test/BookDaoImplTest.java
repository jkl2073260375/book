package com.book.test;

import com.book.dao.impl.BookDaoImpl;
import com.book.pojo.Book;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class BookDaoImplTest {
    BookDaoImpl bookDao=new BookDaoImpl();
    @Test
    public void addBook() {
        Book book=new Book(null,"javaWeb从入门到放弃","kyh",new BigDecimal(100),30,40,"static/img/logo.gif");
        bookDao.addBook(book);
    }

    @Test
    public void deleteBookById() {
        bookDao.deleteBookById(10);
    }

    @Test
    public void updateBookById() {
        bookDao.updateBookById(new Book(19,"c++","ld",new BigDecimal(50),40,30,null));
    }

    @Test
    public void queryBookById() {
        Book book = bookDao.queryBookById(2);
        System.out.println(book);
    }

    @Test
    public void queryBooks() {
        List<Book> bookList = bookDao.queryBooks();
        for(Book book : bookList){
            System.out.println(book);
        }
    }
    @Test
    public void  queryForPageTotalCount() {
        Integer integer = bookDao.queryForPageTotalCount();
        System.out.println(integer);
    }
    @Test
    public void  queryForPageTotalCount1(){
        Integer integer = bookDao.queryForPageTotalCount(50,100);
        System.out.println(integer);
    }
    @Test
    public void queryForPageItems() {
        List<Book> books = bookDao.queryForPageItems(4, 4);
        for(Book book:books){
            System.out.println(book);
        }
    }
    @Test
    public void queryForPageItems1() {
        List<Book> books = bookDao.queryForPageItems(4, 4,50,100);
        for(Book book:books){
            System.out.println(book);
        }
    }
}