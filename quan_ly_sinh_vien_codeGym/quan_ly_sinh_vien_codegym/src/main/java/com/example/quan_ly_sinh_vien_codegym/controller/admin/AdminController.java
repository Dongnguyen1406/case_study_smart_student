package com.example.quan_ly_sinh_vien_codegym.controller.admin;

import com.example.quan_ly_sinh_vien_codegym.dto.ClassResponseDto;
import com.example.quan_ly_sinh_vien_codegym.service.*;
import com.example.quan_ly_sinh_vien_codegym.entity.Account;
import com.example.quan_ly_sinh_vien_codegym.entity.Student;
import com.example.quan_ly_sinh_vien_codegym.entity.Teacher;
import com.example.quan_ly_sinh_vien_codegym.entity.Course;
import com.example.quan_ly_sinh_vien_codegym.entity.Module;

import com.example.quan_ly_sinh_vien_codegym.service.impl.*;
import com.example.quan_ly_sinh_vien_codegym.util.SessionUtil;
import com.example.quan_ly_sinh_vien_codegym.entity.Module;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
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
        HttpSession session = req.getSession(false);
        String role = null;
        if (session != null) {
            role = (String) session.getAttribute("role");
        }
        if (role == null || !role.equals("admin")) {
            resp.sendRedirect("/access-denied.jsp");
            return;
        }

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
                displayModule(req, resp);
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
        req.setAttribute("startIndex", startIndex);
        int endIndex = Math.min(startIndex + pageSize, totalStudents);
        List<Student> paginatedList = allStudents.subList(startIndex, endIndex);

        req.setAttribute("students", paginatedList);
        req.setAttribute("totalPages", totalPages);
        req.setAttribute("currentPage", currentPage);
        req.setAttribute("pageType", "students");
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
        req.setAttribute("startIndex", startIndex);
        int endIndex = Math.min(startIndex + pageSize, totalTeachers);
        List<Teacher> paginatedList = allTeachers.subList(startIndex, endIndex);
        
        req.setAttribute("teachers", paginatedList);
        req.setAttribute("totalPages", totalPages);
        req.setAttribute("currentPage", currentPage);
        req.setAttribute("pageType", "teachers");

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
        req.setAttribute("startIndex", startIndex);
        int endIndex = Math.min(startIndex + pageSize, totalClasses);
        List<ClassResponseDto> paginatedList = allClasses.subList(startIndex, endIndex);

        req.setAttribute("classes", paginatedList);
        req.setAttribute("modules", moduleService.findAll());
        req.setAttribute("courses", courseService.findAll());
        req.setAttribute("teachers", teacherService.findAll());
        req.setAttribute("totalPages", totalPages);
        req.setAttribute("currentPage", currentPage);
        req.setAttribute("pageType", "classes");

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
        req.setAttribute("startIndex", startIndex);
        int endIndex = Math.min(startIndex + pageSize, totalCourses);
        List<Course> paginatedList = allCourses.subList(startIndex, endIndex);

        req.setAttribute("courses", paginatedList);
        req.setAttribute("totalPages", totalPages);
        req.setAttribute("currentPage", currentPage);
        req.setAttribute("pageType", "courses");
        req.getRequestDispatcher("WEB-INF/view/admin/admin.jsp?page=courses").forward(req, resp);
    }
    

    private void displayModule(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageSize = 5;
        int currentPage = 1;

        String pageParam = req.getParameter("currentPage");
        if (pageParam != null) {
            currentPage = Integer.parseInt(pageParam);
        }

        List<Module> allModules = moduleService.findAll();
        int totalModules = allModules.size();
        int totalPages = (int) Math.ceil((double) totalModules / pageSize);

        int startIndex = (currentPage - 1) * pageSize;
        req.setAttribute("startIndex", startIndex);
        int endIndex = Math.min(startIndex + pageSize, totalModules);
        List<Module> paginatedList = allModules.subList(startIndex, endIndex);

        req.setAttribute("modules", paginatedList);
        req.setAttribute("totalPages", totalPages);
        req.setAttribute("currentPage", currentPage);
        req.setAttribute("pageType", "modules"); 

        req.getRequestDispatcher("WEB-INF/view/admin/admin.jsp?page=modules").forward(req, resp);
    }
    
    

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        String role = null;
        if (session != null) {
            role = (String) session.getAttribute("role");
        }
        if (role == null || !role.equals("admin")) {
            resp.sendRedirect("/access-denied.jsp");
            return;
        }
        String page = req.getParameter("page");
        if (page == null) page = "";

        switch (page) {
            case "addStudent":
//                handleAddStudent(req, resp);
                break;
            case "addCourse":
                handleAddCourse(req, resp);
                break;
            case "addModule":
                handleAddModule(req, resp);
                break;
            case "updateStudent":
                handleUpdateStudent(req, resp);
                break;
            case "updateTeacher":
                handleUpdateTeacher(req, resp);
                break;
            case "updateClass":
                handleUpdateClass(req, resp);
                break;
            case "updateCourse":
                handleUpdateCourse(req, resp);
                break;
            case "updateModule":
                handleUpdateModule(req, resp);
                break;
            case "deleteStudent":
                handleDeleteStudent(req, resp);
                break;
            case "deleteTeacher":
                handleDeleteTeacher(req, resp);
                break;
            case "deleteClass":
                handleDeleteClass(req, resp);
                break;
            case "deleteCourse":
                handleDeleteCourse(req, resp);
                break;
            case "deleteModule":
                handleDeleteModule(req, resp);
                break;
            default:
                resp.sendRedirect("/admin?page=students");
        }
    }

    private void handleAddCourse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");

        if (name != null && !name.trim().isEmpty()) {
            Course course = new Course();
            course.setCourseName(name.trim());

            courseService.add(course);
            resp.sendRedirect("/admin?page=courses");
        } else {
            resp.sendRedirect("/admin?page=courses&error=emptyName");
        }
    }

    private void handleAddModule(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");

        if (name != null && !name.trim().isEmpty()) {
            Module module = new Module();
            module.setModuleName(name.trim());

            moduleService.add(module);
            resp.sendRedirect("/admin?page=modules");
        } else {
            resp.sendRedirect("/admin?page=modules&error=emptyName");
        }
    }


    private void handleUpdateStudent(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        String fullName = req.getParameter("fullname");
        String gender = req.getParameter("gender");
        String dob = req.getParameter("dob");
        String address = req.getParameter("address");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String className = req.getParameter("className");
        String statusStr = req.getParameter("status");

        // Chuyển đổi kiểu dữ liệu
        LocalDate parsedDob = LocalDate.parse(dob);
        boolean status = "1".equals(statusStr);

        Student student = new Student();
        student.setStudentId(id);
        student.setStudentName(fullName);
        student.setGender(gender);
        student.setDob(parsedDob);
        student.setAddress(address);
        student.setEmail(email);
        student.setNumberPhone(phone);
        student.setClassName(className);
        student.setStatus(status);

        boolean isUpdated = iStudentService.updateStudent(student);
        if (isUpdated) {
            resp.sendRedirect("/admin?page=students");
        } else {
            resp.sendRedirect("/admin?page=students&error=updateFailed");
        }
    }

    private void handleUpdateTeacher(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        String fullName = req.getParameter("fullname");
        String gender = req.getParameter("gender");
        String dob = req.getParameter("dob");
        String address = req.getParameter("address");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String statusStr = req.getParameter("status");

        // Chuyển đổi kiểu dữ liệu
        LocalDate parsedDob = LocalDate.parse(dob);
        boolean status = "1".equals(statusStr);

        Teacher teacher = new Teacher();
        teacher.setTeacherId(id);
        teacher.setTeacherName(fullName);
        teacher.setGender(gender);
        teacher.setDob(parsedDob);
        teacher.setAddress(address);
        teacher.setEmail(email);
        teacher.setNumberPhone(phone);
        teacher.setStatus(status);

        boolean isUpdated = teacherService.update(teacher);
        if (isUpdated) {
            resp.sendRedirect("/admin?page=teachers");
        } else {
            resp.sendRedirect("/admin?page=teachers&error=updateFailed");
        }
    }

    private void handleUpdateClass(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String className = req.getParameter("className");
            int moduleId = Integer.parseInt(req.getParameter("module"));
            int courseId = Integer.parseInt(req.getParameter("course"));
            String teacherName = req.getParameter("teacher");
            int quantity = Integer.parseInt(req.getParameter("size"));
            LocalDate startDate = LocalDate.parse(req.getParameter("startDate"));

            ClassResponseDto classDto = new ClassResponseDto();
            classDto.setClassId(id);
            classDto.setClassName(className);
            classDto.setModuleId(moduleId);
            classDto.setCourseId(courseId);
            classDto.setTeacherName(teacherName);
            classDto.setQuantity(quantity);
            classDto.setStartDate(startDate);

            boolean isUpdated = classService.update(classDto);

            if (isUpdated) {
                resp.sendRedirect("/admin?page=classes");
            } else {
                resp.sendRedirect("/admin?page=classes&error=updateFailed");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("/admin?page=classes&error=exception");
        }
    }

    private void handleUpdateCourse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id")) ;
        String name = req.getParameter("name");
        
        Course course = new Course();
        course.setCourseId(id);
        course.setCourseName(name);

        boolean isUpdated = courseService.update(course);
        if (isUpdated) {
            resp.sendRedirect("/admin?page=courses");
        } else {
            resp.sendRedirect("/admin?page=courses&error=updateFailed");
        }
    }

    private void handleUpdateModule(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id")) ;
        String name = req.getParameter("name");

        Module module = new Module();
        module.setModuleId(id);
        module.setModuleName(name);

        boolean isUpdated = moduleService.update(module);
        if (isUpdated) {
            resp.sendRedirect("/admin?page=modules");
        } else {
            resp.sendRedirect("/admin?page=modules&error=updateFailed");
        }
    }


    private void handleDeleteStudent(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");

        if (id != null && !id.isEmpty()) {
            boolean deleted = iStudentService.delete(id); // gọi hàm xóa từ service
            if (deleted) {
                resp.sendRedirect("/admin?page=students");
            } else {
                resp.sendRedirect("/admin?page=students&error=deleteFailed");
            }
        } else {
            resp.sendRedirect("/admin?page=students&error=invalidId");
        }
    }

    private void handleDeleteTeacher(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");

        if (id != null && !id.isEmpty()) {
            boolean deleted = teacherService.delete(id); // gọi hàm xóa từ service
            if (deleted) {
                resp.sendRedirect("/admin?page=teachers");
            } else {
                resp.sendRedirect("/admin?page=teachers&error=deleteFailed");
            }
        } else {
            resp.sendRedirect("/admin?page=teachers&error=invalidId");
        }
    }

    private void handleDeleteClass(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idParam = req.getParameter("id");

        if (idParam != null && !idParam.isEmpty()) {
            try {
                int id = Integer.parseInt(idParam); // chuyển sang int sau khi kiểm tra null & empty
                boolean deleted = classService.deleteById(id); // gọi hàm xóa từ service

                if (deleted) {
                    resp.sendRedirect("/admin?page=classes");
                } else {
                    resp.sendRedirect("/admin?page=classes&error=deleteFailed");
                }
            } catch (NumberFormatException e) {
                // xử lý trường hợp id không phải là số hợp lệ
                resp.sendRedirect("/admin?page=classes&error=invalidIdFormat");
            }
        } else {
            resp.sendRedirect("/admin?page=classes&error=invalidId");
        }
    }

    private void handleDeleteCourse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idParam = req.getParameter("id");

        if (idParam != null && !idParam.isEmpty()) {
            try {
                int id = Integer.parseInt(idParam); // chuyển sang int sau khi kiểm tra null & empty
                boolean deleted = courseService.deleteById(id); // gọi hàm xóa từ service

                if (deleted) {
                    resp.sendRedirect("/admin?page=courses");
                } else {
                    resp.sendRedirect("/admin?page=courses&error=deleteFailed");
                }
            } catch (NumberFormatException e) {
                // xử lý trường hợp id không phải là số hợp lệ
                resp.sendRedirect("/admin?page=courses&error=invalidIdFormat");
            }
        } else {
            resp.sendRedirect("/admin?page=courses&error=invalidId");
        }
    }

    private void handleDeleteModule(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idParam = req.getParameter("id");

        if (idParam != null && !idParam.isEmpty()) {
            try {
                int id = Integer.parseInt(idParam); // chuyển sang int sau khi kiểm tra null & empty
                boolean deleted = moduleService.deleteById(id); // gọi hàm xóa từ service

                if (deleted) {
                    resp.sendRedirect("/admin?page=modules");
                } else {
                    resp.sendRedirect("/admin?page=modules&error=deleteFailed");
                }
            } catch (NumberFormatException e) {
                // xử lý trường hợp id không phải là số hợp lệ
                resp.sendRedirect("/admin?page=modules&error=invalidIdFormat");
            }
        } else {
            resp.sendRedirect("/admin?page=modules&error=invalidId");
        }
    }




}
