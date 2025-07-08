package com.example.quan_ly_sinh_vien_codegym.controller;

import com.example.quan_ly_sinh_vien_codegym.dto.ClassResponseDto;
import com.example.quan_ly_sinh_vien_codegym.dto.ModuleScore;
import com.example.quan_ly_sinh_vien_codegym.entity.Account;
import com.example.quan_ly_sinh_vien_codegym.entity.Assessment;
import com.example.quan_ly_sinh_vien_codegym.entity.Attendance;
import com.example.quan_ly_sinh_vien_codegym.entity.Module;
import com.example.quan_ly_sinh_vien_codegym.entity.Student;
import com.example.quan_ly_sinh_vien_codegym.entity.Teacher;
import com.example.quan_ly_sinh_vien_codegym.service.IAttendanceService;
import com.example.quan_ly_sinh_vien_codegym.service.IAssessmentService;
import com.example.quan_ly_sinh_vien_codegym.service.IClassService;
import com.example.quan_ly_sinh_vien_codegym.service.IModuleService;
import com.example.quan_ly_sinh_vien_codegym.service.IScoreService;
import com.example.quan_ly_sinh_vien_codegym.service.impl.AttendanceService;
import com.example.quan_ly_sinh_vien_codegym.service.impl.ClassService;
import com.example.quan_ly_sinh_vien_codegym.service.impl.ModuleService;
import com.example.quan_ly_sinh_vien_codegym.service.impl.ScoreService;
import com.example.quan_ly_sinh_vien_codegym.service.impl.AssessmentService;
import com.example.quan_ly_sinh_vien_codegym.util.SessionUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/teacher"})
public class TeacherController extends HttpServlet {
    private final IClassService classService = new ClassService();
    private final IModuleService moduleService = new ModuleService();
    private final IAttendanceService attendanceService = new AttendanceService();
    private final IAssessmentService assessmentService = new AssessmentService();
    private final IScoreService scoreService = new ScoreService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = (Account) SessionUtil.get(req, "account");
        HttpSession session = req.getSession(false);
        String role = null;
        if (session != null) {
            role = (String) session.getAttribute("role");
        }
        if (role == null || (!role.equals("teacher") && !role.equals("admin"))) {
            resp.sendRedirect("/access-denied.jsp");
            return;
        }
        String page = req.getParameter("page");
        if (page == null) page = "dashboard";

        Teacher teacher = (Teacher) session.getAttribute("teacher");
        String teacherId = (teacher != null) ? teacher.getTeacherId() : null;
        System.out.println("TeacherController - TeacherId: " + teacherId);

        List<ClassResponseDto> classes = (teacherId != null) ? classService.findByTeacherId(teacherId) : new ArrayList<>();
        System.out.println("TeacherController - Classes size: " + (classes != null ? classes.size() : "null"));
        session.setAttribute("managedClasses", classes);
        req.setAttribute("teacherClasses", classes);
        req.setAttribute("classes", classes);
        req.setAttribute("modules", moduleService.findAll());

