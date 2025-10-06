/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package telas;
import javax.swing.*;
import java.awt.*;
import java.sql.*;

/**
 *
 * @author Marjori Barros
 */
public class EdicaoUsuario extends JDialog {
    private JTextField txtNome, txtCargo;
    private JComboBox<String> cbSetor;
    private JButton btnSalvar, btnCancelar;
    private int idUsuario;
    private String cargo;
    private String setor;
    
    public EdicaoUsuario(JFrame parent, int id, String nome, String email, String setor, String cargo) {
        super(parent, "Editar Usuário", true);
        this.idUsuario = id;
        
        setSize(400, 300);
        setLocationRelativeTo(parent);
        
        initComponents();
        
        // Preencher campos com dados atuais
        txtNome.setText(nome);
        txtCargo.setText(cargo);
        cbSetor.setSelectedItem(setor);
    }
    
    private void initComponents() {
        setLayout(new GridLayout(5, 2, 10, 10));
        
        add(new JLabel("Nome:"));
        txtNome = new JTextField();
        add(txtNome);
        
        add(new JLabel("Cargo:"));
        txtCargo = new JTextField();
        add(txtCargo);
        
        add(new JLabel("Setor:"));
        cbSetor = new JComboBox<>();
        cbSetor.addItem("Atendimento");
        cbSetor.addItem("Contábil");
        cbSetor.addItem("Declarações");
        cbSetor.addItem("Financeiro");
        cbSetor.addItem("Fiscal");
        cbSetor.addItem("RH");
        cbSetor.addItem("Societário");
        cbSetor.addItem("TI");
        add(cbSetor);
        
        btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> salvarEdicao());
        add(btnSalvar);
        
        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());
        add(btnCancelar);
    }
    
    private void salvarEdicao() {
        String nome = txtNome.getText().trim();
        String data = txtCargo.getText().trim();
        String categoria = (String) cbSetor.getSelectedItem();
        
        if (nome.isEmpty() || data.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Preencha todos os campos!",
                "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try (Connection conn = conectar()) {
            String sql = "UPDATE usuarios SET nome = ?, cargo = ?, setor = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, cargo);
            stmt.setString(3, setor);
            stmt.setInt(4, idUsuario);
            
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, 
                    "Usuário atualizado com sucesso!",
                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, 
                "Erro ao atualizar Usuário: " + e.getMessage(),
                "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private Connection conectar() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/integra_inicial";
        String user = "root";
        String password = "l5PV3IYH@";
        return DriverManager.getConnection(url, user, password);
    }
}   
    

