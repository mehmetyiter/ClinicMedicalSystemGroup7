import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AppointmentService {
    private static List<Appointment> appointments = new ArrayList<>();

    static {
        loadAppointmentsFromFile(); // Load appointment data on startup
    }

    public static void createNewAppointment(Scanner scanner) {
        System.out.println("Scheduling a new appointment:");
        System.out.println("Enter patient's name:");
        String patientName = scanner.nextLine().trim();
        System.out.println("Enter doctor's name:");
        String doctorName = scanner.nextLine().trim();
        System.out.println("Enter appointment date (YYYY-MM-DD):");
        String date = scanner.nextLine().trim();
        System.out.println("Enter appointment time (HH:MM):");
        String time = scanner.nextLine().trim();

        // Data validation can be added here as needed

        Appointment appointment = new Appointment(patientName, doctorName, date, time);
        appointments.add(appointment);
        saveAppointmentsToFile(); // Save appointments to file after addition
        System.out.println("Appointment created successfully!");
    }

    public static void displayAllAppointments() {
        if (appointments.isEmpty()) {
            System.out.println("No appointments scheduled.");
        } else {
            System.out.println("Scheduled Appointments:");
            for (Appointment appointment : appointments) {
                System.out.printf("Patient Name: %s\nDoctor Name: %s\nDate: %s\nTime: %s\n\n",
                                  appointment.getPatientName(), appointment.getDoctorName(),
                                  appointment.getDate(), appointment.getTime());
            }
        }
    }

    public static void modifyAppointment(Scanner scanner) {
        System.out.println("Enter the patient's name for the appointment to modify:");
        String patientName = scanner.nextLine();

        for (Appointment appointment : appointments) {
            if (appointment.getPatientName().equals(patientName)) {
                System.out.println("Modifying appointment for: " + patientName);
                System.out.println("Enter the new appointment date (YYYY-MM-DD):");
                String newDate = scanner.nextLine();
                System.out.println("Enter the new appointment time (HH:MM):");
                String newTime = scanner.nextLine();

                appointment.setDate(newDate);
                appointment.setTime(newTime);
                saveAppointmentsToFile(); // Save changes
                System.out.println("Appointment modified successfully!");
                return;
            }
        }
        System.out.println("Appointment not found!");
    }

    public static void deleteAppointment(Scanner scanner) {
        System.out.println("Enter the patient's name for the appointment to delete:");
        String patientName = scanner.nextLine();

        boolean removed = appointments.removeIf(app -> app.getPatientName().equals(patientName));
        if (removed) {
            saveAppointmentsToFile(); // Save changes
            System.out.println("Appointment deleted successfully!");
        } else {
            System.out.println("Appointment not found!");
        }
    }

    private static void saveAppointmentsToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("appointments.txt", false))) {
            for (Appointment appointment : appointments) {
                writer.println(appointment.getPatientName() + "," +
                               appointment.getDoctorName() + "," +
                               appointment.getDate() + "," +
                               appointment.getTime());
            }
            System.out.println("Appointments saved to file successfully!");
        } catch (IOException e) {
            System.out.println("Error saving appointments to file: " + e.getMessage());
        }
    }

    private static void loadAppointmentsFromFile() {
        File file = new File("appointments.txt");
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",");
                    if (data.length == 4) {
                        Appointment appointment = new Appointment(data[0], data[1], data[2], data[3]);
                        appointments.add(appointment);
                    }
                }
            } catch (IOException e) {
                System.out.println("Error loading appointments from file: " + e.getMessage());
            }
        }
    }
}
