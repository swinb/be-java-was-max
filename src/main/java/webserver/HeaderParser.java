package webserver;

public class HeaderParser {
    public HeaderParser(){
    }

    static public String getURL(String line){
        String[] tokens = line.split(" ");
        return tokens[1];
    }
}
