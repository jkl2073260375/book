package com.book.filter;



import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ManagerFilter implements  Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }


    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req= (HttpServletRequest) servletRequest;
        HttpSession session = req.getSession();
        Object admin = session.getAttribute("admin");
        Object user = session.getAttribute("user");
        if(user!=null){
            session.invalidate();
        }
        if(admin==null){
            req.getSession().setAttribute("manager",1);
            req.getRequestDispatcher("/pages/user/login.jsp").forward(servletRequest,servletResponse);
        }
        if(admin!=null){
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }


    public void destroy() {

    }


    public boolean test(int node) {
        return false;
    }
}
