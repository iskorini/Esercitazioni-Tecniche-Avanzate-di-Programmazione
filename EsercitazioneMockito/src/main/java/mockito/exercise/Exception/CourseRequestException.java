package mockito.exercise.Exception;

import mockito.exercise.entity.Course;

/**
 * Created by federicoschipani on 19/10/2016.
 */
public class CourseRequestException extends Exception {
    public CourseRequestException(String s) {
        super(s);
    }
    public CourseRequestException() {
        super();
    }
}
