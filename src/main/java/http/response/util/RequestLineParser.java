package http.response.util;

import db.Database;
import http.ContentType;
import http.request.HttpRequest;
import http.response.HttpResponse;
import model.User;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class RequestLineParser {

    private final HttpRequest httpRequest;

    public RequestLineParser(HttpRequest httpRequest) {
        this.httpRequest = httpRequest;
    }

    public String getResourcePath() {
        return PathGenerator.generate(httpRequest.getRequestPath().endsWith("html")) + httpRequest.getRequestPath();
    }

    public int getStatusCode() {
        if (httpRequest.getRequestPath().startsWith("user/create") || httpRequest.getHttpMethod().equals("POST")) {
            return 302;
        }
        return 200;
    }

    public List<String> getStatusLine(int statusCode) {
        List<String> statusLine = new ArrayList<>();
        if (statusCode == 302) {
            statusLine.add("HTTP/1.1 302 FOUND \r\n");
            statusLine.add("Location: /index.html");
            return statusLine;
        }
        statusLine.add("HTTP/1.1 200 OK \r\n");
        statusLine.add("Content-Type: " + ContentType.findByRequestType(httpRequest.getRequestPath()).getContentType() + "\r\n");
        System.out.println(ContentType.findByRequestType(httpRequest.getRequestPath()).getContentType());
        return statusLine;
    }

    public HttpResponse getHttpResponse() throws IOException {
        int statusCode = getStatusCode();
        if (statusCode != 200) {
            return new HttpResponse.httpResponseBuilder(statusCode, getStatusLine(statusCode)).build();
        }
        List<String> statusLine = getStatusLine(statusCode);
        byte[] body = generateByte(getResourcePath());
        statusLine.add("Content-Length: " + body.length + "\r\n");
        statusLine.add("\r\n");
        return new HttpResponse.httpResponseBuilder(statusCode,statusLine).setBody(body).build();

    }

    public byte[] generateByte(String path) throws IOException {
        return Files.readAllBytes(new File(path).toPath());
    }

    private User createUser(String[] data) {
        List<String> userData = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            String[] tokens = data[i].split("=");
            userData.add(tokens[1]);
        }
        User user = new User(userData.get(0), userData.get(1), userData.get(2), userData.get(3));
        Database.addUser(user);
        return user;
    }
}
