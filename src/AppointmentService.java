import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AppointmentService {
    // List to store appointments
    private static List<Appointment> appointments = new ArrayList<>();
    // List to store doctors
    private static List<Doctor> doctors = DoctorService.getDoctors(); // Get the list of doctors

    // Static block to load appointment data on startup
    static {
        loadAppointmentsFromFile();
    }

    // Method to create a new appointment
    public static void createNewAppointment(Scanner scanner) {
        try {
            System.out.println("Scheduling a new appointment:");
            System.out.println("Enter patient's name:");
            String patientName = scanner.nextLine().trim();
            
            // Select a doctor for the appointment
            Doctor doctor = selectDoctor(scanner);
            if (doctor == null) {
                System.out.println("No doctor selected or invalid selection.");
                return;
            }

            // Enter appointment date and time
            System.out.println("Enter appointment date (YYYY-MM-DD):");
            String date = scanner.nextLine().trim();
            System.out.println("Enter appointment time (HH:MM):");
            String time = scanner.nextLine().trim();

            // Create the appointment object and add it to the list
            Appointment appointment = new Appointment(patientName, doctor.getFullName(), date, time);
            appointments.add(appointment);
            // Save the updated appointments to file
            saveAppointmentsToFile(); 
            System.out.println("Appointment created successfully!");
        } catch (Exception e) {
            System.out.println("Failed to create an appointment: " + e.getMessage());
        }
    }

    // Method to select a doctor from the list of available doctors
    private static Doctor selectDoctor(Scanner scanner) {
        List<Doctor> doctors = DoctorService.getDoctors();  // Get the list of doctors from DoctorService
        if (doctors.isEmpty()) {
            System.out.println("No doctors available.");
            return null;
        }
        
        System.out.println("Select a doctor:");
        for (int i = 0; i < doctors.size(); i++) {
            Doctor doc = doctors.get(i);
            System.out.printf("%d. Dr. %s %s - %s\n", i + 1, doc.getFirstName(), doc.getLastName(), doc.getSpecialty());
        }
    
        System.out.println("Enter the number of the doctor:");
        int choice = Integer.parseInt(scanner.nextLine()); // Read the choice as an integer
        if (choice < 1 || choice > doctors.size()) {
            System.out.println("Invalid selection.");
            return null;
        }
    
        return doctors.get(choice - 1); // Return the selected doctor
    }

    // Method to display all appointments
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

    // Method to search appointments by patient name
    public static void searchAppointmentsByPatientName(Scanner scanner) {
        System.out.println("Enter patient's name to search:");
        String patientName = scanner.nextLine();

        boolean found = false;
        for (Appointment appointment : appointments) {
            if (appointment.getPatientName().equals(patientName)) {
                System.out.printf("Patient Name: %s\nDoctor Name: %s\nDate: %s\nTime: %s\n\n",
                                  appointment.getPatientName(), appointment.getDoctorName(),
                                  appointment.getDate(), appointment.getTime());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No appointments found for patient: " + patientName);
        }
    }

    // Method to search appointments by doctor name
    public static void searchAppointmentsByDoctorName(Scanner scanner) {
        System.out.println("Enter doctor's name to search:");
        String doctorName = scanner.nextLine();

        boolean found = false;
        for (Appointment appointment : appointments) {
            if (appointment.getDoctorName().equals(doctorName)) {
                System.out.printf("Patient Name: %s\nDoctor Name: %s\nDate: %s\nTime: %s\n\n",
                                  appointment.getPatientName(), appointment.getDoctorName(),
                                  appointment.getDate(), appointment.getTime());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No appointments found for doctor: " + doctorName);
        }
    }

    // Method to modify an appointment
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

                // Update appointment details
                appointment.setDate(newDate);
                appointment.setTime(newTime);
                // Save changes to file
                saveAppointmentsToFile(); 
                System.out.println("Appointment modified successfully!");
                return;
            }
        }
        System.out.println("Appointment not found!");
    }

    // Method to delete an appointment
    public static void deleteAppointment(Scanner scanner) {
        System.out.println("Enter the patient's name for the appointment to delete:");
        String patientName = scanner.nextLine();

        // Remove the appointment if found
        boolean removed = appointments.removeIf(app -> app.getPatientName().equals(patientName));
        if (removed) {
            // Save changes to file
            saveAppointmentsToFile(); 
            System.out.println("Appointment deleted successfully!");
        } else {
            System.out.println("Appointment not found!");
        }
    }

    // Method to save appointments to file
    private static void saveAppointmentsToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("appointments.txt", false))) {
            for (Appointment appointment : appointments) {
                // Write appointment details to file
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

    // Method to load appointments from file
    private static void loadAppointmentsFromFile() {
        File file = new File("appointments.txt");
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    // Split the line and create Appointment objects
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
