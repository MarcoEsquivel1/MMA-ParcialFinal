package sv.edu.catolica.mma_parcialfinal.apiResources.Meetings;

import java.io.Serializable;

public class MeetingsRequired implements Serializable {
    private int course_id;

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }
}
