/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sistemaintegraincial;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

/**
 *
 * @author Marjori Barros
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("🔍 Consulta de Processos por Setor");
        try (Connection conn = ConexaoBD.conectar()) {
            String sql = "SELECT p.descricao, s.nome_setor FROM Processos p " +
                         "INNER JOIN Setores s ON p.setor_id = s.id_setor " +
                         "WHERE s.nome_setor = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "Fiscal"); // Exemplo: consultar processos do setor Fiscal
            
    ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                String processo = rs.getString("descricao");
                String setor = rs.getString("nome_setor");
                System.out.println("Setor: " + setor + " | Processo: " + processo);
            }
                        } catch (SQLException e) {
            System.out.println("Erro ao conectar ou consultar: " + e.getMessage());
        }
    }
}
