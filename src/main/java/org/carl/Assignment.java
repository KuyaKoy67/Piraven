package org.carl;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Random;

@Setter
@Getter
public class Assignment {
    private String assignmentId;
    private String assignmentName;
    private double weight;
    private ArrayList<Integer> scores;

    private static int nextId;

    public Assignment(String assignmentName, double weight, int maxScore) {
        this.assignmentId = String.format("%05d",  nextId++);
        this.assignmentName = Util.toTitleCase(assignmentName);
        this.weight = weight;
        this.scores = new ArrayList<>();
    }

    /**
     * calculates the average score for an assignment
     */
    public void calcAssignmentAvg() {
        if (scores.isEmpty()) {
            double avg = 0;
        }

        double sum = 0;

        for (int score : scores) {
            sum += score;
        }

        double average = sum / scores.size();
    }

    /**
     * generates random scores for all students in an assignment depending on
     * a random value.
     */
    public void generateRandomScore() {
        Random rand = new Random();

        int randomValue = rand.nextInt(0, 10);
        int randomScore;

        if (randomValue == 0) {
            randomScore = rand.nextInt(0, 60);

        } else if (randomValue == 1 || randomValue == 2) {
            randomScore = rand.nextInt(60, 70);

        } else if (randomValue == 3 || randomValue == 4) {
            randomScore = rand.nextInt(70, 80);

        } else if (randomValue == 5 || randomValue == 6 ||
                randomValue == 7 || randomValue == 8) {
            randomScore = rand.nextInt(80, 90);

        } else {
            randomScore = rand.nextInt(90, 100 + 1);

        }

        scores.add(randomScore);
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "assignmentId='" + assignmentId + '\'' +
                ", assignmentName='" + assignmentName + '\'' +
                ", weight=" + weight +
                '}';
    }
}
