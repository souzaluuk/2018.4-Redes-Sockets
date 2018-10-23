package pages;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
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
    
    public String getPageHTML() throws Exception{
        File file = new File(DIR_PAGES+getNameFile());
        StringBuilder header_http = new StringBuilder();
        String data = "";
        
        if (file.isDirectory()){
            file = new File(DIR_PAGES+getNameFile()+"index.html");
        }
        if (file.exists() && file.isFile()){
            data = replacePage(file.getName(),getTextFile(file));
            
            header_http.append("HTTP/1.1 200 OK\n")
                    .append("Content-Length: ").append(data.length()-1).append('\n')
                    .append("Content-Type: text/html\n")
                    .append("Server: Kelly & Lucas\n")
                    .append("\n")
                    .append(data);
            
            return header_http.toString();
        }else{
            /*header_http.append("HTTP/1.1 404 Not Found\n")
                    .append("Connection: Keep-Alive\n")
                    .append("Content-Length: 0\n")
                    .append("Content-Type: text/html\n")
                    //.append("Date: ").append(format_date.format(current_date)).append('\n')
                    .append("Keep-Alive: timeout=3, max=1000\n")
                    .append("Last-Modified: ").append(file.lastModified()).append('\n')
                    .append("Server: Kelly & Lucas\n")
                    .append("\n");*/
            header_http.append("HTTP/1.1 404 Not Found\n")
                    .append("Connection: Keep-Alive\n")
                    .append("Content-Length: ").append(file.length()).append('\n')
                    .append("Content-Type: text/html\n")
                    .append("Date: ")//.append(format_date.format(current_date)).append('\n')
                    .append("Keep-Alive: timeout=3, max=1000\n")
                    .append("Last-Modified: ").append(file.lastModified()).append('\n')
                    .append("Server: Kelly & Lucas\n")
                    .append("\n");
            
            return header_http.toString();
        }
    }
    
    private String replacePage(String name_file, String text_file){
        HashMap<String,Integer> args = getArgs();
        
        try {
            if (name_file.equals("result.html") && args.size() > 1){
                int num1 = args.get("num1");
                int num2 = args.get("num2");
                int result = num1+num2;
                
                return text_file.replaceAll("\\{num1\\}", num1+"")
                         .replaceAll("\\{num2\\}", num2+"")
                         .replaceAll("\\{result\\}", result+"");
            }
        } catch (Exception e) {
            System.err.println(e.toString());
            System.err.println("Invalid arguments");
            return text_file;
        }
        
        return text_file;
    }
    
    private String getTextFile(File file) throws Exception{
        BufferedReader buffer_file = new BufferedReader(new FileReader(file));
        StringBuilder text = new StringBuilder();
        String line;
        
        while((line = buffer_file.readLine()) != null){
            text.append(line).append("\n");
        }
        
        buffer_file.close();
        return text.toString();
    }
    
    private String getURL(String request){
        return request.split(" ")[1].replace('/', '\\');
    }
    
    private String getNameFile(){
        return this.request.split("\\?")[0];
    }
    
    private HashMap<String,Integer> getArgs(){
        HashMap<String,Integer> args = new HashMap<>();
        String[] part_args = this.request.split("\\?");
        
        if (part_args.length < 2){
            return args;
        }
        
        String[] split_tmp;
        String name_arg;
        int value_arg;
        try {
            for (String str : part_args[1].split("&")) {
                System.out.println("ok");
                if (correctArg(str)){
                    split_tmp = str.split("=");
                    name_arg = split_tmp[0];
                    value_arg = Integer.parseInt(split_tmp[1]);
                    
                    args.put(name_arg, value_arg);
                }else{
                    return new HashMap<>();
                }
            }
            return args;
        } catch (NumberFormatException e) {
            System.out.println(e.toString());
            return args;
        }
    }
    
    private boolean correctArg(String arg){
        String[] split_arg = arg.split("\\=");
        return (split_arg.length > 1 && !split_arg[1].isEmpty());
    }
}
