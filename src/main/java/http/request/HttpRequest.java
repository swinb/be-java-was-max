package http.request;

import java.util.List;


public class HttpRequest {

    private List<String> headers;
    private String httpMethod;
    private String requestPath;

    public List<String> getHeaders() {
        return headers;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public String getRequestPath() {
        return requestPath;
    }

    public HttpRequest(List<String> headers, String httpMethod, String requestPath) {
        this.headers = headers;
        this.httpMethod = httpMethod;
        this.requestPath = requestPath;
    }
}
