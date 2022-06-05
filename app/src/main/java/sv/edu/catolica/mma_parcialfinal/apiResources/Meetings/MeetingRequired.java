package sv.edu.catolica.mma_parcialfinal.apiResources.Meetings;

import java.io.Serializable;

public class MeetingRequired implements Serializable {
    private int course_id;
    private String secret_code;
    private String finish_time;

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getSecret_code() {
        return secret_code;
    }

    public void setSecret_code(String secret_code) {
        this.secret_code = secret_code;
    }

    public String getFinish_time() {
        return finish_time;
    }

    public void setFinish_time(String finish_time) {
        this.finish_time = finish_time;
    }
}
