package com.example.quan_ly_sinh_vien_codegym.controller.user;


import com.example.quan_ly_sinh_vien_codegym.entity.Account;
import com.example.quan_ly_sinh_vien_codegym.service.IAccountService;
import com.example.quan_ly_sinh_vien_codegym.service.IStudentService;
import com.example.quan_ly_sinh_vien_codegym.service.impl.AccountService;
import com.example.quan_ly_sinh_vien_codegym.service.impl.StudentService;
import com.example.quan_ly_sinh_vien_codegym.util.PasswordEncodeUtil;
import com.example.quan_ly_sinh_vien_codegym.util.SessionUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/login","/logout"})
public class LoginController extends HttpServlet {
    private IAccountService accountService = new AccountService();
    private static IStudentService iStudentService= new StudentService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//       String hashedPassword = PasswordEncodeUtil.encode("123");
//       System.out.println("---------------------------");
//        System.out.println(hashedPassword);

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
            if(account.getRoleId()==1){
                HttpSession session = req.getSession();
                session.setAttribute("success", "Đăng nhập admin thành công!");
                resp.sendRedirect("/admin");
            } else if (account.getRoleId()==2) {
                HttpSession session = req.getSession();
                session.setAttribute("successMessage", "Đăng nhập thành công!");
                resp.sendRedirect("/student");
            }else {
//                teacher
            }


        }
    }
}
