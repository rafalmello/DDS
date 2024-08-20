package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GerenciamentoFuncionario extends  GerenciamentoPessoa{
    private List<Funcionario> funcionarios = new ArrayList<Funcionario>();

    Scanner scanner = new Scanner(System.in);

    public GerenciamentoFuncionario() {
    }
    public void insert(Funcionario funcionario){
        funcionarios.add(funcionario);
    }

    @Override
    public void insert() {

        System.out.println("Insira o CPF do funcionário: ");
        String CPF = scanner.nextLine();
        System.out.println("Insira o nome do funcionário: ");
        String nome = scanner.nextLine();
        System.out.println("Insira o endeço do funcionário: ");
        String endereco = scanner.nextLine();
        System.out.println("Insira o cargo do funcionário :");
        String cargo = scanner.nextLine();
        System.out.println("Insira o Salario do funcionário :");
        double salario = scanner.nextDouble();
        scanner.nextLine(); // Consumir a nova linha


        if (!CPF.isEmpty() && !nome.isEmpty() && !endereco.isEmpty() && !cargo.isEmpty() && salario > 0) {
            Funcionario funcionario = new Funcionario(CPF, nome, endereco, cargo, salario);



            funcionarios.add(funcionario);
            System.out.println("funcionário inserio com sucesso");

        }else {
            System.out.println("não foi possível inserir o funcionário");
        }


    }

    @Override
    public String update() {
        String mensagem = "";
        System.out.println("insira o cpf do funcionário: ");
        String cpf = scanner.nextLine();
        String resposta;
        for (Funcionario funcionario: funcionarios) {
            if (cpf.equalsIgnoreCase(funcionario.getCpf())){



                System.out.println("voce deseja atualizar o nome do funcionário? digite: 's' para sim e 'n'  para não");
                resposta = scanner.nextLine();
                if(resposta.equalsIgnoreCase("s")){
                    System.out.println("Insira o novo nome do funcionário: ");
                    String nome= scanner.nextLine();
                    funcionario.setNome(nome);
                }

                System.out.println("voce deseja atualizar o endereço do funcionário? digite: 's' para sim e 'n'  para não");
                resposta = scanner.nextLine();

                if(resposta.equalsIgnoreCase("s")){
                    System.out.println("Insira o novo endereço do funcionário: ");
                    String endereco= scanner.nextLine();
                    funcionario.setEndereco(endereco);
                }

                System.out.println("voce deseja atualizar o cargo do funcionário? digite: 's' para sim e 'n'  para não");
                resposta = scanner.nextLine();

                if(resposta.equalsIgnoreCase("s")){
                    System.out.println("Insira o novo cargo do funcionário: ");
                    String cargo= scanner.nextLine();
                    funcionario.setCargo(cargo);
                }

                System.out.println("voce deseja atualizar o salário do funcionário? digite: 's' para sim e 'n'  para não");
                resposta = scanner.nextLine();

                if(resposta.equalsIgnoreCase("s")){
                    System.out.println("Insira o novo salário do funcionário :");
                    double salario = Double.parseDouble(scanner.nextLine());
                    funcionario.setSalario(salario);
                }



                mensagem = "Funcionário atualizado com sucesso";



                return mensagem;
            }

        }
        mensagem = "Funcionário não encontrado";
        return mensagem;

    }

    @Override
    public String getBuscar() {
        String mensagem = "";
        System.out.println("insira o cpf do funcionário: ");
        String cpf = scanner.nextLine();
        String resposta;
        for (Funcionario funcionario: funcionarios) {
            if (cpf.equalsIgnoreCase(funcionario.getCpf())){
                return funcionario.toString();

            }

        }
        if (funcionarios.isEmpty()){
            resposta = "Sem Funcionários cadastrados";
            return  resposta;
        }

        mensagem = "Funcionário não encontrado";
        return mensagem;
    }

    @Override
    public String delete() {
        String mensagem = "";
        System.out.println("insira o cpf do funcionário: ");
        String cpf = scanner.nextLine();
        String resposta;
        for (Funcionario funcionario: funcionarios) {
            if (cpf.equalsIgnoreCase(funcionario.getCpf())){
                funcionarios.remove(funcionario);
                mensagem = "funcionário removido com sucesso";
                return mensagem;
            }

        }
        if (funcionarios.isEmpty()){
            resposta = "Sem Funcionários cadastrados";
            return  resposta;
        }

        mensagem = "Funcionário não encontrado";
        return mensagem;
    }

    @Override
    public String visualizarTodos() {
        String mensagem = "";
        if (funcionarios.isEmpty()){
            mensagem = "0";
            return mensagem;
        }

        for (Funcionario funcionario: funcionarios) {
            mensagem = mensagem + funcionario.toString() + "\n";
        }


        return mensagem;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }
}

