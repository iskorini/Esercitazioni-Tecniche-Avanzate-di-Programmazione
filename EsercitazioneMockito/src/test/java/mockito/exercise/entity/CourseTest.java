package mockito.exercise.entity;/**
 * Created by federicoschipani on 19/10/2016.
 */

import mockito.exercise.Interface.DataRetriever;
import mockito.exercise.Interface.MailSender;
import org.junit.Test;
import org.junit.Before;
import org.mockito.*;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CourseTest {


    private Course course;
    @Mock
    DataRetriever dataRetriever;
    @Mock
    MailSender mailSender;

    private Course getTestCourse(String id, String name, String mail) {
        return new Course(id, name, mail, dataRetriever, mailSender);
    }
    

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        course = getTestCourse("C1", "COURSE1", "teacher@mail.com");
    }


    @Test
    public void testHandleCourseRequestBase() throws Exception {
        Student t = mock(Student.class);
        when(t.getID()).thenReturn("Student1");
        when(t.getMail()).thenReturn("student@mail.com");
        when(dataRetriever.getStudentByID("Student1")).thenReturn(Optional.of(t));
        CourseRequest cr = new CourseRequest("Student1", "COURSE1");
        course.handleCourseRequest(cr);
        verify(dataRetriever, times(1)).persistCourseAttendance(any(CourseAttendance.class));
        verify(mailSender, times(1)).sendMail(anyString(), eq("student@mail.com"), anyString());
    }

    @Test
    public void testSetNewTeacherSubstitute() throws Exception {
        Teacher t = mock(Teacher.class);
        course = spy(course);
        doReturn("ATeacherID").when(course).getIDTeacher();
        Teacher aTeacher = mock(Teacher.class);
        when(aTeacher.getMail()).thenReturn("aMail@mail.com");
        when(aTeacher.getID()).thenReturn("ATeacherID");
        when(dataRetriever.getTeacherByID("ATeacherID")).thenReturn(Optional.of(aTeacher));
        when(t.getID()).thenReturn("ID1");
        when(t.getMail()).thenReturn("ID1@ID1.com");
        when(dataRetriever.getTeacherByID("ID1")).thenReturn(Optional.of(t));
        course.setTeacher("ID1");
        verify(dataRetriever, times(2)).getTeacherByID(anyString());
        ArgumentCaptor<String> fromMail = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> toMail = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> objectCaptor = ArgumentCaptor.forClass(String.class);
        verify(mailSender, times(2)).sendMail(fromMail.capture(), toMail.capture(), objectCaptor.capture());
        assertEquals(fromMail.getAllValues().get(0), "teacher@mail.com");
        assertEquals(fromMail.getAllValues().get(1), "teacher@mail.com");
        assertEquals(toMail.getAllValues().get(0), "aMail@mail.com");
        assertEquals(toMail.getAllValues().get(1), "ID1@ID1.com");
        assertEquals(objectCaptor.getAllValues().get(0), "NEW TEACHER");
    }

    @Test
    public void testSetNewTeacher() throws Exception {
        Teacher t = mock(Teacher.class);
        when(t.getID()).thenReturn("ID1");
        when(t.getMail()).thenReturn("ID1@ID1.com");
        when(dataRetriever.getTeacherByID("ID1")).thenReturn(Optional.of(t));
        course.setTeacher("ID1");
        verify(dataRetriever, times(1)).getTeacherByID(anyString());
        verify(mailSender, times(1)).sendMail(eq("teacher@mail.com"), eq("ID1@ID1.com"), anyString());
    }

    @Test
    public void getTeacherIDTest() throws Exception {
        course = spy(this.course);
        doReturn("teacher4").when(course).getIDTeacher();
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
