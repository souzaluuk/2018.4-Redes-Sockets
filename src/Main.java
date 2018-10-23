
import sockets.TCPServerHTTP;


public class Main {
    public static void main(String[] args) throws Exception {
        TCPServerHTTP server = new TCPServerHTTP(8080);
        
        server.startServer();
    }
}
