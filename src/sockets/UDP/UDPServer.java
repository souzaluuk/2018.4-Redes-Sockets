package sockets.UDP;

import java.net.*;
import java.util.HashMap;
import java.util.Objects;

public class UDPServer {
    private static DatagramSocket server = null;
    private final int port;
    private final HashMap<String,String> names = new HashMap<>();

    public UDPServer(int port) {
        this.port = port;
    }
    
    private void initNames(){
        names.put("www.souzaluuk.org", "200.896.45.185");
        names.put("souzaluuk.org", "200.896.45.185");
        names.put("www.kellydosocorro.com", "200.196.84.462");
        names.put("kellydosocorro.com", "200.196.84.462");
        names.put("www.site.com.br", "200.746.32.127");
        names.put("site.com.br", "200.746.32.127");
        names.put("funcoeszz.net", "200.650.89.156");
        names.put("www.funcoeszz.net", "200.650.89.156");
    }
    
    public void startServer() throws Exception{
        if (Objects.isNull(server)){
            server = new DatagramSocket(this.port);
            System.out.println("Server UDP DNS listening in the port "+this.port);
            System.out.println("Acess in localhost:"+this.port);
            initNames();
            listen();
        }else{
            System.out.println("Server UDP DNS listening in the port "+this.port);
        }
    }
    
    private void listen() throws Exception{        
        while(true){
            byte[] receiveData = new byte[1024];
            byte[] sendData;
            
            DatagramPacket receivePacket =
                    new DatagramPacket(receiveData,receiveData.length);
            
            server.receive(receivePacket);
            
            String sentence = new String(receivePacket.getData());
            
            InetAddress IPAddress = receivePacket.getAddress();
            int port_packet = receivePacket.getPort();
            
            String capitalizedSentence = "Not found";
            
            String name_only = getNameOnly(sentence);
            System.out.println("Request: "+name_only);
            
            if (names.containsKey(name_only)){
                capitalizedSentence = names.get(name_only);
            }
            
            sendData = capitalizedSentence.getBytes();
            
            DatagramPacket sendPacket =
                    new DatagramPacket(sendData,
                            sendData.length, IPAddress, port_packet);
            
            System.out.println("Sending " + capitalizedSentence + "...\n");            
            server.send(sendPacket);
            
        }
    }
    
    private String getNameOnly(String sentence){
        return (sentence.split("\0").length == 1)?sentence.split("\0")[0]:"";
    }
}