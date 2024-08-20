package org.example;

import Threads.ClienteThread;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    private Long id;
    private String name;
    private String email;
    private String number;

    public Cliente(Long id, String name, String email, String number) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", number='" + number + '\'' +
                '}';
    }

    public static   void  chatCliente()throws IOException {
        Socket socketCliente = new Socket("localhost",4000);
        Scanner scanner = new Scanner(System.in);

        ClienteThread clienteThread = new ClienteThread(socketCliente);
        clienteThread.start();

        PrintStream exitCliente = new PrintStream(socketCliente.getOutputStream());
        System.out.println("Connected for the servidor. Cliente can start send message");

        while (scanner.hasNextLine()){
            String messageForSend = scanner.nextLine();
            exitCliente.println("Cliente: " + messageForSend);
        }
        exitCliente.close();
        scanner.close();
        socketCliente.close();

    }

    public static void main(String[] args) throws IOException {
        chatCliente();
    }

}

