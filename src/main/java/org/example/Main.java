package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    // Lista de PrintStream para todos os clientes conectados
    private static List<PrintStream> clientes = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        criarIntanacias();



        // Cria um Server Socket na porta 4000
        ServerSocket serverSocket = new ServerSocket(4000);
        System.out.println("Servidor está escutando a porta 4000");

        // Loop infinito para aceitar múltiplas conexões de clientes
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("Cliente conectou");

            // Cria uma nova thread para lidar com a comunicação com o cliente
            new Thread(() -> {
                try {
                    // Cria um PrintStream para enviar mensagens ao cliente
                    PrintStream saida = new PrintStream(socket.getOutputStream());

                    //Adiciona o PrintStream à lista de clientes
                    synchronized (clientes) {
                        clientes.add(saida);
                    }

                    // Cria um BufferedReader("Leitor bufferizado") para ler as mensagens do cliente
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    // Scanner que lê as mensagens do console do servidor
                    Scanner scanner = new Scanner(System.in);

                    // Sub-thread para ler as mensagens enviadas pelo cliente
                    Thread readMenssage = new Thread(() -> {
                        String menssageReceived;
                        try {
                            // Loop que continua lendo mensagens enquanto o cliente enviar
                            while ((menssageReceived = bufferedReader.readLine()) != null) {

                                // Envia a mensagem para todos os clientes
                                synchronized (clientes) {
                                    for (PrintStream cliente : clientes) {
                                        cliente.println(menssageReceived);
                                    }
                                }
                                System.out.println(menssageReceived);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            // Remove o PrintStream da lista de clientes quando a conexão é fechada
                            synchronized (clientes) {
                                clientes.remove(saida);
                            }
                        }
                    });

                    // Inicia a sub-thread que vai ler as mensagens
                    readMenssage.start();

                    // Loop principal que torna possível enviar mensagens do servidor
                    while (scanner.hasNextLine()) {
                        String menssageForSend = scanner.nextLine();

                        // Envia a mensagem para todos os clientes
                        synchronized (clientes) {
                            for (PrintStream cliente : clientes) {
                                cliente.println("Servidor: " + menssageForSend);
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }




    }






    private static void criarIntanacias() throws IOException {
        GerenciamentoGerente gerenciamentoGerente = new GerenciamentoGerente();
        GerenciamentoFuncionario gerenciamentoFuncionario = new GerenciamentoFuncionario();
        String nomes[] = {"Paulo", "João", "Pedro", "Fernando", " Rafael"};
        String endereco[] = {"bela vista", "progresso", "carvalho","lago norte","lago sul"};

        List<Gerente> gerentes = new ArrayList<Gerente>();
        List<Funcionario>funcionarios =new ArrayList<Funcionario>();

        Setor setor1 = new Setor(gerentes, funcionarios,  "Dev","Gerir os softwares da empresa");
        //Setor setor2 = new Setor(new ArrayList<Gerente>(), new ArrayList<Funcionario>(), "Vendas","Gerir as vendas da empresa");
        Setor.getSetores().put("Dev",setor1);

        for (int i = 0; i<4; i++){
            Gerente gerente = new Gerente( +i + 1000+ "", nomes[i],  endereco[i], "gerente" + 1, i* 2000, i*i /*, Setor setor*/) ;
            gerenciamentoGerente.insert(gerente);

            setor1.associarGerente(gerente);
        }

        for (int i = 0; i<19; i++){
            int numInstancia = i;

            if (numInstancia >= 4) {
                numInstancia = 1;}
            Funcionario funcionario   = new Funcionario( " "+i + 1000 + "", nomes[numInstancia]+ ""+i,  endereco[numInstancia], "Funcionário " + i, i* 1400/*, Setor setor*/) ;
            gerenciamentoFuncionario.insert(funcionario);

            setor1.associarFuncionario(funcionario);
        }





    }

}

