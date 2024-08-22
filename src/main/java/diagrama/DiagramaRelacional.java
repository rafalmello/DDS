/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package diagrama;

import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DiagramaRelacional {

    public static void main(String[] args) {
        InformacoesBanco informacoes = new InformacoesBanco();
      
        String nomeArquivo = "output.dot";
        
        //gerando um documento DOT com as informações do banco de dados
       try (FileWriter fileWriter = new FileWriter(nomeArquivo)) {
            fileWriter.write(informacoes.construirDocumento());
            System.out.println("Código DOT gerado no arquivo " + nomeArquivo);
        } catch (IOException ex) {
            Logger.getLogger(DiagramaRelacional.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       //abrir cmd onde o arquivo está localizado e executar o comando: dot -Tpng -o meu_grafo.png output.dot
       //imagem com o diagrama relacional será gerado
       //necessario ter baixado e instalado o graphviz

    }
}
