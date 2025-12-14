package org.carl;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@EqualsAndHashCode
@Getter
@Setter
public class Student {
    private String studentId;
    private String studentName;
    private Gender gender;
    private Address address;
    private Department department;
    private ArrayList<Course> registeredCourses;

    private static int nextId;

    public boolean registerCourse(Course course) {
        if (registeredCourses.contains(course)) {
            return false;
        }

        registeredCourses.add(course);

        course.getRegisteredStudents().add(this);

        for (Assignment assignment : course.getAssignments()) {
            assignment.getScores().add(null);
        }

        return true;
    }

    public boolean dropCourse(Course course) {
        if (!registeredCourses.contains(course)) {
            return false;
        }

        registeredCourses.remove(course);

        course.getRegisteredStudents().remove(this);

        return true;
    }

    public String toSimplifiedString() {
        return  "studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", departmentName=" + department.getDepartmentName();
    }

    @Override
    public String toString() {
        String result = "Student{" +
                "studentId= " + studentId +
                ", studentName= " + studentName +
                ", gender= " + gender +
                ", address= " + address +
                ", departmentName= " + department.getDepartmentName() +
                "\nregisteredCourses= [";

        for (int i = 0; i < registeredCourses.size(); i++) {
            Course course = registeredCourses.get(i);

            result = result + course.toSimplifiedString();

            result = result.substring(0, result.length() - 1);
            result = result + ", " + course.getDepartment().getDepartmentName() + ")";

            if (i < registeredCourses.size() - 1) {
                result = result + ", ";
            }
        }

        result = result + "]" + "}";
        return result;
    }

    public Student(String studentName, Gender gender, Address address,
                   Department department) {
        this.studentId = String.format("%06d", nextId++);
        this.studentName = Util.toTitleCase(studentName);
        this.gender = gender;
        this.address = address;
        this.department = department;
        this.registeredCourses = new ArrayList<>();
    }

    public enum Gender {
        FEMALE, MALE
    }
}
