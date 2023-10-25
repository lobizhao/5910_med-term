public class Train {
    private String originCity;
    private String destinationCity;
    private int id;
    // Scheduled arrival/departure time
    private int scheduledHour;
    private int scheduledMinute;
    // Predicted arrival/departure time
    private int actualHour;
    private int actualMinute;


    public Train(String originCity, String destinationCity, int id, int scheduledHour, int scheduledMinute, int actualHour, int actualMinute) {
        this.originCity = originCity;
        this.destinationCity = destinationCity;
        this.id = id;
        this.scheduledHour = scheduledHour;
        this.scheduledMinute = scheduledMinute;
        this.actualHour = actualHour;
        this.actualMinute = actualMinute;
    }


    public boolean isDelayed() {
        int scheduTime = scheduledHour * 60 + scheduledMinute;
        int actualTime = actualHour * 60 + actualMinute;
        return actualTime > scheduTime;
    }

    @Override
    public String toString() {
        return String.format("Train Number:\t%d\tOrigin:\t%s\tDestination:\t%s\tScheduled:\t%d:%d\tActual:\t%d:%d\t",
                id, originCity, destinationCity, scheduledHour, scheduledMinute, actualHour, actualMinute);
    }
}
