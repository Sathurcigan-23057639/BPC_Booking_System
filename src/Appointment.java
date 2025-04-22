public class Appointment {
    private final int id;
    private final Patient patient;
    private final Physiotherapist physiotherapist;
    private final String treatment;
    private final String expertise;
    private String time;
    private String status;

    public Appointment(int id, Patient patient, Physiotherapist physiotherapist, String expertise, String treatment, String time) {
        this.id = id;
        this.patient = patient;
        this.physiotherapist = physiotherapist;
        this.expertise = expertise;
        this.treatment = treatment;
        this.time = time;
        this.status = "Booked";
    }

    public int getId() {
        return id;
    }

    public Patient getPatient() {
        return patient;
    }

    public Physiotherapist getPhysiotherapist() {
        return physiotherapist;
    }

    public String getTreatment() {
        return treatment;
    }

    public String getExpertise() {
        return expertise;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Appointment ID: " + id + ", Patient: " + patient.getName() +
                ", Physiotherapist: " + physiotherapist.getName() +
                ", Expertise: " + getExpertise() + ", Treatment: " + treatment + ", Time: " + time +
                ", Status: " + status;
    }
}