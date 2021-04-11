package com.epam.web.extractor;

import com.epam.web.entities.User;

import java.util.Map;

public interface Extractor<T> {
    
    Map<String, String> extract(User user);
}
