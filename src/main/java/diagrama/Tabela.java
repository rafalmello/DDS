/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package diagrama;

import java.util.ArrayList;
import java.util.List; 

public class Tabela {
    private String nome;
    private List<Coluna> colunas = new ArrayList<>();

    public Tabela(String nome) {
        this.nome = nome;
    }
    
    public void addColuna(String nome, String tipo) {
        Coluna coluna = new Coluna(nome, tipo);
        colunas.add(coluna);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Coluna> getColunas() {
        return colunas;
    }

    public void setColunas(List<Coluna> colunas) {
        this.colunas = colunas;
    }

    @Override
    public String toString() {
        String retorno = "  " + nome + " [shape=record, label=\"{"+nome+" | ";
        for (Coluna c : colunas){
            retorno += c.getNome() + " : " + c.getTipo()+ "\\l";
        }
         
        retorno += "}\"];" + "\n";
        
        return retorno;
    }
    
}
