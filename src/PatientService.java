import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PatientService {
    private static List<Patient> patients = new ArrayList<>();

    static {
        loadPatientsFromFile(); // Load patient data on startup
    }

    public static void registerNewPatient(Scanner scanner) {
        System.out.println("Enter patient's first name:");
        String firstName = scanner.nextLine().trim();
        System.out.println("Enter patient's last name:");
        String lastName = scanner.nextLine().trim();
        System.out.println("Enter patient's birth date (YYYY-MM-DD):");
        String birthDate = scanner.nextLine().trim();
        System.out.println("Enter patient's employer:");
        String employer = scanner.nextLine().trim();
        System.out.println("Enter patient's insurance company:");
        String insurance = scanner.nextLine().trim();

        Patient patient = new Patient(firstName, lastName, birthDate, employer, insurance);
        patients.add(patient);
        savePatientsToFile();
        
        System.out.println("Patient registered successfully!");
    }

    public static void displayAllPatients() {
        if (patients.isEmpty()) {
            System.out.println("No patients are registered.");
        } else {
            System.out.println("Registered Patients:");
            for (Patient patient : patients) {
                System.out.println("--------------------------------------");
                System.out.printf("Name: %s %s\n", patient.getFirstName(), patient.getLastName());
                System.out.printf("Birth Date: %s\n", patient.getBirthDate());
                System.out.printf("Employer: %s\n", patient.getEmployer());
                System.out.printf("Insurance: %s\n", patient.getInsurance());
                System.out.println("--------------------------------------\n");
            }
        }
    }
    

    public static void modifyPatient(Scanner scanner) {
        System.out.println("Enter patient's last name to modify:");
        String lastName = scanner.nextLine();
        for (Patient patient : patients) {
            if (patient.getLastName().equals(lastName)) {
                System.out.println("Modifying details for: " + patient);
                System.out.println("Enter new employer (current: " + patient.getEmployer() + "):");
                patient.setEmployer(scanner.nextLine());
                System.out.println("Enter new insurance company (current: " + patient.getInsurance() + "):");
                patient.setInsurance(scanner.nextLine());
                savePatientsToFile();
                System.out.println("Details updated successfully!");
                return;
            }
        }
        System.out.println("Patient not found!");
    }

    public static void deletePatient(Scanner scanner) {
        System.out.println("Enter patient's last name to delete:");
        String lastName = scanner.nextLine();
        boolean found = false;
        for (int i = 0; i < patients.size(); i++) {
            if (patients.get(i).getLastName().equals(lastName)) {
                patients.remove(i);
                found = true;
                savePatientsToFile();
                System.out.println("Patient deleted successfully!");
                break;
            }
        }
        if (!found) {
            System.out.println("Patient not found!");
        }
    }

    private static void savePatientsToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("patients.txt", false))) {
            for (Patient patient : patients) {
                writer.println(patient.getFirstName() + "," +
                               patient.getLastName() + "," +
                               patient.getBirthDate() + "," +
                               patient.getEmployer() + "," +
                               patient.getInsurance());
            }
            System.out.println("Patients saved to file successfully!");
        } catch (IOException e) {
            System.out.println("Error saving patients to file: " + e.getMessage());
        }
    }

    private static void loadPatientsFromFile() {
        System.out.println("Loading patients from file...");
        File file = new File("D:\\FSD12\\Programing 2\\Project\\ClinicMedicalSystemGroup7\\ClinicMedicalSystemGroup7\\patients.txt");
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                int count = 0;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",");
                    if (data.length == 5) {
                        Patient patient = new Patient(data[0], data[1], data[2], data[3], data[4]);
                        patients.add(patient);
                        count++;
                    }
                }
                System.out.println(count + " patients loaded.");
            } catch (IOException e) {
                System.out.println("Error loading patients from file: " + e.getMessage());
            }
        } else {
            System.out.println("No file found at specified path.");
        }
    }
}
