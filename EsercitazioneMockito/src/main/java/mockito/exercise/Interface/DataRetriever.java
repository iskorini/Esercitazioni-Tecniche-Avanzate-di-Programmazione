package mockito.exercise.Interface;

import mockito.exercise.entity.*;
import org.mockito.ArgumentMatcher;

import java.util.List;
import java.util.Optional;

/**
 * Created by federicoschipani on 19/10/2016.
 */
public interface DataRetriever {

    List<Course> getAllCourse();
    List<Student> getAllStudent();
    List<Teacher> getAllTeacher();


    Optional<TutorRequest> getTutorRequestByStudentID(String id);

    boolean persistTutorRequest(TutorRequest tr);

    Optional<Teacher> getTeacherByID(String teacherID);

    Optional<CourseRequest> getCourseRequestByStudentID(String studentID);

    boolean persistCourseRequest(CourseRequest courseRequest);

    Optional<Course> getCourseByID(String ID);
}
