package sockets.UDP;

import java.io.*;
import java.net.*;
 
public class UDPClient {
	public static void main(String args[]) throws Exception {
		BufferedReader inFromUser = 
                        new BufferedReader(new InputStreamReader(System.in));
 
		DatagramSocket clientSocket = new DatagramSocket();
 
		String servidor = "127.0.0.1";
		int porta = 8080;
 
		InetAddress IPAddress = InetAddress.getByName(servidor);
 
		byte[] sendData = new byte[1024];
		byte[] receiveData = new byte[1024];
 
		System.out.println("Digite o texto a ser enviado ao servidor: ");
		String sentence = inFromUser.readLine();
		sendData = sentence.getBytes();
		DatagramPacket sendPacket = new DatagramPacket(sendData,
				sendData.length, IPAddress, porta);
 
		System.out.println("Enviando pacote UDP para " + servidor + ":" + porta);
		clientSocket.send(sendPacket);
 
		DatagramPacket receivePacket = new DatagramPacket(receiveData,
				receiveData.length);
 
		clientSocket.receive(receivePacket);
		System.out.println("Pacote UDP recebido...");
 
		String modifiedSentence = new String(receivePacket.getData());
 
		System.out.println("Texto recebido do servidor:" + modifiedSentence);
		clientSocket.close();
		System.out.println("Socket cliente fechado!");
	}
}