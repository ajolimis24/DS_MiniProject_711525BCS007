/**
 * Student class to store student information
 */
class Student {
    private int rollNo;
    private String name;
    private double marks;
    private int rank;

    /**
     * Constructor to initialize Student object
     */
    public Student(int rollNo, String name, double marks) {
        this.rollNo = rollNo;
        this.name = name;
        this.marks = marks;
        this.rank = 0;
    }

    // Getters
    public int getRollNo() {
        return rollNo;
    }

    public String getName() {
        return name;
    }

    public double getMarks() {
        return marks;
    }

    public int getRank() {
        return rank;
    }

    // Setter for rank
    public void setRank(int rank) {
        this.rank = rank;
    }

    /**
     * Display student information
     */
    public void display() {
        System.out.printf("| %-4d | %-15s | %6.2f | %-4d |\n", rollNo, name, marks, rank);
    }

    /**
     * Display student information with header
     */
    public static void displayHeader() {
        System.out.println("+=====+=================+=======+====+");
        System.out.println("| Roll| Name            | Marks | Rank|");
        System.out.println("+=====+=================+=======+====+");
    }

    @Override
    public String toString() {
        return "Student{" +
                "rollNo=" + rollNo +
                ", name='" + name + '\'' +
                ", marks=" + marks +
                ", rank=" + rank +
                '}';
    }
}

/**
 * Student Result System
 * Uses Insertion Sort for sorting and Linear Search for searching
 */
class StudentResultSystem {
    private Student[] students;
    private int count;

    /**
     * Constructor to initialize the system with maximum capacity
     */
    public StudentResultSystem(int capacity) {
        students = new Student[capacity];
        count = 0;
    }

    /**
     * Add a student to the system
     */
    public void addStudent(int rollNo, String name, double marks) {
        if (count < students.length) {
            students[count] = new Student(rollNo, name, marks);
            count++;
            System.out.println("✓ Student added successfully: " + name);
        } else {
            System.out.println("✗ System is full. Cannot add more students.");
        }
    }

    /**
     * Insertion Sort - Sorts students by marks in descending order
     * Time Complexity: O(n^2) in worst and average case, O(n) in best case
     * Space Complexity: O(1) - sorts in-place
     */
    public void sortByMarks() {
        long startTime = System.nanoTime();
        
        for (int i = 1; i < count; i++) {
            Student key = students[i];
            int j = i - 1;

            // Compare marks in descending order
            while (j >= 0 && students[j].getMarks() < key.getMarks()) {
                students[j + 1] = students[j];
                j--;
            }
            students[j + 1] = key;
        }

        long endTime = System.nanoTime();
        System.out.println("\n✓ Insertion Sort completed in " + (endTime - startTime) + " nanoseconds");
        
        // Assign ranks
        assignRanks();
    }

    /**
     * Assign ranks after sorting
     */
    private void assignRanks() {
        for (int i = 0; i < count; i++) {
            students[i].setRank(i + 1);
        }
    }

    /**
     * Linear Search - Search student by name
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * 
     * @param name Name of the student to search
     * @return Student object if found, null otherwise
     */
    public Student linearSearch(String name) {
        long startTime = System.nanoTime();
        
        for (int i = 0; i < count; i++) {
            if (students[i].getName().equalsIgnoreCase(name)) {
                long endTime = System.nanoTime();
                System.out.println("\n✓ Linear Search completed in " + (endTime - startTime) + " nanoseconds");
                return students[i];
            }
        }
        
        long endTime = System.nanoTime();
        System.out.println("\n✗ Linear Search completed in " + (endTime - startTime) + " nanoseconds - Not Found");
        return null;
    }

    /**
     * Linear Search - Search student by roll number
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * 
     * @param rollNo Roll number of the student to search
     * @return Student object if found, null otherwise
     */
    public Student linearSearchByRollNo(int rollNo) {
        long startTime = System.nanoTime();
        
        for (int i = 0; i < count; i++) {
            if (students[i].getRollNo() == rollNo) {
                long endTime = System.nanoTime();
                System.out.println("\n✓ Linear Search completed in " + (endTime - startTime) + " nanoseconds");
                return students[i];
            }
        }
        
        long endTime = System.nanoTime();
        System.out.println("\n✗ Linear Search completed in " + (endTime - startTime) + " nanoseconds - Not Found");
        return null;
    }

    /**
     * Display all students with their ranks
     */
    public void displayAllStudents() {
        if (count == 0) {
            System.out.println("\n✗ No students in the system.");
            return;
        }
        
        System.out.println("\n========== STUDENT RESULT SYSTEM ==========");
        Student.displayHeader();
        for (int i = 0; i < count; i++) {
            students[i].display();
        }
        System.out.println("+=====+=================+=======+====+\n");
    }