        switch (page) {
            case "dashboard":
                displayDashboard(req, resp);
                break;
            case "attendance":
                displayAttendance(req, resp);
                break;
            case "assessment":
                displayAssessment(req, resp);
                break;
            case "score":
                displayScore(req, resp);
                break;
            default:
                req.getRequestDispatcher("/WEB-INF/view/teacher/main.jsp").forward(req, resp);
        }
    }

    private void displayDashboard(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/teacher/main.jsp").forward(req, resp);
    }

    private void displayAttendance(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String classIdStr = req.getParameter("classId");
        System.out.println("TeacherController - ClassId from request: " + classIdStr);
        if (classIdStr != null && !classIdStr.isEmpty()) {
            try {
                int classId = Integer.parseInt(classIdStr);
                @SuppressWarnings("unchecked")
                List<ClassResponseDto> managedClasses = (List<ClassResponseDto>) req.getSession().getAttribute("managedClasses");
                boolean isManagedClass = managedClasses != null && !managedClasses.isEmpty() && managedClasses.stream().anyMatch(c -> c.getClassId() == classId);
                if (!isManagedClass) {
                    req.setAttribute("error", "Bạn không có quyền truy cập lớp này!");
                    req.getRequestDispatcher("/WEB-INF/view/teacher/main.jsp").forward(req, resp);
                    return;
                }
                List<Student> students = attendanceService.findStudentsByClassId(classId);
                System.out.println("TeacherController - Students size for attendance: " + (students != null ? students.size() : "null"));
                req.setAttribute("students", students);
                req.setAttribute("attendanceStatuses", attendanceService.findAllStatuses());
            } catch (NumberFormatException e) {
                System.out.println("TeacherController - Invalid classId format: " + classIdStr);
                req.setAttribute("error", "ID lớp không hợp lệ");
            }
        }
        req.getRequestDispatcher("/WEB-INF/view/teacher/main.jsp?page=attendance&classId=" + req.getParameter("classId")).forward(req, resp);
    }

    private void displayAssessment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String classIdStr = req.getParameter("classId");
        String moduleIdStr = req.getParameter("moduleId");
        System.out.println("TeacherController - ClassId: " + classIdStr + ", ModuleId: " + moduleIdStr);
        if (classIdStr != null && moduleIdStr != null && !classIdStr.isEmpty() && !moduleIdStr.isEmpty()) {
            try {
                int classId = Integer.parseInt(classIdStr);
                int moduleId = Integer.parseInt(moduleIdStr);
                @SuppressWarnings("unchecked")
                List<ClassResponseDto> managedClasses = (List<ClassResponseDto>) req.getSession().getAttribute("managedClasses");
                boolean isManagedClass = managedClasses != null && !managedClasses.isEmpty() && managedClasses.stream().anyMatch(c -> c.getClassId() == classId);
                if (!isManagedClass) {
                    req.setAttribute("error", "Bạn không có quyền truy cập lớp này!");
                    req.getRequestDispatcher("/WEB-INF/view/teacher/main.jsp").forward(req, resp);
                    return;
                }
                List<Student> students = assessmentService.findStudentsByClassAndModule(classId, moduleId);
                System.out.println("TeacherController - Students size for assessment: " + (students != null ? students.size() : "null"));
                req.setAttribute("students", students);
            } catch (NumberFormatException e) {
                System.out.println("TeacherController - Invalid classId or moduleId format: " + classIdStr + ", " + moduleIdStr);
                req.setAttribute("error", "ID lớp hoặc module không hợp lệ");
            }
        }
        req.getRequestDispatcher("/WEB-INF/view/teacher/main.jsp?page=assessment&classId=" + req.getParameter("classId") + "&moduleId=" + req.getParameter("moduleId")).forward(req, resp);
    }

    private void displayScore(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String classIdStr = req.getParameter("classId");
        String moduleIdStr = req.getParameter("moduleId");
        System.out.println("TeacherController - ClassId: " + classIdStr + ", ModuleId: " + moduleIdStr);
        if (classIdStr != null && moduleIdStr != null && !classIdStr.isEmpty() && !moduleIdStr.isEmpty()) {
            try {
                int classId = Integer.parseInt(classIdStr);
                int moduleId = Integer.parseInt(moduleIdStr);
                @SuppressWarnings("unchecked")
                List<ClassResponseDto> managedClasses = (List<ClassResponseDto>) req.getSession().getAttribute("managedClasses");
                boolean isManagedClass = managedClasses != null && !managedClasses.isEmpty() && managedClasses.stream().anyMatch(c -> c.getClassId() == classId);
                if (!isManagedClass) {
                    req.setAttribute("error", "Bạn không có quyền truy cập lớp này!");
                    req.getRequestDispatcher("/WEB-INF/view/teacher/main.jsp").forward(req, resp);
                    return;
                }
                List<Student> students = scoreService.findStudentsByClassAndModule(classId, moduleId);
                System.out.println("TeacherController - Students size for score: " + (students != null ? students.size() : "null"));
                req.setAttribute("students", students);
            } catch (NumberFormatException e) {
                System.out.println("TeacherController - Invalid classId or moduleId format: " + classIdStr + ", " + moduleIdStr);
                req.setAttribute("error", "ID lớp hoặc module không hợp lệ");
            }
        }
        req.getRequestDispatcher("/WEB-INF/view/teacher/main.jsp?page=score&classId=" + req.getParameter("classId") + "&moduleId=" + req.getParameter("moduleId")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        String role = null;
        if (session != null) {
            role = (String) session.getAttribute("role");
        }
        if (role == null || (!role.equals("teacher") && !role.equals("admin"))) {
            resp.sendRedirect("/access-denied.jsp");
            return;
        }
        @SuppressWarnings("unchecked")
        List<ClassResponseDto> managedClasses = (List<ClassResponseDto>) session.getAttribute("managedClasses");
        if (managedClasses == null) {
            System.out.println("TeacherController - ManagedClasses is null in doPost");
            req.setAttribute("error", "Không thể xác định lớp quản lý!");
            req.getRequestDispatcher("/WEB-INF/view/teacher/main.jsp").forward(req, resp);
            return;
        }

        String page = req.getParameter("page");
        String classIdStr = req.getParameter("classId");
        String moduleId = req.getParameter("moduleId");
        List<Student> students = new ArrayList<>();

        if (classIdStr != null && !classIdStr.isEmpty()) {
            try {
                int classId = Integer.parseInt(classIdStr);
                boolean isManagedClass = managedClasses.stream().anyMatch(c -> c.getClassId() == classId);
                if (!isManagedClass) {
                    req.setAttribute("error", "Bạn không có quyền truy cập lớp này!");
                    req.getRequestDispatcher("/WEB-INF/view/teacher/main.jsp").forward(req, resp);
                    return;
                }
                students = attendanceService.findStudentsByClassId(classId);
            } catch (NumberFormatException e) {
                System.out.println("TeacherController - Invalid classId format: " + classIdStr);
                req.setAttribute("error", "ID lớp không hợp lệ");
                req.getRequestDispatcher("/WEB-INF/view/teacher/main.jsp").forward(req, resp);
                return;
            }
        }

        switch (page) {
            case "saveAttendance":
                saveAttendance(req, resp, students);
                break;
            case "saveAssessment":
                saveAssessment(req, resp, students, moduleId);
                break;
            case "saveScore":
                saveScore(req, resp, students, moduleId);
                break;
            default:
                req.getRequestDispatcher("/WEB-INF/view/teacher/main.jsp?page=" + page + "&classId=" + classIdStr + "&moduleId=" + moduleId).forward(req, resp);
        }
    }

    private void saveAttendance(HttpServletRequest req, HttpServletResponse resp, List<Student> students) throws ServletException, IOException {
        String attendanceDate = req.getParameter("attendanceDate");
        List<Attendance> attendances = new ArrayList<>();
        for (Student student : students) {
            String statusIdStr = req.getParameter("status_" + student.getStudentId());
            if (statusIdStr != null && !statusIdStr.isEmpty()) {
                Attendance attendance = new Attendance(0, student.getStudentId(), LocalDate.parse(attendanceDate), Integer.parseInt(statusIdStr));
                attendances.add(attendance);
            }
        }
        attendanceService.saveAttendance(attendances);
        req.getSession().setAttribute("successMessage", "Lưu điểm danh thành công!");
        resp.sendRedirect("/teacher?page=attendance&classId=" + req.getParameter("classId"));
    }

    private void saveAssessment(HttpServletRequest req, HttpServletResponse resp, List<Student> students, String moduleId) throws ServletException, IOException {
        if (moduleId == null || moduleId.isEmpty()) {
            req.setAttribute("error", "Module ID không hợp lệ!");
            req.getRequestDispatcher("/WEB-INF/view/teacher/main.jsp").forward(req, resp);
            return;
        }
        List<Assessment> assessments = new ArrayList<>();
        for (Student student : students) {
            String scoreStr = req.getParameter("averageScore_" + student.getStudentId());
            if (scoreStr != null && !scoreStr.isEmpty()) {
                double score = Double.parseDouble(scoreStr);
                boolean status = score >= 7.5;
                Assessment assessment = new Assessment(0, score, student.getStudentId(), Integer.parseInt(moduleId), status);
                assessments.add(assessment);
            }
        }
        assessmentService.saveAssessments(assessments);
        req.getSession().setAttribute("successMessage", "Lưu đánh giá thành công!");
        resp.sendRedirect("/teacher?page=assessment&classId=" + req.getParameter("classId") + "&moduleId=" + moduleId);
    }

    private void saveScore(HttpServletRequest req, HttpServletResponse resp, List<Student> students, String moduleId) throws ServletException, IOException {
        if (moduleId == null || moduleId.isEmpty()) {
            req.setAttribute("error", "Module ID không hợp lệ!");
            req.getRequestDispatcher("/WEB-INF/view/teacher/main.jsp").forward(req, resp);
            return;
        }
        List<ModuleScore> scores = new ArrayList<>();
        for (Student student : students) {
            String quizScoreStr = req.getParameter("quizScore_" + student.getStudentId());
            String practiceScoreStr = req.getParameter("practiceScore_" + student.getStudentId());
            if (quizScoreStr != null && practiceScoreStr != null && !quizScoreStr.isEmpty() && !practiceScoreStr.isEmpty()) {
                ModuleScore score = new ModuleScore(null, Double.parseDouble(quizScoreStr), Double.parseDouble(practiceScoreStr), 0);
                score.setStudentId(student.getStudentId());
                score.setModuleId(Integer.parseInt(moduleId));
                scores.add(score);
            }
        }
        scoreService.saveScores(scores);
        req.getSession().setAttribute("successMessage", "Lưu điểm thành công!");
        resp.sendRedirect("/teacher?page=score&classId=" + req.getParameter("classId") + "&moduleId=" + moduleId);
    }
}