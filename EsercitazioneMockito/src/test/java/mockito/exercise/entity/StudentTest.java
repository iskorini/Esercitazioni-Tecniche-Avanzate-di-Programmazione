package mockito.exercise.entity;/**
 * Created by federicoschipani on 19/10/2016.
 */

import mockito.exercise.Exception.CourseRequestException;
import mockito.exercise.Exception.NoTutorException;
import mockito.exercise.Exception.TutorRequestException;
import mockito.exercise.Interface.DataRetriever;
import mockito.exercise.Interface.MailSender;
import org.junit.Test;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class StudentTest {


    private Student student;

    @Mock private DataRetriever dataRetriever;
    @Mock private MailSender mailSender;



    private Student createTestStudent(String id, String name, String mail, String surname) {
        Student student =
                new Student(id, name, surname, mail, dataRetriever, mailSender);
        return student;
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        student = createTestStudent("ID1", "Gilberto", "aaaa@gmail.com", "Templari");
    }

    @Test(expected = NoTutorException.class)
    public void testSendMailToTutorIfTutorDoesNotExists() throws Exception {
        student.sendMailToTutor("FOO");
    }

    @Test
    public void testSendMailToTutor() throws Exception {
        String object = "object_test";
        String teacherMail = "aaa";
        String id = "teacher1";
        Student student1 = spy(student);
        doReturn(id).when(student1).getTutorID();
        Teacher teacher = new Teacher(id, "name", "surname", teacherMail, dataRetriever, mailSender);
        when(dataRetriever.getTeacherByID(id)).thenReturn(Optional.of(teacher));
        student1.sendMailToTutor(object);
        verify(mailSender, times(1)).sendMail(student.getMail(), teacherMail, object);


    }

    @Test(expected = CourseRequestException.class)
    public void testCourseRequestErrorAlreadyExists() throws Exception {
        String courseID = "Course1";
        Optional<CourseRequest> courseRequestOptional = Optional.of(new CourseRequest(courseID, this.student.getID()));
        when(dataRetriever.getCourseRequestByStudentID(student.getID())).thenReturn(courseRequestOptional);
        when(dataRetriever.getCourseByID(courseID)).
                thenReturn(Optional.of(new Course(courseID,"name","mail", "teacher", dataRetriever, mailSender)));
        student.doCourseRequest(courseID);
    }

    @Test
    public void testCourseRequestErrorCourseNotExists() throws Exception {
        String courseID = "course1";
        when(dataRetriever.getCourseByID(courseID)).thenReturn(Optional.empty());
        when(dataRetriever.getCourseRequestByStudentID(student.getID())).thenReturn(Optional.empty());
        student.doCourseRequest(courseID);
        verify(dataRetriever, times(0)).persistCourseRequest(any());
    }

    @Test
    public void testCourseRequestBase() throws Exception {
        String courseID = "Course1";
        when(dataRetriever.getCourseRequestByStudentID(student.getID())).thenReturn(Optional.empty());
        when(dataRetriever.getCourseByID(courseID)).
                thenReturn(Optional.of(new Course(courseID,"name","mail", "teacher", dataRetriever, mailSender)));

        student.doCourseRequest(courseID);
        verify(dataRetriever, times(1)).persistCourseRequest(new CourseRequest(student.getID(), courseID));

    }

    @Test
    public void testTutorRequestBase() throws Exception {
        when(dataRetriever.getTutorRequestByStudentID(student.getID())).thenReturn(Optional.empty());
        when(dataRetriever.getTeacherByID("teacher1")).
                thenReturn(Optional.of(new Teacher("teacher1", "Pierluigi", "Crescenzi", "pi@lu.com", dataRetriever, mailSender)));

        student.doTutorRequest("teacher1");
        verify(dataRetriever, times(1)).getTutorRequestByStudentID(student.getID());
        verify(dataRetriever, times(1)).getTeacherByID("teacher1");
        verify(dataRetriever, times(1)).persistTutorRequest(new TutorRequest(student.getID(), "teacher1"));
    }


    @Test(expected = TutorRequestException.class)
    public void testTutorRequestAlreadyExist() throws Exception {
        when(dataRetriever.getTutorRequestByStudentID(student.getID())).
                thenReturn(Optional.ofNullable(new TutorRequest("foo", "bar")));
        student.doTutorRequest("teacher1");
    }

    @Test
    public void getIDTest() throws Exception {

        assertEquals(student.getID(), "ID1");

    }

    @Test
    public void getNameTest() {

        assertEquals(student.getName(), "Gilberto");
    }

    @Test
    public void getSurnameTest() throws Exception {

        assertEquals(student.getSurname(), "Templari");

    }

    @Test
    public void getMailTest() throws Exception {

        assertEquals(student.getMail(), "aaaa@gmail.com");
    }

}