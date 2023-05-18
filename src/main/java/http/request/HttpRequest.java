package http.request;

import java.util.Map;


public class HttpRequest {

    private Map<String, String> headers;
    private String httpMethod;
    private String requestPath;
    private String requestBody;

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public String getRequestPath() {
        return requestPath;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public HttpRequest(HttpRequestBuilder httpRequestBuilder) {
        this.headers = httpRequestBuilder.headers;
        this.httpMethod = httpRequestBuilder.httpMethod;
        this.requestPath = httpRequestBuilder.requestPath;
        this.requestBody = httpRequestBuilder.requestBody;
    }

    public static class HttpRequestBuilder {
        private Map<String, String> headers;
        private String httpMethod;
        private String requestPath;
        private String requestBody;

        public HttpRequestBuilder(Map<String, String> headers, String httpMethod, String requestPath, String requestBody) {
            this.headers = headers;
            this.httpMethod = httpMethod;
            this.requestPath = requestPath;
            this.requestBody = requestBody;
        }

        public HttpRequest build() {
            return new HttpRequest(this);
        }
    }
}
