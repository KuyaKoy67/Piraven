package org.carl;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@EqualsAndHashCode
@Getter
@Setter
public class Course {
    private String courseId;
    private String courseName;
    private double credits;
    private Department department;
    private ArrayList<Assignment> assignments;
    private ArrayList<Student> registeredStudents;
    private ArrayList<Double> finalScores;

    private static int nextId;

    public boolean isAssignmentWeightValid() {
        double sum = 0;
        double sumOfWeights = 100;

        for (Assignment assignment : assignments) {
            sum += assignment.getWeight();
        }

        return sum == sumOfWeights;
    }

    public boolean registerStudent(Student student) {
        if (registeredStudents.contains(student)) {
            return false;
        }

        registeredStudents.add(student);

        for (Assignment assignment : assignments) {
            assignment.getScores().add(null);

            finalScores.add(null);
        }

        return true;
    }

    public int[] calcStudentAverage() {
        int numOfStudents = registeredStudents.size();

        int[] finalScoresArray = new int[numOfStudents];

        for (int idxOfStudent = 0; idxOfStudent < numOfStudents; idxOfStudent++) {
            double studentWeightedAverage = 0.0;

            for (Assignment assignment : assignments) {
                Integer score = assignment.getScores().get(idxOfStudent);

                if (score != null) {
                    double weight = assignment.getWeight();

                    studentWeightedAverage += (score * weight);
                }
            }

            finalScores.add(studentWeightedAverage);

            finalScoresArray[idxOfStudent] = (int) Math.round(studentWeightedAverage);
        }

        return finalScoresArray;
    }

    public boolean addAssignment(String assignmentName, double weight, int maxScore) {
        Assignment newAssignment = new Assignment(assignmentName, weight, maxScore);
        assignments.add(newAssignment);

        for (int i = 0; i < registeredStudents.size(); i++) {
            newAssignment.getScores().add(null);
        }

        return true;
    }

    public void generateScores() {
        Random random = new Random();

        int numOfStudents = registeredStudents.size();

        for (Assignment assignment : assignments) {
            for (int studentIdx = 0; studentIdx < numOfStudents; studentIdx++) {
                int maxScore = assignment.getMaxPossibleScore();
                int randomScore = random.nextInt(maxScore + 1);

                assignment.getScores().set(studentIdx, randomScore);
            }
        }

        this.calcStudentsAverage();
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId='" + courseId + '\'' +
                ", courseName='" + courseName + '\'' +
                ", credits=" + credits +
                ", departmentName=" + department.getDepartmentName() +
                ", assignments=" + assignments +
                ", registeredStudents=" + registeredStudents.toString() +
                '}';
    }
    
    public String toSimplifiedString() {
        return "Course{" +
                "courseId='" + courseId + '\'' +
                ", courseName='" + courseName + '\'' +
                ", credits=" + credits +
                ", department=" + department.getDepartmentName() +
                '}';
    }
}
