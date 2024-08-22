package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GerenciamentoGerente extends GerenciamentoPessoa{

    private static List<Gerente> gerentes = new ArrayList<Gerente>();

    Scanner scanner = new Scanner(System.in);

    public GerenciamentoGerente() {
    }

    public static List<Gerente> getGerentes() {
        return gerentes;
    }

    public static void setGerentes(List<Gerente> gerentes) {
        GerenciamentoGerente.gerentes = gerentes;
    }

    public void insert(Gerente gerente){
        gerentes.add(gerente);
    }
    @Override
    public void insert() {

        System.out.println("Insira o CPF do gerente: ");
        String CPF = scanner.nextLine();
        System.out.println("Insira o nome do gerente: ");
        String nome = scanner.nextLine();
        System.out.println("Insira o endeço do gerente: ");
        String endereco = scanner.nextLine();
        System.out.println("Insira o cargo do gerente :");
        String cargo = scanner.nextLine();
        System.out.println("Insira o Salario do gerente :");
        double salario = scanner.nextDouble();
        System.out.println("Insira a quantidade de funcionários do gerente :");
        int quantSubordinados = scanner.nextInt();
        System.out.println("Insira o nome do setor do gerente :");
        String setor = scanner.nextLine();




        if ( (CPF != null) && (nome != null) && (endereco != null) && (cargo != null) && (salario != 0 ) && (quantSubordinados != 0 )  ){
            Gerente gerente = new Gerente(CPF,nome,endereco,cargo,salario,quantSubordinados/*,setor*/);
            gerentes.add(gerente);

        }


    }

    @Override
    public String update() {
        String mensagem = "";
        System.out.println("insira o cpf do Gerente: ");
        String cpf = scanner.nextLine();
        String resposta;
        for (Gerente gerente: gerentes) {
            if (cpf.equalsIgnoreCase(gerente.getCpf())){



                System.out.println("voce deseja atualizar o nome do Gerente? digite: 's' para sim e 'n'  para não");
                resposta = scanner.nextLine();
                if(resposta.equalsIgnoreCase("s")){
                    System.out.println("Insira o novo nome do Gerente: ");
                    String nome= scanner.nextLine();
                    gerente.setNome(nome);
                }

                System.out.println("voce deseja atualizar o endereço do Gerente? digite: 's' para sim e 'n'  para não");
                resposta = scanner.nextLine();

                if(resposta.equalsIgnoreCase("s")){
                    System.out.println("Insira o novo endereço do Gerente: ");
                    String endereco= scanner.nextLine();
                    gerente.setEndereco(endereco);
                }

                System.out.println("voce deseja atualizar o cargo do Gerente? digite: 's' para sim e 'n'  para não");
                resposta = scanner.nextLine();

                if(resposta.equalsIgnoreCase("s")){
                    System.out.println("Insira o novo cargo do Gerente: ");
                    String cargo= scanner.nextLine();
                    gerente.setCargo(cargo);
                }

                System.out.println("voce deseja atualizar o salário do Gerente? digite: 's' para sim e 'n'  para não");
                resposta = scanner.nextLine();

                if(resposta.equalsIgnoreCase("s")){
                    System.out.println("Insira o novo salário do Gerente :");
                    double salario = Double.parseDouble(scanner.nextLine());
                    gerente.setSalario(salario);
                }

                System.out.println("voce deseja atualizar o número de funcionários do Gerente? digite: 's' para sim e 'n'  para não");
                resposta = scanner.nextLine();

                if(resposta.equalsIgnoreCase("s")){
                    System.out.println("Insira o novo número de funcionários do Gerente :");
                    int QuantSubordinados = scanner.nextInt();
                    gerente.setQuantSubordinados(QuantSubordinados);
                }

                System.out.println("voce deseja atualizar o setor do Gerente? digite: 's' para sim e 'n'  para não");
                if(resposta.equalsIgnoreCase("s")){
                    System.out.println("Insira o novo salário do Gerente :");
                    String setor = scanner.nextLine();
                    //gerente.setSetor(setor);
                }



                mensagem = "Gerente atualizado com sucesso";



                return mensagem;
            }

        }
        mensagem = "Gerente não encontrado";
        return mensagem;

    }

    @Override
    public String getBuscar() {
        String mensagem = "";
        System.out.println("insira o cpf do gerente: ");
        String cpf = scanner.nextLine();
        String resposta;
        for (Gerente gerente: gerentes) {
            if (cpf.equalsIgnoreCase(gerente.getCpf())){
                return gerente.toString();

            }

        }
        if (gerentes.isEmpty()){
            resposta = "Sem gerentes cadastrados";
            return  resposta;
        }

        mensagem = "Gerente não encontrado";
        return mensagem;
    }

    @Override
    public String delete() {
        String mensagem = "";

        if (gerentes.isEmpty()){
            mensagem = "Sem gerentes cadastrados";
            return  mensagem;
        }


        System.out.println(" Cpf dos gerentes");
        for (Gerente gerente: gerentes) {
            System.out.println(" O gerente se chama: "+gerente.getNome()+ " e o Cpf dele é:  " + gerente.getCpf() );
        }

        System.out.println("insira o cpf do gerente: ");
        String cpf = scanner.nextLine();


        for (Gerente gerente: gerentes) {
            if (cpf.equalsIgnoreCase(gerente.getCpf())){
                gerentes.remove(gerente);
                mensagem = "Gerente removido com sucesso";
                return mensagem;
            }
        }


        mensagem = "gerente não encontrado";
        return mensagem;
    }

    @Override
    public String visualizarTodos() {
        String mensagem = "";
        if (gerentes.isEmpty()){
            mensagem = "0";
            return mensagem;
        }

        for (Gerente gerente: gerentes) {
            mensagem = mensagem + gerente.toString() + "\n";
        }


        return mensagem;
    }




}
