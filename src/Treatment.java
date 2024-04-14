public class Treatment {
    private String doctorName;
    private String patientName;
    private String medication;
    private String description;

    public Treatment(String doctorName, String patientName, String medication, String description) {
        this.doctorName = doctorName;
        this.patientName = patientName;
        this.medication = medication;
        this.description = description;
    }

    // Getters
    public String getDoctorName() { return doctorName; }
    public String getPatientName() { return patientName; }
    public String getMedication() { return medication; }
    public String getDescription() { return description; }

    // Setters
    public void setDoctorName(String doctorName) { this.doctorName = doctorName; }
    public void setPatientName(String patientName) { this.patientName = patientName; }
    public void setMedication(String medication) { this.medication = medication; }
    public void setDescription(String description) { this.description = description; }

    @Override
    public String toString() {
        return "Treatment{" +
                "doctorName='" + doctorName + '\'' +
                ", patientName='" + patientName + '\'' +
                ", medication='" + medication + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
