package com.book.test;

import com.book.dao.impl.OrderItemDaoImpl;
import com.book.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;

public class OrderItemDaoImplTest {
     OrderItemDaoImpl orderItem=new OrderItemDaoImpl();
    @Test
    public void saveOrderItem() {
        orderItem.saveOrderItem(new OrderItem(null,"dd",30,new BigDecimal(40),new BigDecimal(1200),"1"));

    }
    @Test
    public void queryOrderItemByOrderId() {
        System.out.println(orderItem.queryOrderItemByOrderId("111"));
    }

}