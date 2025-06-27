package Entity;

public class Assessment {
    int assessmentId;
    Boolean averageId;
    String studentId;
    int moduleId;
    Boolean status;

    public Assessment(int assessmentId, Boolean averageId, String studentId, int moduleId, Boolean status) {
        this.assessmentId = assessmentId;
        this.averageId = averageId;
        this.studentId = studentId;
        this.moduleId = moduleId;
        this.status = status;
    }

    public int getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(int assessmentId) {
        this.assessmentId = assessmentId;
    }

    public Boolean getAverageId() {
        return averageId;
    }

    public void setAverageId(Boolean averageId) {
        this.averageId = averageId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public int getModuleId() {
        return moduleId;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
