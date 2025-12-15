package org.carl;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Random;

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

    public Course(String courseName, double credits, Department department) {
        this.courseId = "C" + "-" + department.getDepartmentId() + "-" + String.format("%02d", nextId++);
        this.courseName = Util.toTitleCase(courseName);
        this.credits = credits;
        this.department = department;
        this.assignments = new ArrayList<>();
        this.registeredStudents = new ArrayList<>();
        this.finalScores = new ArrayList<>();
    }

    /**
     * checks if the sum of weights of all assignments of a course is a 100%
     * @return the boolean value. if the sum is 100, it returns true. else, returns false
     */
    public boolean isAssignmentWeightValid() {
        double sum = 0;
        double sumOfWeights = 100;

        for (Assignment assignment : assignments) {
            sum += assignment.getWeight();
        }

        return sum == sumOfWeights;
    }

    /**
     * adds a student to the student list of the course, adds a new null element
     * to each assignment of this course and add a new null element for the finalScores
     * @param student the object student
     * @return the boolean value that confirms if the student is added to the course or not
     */
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

    /**
     * calculates the weighted average score of a student
     * @return an array of averages of students
     */
    public int[] calcStudentsAverage() {
        int numOfStudents = registeredStudents.size();
        int[] finalScoresArray = new int[numOfStudents];

        finalScores.clear();

        for (int i = 0; i < numOfStudents; i++) {
            double weightedTotal = 0.0;

            for (Assignment assignment : assignments) {
                Integer score = assignment.getScores().get(i);
                if (score != null) {
                    weightedTotal += score * (assignment.getWeight() / 100.0);
                }
            }

            int roundedScore = (int) Math.round(weightedTotal);

            finalScores.add((double) roundedScore);
            finalScoresArray[i] = roundedScore;
        }

        return finalScoresArray;
    }

    /**
     * adds a new assignment to the course
     * @param assignmentName the name of the assignment
     * @param weight the weight of the assignment
     * @param maxScore the maximum score of the assignment
     * @return the boolean value that confirms if an assignment is added or not
     */
    public boolean addAssignment(String assignmentName, double weight, int maxScore) {
        Assignment newAssignment = new Assignment(assignmentName, weight, maxScore);
        assignments.add(newAssignment);

        for (int i = 0; i < registeredStudents.size(); i++) {
            newAssignment.getScores().add(null);
        }

        return true;
    }

    /**
     * generates random scores for each assignment and student, and calculates
     * the final score for each student
     */
    public void generateScores() {
        Random random = new Random();

        int numOfStudents = registeredStudents.size();

        for (Assignment assignment : assignments) {
            for (int studentIdx = 0; studentIdx < numOfStudents; studentIdx++) {
                int maxScore = 100;
                int randomScore = random.nextInt(maxScore + 1);

                assignment.getScores().set(studentIdx, randomScore);
            }
        }

        this.calcStudentsAverage();
    }

    /**
     * displays the scores of a course in a table, with the assignment averages and
     * student weighted average
     */
    public void displayScores() {
        calcStudentsAverage();

        System.out.println("Course: " + courseName + " (" + courseId + ")");
        System.out.println();

        System.out.printf("%-20s", "");
        for (Assignment assignment : assignments) {
            System.out.printf("%-15s", assignment.getAssignmentName());
        }

        System.out.printf("%-15s%n", "Final Score");

        for (int i = 0; i < registeredStudents.size(); i++) {
            Student student = registeredStudents.get(i);

            System.out.printf("%-20s", student.getStudentName());

            for (Assignment assignment : assignments) {
                Integer score = assignment.getScores().get(i);
                System.out.printf("%-15s", score == null ? "-" : score);
            }

            System.out.printf("%-15d%n", finalScores.get(i));
        }

        System.out.printf("%-20s", "Average");

        for (Assignment assignment : assignments) {
            int sum = 0;
            int count = 0;

            for (Integer score : assignment.getScores()) {
                if (score != null) {
                    sum += score;
                    count++;
                }
            }

            int avg = count == 0 ? 0 : Math.round((float) sum / count);
            System.out.printf("%-15d", avg);
        }

        System.out.println();
    }

    @Override
    public String toString() {
        String result = "Course{" +
                "courseId= " + courseId +
                ", courseName= " + courseName +
                ", credits= " + credits +
                ", departmentName= " + department.getDepartmentName();

        result = result + "\nassignments= [";
        for (int i = 0; i < assignments.size(); i++) {
            result = result + assignments.get(i).toString();

            if (i < assignments.size() - 1) {
                result = result + ", ";
            }
        }

        result = result + "]";

        result = result + "\nregisteredStudents= [";

        for (int i = 0; i < registeredStudents.size(); i++) {
            result = result + registeredStudents.get(i).toSimplifiedString();

            if (i < registeredStudents.size() - 1) {
                result = result + ", ";
            }
        }

        result = result + "]";

        boolean isValid = isAssignmentWeightValid();

        String weightStatus = isValid ? "Total assignment weight is valid" : "Total assignment weight is invalid";

        result = result + "\nweightStatus=" + weightStatus;

        return result + "}";
    }

    public String toSimplifiedString() {
        return  "courseId='" + courseId + '\'' +
                ", courseName='" + courseName + '\'' +
                ", credits=" + credits +
                ", department=" + department.getDepartmentName();
    }
}
