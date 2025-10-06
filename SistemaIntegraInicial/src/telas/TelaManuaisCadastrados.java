/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package telas;

/**
 *
 * @author Marjori Barros
 */
<<<<<<< HEAD
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import javax.swing.table.DefaultTableModel;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

    public class TelaManuaisCadastrados extends javax.swing.JFrame {
    private String tipoUsuario;
    private Connection conexao;
=======
public class TelaManuaisCadastrados extends javax.swing.JFrame {
>>>>>>> 06c877f18a4b3a1dda1dab94ab8544262e3d8241

    /**
     * Creates new form TelaManuaisCadastrados
     */
    public TelaManuaisCadastrados() {
        initComponents();
<<<<<<< HEAD
        setLocationRelativeTo(null);
        this.tipoUsuario = "Visitante";
        configurarPermissoes("Visitante");
        atualizarLabelUsuario();
        configurarListenersAdicionais();
        inicializarConexao(); 
        carregarManuais(); 
    }
  
    public TelaManuaisCadastrados(String tipoUsuario) {
        initComponents();
        setLocationRelativeTo(null);
        this.tipoUsuario = tipoUsuario;
        setTitle("Manuais Cadastrados - " + tipoUsuario);
        configurarPermissoes(tipoUsuario);
        atualizarLabelUsuario();
        configurarListenersAdicionais();
        inicializarConexao(); 
        carregarManuais();
    }
    private void configurarListenersAdicionais() {
    // Listener para duplo-clique na tabela
    jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            if (evt.getClickCount() == 2) { // Duplo-clique
                visualizarDocumentoSelecionado();
            }
        }
    });
}
private void configurarPermissoes(String tipoUsuario) {
    boolean podeGerenciar = tipoUsuario.equals("Administrador") || 
                           tipoUsuario.equals("Direção") ||
                           tipoUsuario.equals("Gerência");
    
    boolean podeBaixar = true; // Todos podem baixar
    
    btnExcluirSelecionado.setEnabled(podeGerenciar);
    btnBaixarSelecionado.setEnabled(podeBaixar); // Sempre habilitado
    btnVisualizarDoc.setEnabled(podeBaixar); // Todos podem visualizar
    
    if (!podeGerenciar) {
        btnExcluirSelecionado.setToolTipText("Acesso restrito para " + tipoUsuario);
    }
    
    // Tooltips informativos
    btnBaixarSelecionado.setToolTipText("Baixar arquivo selecionado");
    btnVisualizarDoc.setToolTipText("Visualizar arquivo selecionado");
}
    private void atualizarLabelUsuario() {
    System.out.println("DEBUG: atualizarLabelUsuario() chamado - tipoUsuario = " + tipoUsuario);
    jLabel4.setText("Bem-vindo(a), " + tipoUsuario + "!");
    System.out.println("DEBUG: Texto definido para: " + jLabel4.getText());
}
    private void atualizarTabela() {
    carregarManuais();
}
    private void inicializarConexao() {
    try {
        Properties props = new Properties();
        
        // Tenta carregar do arquivo de configuração
        try (java.io.InputStream input = getClass().getResourceAsStream("/config.properties")) {
            if (input != null) {
                props.load(input);
                System.out.println("Configurações carregadas do arquivo properties");
            } else {
                System.out.println("Arquivo config.properties não encontrado. Usando valores padrão.");
                // Define valores padrão (os mesmos da TelaCadastroManuais)
                props.setProperty("db.url", "jdbc:mysql://localhost:3306/integra_inicial");
                props.setProperty("db.usuario", "root");
                props.setProperty("db.senha", "l5PV3IYH@");
            }
        } catch (java.io.IOException e) {
            System.err.println("Erro ao carregar configurações: " + e.getMessage());
            // Define valores padrão em caso de erro
            props.setProperty("db.url", "jdbc:mysql://localhost:3306/integra_inicial");
            props.setProperty("db.usuario", "root");
            props.setProperty("db.senha", "l5PV3IYH@");
        }
        
        String url = props.getProperty("db.url");
        String usuario = props.getProperty("db.usuario");
        String senha = props.getProperty("db.senha");
        
        conexao = DriverManager.getConnection(url, usuario, senha);
        System.out.println("Conexão com banco estabelecida com sucesso na TelaManuaisCadastrados");
        
    } catch (SQLException e) {
        System.err.println("Erro ao conectar com o banco de dados: " + e.getMessage());
        conexao = null;
    }
}
    
    
  
