package mockito.exercise.entity;

/**
 * Created by federicoschipani on 19/10/2016.
 */
public class CourseAttendance {

    private String IDStudent;
    private String IDCourse;

    public CourseAttendance(String IDStudent, String IDCourse) {
        this.IDStudent = IDStudent;
        this.IDCourse = IDCourse;
    }

    public String getIDStudent() {
        return IDStudent;
    }

    public String getIDCourse() {
        return IDCourse;
    }
}
