package mockito.exercise.entity;

/**
 * Created by federicoschipani on 19/10/2016.
 */
public class Course {

    private String ID;
    private String name;
    private String mail;
    private String IDTeacher;


    public Course(String id, String name, String mail, String idTeacher) {
        ID = id;
        this.name = name;
        this.mail = mail;
        IDTeacher = idTeacher;
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
}
