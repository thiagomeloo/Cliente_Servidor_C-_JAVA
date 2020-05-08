package tcpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {

    public static void main(String args[]) {
        try {
            
            //cria o servidor socket
            ServerSocket srvSocket = new ServerSocket(4000);
            Socket socket;
            while (true) {
                System.out.println("Aguardando conexao...");
                socket = srvSocket.accept(); //espera ate um cliente se conectar
                
                //imprime endereço e porta do cliente
                System.out.println("cliente " + socket.getInetAddress().getHostAddress() + ":" + socket.getPort() + " conectado");
                
                //recebe os dados que o cliente enviou
                InputStream ex = socket.getInputStream();

                //cria uma saida para responder para o cliente
                PrintStream saidaServidor = new PrintStream(socket.getOutputStream());
                
                //responde para o cliente
                saidaServidor.println("dados recebido com sucesso");

                //ler o que o cliente enviou, nesse caso foi so texto/String para teste
                BufferedReader reader = new BufferedReader(new InputStreamReader(ex));
                StringBuffer sb = new StringBuffer();
                String str;
                while ((str = reader.readLine()) != null) {
                    sb.append(str);
                }
                
                //imprime o texto 
                System.out.println(sb.toString());

                //fecha a conexao com o cliente
                socket.close();
            }

        } catch (IOException e) {
           
            e.printStackTrace();
        }

    }

}
