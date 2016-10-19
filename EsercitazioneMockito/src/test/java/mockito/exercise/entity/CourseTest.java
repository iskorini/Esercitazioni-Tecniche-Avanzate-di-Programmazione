package mockito.exercise.entity;/**
 * Created by federicoschipani on 19/10/2016.
 */

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;

public class CourseTest {


    private Course course;


    private Course getTestCourse(String id, String name, String mail, String idTeacher) {
        return new Course(id, name, mail, idTeacher);
    }

    @Before
    public void setUp() {
        course = getTestCourse("C1", "COURSE1", "teacher@mail.com", "teacher4");
    }

    @Test
    public void getTeacherIDTest() throws Exception {
        assertEquals("teacher4", course.getIDTeacher());

    }

    @Test
    public void getMailTest() throws Exception {
        assertEquals("teacher@mail.com", course.getMail());

    }

    @Test
    public void getNameTest() throws Exception {
        assertEquals(course.getName(), "COURSE1");

    }

    @Test
    public void getIDTest() throws Exception {
        assertEquals(course.getID(), "C1");

    }
}
