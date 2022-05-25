package com.book.web;

import com.book.pojo.Admin;
import com.book.service.AdminService;
import com.book.service.impl.AdminServiceImpl;
import com.book.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AdminServlet extends BaseServlet{
    AdminService adminService=new AdminServiceImpl();

    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Admin admin1 = WebUtils.copyParamToBean(new Admin(), req.getParameterMap());
        Admin admin = adminService.login(admin1);
        if( admin==null){
            System.out.println("用户名不存在或密码错误！");
            req.setAttribute("msg","用户名或密码错误！");
            req.setAttribute("username",admin1.getUsername());
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }else{
            System.out.println("登录成功！");
            HttpSession session = req.getSession();
            session.setAttribute("admin",admin);
            session.setAttribute("manager","管理员");
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
        }
    }
}
