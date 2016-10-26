package mockito.exercise.entity;

import mockito.exercise.Exception.CourseRequestException;
import mockito.exercise.Exception.NoTutorException;
import mockito.exercise.Exception.TutorRequestException;
import mockito.exercise.Interface.DataRetriever;
import mockito.exercise.Interface.MailSender;
import org.apache.log4j.Logger;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Created by federicoschipani on 19/10/2016.
 */
public class Student {

    private final static Logger LOGGER = Logger.getLogger(Student.class);


    private String ID;
    private String name;
    private String surname;
    private String mail;
    private String tutorID;

    private DataRetriever dataRetriever;
    private MailSender mailSender;


    public Student(String id, String name, String surname, String mail, DataRetriever dataRetriever, MailSender mailSender) {
        this.ID = id;
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.dataRetriever = dataRetriever;
        this.mailSender = mailSender;
    }

    public Student(String ID, String name, String surname, String mail) {
        this.ID = ID;
        this.name = name;
        this.surname = surname;
        this.mail = mail;
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

    public String getTutorID() {
        return tutorID;
    }


    public void doTutorRequest(String teacherID) throws TutorRequestException {

        if (this.getTutorID() != null) {
            throw new TutorRequestException("Lo studente ha gia' un tutor");
        }

        try {
            dataRetriever.getTutorRequestByStudentID(this.getID()).get();
            throw new TutorRequestException("Lo studente ha una richiesta gia presente");
        } catch (NoSuchElementException e) {
            TutorRequest tr = new TutorRequest(this.ID, teacherID);
            Teacher teacher = dataRetriever.getTeacherByID(teacherID).get();
            mailSender.sendMail(this.getMail(), teacher.getMail(), "Tutor Request");
            dataRetriever.persistTutorRequest(tr);

        }

    }

    public void doCourseRequest(String courseID) throws CourseRequestException{
        Optional<Course> courseOptional;
        try {
            dataRetriever.getCourseRequestByStudentID(this.getID()).get();
            throw new CourseRequestException("Lo studente ha gia effettuato una richiesta per un corso");
        } catch (NoSuchElementException e) {
            courseOptional = dataRetriever.getCourseByID(courseID);
        }
        try {
            Course course = courseOptional.get();
            CourseRequest courseRequest = new CourseRequest(this.getID(), course.getID());
            dataRetriever.persistCourseRequest(courseRequest);
        } catch (NoSuchElementException e) {
            LOGGER.error("IL CORSO A CUI E' STATA FATTA RICHIESTA NON ESISTE", e);
        }
    }

    public void sendMailToTutor(String object) throws NoTutorException {
        if (this.getTutorID() != null) {
            String mail = dataRetriever.getTeacherByID(this.getTutorID()).get().getMail();
            mailSender.sendMail(this.getMail(), mail, object);
        } else{
            throw new NoTutorException("LO STUDENTE NON HA TUTOR");
        }
    }
}
