import java.util.Scanner;

public class DoctorMenu {
    public static void displayDoctorMenu(Scanner scanner) {
        boolean exit = false;
        while (!exit) {
            System.out.println("\nDOCTOR MENU");
            System.out.println("1. Register new doctor");
            System.out.println("2. Display all doctors");
            System.out.println("3. Modify doctor record");
            System.out.println("4. Delete doctor record");
            System.out.println("5. Return to main menu");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    DoctorService.registerNewDoctor(scanner);
                    break;
                case 2:
                    DoctorService.displayAllDoctors();
                    break;
                case 3:
                    DoctorService.modifyDoctor(scanner);
                    break;
                case 4:
                    DoctorService.deleteDoctor(scanner);
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
