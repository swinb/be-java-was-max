package http.request.util;

import java.io.BufferedReader;
import java.io.IOException;

public class InputUtil {
    public static String[] parsingSemicolonLine(String line){
        return line.replaceAll(" ", "").split(":");
    }

    public static String readRequestBody(BufferedReader br, int contentLength) throws IOException {
        char[] requestBody = new char[contentLength];
        br.read(requestBody,0,contentLength);
        return new String(requestBody);
    }
}
