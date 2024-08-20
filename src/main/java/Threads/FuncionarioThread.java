package Threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class FuncionarioThread extends Thread{
    private Socket socketFuncionario;

    public FuncionarioThread(Socket socketFuncionario) {
        this.socketFuncionario = socketFuncionario;
    }

    @Override
    public  void run(){
        try {
            InputStreamReader inputStreamReaderFuncionario = new InputStreamReader(socketFuncionario.getInputStream());
            BufferedReader bufferedReaderFuncionario = new BufferedReader(inputStreamReaderFuncionario);
            String messageReceivedFuncionario;

            while ((messageReceivedFuncionario = bufferedReaderFuncionario.readLine()) != null){
                System.out.println(messageReceivedFuncionario);
            }
        } catch (IOException ioExceptionFuncionario) {
            ioExceptionFuncionario.printStackTrace();
        }
    }
}
