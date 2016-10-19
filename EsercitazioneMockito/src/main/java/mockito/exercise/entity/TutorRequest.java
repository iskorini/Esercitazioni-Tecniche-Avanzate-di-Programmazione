package mockito.exercise.entity;

import java.util.Objects;

/**
 * Created by federicoschipani on 19/10/2016.
 */
public class TutorRequest {


    private String IDStudent;
    private String IDTeacher;

    public TutorRequest(String IDStudent, String IDTeacher) {
        this.IDStudent = IDStudent;
        this.IDTeacher = IDTeacher;
    }

    public String getIDStudent() {
        return IDStudent;
    }

    public String getIDTeacher() {
        return IDTeacher;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof TutorRequest) {
            TutorRequest tutorRequest = (TutorRequest)o;
            return tutorRequest.getIDTeacher().equals(this.getIDTeacher())
                    && tutorRequest.getIDStudent().equals(this.getIDStudent());
        } else{
            return false;
        }
    }
}
