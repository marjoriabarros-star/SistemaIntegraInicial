/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package telas;

/**
 *
 * @author Marjori Barros
 */

import java.awt.Component;
import java.awt.Container;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.text.MaskFormatter;

public class TelaCadastroUsuario extends javax.swing.JFrame {
    
    private String tipoUsuario;
    

    /**
     * Creates new form TelaCadastroUsuario
     */
   public TelaCadastroUsuario() {
    initComponents(); 
    setLocationRelativeTo(null);
    this.tipoUsuario = "Visitante"; 
    setTitle("Cadastro de Usuários");
    configurarPermissoes(tipoUsuario); 
    atualizarLabelUsuario(); 
    }
   public TelaCadastroUsuario(String tipoUsuario, int id, String nome, String email, String cargo, String setor) {
    initComponents();
    this.tipoUsuario = tipoUsuario;
    setLocationRelativeTo(null);
    setTitle("Cadastro de Usuários - " + tipoUsuario);
    jLabel7.setText("Bem-vindo(a)! Permissão: " + tipoUsuario);
    configurarPermissoes(tipoUsuario);
    atualizarLabelUsuario(); 
    
    // Preencher os campos com os dados do usuário
    preencherCamposEdicao(id, nome, email, cargo, setor);

}

    public TelaCadastroUsuario(String tipoUsuario) {
        initComponents();
        this.tipoUsuario = tipoUsuario;
        setLocationRelativeTo(null);
        setTitle("Cadastro de Usuários - " + tipoUsuario);
        jLabel7.setText("Bem-vindo(a)! Permissão: " + tipoUsuario);
        configurarPermissoes(tipoUsuario);
        atualizarLabelUsuario(); 
        
    btnCadastrarUser.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnCadastrarUserActionPerformed(evt); 
         
        }
        
    });   
    

     // Configurar ação do botão Limpar
    btnLimparCadUser.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            // Adicione a chamada do método aqui:
    
        limparFormulario();
        }
    });    
    }

  // Método para preencher os campos na edição
     private void preencherCamposEdicao(int id, String nome, String email, String cargo, String setor) {
    txtID.setText(String.valueOf(id));
    txtNomeCad.setText(nome);
    txtEmailCad.setText(email);
    
    // Selecionar o cargo no combobox
    for (int i = 0; i < cbCargo.getItemCount(); i++) {
        if (cbCargo.getItemAt(i).trim().equals(cargo.trim())) {
            cbCargo.setSelectedIndex(i);
            break;
        }
    }
    
    // Selecionar o setor no combobox
    for (int i = 0; i < cbSetor.getItemCount(); i++) {
        if (cbSetor.getItemAt(i).equals(setor)) {
            cbSetor.setSelectedIndex(i);
            break;
        }
    }
    
    // Mudar o texto do botão para "Atualizar"
    btnCadastrarUser.setText("Atualizar");
}  
    
    private void btnCadastrarUserActionPerformed(java.awt.event.ActionEvent evt) {
    // 1. PRIMEIRO valida as permissões
    boolean podeGerenciar = tipoUsuario.equals("Administrador") || 
                           tipoUsuario.equals("Direção");
    
    if (!podeGerenciar) {
        JOptionPane.showMessageDialog(this, 
            "Acesso negado! Apenas Administradores e Direção podem cadastrar usuários.");
        return;
    }
    
    // 2. DEPOIS chama o método de cadastro
    cadastroUsuario();
}
    private void configurarPermissoes(String tipoUsuario) {
        // Configurar permissões baseadas no tipo de usuário
        boolean podeGerenciar = tipoUsuario.equals("Administrador") || 
                               tipoUsuario.equals("Direção");
        
        // Se não tiver permissão, mostrar mensagem e voltar
        if (!podeGerenciar) {
            JOptionPane.showMessageDialog(this, 
                "Acesso negado! Apenas Administradores e Direção podem cadastrar usuários.");
            return;
        }
        
        // Habilitar/desabilitar componentes baseado nas permissões
        btnCadastrarUser.setEnabled(podeGerenciar);
        btnLimparCadUser.setEnabled(podeGerenciar);
        
        // Tooltips informativos
        String tooltip = podeGerenciar ? "Acesso permitido" : "Acesso restrito";
        btnCadastrarUser.setToolTipText("Cadastrar usuário - " + tooltip);
        btnLimparCadUser.setToolTipText("Limpar formulário - " + tooltip);
        
    }
    private void atualizarLabelUsuario() {
    System.out.println("DEBUG: atualizarLabelUsuario() chamado - tipoUsuario = " + tipoUsuario);
    
    if (jLabel7 != null && tipoUsuario != null) {
        jLabel7.setText("Bem-vindo(a), " + tipoUsuario + "!");
        System.out.println("DEBUG: Texto definido para: " + jLabel7.getText());
    }
}
    
