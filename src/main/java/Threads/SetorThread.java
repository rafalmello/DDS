package Threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class SetorThread extends Thread{
    private Socket socketSetor;

    public SetorThread(Socket socketSetor) {
        this.socketSetor = socketSetor;
    }

    @Override
    public  void run(){
        try {
            InputStreamReader inputStreamReaderSetor = new InputStreamReader(socketSetor.getInputStream());
            BufferedReader bufferedReaderSetor = new BufferedReader(inputStreamReaderSetor);
            String messageReceivedSetor;

            while ((messageReceivedSetor = bufferedReaderSetor.readLine()) != null){
                System.out.println(messageReceivedSetor);
            }
        } catch (IOException ioExceptionSetor) {
            ioExceptionSetor.printStackTrace();
        }
    }

}
