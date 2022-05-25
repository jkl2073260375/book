package com.book.test;

import com.book.dao.OrderDao;
import com.book.dao.impl.OrderDaoImpl;
import com.book.pojo.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OrderDaoImplTest {
    OrderDao orderDao= new OrderDaoImpl();
    @Test
    public void saveOrder() {
        Order order=new Order("1",new Date(),new BigDecimal(500),0,1);
        orderDao.saveOrder(order);
    }

    @Test
    public void queryOrders() {
        List<Order> orders = orderDao.queryAllOrders();
        for (Order order:orders){
            System.out.println(order.toString());
        }
    }

    @Test
    public void changeOrderStatus() {
        orderDao.changeOrderStatus("111",1);
    }

    @Test
    public void queryOrderByUserId() {
        System.out.println(orderDao.queryOrderByUserId(3));
    }
    @Test
    public void queryOrderByOrderId() {
        System.out.println(orderDao.queryOrderByOrderId("111"));
    }

}