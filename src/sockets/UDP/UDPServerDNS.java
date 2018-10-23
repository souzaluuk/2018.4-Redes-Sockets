package sockets.UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Objects;

public class UDPServerDNS {
    private static DatagramSocket server = null;
    private final int port;

    public UDPServerDNS(int port) {
        this.port = port;
    }
    
    public void startServer() throws Exception{
        if (Objects.isNull(server)){
            server = new DatagramSocket(port);
            System.out.println("Server DNS listening in the port "+this.port);
            System.out.println("Acess DNS UDP in localhost:"+this.port);
        }else{
            System.out.println("Server DNS listening in the port "+this.port);
        }
        
        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];
        
        while (true) {            
            DatagramPacket receivePacket =
                    new DatagramPacket(receiveData, receiveData.length);
            
            server.receive(receivePacket);
            String sentence = new String(receivePacket.getData());            
            InetAddress IPAdress = receivePacket.getAddress();            
            int port_packet = receivePacket.getPort();            
            String capitalizedSentence = sentence.toUpperCase();
            
            sendData = capitalizedSentence.getBytes();            
            DatagramPacket sendPacket = 
                    new DatagramPacket(sendData, sendData.length, IPAdress, port);
            
            server.send(sendPacket);
        }
    }
}