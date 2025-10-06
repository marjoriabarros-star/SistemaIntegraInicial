/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package telas;

/**
 *
 * @author Marjori Barros
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.SwingWorker;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TelaCadastroManuais extends javax.swing.JFrame {
    private String tipoUsuario;
    private File arquivoSelecionado;
    private Connection conexao;
    
    // Constantes para configuração
    private static final long TAMANHO_MAXIMO_ARQUIVO = 50 * 1024 * 1024; // 50MB
    private static final String[] EXTENSOES_PERMITIDAS = {".pdf", ".doc", ".docx", ".xls", ".xlsx", ".ppt", ".pptx", ".txt"};
    
    /**
     * Creates new form TelaCadastroManuais
     */
    public TelaCadastroManuais() {
        initComponents();
        setLocationRelativeTo(null);
        this.tipoUsuario = "Visitante";
        configurarComponentes();
        inicializarConexao();
    }
  
    public TelaCadastroManuais(String tipoUsuario) {
        initComponents();
        setLocationRelativeTo(null);
        this.tipoUsuario = tipoUsuario;
        setTitle("Cadastro de Manuais - " + tipoUsuario);
        configurarComponentes();
        configurarPermissoes(tipoUsuario);
        inicializarConexao();
    }
    
private void inicializarConexao() {
    try {
        Properties props = new Properties();
        
        // Tenta carregar do arquivo de configuração
        try (InputStream input = getClass().getResourceAsStream("/config.properties")) {
            if (input != null) {
                props.load(input);
                System.out.println("Configurações carregadas do arquivo properties");
            } else {
                System.out.println("Arquivo config.properties não encontrado. Usando valores padrão.");
                // Define valores padrão
                props.setProperty("db.url", "jdbc:mysql://localhost:3306/integra_inicial");
                props.setProperty("db.usuario", "root");
                props.setProperty("db.senha", "l5PV3IYH@");
            }
        }
        
        String url = props.getProperty("db.url");
        String usuario = props.getProperty("db.usuario");
        String senha = props.getProperty("db.senha");
        
        conexao = DriverManager.getConnection(url, usuario, senha);
        System.out.println("Conexão com banco estabelecida com sucesso");
        
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, 
            "Erro ao conectar com o banco de dados: " + e.getMessage(),
            "Erro de Conexão", JOptionPane.ERROR_MESSAGE);
        conexao = null;
    } catch (IOException e) {
        System.err.println("Erro ao carregar configurações: " + e.getMessage());
        // Tenta conexão com valores padrão
        try {
            String url = "jdbc:mysql://localhost:3306/integra_inicial";
            String usuario = "root";
            String senha = "l5PV3IYH@";
            conexao = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conexão estabelecida com valores padrão");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, 
                "Erro ao conectar com o banco de dados: " + ex.getMessage(),
                "Erro de Conexão", JOptionPane.ERROR_MESSAGE);
            conexao = null;
        }
    }
}
    
    private boolean verificarConexao() {
        if (conexao == null) {
            JOptionPane.showMessageDialog(this,
                "Não há conexão com o banco de dados. Tente reiniciar a aplicação.",
                "Sem Conexão", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
        
    private void configurarComponentes() {
        // Configurar campo ID
        txtID.setEditable(false);
        txtID.setText("Automático");
        
        // Personalizar mensagem de boas-vindas
        jLabel4.setText("Bem-vindo(a)! Permissão: " + tipoUsuario);
        
        // Tooltips para melhor usabilidade
        cbSetor.setToolTipText("Selecione o setor do manual");
        txtCaminhoArquivo.setToolTipText("Informe o link ou localização do manual");
        
        // Configurar progress bar
        progressBar.setStringPainted(true);
        progressBar.setValue(0);
    }
    
    private void configurarPermissoes(String tipoUsuario) {
        boolean podeGerenciar = tipoUsuario.equals("Administrador") || 
                               tipoUsuario.equals("Direção") ||
                               tipoUsuario.equals("Gerência");
        
        btnCadastrarManual.setEnabled(podeGerenciar);
        btnUpload.setEnabled(podeGerenciar);
        btnSelecionarArquivo.setEnabled(podeGerenciar);
        
        if (!podeGerenciar) {
            btnCadastrarManual.setToolTipText("Acesso restrito para " + tipoUsuario);
            btnUpload.setToolTipText("Acesso restrito para " + tipoUsuario);
            btnSelecionarArquivo.setToolTipText("Acesso restrito para " + tipoUsuario);
            JOptionPane.showMessageDialog(this, 
                "Acesso de visualização apenas. \nApenas Administradores, Direção e Gerência podem cadastrar manuais.",
                "Acesso Restrito", JOptionPane.INFORMATION_MESSAGE);
        }
    }    

    // Método para selecionar arquivo - Funcionalidade 1
    private void selecionarArquivo() {
        // Se já houver um caminho no campo de texto, tenta abrir nesse diretório
        JFileChooser fileChooser = new JFileChooser();
        
        String caminhoAtual = txtCaminhoArquivo.getText().trim();
        if (!caminhoAtual.isEmpty()) {
            File diretorio = new File(caminhoAtual);
            if (diretorio.exists() && diretorio.isDirectory()) {
                fileChooser.setCurrentDirectory(diretorio);
            } else if (diretorio.exists() && diretorio.isFile()) {
                fileChooser.setCurrentDirectory(diretorio.getParentFile());
            }
        }
        
        fileChooser.setDialogTitle("Selecionar Arquivo");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        
        // Filtro para tipos de arquivo comuns
        fileChooser.setAcceptAllFileFilterUsed(true);
        
        int result = fileChooser.showOpenDialog(this);
        
        if (result == JFileChooser.APPROVE_OPTION) {
            arquivoSelecionado = fileChooser.getSelectedFile();
            txtCaminhoArquivo.setText(arquivoSelecionado.getAbsolutePath());
            lblStatus.setText("Arquivo selecionado: " + arquivoSelecionado.getName());
        }
    }

    // Método para fazer upload do arquivo
    private void fazerUpload() {
        if (!validarPermissao()) {
            return;
        }
        
        String caminhoArquivo = txtCaminhoArquivo.getText().trim();
        
        if (caminhoArquivo.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Por favor, selecione um arquivo ou informe o caminho.",
                "Arquivo Não Selecionado", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        File arquivo = new File(caminhoArquivo);
        
        if (!arquivo.exists() || !arquivo.isFile()) {
            JOptionPane.showMessageDialog(this,
                "Arquivo e/ou endereço inválido! Selecione um caminho/arquivo válido.",
                "Arquivo Inválido", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Validações adicionais
        if (!validarArquivo(arquivo)) {
            return;
        }
        
        // Verificar setor selecionado
        if (cbSetor.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this,
                "Por favor, selecione um setor válido.",
                "Setor Não Selecionado", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Verificar conexão com banco
        if (!verificarConexao()) {
            return;
        }
        
        // Executar upload em background
        new UploadWorker(arquivo).execute();
    }
    
    private boolean validarArquivo(File arquivo) {
        // Validar tamanho do arquivo
        if (arquivo.length() > TAMANHO_MAXIMO_ARQUIVO) {
            JOptionPane.showMessageDialog(this,
                "Arquivo muito grande. Tamanho máximo: " + (TAMANHO_MAXIMO_ARQUIVO / (1024 * 1024)) + "MB",
                "Arquivo Grande Demais", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        // Validar extensão do arquivo
        String nomeArquivo = arquivo.getName().toLowerCase();
        boolean extensaoValida = false;
        for (String extensao : EXTENSOES_PERMITIDAS) {
            if (nomeArquivo.endsWith(extensao)) {
                extensaoValida = true;
                break;
            }
        }
        
        if (!extensaoValida) {
            StringBuilder extensoes = new StringBuilder();
            for (String ext : EXTENSOES_PERMITIDAS) {
                extensoes.append(ext).append(" ");
            }
            JOptionPane.showMessageDialog(this,
                "Tipo de arquivo não permitido. Extensões aceitas: " + extensoes.toString(),
                "Tipo de Arquivo Inválido", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        return true;
    }

    // Classe worker para upload em background com progresso
    private class UploadWorker extends SwingWorker<Boolean, Integer> {
        private final File arquivo;
        
        public UploadWorker(File arquivo) {
            this.arquivo = arquivo;
        }
        
        @Override
        protected Boolean doInBackground() throws Exception {
            try {
                publish(0);
                
                // Simular processo de upload com progresso
                // Em um sistema real, aqui você faria o upload real para o servidor
                for (int i = 0; i <= 100; i++) {
                    if (isCancelled()) {
                        return false;
                    }
                    
                    Thread.sleep(50); // Simular tempo de processamento
                    publish(i);
                    
                    // Aqui você implementaria a lógica real de upload
                    // Por exemplo: copiar para servidor, salvar no banco, etc.
                    // FileInputStream fis = new FileInputStream(arquivo);
                    // ... código de upload real
                }
                
                // Salvar informações no banco de dados
                if (salvarNoBanco(arquivo)) {
                    publish(100);
                    return true;
                } else {
                    return false;
                }
                
            } catch (InterruptedException e) {
                System.out.println("Upload cancelado pelo usuário");
                return false;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        
        @Override
        protected void process(java.util.List<Integer> chunks) {
            int progresso = chunks.get(chunks.size() - 1);
            progressBar.setValue(progresso);
            progressBar.setVisible(true);
            lblStatus.setText("Upload em progresso: " + progresso + "%");
        }
        
        @Override
        protected void done() {
            progressBar.setVisible(false);
            try {
                boolean sucesso = get();
                if (sucesso) {
                    lblStatus.setText("Upload concluído com sucesso!");
                    JOptionPane.showMessageDialog(TelaCadastroManuais.this,
                        "Arquivo enviado com sucesso!",
                        "Upload Concluído", JOptionPane.INFORMATION_MESSAGE);
                    // Limpar campos após sucesso
                    limparCampos();
                } else {
                    lblStatus.setText("Erro no upload");
                    JOptionPane.showMessageDialog(TelaCadastroManuais.this,
                        "Erro ao enviar arquivo. Tente novamente.",
                        "Erro no Upload", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                lblStatus.setText("Erro no upload");
                JOptionPane.showMessageDialog(TelaCadastroManuais.this,
                    "Erro durante o upload: " + e.getMessage(),
                    "Erro no Upload", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private boolean salvarNoBanco(File arquivo) {
        if (!verificarConexao()) {
            return false;
        }
        
        PreparedStatement stmt = null;
        try {
            String sql = "INSERT INTO manuais (setor, nome_arquivo, caminho_arquivo, tamanho, data_upload) VALUES (?, ?, ?, ?, NOW())";
            stmt = conexao.prepareStatement(sql);
            
            stmt.setString(1, cbSetor.getSelectedItem().toString());
            stmt.setString(2, arquivo.getName());
            stmt.setString(3, arquivo.getAbsolutePath());
            stmt.setLong(4, arquivo.length());
            
            int result = stmt.executeUpdate();
            return result > 0;
            
        } catch (SQLException e) {
            System.err.println("Erro SQL: " + e.getMessage());
            JOptionPane.showMessageDialog(this,
                "Erro ao salvar no banco de dados. Verifique a conexão.",
                "Erro de Banco de Dados", JOptionPane.ERROR_MESSAGE);
            return false;
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.err.println("Erro ao fechar statement: " + e.getMessage());
                }
            }
        }
    }
    
    private void limparCampos() {
        txtCaminhoArquivo.setText("");
        progressBar.setValue(0);
        lblStatus.setText("Status Upload:");
        cbSetor.setSelectedIndex(0);
        arquivoSelecionado = null;
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

        btnCadastrarManual = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnVoltarCadMan = new javax.swing.JButton();
        txtID = new javax.swing.JTextField();
        txtCaminhoArquivo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnSelecionarArquivo = new javax.swing.JButton();
        btnUpload = new javax.swing.JButton();
        lblStatus = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();
        jLabel4 = new javax.swing.JLabel();
        cbSetor = new javax.swing.JComboBox<>();
        btnLimparCadManuais = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnCadastrarManual.setText("Cadastrar");
        btnCadastrarManual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarManualActionPerformed(evt);
            }
        });

        jLabel2.setText(" ID:");

        jLabel3.setText("Setor:");

        btnVoltarCadMan.setText("Voltar");
        btnVoltarCadMan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarCadManActionPerformed(evt);
            }
        });

        txtID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Cadastro de Manuais");

        btnSelecionarArquivo.setText("Selecionar arquivo");
        btnSelecionarArquivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelecionarArquivoActionPerformed(evt);
            }
        });

        btnUpload.setText("Upload");
        btnUpload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUploadActionPerformed(evt);
            }
        });

        lblStatus.setText("Status Upload:");

        jLabel4.setText("Bem-vindo(a), [Usuário]! ");

        cbSetor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecionar", "Fiscal", "Contábil", "RH", "Declarações", "Societário", "Atendimento", "Financeiro", "TI", " " }));

        btnLimparCadManuais.setText("Limpar");
        btnLimparCadManuais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparCadManuaisActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbSetor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(btnCadastrarManual)
                        .addGap(38, 38, 38)
                        .addComponent(btnLimparCadManuais)
                        .addGap(43, 43, 43)
                        .addComponent(btnVoltarCadMan))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblStatus)
                        .addGap(18, 18, 18)
                        .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSelecionarArquivo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCaminhoArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnUpload)))
                .addContainerGap(84, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel1)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbSetor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSelecionarArquivo)
                    .addComponent(txtCaminhoArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpload))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblStatus)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCadastrarManual)
                    .addComponent(btnVoltarCadMan)
                    .addComponent(btnLimparCadManuais))
                .addGap(38, 38, 38)
                .addComponent(jLabel4)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVoltarCadManActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarCadManActionPerformed
        new TelaManuais(tipoUsuario).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnVoltarCadManActionPerformed

    private void txtIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDActionPerformed

    private void btnSelecionarArquivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelecionarArquivoActionPerformed
        selecionarArquivo();
    }//GEN-LAST:event_btnSelecionarArquivoActionPerformed

    private void btnUploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUploadActionPerformed
        fazerUpload();
    }//GEN-LAST:event_btnUploadActionPerformed

    private void btnCadastrarManualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarManualActionPerformed
            if (validarPermissao()) {
            JOptionPane.showMessageDialog(this,
                "Manual cadastrado com sucesso!",
                "Cadastro Concluído", JOptionPane.INFORMATION_MESSAGE);        
            }
    }//GEN-LAST:event_btnCadastrarManualActionPerformed

    private void btnLimparCadManuaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparCadManuaisActionPerformed
     limparCampos();
    }//GEN-LAST:event_btnLimparCadManuaisActionPerformed
              
    private boolean validarPermissao() {
        boolean podeGerenciar = tipoUsuario.equals("Administrador") || 
                               tipoUsuario.equals("Direção") ||
                               tipoUsuario.equals("Gerência");
        
        if (!podeGerenciar) {
            JOptionPane.showMessageDialog(this, 
                "Acesso negado! Apenas Administradores, Direção e Gerência podem cadastrar manuais.",
                "Acesso Negado", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
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
            java.util.logging.Logger.getLogger(TelaCadastroManuais.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroManuais.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroManuais.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroManuais.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCadastroManuais().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCadastrarManual;
    private javax.swing.JButton btnLimparCadManuais;
    private javax.swing.JButton btnSelecionarArquivo;
    private javax.swing.JButton btnUpload;
    private javax.swing.JButton btnVoltarCadMan;
    private javax.swing.JComboBox<String> cbSetor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JTextField txtCaminhoArquivo;
    private javax.swing.JTextField txtID;
    // End of variables declaration//GEN-END:variables

    }
    
    
