package test;

import code.School;
import code.Student;
import code.Teacher;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class SchoolTest {

	private School school;
	private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

	@BeforeEach
	void setUp() {
		school = new School();
		System.setOut(new PrintStream(outputStream)); // Capture console output for display tests
	}
	// Group 1: Student Tests
	@Tag("student")
	@Test
	@Order(1)
	void testAddValidStudent() {
		school.addStudent("Alice", 1);
		assertEquals(1, school.getStudents().size());
	}

	@Tag("student")
	@Test
	@Order(2)
	void testAddStudentWithEmptyName() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			school.addStudent("", 1);
		});
		assertEquals("Invalid student name.", exception.getMessage());
	}

	@Tag("student")
	@Test
	@Order(3)
	void testAddStudentWithNullName() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			school.addStudent(null, 1);
		});
		assertEquals("Invalid student name.", exception.getMessage());
	}

	@Tag("student")
	@Test
	@Order(4)
	void testAddStudentWithNegativeID() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			school.addStudent("Alice", -1);
		});
		assertEquals("ID cannot be negative.", exception.getMessage());
	}

	@Tag("student")
	@Test
	@Order(5)
	void testAddStudentWithDuplicateID() {
		school.addStudent("Alice", 1);
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			school.addStudent("Bob", 1);
		});
		assertEquals("Student ID already exists.", exception.getMessage());
	}

	@Tag("student")
	@Test
	@Order(6)
	void testDisplayStudents() {
		school.addStudent("Alice", 1);
		school.addStudent("Bob", 2);

		// Call displayStudents() and check output
		school.displayStudents();
		String output = outputStream.toString().trim();

		// Verify each student is displayed correctly
		assertTrue(output.contains("Student ID: 1, Name: Alice"));
		assertTrue(output.contains("Student ID: 2, Name: Bob"));
	}
	
	@Tag("student")
	@Test
	@Order(7)
	void testAddStudentConsoleOutput() {
		school.addStudent("Alice", 1);

		// Verify console output
		assertTrue(outputStream.toString().contains("Student added: Alice"));
	}
	
	@Tag("student")
	@Test
	@Order(8)
	void testAddStudentWithSpecialCharactersInName() {
		school.addStudent1("Alice@123", 3);
		assertEquals("Alice@123", school.getStudents().get(0).getName(), "Special characters in name should be accepted.");
	}
	
	@Tag("student")
	@Test
	@Order(9)
	void testDisplayStudentsWithEmptyList() {
	    school.displayStudents();
	    assertEquals("Students List:\n", outputStream.toString().trim(), "Output should indicate no students are added.");
	}
	
	@Tag("student")
	@Test
	@Order(10)
	void testAddStudentWithMaxIntegerID() {
	    school.addStudent1("MaxIDStudent", Integer.MAX_VALUE);
	    assertEquals(Integer.MAX_VALUE, school.getStudents().get(0).getId(), "Student ID should accept max integer value.");
	}
	
	// Group 2: Teacher Tests
	@Tag("teacher")
	@Test
	@Order(1)
	void testAddValidTeacher() {
		school.addTeacher("Mr. Smith", 101);
		assertEquals(1, school.getTeachers().size());
	}

	@Tag("teacher")
	@Test
	@Order(2)
	void testAddTeacherWithEmptyName() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			school.addTeacher("", 101);
		});
		assertEquals("Invalid teacher name.", exception.getMessage());
	}

	@Tag("teacher")
	@Test
	@Order(3)
	void testAddTeacherWithNullName() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			school.addTeacher(null, 101);
		});
		assertEquals("Invalid teacher name.", exception.getMessage());
	}

	@Tag("teacher")
	@Test
	@Order(4)
	void testAddTeacherWithNegativeID() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			school.addTeacher("Mr. Smith", -101);
		});
		assertEquals("ID cannot be negative.", exception.getMessage());
	}

	@Tag("teacher")
	@Test
	@Order(5)
	void testAddTeacherWithDuplicateID() {
		school.addTeacher("Mr. Smith", 101);
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			school.addTeacher("Ms. Johnson", 101);
		});
		assertEquals("Teacher ID already exists.", exception.getMessage());
	}

	@Tag("teacher")
	@Test
	@Order(6)
	void testDisplayTeachers() {
		school.addTeacher("Mr. Smith", 101);
		school.addTeacher("Ms. Johnson", 102);

		// Call displayTeachers() and check output
		school.displayTeachers();
		String output = outputStream.toString().trim();

		// Verify each teacher is displayed correctly
		assertTrue(output.contains("Teacher ID: 101, Name: Mr. Smith"));
		assertTrue(output.contains("Teacher ID: 102, Name: Ms. Johnson"));
	}

	@Tag("teacher")
	@Test
	@Order(7)
	void testAddTeacherConsoleOutput() {
		school.addTeacher("Mr. Smith", 101);

		// Verify console output
		assertTrue(outputStream.toString().contains("Teacher added: Mr. Smith"));
	}
	
	@Tag("teacher")
	@Test
	@Order(8)
	void testAddTeacherWithLongName() {
	    String longName = "Mr. Jonathan Christopher Smith the Third Esq.";
	    school.addTeacher1(longName, 103);
	    assertEquals(longName, school.getTeachers().get(0).getName(), "Long names should be accepted.");
	}
	
	@Tag("teacher")
	@Test
	@Order(9)
	void testDisplayTeachersWithEmptyList() {
	    school.displayTeachers();
	    assertEquals("Teachers List:\n", outputStream.toString().trim(), "Output should indicate no teachers are added.");
	}
	
	@Tag("teacher")
	@Test
	@Order(10)
	void testAddTeacherWithMaxIntegerID() {
	    school.addTeacher1("MaxIDTeacher", Integer.MAX_VALUE);
	    assertEquals(Integer.MAX_VALUE, school.getTeachers().get(0).getId(), "Teacher ID should accept max integer value.");
	}
}

