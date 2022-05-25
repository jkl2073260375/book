package com.book.web;

import com.book.pojo.User;
import com.book.service.UserService;
import com.book.service.impl.UserServiceImpl;
import com.book.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserServlet extends BaseServlet {
    private UserService userService=new UserServiceImpl();
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        User user1 = WebUtils.copyParamToBean(new User(),req.getParameterMap());
        User user=userService.login(user1);
        if( user==null){
            System.out.println("用户名不存在或密码错误！");
            req.setAttribute("msg","用户名或密码错误！");
            req.setAttribute("username",user1.getUsername());
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }else{
            System.out.println("登录成功！");
            HttpSession session = req.getSession();
            session.setAttribute("user",user);
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
        }
    }

    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        //获取谷歌验证码中的字符串
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        req.setAttribute("token",token);
        //将Session域中的验证码去除
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        String code = req.getParameter("code");
        User user = WebUtils.copyParamToBean(new User(),req.getParameterMap());
        if(token!=null && token.equalsIgnoreCase(code)){
            if(userService.existsUsername(user.getUsername())){
                System.out.println("用户名"+user.getUsername()+"已存在！");
                req.setAttribute("username","用户名已存在");
                req.setAttribute("password",user.getPassword());
                req.setAttribute("email",user.getEmail());
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
            }else{
                System.out.println("用户名"+user.getUsername()+"可用!");
                userService.registUser(user);
                HttpSession session = req.getSession();
                session.setAttribute("user",user);
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,resp);
                System.out.println("注册成功！");
            }
        }else{
            System.out.println("验证码"+code+"错误");
//            req.setAttribute("error","验证码输入错误,请重新输入");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
        }

    }
    protected void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        boolean existsUsername = userService.existsUsername(username);
        Map<String ,Object> map=new HashMap<>();
        map.put("existsUsername",existsUsername);
        Gson gson=new Gson();
        String json = gson.toJson(map);
        resp.getWriter().write(json);
    }
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        HttpSession session = req.getSession();
        session.invalidate();
        resp.sendRedirect(req.getContextPath());
    }

}
