package http.response;

import java.util.List;


public class HttpResponse {

    int statusCode;
    List<String> statusLine;
    byte[] body;

    public HttpResponse(httpResponseBuilder httpResponseBuilder) {
        this.statusCode = httpResponseBuilder.statusCode;
        this.statusLine = httpResponseBuilder.statusLine;
        this.body = httpResponseBuilder.body;
    }


    public List<String> getStatusLine() {
        return statusLine;
    }

    public byte[] getBody() {
        return body;
    }

    public static class httpResponseBuilder {
        private int statusCode;
        private List<String> statusLine;

        private byte[] body = null;

        public httpResponseBuilder(int statusCode, List<String> statusLine) {
            this.statusCode = statusCode;
            this.statusLine = statusLine;
        }

        public httpResponseBuilder setBody(byte[] body) {
            this.body = body;
            return this;
        }

        public HttpResponse build() {
            return new HttpResponse(this);
        }
    }

}
