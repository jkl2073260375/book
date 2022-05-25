package com.book.service;

import com.book.pojo.Cart;
import com.book.pojo.Order;
import com.book.pojo.OrderItem;

import java.util.List;

public interface OrderService {
    public String createOrder(Cart cart, Integer userId);
    public List<Order> showAllOrders();
    public List<Order> showUserOrders(Integer userId);
    public List<OrderItem> showUserOrderDetail(String orderId);
    public Order queryOrderByOderId(String orderId);
    public void changeOrderStatus(String orderId,Integer status);
}
