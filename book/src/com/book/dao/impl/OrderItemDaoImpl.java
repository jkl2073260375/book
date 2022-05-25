package com.book.dao.impl;

import com.book.dao.OrderItemDao;
import com.book.pojo.OrderItem;

import java.util.List;

public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public void saveOrderItem(OrderItem orderItem) {
        String sql="insert into t_order_item(`name`,`count`,`price`,`total_price`,`order_id`) value(?,?,?,?,?)";
        update(sql,orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),
                orderItem.getTotalPrice(),orderItem.getOrderId());
    }
    @Override
    public List<OrderItem> queryOrderItemByOrderId(String orderId) {
        String sql="select `id`,`name`,`count`,`price`,`total_price` totalPrice,`order_id` orderId from t_order_item where `order_id`=?";
        List<OrderItem> orderList = queryForList(OrderItem.class, sql, orderId);
        return orderList;
    }

}
