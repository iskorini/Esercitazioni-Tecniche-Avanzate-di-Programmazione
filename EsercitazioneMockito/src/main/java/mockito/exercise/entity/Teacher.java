package mockito.exercise.entity;

import mockito.exercise.Exception.TooMuchTutoredStudentException;
import mockito.exercise.Interface.DataRetriever;
import mockito.exercise.Interface.MailSender;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by federicoschipani on 19/10/2016.
 */
public class Teacher {


    private String ID;
    private String name;
    private String surname;
    private String mail;
    private DataRetriever dataRetriever;
    private MailSender mailSender;
    private List<Student> tutoredStudents;


    public Teacher(String id, String name, String surname, String mail, DataRetriever dataRetriever, MailSender mailSender) {
        this.ID = id;
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.dataRetriever = dataRetriever;
        this.mailSender = mailSender;
        this.tutoredStudents = new ArrayList<>();
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getMail() {
        return mail;
    }

    public void handleTutorRequest(TutorRequest tutorRequest) throws TooMuchTutoredStudentException {
        if (tutoredStudents.size() < 3) {
            String studentID = tutorRequest.getIDStudent();
            Student student = dataRetriever.getStudentByID(studentID).get();
            String studentMail = student.getMail();
            mailSender.sendMail(this.getMail(), studentMail, "TutorRequest Approved");
            this.tutoredStudents.add(student);
        } else {
            throw new TooMuchTutoredStudentException();
        }

    }

    public void sendMailToAllTutoredStudent(String object) {

        for (Student student : tutoredStudents) {
            mailSender.sendMail(this.getMail(), student.getMail(), object);
        }

    }
}
