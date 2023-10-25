import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Controller {
    private List<Location> cities = new ArrayList<Location>();

    public Controller() {
        try {
            Scanner reader = new Scanner(new File("src/data.txt"));
            while(reader.hasNext()) {
                // Get name line
                String name = reader.nextLine();
                // Get arrival line
                int numArrivals = Integer.parseInt(reader.nextLine());
                Train[] arrivals = populateTrainData(reader, numArrivals, true, name);
                // Get departure line
                int numDepartures = Integer.parseInt(reader.nextLine());
                Train[] departures = populateTrainData(reader, numDepartures, false, name);
                Schedule schedule = new Schedule(arrivals, departures);
                cities.add(new Location(name, schedule));
            }
        } catch(FileNotFoundException ex) {
            System.out.println(ex);
        }
    }

    /***
     *
     * @param reader input scanner for the file
     * @param number number of trains for the array
     * @param arrivals true if creating array of arriving trains, false otherwise
     * @param city if arrivals, city is the destination, if departures, city is origin
     * @return
     */
    private Train[] populateTrainData(Scanner reader, int number, boolean arrivals, String city) {
        Train[] trains = new Train[number];
        for (int i = 0; i < number; i++){
            // Get departures
            int id = Integer.parseInt(reader.nextLine());
            String time = reader.nextLine();
            int scheduledHour = Integer.parseInt(time.substring(0,2));
            int scheduledMin = Integer.parseInt(time.substring(3));
            time = reader.nextLine();
            int actualHour = Integer.parseInt(time.substring(0,2));;
            int actualMin = Integer.parseInt(time.substring(3));;
            String nextCity = reader.nextLine();
            if (arrivals) {
                trains[i] = new Train(nextCity, city, id, scheduledHour, scheduledMin, actualHour, actualMin);
            } else {
                trains[i] = new Train(city, nextCity, id, scheduledHour, scheduledMin, actualHour, actualMin);
            }
        }
        return trains;
    }

    private boolean isValidCity(String city) {
        return cities.contains(new Location(city, null));
    }

    private Schedule getSchedule(String city) {
        Location loc = cities.get(cities.indexOf(new Location(city, null)));
        return loc.getSchedule();
    }

    private List<String> getCities() {
        return cities.stream().map(element -> element.getCity()).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String city;
        Controller admin = new Controller();

        System.out.println("Welcome to the CIT 5910 train schedule manager.");
        System.out.println("Schedules for the following cities are available:");
        admin.getCities().stream().forEach(System.out::println);
        System.out.print("Please enter a city or CTRL + D to exit: ");
        while (input.hasNext()) {
            city = input.nextLine();
            if (!admin.isValidCity(city)) {
                System.out.println("Invalid city.");
                System.out.print("Please enter a city or CTRL + D to exit: ");
                continue;
            }
            System.out.println(admin.getSchedule(city));
            System.out.print("Please enter a city or CTRL + D to exit: ");
        }
    }
}
