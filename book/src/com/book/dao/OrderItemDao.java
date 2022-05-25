package com.book.dao;

import com.book.pojo.OrderItem;

import java.util.List;

public interface OrderItemDao {
    public void saveOrderItem(OrderItem orderItem);
    public List<OrderItem> queryOrderItemByOrderId(String orderId);

}
