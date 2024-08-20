package org.example;

import Threads.SetorThread;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.*;

public class Setor {


    private static Map<String,Setor> setores = new HashMap<String,Setor>();

    private List<Gerente> gerentes = new ArrayList<Gerente>();
    private List<Funcionario> funcionarios = new ArrayList<>();
    private String nomeSetor;
    private String funcaoSetor;


    static Scanner scanner = new Scanner(System.in);

    public Setor(List<Gerente> gerentes, List<Funcionario> funcionarios, String nomeSetor, String funcaoSetor) {
        this.gerentes = gerentes;
        this.funcionarios = funcionarios;
        this.nomeSetor = nomeSetor;
        this.funcaoSetor = funcaoSetor;
    }

    public Setor() {
    }

    // Gerenciamento
    private GerenciamentoGerente gerenciamentoGerente = new GerenciamentoGerente();
    private GerenciamentoFuncionario gerenciamentoFuncionario = new GerenciamentoFuncionario();

    public static void chatSetor() throws IOException {
        Socket socketSetor = new Socket("localhost", 4000);
        Scanner scanner = new Scanner(System.in);

        SetorThread setorThread = new SetorThread(socketSetor);
        setorThread.start();

        PrintStream exit = new PrintStream(socketSetor.getOutputStream());
        System.out.println("Connected for the Servidor. Setor can start sending messages");

        while (scanner.hasNextLine()) {
            String messageForSend = scanner.nextLine();

            if (messageForSend.equalsIgnoreCase("iniciar operações")) {
                System.out.println("Insira o 'G' para realizar as operações no gerente," +
                        " 'F' para realizar as operações no funcionário ou" +
                        " 'S' para realizar as operações setor ");
                String escolha = scanner.nextLine();
                String operacao = "";
                if (escolha.equalsIgnoreCase("F") || escolha.equalsIgnoreCase("G")){
                    System.out.println("Insira o tipo de operação que você deseja realizar. As operações são: " +
                            "INSERT para adicionar, " +
                            "UPDATE para atualizar, " +
                            "GET para buscar, " +
                            "DELETE para deletar, " +
                            "LIST para ver todos");


                     operacao = scanner.nextLine();
                }
                //"REMOVER GERENTE" / ADICIONAR FUNCIONÁRIO/REMOVER FUNCIONÁRIO"
                if (escolha.equalsIgnoreCase("S") ){
                    System.out.println("Insira o tipo de operação que você deseja realizar no setor. As operações são: " +
                            "\n ADICIONAR GERENTE para adicionar um gerente, \n" +
                            "\n REMOVER GERENTE para remover um gerente, " +
                            "\n ADICIONAR FUNCIONÁRIO para adicionar um funcionário e " +
                            "\n REMOVER FUNCIONÁRIO para remover um funcionário, " +
                            "\n LIST para ver todas as pessoas do setor do setor");


                    operacao = scanner.nextLine();
                }


                String resultado = processarOperacao(escolha, operacao);
                System.out.println(resultado); // Exibir resultado da operação no console
            }

            exit.println("Setor: " + messageForSend);
        }

        exit.close();
        scanner.close();
        socketSetor.close();
    }

    public static void main(String[] args) throws IOException {
        chatSetor();
    }


    private static String processarOperacao(String escolha, String operacao) {
        if (escolha.equalsIgnoreCase("F")) {
            return processarFuncionario(operacao);
        } else if (escolha.equalsIgnoreCase("G")) {
            return processarGerente(operacao);
        } else if (escolha.equalsIgnoreCase("S")){
            return  processarSetor1(operacao);
        } else {
            return "Opção inválida. Escolha 'F' para Funcionário, 'G' para Gerente ou 'S' para setor.";
        }
    }
   //teste de mudança
   private static String processarSetor1(String operacao) {
       // Obtém o nome do setor do usuário
       System.out.println("Insira o nome do setor:");
       String nomeSetor = scanner.nextLine();

       // Busca o setor no mapa de setores
       Setor setor = setores.get(nomeSetor);

       // Verifica se o setor existe antes de continuar
       if (setor == null) {
           return "Setor não encontrado.";
       }

       // Usa a instância correta do setor para realizar a operação
       String resultado = setor.processarSetor(operacao);
       return resultado;
   }

    /*private static String processarSetor1(String operacao) {
        Setor setor = new Setor();
        String operacao1  = setor.processarSetor( operacao);
        return operacao1;
    }*/


    private  String processarSetor(String operacao) {

        //associarGerente()
        switch (operacao.toUpperCase()) {
            case "ADICIONAR GERENTE":
                return associarGerente();
            case "REMOVER GERENTE":
                return removerGerente();
            case "ADICIONAR FUNCIONÁRIO":
                return associarFuncionario();
            case "REMOVER FUNCIONÁRIO":
                return removerFuncionario();
            case "LIST":
                return retornarSetorComPessoas();
            default:
                return "Operação desconhecida.";
        }

    }

    private static String processarFuncionario(String operacao) {
        GerenciamentoFuncionario gerenciamentoFuncionario = new GerenciamentoFuncionario();
        switch (operacao.toUpperCase()) {
            case "INSERT":
                gerenciamentoFuncionario.insert();
                return " ";
            case "UPDATE":
                return gerenciamentoFuncionario.update();
            case "GET":
                return gerenciamentoFuncionario.getBuscar();
            case "DELETE":
                return gerenciamentoFuncionario.delete();
            case "LIST":
                return gerenciamentoFuncionario.visualizarTodos();
            default:
                return "Operação desconhecida.";
        }
    }

