package org.example;

import java.util.List;

public class GerenciamentoCliente {
    private List<Cliente> clientes;

    public void adicionarCliente(String nome, String email, String telefone) {
        Cliente cliente = new Cliente(1L,nome,email,telefone);

        if (cliente != null && (nome != null)&&(email != null)&&(telefone != null) ){
            clientes.add(cliente);
        }
    }

    public void removerCliente(int id) {
        for (Cliente cliente:
                clientes) {
            if (cliente.getId() == id){
                clientes.remove(cliente);
            }

        }

    }

    public void atualizarCliente(int id, String nome, String email, String telefone) {
        for (Cliente cliente :
                clientes) {
            if (cliente.getId() == id) {
                //return cliente;
            }

            // Implementação para atualizar cliente
        }
    }

    public Cliente buscarCliente(Long id){
            Long useId = id;
            for (Cliente cliente:
                    clientes) {
                if (cliente.getId() == id){
                    return cliente;
                }

            }
            return null;

        }




    public void visualizarClientes(List<Cliente> clientes) {

        for (Cliente cliente: clientes) {
                System.out.println(cliente.toString());
        }

    }
}
