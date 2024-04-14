import java.util.Scanner;

public class PatientMenu {
    public static void displayPatientMenu(Scanner scanner) {
        boolean exit = false;
        while (!exit) {
            System.out.println("\nPATIENT MENU");
            System.out.println("1. Register new patient");
            System.out.println("2. Display all patients");
            System.out.println("3. Modify patient record");
            System.out.println("4. Delete patient record");
            System.out.println("5. Return to main menu");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    PatientService.registerNewPatient(scanner);
                    break;
                case 2:
                    PatientService.displayAllPatients();
                    break;
                case 3:
                    PatientService.modifyPatient(scanner);
                    break;
                case 4:
                    PatientService.deletePatient(scanner);
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
    
}
