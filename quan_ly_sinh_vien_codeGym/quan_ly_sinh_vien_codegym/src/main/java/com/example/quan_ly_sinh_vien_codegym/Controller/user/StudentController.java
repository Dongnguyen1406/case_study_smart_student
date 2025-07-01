package com.example.quan_ly_sinh_vien_codegym.Controller.user;

import com.example.quan_ly_sinh_vien_codegym.Service.IStudentService;
import com.example.quan_ly_sinh_vien_codegym.Service.impl.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/student")
public class StudentController extends HttpServlet {
    private static IStudentService iStudentService= new StudentService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/view/login/login.jsp").forward(req,resp);
//        List<Student> students= iStudentService.findAll();
//        req.setAttribute("students",students);
//        req.getRequestDispatcher("/view/student_form.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
