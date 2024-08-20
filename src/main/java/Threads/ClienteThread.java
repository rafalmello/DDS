package Threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClienteThread extends Thread{
    private Socket SocketCliente;

    public ClienteThread(Socket SocketCliente) {
        this.SocketCliente = SocketCliente;
    }

    @Override
    public void run(){
        try {
            InputStreamReader inputStreamReaderCliente = new InputStreamReader(SocketCliente.getInputStream());
            BufferedReader bufferedReaderCliente = new BufferedReader(inputStreamReaderCliente);
            String mensageReceived;

            //loop para continuar lendo as mensagens que o servidor envia
            while ((mensageReceived = bufferedReaderCliente.readLine()) != null){
                System.out.println(mensageReceived);
            }

        } catch (IOException ioExceptionCliente) {
            ioExceptionCliente.printStackTrace();
        }finally {
            try {
                SocketCliente.close();
            } catch (IOException ClienteioException) {
                ClienteioException.printStackTrace();
            }
        }
    }
}
