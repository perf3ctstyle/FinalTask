package com.epam.web.validator;

public class CredentialsInfoValidator {

    private static final String VALID_NAME = "^[A-Z][a-z]+$";

    public boolean validateCredential(String credential) {
        if (credential == null) {
            return false;
        }
        return credential.matches(VALID_NAME);
    }
}