=======
    }

>>>>>>> 06c877f18a4b3a1dda1dab94ab8544262e3d8241
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

<<<<<<< HEAD
        btnBaixarSelecionado = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        btnFiltrar = new javax.swing.JButton();
        btnVisualizarDoc = new javax.swing.JButton();
=======
        btnAlterarSelecionado = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        btnFiltrar = new javax.swing.JButton();
        btnCriarNovoManSelec = new javax.swing.JButton();
>>>>>>> 06c877f18a4b3a1dda1dab94ab8544262e3d8241
        btnVoltarMan = new javax.swing.JButton();
        btnExcluirSelecionado = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
<<<<<<< HEAD

        btnBaixarSelecionado.setText("Baixar arquivo");
        btnBaixarSelecionado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBaixarSelecionadoActionPerformed(evt);
            }
        });
=======
        setPreferredSize(new java.awt.Dimension(600, 400));

        btnAlterarSelecionado.setText("Alterar selecionado");
>>>>>>> 06c877f18a4b3a1dda1dab94ab8544262e3d8241

        jLabel2.setText("Filtro por Descrição:");

        btnFiltrar.setText("Filtrar");

<<<<<<< HEAD
        btnVisualizarDoc.setText("Visualizar");
        btnVisualizarDoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVisualizarDocActionPerformed(evt);
=======
        btnCriarNovoManSelec.setText("Criar novo a partir de   selecionado");
        btnCriarNovoManSelec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCriarNovoManSelecActionPerformed(evt);
>>>>>>> 06c877f18a4b3a1dda1dab94ab8544262e3d8241
            }
        });

        btnVoltarMan.setText("Voltar");
        btnVoltarMan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarManActionPerformed(evt);
            }
        });

        btnExcluirSelecionado.setText("Excluir selecionado");
<<<<<<< HEAD
        btnExcluirSelecionado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirSelecionadoActionPerformed(evt);
            }
        });
=======
>>>>>>> 06c877f18a4b3a1dda1dab94ab8544262e3d8241

        jLabel3.setText("Setor:");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Manuais cadastrados");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Descrição", "Setor", "Link"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Atendimento", "Contábil", "Declarações", "Financeiro", "Fiscal", "RH", "Societário", "TI" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel4.setText("Bem-vindo(a), [Usuário]! ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(193, 193, 193)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(51, 51, 51)
<<<<<<< HEAD
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnFiltrar))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnExcluirSelecionado)
                                .addGap(62, 62, 62)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnBaixarSelecionado)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(btnVoltarMan)
                                        .addGap(24, 24, 24)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnVisualizarDoc)
                                .addGap(25, 25, 25)))))
                .addContainerGap(62, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(24, 24, 24))
=======
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnExcluirSelecionado)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnAlterarSelecionado))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(192, 192, 192)
                                        .addComponent(btnVoltarMan)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCriarNovoManSelec))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane1)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(24, 24, 24)
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnFiltrar))))))
                .addContainerGap(66, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(19, 19, 19))
>>>>>>> 06c877f18a4b3a1dda1dab94ab8544262e3d8241
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFiltrar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExcluirSelecionado)
<<<<<<< HEAD
                    .addComponent(btnBaixarSelecionado)
                    .addComponent(btnVisualizarDoc))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVoltarMan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(31, 31, 31))
=======
                    .addComponent(btnAlterarSelecionado)
                    .addComponent(btnCriarNovoManSelec))
                .addGap(18, 18, 18)
                .addComponent(btnVoltarMan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addContainerGap(43, Short.MAX_VALUE))
>>>>>>> 06c877f18a4b3a1dda1dab94ab8544262e3d8241
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

