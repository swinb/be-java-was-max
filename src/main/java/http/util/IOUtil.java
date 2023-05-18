package http.util;

import java.io.BufferedReader;
import java.io.IOException;

public class IOUtil {
    public static String[] splitBySemicolon(String line) {
        return line.replaceAll(" ", "").split(":");
    }

    public static String[] splitByAmpersand(String line) {
        return line.split("&");
    }

    public static String[] splitByEqualSign(String line) {
        return line.split("=");

    }
    public static String readRequestBody(BufferedReader br, int contentLength) throws IOException {
        char[] requestBody = new char[contentLength];
        br.read(requestBody, 0, contentLength);
        return new String(requestBody);
    }


}

