package com.book.dao.impl;

import com.book.dao.OrderDao;
import com.book.pojo.Order;

import java.util.List;

public class OrderDaoImpl extends BaseDao implements OrderDao {
    @Override
    public void saveOrder(Order order) {
        String sql="insert into t_order(`order_id`,`create_time`,`price`,`status`,`user_id`) value(?,?,?,?,?)";
        update(sql,order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }


    @Override
    public List<Order> queryAllOrders() {
        String sql="select `order_id` orderId,`create_time` createTime,`price`,`status`,`user_id` userId from t_order";
        List<Order> orders = queryForList(Order.class, sql);
        return orders;
    }


    @Override
    public void changeOrderStatus(String orderId, Integer status) {
        String sql="update t_order set `status`=? where `order_id`=?";
        update(sql, status,orderId);
    }

    @Override
    public List<Order> queryOrderByUserId(Integer userId) {
        String sql="select `order_id` orderId,`create_time` createTime,`price`,`status`,`user_id` userId from t_order where `user_id`=?";
        List<Order> orders = queryForList(Order.class, sql, userId);
        return orders;
    }
    @Override
    public Order queryOrderByOrderId(String orderId) {
        String sql="select `order_id` orderId,`create_time` createTime,`price`,`status`,`user_id` userId from t_order where `order_id`=?";
        Order orders = queryForOne(Order.class, sql, orderId);
        return orders;
    }
}
