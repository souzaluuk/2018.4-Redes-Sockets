import sockets.TCP.TCPServerHTTP;
import sockets.UDP.*;

public class Main {
    private static void printErro(){        
        System.out.println("Use: java -jar Redes-Sockets.jar [option] [arg]");
        System.out.println("e.g:");
        System.out.println("     java -jar Redes-Sockets.jar tcp_server [port]");
        System.out.println("     java -jar Redes-Sockets.jar udp_server [port]");
        System.out.println("     java -jar Redes-Sockets.jar udp_client [port] [domain]");
    }
    public static void main(String[] args) throws Exception {
        //args = new String[]{"udp_server","8080"};
        
        if (args.length != 2){
            printErro();
        }else{
            int port = 80;
            try {
                port = Integer.parseInt(args[1]);
                switch(args[0]){
                    case "tcp_server":
                        TCPServerHTTP serverTCP = new TCPServerHTTP(port);
                        serverTCP.startServer();
                    break;
                    case "udp_server":
                        UDPServer serverUDP = new UDPServer(port);
                        serverUDP.startServer();
                    break;
                    case "udp_client":
                    break;
            }
            } catch (NumberFormatException e) {
                printErro();
                System.exit(0);
            }
        }
        
        System.out.println(args.length);
        System.exit(0);
        //System.out.println(args.length);
        /*UDPServerDNS serverUDP = new UDPServerDNS(8080);
        serverUDP.startServer();*/
    }
}
