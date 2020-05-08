

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 *
 * @author Thiago
 */
public class Cliente {
    
    public static void main(String[] args) {
    

        try {
            Socket iniciador = new Socket("127.0.0.1",4000);
            System.out.println("Conexão estabelecida!");
            Scanner teclado = new Scanner(System.in);
            PrintStream saidaCliente = new PrintStream(iniciador.getOutputStream());
            
            
            //botei aqui
            InputStream ex = iniciador.getInputStream();
            Scanner lerDoSocketServidor = new Scanner(ex);
            
            while(teclado.hasNextLine()){
                
                saidaCliente.println(teclado.nextLine());
                System.out.println(lerDoSocketServidor.nextLine());
                
                
            }
        } catch (UnknownHostException  ex) {
            ex.printStackTrace();
        }catch (IOException ex) {
            ex.printStackTrace();
        }

        
        
    }
    
}