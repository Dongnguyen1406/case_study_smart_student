package com.example.quan_ly_sinh_vien_codegym.controller;

import com.example.quan_ly_sinh_vien_codegym.entity.Account;
import com.example.quan_ly_sinh_vien_codegym.entity.Role;
import com.example.quan_ly_sinh_vien_codegym.entity.Student;
import com.example.quan_ly_sinh_vien_codegym.entity.Teacher;
import com.example.quan_ly_sinh_vien_codegym.service.IAccountService;
import com.example.quan_ly_sinh_vien_codegym.service.IRoleService;
import com.example.quan_ly_sinh_vien_codegym.service.IStudentService;
import com.example.quan_ly_sinh_vien_codegym.service.ITeacherService;
import com.example.quan_ly_sinh_vien_codegym.service.impl.AccountService;
import com.example.quan_ly_sinh_vien_codegym.service.impl.RoleService;
import com.example.quan_ly_sinh_vien_codegym.service.impl.StudentService;
import com.example.quan_ly_sinh_vien_codegym.service.impl.TeacherService;
import com.example.quan_ly_sinh_vien_codegym.util.PasswordEncodeUtil;
import com.example.quan_ly_sinh_vien_codegym.util.SessionUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/login", "/logout"})
public class LoginController extends HttpServlet {
    private IRoleService iRoleService = new RoleService();
    private IAccountService accountService = new AccountService();
    private IStudentService studentService = new StudentService();
    private ITeacherService teacherService = new TeacherService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        if ("/logout".equals(action)) {
            SessionUtil.remove(req, "account");
            resp.sendRedirect("/");
        } else {
            req.getRequestDispatcher("WEB-INF/view/login/login.jsp").forward(req, resp);
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
            Account account1 = accountService.findByUsername(username);
            SessionUtil.set(req, "account", account1);
            HttpSession session = req.getSession();

            if (account.getRoleId() == 1) {
                session.setAttribute("role", "admin");
                session.setAttribute("successMessage", "Đăng nhập admin thành công!");
                resp.sendRedirect("/admin");
            } else if (account.getRoleId() == 2) {
                Student student = studentService.displayStudent(username);
                if (student != null) {
                    session.setAttribute("student", student);
                    session.setAttribute("role", "user");
                    session.setAttribute("successMessage", "Đăng nhập thành công!");
                    resp.sendRedirect("/student");
                } else {
                    req.setAttribute("message", "student-not-found");
                    req.getRequestDispatcher("WEB-INF/view/login/login.jsp").forward(req, resp);
                }
            } else if (account.getRoleId() == 3) {


                String teacherId = account.getTeacherId();
                System.out.println("TeacherId from account: " + teacherId); // Debug
                if (teacherId != null && !teacherId.isEmpty()) {
                    Teacher teacher = teacherService.findById(teacherId);
                    if (teacher != null) {
                        System.out.println("Teacher found: " + teacher.getTeacherName()); // Debug
                        session.setAttribute("teacher", teacher);
                        session.setAttribute("role", "teacher");
                        session.setAttribute("successMessage", "Đăng nhập thành công!");
                        resp.sendRedirect("/teacher");
                    } else {
                        System.out.println("Teacher not found for teacherId: " + teacherId); // Debug
                        req.setAttribute("message", "teacher-not-found");
                        req.getRequestDispatcher("WEB-INF/view/login/login.jsp").forward(req, resp);
                    }
                } else {
                    System.out.println("TeacherId is null or empty for username: " + username); // Debug
                    req.setAttribute("message", "teacher-id-not-found");
                    req.getRequestDispatcher("WEB-INF/view/login/login.jsp").forward(req, resp);
                }
            } else {
                req.setAttribute("message", "role-error");
                req.getRequestDispatcher("WEB-INF/view/login/login.jsp").forward(req, resp);
            }
        }
    }
}