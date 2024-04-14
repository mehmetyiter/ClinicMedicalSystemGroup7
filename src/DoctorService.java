import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DoctorService {
    private static List<Doctor> doctors = new ArrayList<>();

    static {
        loadDoctorsFromFile(); // Load doctor data on startup
    }
    public static List<Doctor> getDoctors() {
        return new ArrayList<>(doctors);  // Return a copy of the list to prevent external modifications
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

    public static void modifyDoctor(Scanner scanner) {
        System.out.println("Enter doctor's last name to modify:");
        String lastName = scanner.nextLine();
        for (Doctor doctor : doctors) {
            if (doctor.getLastName().equals(lastName)) {
                System.out.println("Modifying details for: Dr. " + doctor.getFirstName() + " " + doctor.getLastName());
                System.out.println("Enter new specialty (current: " + doctor.getSpecialty() + "):");
                doctor.setSpecialty(scanner.nextLine());
                saveDoctorsToFile();
                System.out.println("Details updated successfully!");
                return;
            }
        }
        System.out.println("Doctor not found with last name: " + lastName);
    }

    public static void deleteDoctor(Scanner scanner) {
        System.out.println("Enter doctor's last name to delete:");
        String lastName = scanner.nextLine();
        boolean found = false;
        for (Doctor doctor : doctors) {
            if (doctor.getLastName().equals(lastName)) {
                doctors.remove(doctor);
                saveDoctorsToFile();
                System.out.println("Doctor deleted successfully!");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Doctor not found with last name: " + lastName);
        }
    }

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

    private static void loadDoctorsFromFile() {
        System.out.println("Loading doctors from file...");
        File file = new File("doctors.txt");
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                int count = 0;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",");
                    if (data.length == 5) {
                        try {
                            String firstName = data[0].trim();
                            String lastName = data[1].trim();
                            String birthDate = data[2].trim();
                            String employedDate = data[3].trim();
                            String specialty = data[4].trim();
                            
                            // Validate date formats or other data criteria here if needed
                            Doctor doctor = new Doctor(firstName, lastName, birthDate, employedDate, specialty);
                            doctors.add(doctor);
                            count++;
                        } catch (Exception e) {
                            System.out.println("Error parsing doctor data: " + e.getMessage() + " in line: " + line);
                        }
                    } else {
                        System.out.println("Skipping improperly formatted line: " + line);
                    }
                }
                System.out.println(count + " doctors loaded.");
            } catch (IOException e) {
                System.out.println("Error loading doctors from file: " + e.getMessage());
            }
        } else {
            System.out.println("No file found at the specified path: " + file.getAbsolutePath());
        }
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
}
