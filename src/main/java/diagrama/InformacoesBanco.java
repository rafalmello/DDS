/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package diagrama;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InformacoesBanco {

    public List<Tabela> obterTabelas() {
        List<Tabela> tabelas = new ArrayList<>();

        try (Connection connection = Conexao.getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            //retorno das tabelas
            ResultSet tables = metaData.getTables(null, null, null, new String[]{"TABLE"});

            while (tables.next()) {
                String nomeTabela = tables.getString("TABLE_NAME");
                Tabela tabela = new Tabela(nomeTabela);

                //retorno das colunas
                ResultSet columns = metaData.getColumns(null, null, nomeTabela, null);
                while (columns.next()) {
                    String nomeColuna = columns.getString("COLUMN_NAME");
                    String tipo = columns.getString("TYPE_NAME");
                    tabela.addColuna(nomeColuna, tipo);
                }
                //salvando as tabelas na lista de tabelas
                tabelas.add(tabela);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tabelas;
    }

    public String gerarArestas() {
        String arestas = "";

        try (Connection connection = Conexao.getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();

            //retorno das chaves das tabelas
            ResultSet foreignKeys = metaData.getImportedKeys(null, null, null);
            while (foreignKeys.next()) {
                String pkTabela = foreignKeys.getString("PKTABLE_NAME");
                String fkTabela = foreignKeys.getString("FKTABLE_NAME");

                arestas += "  " + pkTabela + " -> " + fkTabela + ";\n";
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arestas;
    }

    public String construirDocumento() {
        InformacoesBanco informacoes = new InformacoesBanco();

        List<Tabela> tabelas = informacoes.obterTabelas();
        String output = "";
        
        output = "digraph DiagramaRelacionalVisual {\n";
        for (Tabela t : tabelas) {
            output += t.toString();
        }

        output += informacoes.gerarArestas();
        output += "}";

        return output;

    }
}
