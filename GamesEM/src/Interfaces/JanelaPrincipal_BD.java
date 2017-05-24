//Emmanuel Capelini Magalhães RA:1351559
//Marcelo Caetano Mota RA:1349759
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Codigos.Cliente;
import Codigos.Hardware;
import Codigos.ImprimeObjeto;
import Codigos.ManipuladorArquivos;
import Codigos.Software;
import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.JOptionPane;
import java.sql.*;
import java.util.Vector;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author marcelo
 */
public class JanelaPrincipal_BD extends javax.swing.JFrame {

    /**
     * Creates new form JanelaPrincipal
     */
    public JanelaPrincipal_BD() {
        initComponents();
        AcessoAoBanco();
    }

    //Realiza a conexao com o banco de dados
    public boolean AcessoAoBanco(){
        try{
            String usuario = "postgres";
            String senha = "postgres";
            Class.forName("org.postgresql.Driver");
            String urlConexao = "jdbc:postgresql://127.0.0.1/LojaDeGames";
            conexao = DriverManager.getConnection(urlConexao, usuario, senha);
            conexao.setAutoCommit(true); //O padrão normalmente é false. Estamos usando  o true por propósitos de teste
            DatabaseMetaData dbmt = conexao.getMetaData();
            System.out.println("Nome do BD: " + dbmt.getDatabaseProductName());
            System.out.println("Versao do BD: " + dbmt.getDatabaseProductVersion());
            System.out.println("URL: " + dbmt.getURL());
            System.out.println("Driver: " + dbmt.getDriverName());
            System.out.println("Versao Driver: " + dbmt.getDriverVersion());
            System.out.println("Usuario: " + dbmt.getUserName());
        }
        catch(ClassNotFoundException erro)
        {
            System.out.println("Falha no carregamento do Driver "+erro);
            return false;
        }
        catch(SQLException erro)
        {
            System.out.println("Falha na conexão, comando SQL" +erro);
            return false;
        }
        return true;
    }
    
