import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;

        while (!quit) {
            System.out.println("\nMAIN MENU");
            System.out.println("Choose one of the following options (enter a number):");
            System.out.println("1. Patients");
            System.out.println("2. Doctors");
            System.out.println("3. Appointments");
            System.out.println("4. Treatments");
            System.out.println("5. Quit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    MenuController.displayPatientMenu(scanner);
                    break;
                case 2:
                    MenuController.displayDoctorMenu(scanner);
                    break;
                case 3:
                    MenuController.displayAppointmentMenu(scanner);
                    break;
                case 4:
                    MenuController.displayTreatmentMenu(scanner);
                    break;
                case 5:
                    quit = true;
                    System.out.println("Exiting the program...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
        scanner.close();
    }
}
