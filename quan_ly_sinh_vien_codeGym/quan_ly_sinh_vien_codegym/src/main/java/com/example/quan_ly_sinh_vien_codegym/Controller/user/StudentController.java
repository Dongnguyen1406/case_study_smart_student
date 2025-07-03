package com.example.quan_ly_sinh_vien_codegym.Controller.user;

import com.example.quan_ly_sinh_vien_codegym.Entity.Account;
import com.example.quan_ly_sinh_vien_codegym.Entity.Student;
import com.example.quan_ly_sinh_vien_codegym.Service.IStudentService;
import com.example.quan_ly_sinh_vien_codegym.Service.impl.StudentService;
import com.example.quan_ly_sinh_vien_codegym.dto.ModuleScore;
import com.example.quan_ly_sinh_vien_codegym.util.SessionUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static jdk.internal.editor.external.ExternalEditor.edit;
import static jdk.jfr.internal.consumer.EventLog.update;

@WebServlet(urlPatterns = "/student")
public class StudentController extends HttpServlet {
    private static IStudentService iStudentService = new StudentService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = (Account) SessionUtil.get(req, "account");
        if (account == null) {
            resp.sendRedirect("/login");
        }
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "update":
                update(req, resp);
                break;
            case "score":
                displayScore(req,resp);
                        break;
            default:
                Student student= iStudentService.displayStudent(account.getUsername());
                req.setAttribute("student",student);
                req.getRequestDispatcher("/WEB-INF/view/student/student-form.jsp").forward(req,resp);
        }


    }

    private void displayScore(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = (Account) SessionUtil.get(req, "account");
        if (account != null) {
            req.setAttribute("moduleScore",iStudentService.displayScore(account.getUsername())) ;
            req.getRequestDispatcher("WEB-INF/view/student/module-score.jsp").forward(req, resp);
        }

    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = (Account) SessionUtil.get(req, "account");
        if (account != null) {
            req.setAttribute("student", iStudentService.displayStudent(account.getUsername()));
            req.getRequestDispatcher("WEB-INF/view/student/update.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "update":
                edit(req, resp);
                break;
        }
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = (Account) SessionUtil.get(req, "account");
        if (account == null) {
            resp.sendRedirect("/login");
        }else {
            String studentName = req.getParameter("studentName");
            LocalDate dob = LocalDate.parse(req.getParameter("dob"));
            String gender = req.getParameter("gender");
            String address = req.getParameter("address");
            String numberPhone = req.getParameter("numberPhone");
            String email = req.getParameter("email");
            LocalDate startLearnDate = LocalDate.parse(req.getParameter("startLearnDate"));
            String className = req.getParameter("className");
            Student student = new Student(account.getStudentId(), studentName, dob, gender, address, numberPhone, email, startLearnDate, className);
            iStudentService.updateStudent(student);
            resp.sendRedirect("/student");

        }
    }
    
}
