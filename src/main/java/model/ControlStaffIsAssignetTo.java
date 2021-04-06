package model;

public class ControlStaffIsAssignetTo {
    private int id;
    private int controlStaff;

    public ControlStaffIsAssignetTo(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getControlStaff() {
        return controlStaff;
    }

    public void setControlStaff(int controlStaff) {
        this.controlStaff = controlStaff;
    }

    @Override
    public String toString() {
        return "ControlStaffIsAssignetTo{" +
                "id=" + id +
                ", controlStaff=" + controlStaff +
                '}';
    }
}
