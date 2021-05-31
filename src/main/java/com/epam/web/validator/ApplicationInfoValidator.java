package com.epam.web.validator;

public class ApplicationInfoValidator {

    public boolean validateScore(String scoreString, int maxScore) {
        if (scoreString == null) {
            return false;
        }

        int score = 0;
        try {
            score = Integer.parseInt(scoreString);
        } catch (NumberFormatException e) {
            return false;
        }
        if (score<0) {
            return false;
        }

        return score <= maxScore;
    }
}
