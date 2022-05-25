package com.book.test;

import com.book.pojo.Cart;
import com.book.pojo.CartItem;
import org.junit.Test;

import java.math.BigDecimal;

public class CartTest {
    Cart cart=new Cart();
    @Test
    public void addItem() {
        cart.addItem(new CartItem(1,"java",30,new BigDecimal(30),new BigDecimal(900)));
        cart.addItem(new CartItem(2,"java",40,new BigDecimal(40),new BigDecimal(1600)));
        cart.addItem(new CartItem(3,"java",50,new BigDecimal(50),new BigDecimal(2500)));
        System.out.println(cart);
        System.out.println("--------------------------------------------------------------------------");
        cart.deleteItem(1);
        System.out.println(cart);
        System.out.println("----------------------------------------------------------------------------");
        cart.updateCount(2,4);
        System.out.println(cart);
        System.out.println("-----------------------------------------------------------------------------");
        cart.clear();
        System.out.println(cart);
    }

    @Test
    public void deleteItem() {
        cart.deleteItem(1);
        System.out.println(cart);
    }

    @Test
    public void clear() {
        cart.clear();
        System.out.println(cart);
    }

    @Test
    public void updateItem() {
        cart.updateCount(2,4);
        System.out.println(cart);
    }
}