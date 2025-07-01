package com.example.quan_ly_sinh_vien_codegym.Controller.user;


import com.example.quan_ly_sinh_vien_codegym.Entity.Account;
import com.example.quan_ly_sinh_vien_codegym.Service.IAccountService;
import com.example.quan_ly_sinh_vien_codegym.Service.impl.AccountService;
import com.example.quan_ly_sinh_vien_codegym.util.SessionUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/login","/logout"})
public class LoginController extends HttpServlet {
    private IAccountService accountService = new AccountService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        if ("/logout".equals(action)) {
            SessionUtil.remove(req, "account");
            resp.sendRedirect("/");
        }else {
            req.getRequestDispatcher("WEB-INF/view/login/login.jsp").forward(req,resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Account account = accountService.checkLogin(username, password);
        if (account == null) {
            req.setAttribute("username", username);
            req.setAttribute("message", "login-error");
            req.getRequestDispatcher("WEB-INF/view/login/login.jsp").forward(req, resp);
        } else {
            SessionUtil.set(req, "account", account);
            SessionUtil.set(req, "message", "login-success");
            resp.sendRedirect("WEB-INF/view/student/student_form");
        }
    }
}
