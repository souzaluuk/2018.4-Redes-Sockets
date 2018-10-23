
import pages.PageHTML;
import sockets.TCPServerHTTP;


public class Main {
    public static void main(String[] args) throws Exception {
        //System.out.println(new PageHTML("GET /index.html HTTP/1.1").getPageHTML());
        
        TCPServerHTTP server = new TCPServerHTTP(8080);
        
        server.startServer();
    }
}
