import pages.PageHTML;
import sockets.TCP.TCPServerHTTP;
import sockets.UDP.*;

public class Main {
    private static void printErro(){        
        System.out.println("Use: java -jar Redes-Sockets.jar [option] [arg]");
        System.out.println("e.g:");
        System.out.println("     java -jar Redes-Sockets.jar tcp_server [port]");
        System.out.println("     java -jar Redes-Sockets.jar udp_server [port]");
        System.out.println("     java -jar Redes-Sockets.jar udp_client [hostname_dns:port] [domain]");
    }
    public static void main(String[] args) throws Exception {
        //System.out.println(new PageHTML("Get /").getClass().getResource("html/index.html"));
        
        if (args.length != 2 && args.length != 3){
            printErro();
        }else{
            int port;
            try {
                switch(args[0]){
                    case "tcp_server":
                        port = Integer.parseInt(args[1]);
                        TCPServerHTTP serverTCP = new TCPServerHTTP(port);
                        serverTCP.startServer();
                    break;
                    case "udp_server":
                        port = Integer.parseInt(args[1]);
                        UDPServer serverUDP = new UDPServer(port);
                        serverUDP.startServer();
                    break;
                    case "udp_client":
                        String[] host_port = args[1].split(":");
                        UDPClient clientUDP = 
                                new UDPClient(Integer.parseInt(host_port[1]),
                                        host_port[0], args[2]);
                        clientUDP.startClient();
                    break;
                    default:
                        printErro();
            }
            } catch (Exception e) {
                printErro();
                System.out.println(e.toString());
                System.exit(0);
            }
        }
    }
}