    private static String processarGerente(String operacao) {
        GerenciamentoGerente gerenciamentoGerente = new GerenciamentoGerente();
        switch (operacao.toUpperCase()) {
            case "INSERT":
                gerenciamentoGerente.insert();
                return "Gerente inserido com sucesso.";
            case "UPDATE":
                return gerenciamentoGerente.update();
            case "GET":
                return gerenciamentoGerente.getBuscar();
            case "DELETE":
                return gerenciamentoGerente.delete();
            case "LIST":
                return gerenciamentoGerente.visualizarTodos();
            default:
                return "Operação desconhecida.";
        }
    }



    // Método para retornar Setor com os objetos relacionados
    public String retornarSetorComPessoas() {

        System.out.println("insira o nome do setor:");
        String nome = scanner.nextLine();
        Setor setor = setores.get(nome);

        StringBuilder detalhesSetor = new StringBuilder();
        detalhesSetor.append("Setor: ").append(nomeSetor).append("\n");
        detalhesSetor.append("Função: ").append(funcaoSetor).append("\n");

        if (!setor.gerentes.isEmpty()) {
            int numGerente = gerentes.size();
            System.out.println(numGerente);
            detalhesSetor.append("Gerentes: \n");
            for (Gerente gerente : gerentes) {
                detalhesSetor.append(gerente.toString()).append("\n");
            }
        }

        if (!setor.funcionarios.isEmpty()) {
            int numfuncionario = funcionarios.size();
            System.out.println(numfuncionario);
            detalhesSetor.append("Funcionários: \n");
            for (Funcionario funcionario : funcionarios) {
                detalhesSetor.append(funcionario.toString()).append("\n");
            }
        } else {
            detalhesSetor.append("Nenhum funcionário associado.\n");
        }

        return detalhesSetor.toString();
    }

    // Métodos para associar Gerente e Funcionários ao Setor
    public void associarGerente(Gerente gerente){
        this.gerentes.add(gerente);
    }
    public String associarGerente() {
        System.out.println("Insira o CPF do gerente: ");
        String Cpf = scanner.nextLine();
        GerenciamentoGerente gerenciamentoGerente = new GerenciamentoGerente();

        for (Gerente gerente: gerenciamentoGerente.getGerentes()) {
            if (Cpf.equalsIgnoreCase(gerente.getCpf())){
                this.gerentes.add(gerente);
                return "Gerente inserido com sucesso.";


            }
        }
        return "Não foi possível inserir o gerente. Por favor confira os dados";

    }
    public String removerGerente() {
        System.out.println("Insira o CPF do gerente: ");
        String Cpf = scanner.nextLine();
        GerenciamentoGerente gerenciamentoGerente = new GerenciamentoGerente();

        for (Gerente gerente: gerenciamentoGerente.getGerentes()) {
            if (Cpf.equalsIgnoreCase(gerente.getCpf())){
                this.gerentes.remove(gerente);
                return "gerente removido com sucesso.";
            }
        }
        return "Não foi possível remover o gerente. Por favor confira os dados";
    }

    public void associarFuncionario(Funcionario funcionario){
        this.funcionarios.add(funcionario);
    }

    public String associarFuncionario() {
        System.out.println("Insira o CPF do funcionário: ");
        String Cpf = scanner.nextLine();
        GerenciamentoFuncionario gerenciamentoFuncionario = new GerenciamentoFuncionario();

        for (Funcionario funcionario : gerenciamentoFuncionario.getFuncionarios()) {
            if (Cpf.equalsIgnoreCase(funcionario.getCpf())){
                this.funcionarios.add(funcionario);
                return "Funcionário inserido com sucesso.";

            }
        }
        return "Não foi possível inserir o funcionário. Por favor confira os dados";

    }

    public String removerFuncionario() {
        System.out.println("Insira o CPF do funcionário: ");
        String Cpf = scanner.nextLine();
        GerenciamentoFuncionario gerenciamentoFuncionario = new GerenciamentoFuncionario();

        for (Funcionario funcionario : gerenciamentoFuncionario.getFuncionarios()) {
            if (Cpf.equalsIgnoreCase(funcionario.getCpf())){
                this.funcionarios.remove(funcionario);
                return "Funcionário removido com sucesso.";

            }
        }
        return "Não foi possível remover o funcionário. Por favor confira os dados";
    }

    public List<Gerente> getGerentes() {
        return gerentes;
    }

    public void setGerentes(List<Gerente> gerentes) {
        this.gerentes = gerentes;
    }

    public String getNomeSetor() {
        return nomeSetor;
    }
    // Sincronização básica para evitar condições de corrida
    public synchronized static Map<String, Setor> getSetores() {
        return setores;
    }

    public static void setSetores(Map<String, Setor> setores) {
        Setor.setores = setores;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public String getFuncaoSetor() {
        return funcaoSetor;
    }

    public void setNomeSetor(String nomeSetor) {
        this.nomeSetor = nomeSetor;
    }

    public void setFuncaoSetor(String funcaoSetor) {
        this.funcaoSetor = funcaoSetor;
    }
}
