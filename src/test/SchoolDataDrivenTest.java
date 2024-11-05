package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import code.School;

class SchoolDataDrivenTest {
	private School school;

	@BeforeEach
	void setUp() {
		school = new School();
	}

	static List<Arguments> getCSVData() throws Exception {
		List<Arguments> arguments = new ArrayList<>();
		FileReader in = new FileReader("src/test/resources/TestData.csv");
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);

		for (CSVRecord record : records) {
			String type = record.get("type");
			String name = record.get("name");
			String idString = record.get("id").trim(); // Trim whitespace
			int id = 0;

			try {
				id = Integer.parseInt(idString); // Parse integer
			} catch (NumberFormatException e) {
				System.err.println("Invalid input for integer ID: " + idString);
				// You can decide how to handle this case, e.g., set a default id or skip this record
				continue; // Skip this record or set an appropriate default value
			}

			String expectedOutcome = record.get("expectedOutcome");
			arguments.add(Arguments.of(type, name, id, expectedOutcome));
		}
		return arguments;
	}

	@ParameterizedTest
	@MethodSource("getCSVData")
	void testAddPerson(String type, String name, int id, String expectedOutcome) {
		if ("student".equals(type)) {
			if ("success".equals(expectedOutcome)) {
				school.addStudent(name, id);
				assertEquals(1, school.getStudents().size(), "Student should be added successfully.");
			} else {
				Exception exception = assertThrows(IllegalArgumentException.class, () -> {
					school.addStudent(name, id);
				});
				assertTrue(exception.getMessage().contains("Invalid student name") || 
						exception.getMessage().contains("ID cannot be negative") || 
						exception.getMessage().contains("Student ID already exists."));
			}
		} else if ("teacher".equals(type)) {
			if ("success".equals(expectedOutcome)) {
				school.addTeacher(name, id);
				assertEquals(1, school.getTeachers().size(), "Teacher should be added successfully.");
			} else {
				Exception exception = assertThrows(IllegalArgumentException.class, () -> {
					school.addTeacher(name, id);
				});
				assertTrue(exception.getMessage().contains("Invalid teacher name") || 
						exception.getMessage().contains("ID cannot be negative") || 
						exception.getMessage().contains("Teacher ID already exists."));
			}
		}
	}

	static List<Arguments> getAdditionalCSVDataForEdgeCases() throws Exception {
		List<Arguments> arguments = new ArrayList<>();
		FileReader in = new FileReader("src/test/resources/TestData2.csv");
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);

		for (CSVRecord record : records) {
			String type = record.get("type");
			String name = record.get("name");
			String idString = record.get("id").trim();
			int id = 0;

			try {
				id = Integer.parseInt(idString); // Parse integer
			} catch (NumberFormatException e) {
				System.err.println("Invalid input for integer ID: " + idString);
				continue; 
			}

			String expectedOutcome = record.get("expectedOutcome");
			arguments.add(Arguments.of(type, name, id, expectedOutcome));
		}
		return arguments;
	}

	@ParameterizedTest
	@MethodSource("getAdditionalCSVDataForEdgeCases")
	void testAddPersonEdgeCases(String type, String name, int id, String expectedOutcome) {
		if ("student".equals(type)) {
			if ("success".equals(expectedOutcom)) {
				school.addStudent1(name, id);
				assertEquals(1, school.getStudents().size(), "Student should be added successfully.");
			} else {
				Exception exception = assertThrows(IllegalArgumentException.class, () -> {
					school.addStudent1(name, id);
				});
				assertTrue(exception.getMessage().contains("Invalid student name") ||
						exception.getMessage().contains("ID cannot be negative") || 
						exception.getMessage().contains("Student ID already exists."));
			}
		} else if ("teacher".equals(type)) {
			if ("success".equals(expectedOutcome)) {
				school.addTeacher1(name, id);
				assertEquals(1, school.getTeachers().size(), "Teacher should be added successfully.");
			} else {
				Exception exception = assertThrows(IllegalArgumentException.class, () -> {
					school.addTeacher1(name, id);
				});
				assertTrue(exception.getMessage().contains("Invalid teacher name") || 
						exception.getMessage().contains("ID cannot be negative") || 
						exception.getMessage().contains("Teacher ID already exists."));
			}
		}
	}
}
