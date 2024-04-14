import java.util.Scanner;

public class TreatmentMenu {
    public static void displayTreatmentMenu(Scanner scanner) {
        boolean exit = false;
        while (!exit) {
            System.out.println("\nTREATMENT MENU");
            System.out.println("1. Create new treatment");
            System.out.println("2. Display all treatments");
            System.out.println("3. Search treatments by patient name");
            System.out.println("4. Search treatments by doctor name");
            System.out.println("5. Modify a treatment");
            System.out.println("6. Delete a treatment");
            System.out.println("7. Return to main menu");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    TreatmentService.createNewTreatment(scanner);
                    break;
                case 2:
                    TreatmentService.displayAllTreatments();
                    break;
                case 3:
                    TreatmentService.searchTreatmentsByPatientName(scanner);
                    break;
                case 4:
                    TreatmentService.searchTreatmentsByDoctorName(scanner);
                    break;
                case 5:
                    TreatmentService.modifyTreatment(scanner);
                    break;
                case 6:
                    TreatmentService.deleteTreatment(scanner);
                    break;
                case 7:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
    
}
