package com.example.DiningReview;

import com.example.DiningReview.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class jacksonConverter {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        User testUser = new User("naeblessed", "qeeqg", "cityso", "4th", 32523, true, false, false);
        String jsonString = objectMapper.writeValueAsString(testUser);
        System.out.println(jsonString);
    }
}
