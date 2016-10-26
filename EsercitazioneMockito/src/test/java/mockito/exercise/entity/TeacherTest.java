package mockito.exercise.entity;/**
 * Created by federicoschipani on 19/10/2016.
 */

import mockito.exercise.Exception.TooMuchTutoredStudentException;
import mockito.exercise.Interface.DataRetriever;
import mockito.exercise.Interface.MailSender;
import org.junit.Test;
import org.junit.Before;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TeacherTest {


    Teacher teacher;
    @Mock private DataRetriever dataRetriever;
    @Mock private MailSender mailSender;



    private Teacher createTestTeacher(String mail, String surname, String name, String id) {
        return new Teacher(id, name, surname, mail, dataRetriever, mailSender);
    }


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        teacher = createTestTeacher("asd@gmail.com", "surname", "name", "teacher1");
    }

    @Test
    public void testSendMailToThreeTutoredStudent() throws Exception {

        generateTutorRequestToHandle("ID1", "teacher1", "niki", "giusino", "niki@giusino.it");
        verify(mailSender, times(1)).sendMail(matches(teacher.getMail()),matches("niki@giusino.it"), anyString() );

        generateTutorRequestToHandle("ID2", "teacher1", "gabriele", "paolini", "gabriele@paolini.it");
        verify(mailSender, times(1)).sendMail(matches(teacher.getMail()),matches("gabriele@paolini.it"), anyString() );

        generateTutorRequestToHandle("ID3", "teacher1", "enrico", "papi", "enrico@papi.it");
        verify(mailSender, times(1)).sendMail(matches(teacher.getMail()),matches("enrico@papi.it"), anyString() );

        String object = "mail to 3 students";

        teacher.sendMailToAllTutoredStudent(object);

        ArgumentCaptor<String> mailCaptor = ArgumentCaptor.forClass(String.class);

        ArgumentCaptor<String> teacherMailCaptor = ArgumentCaptor.forClass(String.class);

        ArgumentCaptor<String> objectMailCaptor = ArgumentCaptor.forClass(String.class);

        verify(mailSender, times(6)).sendMail(teacherMailCaptor.capture(), mailCaptor.capture(), objectMailCaptor.capture());

        assertEquals(mailCaptor.getAllValues().get(0), "niki@giusino.it");
        assertEquals(mailCaptor.getAllValues().get(1), "gabriele@paolini.it");
        assertEquals(mailCaptor.getAllValues().get(2), "enrico@papi.it");
        assertEquals(teacherMailCaptor.getAllValues().get(0), teacher.getMail());
        assertEquals(objectMailCaptor.getAllValues().get(0), object);

    }

    @Test
    public void testSendMailToAllTutoredStudent() throws Exception {

        this.generateTutorRequestToHandle("ID1", "teacher1", "niki", "giusino", "niki@giusino.it");
        teacher.sendMailToAllTutoredStudent("Mail to all my student");
        verify(mailSender, times(1)).
                sendMail(matches(teacher.getMail()),matches("niki@giusino.it"), matches("Mail to all my student") );

    }

    @Test
    public void testSendMailToZeroTutoredStudent() throws Exception {

        teacher.sendMailToAllTutoredStudent("Mail to all my student");
        verify(mailSender, times(0)).sendMail(anyString(), anyString(), anyString());

    }

    @Test(expected = TooMuchTutoredStudentException.class)
    public void testTutorRequestTooMuch() throws Exception {

        generateTutorRequestToHandle("ID1", "teacher1", "niki", "giusino", "niki@giusino.it");
        verify(mailSender, times(1)).sendMail(matches(teacher.getMail()),matches("niki@giusino.it"), anyString() );

        generateTutorRequestToHandle("ID2", "teacher1", "gabriele", "paolini", "gabriele@paolini.it");
        verify(mailSender, times(1)).sendMail(matches(teacher.getMail()),matches("gabriele@paolini.it"), anyString() );


        generateTutorRequestToHandle("ID3", "teacher1", "enrico", "papi", "enrico@papi.it");
        verify(mailSender, times(1)).sendMail(matches(teacher.getMail()),matches("enrico@papi.it"), anyString() );


        generateTutorRequestToHandle("ID4", "teacher1", "santa", "claus", "santa@claus.it");
        verify(mailSender, times(1)).sendMail(matches(teacher.getMail()),matches("santa@claus.it"), anyString() );
    }

    private void generateTutorRequestToHandle(String idStudent, String idTeacher, String nameStudent, String surnameStudent, String mailStudent) throws TooMuchTutoredStudentException {
        TutorRequest tutorRequest = new TutorRequest(idStudent, idTeacher);
        when(dataRetriever.getStudentByID(idStudent)).
                thenReturn(Optional.of(new Student(idStudent, nameStudent, surnameStudent, mailStudent)));
        teacher.handleTutorRequest(tutorRequest);
    }

    @Test
    public void testTutorRequestBase() throws Exception {
        generateTutorRequestToHandle("ID1", "teacher1", "niki", "giusino", "niki@giusino.it");
        verify(mailSender, times(1)).sendMail(matches(teacher.getMail()),matches("niki@giusino.it"), anyString() );

    }

    @Test
    public void getIDTest() {
        assertEquals(teacher.getID(), "teacher1");
    }

    @Test
    public void getNameTest() throws Exception {
        assertEquals(teacher.getName(), "name");

    }

    @Test
    public void getSurnameTest() throws Exception {
        assertEquals(teacher.getSurname(), "surname");
    }

    @Test
    public void getMailTest() throws Exception {
        assertEquals(teacher.getMail(), "asd@gmail.com");

    }
}
