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

    public void calcAssignmentAvg() {
        double sum = 0;

        for (int score : scores) {
            sum += score;
        }

        double average = sum / scores.size();
    }

    public void generateRandomScore() {
        Random rand = new Random();

        int randomValue = rand.nextInt(0, 10 + 1);
        int randomScore;

        if (randomValue == 0) {
            randomScore = rand.nextInt(0, 60 + 1);

        } else if (randomValue == 1 || randomValue == 2) {
            randomScore = rand.nextInt(60, 70 + 1);

        } else if (randomValue == 3 || randomValue == 4) {
            randomScore = rand.nextInt(70, 80 + 1);

        } else if (randomValue == 5 || randomValue == 6 ||
                randomValue == 7 || randomValue == 8) {
            randomScore = rand.nextInt(80, 90 + 1);

        } else {
            randomScore = rand.nextInt(90, 100 + 1);

        }

        scores.add(randomScore);
    }

    @Override
    public String toString() {
        return "Assignment{" +
                String.format("assignmentId='" + nextId++) + '\'' +
                ", assignmentName='" + assignmentName + '\'' +
                ", weight=" + weight +
                '}';
    }
}
