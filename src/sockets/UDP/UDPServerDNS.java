package sockets.UDP;

import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Objects;

public class UDPServerDNS {
    private static DatagramSocket server = null;
    private final int port;

    public UDPServerDNS(int port) {
        this.port = port;
    }
    
    public void startServer() throws SocketException{
        if (Objects.isNull(server)){
            server = new DatagramSocket(port);
            System.out.println("Server DNS listening in the port "+this.port);
            System.out.println("Acess DNS UDP in localhost:"+this.port);
        }else{
            System.out.println("Server DNS listening in the port "+this.port);
        }
        
        byte[] requestData = new byte[1024];
        byte[] responseData = new byte[1024];
        
        
    }
}