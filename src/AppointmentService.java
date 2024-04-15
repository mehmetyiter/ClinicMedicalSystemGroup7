import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AppointmentService {
    // List to store appointments
    private static List<Appointment> appointments = new ArrayList<>();
       static {
        loadAppointmentsFromFile();
    }

    // Method to create a new appointment
    public static void createNewAppointment(Scanner scanner) {
    
        System.out.println("Enter patient's first name:");
        String patientFirstName = scanner.nextLine();
        System.out.println("Enter patient's last name:");
        String patientLastName = scanner.nextLine();
        Doctor doctor = selectDoctor(scanner); // Select a doctor
        if (doctor == null) {
            return;
        }
    
        System.out.println("Enter appointment date (YYYY-MM-DD):");
        String date = scanner.nextLine();
        System.out.println("Enter appointment time (HH:MM):");
        String time = scanner.nextLine();
    
        Appointment appointment = new Appointment(patientFirstName, patientLastName, doctor.getFirstName() + " " + doctor.getLastName(), date, time);
        appointments.add(appointment);
        saveAppointmentsToFile(); // Save changes to file
        System.out.println("Appointment scheduled successfully!");
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
            System.out.println("No appointments are scheduled.");
        } else {
            System.out.println("Scheduled Appointments:");
            for (Appointment appointment : appointments) {
                System.out.println("--------------------------------------");
                System.out.printf("Patient Name: %s\n", appointment.getPatientName());
                System.out.printf("Doctor Name: %s\n", appointment.getDoctorName());
                System.out.printf("Date: %s\n", appointment.getDate());
                System.out.printf("Time: %s\n", appointment.getTime());
                System.out.println("--------------------------------------\n");
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
                System.out.println("--------------------------------------");
                System.out.printf("Patient Name: %s\nDoctor Name: %s\nDate: %s\nTime: %s\n\n",
                                  appointment.getPatientName(), appointment.getDoctorName(),
                                  appointment.getDate(), appointment.getTime());
                System.out.println("--------------------------------------\n");
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
                System.out.println("--------------------------------------");
                System.out.printf("Patient Name: %s\nDoctor Name: %s\nDate: %s\nTime: %s\n\n",
                                  appointment.getPatientName(), appointment.getDoctorName(),
                                  appointment.getDate(), appointment.getTime());
                System.out.println("--------------------------------------\n");
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
                    String[] data = line.split(",");
                    if (data.length == 4) {
                        String[] fullName = data[0].split(" "); // Assuming the full name is separated by a space
                        String patientFirstName = fullName.length > 0 ? fullName[0] : "";
                        String patientLastName = fullName.length > 1 ? fullName[1] : "";
                        // Initialize Appointment with split first and last names
                        Appointment appointment = new Appointment(patientFirstName, patientLastName, data[1], data[2], data[3]);
                        appointments.add(appointment);
                    }
                }
            } catch (IOException e) {
                System.out.println("Error loading appointments from file: " + e.getMessage());
            }
        }
    }
    
}
