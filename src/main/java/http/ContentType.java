package http;


import java.util.Arrays;

public enum ContentType {
    HTML("html","text/html"),
    JPG("jpg", "text/html"),
    PNG("png", "text/html"),
    CSS("css", "text/html"),
    JS("js", "text/html"),
    ICO("ico", "text/html"),
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

    public static ContentType findByRequestType(String requestCode){
        return Arrays.stream(ContentType.values())
                .filter(contentType -> contentType.requestType.equals(requestCode))
                .findAny()
                .orElse(EMPTY);
    }
}
