import java.sql.*;
import java.sql.DriverManager;
import java.util.Scanner;

public class Main {
    private static Connection con = null;
    public static void main(String[] args) throws Exception {
        String query = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital1", "root", "");
            System.out.println("xampp Mysql Connected...");
            Scanner input = new Scanner(System.in);
            System.out.println("1_Distinct Patient");
            System.out.println("2_Distinct Doctors");
            System.out.println("3_Distinct Department with NumDoctors");
            System.out.println("4_Distinct Patient with 510152 ID");
            System.out.println("5_Distinct All Mirak doctor appointments with ID 352");
            System.out.println("6-change sara phoneNumber");
            int number = input.nextInt();
            if (number == 1) {
                query = "SELECT FirstName,LastName,ID,Age,Gender,PhoneNumber,Etc FROM patientinformation";
            } else if (number == 2) {
                query = "SELECT ID, Name ,Specialty,Email FROM doctors";
            } else if (number == 3) {
                query = "SELECT DepartmentName, NumDoctors FROM departments " +
                        "UNION ALL " +
                        "SELECT NULL AS DepartmentName, SUM(NumDoctors) FROM departments";
            } else if (number == 4) {
                query = "SELECT PatientID,Diagnosis,Medication,Details FROM medicalrecords where PatientID LIKE '5_____'";
            } else if (number == 5) {
                query = "SELECT pationtID, AppointmentDate FROM appointments1 WHERE DoctorID = 352";
            } else if (number == 6) {
                query = "SELECT * FROM patientinformation WHERE ID = 381318 ";
                System.out.println("1-change PhoneNumber");
                int a = input.nextInt();
                if (a == 1) {
                    query = "UPDATE patientinformation SET PhoneNumber = 221221 WHERE ID = 381318";
                }
            }
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                if (number == 1) {
                    String FirstName = rs.getString("FirstName");
                    String LastName = rs.getString("LastName");
                    int ID = rs.getInt("ID");
                    int age = rs.getInt("age");
                    String gender = rs.getString("Gender");
                    Long phoneNumber = rs.getLong("PhoneNumber");
                    String Etc = rs.getString("Etc");
                    System.out.printf("%-20s %-20s %-10s %-10s %-10s %-10s %-20s%n", "FirstName", "LastName", "ID", "Age", "Gender", "PhoneNumber", "Etc");
                    System.out.printf("%-20s %-20s %-10s %-10s %-10s %-10s %-20s%n", FirstName, LastName, ID, age, gender, phoneNumber, Etc);
                    System.out.println("--------------------------------------------------");
                } else if (number == 2) {
                    int ID = rs.getInt("ID");
                    String Name = rs.getString("Name");
                    String Specialty = rs.getString("Specialty");
                    String Email = rs.getString("Email");
                    System.out.printf("%-20s %-20s %-10s %-10s%n", "ID", "Name", "Specialty", "Email");
                    System.out.printf("%-20s %-20s %-10s %-10s%n", ID, Name, Specialty, Email);
                    System.out.println("--------------------------------------------------");
                } else if (number == 3) {
                    String departmentName = rs.getString("DepartmentName");
                    int NumDoctors = rs.getInt("NumDoctors");
                    System.out.println("Department Name: " + departmentName);
                    System.out.println("Total Doctors: " + NumDoctors);
                    System.out.println("---------------------------");
                } else if (number == 4) {
                    int PatientID = rs.getInt("PatientID");
                    String Diagnosis = rs.getString("Diagnosis");
                    String Medication = rs.getString("Medication");
                    String Details = rs.getString("Details");
                    System.out.println("--------------------------------------------------");
                    System.out.printf("%-10s %-10s %-10s %-10s%n", "PatientID", "Diagnosis", "Medication", "Details");
                    System.out.printf("%-10s %-10s %-10s %-10s%n", PatientID, Diagnosis, Medication, Details);
                    System.out.println("--------------------------------------------------");
                } else if (number == 5) {
                    String pationtID = rs.getString("pationtID");
                    String AppointmentDate = rs.getString("AppointmentDate");
                    System.out.println("--------------------------------------------------");
                    System.out.printf("%-10s %-10s%n", "PatientID", "AppointmentDate");
                    System.out.printf("%-10s %-10s%n", pationtID, AppointmentDate);
                    System.out.println("--------------------------------------------------");
                } else if (number == 6) {
                    String FirstName = rs.getString("FirstName");
                    String LastName = rs.getString("LastName");
                    int ID = rs.getInt("ID");
                    int Age = rs.getInt("Age");
                    String Gender = rs.getString("Gender");
                    String PhoneNumber = rs.getString("PhoneNumber");
                    System.out.println("First Name: " + FirstName);
                    System.out.println("Last Name: " + LastName);
                    System.out.println("ID: " + ID);
                    System.out.println("Age: " + Age);
                    System.out.println("Gender: " + Gender);
                    System.out.println("Phone Number: " + PhoneNumber);
                    System.out.println("Phone number updated successfully.");
                }
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}