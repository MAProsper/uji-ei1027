package app.validator.generic;

public enum ValidatorMessage {
    SHEDULE_ORDER("scheduleOrder", "La fecha de fin deber ser posterior a la de incio"),
    PERDIOD_ORDER("periodOrder", "La hora de fin deber ser posterior a la de incio");

    private final String machine;
    private final String human;

    ValidatorMessage(String machine, String human) {
        this.machine = machine;
        this.human = human;
    }

    public String getMachine() {
        return machine;
    }

    public String getHuman() {
        return human;
    }
}
