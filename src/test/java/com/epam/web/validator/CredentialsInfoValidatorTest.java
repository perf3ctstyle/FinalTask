package com.epam.web.validator;

import org.junit.Assert;
import org.junit.Test;

public class CredentialsInfoValidatorTest {

    private final CredentialsInfoValidator validator = new CredentialsInfoValidator();

    private static final String CORRECT_NAME = "Alexey";
    private static final String INCORRECT_NAME = "alexey";
    private static final String EMPTY_STRING = "";
    private static final String INCORRECT_NAME_WITH_CAPITAL_LETTERS = "MARINA";
    private static final String INCORRECT_NAME_WITH_WHITESPACE_IN_BEGINNING = " Noname";
    private static final String INCORRECT_NAME_WITH_WHITESPACE_IN_MIDDLE = "No name";
    private static final String INCORRECT_NAME_WITH_WHITESPACE_IN_END = "Noname ";
    private static final String INCORRECT_NAME_WITH_SEMICOLON = "Alexander;";
    private static final String INCORRECT_NAME_WITH_NUMBERS = "Nikita12";

    @Test
    public void testShouldValidateCredentialWhenGivenCorrectString() {
        boolean result = validator.validateCredential(CORRECT_NAME);

        Assert.assertTrue(result);
    }

    @Test
    public void testShouldValidateCredentialWhenGivenIncorrectString() {
        boolean result = validator.validateCredential(INCORRECT_NAME);

        Assert.assertFalse(result);
    }

    @Test
    public void testShouldValidateCredentialWhenGivenNullString() {
        boolean result = validator.validateCredential(null);

        Assert.assertFalse(result);
    }

    @Test
    public void testShouldValidateCredentialWhenGivenEmptyString() {
        boolean result = validator.validateCredential(EMPTY_STRING);

        Assert.assertFalse(result);
    }

    @Test
    public void testShouldValidateCredentialWhenGivenIncorrectStringWithCapitalLetters() {
        boolean result = validator.validateCredential(INCORRECT_NAME_WITH_CAPITAL_LETTERS);
    }

    @Test
    public void testShouldValidateCredentialWhenGivenIncorrectStringWithSpaceInBeginning() {
        boolean result = validator.validateCredential(INCORRECT_NAME_WITH_WHITESPACE_IN_BEGINNING);

        Assert.assertFalse(result);
    }

    @Test
    public void testShouldValidateCredentialWhenGivenIncorrectStringWithSpaceInMiddle() {
        boolean result = validator.validateCredential(INCORRECT_NAME_WITH_WHITESPACE_IN_MIDDLE);

        Assert.assertFalse(result);
    }

    @Test
    public void testShouldValidateCredentialWhenGivenIncorrectStringWithSpaceInEnd() {
        boolean result = validator.validateCredential(INCORRECT_NAME_WITH_WHITESPACE_IN_END);

        Assert.assertFalse(result);
    }

    @Test
    public void testShouldValidateCredentialWhenGivenIncorrectStringWithSemicolon() {
        boolean result = validator.validateCredential(INCORRECT_NAME_WITH_SEMICOLON);

        Assert.assertFalse(result);
    }

    @Test
    public void testShouldValidateCredentialWhenGivenIncorrectStringWithNumbers() {
        boolean result = validator.validateCredential(INCORRECT_NAME_WITH_NUMBERS);

        Assert.assertFalse(result);
    }
}
