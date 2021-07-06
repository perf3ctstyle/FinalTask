package com.epam.web.validator;

import org.junit.Assert;
import org.junit.Test;

public class ApplicationInfoValidatorTest {

    private final ApplicationInfoValidator validator = new ApplicationInfoValidator();

    @Test
    public void testShouldValidateApplicationInfoWhenGivenStringWithNumberLessThanMaxScore() {
        String scoreString = "100";
        int maxScore = 200;

        boolean result = validator.validateScore(scoreString, maxScore);

        Assert.assertTrue(result);
    }

    @Test
    public void testShouldValidateApplicationInfoWhenGivenStringWithNumberBiggerThanMaxScore() {
        String scoreString = "300";
        int maxScore = 200;

        boolean result = validator.validateScore(scoreString, maxScore);

        Assert.assertFalse(result);
    }

    @Test
    public void testShouldValidateApplicationInfoWhenGivenStringWithNumberLessThanZero() {
        String scoreString = "-150";
        int maxScore = 100;

        boolean result = validator.validateScore(scoreString, maxScore);

        Assert.assertFalse(result);
    }

    @Test
    public void testShouldValidateApplicationInfoWhenGivenNullString() {
        String scoreString = null;
        int maxScore = 0;

        boolean result = validator.validateScore(scoreString, maxScore);

        Assert.assertFalse(result);
    }

    @Test
    public void testShouldValidateApplicationInfoWhenGivenStringWithLetters() {
        String scoreString = "23abc";
        int maxScore = 230;

        boolean result = validator.validateScore(scoreString, maxScore);

        Assert.assertFalse(result);
    }

    @Test
    public void testShouldValidateApplicationInfoWhenGivenEmptyString() {
        String scoreString = "";
        int maxScore = 150;

        boolean result = validator.validateScore(scoreString, maxScore);

        Assert.assertFalse(result);
    }
}
