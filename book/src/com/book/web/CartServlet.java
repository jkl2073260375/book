package com.book.web;

import com.book.pojo.Book;
import com.book.pojo.Cart;
import com.book.pojo.CartItem;
import com.book.service.BookService;
import com.book.service.impl.BookServiceImpl;
import com.book.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CartServlet extends BaseServlet {
    BookService bookServlet = new BookServiceImpl();

    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        Book book = bookServlet.queryBookById(id);
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        cart.addItem(cartItem);
        req.getSession().setAttribute("lastName",cartItem.getName());
        resp.sendRedirect(req.getHeader("Referer"));
    }
    protected void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        Book book = bookServlet.queryBookById(id);
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        cart.addItem(cartItem);
        //req.getSession().setAttribute("cartItem",cartItem);

        Map<String ,Object> map=new HashMap<>();
        map.put("totalCount",cart.getTotalCount());
        map.put("lastName",cartItem.getName());
        Gson gson=new Gson();
        String json = gson.toJson(map);
        resp.getWriter().write(json);
        req.getSession().setAttribute("lastName",cartItem.getName());
    }
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        // ?????????????????????
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) { // ?????? ?????????????????????
             cart.deleteItem(id);
             // ???????????????????????????????????????
        }
        resp.sendRedirect(req.getHeader("Referer"));
    }
        protected void clearItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart!=null){
            cart.clear();
        }
        resp.sendRedirect(req.getHeader("Referer"));
    }
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = WebUtils.parseInt(req.getParameter("id"),0);
        int count = WebUtils.parseInt(req.getParameter("count"), 1);
        // ?????? Cart ???????????????
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
        // ??????????????????
         cart.updateCount(id,count);
        // ???????????????????????????????????????
        resp.sendRedirect(req.getHeader("Referer"));
    }
}
}