<<<<<<< HEAD
    private void btnVisualizarDocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVisualizarDocActionPerformed
    visualizarDocumentoSelecionado();
    }//GEN-LAST:event_btnVisualizarDocActionPerformed

    private void btnVoltarManActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarManActionPerformed
        new TelaManuais(tipoUsuario).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnVoltarManActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
    filtrarManuais();
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void btnExcluirSelecionadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirSelecionadoActionPerformed
    int linhaSelecionada = jTable1.getSelectedRow();
    
    // Verifica se uma linha foi selecionada
    if (linhaSelecionada == -1) {
        javax.swing.JOptionPane.showMessageDialog(this,
            "Por favor, selecione um manual para excluir.",
            "Nenhum Manual Selecionado",
            javax.swing.JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    // Verifica permissões do usuário
    boolean podeExcluir = tipoUsuario.equals("Administrador") || 
                         tipoUsuario.equals("Direção") ||
                         tipoUsuario.equals("Gerência");
    
    if (!podeExcluir) {
        javax.swing.JOptionPane.showMessageDialog(this,
            "Acesso negado! Apenas Administradores, Direção e Gerência podem excluir manuais.",
            "Acesso Restrito",
            javax.swing.JOptionPane.ERROR_MESSAGE);
        return;
    }
    
    try {
        // Obtém os dados da linha selecionada
        int idManual = (int) jTable1.getValueAt(linhaSelecionada, 0);
        String nomeArquivo = jTable1.getValueAt(linhaSelecionada, 1).toString();
        String caminhoArquivo = jTable1.getValueAt(linhaSelecionada, 3).toString();
        
        // Confirmação do usuário
        int confirmacao = javax.swing.JOptionPane.showConfirmDialog(this,
            "Tem certeza que deseja excluir o manual:\n" +
            "\"" + nomeArquivo + "\"?\n\n" +
            "Esta ação não pode ser desfeita.",
            "Confirmar Exclusão",
            javax.swing.JOptionPane.YES_NO_OPTION,
            javax.swing.JOptionPane.WARNING_MESSAGE);
        
        if (confirmacao != javax.swing.JOptionPane.YES_OPTION) {
            return; // Usuário cancelou a exclusão
        }
        
        // Primeiro, exclui o arquivo físico
        boolean arquivoExcluido = excluirArquivoFisico(caminhoArquivo);
        
        // Depois, exclui o registro do banco de dados
        boolean registroExcluido = excluirRegistroBanco(idManual);
        
        if (registroExcluido) {
            String mensagem = "Manual \"" + nomeArquivo + "\" excluído com sucesso!";
            if (!arquivoExcluido) {
                mensagem += "\n\nAviso: O arquivo físico não pôde ser excluído, mas o registro foi removido do sistema.";
            }
            
            javax.swing.JOptionPane.showMessageDialog(this,
                mensagem,
                "Exclusão Concluída",
                javax.swing.JOptionPane.INFORMATION_MESSAGE);
            
            // Atualiza a tabela
            carregarManuais();
            
        } else {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Erro ao excluir o manual do banco de dados.",
                "Erro na Exclusão",
                javax.swing.JOptionPane.ERROR_MESSAGE);
        }
        
    } catch (Exception e) {
        System.err.println("Erro ao excluir manual: " + e.getMessage());
        e.printStackTrace();
        
        javax.swing.JOptionPane.showMessageDialog(this,
            "Erro ao excluir o manual:\n" + e.getMessage(),
            "Erro na Exclusão",
            javax.swing.JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_btnExcluirSelecionadoActionPerformed

    private void btnBaixarSelecionadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBaixarSelecionadoActionPerformed
        int linhaSelecionada = jTable1.getSelectedRow();
    
    // Verifica se uma linha foi selecionada
    if (linhaSelecionada == -1) {
        javax.swing.JOptionPane.showMessageDialog(this,
            "Por favor, selecione um manual para baixar.",
            "Nenhum Manual Selecionado",
            javax.swing.JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    try {
        // Obtém os dados da linha selecionada
        String nomeArquivo = jTable1.getValueAt(linhaSelecionada, 1).toString();
        String caminhoArquivoOrigem = jTable1.getValueAt(linhaSelecionada, 3).toString();
        
        System.out.println("Iniciando download do arquivo: " + caminhoArquivoOrigem);
        
        // Verifica se o arquivo de origem existe
        File arquivoOrigem = new File(caminhoArquivoOrigem);
        if (!arquivoOrigem.exists()) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Arquivo não encontrado no servidor:\n" + caminhoArquivoOrigem +
                "\n\nO arquivo pode ter sido movido ou excluído.",
                "Arquivo Não Encontrado",
                javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Cria um seletor de arquivos para escolher onde salvar
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Salvar arquivo como...");
        fileChooser.setSelectedFile(new File(nomeArquivo)); // Sugere o nome original
        
        // Configura o filtro para manter a extensão original
        String extensao = getFileExtension(nomeArquivo);
        if (extensao != null && !extensao.isEmpty()) {
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Arquivos " + extensao.toUpperCase() + " (*." + extensao + ")", extensao);
            fileChooser.setFileFilter(filter);
        }
        
        // Mostra o diálogo de salvar
        int userSelection = fileChooser.showSaveDialog(this);
        
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File arquivoDestino = fileChooser.getSelectedFile();
            
            // Garante que o arquivo tenha a extensão correta
            if (extensao != null && !extensao.isEmpty() && 
                !arquivoDestino.getName().toLowerCase().endsWith("." + extensao.toLowerCase())) {
                arquivoDestino = new File(arquivoDestino.getAbsolutePath() + "." + extensao);
            }
            
            // Verifica se o arquivo de destino já existe
            if (arquivoDestino.exists()) {
                int overwrite = javax.swing.JOptionPane.showConfirmDialog(this,
                    "O arquivo \"" + arquivoDestino.getName() + "\" já existe.\n" +
                    "Deseja substituí-lo?",
                    "Arquivo Existente",
                    javax.swing.JOptionPane.YES_NO_OPTION,
                    javax.swing.JOptionPane.WARNING_MESSAGE);
                
                if (overwrite != javax.swing.JOptionPane.YES_OPTION) {
                    return; // Usuário não quer substituir
                }
            }
            
            // Realiza a cópia do arquivo
            boolean sucesso = copiarArquivo(arquivoOrigem, arquivoDestino);
            
            if (sucesso) {
                javax.swing.JOptionPane.showMessageDialog(this,
                    "Arquivo baixado com sucesso!\n\n" +
                    "Salvo em: " + arquivoDestino.getAbsolutePath(),
                    "Download Concluído",
                    javax.swing.JOptionPane.INFORMATION_MESSAGE);
                
                System.out.println("Download concluído: " + arquivoDestino.getAbsolutePath());
                
                // Opcional: abrir a pasta onde o arquivo foi salvo
                int abrirPasta = javax.swing.JOptionPane.showConfirmDialog(this,
                    "Deseja abrir a pasta onde o arquivo foi salvo?",
                    "Abrir Pasta",
                    javax.swing.JOptionPane.YES_NO_OPTION);
                
                if (abrirPasta == javax.swing.JOptionPane.YES_OPTION) {
                    abrirPastaNoExplorador(arquivoDestino.getParentFile());
                }
                
            } else {
                javax.swing.JOptionPane.showMessageDialog(this,
                    "Erro ao baixar o arquivo.\n" +
                    "Verifique as permissões do diretório de destino.",
                    "Erro no Download",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        } else {
            System.out.println("Download cancelado pelo usuário.");
        }
        
    } catch (Exception e) {
        System.err.println("Erro durante o download: " + e.getMessage());
        e.printStackTrace();
        
        javax.swing.JOptionPane.showMessageDialog(this,
            "Erro durante o download:\n" + e.getMessage(),
            "Erro no Download",
            javax.swing.JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_btnBaixarSelecionadoActionPerformed
 
    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {                                           
    filtrarManuais();
}
    

private void filtrarManuais() {
    if (!verificarConexao()) {
        return;
    }
    
    try {
        String filtroDescricao = jTextField1.getText().trim();
        String setorSelecionado = (String) jComboBox1.getSelectedItem();
        
        StringBuilder sql = new StringBuilder(
            "SELECT id_manual, nome_arquivo as descricao, setor, caminho_arquivo as link, data_upload " +
            "FROM manuais WHERE 1=1"
        );
        
        // Filtro por descrição (nome do arquivo)
        if (!filtroDescricao.isEmpty()) {
            sql.append(" AND nome_arquivo LIKE ?");
        }
        
        // Filtro por setor
        if (!"Todos".equals(setorSelecionado)) {
            sql.append(" AND setor = ?");
        }
        
        sql.append(" ORDER BY data_upload DESC");
        
        PreparedStatement stmt = conexao.prepareStatement(sql.toString());
        
        int paramIndex = 1;
        if (!filtroDescricao.isEmpty()) {
            stmt.setString(paramIndex++, "%" + filtroDescricao + "%");
        }
        if (!"Todos".equals(setorSelecionado)) {
            stmt.setString(paramIndex++, setorSelecionado);
        }
        
        ResultSet rs = stmt.executeQuery();
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        
        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getInt("id_manual"),
                rs.getString("descricao"),
                rs.getString("setor"),
                rs.getString("link")
            });
        }
        
        rs.close();
        stmt.close();
        
        System.out.println("Filtro aplicado: " + model.getRowCount() + " registros encontrados");
        
    } catch (SQLException e) {
        System.err.println("Erro ao filtrar manuais: " + e.getMessage());
        e.printStackTrace();
    }
}
    
    private void carregarManuais() {
    if (!verificarConexao()) {
        return;
    }
    
    try {
        
        String sql = "SELECT id_manual, nome_arquivo as descricao, setor, caminho_arquivo as link, data_upload " +
                     "FROM manuais ORDER BY data_upload DESC";
        
        PreparedStatement stmt = conexao.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        
        // Obter o modelo da tabela
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0); // Limpa a tabela
        
        // Preencher a tabela com os dados
        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getInt("id_manual"),
                rs.getString("descricao"),
                rs.getString("setor"),
                rs.getString("link")
            });
        }
        
        rs.close();
        stmt.close();
        
        System.out.println("Manuais carregados com sucesso: " + model.getRowCount() + " registros");
        
    } catch (SQLException e) {
        System.err.println("Erro ao carregar manuais: " + e.getMessage());
        e.printStackTrace();
        javax.swing.JOptionPane.showMessageDialog(this,
            "Erro ao carregar manuais do banco de dados: " + e.getMessage(),
            "Erro de Banco de Dados", javax.swing.JOptionPane.ERROR_MESSAGE);
    }
    }
private boolean verificarConexao() {
    if (conexao == null) {
        javax.swing.JOptionPane.showMessageDialog(this,
            "Não há conexão com o banco de dados. Os manuais não podem ser carregados.",
            "Sem Conexão", javax.swing.JOptionPane.WARNING_MESSAGE);
        return false;
    }
    return true;
}
    private void visualizarDocumentoSelecionado() {
    int linhaSelecionada = jTable1.getSelectedRow();
    
    // Verifica se uma linha foi selecionada
    if (linhaSelecionada == -1) {
        javax.swing.JOptionPane.showMessageDialog(this,
            "Por favor, selecione um documento para visualizar.",
            "Nenhum Documento Selecionado",
            javax.swing.JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    try {
        // Obtém o caminho do arquivo da coluna "Link" (índice 3)
        String caminhoArquivo = jTable1.getValueAt(linhaSelecionada, 3).toString();
        String nomeArquivo = jTable1.getValueAt(linhaSelecionada, 1).toString();
        
        System.out.println("Tentando abrir arquivo: " + caminhoArquivo);
        
        // Verifica se o caminho do arquivo existe
        File arquivo = new File(caminhoArquivo);
        
        if (!arquivo.exists()) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Arquivo não encontrado no caminho:\n" + caminhoArquivo +
                "\n\nO arquivo pode ter sido movido ou excluído.",
                "Arquivo Não Encontrado",
                javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Tenta abrir o arquivo com o programa padrão do sistema
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            
            // Verifica se o arquivo pode ser aberto
            if (desktop.isSupported(Desktop.Action.OPEN)) {
                desktop.open(arquivo);
                System.out.println("Arquivo aberto com sucesso: " + nomeArquivo);
            } else {
                javax.swing.JOptionPane.showMessageDialog(this,
                    "Não é possível abrir o arquivo.\n" +
                    "A ação OPEN não é suportada no seu sistema.",
                    "Erro ao Abrir Arquivo",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Desktop não é suportado no seu sistema.\n" +
                "Não é possível abrir o arquivo automaticamente.",
                "Funcionalidade Não Suportada",
                javax.swing.JOptionPane.ERROR_MESSAGE);
        }
        
    } catch (IOException e) {
        System.err.println("Erro ao abrir arquivo: " + e.getMessage());
        e.printStackTrace();
        
        javax.swing.JOptionPane.showMessageDialog(this,
            "Erro ao abrir o arquivo:\n" + e.getMessage() +
            "\n\nVerifique se o arquivo existe e se há um programa associado para abri-lo.",
            "Erro ao Abrir Arquivo",
            javax.swing.JOptionPane.ERROR_MESSAGE);
    } catch (Exception e) {
        System.err.println("Erro inesperado: " + e.getMessage());
        e.printStackTrace();
        
        javax.swing.JOptionPane.showMessageDialog(this,
            "Erro inesperado ao tentar abrir o arquivo:\n" + e.getMessage(),
            "Erro Inesperado",
            javax.swing.JOptionPane.ERROR_MESSAGE);
    }
}
    private boolean excluirArquivoFisico(String caminhoArquivo) {
    try {
        File arquivo = new File(caminhoArquivo);
        
        if (arquivo.exists()) {
            if (arquivo.delete()) {
                System.out.println("Arquivo físico excluído: " + caminhoArquivo);
                return true;
            } else {
                System.err.println("Não foi possível excluir o arquivo físico: " + caminhoArquivo);
                return false;
            }
        } else {
            System.out.println("Arquivo físico não encontrado: " + caminhoArquivo);
            return false; // Arquivo já não existe
        }
        
    } catch (SecurityException e) {
        System.err.println("Erro de segurança ao excluir arquivo: " + e.getMessage());
        return false;
    } catch (Exception e) {
        System.err.println("Erro ao excluir arquivo físico: " + e.getMessage());
        return false;
    }
}

private boolean excluirRegistroBanco(int idManual) {
    if (!verificarConexao()) {
        return false;
    }
    
    String sql = "DELETE FROM manuais WHERE id_manual = ?";
    
    try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
        stmt.setInt(1, idManual);
        int linhasAfetadas = stmt.executeUpdate();
        
        return linhasAfetadas > 0;
        
    } catch (SQLException e) {
        System.err.println("Erro ao excluir registro do banco: " + e.getMessage());
        e.printStackTrace();
        return false;
    }
}
/**
 * Obtém a extensão do arquivo a partir do nome
 */
private String getFileExtension(String fileName) {
    int lastDotIndex = fileName.lastIndexOf('.');
    if (lastDotIndex > 0 && lastDotIndex < fileName.length() - 1) {
        return fileName.substring(lastDotIndex + 1).toLowerCase();
    }
    return "";
}

/**
 * Copia o arquivo de origem para o destino
 */
private boolean copiarArquivo(File origem, File destino) {
    try {
        // Usa NIO para cópia mais eficiente
        Files.copy(origem.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);
        return true;
        
    } catch (Exception e) {
        System.err.println("Erro na cópia do arquivo: " + e.getMessage());
        
        // Fallback para método tradicional se NIO falhar
        return copiarArquivoTradicional(origem, destino);
    }
}

/**
 * Método tradicional de cópia de arquivo (fallback)
 */
private boolean copiarArquivoTradicional(File origem, File destino) {
    try (InputStream in = new FileInputStream(origem);
         OutputStream out = new FileOutputStream(destino)) {
        
        byte[] buffer = new byte[1024];
        int length;
        while ((length = in.read(buffer)) > 0) {
            out.write(buffer, 0, length);
        }
        return true;
        
    } catch (Exception e) {
        System.err.println("Erro na cópia tradicional: " + e.getMessage());
        return false;
    }
}

/**
 * Abre a pasta no explorador de arquivos do sistema
 */
private void abrirPastaNoExplorador(File pasta) {
    try {
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            if (desktop.isSupported(Desktop.Action.OPEN)) {
                desktop.open(pasta);
                System.out.println("Pasta aberta: " + pasta.getAbsolutePath());
            } else {
                System.err.println("Ação OPEN não suportada para pastas");
            }
        } else {
            System.err.println("Desktop não suportado");
        }
    } catch (Exception e) {
        System.err.println("Erro ao abrir pasta: " + e.getMessage());
    }
}


=======
    private void btnCriarNovoManSelecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCriarNovoManSelecActionPerformed
        new TelaCadastroManuais().setVisible(true); // Abre a tela de cadastro de manuais
        this.dispose(); // Fecha o dashboard
    }//GEN-LAST:event_btnCriarNovoManSelecActionPerformed

    private void btnVoltarManActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarManActionPerformed
        new TelaManuais().setVisible(true); // Abre a tela de manuais
        this.dispose(); // Fecha o dashboard
    }//GEN-LAST:event_btnVoltarManActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

>>>>>>> 06c877f18a4b3a1dda1dab94ab8544262e3d8241
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
            java.util.logging.Logger.getLogger(TelaManuaisCadastrados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaManuaisCadastrados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaManuaisCadastrados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaManuaisCadastrados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaManuaisCadastrados().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
<<<<<<< HEAD
    private javax.swing.JButton btnBaixarSelecionado;
    private javax.swing.JButton btnExcluirSelecionado;
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JButton btnVisualizarDoc;
=======
    private javax.swing.JButton btnAlterarSelecionado;
    private javax.swing.JButton btnCriarNovoManSelec;
    private javax.swing.JButton btnExcluirSelecionado;
    private javax.swing.JButton btnFiltrar;
>>>>>>> 06c877f18a4b3a1dda1dab94ab8544262e3d8241
    private javax.swing.JButton btnVoltarMan;
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
