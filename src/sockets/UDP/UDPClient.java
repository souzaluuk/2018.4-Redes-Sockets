package sockets.UDP;

import java.io.*;
import java.net.*;
 
public class UDPClient {
    private static DatagramSocket client = null;
    private final int port;
    private final String hostname_server;
    private final String domain_request;

    public UDPClient(int port, String hostname_server, String domain_request) {
        this.port = port;
        this.hostname_server = hostname_server;
        this.domain_request = domain_request;
    }
    
    public void startClient() throws Exception{
        client = new DatagramSocket();
        sendDomain();
    }
    
    private void sendDomain() throws Exception{
		InetAddress IPAddress = InetAddress.getByName(this.hostname_server);
 
		byte[] receiveData = new byte[1024];
                byte[] sendData;
		
		sendData = this.domain_request.getBytes();
                
		DatagramPacket sendPacket = 
                        new DatagramPacket(sendData,
                                sendData.length,IPAddress, this.port);
 
		System.out.println("Server UDP: " + hostname_server + ":" + port);
                
		client.send(sendPacket);
                
		DatagramPacket receivePacket = new DatagramPacket(receiveData,
				receiveData.length);
                
		client.receive(receivePacket);
                
		String modifiedSentence = new String(receivePacket.getData());
                
                System.out.println("Domain: " + domain_request);
		System.out.println("Address: " + modifiedSentence.split("\0")[0]);
		client.close();
    }
}