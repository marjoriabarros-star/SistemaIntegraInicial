/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package telas;

/**
 *
 * @author Marjori Barros
 */
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

    public class TelaProcessosCadastrados extends javax.swing.JFrame {
    private String tipoUsuario;

    /**
     * Creates new form TelaProcessosCadastrados
     */
    public TelaProcessosCadastrados() {
        initComponents();
        setLocationRelativeTo(null);
        this.tipoUsuario = "Visitante";
        configurarPermissoes("Visitante");
        atualizarLabelUsuario();
    }

    public TelaProcessosCadastrados(String tipoUsuario) {
        initComponents();
        setLocationRelativeTo(null);
        this.tipoUsuario = tipoUsuario;
        setTitle("Processos Cadastrados - " + tipoUsuario);
        configurarPermissoes(tipoUsuario);
        carregarDados();
        configurarActionListeners();
        atualizarLabelUsuario();
    }
        private void configurarActionListeners() {
        btnFiltrar.addActionListener(evt -> btnFiltrarActionPerformed(evt));
        btnExcluirSelecionado.addActionListener(evt -> btnExcluirSelecionadoActionPerformed(evt));
        btnAlterarSelecionado.addActionListener(evt -> btnAlterarSelecionadoActionPerformed(evt));
    }
    
    private void configurarPermissoes(String tipoUsuario) {
        boolean podeGerenciar = tipoUsuario.equals("Administrador") || 
                               tipoUsuario.equals("Direção") ||
                               tipoUsuario.equals("Gerência");
        
        btnExcluirSelecionado.setEnabled(podeGerenciar);
        btnAlterarSelecionado.setEnabled(podeGerenciar);
        btnCriarNovoProcSelec.setEnabled(podeGerenciar);
        
        if (!podeGerenciar) {
            String tooltip = "Acesso restrito para " + tipoUsuario;
            btnExcluirSelecionado.setToolTipText(tooltip);
            btnAlterarSelecionado.setToolTipText(tooltip);
            btnCriarNovoProcSelec.setToolTipText(tooltip);
        }
    }
    
    private void carregarDados() {
        try (Connection conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/integra_inicial", "root", "l5PV3IYH@")) {
            
            StringBuilder sql = new StringBuilder(
                "SELECT p.id_processo, p.descricao, s.nome_setor " +
                "FROM Processos p LEFT JOIN Setores s ON p.setor_id = s.id_setor WHERE 1=1"
            );
            
            String filtroDescricao = jTextField1.getText().trim();
            String setorSelecionado = (String) jComboBox1.getSelectedItem();
            
            List<Object> parametros = new ArrayList<>();
            
            if (!filtroDescricao.isEmpty()) {
                sql.append(" AND p.descricao LIKE ?");
                parametros.add("%" + filtroDescricao + "%");
            }
            
            if (!"Todos".equals(setorSelecionado)) {
                sql.append(" AND s.nome_setor = ?");
                parametros.add(setorSelecionado);
            }
            
            PreparedStatement stmt = conn.prepareStatement(sql.toString());
            
            for (int i = 0; i < parametros.size(); i++) {
                stmt.setObject(i + 1, parametros.get(i));
            }
            
            ResultSet rs = stmt.executeQuery();
            
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID");
            model.addColumn("Descrição");
            model.addColumn("Setor");
            model.addColumn("Ações");
            
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("id_processo"),
                    rs.getString("descricao"),
                    rs.getString("nome_setor"),
                    "✏️ 🗑️" // Ícones de ações
                });
            }
            
            jTable1.setModel(model);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, 
                "Erro ao carregar processos: " + e.getMessage(), 
                "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
        private void atualizarLabelUsuario() {
    System.out.println("DEBUG: atualizarLabelUsuario() chamado - tipoUsuario = " + tipoUsuario);
    jLabel4.setText("Bem-vindo(a), " + tipoUsuario + "!");
    System.out.println("DEBUG: Texto definido para: " + jLabel4.getText());
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnFiltrar = new javax.swing.JButton();
        btnVoltarProc = new javax.swing.JButton();
        btnExcluirSelecionado = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jComboBox1 = new javax.swing.JComboBox<>();
        btnAlterarSelecionado = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        btnCriarNovoProcSelec = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnFiltrar.setText("Filtrar");
        btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarActionPerformed(evt);
            }
        });

        btnVoltarProc.setText("Voltar");
        btnVoltarProc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarProcActionPerformed(evt);
            }
        });

        btnExcluirSelecionado.setText("Excluir selecionado");
        btnExcluirSelecionado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirSelecionadoActionPerformed(evt);
            }
        });

        jLabel3.setText("Setor:");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Processos cadastrados");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Descrição", "Setor", "Ações"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Atendimento", "Contábil", "Declarações", "Financeiro", "Fiscal", "RH", "Societário", "TI" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        btnAlterarSelecionado.setText("Alterar selecionado");
        btnAlterarSelecionado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarSelecionadoActionPerformed(evt);
            }
        });

        jLabel2.setText("Filtro por Descrição:");

        btnCriarNovoProcSelec.setText("Criar novo a partir de   selecionado");
        btnCriarNovoProcSelec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCriarNovoProcSelecActionPerformed(evt);
            }
        });

        jLabel4.setText("Bem-vindo(a), [Usuário]! ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnFiltrar)))))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnExcluirSelecionado)
                        .addGap(18, 18, 18)
                        .addComponent(btnAlterarSelecionado))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(192, 192, 192)
                        .addComponent(btnVoltarProc)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCriarNovoProcSelec)
                .addGap(0, 52, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFiltrar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExcluirSelecionado)
                    .addComponent(btnAlterarSelecionado)
                    .addComponent(btnCriarNovoProcSelec))
                .addGap(18, 18, 18)
                .addComponent(btnVoltarProc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVoltarProcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarProcActionPerformed
        new TelaProcessos(tipoUsuario).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnVoltarProcActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
     carregarDados();
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void btnCriarNovoProcSelecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCriarNovoProcSelecActionPerformed
        new TelaCadastroDeProcessos(tipoUsuario).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCriarNovoProcSelecActionPerformed

    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
     carregarDados();
    }//GEN-LAST:event_btnFiltrarActionPerformed

    private void btnExcluirSelecionadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirSelecionadoActionPerformed
     int linhaSelecionada = jTable1.getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um processo para excluir!");
            return;
        }
        
        if (!usuarioPodeGerenciar()) {
            JOptionPane.showMessageDialog(this, 
                "Acesso negado! Apenas Administradores, Direção e Gerência podem excluir processos.");
            return;
        }
        
        int idProcesso = (int) jTable1.getValueAt(linhaSelecionada, 0);
        String descricao = (String) jTable1.getValueAt(linhaSelecionada, 1);
        
        int confirmacao = JOptionPane.showConfirmDialog(this,
            "Deseja excluir o processo:\n" + descricao + " (ID: " + idProcesso + ")?",
            "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
        
        if (confirmacao == JOptionPane.YES_OPTION) {
            excluirProcesso(idProcesso);
        }
    }//GEN-LAST:event_btnExcluirSelecionadoActionPerformed

    private void btnAlterarSelecionadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarSelecionadoActionPerformed
     int linhaSelecionada = jTable1.getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um processo para alterar!");
            return;
        }
        
        if (!usuarioPodeGerenciar()) {
            JOptionPane.showMessageDialog(this, 
                "Acesso negado! Apenas Administradores, Direção e Gerência podem alterar processos.");
            return;
        }
        
    
        // Obter dados do processo selecionado
        int idProcesso = (int) jTable1.getValueAt(linhaSelecionada, 0);
        String descricao = (String) jTable1.getValueAt(linhaSelecionada, 1);
        String setor = (String) jTable1.getValueAt(linhaSelecionada, 2);
    
        // Abrir tela de edição com os dados do processo
        abrirTelaEdicaoProcesso(idProcesso, descricao, setor);
        }                                                     

    private void abrirTelaEdicaoProcesso(int idProcesso, String descricao, String setor) {
    this.dispose();
    
    // Abrir TelaCadastroDeProcessos em modo edição
    TelaCadastroDeProcessos telaEdicao = new TelaCadastroDeProcessos(tipoUsuario, idProcesso, descricao, setor);
    telaEdicao.setVisible(true);
     
    
    }//GEN-LAST:event_btnAlterarSelecionadoActionPerformed

    
      private boolean usuarioPodeGerenciar() {
        return tipoUsuario != null && 
              (tipoUsuario.equals("Administrador") || 
               tipoUsuario.equals("Direção") || 
               tipoUsuario.equals("Gerência"));
    }
    
    private void excluirProcesso(int idProcesso) {
        try (Connection conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/integra_inicial", "root", "l5PV3IYH@")) {
            
            String sql = "DELETE FROM Processos WHERE id_processo = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idProcesso);
            
            int linhasAfetadas = stmt.executeUpdate();
            
            if (linhasAfetadas > 0) {
                JOptionPane.showMessageDialog(this, "Processo excluído com sucesso!");
                carregarDados();
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao excluir processo: " + e.getMessage());
        }
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
            java.util.logging.Logger.getLogger(TelaProcessosCadastrados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaProcessosCadastrados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaProcessosCadastrados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaProcessosCadastrados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaProcessosCadastrados().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterarSelecionado;
    private javax.swing.JButton btnCriarNovoProcSelec;
    private javax.swing.JButton btnExcluirSelecionado;
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JButton btnVoltarProc;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables


}
