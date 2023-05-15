package webserver;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.HeaderParser;

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
            BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String firstHeaderLine = br.readLine();
            String path = HeaderParser.getURL(firstHeaderLine);
            String resourcePath = HeaderParser.getResourcePath(path);
            String contentType = HeaderParser.getContentType(path);
            logger.debug("Client Request = " + path);
            while (!firstHeaderLine.equals("")) {
                logger.debug(firstHeaderLine);
                firstHeaderLine = br.readLine();
            }
            if(HeaderParser.checkRequestType(path)){
                resourcePath = "/templates";
                path = "/index.html";
            }
            DataOutputStream dos = new DataOutputStream(out);
            byte[] body = Files.readAllBytes(new File("./src/main/resources" +resourcePath + path).toPath());
            response200Header(dos, body.length,contentType);
            responseBody(dos, body);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private void response200Header(DataOutputStream dos, int lengthOfBodyContent,String contentType) {
        try {
            dos.writeBytes("HTTP/1.1 200 OK \r\n");
            dos.writeBytes("Content-Type: "+contentType+"\r\n");
            dos.writeBytes("Content-Length: " + lengthOfBodyContent + "\r\n");
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
