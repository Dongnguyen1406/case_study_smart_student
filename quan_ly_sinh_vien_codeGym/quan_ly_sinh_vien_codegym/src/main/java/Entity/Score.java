package Entity;

public class Score {
    int scoreId;
    int studentModuleId;
    Boolean quizScore;
    Boolean practiceScore;
    Boolean avergeScore;

    public Score(int scoreId, int studentModuleId, Boolean quizScore, Boolean practiceScore, Boolean avergeScore) {
        this.scoreId = scoreId;
        this.studentModuleId = studentModuleId;
        this.quizScore = quizScore;
        this.practiceScore = practiceScore;
        this.avergeScore = avergeScore;
    }

    public int getScoreId() {
        return scoreId;
    }

    public void setScoreId(int scoreId) {
        this.scoreId = scoreId;
    }

    public int getStudentModuleId() {
        return studentModuleId;
    }

    public void setStudentModuleId(int studentModuleId) {
        this.studentModuleId = studentModuleId;
    }

    public Boolean getQuizScore() {
        return quizScore;
    }

    public void setQuizScore(Boolean quizScore) {
        this.quizScore = quizScore;
    }

    public Boolean getPracticeScore() {
        return practiceScore;
    }

    public void setPracticeScore(Boolean practiceScore) {
        this.practiceScore = practiceScore;
    }

    public Boolean getAvergeScore() {
        return avergeScore;
    }

    public void setAvergeScore(Boolean avergeScore) {
        this.avergeScore = avergeScore;
    }
}
