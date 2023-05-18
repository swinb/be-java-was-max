package http.response.util;

import db.Database;
import http.util.IOUtil;
import model.User;

import java.util.HashMap;
import java.util.Map;

public class UserCreator {
    public static User create(String requestBody) {
        String[] tokens = IOUtil.splitByAmpersand(requestBody);
        Map<String,String> createUserData = new HashMap<>();
        for (String token : tokens) {
            String[] data = IOUtil.splitByEqualSign(token);
            createUserData.put(data[0],data[1]);
        }
        User newUser = new User(createUserData.get("userId"),createUserData.get("password"),createUserData.get("name"),createUserData.get("email"));
        Database.addUser(newUser);
        System.out.println(newUser);
        return newUser;
    }


}
