package http;

import java.io.BufferedReader;
import java.util.List;
import java.util.stream.Collectors;

public class HttpRequest {
    String requestLine;
    List<String> httpHeaders;
    public HttpRequest(List<String> httpHeaders,String requestLine){
    }

    public static HttpRequest from(BufferedReader httpHeaders){
        List<String> httpHeadersList = httpHeaders.lines().collect(Collectors.toList());
        String requestLine = httpHeadersList.get(0);
        return new HttpRequest(httpHeadersList,requestLine);
    }

}
