public class Patient extends User {
    private int id;

    public Patient(int id, String name, String address, String phone) {
        super(name, address, phone);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Patient ID: " + id + ", " + super.toString();
    }
}