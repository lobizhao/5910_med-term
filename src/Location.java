public class Location {
    private String city;
    private Schedule schedule;

    public Location(String city, Schedule schedule) {
        this.city = city;
        this.schedule = schedule;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public String getCity() {
        return city;
    }

    @Override
    public boolean equals(Object toCompare) {
        return (toCompare instanceof Location && this.city.equals(((Location) toCompare).city));
    }
}
