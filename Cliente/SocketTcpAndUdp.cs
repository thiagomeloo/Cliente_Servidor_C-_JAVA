using System.Collections;
using System.Collections.Generic;
using UnityEngine;

using System;
using System.Text;
using System.IO;
using System.Net;
using System.Net.Sockets;

public class SocketTcpAndUdp : MonoBehaviour {


    private static void testeUDP(string server, int port, string texto)
    {
        //converte a string em bytes
        byte[] data = Encoding.ASCII.GetBytes(texto);

        try
        {
            //cria a conexao e conecta ao servidor
            UdpClient cli = new UdpClient();
            IPEndPoint endP = new IPEndPoint(IPAddress.Parse(server), port);
            cli.Connect(endP);

            //envia dados para o servidor
            cli.Send(data, data.Length);

            // recebe a resposta do servidor
            byte[] receiveBytes = cli.Receive(ref endP);
            string returnData = Encoding.ASCII.GetString(receiveBytes);


            Debug.Log(returnData);


        }
        catch (Exception ex)
        {
            Debug.Log(ex.Message);
        }
    }

    private static void testeTCP(string server, int port, string texto)
    {
        // buffer para receber os dados
        byte[] bytes = new byte[1024];
        try
        {

            IPHostEntry ipHost = Dns.Resolve(server);
            
            IPAddress ipAddr = ipHost.AddressList[0];
            
            IPEndPoint ipEndPoint = new IPEndPoint(ipAddr, port);

            Socket sender = new Socket(ipEndPoint.AddressFamily,SocketType.Stream, ProtocolType.Tcp);
            sender.Connect(ipEndPoint);

            //ver o ip e porta que foi conectado
            Debug.Log(sender.RemoteEndPoint.ToString());

            //converte meu texto em bytes pode converter qualquer coisa, usei string como exemplo
            byte[] msg = Encoding.ASCII.GetBytes(texto);

            // Envia os dados para o servidor 
            int bytesSent = sender.Send(msg);

            // Recebe resposta do servidor, pega a qntd e joga no buffer
            int bytesRec = sender.Receive(bytes);

            Debug.Log(Encoding.ASCII.GetString(bytes, 0, bytesRec));

            //fecha o socket
            sender.Shutdown(SocketShutdown.Both);
            sender.Close();
        }
        catch (Exception e)
        {
            Debug.Log(e.StackTrace);
            //Debug.Log(e.Message);

        }

    }

    // Use this for initialization
    void Start()
    {

        testeTCP("127.0.0.1", 4000,"ola mundo");

        //testeUDP("127.0.0.1", 9876, "testeUDP");
    }


    // Update is called once per frame
    void Update () {
		
	}
}
