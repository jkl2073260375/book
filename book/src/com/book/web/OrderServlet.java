package com.book.web;

import com.book.pojo.*;
import com.book.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderServlet extends BaseServlet{
    private OrderServiceImpl orderService = new OrderServiceImpl();
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 先获取 Cart 购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        // 获取Userid
        User loginUser = (User) req.getSession().getAttribute("user");
        if (loginUser == null) {
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }
        Integer userId = loginUser.getId();
        // 调用 orderService.createOrder(Cart,Userid);生成订单
        String orderId = orderService.createOrder(cart, userId);
        // 请求转发到/pages/cart/checkout.jsp
        req.getSession().setAttribute("orderId",orderId);
        resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");
    }
    //用户查看订单
    protected void showUserOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        User user = (User) req.getSession().getAttribute("user");
        if(user==null){
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }else{
            List<Order> orders = orderService.showUserOrders(user.getId());
            req.getSession().setAttribute("orders",orders);
            req.getRequestDispatcher("/pages/order/order.jsp").forward(req,resp);
        }
    }
    //管理员查看所有订单
    protected void showAllOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        Admin admin = (Admin) req.getSession().getAttribute("admin");
        if(admin==null){
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }else{
            List<Order> orders = orderService.showAllOrders();
            req.getSession().setAttribute("orders",orders);
            req.getRequestDispatcher("/pages/order/order.jsp").forward(req,resp);
        }
    }
    //查看详情
    protected void showOrdersDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        String orderId = req.getParameter("orderId");
        req.setAttribute("orderId",orderId);
        Order order = orderService.queryOrderByOderId(orderId);
        req.setAttribute("userId",order.getUserId());
        List<OrderItem> orderItems = orderService.showUserOrderDetail(orderId);
        req.getSession().setAttribute("orderItems",orderItems);
        req.getRequestDispatcher("/pages/order/order_detail.jsp").forward(req,resp);
    }
    //设置发货状态
    protected void sendOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String orderId = req.getParameter("orderId");
        Order order = orderService.queryOrderByOderId(orderId);
        if(order.getStatus()==0){
            orderService.changeOrderStatus(orderId,order.getStatus()+1);
            req.getSession().setAttribute("order",order);
        }
        resp.sendRedirect(req.getContextPath()+"/orderServlet?action=showAllOrders");
    }

    //签收订单
    protected void receiverOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String orderId = req.getParameter("orderId");
        Order order = orderService.queryOrderByOderId(orderId);
        if(order.getStatus()==1){
            orderService.changeOrderStatus(orderId,order.getStatus()+1);
            req.getSession().setAttribute("order",order);
        }
        resp.sendRedirect(req.getContextPath()+"/orderServlet?action=showUserOrders");
    }
}
