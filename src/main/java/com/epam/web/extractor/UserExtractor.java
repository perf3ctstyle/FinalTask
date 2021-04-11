package com.epam.web.extractor;

import com.epam.web.entities.User;
import com.epam.web.enums.UserFields;

import java.util.HashMap;
import java.util.Map;

public class UserExtractor implements Extractor<User> {

    @Override
    public Map<String, String> extract(User user) {
        HashMap<String, String> userValues = new HashMap<>();

        String idName = UserFields.ID.getName();
        userValues.put(idName, user.getId().toString());

        String loginName = UserFields.LOGIN.getName();
        userValues.put(loginName, user.getLogin());

        String passwordName = UserFields.PASSWORD.getName();
        userValues.put(passwordName, user.getPassword());

        String userRoleName = UserFields.USER_ROLE.getName();
        userValues.put(userRoleName, user.getUserRole().toString());
        
        return userValues;
    }
}
