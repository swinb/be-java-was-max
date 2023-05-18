package webserver;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.util.List;

import http.request.HttpRequest;
import http.response.HttpResponse;
import http.response.util.RequestLineParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import http.request.util.HeaderParser;

public class RequestHandler implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(RequestHandler.class);

    private Socket connection;

    public RequestHandler(Socket connectionSocket) {
        this.connection = connectionSocket;
    }

    public void run() {
        logger.debug("New Client Connect! Connected IP : {}, Port : {}", connection.getInetAddress(),
                connection.getPort());

        try (InputStream in = connection.getInputStream(); OutputStream out = connection.getOutputStream()) {
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            HttpRequest httpRequest = HeaderParser.getHttpRequest(br);
            RequestLineParser requestLineParser = new RequestLineParser(httpRequest);
            HttpResponse httpResponse = requestLineParser.getHttpResponse();

            for(String a : httpRequest.getHeaders()){
                logger.debug(a);
            }
            DataOutputStream dos = new DataOutputStream(out);
            logger.debug("Client Request = " + httpRequest.getRequestPath());
            byte[] body = httpResponse.getBody();
            response200Header(dos, httpResponse.getStatusLine());
            responseBody(dos, body);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private void response200Header(DataOutputStream dos, List<String> statusLine) {
        try {
            for(String line : statusLine){
                dos.writeBytes(line);
            }
            dos.writeBytes("\r\n");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private void responseBody(DataOutputStream dos, byte[] body) {
        try {
            dos.write(body, 0, body.length);
            dos.flush();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
