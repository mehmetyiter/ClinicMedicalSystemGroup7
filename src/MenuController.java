import java.util.Scanner;

public class MenuController {
    public static void displayPatientMenu(Scanner scanner) {
        PatientMenu.displayPatientMenu(scanner);
    }

    public static void displayDoctorMenu(Scanner scanner) {
        DoctorMenu.displayDoctorMenu(scanner);
    }

    public static void displayAppointmentMenu(Scanner scanner) {
        AppointmentMenu.displayAppointmentMenu(scanner);
    }

    public static void displayTreatmentMenu(Scanner scanner) {
        TreatmentMenu.displayTreatmentMenu(scanner);
    }
}
