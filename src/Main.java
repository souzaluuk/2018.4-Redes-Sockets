import sockets.TCP.TCPServerHTTP;
import sockets.UDP.UDPServerDNS;

public class Main {
    public static void main(String[] args) throws Exception {
        //TCPServerHTTP serverTCP = new TCPServerHTTP(80);
        //server.startServer();
        
        UDPServerDNS serverUDP = new UDPServerDNS(8080);
        
        serverUDP.startServer();
    }
}
