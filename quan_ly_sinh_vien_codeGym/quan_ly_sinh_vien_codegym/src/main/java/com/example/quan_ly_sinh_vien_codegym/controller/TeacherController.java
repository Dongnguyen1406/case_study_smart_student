package com.example.quan_ly_sinh_vien_codegym.controller;

import com.example.quan_ly_sinh_vien_codegym.dto.ClassResponseDto;
import com.example.quan_ly_sinh_vien_codegym.dto.ModuleScore;
import com.example.quan_ly_sinh_vien_codegym.entity.Account;
import com.example.quan_ly_sinh_vien_codegym.entity.Assessment;
import com.example.quan_ly_sinh_vien_codegym.entity.Attendance;
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
import java.util.Map;
import java.util.Collections;

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
        HttpSession session = req.getSession();
        String role = null;
        if (session != null) {
            role = (String) session.getAttribute("role");
        }
        if (role == null || (!role.equals("user") && !role.equals("admin"))) {
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
                
                // If date parameter is provided, load existing attendance records
                String attendanceDateStr = req.getParameter("attendanceDate");
                if (attendanceDateStr != null && !attendanceDateStr.isEmpty()) {
                    LocalDate attendanceDate = LocalDate.parse(attendanceDateStr);
                    Map<String, Integer> existingAttendance = attendanceService.findAttendanceByClassAndDate(classId, attendanceDate);
                    System.out.println("TeacherController - Existing attendance size: " + (existingAttendance != null ? existingAttendance.size() : "null"));
                    req.setAttribute("existingAttendance", existingAttendance);
                }
            } catch (NumberFormatException e) {
                System.out.println("TeacherController - Invalid classId format: " + classIdStr);
                req.setAttribute("error", "ID lớp không hợp lệ");
            }
        }
        req.getRequestDispatcher("/WEB-INF/view/teacher/main.jsp?page=attendance&classId=" + req.getParameter("classId") + "&attendanceDate=" + req.getParameter("attendanceDate")).forward(req, resp);
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
                Map<String, Assessment> existingAssessments = assessmentService.findAssessmentsByClassAndModule(classId, moduleId);
                
                System.out.println("TeacherController - Students size for assessment: " + (students != null ? students.size() : "null"));
                System.out.println("TeacherController - Existing assessments size: " + (existingAssessments != null ? existingAssessments.size() : "null"));
                
                req.setAttribute("students", students);
                req.setAttribute("existingAssessments", existingAssessments);
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
                Map<String, ModuleScore> existingScores = scoreService.findScoresByClassAndModule(classId, moduleId);
                
                System.out.println("TeacherController - Students size for score: " + (students != null ? students.size() : "null"));
                System.out.println("TeacherController - Existing scores size: " + (existingScores != null ? existingScores.size() : "null"));
                
                req.setAttribute("students", students);
                req.setAttribute("existingScores", existingScores);
            } catch (NumberFormatException e) {
                System.out.println("TeacherController - Invalid classId or moduleId format: " + classIdStr + ", " + moduleIdStr);
                req.setAttribute("error", "ID lớp hoặc module không hợp lệ");
            }
        }
        req.getRequestDispatcher("/WEB-INF/view/teacher/main.jsp?page=score&classId=" + req.getParameter("classId") + "&moduleId=" + req.getParameter("moduleId")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String role = null;
        if (session != null) {
            role = (String) session.getAttribute("role");
        }
        if (role == null || (!role.equals("user") && !role.equals("admin"))) {
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
            case "saveStudentAttendance":
                saveStudentAttendance(req, resp);
                break;
            case "saveAssessment":
                saveAssessment(req, resp, students, moduleId);
                break;
            case "saveStudentAssessment":
                saveStudentAssessment(req, resp);
                break;
            case "saveScore":
                saveScore(req, resp, students, moduleId);
                break;
            case "saveStudentScore":
                saveStudentScore(req, resp);
                break;
            default:
                req.getRequestDispatcher("/WEB-INF/view/teacher/main.jsp?page=" + page + "&classId=" + classIdStr + "&moduleId=" + moduleId).forward(req, resp);
        }
    }

    private void saveAttendance(HttpServletRequest req, HttpServletResponse resp, List<Student> students) throws ServletException, IOException {
        String attendanceDate = req.getParameter("attendanceDate");
        if (attendanceDate == null || attendanceDate.isEmpty()) {
            req.setAttribute("error", "Ngày điểm danh không hợp lệ!");
            req.getRequestDispatcher("/WEB-INF/view/teacher/main.jsp").forward(req, resp);
            return;
        }
        
        int classId = Integer.parseInt(req.getParameter("classId"));
        LocalDate parsedDate = LocalDate.parse(attendanceDate);
        
        // First delete existing attendance records for this class and date
        attendanceService.deleteAttendanceByClassAndDate(classId, parsedDate);
        
        List<Attendance> attendances = new ArrayList<>();
        for (Student student : students) {
            String statusIdStr = req.getParameter("status_" + student.getStudentId());
            if (statusIdStr != null && !statusIdStr.isEmpty()) {
                Attendance attendance = new Attendance(0, student.getStudentId(), parsedDate, Integer.parseInt(statusIdStr));
                attendances.add(attendance);
            }
        }
        attendanceService.saveAttendance(attendances);
        req.getSession().setAttribute("successMessage", "Lưu điểm danh thành công!");
        resp.sendRedirect("/teacher?page=attendance&classId=" + req.getParameter("classId") + "&attendanceDate=" + attendanceDate);
    }

    private void saveStudentAttendance(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String classIdStr = req.getParameter("classId");
        String attendanceDateStr = req.getParameter("attendanceDate");
        String studentId = req.getParameter("studentId");
        String statusIdStr = req.getParameter("statusId");
        
        if (classIdStr == null || attendanceDateStr == null || studentId == null || statusIdStr == null || 
            classIdStr.isEmpty() || attendanceDateStr.isEmpty() || studentId.isEmpty() || statusIdStr.isEmpty()) {
            
            req.setAttribute("error", "Thông tin điểm danh không hợp lệ!");
            req.getRequestDispatcher("/WEB-INF/view/teacher/main.jsp").forward(req, resp);
            return;
        }
        
        try {
            int classId = Integer.parseInt(classIdStr);
            LocalDate attendanceDate = LocalDate.parse(attendanceDateStr);
            int statusId = Integer.parseInt(statusIdStr);
            
            // Create a list with just this one attendance record
            List<Attendance> attendances = new ArrayList<>();
            Attendance attendance = new Attendance(0, studentId, attendanceDate, statusId);
            attendances.add(attendance);
            
            // Delete existing attendance for this student on this date
            attendanceService.deleteStudentAttendanceByDate(studentId, attendanceDate);
            
            // Save the new attendance
            attendanceService.saveAttendance(attendances);
            req.getSession().setAttribute("successMessage", "Lưu điểm danh thành công!");
            
        } catch (NumberFormatException e) {
            System.out.println("TeacherController - Invalid number format: " + e.getMessage());
            req.setAttribute("error", "Định dạng số không hợp lệ");
            req.getRequestDispatcher("/WEB-INF/view/teacher/main.jsp").forward(req, resp);
            return;
        }
        
        resp.sendRedirect("/teacher?page=attendance&classId=" + classIdStr + "&attendanceDate=" + attendanceDateStr);
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
        
        // First, delete existing assessments for these students in this module
        int classId = Integer.parseInt(req.getParameter("classId"));
        assessmentService.deleteAssessmentsByClassAndModule(classId, Integer.parseInt(moduleId));
        
        // Then save the new/updated assessments
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
        
        // First, delete existing scores for these students in this module
        int classId = Integer.parseInt(req.getParameter("classId"));
        scoreService.deleteScoresByClassAndModule(classId, Integer.parseInt(moduleId));
        
        // Then save the new/updated scores
        scoreService.saveScores(scores);
        req.getSession().setAttribute("successMessage", "Lưu điểm thành công!");
        resp.sendRedirect("/teacher?page=score&classId=" + req.getParameter("classId") + "&moduleId=" + moduleId);
    }

    private void saveStudentScore(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String classIdStr = req.getParameter("classId");
            String moduleIdStr = req.getParameter("moduleId");
            String studentId = req.getParameter("studentId");
            String quizScoreStr = req.getParameter("quizScore");
            String practiceScoreStr = req.getParameter("practiceScore");
            
            // Validate input parameters
            if (classIdStr == null || moduleIdStr == null || studentId == null || 
                quizScoreStr == null || practiceScoreStr == null || 
                classIdStr.isEmpty() || moduleIdStr.isEmpty() || studentId.isEmpty() || 
                quizScoreStr.isEmpty() || practiceScoreStr.isEmpty()) {
                
                req.setAttribute("error", "Vui lòng điền đầy đủ thông tin");
                req.getRequestDispatcher("/WEB-INF/view/teacher/score.jsp").forward(req, resp);
                return;
            }

            // Parse parameters
            int classId = Integer.parseInt(classIdStr);
            int moduleId = Integer.parseInt(moduleIdStr);
            double quizScore = Double.parseDouble(quizScoreStr);
            double practiceScore = Double.parseDouble(practiceScoreStr);

            // Create ModuleScore object
            ModuleScore score = new ModuleScore(studentId, moduleId, quizScore, practiceScore);

            // Save score
            scoreService.saveScores(Collections.singletonList(score));

            // Use URL encoding for special characters
            String encodedMessage = java.net.URLEncoder.encode("Lưu điểm thành công", "UTF-8");
            
            // Construct redirect URL manually to avoid header issues
            String redirectUrl = req.getContextPath() + "/teacher?page=score&classId=" + classId + "&moduleId=" + moduleId + "&successMessage=" + encodedMessage;
            
            resp.sendRedirect(redirectUrl);
        } catch (NumberFormatException e) {
            req.setAttribute("error", "Định dạng điểm không hợp lệ");
            req.getRequestDispatcher("/WEB-INF/view/teacher/score.jsp").forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("error", "Có lỗi xảy ra: " + e.getMessage());
            req.getRequestDispatcher("/WEB-INF/view/teacher/score.jsp").forward(req, resp);
        }
    }

    private void saveStudentAssessment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String classIdStr = req.getParameter("classId");
        String moduleIdStr = req.getParameter("moduleId");
        String studentId = req.getParameter("studentId");
        String averageScoreStr = req.getParameter("averageScore");
        
        if (classIdStr == null || moduleIdStr == null || studentId == null || averageScoreStr == null || 
            classIdStr.isEmpty() || moduleIdStr.isEmpty() || studentId.isEmpty() || averageScoreStr.isEmpty()) {
            
            req.setAttribute("error", "Thông tin đánh giá không hợp lệ!");
            req.getRequestDispatcher("/WEB-INF/view/teacher/main.jsp").forward(req, resp);
            return;
        }
        
        try {
            int classId = Integer.parseInt(classIdStr);
            int moduleId = Integer.parseInt(moduleIdStr);
            double averageScore = Double.parseDouble(averageScoreStr);
            boolean status = averageScore >= 7.5;
            
            // Create a list with just this one assessment
            List<Assessment> assessments = new ArrayList<>();
            Assessment assessment = new Assessment(0, averageScore, studentId, moduleId, status);
            assessments.add(assessment);
            
            // Delete existing assessment for this student in this module
            assessmentService.deleteStudentAssessmentByModuleId(studentId, moduleId);
            
            // Save the new assessment
            assessmentService.saveAssessments(assessments);
            req.getSession().setAttribute("successMessage", "Lưu đánh giá thành công!");
            
        } catch (NumberFormatException e) {
            System.out.println("TeacherController - Invalid number format: " + e.getMessage());
            req.setAttribute("error", "Định dạng số không hợp lệ");
            req.getRequestDispatcher("/WEB-INF/view/teacher/main.jsp").forward(req, resp);
            return;
        }
        
        resp.sendRedirect("/teacher?page=assessment&classId=" + classIdStr + "&moduleId=" + moduleIdStr);
    }
}