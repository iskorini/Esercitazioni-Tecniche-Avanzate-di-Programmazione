package mockito.exercise.entity;

import java.util.Objects;

/**
 * Created by federicoschipani on 19/10/2016.
 */
public class CourseRequest {


    private final String courseID;
    private final String studentID;

    public CourseRequest(String studentID, String courseID) {
        this.studentID = studentID;
        this.courseID = courseID;
    }

    public String getCourseID() {
        return courseID;
    }

    public String getStudentID() {
        return studentID;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof CourseRequest) {
            return this.getCourseID().equals(((CourseRequest) o).getCourseID()) &&
                    this.getStudentID().equals(((CourseRequest) o).getStudentID());
        } else {
            return false;
        }
    }
}
