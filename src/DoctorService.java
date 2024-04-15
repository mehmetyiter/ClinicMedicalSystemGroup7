import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DoctorService {
    // List to store doctors
    private static List<Doctor> doctors = new ArrayList<>();

    // Static block to load doctor data on startup
    static {
        loadDoctorsFromFile();
    }

    // Method to return a copy of the list of doctors to prevent external
    // modifications
    public static List<Doctor> getDoctors() {
        return new ArrayList<>(doctors);
    }

    // Method to register a new doctor
    public static void registerNewDoctor(Scanner scanner) {
        System.out.println("Enter doctor's first name:");
        String firstName = scanner.nextLine();
        System.out.println("Enter doctor's last name:");
        String lastName = scanner.nextLine();
        System.out.println("Enter doctor's birth date (MM/DD/YYYY):");
        String birthDate = scanner.nextLine();
        System.out.println("Enter doctor's employment date (YYYY-MM-DD):");
        String employedDate = scanner.nextLine();
        System.out.println("Enter doctor's specialty:");
        String specialty = scanner.nextLine();

        Doctor doctor = new Doctor(firstName, lastName, birthDate, employedDate, specialty);
        doctors.add(doctor);
        saveDoctorsToFile();
        System.out.println("Doctor registered successfully!");
    }

    // Method to modify a doctor's record
    public static void modifyDoctor(Scanner scanner) {
        System.out.println("Enter doctor's first name:");
        String firstName = scanner.nextLine();
        System.out.println("Enter doctor's last name:");
        String lastName = scanner.nextLine();    
        for (Doctor doctor : doctors) {
            if (doctor.getFirstName().equalsIgnoreCase(firstName) && doctor.getLastName().equalsIgnoreCase(lastName)) {
                System.out.println("Modifying details for: Dr. " + doctor.getFirstName() + " " + doctor.getLastName());
                System.out.println("Enter new first name (current: " + doctor.getFirstName() + "):");
                doctor.setFirstName(scanner.nextLine());
                System.out.println("Enter new last name (current: " + doctor.getLastName() + "):");
                doctor.setLastName(scanner.nextLine());
              
                System.out.println("Enter new employment date (YYYY-MM-DD) (current: " + doctor.getEmployedDate() + "):");
                doctor.setEmployedDate(scanner.nextLine());
                System.out.println("Enter new specialty (current: " + doctor.getSpecialty() + "):");
                doctor.setSpecialty(scanner.nextLine());
                saveDoctorsToFile();
                System.out.println("Details updated successfully!");
                return;
            }
        }
        System.out.println("Doctor not found with last name: " + lastName);
    }

    // Method to delete a doctor's record
    public static void deleteDoctor(Scanner scanner) {
    System.out.println("Enter doctor's first name:");
    String firstName = scanner.nextLine();
    System.out.println("Enter doctor's last name:");
    String lastName = scanner.nextLine();
        boolean found = false;
        for (Doctor doctor : doctors) {
            if  (doctor.getFirstName().equals(firstName) && doctor.getLastName().equals(lastName)) {
                doctors.remove(doctor);
                saveDoctorsToFile();
                System.out.println("Doctor deleted successfully!");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Doctor not found with name: " + firstName + " " + lastName);
        }
    }

    // Method to display all doctors
    public static void displayAllDoctors() {
        if (doctors.isEmpty()) {
            System.out.println("No doctors are registered.");
        } else {
            System.out.println("Registered Doctors:");
            for (Doctor doctor : doctors) {
                System.out.println("--------------------------------------");
                System.out.printf("Doctor Name: Dr. %s %s\n", doctor.getFirstName(), doctor.getLastName());
                System.out.printf("Employed Date: %s\n", doctor.getEmployedDate());
                System.out.printf("Specialty: %s\n", doctor.getSpecialty());
                System.out.println("--------------------------------------\n");
            }
        }
    }

    // Method to load doctors from file
    private static void loadDoctorsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("doctors.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                Doctor doctor = new Doctor(data[0], data[1], data[2], data[3], data[4]);
                doctors.add(doctor);
            }
            System.out.println("Doctors loaded from file successfully!");
        } catch (IOException e) {
            System.out.println("Error loading doctors from file: " + e.getMessage());
        }
    }

    // Method to save doctors to file
    private static void saveDoctorsToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("doctors.txt", false))) {
            for (Doctor doctor : doctors) {
                // Write doctor details to file
                writer.println(doctor.getFirstName() + "," +
                        doctor.getLastName() + "," +
                        doctor.getBirthDate() + "," +
                        doctor.getEmployedDate() + "," +
                        doctor.getSpecialty());
            }
            System.out.println("Doctors saved to file successfully!");
        } catch (IOException e) {
            System.out.println("Error saving doctors to file: " + e.getMessage());
        }
    }
}
