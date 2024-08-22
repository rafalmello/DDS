package org.example;

import Threads.FuncionarioThread;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;


public class Funcionario extends Pessoa {

    private String cargo;
    private double salario;

    private static List<Funcionario>funcionarios;

    private Setor setor;

    Scanner scanner = new Scanner(System.in);

    public Funcionario(String cpf, String nome, String endereco, String cargo, double salario) {
        super(cpf, nome, endereco);
        this.cargo = cargo;
        this.salario = salario;

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



    public static void chatFuncionario() throws IOException {
        Socket socketFuncionario = new Socket("localhost",4000);
        Scanner scanner = new Scanner(System.in);

        FuncionarioThread funcionarioThread = new FuncionarioThread(socketFuncionario);
        funcionarioThread.start();

        PrintStream exit = new PrintStream(socketFuncionario.getOutputStream());
        System.out.println("Connected for the Servidor. Funcionário can start send message");

        while (scanner.hasNextLine()){
            String messageForSend = scanner.nextLine();
            exit.println("Funcionário: " + messageForSend);
        }

        exit.close();
        scanner.close();
        socketFuncionario.close();


    }
    public static void main(String[] args) throws IOException {
        chatFuncionario();
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "cpf='" + getCpf() + '\'' +
                ", nome='" + getNome() + '\'' +
                ", endereco='" + getEndereco() + '\'' +
                ", cargo='" + cargo + '\'' +
                ", salario=" + salario +
                '}';
    }



    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }
}

