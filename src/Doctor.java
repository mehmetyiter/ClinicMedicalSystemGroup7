public class Doctor {
    private String firstName;
    private String lastName;
    private String birthDate;
    private String employedDate;
    private String specialty;

    public Doctor(String firstName, String lastName, String birthDate, String employedDate, String specialty) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.employedDate = employedDate;
        this.specialty = specialty;
    }

    // Getters
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getFullName() {return "Dr. " + firstName + " " + lastName;}
    public String getBirthDate() { return birthDate; }
    public String getEmployedDate() { return employedDate; }
    public String getSpecialty() { return specialty; }

    // Setters
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setBirthDate(String birthDate) { this.birthDate = birthDate; }
    public void setEmployedDate(String employedDate) { this.employedDate = employedDate; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }

    @Override
    public String toString() {
        return "Doctor{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", employedDate='" + employedDate + '\'' +
                ", specialty='" + specialty + '\'' +
                '}';
    }
}
