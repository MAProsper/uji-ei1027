package app.model.period;

import java.time.LocalDateTime;

public class Period {
    public int id;
    public LocalDateTime start;
    public LocalDateTime finish;

    public Period() {

    }

    @Override
    public String toString() {
        return "Period{" +
                "id=" + id +
                ", start=" + start +
                ", finish=" + finish +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getFinish() {
        return finish;
    }

    public void setFinish(LocalDateTime finish) {
        this.finish = finish;
    }
}
