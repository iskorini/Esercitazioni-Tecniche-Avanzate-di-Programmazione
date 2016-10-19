package mockito.exercise.entity;/**
 * Created by federicoschipani on 19/10/2016.
 */

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;

public class CourseRequestTest {

    private CourseRequest courseRequest;

    @Before
    public void setUp() {
        courseRequest = new CourseRequest("StudentID1", "CourseID");
    }

    @Test
    public void getCourseIDTest() throws Exception {
        assertEquals("CourseID", courseRequest.getCourseID());

    }

    @Test
    public void getStudentIDTest() throws Exception {
        assertEquals("StudentID1", courseRequest.getStudentID());

    }
}
