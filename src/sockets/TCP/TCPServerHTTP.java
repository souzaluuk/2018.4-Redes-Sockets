package sockets.TCP;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;
import pages.PageHTML;

public class TCPServerHTTP {
    private static ServerSocket server = null;
    private final int port;

    public TCPServerHTTP(int port) {
        this.port = port;
    }
    
    public void startServer() throws IOException{
        if (Objects.isNull(server)){
            server = new ServerSocket(this.port); //New ServerSocket instance
            System.out.println("Server TCP listening in the port "+this.port);
            System.out.println("Acess in localhost:"+this.port);
            listen();
        }else{
            System.out.println("Server TCP listening in the port "+this.port);
        }
    }
    
    private void listen() throws IOException{
        while(true){
            new Response(server.accept()).start(); //Start responses Threads
        }
    }
    
    private class Response extends Thread{ //Extends for multi clients
        private final Socket client;
        private String clientRequest;

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
                DataOutputStream outClient =
                        new DataOutputStream(client.getOutputStream());
                
                /**Read line request
                 * e.g: GET /example.html HTTP/1.1
                */
                clientRequest = inClient.readLine();
                System.out.println("\nIP Client: "+client.getInetAddress());
                System.out.println("Request: "+clientRequest);
                
                PageHTML page = new PageHTML(clientRequest);
                //System.out.print(page.getPageHTML());
                outClient.writeBytes(page.getPageHTML());
                
                client.close();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }
}
