package org.carl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StudentTest {

    @Test
    @DisplayName("RegisterCourse(): Course to register -> true")
    void registerCourseTest1() {
        Department department = new Department("Computer Science");
        Address address = new Address(120, "Bouchette", "Montreal", Address.Province.QC, "A1B2C3");
        Student student = new Student("John Alack", Student.Gender.MALE, address, department);
        Course course1 = new Course("Discrete Math", 3.0, department);

        boolean expectedReturn = true;
        boolean actualReturn = student.registerCourse(course1);

        Assertions.assertEquals(expectedReturn, actualReturn);
    }

    @Test
    @DisplayName("RegisterCourse(): Course already registered -> false")
    void registerCourseTest2() {
        Department department = new Department("Computer Science");
        Address address = new Address(120, "Bouchette", "Montreal", Address.Province.QC, "A1B2C3");
        Student student = new Student("John Alack", Student.Gender.MALE, address, department);
        Course course1 = new Course("Discrete Math", 3.0, department);

        student.getRegisteredCourses().add(course1);
        course1.getRegisteredStudents().add(student);

        boolean expectedReturn = false;
        boolean actualReturn = student.registerCourse(course1);

        Assertions.assertEquals(expectedReturn, actualReturn);
    }

    @Test
    @DisplayName("DropCourse(): Course drop -> true")
    void dropCourseTest1() {
        Department department = new Department("Computer Science");
        Address address = new Address(120, "Bouchette", "Montreal", Address.Province.QC, "A1B2C3");
        Student student = new Student("John Alack", Student.Gender.MALE, address, department);
        Course course = new Course("Discrete Math", 3.0, department);

        student.getRegisteredCourses().add(course);
        course.getRegisteredStudents().add(student);

        boolean expectedReturn = true;
        boolean actualReturn = student.dropCourse(course);

        Assertions.assertEquals(expectedReturn, actualReturn);
    }

    @Test
    @DisplayName("DropCourse(): Course that's already dropped -> false")
    void dropCourseTest2() {
        Department department = new Department("Computer Science");
        Address address = new Address(120, "Bouchette", "Montreal", Address.Province.QC, "A1B2C3");
        Student student = new Student("John Alack", Student.Gender.MALE, address, department);
        Course course = new Course("Discrete Math", 3.0, department);

        boolean expectedReturn = false;
        boolean actualReturn = student.dropCourse(course);

        Assertions.assertEquals(expectedReturn, actualReturn);
    }
}
