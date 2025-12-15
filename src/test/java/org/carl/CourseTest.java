package org.carl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class CourseTest {

    @Test
    @DisplayName("Sum = 20.0 + 40.0 + 40.0 -> true")
    void isAssignmentWeightValid1() {
        Department department = new Department("Computer Science");
        Course course1 = new Course("Discrete Math", 3.0, department);

        Assignment assignment1 = new Assignment("Assignment1", 20.0);
        Assignment assignment2 = new Assignment("Assignment2", 40.0);
        Assignment assignment3 = new Assignment("Assignment3", 40.0);

        course1.getAssignments().add(assignment1);
        course1.getAssignments().add(assignment2);
        course1.getAssignments().add(assignment3);

        boolean expected = true;
        boolean actual = course1.isAssignmentWeightValid();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Sum = 20.0 + 40.0 + 20.0 -> false")
    void isAssignmentWeightValid2() {
        Department department = new Department("Computer Science");
        Course course1 = new Course("Discrete Math", 3.0, department);

        Assignment assignment1 = new Assignment("Assignment1", 20.0);
        Assignment assignment2 = new Assignment("Assignment2", 40.0);
        Assignment assignment3 = new Assignment("Assignment3", 20.0);

        course1.getAssignments().add(assignment1);
        course1.getAssignments().add(assignment2);
        course1.getAssignments().add(assignment3);

        boolean expected = false;
        boolean actual = course1.isAssignmentWeightValid();
        Assertions.assertEquals(expected, actual);
    }
}