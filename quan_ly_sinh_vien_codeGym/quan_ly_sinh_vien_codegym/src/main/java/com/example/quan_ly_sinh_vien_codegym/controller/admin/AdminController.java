package com.example.quan_ly_sinh_vien_codegym.controller.admin;

import com.example.quan_ly_sinh_vien_codegym.dto.ClassResponseDto;
import com.example.quan_ly_sinh_vien_codegym.entity.*;
import com.example.quan_ly_sinh_vien_codegym.service.*;
import com.example.quan_ly_sinh_vien_codegym.service.impl.*;
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
    private static ICourseService courseService = new CourseService();
    private static IModuleService moduleService = new ModuleService();

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
            case "courses":
                displayCourse(req, resp);
                break;
            case "modules":
//                displayModule(req, resp);
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

    private void displayCourse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageSize = 5;
        int currentPage = 1;

        String pageParam = req.getParameter("currentPage");
        if (pageParam != null) {
            currentPage = Integer.parseInt(pageParam);
        }

        List<Course> allCourses = courseService.findAll();
        int totalCourses = allCourses.size();
        int totalPages = (int) Math.ceil((double) totalCourses / pageSize);

        int startIndex = (currentPage - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, totalCourses);
        List<Course> paginatedList = allCourses.subList(startIndex, endIndex);

        req.setAttribute("courses", paginatedList);
        req.setAttribute("totalPages", totalPages);
        req.setAttribute("currentPage", currentPage);

        req.getRequestDispatcher("WEB-INF/view/admin/admin.jsp?page=courses").forward(req, resp);
    }

//    private void displayModule(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        int pageSize = 5;
//        int currentPage = 1;
//
//        String pageParam = req.getParameter("currentPage");
//        if (pageParam != null) {
//            currentPage = Integer.parseInt(pageParam);
//        }
//
//        List<Module> allModules = moduleService.findAll();
//        int totalModules = allModules.size();
//        int totalPages = (int) Math.ceil((double) totalModules / pageSize);
//
//        int startIndex = (currentPage - 1) * pageSize;
//        int endIndex = Math.min(startIndex + pageSize, totalModules);
//        List<Module> paginatedList = allModules.subList(startIndex, endIndex);
//
//        req.setAttribute("modules", paginatedList);
//        req.setAttribute("totalPages", totalPages);
//        req.setAttribute("currentPage", currentPage);
//
//        req.getRequestDispatcher("WEB-INF/view/admin/admin.jsp?page=modules").forward(req, resp);
//    }

}
