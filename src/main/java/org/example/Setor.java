package org.example;

import Threads.SetorThread;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.*;

public class Setor {


    private static Map<String,Setor> setores = new HashMap<String,Setor>();

    private List<Gerente> gerentes = new ArrayList<Gerente>();
    private List<Funcionario> funcionarios = new ArrayList<Funcionario>();
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
        boolean adicionarPessoas = true;
        System.out.println("iniciar operações");

        GerenciamentoFuncionario gerenciamentoFuncionario1 = new GerenciamentoFuncionario();
        GerenciamentoGerente gerenciamentoGerente1 = new GerenciamentoGerente();

        if (adicionarPessoas){
            String nomes[] = {"Rafael","Fernando","Jorge","Marcelo","Luiz",
                    "Gabriel","Diego","Lucas","Davi","João","Zé","Gabriela Whestphal"};

            String nomesSetor[] = {"Setor de Software", "Setor Financeiro(Sonegador)","Setor Comercial" };

            String funcoesSetor[] = {"Desanvolver e a\n" +
                    "produzir sistemas de software de alta qualidade. Por alta qualidade, compreende-se softwares produzidos\n" +
                    "aplicando-se técnicas, métodos e ferramentas que permitam produzi-los como propriedades ergonômicas,\n" +
                    "funcionais, manuteníveis, seguros e de alto desempenho para as diversas áreas de negócio. ",
                    "Descobrir como papagar menos imposto " +
                    "legalmente dentro e fora do país e nos países os quais presta serviço",
                    "O setor de vendas, também conhecido como departamento comercial, é responsável por garantir" +
                            " que os produtos" + " ou serviços de uma empresa cheguem ao mercado e aos clientes-alvo. O setor de vendas atua" +
                            " desde a prospecção de clientes até o pós-venda," +
                    " com o objetivo de entender as necessidades dos consumidores e garantir satisfação." };

            Setor setorSoftware = new Setor(new ArrayList<Gerente>(),new ArrayList<Funcionario>(),nomesSetor[0] ,funcoesSetor[0]);
            Setor setorFinanceiro = new Setor(new ArrayList<Gerente>(),new ArrayList<Funcionario>(),nomesSetor[1] ,funcoesSetor[1]);
            Setor setorComercial = new Setor(new ArrayList<Gerente>(),new ArrayList<Funcionario>(),nomesSetor[2] ,funcoesSetor[2]);

            for (int i = 0; i<11; i++){
                int cont = i;
                Funcionario funcionario = new Funcionario("" + i*14139+(i*i*3), nomes[i] ,
                                "rua " + (i+2)*i*12+7, "desenvolvedor", i*1000 );
                gerenciamentoFuncionario1.insert(funcionario);

                Gerente gerente = new Gerente("" + (i+4)*13737+412,  nomes[i] ,
                        "rua " + i*17+3, "gerente" +i, i*1000, i*4 );



                gerenciamentoGerente1.insert(gerente);

                if (i == 10)break;// para teste


                    if((i+1)% 3 == 0) {
                        setorSoftware.associarFuncionario(funcionario);
                        setorSoftware.associarGerente(gerente);
                        gerente.setSetor(setorSoftware);
                        funcionario.setSetor(setorSoftware);

                    } else if ((i+1)% 2 == 0) {
                        setorFinanceiro.associarFuncionario(funcionario);
                        setorFinanceiro.associarGerente(gerente);

                        gerente.setSetor(setorFinanceiro);
                        funcionario.setSetor(setorFinanceiro);

                    }else if ((i+1)%1 == 0) {
                        setorComercial.associarFuncionario(funcionario);
                        setorComercial.associarGerente(gerente);

                        gerente.setSetor(setorComercial);
                        funcionario.setSetor(setorComercial);
                    }


            }
            setores.put("Setor de Software",setorSoftware);
            setores.put("Setor Financeiro(Sonegador)",setorFinanceiro);
            setores.put("Setor Comercial",setorComercial);
            adicionarPessoas = false;
        }

