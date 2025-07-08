package com.example.quan_ly_sinh_vien_codegym.controller.user;

import com.example.quan_ly_sinh_vien_codegym.entity.Account;
import com.example.quan_ly_sinh_vien_codegym.entity.Student;
import com.example.quan_ly_sinh_vien_codegym.service.IAccountService;
import com.example.quan_ly_sinh_vien_codegym.service.IModuleService;
import com.example.quan_ly_sinh_vien_codegym.service.IStudentService;
import com.example.quan_ly_sinh_vien_codegym.service.impl.AccountService;
import com.example.quan_ly_sinh_vien_codegym.service.impl.ModuleService;
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
import java.time.LocalDate;

@WebServlet(urlPatterns = "/student")
public class StudentController extends HttpServlet {
    private static final IStudentService iStudentService = new StudentService();
    private static final IAccountService iAccountService = new AccountService();
    private static final IModuleService moduleService = new ModuleService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = (Account) SessionUtil.get(req, "account");
        HttpSession session = req.getSession();
        String role = null;
        if (session != null) {
            role = (String) session.getAttribute("role");
        }
        if (role == null || (!role.equals("user") && !role.equals("admin"))) {
            resp.sendRedirect("/access-denied.jsp");
            return;
        }

        if (account == null) {
            resp.sendRedirect("/login");
        }
        String page = req.getParameter("page");
        if (page == null) {
            page = "";
        }
        switch (page) {
            case "update":
                update(req, resp);
                break;
            case "score":
                displayScore(req, resp);
                break;
            case "attendance":
                displayAttendance(req, resp);
                break;
            case "display":
                displayStudent(req, resp);
                break;
            case "updatePassword":
                updatePassword(req, resp);
                break;
            default:
                Student student = iStudentService.displayStudent(account.getUsername());
                session.setAttribute("student", student);
                req.getRequestDispatcher("WEB-INF/view/user/student.jsp").forward(req, resp);
        }


    }

    private void updatePassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/view/user/student.jsp?page=updatePassword").forward(req, resp);

    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = (Account) SessionUtil.get(req, "account");
        if (account != null) {
            req.setAttribute("student", iStudentService.displayStudent(account.getUsername()));
            req.getRequestDispatcher("WEB-INF/view/user/student.jsp?page=update").forward(req, resp);
        }
    }

    private void displayStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = (Account) SessionUtil.get(req, "account");
        if (account != null) {
            Student student = iStudentService.displayStudent(account.getUsername());

            req.setAttribute("student", student);
            req.getRequestDispatcher("WEB-INF/view/user/student.jsp?page=display").forward(req, resp);
        }

    }

    private void displayAttendance(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = (Account) SessionUtil.get(req, "account");
        if (account != null) {
            req.setAttribute("attendanceDate", iStudentService.displayAttendanceDate(account.getStudentId()));
            req.setAttribute("module", moduleService.findAll());
            req.setAttribute("moduleAttendance", iStudentService.displayAttendance(account.getUsername()));
            req.getRequestDispatcher("WEB-INF/view/user/student.jsp?page=attendance").forward(req, resp);
        }
    }


    private void displayScore(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = (Account) SessionUtil.get(req, "account");
        if (account != null) {
            req.setAttribute("moduleScore", iStudentService.displayScore(account.getUsername()));
            req.getRequestDispatcher("WEB-INF/view/user/student.jsp?page=score").forward(req, resp);
        }

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String role = null;
        if (session != null) {
            role = (String) session.getAttribute("role");
        }
        if (role == null || !role.equals("user") || !role.equals("admin")) {
            resp.sendRedirect("/access-denied.jsp");
            return;
        }
        String page = req.getParameter("page");
        if (page == null) {
            page = "";
        }
        switch (page) {
            case "update":
                edit(req, resp);
                break;
            case "updatePassword":
                savePassword(req, resp);
                break;
        }
    }

    private void savePassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String currentPassword = req.getParameter("currentPassword");
        String newPassword = req.getParameter("newPassword");
        String confirmNewPassword = req.getParameter("confirmNewPassword");

        Account account = (Account) SessionUtil.get(req, "account");
        System.out.println(account.getUsername());
        System.out.println(account.getPassword());
        if (account.getPassword() == null || !PasswordEncodeUtil.check(currentPassword, account.getPassword())) {
            req.setAttribute("error", "Mật khẩu hiện tại không đúng");
            req.getRequestDispatcher("WEB-INF/view/user/student.jsp?page=updatePassword").forward(req, resp);
            return;
        }

        if (!newPassword.equals(confirmNewPassword)) {
            req.setAttribute("newError", "Mật khẩu mới không khớp");
            req.getRequestDispatcher("WEB-INF/view/user/student.jsp?page=updatePassword").forward(req, resp);
            return;
        }

        String encodedPassword = PasswordEncodeUtil.encode(newPassword);
        iAccountService.updatePassword(account.getAccountId(), encodedPassword);

        // Cách 1: Hiển thị tại form đổi mật khẩu (nên dùng nếu người dùng muốn kiểm tra lại)
//        req.setAttribute("success", "Đổi mật khẩu thành công");
//        req.getRequestDispatcher("/WEB-INF/view/account/update-password.jsp").forward(req, resp);

        // --- HOẶC ---
        // Cách 2: Chuyển về trang chính

        HttpSession session = req.getSession();
        session.setAttribute("successMessage", "Cập nhật mật khẩu thành công!");
        resp.sendRedirect(req.getContextPath() + "/student?page=display");

    }


    private void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = (Account) SessionUtil.get(req, "account");
        if (account == null) {
            resp.sendRedirect("/login");
        } else {
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
            HttpSession session = req.getSession();
            session.setAttribute("successMessage", "Cập nhật thành công!");
            resp.sendRedirect(req.getContextPath() + "/student?page=display");

        }
    }

}
