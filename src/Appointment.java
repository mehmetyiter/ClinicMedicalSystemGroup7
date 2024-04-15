public class Appointment {
    // Fields to store appointment details
    private String patientFirstName;
    private String patientLastName;
    private String doctorName;
    private String date;
    private String time;

    // Constructor to initialize appointment details
    public Appointment(String patientFirstName, String patientLastName, String doctorName, String date, String time) {
        this.patientFirstName = patientFirstName;
        this.patientLastName = patientLastName;
        this.doctorName = doctorName;
        this.date = date;
        this.time = time;
    }
    
    // Getter methods to access appointment details
    public String getPatientFirstName() { return patientFirstName; }
    public String getPatientLastName() { return patientLastName; }
    public String getPatientName() { return patientFirstName + " " + patientLastName; }
    public String getDoctorName() { return doctorName; }
    public String getDate() { return date; }
    public String getTime() { return time; }

    // Setter methods to modify appointment details
    public void setPatientFirstName(String patientFirstName) { this.patientFirstName = patientFirstName; }
    public void setPatientLastName(String patientLastName) { this.patientLastName = patientLastName; }
    public void setPatientName(String patientFirstName, String patientLastName) {
        this.patientFirstName = patientFirstName;
        this.patientLastName = patientLastName;
    }
    public void setDoctorName(String doctorName) { this.doctorName = doctorName; }
    public void setDate(String date) { this.date = date; }
    public void setTime(String time) { this.time = time; }

    
    // Override toString() method to provide a string representation of the Appointment object
    @Override
    public String toString() {
        return "Appointment{" +
                "patientFirstName='" + patientFirstName + '\'' +
                ", patientLastName='" + patientLastName + '\'' +
                ", doctorName='" + doctorName + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
