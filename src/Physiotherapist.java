import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Physiotherapist extends User {
    private final int id;
    private final String expertise;
    private final List<String> treatments;
    private final Map<String, String> availability;

    public Physiotherapist(int id, String name, String address, String phone, String expertise) {
        super(name, address, phone);
        this.id = id;
        this.expertise = expertise.substring(0, 1).toUpperCase() + expertise.substring(1);
        this.treatments = new ArrayList<>();
        this.availability = new HashMap<>();
    }

    public static LocalDate getNextWeekday(LocalDate startDate, DayOfWeek targetDay) {
        LocalDate nextDate = startDate;
        while (nextDate.getDayOfWeek() != targetDay) {
            nextDate = nextDate.plusDays(1);
        }
        return nextDate;
    }

    public int getId() {
        return id;
    }

    public String getExpertise() {
        return expertise;
    }

    public List<String> getTreatments() {
        return treatments;
    }

    public void addTreatment(String treatment) {
        treatments.add(treatment.substring(0, 1).toUpperCase() + treatment.substring(1));
    }

    public Map<String, String> getAvailability() {
        return availability;
    }

    public void addAvailability(String day, String timeSlot) {
        LocalDate currentDate = LocalDate.now();
        DayOfWeek targetDay = Physiotherapist.getDayOfWeekFromString(day);
        if (targetDay == null) {
            System.out.println("Invalid day input..!");
            return;
        }

        // Get the date for the next occurrence of the target day
        LocalDate nextDay = getNextWeekday(currentDate, targetDay);

        // Loop over the next 4 weeks to add the availability
        for (int i = 0; i < 4; i++) {
            LocalDate weekDate = nextDay.plusWeeks(i);
            availability.put(String.valueOf(weekDate), timeSlot);
        }
    }

    public static DayOfWeek getDayOfWeekFromString(String dayOfWeek) {
        return switch (dayOfWeek.toLowerCase()) {
            case "monday" -> DayOfWeek.MONDAY;
            case "tuesday" -> DayOfWeek.TUESDAY;
            case "wednesday" -> DayOfWeek.WEDNESDAY;
            case "thursday" -> DayOfWeek.THURSDAY;
            case "friday" -> DayOfWeek.FRIDAY;
            case "saturday" -> DayOfWeek.SATURDAY;
            case "sunday" -> DayOfWeek.SUNDAY;
            default -> null;
        };
    }

    @Override
    public String toString() {
        return "Physiotherapist ID: " + id + ", " + super.toString() +
                ", Expertise: " + expertise + ", Treatments: " + treatments +
                ", Availability: " + availability;
    }
}