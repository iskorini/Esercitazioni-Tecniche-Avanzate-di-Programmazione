package mockito.exercise.entity;/**
 * Created by federicoschipani on 19/10/2016.
 */

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;

public class CourseAttendanceTest {


    private CourseAttendance courseAttendance;

    @Before
    public void setUp() {
        courseAttendance = new CourseAttendance("Student1", "Course1");
    }

    @Test
    public void testGetStudentID() throws Exception {
        assertEquals("Student1", courseAttendance.getIDStudent());

    }

    @Test
    public void getCourseIDTest() throws Exception {
        assertEquals("Course1", courseAttendance.getIDCourse());

    }
}
