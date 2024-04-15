import java.util.Scanner;

public class TreatmentMenu {
    // Method to display the treatment menu and handle user input
    public static void displayTreatmentMenu(Scanner scanner) {
        boolean exit = false;
        while (!exit) {
            // Display the treatment menu options
            System.out.println("\nTREATMENT MENU");
            System.out.println("1. Create new treatment");
            System.out.println("2. Display all treatments");
            System.out.println("3. Search treatments by patient name");
            System.out.println("4. Search treatments by doctor name");
            System.out.println("5. Modify a treatment");
            System.out.println("6. Delete a treatment");
            System.out.println("7. Return to main menu");

            // Prompt the user to enter their choice
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            // Process the user's choice
            switch (choice) {
                case 1:
                    TreatmentService.createNewTreatment(scanner); // Create new treatment
                    break;
                case 2:
                    TreatmentService.displayAllTreatments(); // Display all treatments
                    break;
                case 3:
                    TreatmentService.searchTreatmentsByPatientName(scanner); // Search treatments by patient name
                    break;
                case 4:
                    TreatmentService.searchTreatmentsByDoctorName(scanner); // Search treatments by doctor name
                    break;
                case 5:
                    TreatmentService.modifyTreatment(scanner); // Modify a treatment
                    break;
                case 6:
                    TreatmentService.deleteTreatment(scanner); // Delete a treatment
                    break;
                case 7:
                    exit = true; // Return to main menu
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
