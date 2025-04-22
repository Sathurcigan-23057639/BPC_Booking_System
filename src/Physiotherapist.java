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

    public void addAvailability(String day, String time) {
        availability.put(day, time);
    }

    public Map<String, String> getAvailability() {
        return availability;
    }

    @Override
    public String toString() {
        return "Physiotherapist ID: " + id + ", " + super.toString() +
                ", Expertise: " + expertise + ", Treatments: " + treatments +
                ", Availability: " + availability;
    }
}