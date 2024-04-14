import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DoctorService {
    private static List<Doctor> doctors = new ArrayList<>();

    // Static initializer to load doctors from file when the class is loaded
    static {
        loadDoctorsFromFile();
    }

    public static void registerNewDoctor(Scanner scanner) {
        System.out.println("Enter doctor's first name:");
        String firstName = scanner.nextLine().trim();
        if (firstName.isEmpty()) {
            System.out.println("First name cannot be empty.");
            return;
        }
        
        System.out.println("Enter doctor's last name:");
        String lastName = scanner.nextLine().trim();
        if (lastName.isEmpty()) {
            System.out.println("Last name cannot be empty.");
            return;
        }
        
        System.out.println("Enter doctor's date of birth (YYYY-MM-DD):");
        String birthDate = scanner.nextLine().trim();
        if (!birthDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            return;
        }
        
        System.out.println("Enter doctor's employment date (YYYY-MM-DD):");
        String employedDate = scanner.nextLine().trim();
        if (!employedDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            return;
        }
        
        System.out.println("Enter doctor's specialty:");
        String specialty = scanner.nextLine().trim();
        if (specialty.isEmpty()) {
            System.out.println("Specialty cannot be empty.");
            return;
        }

        Doctor doctor = new Doctor(firstName, lastName, birthDate, employedDate, specialty);
        doctors.add(doctor);
        saveDoctorsToFile();
        System.out.println("Doctor registered successfully!");
    }

    public static void displayAllDoctors() {
        if (doctors.isEmpty()) {
            System.out.println("No doctors are registered.");
        } else {
            System.out.println("Registered Doctors:");
            for (Doctor doctor : doctors) {
                System.out.printf("Doctor Name: Dr. %s %s\n", doctor.getFirstName(), doctor.getLastName());
                System.out.printf("Employed Date: %s\n", doctor.getEmployedDate());
                System.out.printf("Specialty: %s\n\n", doctor.getSpecialty());
            }
        }
    }

    public static void modifyDoctor(Scanner scanner) {
        System.out.println("Enter doctor's last name to modify:");
        String lastName = scanner.nextLine();
        for (Doctor doctor : doctors) {
            if (doctor.getLastName().equals(lastName)) {
                System.out.println("Modifying details for: " + doctor);
                System.out.println("Enter new employed date (current: " + doctor.getEmployedDate() + "):");
                doctor.setEmployedDate(scanner.nextLine());
                System.out.println("Enter new specialty (current: " + doctor.getSpecialty() + "):");
                doctor.setSpecialty(scanner.nextLine());
                saveDoctorsToFile();
                System.out.println("Doctor's details updated successfully!");
                return;
            }
        }
        System.out.println("Doctor not found!");
    }

    public static void deleteDoctor(Scanner scanner) {
        System.out.println("Enter doctor's last name to delete:");
        String lastName = scanner.nextLine();
        for (int i = 0; i < doctors.size(); i++) {
            if (doctors.get(i).getLastName().equals(lastName)) {
                doctors.remove(i);
                saveDoctorsToFile();
                System.out.println("Doctor deleted successfully!");
                return;
            }
        }
        System.out.println("Doctor not found!");
    }

    private static void saveDoctorsToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("doctors.txt", false))) {
            for (Doctor doctor : doctors) {
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

    private static void loadDoctorsFromFile() {
        try (Scanner scanner = new Scanner(new File("doctors.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    Doctor doctor = new Doctor(parts[0], parts[1], parts[2], parts[3], parts[4]);
                    doctors.add(doctor);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No previous doctors file found. Starting fresh.");
        } catch (Exception e) {
            System.out.println("Error reading doctors from file: " + e.getMessage());
        }
    }
}
