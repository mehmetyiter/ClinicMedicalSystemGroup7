import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Scanner object for user input
        boolean quit = false; // Flag to control program termination

        // Main menu loop
        while (!quit) {
            System.out.println("\nMAIN MENU");
            System.out.println("Choose one of the following options (enter a number):");
            System.out.println("1. Patients");
            System.out.println("2. Doctors");
            System.out.println("3. Appointments");
            System.out.println("4. Treatments");
            System.out.println("5. Quit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt(); // Read user choice
            scanner.nextLine();  // Consume newline

            // Switch statement to handle different menu options
            switch (choice) {
                case 1:
                    MenuController.displayPatientMenu(scanner); // Display patient menu
                    break;
                case 2:
                    MenuController.displayDoctorMenu(scanner); // Display doctor menu
                    break;
                case 3:
                    MenuController.displayAppointmentMenu(scanner); // Display appointment menu
                    break;
                case 4:
                    MenuController.displayTreatmentMenu(scanner); // Display treatment menu
                    break;
                case 5:
                    quit = true; // Exit the program
                    System.out.println("Exiting the program...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
        scanner.close(); // Close the scanner to release resources
    }
}
