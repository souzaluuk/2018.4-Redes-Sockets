package sockets;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class TCPServerHTTP {
    private static ServerSocket server = null;
    private final int port;

    public TCPServerHTTP(int port) {
        this.port = port;
    }
    
    public void startServer() throws IOException{
        if (Objects.isNull(server)){
            server = new ServerSocket(this.port); //New ServerSocket instance
            System.out.println("Server listening in the port "+this.port);
            listen();
        }else{
            System.out.println("Server listening in the port "+this.port);
        }
    }
    
    private void listen() throws IOException{
        while(true){
            new Response(server.accept()).start();
        }
    }
    
    private class Response extends Thread{ //Extends for multi clients
        private final Socket client;
        private String clientRequest;
        private StringBuilder clientResponse;

        public Response(Socket client) {
            this.client = client;
        }

        @Override
        public void run() { //Thread start function
            try {
                //Client input
                BufferedReader inClient =
                        new BufferedReader(
                                new InputStreamReader(
                                        client.getInputStream()));
                //Client output
                BufferedWriter outClient =
                        new BufferedWriter(
                                new OutputStreamWriter(
                                        client.getOutputStream()));
                /**Read line request
                 * e.g: GET /example.html HTTP/1.1
                */
                clientRequest = inClient.readLine();
                
                System.out.println("\nIP Client: "+client.getInetAddress());
                System.out.println("Request: "+clientRequest);
                
                //clientResponse = get
            } catch (Exception e) {
            }
        }
    }
}
