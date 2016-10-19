package mockito.exercise.entity;

/**
 * Created by federicoschipani on 19/10/2016.
 */
public class Teacher {


    private String ID;
    private String name;
    private String surname;
    private String mail;

    public Teacher(String id, String name, String surname, String mail) {
        ID = id;
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
}
