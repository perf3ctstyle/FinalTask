package com.epam.web.extractor;

import com.epam.web.entities.User;
import com.epam.web.enums.UserFields;

import java.util.HashMap;
import java.util.Map;

public class UserExtractor implements Extractor<User> {

    @Override
    public Map<String, String> extract(User user) {
        HashMap<String, String> userValues = new HashMap<>();

        String idName = UserFields.ID.toString();
        userValues.put(idName, user.getId().toString());

        String loginName = UserFields.LOGIN.toString();
        userValues.put(loginName, user.getLogin());

        String passwordName = UserFields.PASSWORD.toString();
        userValues.put(passwordName, user.getPassword());

        String userRoleName = UserFields.ROLE.toString();
        userValues.put(userRoleName, user.getRole().toString());
        
        return userValues;
    }
}
