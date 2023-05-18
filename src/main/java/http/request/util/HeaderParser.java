package http.request.util;

import http.request.HttpRequest;
import http.util.IOUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class HeaderParser {

    public static HttpRequest getHttpRequest(BufferedReader httpHeaders) throws IOException {
        String requestLine = httpHeaders.readLine();
        String headersLine;
        Map<String, String> headers = new HashMap<>();
        String body = null;

        while (!((headersLine = httpHeaders.readLine()).equals(""))) {
            String[] tokens = IOUtil.splitBySemicolon(headersLine);
            headers.put(tokens[0], tokens[1]);
        }
        if(headers.containsKey("Content-Length")){
            body = IOUtil.readRequestBody(httpHeaders, Integer.parseInt(headers.get("Content-Length")));
        }

        String[] tokens = requestLine.split(" ");

        return new HttpRequest.HttpRequestBuilder(headers,tokens[0],tokens[1],body).build();
    }
}