    //Função de montagem de tabela, essa é a do professor. Implementar a minha posteriormente.
    private void ExibeTabela(ResultSet rs) throws SQLException {
        Vector cabecalhos = new Vector();
        Vector registros = new Vector();
        if (!rs.first()) {
            atualizaTabela(cabecalhos, registros);
            JOptionPane.showMessageDialog(this, "O ResultSet esta vazio.");
            return;
        }
        try {
            //obtem as informacoes sobre o banco de dados.
            DatabaseMetaData dbmt = conexao.getMetaData();
            System.out.println("Nome do BD: " + dbmt.getDatabaseProductName());

            // obtem titulos de coluna
            ResultSetMetaData rsmd = rs.getMetaData();
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                cabecalhos.addElement(rsmd.getColumnName(i));
            }
            // obtem dados da linha
            do {
                registros.addElement(ProximoRegistro(rs, rsmd));
            } while (rs.next());
            atualizaTabela(cabecalhos, registros);

        } catch (SQLException erro) {
            System.out.println("Erro Exibe Tabela = " + erro);
        }
    }

    public void atualizaTabela(Vector cabecalhos, Vector registros) {
        if (tabela != null) {
            remove(tabela);
            remove(scroller);
        }
        tabela = new JTable(registros, cabecalhos);
        // exibe a tabela com conteudos de ResultSet
        scroller = new JScrollPane(tabela);
        add(scroller);
        scroller.setBounds(30, 380, 700, 300);
        validate();
    }
        private Vector ProximoRegistro(ResultSet rs, ResultSetMetaData rsmd) throws SQLException {
        Vector registro = new Vector();
        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            //A classe Types eh uma classo do pacote java.sql
            if (rsmd.getColumnType(i) == Types.VARCHAR
                    || rsmd.getColumnClassName(i).equalsIgnoreCase("java.lang.String")) {//para string
                registro.addElement(rs.getString(i));
            } else if (rsmd.getColumnType(i) == Types.DOUBLE) {//para doble precision
                registro.addElement(rs.getDouble(i));
            } else {
                //tratamento para os tipos que serao lidos do banco de dados.
            }
        }
        return registro;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        comboBox1 = new javax.swing.JComboBox<>();
        btnCarregar = new javax.swing.JButton();
        btnApagar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnEditar = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        menuNovoHardware = new javax.swing.JMenuItem();
        menuNovoCliente = new javax.swing.JMenuItem();
        menuNovoSoftware = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        menuVerHardware = new javax.swing.JMenuItem();
        menuVerCliente = new javax.swing.JMenuItem();
        menuVerSoftware = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        comboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hardware", "Cliente", "Software" }));
        comboBox1.setToolTipText("Selecione o item para ser exibido na área de texto");
        comboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBox1ActionPerformed(evt);
            }
        });

        btnCarregar.setText("Carregar");
        btnCarregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCarregarActionPerformed(evt);
            }
        });

        btnApagar.setText("Apagar");
        btnApagar.setFocusable(false);
        btnApagar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnApagar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnApagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApagarActionPerformed(evt);
            }
        });

        jLabel1.setText("Selecione o item");

        jLabel2.setText("Selecione a Ação");

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnNovo.setText("Adicionar");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        jMenu1.setText("Arquivo");

        jMenu3.setText("Adicionar");

        menuNovoHardware.setText("Hardware");
        menuNovoHardware.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuNovoHardwareActionPerformed(evt);
            }
        });
        jMenu3.add(menuNovoHardware);

        menuNovoCliente.setText("Cliente");
        menuNovoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuNovoClienteActionPerformed(evt);
            }
        });
        jMenu3.add(menuNovoCliente);

        menuNovoSoftware.setText("Software");
        menuNovoSoftware.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuNovoSoftwareActionPerformed(evt);
            }
        });
        jMenu3.add(menuNovoSoftware);

        jMenu1.add(jMenu3);

        jMenu4.setText("Carregar");

        menuVerHardware.setText("Hardware");
        menuVerHardware.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuVerHardwareActionPerformed(evt);
            }
        });
        jMenu4.add(menuVerHardware);

        menuVerCliente.setText("Cliente");
        menuVerCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuVerClienteActionPerformed(evt);
            }
        });
        jMenu4.add(menuVerCliente);

        menuVerSoftware.setText("Software");
        menuVerSoftware.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuVerSoftwareActionPerformed(evt);
            }
        });
        jMenu4.add(menuVerSoftware);

        jMenu1.add(jMenu4);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addComponent(btnCarregar)
                        .addGap(99, 99, 99)
                        .addComponent(btnNovo)
                        .addGap(68, 68, 68)
                        .addComponent(btnEditar)
                        .addGap(76, 76, 76)
                        .addComponent(btnApagar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(327, 327, 327)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(246, 246, 246)
                        .addComponent(comboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(324, 324, 324)
                        .addComponent(jLabel1)))
                .addContainerGap(163, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(jLabel1)
                .addGap(29, 29, 29)
                .addComponent(comboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnCarregar)
                        .addComponent(btnNovo)
                        .addComponent(btnEditar))
                    .addComponent(btnApagar))
                .addContainerGap(465, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCarregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCarregarActionPerformed
        int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
        int concorrencia = ResultSet.CONCUR_UPDATABLE;
        switch(comboBox1.getSelectedIndex())
        {
            case 0: 
            {
                try{
            
                    consulta = conexao.prepareCall("{call consultahardware()}", tipo, concorrencia);
                    resultadoConsulta = consulta.executeQuery();
            
                }
                    catch(SQLException erro){
                    System.out.println("Erro executando stored procedure no botão carregar: "+erro);
        }
                break;
            }
            case 1:
                {
                try{
            
                    consulta = conexao.prepareCall("{call consultaclientes()}", tipo, concorrencia);
                    resultadoConsulta = consulta.executeQuery();
            
                }
                    catch(SQLException erro){
                    System.out.println("Erro executando stored procedure no botão carregar: "+erro);
        }
                break;
            }
            case 2:
                {
                try{
            
                    consulta = conexao.prepareCall("{call consultasoftware()}", tipo, concorrencia);
                    resultadoConsulta = consulta.executeQuery();
            
                }
                    catch(SQLException erro){
                    System.out.println("Erro executando stored procedure no botão carregar: "+erro);
        }
                break;
            }
        }
        try {
            ExibeTabela(resultadoConsulta);
            System.out.println("A tabela foi carregada sem problemas");
        } catch (SQLException ex) {
            System.out.println("Erro gerando a tabela :"+ex);
        }
        
    }//GEN-LAST:event_btnCarregarActionPerformed

    private void menuVerSoftwareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuVerSoftwareActionPerformed
        // Carrega todo o conteudo do BD de software para a combobox
        // Na nova versão, carregará para uma table,a isso se esse menu não for excluído
    }//GEN-LAST:event_menuVerSoftwareActionPerformed

    private void comboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBox1ActionPerformed

    private void menuNovoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuNovoClienteActionPerformed
        NovoCliente_BD nc = new NovoCliente_BD();
        nc.setVisible(true);
    }//GEN-LAST:event_menuNovoClienteActionPerformed

    private void menuVerClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuVerClienteActionPerformed
        // Carrega todo o conteudo do BD de clientes para a combobox
        //Na nova versão, carregará para uma tabela, isso se ese menu não vor excluído
    }//GEN-LAST:event_menuVerClienteActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
       // Implementar edição de conteúdo aqui
       // Usará o  update table
       if(tabela!=null)
       {
           switch(comboBox1.getSelectedIndex())
        {
            case 0: 
            {
                int linha = tabela.getSelectedRow();
                String nome = (String) tabela.getValueAt(linha, 0);
                System.out.println("O nome selecionado é :"+nome);
                EditarHardware_BD edhard = new EditarHardware_BD();
                edhard.nomeASerAlterado(nome);
                edhard.setVisible(true);
                break;
            }
            case 1:
                {
                int linha = tabela.getSelectedRow();
                String cpf = (String) tabela.getValueAt(linha, 3);
                System.out.println("O CPF selecionado é :"+cpf);
                EditarCliente_BD edcli = new EditarCliente_BD();
                edcli.setCPF(cpf);
                edcli.setVisible(true);
                break;
            }
            case 2:
                {
                int linha = tabela.getSelectedRow();
                String nome = (String) tabela.getValueAt(linha, 0);
                System.out.println("O nome selecionado é :"+nome);
                EditarSoftware_BD edsof = new EditarSoftware_BD();
                edsof.nomeASerAlterado(nome);
                edsof.setVisible(true);
                break;
            }
        }
       }
       else
           JOptionPane.showMessageDialog(this, "Ainda não foi carregada nenhuma tabela.");
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnApagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApagarActionPerformed
        //Deleta o conteúdo selecionado na ComboBox.
        //Pode estar obsoleto nessa nova versão. Veremos.
        
    }//GEN-LAST:event_btnApagarActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        switch(comboBox1.getSelectedIndex()){
            case 0:{
                NovoHardware_BD nh = new NovoHardware_BD();
                nh.setVisible(true);
                break;
            }
            case 1:{
                NovoCliente_BD nc = new NovoCliente_BD();
                nc.setVisible(true);
                break;
            }
            case 2:{
                NovoSoftware_BD ns = new NovoSoftware_BD();
                ns.setVisible(true);
                break;
            }
            default:{
                System.out.println("Calma que já que nois implementa");
                break;
            }
        }
    }//GEN-LAST:event_btnNovoActionPerformed

    private void menuNovoHardwareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuNovoHardwareActionPerformed
        NovoHardware_BD nh = new NovoHardware_BD();
        nh.setVisible(true);
    }//GEN-LAST:event_menuNovoHardwareActionPerformed

    private void menuNovoSoftwareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuNovoSoftwareActionPerformed
       NovoSoftware_BD ns = new NovoSoftware_BD();
        ns.setVisible(true);
    }//GEN-LAST:event_menuNovoSoftwareActionPerformed

    private void menuVerHardwareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuVerHardwareActionPerformed
        // Carrega todo o conteudo do BD de software para a combobox
        // Na nova versão, carregará para uma table,a isso se esse menu não for excluído
    }//GEN-LAST:event_menuVerHardwareActionPerformed

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
            java.util.logging.Logger.getLogger(JanelaPrincipal_BD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JanelaPrincipal_BD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JanelaPrincipal_BD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JanelaPrincipal_BD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JanelaPrincipal_BD().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnApagar;
    private javax.swing.JButton btnCarregar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JComboBox<String> comboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem menuNovoCliente;
    private javax.swing.JMenuItem menuNovoHardware;
    private javax.swing.JMenuItem menuNovoSoftware;
    private javax.swing.JMenuItem menuVerCliente;
    private javax.swing.JMenuItem menuVerHardware;
    private javax.swing.JMenuItem menuVerSoftware;
    // End of variables declaration//GEN-END:variables
    private int flagTipoObj = 0; // 0 - padrao; 1 - Hardware, 2 - Cliente, 3 - Software
    private Connection conexao = null;
    private CallableStatement consulta = null;
    private ResultSet resultadoConsulta = null;
    private JScrollPane scroller = null;
    private JTable tabela = null;
    
}
