package boilerhungry.backend;

public enum DayOfWeek {

    MONDAY("Monday"), TUESDAY("Tuesday"), WEDNESDAY("Wednesday"), THURSDAY("Thursday"), FRIDAY("Friday"),
    SATURDAY("Saturday"), SUNDAY("Sunday");

    private final String nameOfDay;

    DayOfWeek(String nameOfDay) {
        this.nameOfDay = nameOfDay;
    }

    @Override
    public String toString() {
        return nameOfDay;
    }

}
