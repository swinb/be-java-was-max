package http;


import java.util.Arrays;

public enum ContentType {
    HTML("html","text/html"),
    JPG("jpg", "image/jpeg"),
    PNG("png", "image/png"),
    CSS("css", "text/css"),
    JS("js", "text/javascript"),
    ICO("ico", "image/x-icon"),
    EMPTY("empty","empty");

    private final String requestType;
    private final String contentType;

    ContentType(String requestType, String contentType) {
        this.requestType = requestType;
        this.contentType = contentType;
    }

    public String getContentType() {
        return contentType;
    }

    public static ContentType findByRequestType(String requestPath){
        String[] tokens = requestPath.split("\\.");
        return Arrays.stream(ContentType.values())
                .filter(contentType -> contentType.requestType.equals(tokens[tokens.length-1]))
                .findAny()
                .orElse(EMPTY);
    }
}
