package model.controlStaff.controlStaffIsAssignedTo;

public class ControlStaffIsAssignedTo {
    private int id;
    private int controlStaff;
    private int areaPeriod;

    public ControlStaffIsAssignedTo() {

    }

    public int getAreaPeriod() {
        return areaPeriod;
    }

    public void setAreaPeriod(int areaPeriod) {
        this.areaPeriod = areaPeriod;
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
        return "ControlStaffIsAssignedTo{" +
                "id=" + id +
                ", controlStaff=" + controlStaff +
                ", areaPeriod=" + areaPeriod +
                '}';
    }
}
