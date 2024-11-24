
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class StudentGradebookGUI {
    private JFrame frame;
    private HashMap<String, Student> students;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                StudentGradebookGUI window = new StudentGradebookGUI();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public StudentGradebookGUI() {
        students = new HashMap<>();
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new CardLayout(0, 0));

        JPanel mainPanel = new JPanel();
        frame.getContentPane().add(mainPanel, "mainPanel");
        mainPanel.setLayout(null);

        JButton btnAddStudent = new JButton("Add Student");
        btnAddStudent.setBounds(150, 50, 150, 25);
        mainPanel.add(btnAddStudent);

        JButton btnAddGrades = new JButton("Add Grades");
        btnAddGrades.setBounds(150, 100, 150, 25);
        mainPanel.add(btnAddGrades);

        JButton btnFindGrades = new JButton("Find Student Grades");
        btnFindGrades.setBounds(150, 150, 150, 25);
        mainPanel.add(btnFindGrades);

        btnAddStudent.addActionListener(e -> showAddStudentDialog());
        btnAddGrades.addActionListener(e -> showAddGradesDialog());
        btnFindGrades.addActionListener(e -> showFindGradesDialog());
    }

    private void showAddStudentDialog() {
        JTextField studentIdField = new JTextField();
        JTextField studentNameField = new JTextField();
        Object[] message = {
                "Student ID:", studentIdField,
                "Student Name:", studentNameField
        };

        int option = JOptionPane.showConfirmDialog(frame, message, "Add Student", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String id = studentIdField.getText();
            String name = studentNameField.getText();

            if (students.containsKey(id)) {
                JOptionPane.showMessageDialog(frame, "Student with ID " + id + " already exists!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                students.put(id, new Student(id, name));
                JOptionPane.showMessageDialog(frame, "Student added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void showAddGradesDialog() {
        JTextField studentIdField = new JTextField();
        Object[] idMessage = {
                "Student ID:", studentIdField
        };

        int idOption = JOptionPane.showConfirmDialog(frame, idMessage, "Add Grades", JOptionPane.OK_CANCEL_OPTION);
        if (idOption == JOptionPane.OK_OPTION) {
            String id = studentIdField.getText();

            if (!students.containsKey(id)) {
                JOptionPane.showMessageDialog(frame, "Student with ID " + id + " not found!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Student student = students.get(id);
            JPanel gradePanel = new JPanel(new GridLayout(7, 2));
            JTextField[] gradeFields = new JTextField[7];
            String[] subjects = {"Math", "Science", "English", "History", "Art", "Music", "Physical Education"};

            for (int i = 0; i < subjects.length; i++) {
                gradePanel.add(new JLabel("Grade for " + subjects[i] + ":"));
                gradeFields[i] = new JTextField();
                gradePanel.add(gradeFields[i]);
            }

            int gradeOption = JOptionPane.showConfirmDialog(frame, gradePanel, "Enter Grades", JOptionPane.OK_CANCEL_OPTION);
            if (gradeOption == JOptionPane.OK_OPTION) {
                for (int i = 0; i < subjects.length; i++) {
                    int numericGrade = Integer.parseInt(gradeFields[i].getText());
                    student.grades.put(subjects[i], convertNumericToLetterGrade(numericGrade));
                }
                JOptionPane.showMessageDialog(frame, "Grades added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void showFindGradesDialog() {
        JTextField studentIdField = new JTextField();
        Object[] message = {
                "Student ID:", studentIdField
        };

        int option = JOptionPane.showConfirmDialog(frame, message, "Find Student Grades", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String id = studentIdField.getText();

            if (!students.containsKey(id)) {
                JOptionPane.showMessageDialog(frame, "Student with ID " + id + " not found!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Student student = students.get(id);
            StringBuilder gradesMessage = new StringBuilder("Student ID: " + student.id + "\nStudent Name: " + student.name + "\nGrades:\n");

            student.grades.forEach((subject, grade) -> gradesMessage.append(subject).append(": ").append(grade).append("\n"));

            JOptionPane.showMessageDialog(frame, gradesMessage.toString(), "Student Grades", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static String convertNumericToLetterGrade(int numericGrade) {
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

    class Student {
        String id;
        String name;
        HashMap<String, String> grades;

        public Student(String id, String name) {
            this.id = id;
            this.name = name;
            this.grades = new HashMap<>();
        }
    }
}


