package org.example;

import Threads.GerenteThread;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class Gerente extends Pessoa{
    private String cargo;
    private double salario;
    private int quantSubordinados;

    private Setor setor;





    public Gerente(String cpf, String nome, String endereco, String cargo, double salario, int quantSubordinados/*, Setor setor*/) {
        super(cpf, nome, endereco);
        this.cargo = cargo;
        this.salario = salario;
        //this.setor = setor;
        this.quantSubordinados = quantSubordinados;
    }



    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public int getQuantSubordinados() {
        return quantSubordinados;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public void setQuantSubordinados(int quantSubordinados) {
        this.quantSubordinados = quantSubordinados;
    }



    public static void  chatGerente() throws IOException {
        Socket socketGerente  = new Socket("localhost",4000);
        Scanner scanner = new Scanner(System.in);

        GerenteThread gerenteThread = new GerenteThread(socketGerente);
        gerenteThread.start();

        PrintStream exitGerente = new PrintStream(socketGerente.getOutputStream());
        System.out.println("Gerente Connected for the servidor. You can start send message");

        while (scanner.hasNext()){
            String  messageForSend = scanner.nextLine();
            exitGerente.println("Gerente: " + messageForSend);
        }

    }

    public static void main(String[] args) throws IOException {
        chatGerente();
    }

    @Override
    public String toString() {
        return "Gerente{" +
                "cargo='" + cargo + '\'' +
                ", salario= " + salario +
                ", quantSubordinados= " + quantSubordinados +
                ", setor= " + setor + '\'' +
                '}';
    }
}
