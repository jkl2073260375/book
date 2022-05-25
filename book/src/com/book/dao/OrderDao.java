package com.book.dao;

import com.book.pojo.Order;

import java.util.List;

public interface OrderDao {
    public void saveOrder(Order order);
    public List<Order> queryAllOrders();
    public void changeOrderStatus(String orderId,Integer status);
    public List<Order> queryOrderByUserId(Integer userId);
    public Order queryOrderByOrderId(String orderId);
}
