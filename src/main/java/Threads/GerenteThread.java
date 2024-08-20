package Threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class GerenteThread extends  Thread{

    private Socket socketGerente;

    public GerenteThread(Socket socketGerente) {
        this.socketGerente = socketGerente;
    }

    @Override
    public  void run(){
        try {
            InputStreamReader inputStreamReaderGerente = new InputStreamReader(socketGerente.getInputStream());
            BufferedReader bufferedReaderGerente = new BufferedReader(inputStreamReaderGerente);
            String messageReceivedGerente;

            while ((messageReceivedGerente = bufferedReaderGerente.readLine()) != null){
                System.out.println(messageReceivedGerente);
            }
        } catch (IOException ioExceptionGerente) {
            ioExceptionGerente.printStackTrace();
        }
    }
}