public void preencherCamposComBase(String nomeBase, String cargoBase, String setorBase) {
    // Limpar campos primeiro
    limparFormulario();
    
    // Preencher com dados base (adicionar sugestão no nome)
    txtNomeCad.setText(nomeBase + " - Novo");
    
    // Selecionar o cargo no combobox
    for (int i = 0; i < cbCargo.getItemCount(); i++) {
        if (cbCargo.getItemAt(i).trim().equals(cargoBase.trim())) {
            cbCargo.setSelectedIndex(i);
            break;
        }
    }
    
    // Selecionar o setor no combobox
    for (int i = 0; i < cbSetor.getItemCount(); i++) {
        if (cbSetor.getItemAt(i).equals(setorBase)) {
            cbSetor.setSelectedIndex(i);
            break;
        }
    }
    
    // Focar no campo de email para o usuário preencher
    txtEmailCad.requestFocus();
    
    // Atualizar título para indicar que é uma cópia
    setTitle("Novo Usuário (Base: " + nomeBase + ") - " + tipoUsuario);
    jLabel7.setText("Criando novo usuário com base em: " + nomeBase);
}
        public static void testarConexao() {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Driver carregado com sucesso!");
    } catch (ClassNotFoundException e) {
        System.out.println("Driver não encontrado: " + e.getMessage());
    }
}
        
        
private void cadastroUsuario() {
    System.out.println("🔍 Iniciando cadastroUsuario...");
    
    String nome = txtNomeCad.getText().trim();
    String email = txtEmailCad.getText().trim();
    String senha = txtSenha.getText().trim();
    String cargo = (String) cbCargo.getSelectedItem();  
    String nomeSetor = (String) cbSetor.getSelectedItem();
    
    System.out.println("Dados: " + nome + ", " + email + ", " + cargo + ", " + nomeSetor);
    
    // Validação dos campos obrigatórios
    if (nome.isEmpty() || email.isEmpty() || senha.isEmpty() || 
        cargo == null || cargo.equals("Selecionar") || 
        nomeSetor == null || nomeSetor.equals("Selecionar")) { 
        System.out.println("❌ Validação falhou");
        JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
        return;
    }
    
    System.out.println("✅ Validação passou, tentando conectar ao BD...");
    
    int setorId = obterIdSetor(nomeSetor);
    if (setorId == -1) {
        JOptionPane.showMessageDialog(this, "Setor não encontrado: " + nomeSetor);
        return;
    }

    
    String sql = "INSERT INTO Usuarios (nome, email, senha, cargo, setor_id) VALUES (?, ?, ?, ?, ?)";
    
    try (Connection conn = conectar(this); 
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
    // E no PreparedStatement:
        stmt.setString(1, nome);
        stmt.setString(2, email);
        stmt.setString(3, senha);
        stmt.setString(4, cargo);
        stmt.setInt(5, setorId);
        
        stmt.executeUpdate();
        JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso!");
        
        // Limpar campos após cadastro
        limparFormulario();
        
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, 
            "Erro ao cadastrar: " + e.getMessage(),
            "Erro", JOptionPane.ERROR_MESSAGE);
    }    
} 
    private int obterIdSetor(String nomeSetor) {
    String sql = "SELECT id_setor FROM Setores WHERE nome_setor = ?";
    
    try (Connection conn = conectar(this);
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setString(1, nomeSetor);
        var rs = stmt.executeQuery();
        
        if (rs.next()) {
            return rs.getInt("id_setor");
        }
    } catch (SQLException e) {
        System.out.println("Erro ao buscar ID do setor: " + e.getMessage());
    }
    
    return -1; // Retorna -1 se não encontrou
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtNomeCad = new javax.swing.JTextField();
        txtEmailCad = new javax.swing.JTextField();
        btnCadastrarUser = new javax.swing.JButton();
        btnLimparCadUser = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        cbCargo = new javax.swing.JComboBox<>();
        cbSetor = new javax.swing.JComboBox<>();
        btnVoltarCadUsu = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtSenha = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Cadastro de Usuários");

        jLabel2.setText(" ID:");

        jLabel3.setText(" Nome:");

        jLabel4.setText("Email:");

        jLabel5.setText("Cargo:");

        jLabel6.setText("Setor:");

        btnCadastrarUser.setText("Cadastrar");

        btnLimparCadUser.setText("Limpar");

        jLabel7.setText("Bem-vindo(a), [Usuário]! ");

        cbCargo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecionar", "Direção ", "Gerência ", "Supervisão ", "Analistas ", "Assistentes ", "Auxiliares ", "Atendentes " }));

        cbSetor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecionar", "Atendimento", "Contábil", "Declarações", "Financeiro", "Fiscal", "RH", "Societário", "TI" }));

        btnVoltarCadUsu.setText("Voltar");
        btnVoltarCadUsu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarCadUsuActionPerformed(evt);
            }
        });

        jLabel8.setText("Senha:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(180, 180, 180)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(134, 134, 134)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnCadastrarUser)
                                        .addGap(42, 42, 42)
                                        .addComponent(btnLimparCadUser)
                                        .addGap(46, 46, 46)
                                        .addComponent(btnVoltarCadUsu))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(jLabel8)
                                            .addGap(18, 18, 18)
                                            .addComponent(txtSenha))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel5)
                                                .addComponent(jLabel6)
                                                .addComponent(jLabel4)
                                                .addComponent(jLabel3)
                                                .addComponent(jLabel2))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(txtEmailCad, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                                                .addComponent(txtNomeCad, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(cbCargo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(cbSetor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                        .addGap(0, 148, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel7)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel2, jLabel3, jLabel4, jLabel5, jLabel6});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel1)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNomeCad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEmailCad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(cbCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(cbSetor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnLimparCadUser)
                        .addComponent(btnVoltarCadUsu))
                    .addComponent(btnCadastrarUser))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVoltarCadUsuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarCadUsuActionPerformed
      // Navegação segura mantendo as permissões do usuário
    if (tipoUsuario == null) {
        JOptionPane.showMessageDialog(this, 
            "Erro: informações de usuário não encontradas. Retornando ao login.",
            "Erro", JOptionPane.ERROR_MESSAGE);
        new TelaLogin().setVisible(true);
    } else {
        new TelaUsuarios(tipoUsuario).setVisible(true);
    }
    this.dispose();
    }//GEN-LAST:event_btnVoltarCadUsuActionPerformed

        /**
     * Estabelece conexão com o banco de dados MySQL.
     * Utiliza driver JDBC para conexão com o banco ATIVIDADE1.
     * 
     * @param parentComponent Componente pai para exibição de mensagens de erro
     * @return Connection objeto de conexão com o banco de dados
     * @throws ClassNotFoundException se o driver JDBC não for encontrado
     * @throws SQLException se ocorrer erro na conexão com o banco
     */
    private Connection conectar(Component parentComponent) {
    try {
        
        Class.forName("com.mysql.cj.jdbc.Driver");
        
       var url = "jdbc:mysql://localhost:3306/integra_inicial";
        String user = "root";
        String password = "l5PV3IYH@";
        
        Connection conn = DriverManager.getConnection(url, user, password);
        System.out.println("Conexão estabelecida com sucesso");
        return conn;
        
    } catch (ClassNotFoundException e) {
        JOptionPane.showMessageDialog(parentComponent, 
            "Driver JDBC não encontrado: " + e.getMessage(),
            "Erro de Conexão", JOptionPane.ERROR_MESSAGE);
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(parentComponent, 
            "Erro ao conectar ao banco: " + e.getMessage(),
            "Erro de Conexão", JOptionPane.ERROR_MESSAGE);
        // Log adicional para debug
    }
    
    return null;
}
           
    
    private void limparFormulario() {
    txtID.setText("");
    txtNomeCad.setText("");
    txtEmailCad.setText("");
    txtSenha.setText("");
    cbCargo.setSelectedIndex(0);
    cbSetor.setSelectedIndex(0);
}
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCadastroUsuario().setVisible(true);
            }
        });
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCadastrarUser;
    private javax.swing.JButton btnLimparCadUser;
    private javax.swing.JButton btnVoltarCadUsu;
    private javax.swing.JComboBox<String> cbCargo;
    private javax.swing.JComboBox<String> cbSetor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField txtEmailCad;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtNomeCad;
    private javax.swing.JTextField txtSenha;
    // End of variables declaration//GEN-END:variables

}
