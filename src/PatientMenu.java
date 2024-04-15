import java.util.Scanner;

public class PatientMenu {
    // Method to display the patient menu and handle user input
    public static void displayPatientMenu(Scanner scanner) {
        boolean exit = false; // Flag to control loop termination
        while (!exit) {
            System.out.println("\nPATIENT MENU");
            System.out.println("1. Register new patient");
            System.out.println("2. Display all patients");
            System.out.println("3. Modify patient record");
            System.out.println("4. Delete patient record");
            System.out.println("5. Return to main menu");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt(); // Read user choice
            scanner.nextLine();  // Consume newline

            // Switch statement to handle different menu options
            switch (choice) {
                case 1:
                    PatientService.registerNewPatient(scanner); // Call method to register new patient
                    break;
                case 2:
                    PatientService.displayAllPatients(); // Call method to display all patients
                    break;
                case 3:
                    PatientService.modifyPatient(scanner); // Call method to modify patient record
                    break;
                case 4:
                    PatientService.deletePatient(scanner); // Call method to delete patient record
                    break;
                case 5:
                    exit = true; // Exit the patient menu
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
