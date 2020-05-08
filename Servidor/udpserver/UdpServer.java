package udpserver;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UdpServer {

    public static void main(String[] args) throws SocketException, IOException {
        
        //cria o socket
        DatagramSocket serverSocket = new DatagramSocket(9876);
        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];
        
        while (true) {
            System.out.println("Aguardando pacotes...");
            
            //recebe dados
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            
            //imprime os dados recebidos
            String sentence = new String(receivePacket.getData());
            System.out.println("RECEBIDO: " + sentence);
            
            //pega o ip e a porta do cliente
            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();
            
            //cria a string de resposta
            String capitalizedSentence = "pacote recebido: " + sentence;
            sendData = capitalizedSentence.getBytes();
            
            //envia os dados de resposta
            DatagramPacket sendPacket
                    = new DatagramPacket(sendData, sendData.length, IPAddress, port);
            serverSocket.send(sendPacket);
        }

    }

}
