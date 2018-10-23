package pages;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

//request: GET /example.html HTTP/1.1
public class PageHTML{
    private final String sp = System.getProperty("file.separator");
    private final String user_dir = System.getProperty("user.dir");
    private final String DIR_PAGES = user_dir+sp+"src"+sp+"pages"+sp+"html";
    private final String request;

    public PageHTML(String request) {
        this.request = this.getURL(request);
    }
    
    public String getPageHTML() throws FileNotFoundException{
        File file = new File(DIR_PAGES+getNameFile());
        StringBuilder header_http = new StringBuilder();
        StringBuilder data = new StringBuilder();
        
        if (file.isDirectory()){
            file = new File(DIR_PAGES+getNameFile()+"index.html");
        }
        if (file.exists() && file.isFile()){
            data = getTextFile(file);
            header_http.append("HTTP/1.1 200 OK\n")
                    .append("Content-Length: ").append(header_http.length()-1).append('\n')
                    .append("Content-Type: text/html\n")
                    .append("Server: Kelly & Lucas\\n")
                    .append("\n");
            
            return header_http.toString();
        }else{
            header_http.append("HTTP/1.1 404 Not Found\n")
                    //.append("Connection: Keep-Alive\n")
                    .append("Content-Length: 0\n")
                    //.append("Content-Type: text/html\n")
                    //.append("Date: ").append(format_date.format(current_date)).append('\n')
                    //.append("Keep-Alive: timeout=3, max=1000\n")
                    //.append("Last-Modified: ").append(file.lastModified()).append('\n')
                    .append("Server: Kelly & Lucas\n")
                    .append("\n");
            
            return header_http.toString();
        }
    }
    
    private StringBuilder getTextFile(File file) throws FileNotFoundException{
        BufferedReader buffer_file = new BufferedReader(new FileReader(file));
        return null;
    }
    
    private String getURL(String request){
        return request.split(" ")[1].replace('/', '\\');
    }
    
    private String getNameFile(){
        return this.request.split("\\?")[0];
    }
    
    private HashMap<String,Integer> getArgs(){
        HashMap<String,Integer> args = new HashMap<>();
        String part_args = this.request.split("\\?")[1];
        
        String[] split_tmp;
        String name_arg;
        int value_arg;
        try {
            for (String str : part_args.split("&")) {
                if (correctArg(str)){
                    split_tmp = str.split("=");
                    name_arg = split_tmp[0];
                    value_arg = Integer.parseInt(split_tmp[1]);
                    
                    args.put(name_arg, value_arg);
                }
            }
            return args;
        } catch (NumberFormatException e) {
            System.out.println(e.toString());
            return null;
        }
    }
    
    private boolean correctArg(String arg){
        return arg.matches("num\\d=\\d*");
    }
}
