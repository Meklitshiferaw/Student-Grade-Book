
	import java.util.HashMap;
	import java.util.Scanner;

	public class studentgradebook {
		public static void main(String[] args) {
	        Scanner input = new Scanner(System.in);
	        HashMap<String, Student> students = new HashMap<>();

	        while (true) {
	            System.out.println("\nStudent Gradebook");
	            System.out.println("1. Add Student");
	            System.out.println("2. Add Grades");
	            System.out.println("3. Find Student Grades");
	            System.out.println("4. Exit");

	            int choice = input.nextInt();

	            switch (choice) {
	                case 1:
	                    addStudent(students, input);
	                    break;
	                case 2:
	                    addGrades(students, input);
	                    break;
	                case 3:
	                    findStudentGrades(students, input);
	                    break;
	                case 4:
	                    System.out.println("Exiting program...");
	                    input.close();
	                    return;
	                default:
	                    System.out.println("Invalid choice!");
	            }
	        }

	    }

	    private static void addStudent(HashMap<String, Student> students, Scanner input) {
	        System.out.print("Enter student ID: ");
	        String id = input.next();


	        if (students.containsKey(id)) {
	            System.out.println("Student with ID " + id + " already exists!");


	        } else {
	            System.out.print("Enter student name: ");
	            String name = input.next();
	            students.put(id, new Student(id, name));
	            System.out.println("Student added successfully!");
	        }
	    }

	    private static void addGrades(HashMap<String, Student> students, Scanner input) {
	        System.out.print("Enter student ID: ");
	        String id = input.next();

	        if (!students.containsKey(id)) {
	            System.out.println("Student with ID " + id + " not found!");
	            return;
	        }

	        Student student = students.get(id);
	        String[] subjects = {"Applied Math", "OOP", "Data Structure", "Database","Networking", "Discrete Math"};

	        for (String subject : subjects) {
	            System.out.print("Enter grade for " + subject + ": ");
	            double numericGrade = input.nextDouble();
	            student.grades.put(subject, convertNumericToLetterGrade(numericGrade));
	        }

	        System.out.println("Grades added successfully!");
	    }

	    private static void findStudentGrades(HashMap<String, Student> students, Scanner input) {
	        System.out.print("Enter student ID: ");
	        String id = input.next();

	        if (!students.containsKey(id)) {
	            System.out.println("Student with ID " + id + " not found!");
	            return;
	        }
	        Student student = students.get(id);
	        System.out.println("\nStudent ID: " + student.id);
	        System.out.println("Student Name: " + student.name);
	        System.out.println("Grades:");

	        student.grades.forEach((subject, grade) -> System.out.println(subject + ": " + grade));
	    }

	    private static String convertNumericToLetterGrade(double numericGrade) {
	        if (numericGrade >= 90) {
	            return "A";
	        } else if (numericGrade >= 80) {
	            return "B";
	        } else if (numericGrade >= 70) {
	            return "C";
	        } else if (numericGrade >= 60) {
	            return "D";
	        } else {
	            return "F";
	        }
	    }
	}

	class Student {
	    String id;
	    String name;
	    HashMap<String, String> grades; // Stores subject and corresponding letter grade

	    public Student(String id, String name) {
	        this.id = id;
	        this.name = name;
	        this.grades = new HashMap<>();
	    }
	}