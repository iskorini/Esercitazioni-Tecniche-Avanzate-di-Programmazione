package mockito.exercise.entity;

import mockito.exercise.Interface.DataRetriever;
import mockito.exercise.Interface.MailSender;

import java.util.NoSuchElementException;

/**
 * Created by federicoschipani on 19/10/2016.
 */
public class Course {

    private String ID;
    private String name;
    private String mail;
    private String IDTeacher;
    private DataRetriever dataRetriever;
    private MailSender mailSender;


    public Course(String id, String name, String mail, String idTeacher, DataRetriever dataRetriever, MailSender mailSender) {
        ID = id;
        this.name = name;
        this.mail = mail;
        IDTeacher = idTeacher;
        this.dataRetriever = dataRetriever;
        this.mailSender = mailSender;
    }

    public Course(String ID, String name, String mail, DataRetriever dataRetriever, MailSender mailSender) {
        this.ID = ID;
        this.name = name;
        this.mail = mail;
        this.dataRetriever = dataRetriever;
        this.mailSender = mailSender;
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getMail() {
        return mail;
    }

    public String getIDTeacher() {
        return IDTeacher;
    }

    public void setTeacher(String idTeacher) {
        Teacher teacher;
        try {
            teacher = dataRetriever.getTeacherByID(idTeacher).get();
            if (this.getIDTeacher() != null) {
                Teacher previousTeacher = dataRetriever.getTeacherByID(this.getIDTeacher()).get();
                mailSender.sendMail(this.getMail(), previousTeacher.getMail(), "NEW TEACHER" );
            }
            this.IDTeacher = teacher.getID();
            mailSender.sendMail(this.getMail(), teacher.getMail(), "NEW TEACHER");

        } catch (NoSuchElementException e) {

        }


    }

    public void handleCourseRequest(CourseRequest cr) throws NoSuchElementException{
        Student s = dataRetriever.getStudentByID(cr.getStudentID()).get();
        CourseAttendance courseAttendance = new CourseAttendance(s.getID(), this.getID());
        dataRetriever.persistCourseAttendance(courseAttendance);
        mailSender.sendMail(this.getMail(), s.getMail(), "COURSE APPROVED");
    }
}
