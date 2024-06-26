public class Doctor {
    // Fields to store doctor details
    private String firstName;
    private String lastName;
    private String birthDate;
    private String employedDate;
    private String specialty;

    // Constructor to initialize doctor details
    public Doctor(String firstName, String lastName, String birthDate, String employedDate, String specialty) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.employedDate = employedDate;
        this.specialty = specialty;
    }

    // Getter methods to access doctor details
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getFullName() { return "Dr. " + firstName + " " + lastName; } // Returns full name with title
    public String getBirthDate() { return birthDate; }
    public String getEmployedDate() { return employedDate; }
    public String getSpecialty() { return specialty; }

    // Setter methods to modify doctor details
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setBirthDate(String birthDate) { this.birthDate = birthDate; }
    public void setEmployedDate(String employedDate) { this.employedDate = employedDate; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }

    // Override toString() method to provide a string representation of the Doctor object
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
