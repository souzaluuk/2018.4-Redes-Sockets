import sockets.TCP.TCPServerHTTP;
import sockets.UDP.UDPClientDNS;
import sockets.UDP.UDPServerDNS;

public class Main {
    public static void main(String[] args) throws Exception {
        TCPServerHTTP serverTCP = new TCPServerHTTP(8080);
        serverTCP.startServer();
        //System.out.println(args.length);
        /*UDPServerDNS serverUDP = new UDPServerDNS(8080);
        serverUDP.startServer();*/
    }
}
