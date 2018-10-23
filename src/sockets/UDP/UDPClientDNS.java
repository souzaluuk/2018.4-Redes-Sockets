package sockets.UDP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClientDNS {    
    public static void main(String[] args) throws Exception{
        int port = 8080;
        BufferedReader inUser =
            new BufferedReader(
                    new InputStreamReader(System.in));
    
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName("localhost");
        System.out.println(IPAddress);
        
        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];
        String sentence = inUser.readLine();
        
        sendData = sentence.getBytes();
        
        DatagramPacket sendPacket = 
                new DatagramPacket(sendData, sendData.length, IPAddress, port);        
        clientSocket.send(sendPacket);
        
        DatagramPacket receivePacket =
                new DatagramPacket(receiveData, receiveData.length);        
        clientSocket.receive(receivePacket);
        
        String modifiedSentence = new String(receivePacket.getData());
        
        System.out.println("FROM SERVER: "+modifiedSentence);
        
        clientSocket.close();
    }
}
