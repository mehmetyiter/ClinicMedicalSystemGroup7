import java.util.Scanner;

public class MenuController {
    // Method to display patient menu
    public static void displayPatientMenu(Scanner scanner) {
        PatientMenu.displayPatientMenu(scanner);
    }

    // Method to display doctor menu
    public static void displayDoctorMenu(Scanner scanner) {
        DoctorMenu.displayDoctorMenu(scanner);
    }

    // Method to display appointment menu
    public static void displayAppointmentMenu(Scanner scanner) {
        AppointmentMenu.displayAppointmentMenu(scanner);
    }

    // Method to display treatment menu
    public static void displayTreatmentMenu(Scanner scanner) {
        TreatmentMenu.displayTreatmentMenu(scanner);
    }
}
