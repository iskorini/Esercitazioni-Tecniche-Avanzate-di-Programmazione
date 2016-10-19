package mockito.exercise.entity;/**
 * Created by federicoschipani on 19/10/2016.
 */

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;

public class TeacherTest {


    Teacher teacher;


    private Teacher createTestTeacher(String mail, String surname, String name, String id) {
        return new Teacher(id, name, surname, mail);
    }


    @Before
    public void setUp() {
        teacher = createTestTeacher("asd@gmail.com", "uuu", "aaa", "teacher1");
    }

    @Test
    public void getIDTest() {
        assertEquals(teacher.getID(), "teacher1");
    }

    @Test
    public void getNameTest() throws Exception {
        assertEquals(teacher.getName(), "aaa");

    }

    @Test
    public void getSurnameTest() throws Exception {
        assertEquals(teacher.getSurname(), "uuu");
    }

    @Test
    public void getMailTest() throws Exception {
        assertEquals(teacher.getMail(), "asd@gmail.com");

    }
}
