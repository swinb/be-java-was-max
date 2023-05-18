package http.request.util;

import http.request.HttpRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class HeaderParser {

    public static HttpRequest getHttpRequest(BufferedReader httpHeaders) throws IOException {
        String line;
        List<String> headers = new ArrayList<>();
        while (!((line = httpHeaders.readLine()).equals(""))) {
            headers.add(line);
        }

        String requestLine = headers.get(0);
        String[] tokens = requestLine.split(" ");
        return new HttpRequest(headers, tokens[0], tokens[1]);
    }
}
