package com.book.test;

import com.book.pojo.Book;
import com.book.service.BookService;
import com.book.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class BookServletImplTest {
    BookService bookServlet=new BookServiceImpl();
    @Test
    public void addBook() {
        Book book=new Book(null,"html从入门到放弃","xxf",new BigDecimal(100),30,40,"static/img/logo.gif");
        bookServlet.addBook(book);
    }

    @Test
    public void deleteBookById() {
        bookServlet.deleteBookById(21);
    }

    @Test
    public void updateBookById() {
        bookServlet.updateBook(new Book(22,"c++","ld",new BigDecimal(50),40,30,null));
    }

    @Test
    public void queryBookById() {
        Book book = bookServlet.queryBookById(8);
        System.out.println(book);
    }

    @Test
    public void queryBooks() {
        List<Book> bookList = bookServlet.queryBooks();
        for(Book book:bookList){
            System.out.println(book);
        }
    }
}