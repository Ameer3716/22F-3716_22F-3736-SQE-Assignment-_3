package code;
import java.util.ArrayList;
import java.util.List;

public class School {
    private List<Student> students;
    private List<Teacher> teachers;

    public School() {
        students = new ArrayList<>();
        teachers = new ArrayList<>();
    }

    public void addStudent(String name, int id) {
        students.add(new Student(name, id));
        System.out.println("Student added: " + name);
    }

    public void addTeacher(String name, int id) {
        teachers.add(new Teacher(name, id));
        System.out.println("Teacher added: " + name);
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void displayStudents() {
        System.out.println("Students List:");
        for (Student student : students) {
            System.out.println(student);
        }
    }

    public void displayTeachers() {
        System.out.println("Teachers List:");
        for (Teacher teacher : teachers) {
            System.out.println(teacher);
        }
    }
    public void addStudent1(String name, int id) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Invalid student name.");
        }
        if (id < 0) {
            throw new IllegalArgumentException("ID cannot be negative.");
        }
        for (Student s : students) {
            if (s.getId() == id) {
                throw new IllegalArgumentException("Student ID already exists.");
            }
        }
        students.add(new Student(name, id));
        System.out.println("Student added: " + name);
    }

    public void addTeacher1(String name, int id) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Invalid teacher name.");
        }
        if (id < 0) {
            throw new IllegalArgumentException("ID cannot be negative.");
        }
        for (Teacher t : teachers) {
            if (t.getId() == id) {
                throw new IllegalArgumentException("Teacher ID already exists.");
            }
        }
        teachers.add(new Teacher(name, id));
        System.out.println("Teacher added: " + name);
    }
}
