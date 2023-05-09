package util;

import db.Database;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class HeaderParser {
    public HeaderParser() {
    }

    static public String getURL(String line) {
        String[] tokens = line.split(" ");
        return tokens[1];
    }

    static public Boolean checkRequestType(String line) {
        String[] tokens = line.split("//?");
        if (tokens.length == 2) {
            createUser(tokens[1].split("&"));
            return true;
        }
        return false;
    }

    private static User createUser(String[] data) {
        List<String> userData = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            String[] tokens = data[i].split("=");
            userData.add(tokens[1]);
        }
        User user = new User(userData.get(0),userData.get(1),userData.get(2),userData.get(3));
        Database.addUser(user);
        return user;
    }
}
