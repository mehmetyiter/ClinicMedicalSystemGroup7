public class Patient {
    private String firstName;
    private String lastName;
    private String birthDate;
    private String employer;
    private String insurance;

    public Patient(String firstName, String lastName, String birthDate, String employer, String insurance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.employer = employer;
        this.insurance = insurance;
    }

    // Getters
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getBirthDate() { return birthDate; }
    public String getEmployer() { return employer; }
    public String getInsurance() { return insurance; }

    // Setters
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setBirthDate(String birthDate) { this.birthDate = birthDate; }
    public void setEmployer(String employer) { this.employer = employer; }
    public void setInsurance(String insurance) { this.insurance = insurance; }

    @Override
    public String toString() {
        return "Patient{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", employer='" + employer + '\'' +
                ", insurance='" + insurance + '\'' +
                '}';
    }
}
