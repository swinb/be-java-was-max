package http.response.util;

public class PathGenerator {
    private static final String DIRECTORY_STATIC = "./src/main/resources/static";
    private static final String DIRECTORY_TEMPLATE = "./src/main/resources/templates";

    public static String generate(boolean isTemplate) {
        if (isTemplate) {
            return DIRECTORY_TEMPLATE;
        }
        return DIRECTORY_STATIC;
    }
}
