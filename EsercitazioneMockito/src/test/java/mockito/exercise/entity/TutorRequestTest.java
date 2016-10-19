package mockito.exercise.entity;/**
 * Created by federicoschipani on 19/10/2016.
 */

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;

public class TutorRequestTest {


    private TutorRequest tutorRequest;

    @Before
    public void setUp() {
        tutorRequest = new TutorRequest("Student1", "Teacher1");
    }

    @Test
    public void getTeacherIDTest() throws Exception {
        assertEquals(tutorRequest.getIDTeacher(), "Teacher1");

    }

    @Test
    public void getStudentIDTest() throws Exception {
        assertEquals(tutorRequest.getIDStudent(), "Student1");

    }
}
