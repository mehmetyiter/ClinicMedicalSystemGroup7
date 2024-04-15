import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TreatmentService {
    private static List<Treatment> treatments = new ArrayList<>();

    // Load treatment data on startup
    static {
        loadTreatmentsFromFile();
    }

    // Method to create a new treatment
    public static void createNewTreatment(Scanner scanner) {
        // Input treatment details
        System.out.println("Creating a new treatment record:");
        System.out.println("Enter patient's name:");
        String patientName = scanner.nextLine().trim();
        if (patientName.isEmpty()) {
            System.out.println("Patient name cannot be empty.");
            return;
        }
        System.out.println("Enter doctor's name:");
        String doctorName = scanner.nextLine().trim();
        if (doctorName.isEmpty()) {
            System.out.println("Doctor name cannot be empty.");
            return;
        }
        System.out.println("Enter medication:");
        String medication = scanner.nextLine().trim();
        System.out.println("Enter treatment description:");
        String description = scanner.nextLine().trim();

        // Create and add new treatment
        Treatment treatment = new Treatment(doctorName, patientName, medication, description);
        treatments.add(treatment);
        saveTreatmentsToFile();
        System.out.println("Treatment created successfully!");
    }

    // Method to display all treatments
    public static void displayAllTreatments() {
        if (treatments.isEmpty()) {
            System.out.println("No treatments are recorded.");
        } else {
            System.out.println("Recorded Treatments:");
            for (Treatment treatment : treatments) {
                System.out.println("--------------------------------------");
                System.out.printf("Doctor: %s\nPatient: %s\nMedication: %s\nDescription: %s\n",
                                  treatment.getDoctorName(), treatment.getPatientName(),
                                  treatment.getMedication(), treatment.getDescription());
                System.out.println("--------------------------------------\n");
            }
        }
    }

    // Method to search treatments by patient name
    public static void searchTreatmentsByPatientName(Scanner scanner) {
        System.out.println("Enter patient's name to search:");
        String patientName = scanner.nextLine().trim();

        boolean found = false;
        for (Treatment treatment : treatments) {
            // Compare names with trimming and ignoring case
            if (treatment.getPatientName().trim().equalsIgnoreCase(patientName)) {
                System.out.printf("Doctor: %s\nPatient: %s\nMedication: %s\nDescription: %s\n\n",
                                  treatment.getDoctorName(), treatment.getPatientName(),
                                  treatment.getMedication(), treatment.getDescription());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No treatments found for patient: " + patientName);
        }
    }

    // Method to search treatments by doctor name
    public static void searchTreatmentsByDoctorName(Scanner scanner) {
        System.out.println("Enter doctor's name to search:");
        String doctorName = scanner.nextLine().trim();

        boolean found = false;
        for (Treatment treatment : treatments) {
            // Compare names with trimming and ignoring case
            if (treatment.getDoctorName().trim().equalsIgnoreCase(doctorName)) {
                System.out.printf("Doctor: %s\nPatient: %s\nMedication: %s\nDescription: %s\n\n",
                                  treatment.getDoctorName(), treatment.getPatientName(),
                                  treatment.getMedication(), treatment.getDescription());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No treatments found for doctor: " + doctorName);
        }
    }

    // Method to modify a treatment
    public static void modifyTreatment(Scanner scanner) {
        System.out.println("Enter patient's name for the treatment to modify:");
        String patientName = scanner.nextLine();

        for (Treatment treatment : treatments) {
            if (treatment.getPatientName().equals(patientName)) {
                System.out.println("Modifying treatment record for: " + treatment);
                System.out.println("Enter new medication (current: " + treatment.getMedication() + "):");
                treatment.setMedication(scanner.nextLine());
                System.out.println("Enter new description (current: " + treatment.getDescription() + "):");
                treatment.setDescription(scanner.nextLine());
                saveTreatmentsToFile();
                System.out.println("Treatment record updated successfully!");
                return;
            }
        }
        System.out.println("Treatment record not found!");
    }

    // Method to delete a treatment
    public static void deleteTreatment(Scanner scanner) {
        System.out.println("Enter patient's name for the treatment to delete:");
        String patientName = scanner.nextLine();

        boolean found = false;
        for (int i = 0; i < treatments.size(); i++) {
            if (treatments.get(i).getPatientName().equals(patientName)) {
                treatments.remove(i);
                found = true;
                saveTreatmentsToFile();
                System.out.println("Treatment record deleted successfully!");
                break;
            }
        }
        if (!found) {
            System.out.println("Treatment record not found!");
        }
    }

    // Method to save treatments to file
    private static void saveTreatmentsToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("treatments.txt", false))) {
            for (Treatment treatment : treatments) {
                writer.println(treatment.getDoctorName() + "," +
                               treatment.getPatientName() + "," +
                               treatment.getMedication() + "," +
                               treatment.getDescription());
            }
            System.out.println("Treatments saved to file successfully!");
        } catch (IOException e) {
            System.out.println("Error saving treatments to file: " + e.getMessage());
        }
    }

    // Method to load treatments from file
    private static void loadTreatmentsFromFile() {
        File file = new File("treatments.txt");
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",");
                    if (data.length == 4) {
                        Treatment treatment = new Treatment(data[0], data[1], data[2], data[3]);
                        treatments.add(treatment);
                    }
                }
            } catch (IOException e) {
                System.out.println("Error loading treatments from file: " + e.getMessage());
            }
        }
    }
}