        Setor setor = new Setor();
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
                            "\n ADICIONAR GERENTE para adicionar um gerente," +
                            "\n REMOVER GERENTE para remover um gerente, " +
                            "\n ADICIONAR FUNCIONÁRIO para adicionar um funcionário e " +
                            "\n REMOVER FUNCIONÁRIO para remover um funcionário, " +
                            "\n CRIAR SETOR para criar um novo setor"+
                            "\n LIST para ver todas as pessoas do setor do setor");


                    operacao = scanner.nextLine();
                }


                String resultado = setor.processarOperacao(escolha, operacao);
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


    public  String processarOperacao(String escolha, String operacao) {
        if (escolha.equalsIgnoreCase("F")) {
            return processarFuncionario(operacao);
        } else if (escolha.equalsIgnoreCase("G")) {
            return processarGerente(operacao);
        } else if (escolha.equalsIgnoreCase("S")){
            return  processarSetor(operacao);
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
            case "CRIAR SETOR":
                return criarSetor();
            default:
                return "Operação desconhecida.";
        }

    }

    private static String criarSetor() {
        List<Gerente>gerentes = new ArrayList<Gerente>();
        List<Funcionario>funcionarios = new ArrayList<Funcionario>();
        System.out.println("Insira o nome do setor por favor");
        String nomeSetor = scanner.nextLine();
        System.out.println("Insira a função do setor por favor");
        String funcaoSetor = scanner.nextLine();
        String mensagem;

        Setor setor = new Setor(gerentes, funcionarios,nomeSetor,funcaoSetor);
        if (gerentes != null && funcionarios != null && !nomeSetor.isBlank()&& !funcaoSetor.isBlank()){
            setores.put(nomeSetor,setor);
            System.out.println("Setor Criado com Sucesso");
            mensagem = "Setor Criado com Sucesso";
            return mensagem;
        }
        mensagem = "Não foi posspivel criar o setor";
        return mensagem;
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
    public  String retornarSetorComPessoas() {

        System.out.println("insira o nome de um dos setores abaixo: \n");

        for (Map.Entry<String,Setor> setor : setores.entrySet()) {
            System.out.println(setor.getValue().getNomeSetor());
        }
        String nome = scanner.nextLine();
        Setor setor = setores.get(nome);

        if (setor == null){
            return "setor não encontrado";
        }
        String detalhesSetor = "";
        detalhesSetor = detalhesSetor + "Setor: " +setor.getNomeSetor() +"\n";
        detalhesSetor = detalhesSetor + "Função: "+setor.getFuncaoSetor() + "\n";

        if (!setor.gerentes.isEmpty()) {
            int numGerente = setor.gerentes.size();
            detalhesSetor = detalhesSetor + " o setor possui: "+ numGerente + " gerentes"+ "\n";
            detalhesSetor = detalhesSetor + "Gerentes: \n";
            for (Gerente gerente : setor.gerentes) {
                detalhesSetor = detalhesSetor + gerente.toString()+"\n";
            }
        }

        if (!setor.funcionarios.isEmpty()) {
            int numFuncionario = setor.funcionarios.size();
            System.out.println(numFuncionario);
            detalhesSetor = detalhesSetor + " o setor possui: "+ numFuncionario + " funcionários"+ "\n";
            detalhesSetor = detalhesSetor + "Gerentes: \n";
            for (Funcionario funcionario : setor.funcionarios) {
                detalhesSetor = detalhesSetor + funcionario.toString()+"\n";
            }
        } else {
            detalhesSetor = detalhesSetor +"Nenhum funcionário associado.\n";
        }

        return detalhesSetor.toString();
    }

    // Métodos para associar Gerente e Funcionários ao Setor
    public void associarGerente(Gerente gerente){
        this.gerentes.add(gerente);
    }
    public  String associarGerente() {
        System.out.println("estes são os nomes e os Cpfs dos gerentes");

        for (Gerente gerente: gerenciamentoGerente.getGerentes()) {
            if (gerente.getSetor() == null) { //somente gerente sem setor
                System.out.println("estes é nome: " + gerente.getNome()
                        + " e o Cpf: " + gerente.getCpf() + " do gerente");
            }
        }

        System.out.println("Insira o CPF do gerente: ");
        String Cpf = scanner.nextLine();
        GerenciamentoGerente gerenciamentoGerente = new GerenciamentoGerente();

        Setor setor = setorExistente();

        for (Gerente gerente: gerenciamentoGerente.getGerentes()) {
            if (Cpf.equalsIgnoreCase(gerente.getCpf())){

                if (gerente.getSetor() != null){
                    return "Este gerente já possui setor";
                }
                setor.gerentes.add(gerente);
                return "Gerente inserido com sucesso.";


            }
        }

        return "Não foi possível inserir o gerente. Por favor confira os dados";

    }
    public  String removerGerente() {
        System.out.println("Insira o CPF do gerente: ");
        String Cpf = scanner.nextLine();
        GerenciamentoGerente gerenciamentoGerente = new GerenciamentoGerente();

        Setor setor = setorExistente();

        System.out.println("Gerentes Cadastrados neste Setor: ");
        for (Gerente gerente: setor.gerentes) {
            System.out.println(gerente.getCpf());
        }


                            //setor.getGerentes()
        for (Gerente gerente: gerenciamentoGerente.getGerentes()) {
            if (Cpf.equalsIgnoreCase(gerente.getCpf())){
                setor.gerentes.remove(gerente);
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

        Setor setor = setorExistente();

        for (Funcionario funcionario : gerenciamentoFuncionario.getFuncionarios()) {
            if (Cpf.equalsIgnoreCase(funcionario.getCpf())) {

                if (funcionario.getSetor() != null){
                    return "Este funcionário já possui setor";
                }

                setor.funcionarios.add(funcionario);
                return "Funcionário inserido com sucesso.";

            }//
        }
        return "Não foi possível inserir o funcionário. Por favor confira os dados";

    }

    public String removerFuncionario() {
        System.out.println("Insira o CPF do funcionário: ");
        String Cpf = scanner.nextLine();
        GerenciamentoFuncionario gerenciamentoFuncionario = new GerenciamentoFuncionario();

        Setor setor = setorExistente();

        System.out.println("Funcionários Cadastrados neste Setor:  ");
        for (Funcionario funcionario: setor.funcionarios) {
            System.out.println(funcionario.getCpf());
        }

        for (Funcionario funcionario : gerenciamentoFuncionario.getFuncionarios()) {
            if (Cpf.equalsIgnoreCase(funcionario.getCpf())){
                setor.funcionarios.remove(funcionario);
                return "Funcionário removido com sucesso.";

            }
        }
        return "Não foi possível remover o funcionário. Por favor confira os dados";
    }

    // Por boas práticas: o código dentro deste método estava sendo repetido muitas vezez
    // Então eu transformei ele em um método e no lugar do código tem o método
    // Com isso o tamanho do código foi refurizo em torno de 24 linhas
    // tirei ele de todos os lugares
    public Setor setorExistente(){

        System.out.println("insira o nome de um dos setores abaixo: \n");

        for (Map.Entry<String,Setor> setor : setores.entrySet()) {
            System.out.println(setor.getValue().getNomeSetor());
        }

        String nome = scanner.nextLine();
        Setor setor = setores.get(nome);

        if (setor == null){
            System.out.println("este Setor não existe");
            return null;
        }
        return setor;
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
