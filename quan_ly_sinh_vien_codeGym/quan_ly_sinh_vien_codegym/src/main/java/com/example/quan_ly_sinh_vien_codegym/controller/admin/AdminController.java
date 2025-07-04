package com.example.quan_ly_sinh_vien_codegym.controller.admin;

import com.example.quan_ly_sinh_vien_codegym.dto.ClassResponseDto;
import com.example.quan_ly_sinh_vien_codegym.entity.Account;
import com.example.quan_ly_sinh_vien_codegym.entity.Student;
import com.example.quan_ly_sinh_vien_codegym.entity.Teacher;
import com.example.quan_ly_sinh_vien_codegym.service.IClassService;
import com.example.quan_ly_sinh_vien_codegym.service.IStudentService;
import com.example.quan_ly_sinh_vien_codegym.service.ITeacherService;
import com.example.quan_ly_sinh_vien_codegym.service.impl.ClassService;
import com.example.quan_ly_sinh_vien_codegym.service.impl.StudentService;
import com.example.quan_ly_sinh_vien_codegym.service.impl.TeacherService;
import com.example.quan_ly_sinh_vien_codegym.util.SessionUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/admin")
public class AdminController extends HttpServlet {

    private static IStudentService iStudentService = new StudentService();
    private static ITeacherService teacherService = new TeacherService();
    private static IClassService classService = new ClassService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = (Account) SessionUtil.get(req, "account");
        if (account == null){
            resp.sendRedirect("/login");
        }
        String page = req.getParameter("page");
        if (page == null){
            page = "";
        }
        switch (page){
            case "students":
                displayStudent(req, resp);
                break;
            case "teachers":
                displayTeacher(req, resp);
                break;
            case "classes":
                displayClass(req, resp);
                break;
            case "display-course":
                break;
            case "display-module":
                break;
            default:
                req.getRequestDispatcher("/WEB-INF/view/admin/admin.jsp").forward(req, resp);
        }
    }
    private void displayStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageSize = 5; // mỗi trang 5 học sinh
        int currentPage = 1;

        String pageParam = req.getParameter("currentPage");
        if (pageParam != null) {
            currentPage = Integer.parseInt(pageParam);
        }

        List<Student> allStudents = iStudentService.findAll();
        int totalStudents = allStudents.size();
        int totalPages = (int) Math.ceil((double) totalStudents / pageSize);

        int startIndex = (currentPage - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, totalStudents);
        List<Student> paginatedList = allStudents.subList(startIndex, endIndex);

        req.setAttribute("students", paginatedList);
        req.setAttribute("totalPages", totalPages);
        req.setAttribute("currentPage", currentPage);

        req.getRequestDispatcher("WEB-INF/view/admin/admin.jsp?page=students").forward(req, resp);
    }

    private void displayTeacher(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageSize = 5; // mỗi trang 5 học sinh
        int currentPage = 1;

        String pageParam = req.getParameter("currentPage");
        if (pageParam != null) {
            currentPage = Integer.parseInt(pageParam);
        }

        List<Teacher> allTeachers = teacherService.findAll();
        int totalTeachers = allTeachers.size();
        int totalPages = (int) Math.ceil((double) totalTeachers / pageSize);

        int startIndex = (currentPage - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, totalTeachers);
        List<Teacher> paginatedList = allTeachers.subList(startIndex, endIndex);
        
        req.setAttribute("teachers", paginatedList);
        req.setAttribute("totalPages", totalPages);
        req.setAttribute("currentPage", currentPage);

        req.getRequestDispatcher("WEB-INF/view/admin/admin.jsp?page=teachers").forward(req, resp);
    }

    private void displayClass(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageSize = 5; 
        int currentPage = 1;

        String pageParam = req.getParameter("currentPage");
        if (pageParam != null) {
            currentPage = Integer.parseInt(pageParam);
        }

        List<ClassResponseDto> allClasses = classService.findAll();
        int totalClasses = allClasses.size();
        int totalPages = (int) Math.ceil((double) totalClasses / pageSize);

        int startIndex = (currentPage - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, totalClasses);
        List<ClassResponseDto> paginatedList = allClasses.subList(startIndex, endIndex);

        req.setAttribute("classes", paginatedList);
        req.setAttribute("totalPages", totalPages);
        req.setAttribute("currentPage", currentPage);

        req.getRequestDispatcher("WEB-INF/view/admin/admin.jsp?page=classes").forward(req, resp);
    }


}
