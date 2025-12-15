package org.carl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class CourseTest {

    @Test
    @DisplayName("isAssignmentWeightValid(): Sum = 20.0 + 40.0 + 40.0 -> true")
    void isAssignmentWeightValid1() {
        Department department = new Department("Computer Science");
        Course course1 = new Course("Discrete Math", 3.0, department);

        Assignment assignment1 = new Assignment("Assignment1", 20.0, 100);
        Assignment assignment2 = new Assignment("Assignment2", 40.0, 100);
        Assignment assignment3 = new Assignment("Assignment3", 40.0, 100);

        course1.getAssignments().add(assignment1);
        course1.getAssignments().add(assignment2);
        course1.getAssignments().add(assignment3);

        boolean expected = true;
        boolean actual = course1.isAssignmentWeightValid();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("isAssignmentWeightValid():Sum = 20.0 + 40.0 + 20.0 -> false")
    void isAssignmentWeightValid2() {
        Department department = new Department("Computer Science");
        Course course1 = new Course("Discrete Math", 3.0, department);

        Assignment assignment1 = new Assignment("Assignment1", 20.0, 100);
        Assignment assignment2 = new Assignment("Assignment2", 40.0, 100);
        Assignment assignment3 = new Assignment("Assignment3", 20.0, 100);

        course1.getAssignments().add(assignment1);
        course1.getAssignments().add(assignment2);
        course1.getAssignments().add(assignment3);

        boolean expected = false;
        boolean actual = course1.isAssignmentWeightValid();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("registerStudent(): registering a student in a course -> true ")
    void registerStudentTest1() {
        Department department = new Department("Computer Science");
        Address address = new Address(120, "Bouchette", "Montreal", Address.Province.QC, "A1B2C3");
        Student student = new Student("John Alack", Student.Gender.MALE, address, department);
        Course course1 = new Course("Discrete Math", 3.0, department);

        boolean expected = true;
        boolean actual = course1.registerStudent(student);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("registerStudent(): registering a student in a course that he is already in -> false ")
    void registerStudentTest2() {
        Department department = new Department("Computer Science");
        Address address = new Address(120, "Bouchette", "Montreal", Address.Province.QC, "A1B2C3");
        Student student = new Student("John Alack", Student.Gender.MALE, address, department);
        Course course1 = new Course("Discrete Math", 3.0, department);

        course1.getRegisteredStudents().add(student);

        boolean expected = false;
        boolean actual = course1.registerStudent(student);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("calcStudentsAverage(): 1 student, two assignments with weights 40 and 60 -> {86}")
    void testCalcStudentsAverage1() {
        Department department = new Department("Computer Science");
        Course course1 = new Course("Discrete Math", 3.0, department);
        Address address = new Address(120, "Bouchette", "Montreal", Address.Province.QC, "A1B2C3");
        Student student = new Student("John Alack", Student.Gender.MALE, address, department);

        course1.addAssignment("A1", 40, 100);
        course1.addAssignment("A2", 60, 100);

        student.registerCourse(course1);

        course1.getAssignments().get(0).getScores().set(0, 80);
        course1.getAssignments().get(1).getScores().set(0, 90);

        int[] actual = course1.calcStudentsAverage();
        int[] expected = {86};

        Assertions.assertArrayEquals(actual, expected);
    }

    @Test
    @DisplayName("calcStudentsAverage(): 2 students, two assignments each with weights 40 and 60 -> {86, 90}")
    void testCalcStudentsAverage2() {
        Department department = new Department("Computer Science");
        Course course1 = new Course("Discrete Math", 3.0, department);
        Address address = new Address(120, "Bouchette", "Montreal", Address.Province.QC, "A1B2C3");
        Student student1 = new Student("John Alack", Student.Gender.MALE, address, department);
        Student student2 = new Student("Belledevire Alack", Student.Gender.MALE, address, department);

        course1.addAssignment("A1", 40, 100);
        course1.addAssignment("A2", 60, 100);

        student1.registerCourse(course1);
        student2.registerCourse(course1);

        course1.getAssignments().get(0).getScores().set(0, 80);
        course1.getAssignments().get(1).getScores().set(0, 90);

        course1.getAssignments().get(0).getScores().set(1, 80);
        course1.getAssignments().get(1).getScores().set(1, 100);

        int[] actual = course1.calcStudentsAverage();
        int[] expected = {86, 92};

        Assertions.assertArrayEquals(actual, expected);
    }

    @Test
    @DisplayName("addAssignment(): assignmentName = Midterm Exam, weight = 40.0, maxscore = 100 -> true")
    void addAssignment1() {
        Department department = new Department("Computer Science");
        Course course1 = new Course("Discrete Math", 3.0, department);

        Address address = new Address(120, "Bouchette", "Montreal", Address.Province.QC, "A1B2C3");
        Student student1 = new Student("John Alack", Student.Gender.MALE, address, department);
        Student student2 = new Student("Barley Alack", Student.Gender.MALE, address, department);

        student1.registerCourse(course1);
        student2.registerCourse(course1);

        course1.addAssignment("Mini Quiz", 10.0, 100);

        String newName = "Midterm Exam";
        double newWeight = 40.0;
        int newMaxScore = 100;

        boolean actual = course1.addAssignment(newName, newWeight, newMaxScore);
        Assertions.assertTrue(actual);
    }

    @Test
    @DisplayName("addAssignment(): assignmentName is same as one of the assignments already assigned -> false")
    void addAssignment2() {
        Department department = new Department("Computer Science");
        Course course1 = new Course("Discrete Math", 3.0, department);

        Address address = new Address(120, "Bouchette", "Montreal", Address.Province.QC, "A1B2C3");
        Student student1 = new Student("John Alack", Student.Gender.MALE, address, department);
        Student student2 = new Student("Barley Alack", Student.Gender.MALE, address, department);

        student1.registerCourse(course1);
        student2.registerCourse(course1);

        course1.addAssignment("Mini Quiz", 10.0, 100);

        String newName = "Mini quiz";
        double newWeight = 40.0;
        int newMaxScore = 100;

        boolean actual = course1.addAssignment(newName, newWeight, newMaxScore);
        Assertions.assertFalse(actual);
    }
}
