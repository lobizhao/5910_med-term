public class Schedule {
    private Train[] arrivingTrains;
    private Train[] departingTrains;

    public Schedule(Train[] arrivals, Train[] departures) {
        this.arrivingTrains = arrivals;
        this.departingTrains = departures;
    }

    @Override
    public String toString() {
        String result = "Arriving trains:\n";
        for (int i = 0; i < arrivingTrains.length; i++) {
            result += arrivingTrains[i].toString();
            if (arrivingTrains[i].isDelayed()) {
                result += " DELAYED";
            }
            result += "\n";
        }
        result += "Departing trains:\n";
        for (int i = 0; i < departingTrains.length; i++) {
            result += departingTrains[i].toString();
            if (departingTrains[i].isDelayed()) {
                result += " DELAYED";
            }
            result += "\n";
        }
        return result;
    }
}
