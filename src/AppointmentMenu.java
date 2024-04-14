import java.util.Scanner;

public class AppointmentMenu {
    public static void displayAppointmentMenu(Scanner scanner) {
        boolean exit = false;
        while (!exit) {
            System.out.println("\nAPPOINTMENT MENU");
            System.out.println("1. Create new appointment");
            System.out.println("2. Display all appointments");
            System.out.println("3. Search appointments by patient name");
            System.out.println("4. Search appointments by doctor name");
            System.out.println("5. Modify an appointment");
            System.out.println("6. Delete an appointment");
            System.out.println("7. Return to main menu");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    AppointmentService.createNewAppointment(scanner);
                    break;
                case 2:
                    AppointmentService.displayAllAppointments();
                    break;
                case 3:
                    AppointmentService.searchAppointmentsByPatientName(scanner);
                    break;
                case 4:
                    AppointmentService.searchAppointmentsByDoctorName(scanner);
                    break;
                case 5:
                    AppointmentService.modifyAppointment(scanner);
                    break;
                case 6:
                    AppointmentService.deleteAppointment(scanner);
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