    /**
     * Find top N students
     */
    public void displayTopStudents(int n) {
        if (count == 0) {
            System.out.println("\n✗ No students in the system.");
            return;
        }
        
        n = Math.min(n, count);
        System.out.println("\n========== TOP " + n + " STUDENTS ==========");
        Student.displayHeader();
        for (int i = 0; i < n; i++) {
            students[i].display();
        }
        System.out.println("+=====+=================+=======+====+\n");
    }

    /**
     * Get total count of students
     */
    public int getTotalStudents() {
        return count;
    }
}

/**
 * Main class - Student Result System with User Menu
 */
public class StudentResultSystemApp {
    private static StudentResultSystem system;
    private static java.util.Scanner scanner;

    public static void main(String[] args) {
        scanner = new java.util.Scanner(System.in);
        system = new StudentResultSystem(100);

        displayWelcome();
        boolean running = true;

        while (running) {
            displayMenu();
            System.out.print("Enter your choice (1-8): ");
            
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Clear the buffer

                switch (choice) {
                    case 1:
                        addStudentOption();
                        break;
                    case 2:
                        sortStudentsOption();
                        break;
                    case 3:
                        searchByNameOption();
                        break;
                    case 4:
                        searchByRollNoOption();
                        break;
                    case 5:
                        displayAllStudentsOption();
                        break;
                    case 6:
                        displayTopStudentsOption();
                        break;
                    case 7:
                        loadSampleDataOption();
                        break;
                    case 8:
                        running = false;
                        displayGoodbye();
                        break;
                    default:
                        System.out.println("\n✗ Invalid choice! Please enter a number between 1 and 8.\n");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("\n✗ Invalid input! Please enter a valid number.\n");
                scanner.nextLine(); // Clear the buffer
            }
        }

        scanner.close();
    }

    private static void displayWelcome() {
        System.out.println("\n===============================================");
        System.out.println("  WELCOME TO STUDENT RESULT SYSTEM");
        System.out.println("===============================================");
        System.out.println("Ranking & Searching using Insertion Sort");
        System.out.println("and Linear Search\n");
    }

    private static void displayMenu() {
        System.out.println("\n---------- MENU ----------");
        System.out.println("1. Add Student");
        System.out.println("2. Sort Students by Marks");
        System.out.println("3. Search Student by Name");
        System.out.println("4. Search Student by Roll Number");
        System.out.println("5. Display All Students");
        System.out.println("6. Display Top N Students");
        System.out.println("7. Load Sample Data");
        System.out.println("8. Exit");
        System.out.println("------------------------");
    }

    private static void addStudentOption() {
        System.out.println("\n========== ADD STUDENT ==========");
        System.out.print("Enter Roll Number: ");
        int rollNo = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Marks: ");
        double marks = scanner.nextDouble();
        scanner.nextLine();

        system.addStudent(rollNo, name, marks);
    }

    private static void sortStudentsOption() {
        if (system.getTotalStudents() == 0) {
            System.out.println("\n✗ No students to sort! Add students first.");
            return;
        }
        System.out.println("\n========== SORTING STUDENTS ==========");
        system.sortByMarks();
        system.displayAllStudents();
    }

    private static void searchByNameOption() {
        System.out.println("\n========== SEARCH BY NAME ==========");
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();

        Student foundStudent = system.linearSearch(name);
        if (foundStudent != null) {
            System.out.println("Found: " + foundStudent);
        }
    }

    private static void searchByRollNoOption() {
        System.out.println("\n========== SEARCH BY ROLL NUMBER ==========");
        System.out.print("Enter Roll Number: ");
        int rollNo = scanner.nextInt();
        scanner.nextLine();

        Student foundStudent = system.linearSearchByRollNo(rollNo);
        if (foundStudent != null) {
            System.out.println("Found: " + foundStudent);
        }
    }

    private static void displayAllStudentsOption() {
        System.out.println("\n========== ALL STUDENTS ==========");
        system.displayAllStudents();
    }

    private static void displayTopStudentsOption() {
        if (system.getTotalStudents() == 0) {
            System.out.println("\n✗ No students in the system!");
            return;
        }
        System.out.println("\n========== TOP STUDENTS ==========");
        System.out.print("Enter number of top students to display: ");
        int n = scanner.nextInt();
        scanner.nextLine();

        system.displayTopStudents(n);
    }

    private static void loadSampleDataOption() {
        System.out.println("\n========== LOADING SAMPLE DATA ==========");
        system.addStudent(101, "Rajesh Kumar", 85.5);
        system.addStudent(102, "Priya Sharma", 92.0);
        system.addStudent(103, "Amit Singh", 78.5);
        system.addStudent(104, "Neha Patel", 88.0);
        system.addStudent(105, "Vikram Sharma", 95.5);
        system.addStudent(106, "Deepika Joshi", 81.0);
        system.addStudent(107, "Arjun Reddy", 90.0);
        system.addStudent(108, "Shruti Verma", 87.5);
        System.out.println("\n✓ Sample data loaded successfully!");
    }

    private static void displayGoodbye() {
        System.out.println("\n===============================================");
        System.out.println("  Thank you for using Student Result System!");
        System.out.println("===============================================\n");
    }
}
