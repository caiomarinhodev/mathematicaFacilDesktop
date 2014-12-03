/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.views;

import com.controls.JavaConnect;
import com.controls.Sistema;
import com.model.Pergunta;
import com.model.Usuario;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Caio
 */
public class TelaEntradaUser extends javax.swing.JFrame {

    /**
     * Creates new form TelaEntrada
     */
    Connection conn=null;
    ResultSet rs=null;
    PreparedStatement pst=null;
    private Usuario usuario;
    private Sistema sistema;
    private Pergunta perguntaAtual;
    public TelaEntradaUser(Usuario u, Sistema s) {
        initComponents();
        this.usuario = u;
        this.sistema = s;
        this.perguntaAtual = null;
        conn = JavaConnect.ConnectDb();
        update();
        getListaPerguntas();
        exibepergunta();
    }
    
    private void update(){
        System.out.println("UPDATE INTERFACE ...");
        campoUsuarioAtual.setText(this.usuario.getConta().getLogin());
        setPontos(this.usuario.getPontuacaoAtual());
    }
    
    private void setPontos(int p){

        campoPontuacao.setText(String.valueOf(p));
    }
    
    private void setPergunta(String p){
        campoPergunta.setText(p);
    }
    
    
    
   /* public void updatePontosUsuarioBD(){
        int atual = Integer.parseInt(campoPontuacao.getText());
        String sql = "update usuarios set pontuacao='"+atual+"' where id='"+this.usuario.getId()+"' ";
        try {
            pst = conn.prepareStatement(sql);
            pst.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao UPDATE PONTOS USUARIOS");
        }
    }*/
    
    public void updateNoBD(String table, String campo, String id, String valorAalterar){
        String sql = "update "+table+" set "+campo+"='"+valorAalterar+"' where id='"+id+"' ";
        try {
            pst = conn.prepareStatement(sql);
            pst.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar UPDATE Geral");
        }
    }
    
    public void insertHistoricoNoBD(int status){
        try{
           String sql = "Insert into historico (idusuario,idpergunta,pergunta,resposta,status) values (?,?,?,?,?)";
           pst = conn.prepareStatement(sql);
           pst.setInt(1, this.usuario.getId());
           pst.setInt(2, this.perguntaAtual.getId());
           pst.setString(3, this.perguntaAtual.getPergunta());
           pst.setString(4, campoResposta.getText());
           pst.setInt(5, status);
          
           
           pst.execute();
           //JOptionPane.showMessageDialog(null, "Save!");
           
           
           
       }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
       }
    }
    
    /*private void updatePerguntaNoDB(int idPergunta){
        int v5 = 1;
        String sql = "update perguntas set status='"+v5+"' where id='"+idPergunta+"' ";
        try {
            pst = conn.prepareStatement(sql);
            pst.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao UPDATE PERGUNTA");
        }
            
        
    }*/
    
    private void exibepergunta(){
        if(this.sistema.getListaDePerguntas().size()!=0){
            btnEnviar.setEnabled(true);
            Pergunta p = this.sistema.getListaDePerguntas().get(0);
            this.perguntaAtual = p;
            setPergunta(p.getPergunta());
        }else{
            setPergunta("NÃO HÁ MAIS PERGUNTAS");
            btnEnviar.setEnabled(false);
        }
        
    }
    
    private void getListaPerguntas(){
        System.out.println("PEGANDO LISTA DE PERGUNTAS ...");
        String sql = "select * from perguntas where status=?";
        try{
            pst = conn.prepareStatement(sql);
            pst.setString(1, "0");            
            rs = pst.executeQuery();
            if(rs.next()){
                Pergunta p = new Pergunta();
                p.setId(rs.getInt("id"));
                p.setNivel(rs.getInt("nivel"));
                p.setPergunta(rs.getString("pergunta"));
                p.setResposta(rs.getInt("resposta"));
                p.setStatus(rs.getInt("status"));
                this.sistema.getListaDePerguntas().add(p);
            }else{
                JOptionPane.showMessageDialog(null, "APERTE OK!");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        clear();
    }
    
    
    
    private void clear(){
        campoResposta.setText("");
    }
    
    private void enviar(){
        if(confereResposta()){
            insertHistoricoNoBD(1);
            setPontosVisual();
            updateNoBD("usuarios", "pontuacao", String.valueOf(this.usuario.getId()), this.campoPontuacao.getText().toString());
            //updatePerguntaNoDB(perguntaAtual.getId());
            updateNoBD("perguntas", "status", String.valueOf(this.perguntaAtual.getId()), "1");
            refreshListaPerguntas();
            
            setPerguntaVisual();
            clear();
        }else{
            insertHistoricoNoBD(0);
            clear();
            JOptionPane.showMessageDialog(null, "RESPOSTA ERRADA! \n Tente Novamente!");
            
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        campoPontuacao = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        campoUsuarioAtual = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        campoPergunta = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnEnviar = new javax.swing.JButton();
        campoResposta = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("MATHEMATICA FACIL");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icons/hEAD.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 10, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("PONTUAÇÃO:");

        campoPontuacao.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        campoPontuacao.setForeground(new java.awt.Color(153, 0, 153));
        campoPontuacao.setText("0");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("USUÁRIO:");

        campoUsuarioAtual.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        campoUsuarioAtual.setForeground(new java.awt.Color(255, 0, 0));
        campoUsuarioAtual.setText("SHAUHUHHHHHSHUASHUSHAHUASHU");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icons/1417490360_exit.png"))); // NOI18N
        jButton1.setText("SAIR");
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoUsuarioAtual)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 157, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(29, 29, 29)
                .addComponent(campoPontuacao)
                .addGap(109, 109, 109)
                .addComponent(jButton1)
                .addGap(159, 159, 159))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(20, 20, 20))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(campoUsuarioAtual)
                    .addComponent(campoPontuacao)
                    .addComponent(jLabel7))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "PERGUNTA", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Aharoni", 1, 24), new java.awt.Color(0, 102, 102))); // NOI18N

        campoPergunta.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        campoPergunta.setText("jLabel2");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(272, 272, 272)
                .addComponent(campoPergunta)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(campoPergunta)
                .addGap(0, 70, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("RESPOSTA:");

        btnEnviar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icons/1417490805_Play1Disabled.png"))); // NOI18N
        btnEnviar.setEnabled(false);
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });

        campoResposta.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        campoResposta.setForeground(new java.awt.Color(255, 0, 51));
        campoResposta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campoRespostaKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoResposta, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEnviar)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEnviar)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(campoResposta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(298, 298, 298)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
        new TelaLogin().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarActionPerformed
        enviar();
        
    }//GEN-LAST:event_btnEnviarActionPerformed

    private void campoRespostaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoRespostaKeyPressed
        if(evt.getID()==KeyEvent.VK_ENTER){
            enviar();
        }
    }//GEN-LAST:event_campoRespostaKeyPressed

    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEnviar;
    private javax.swing.JLabel campoPergunta;
    private javax.swing.JLabel campoPontuacao;
    private javax.swing.JTextField campoResposta;
    private javax.swing.JLabel campoUsuarioAtual;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    // End of variables declaration//GEN-END:variables

    private void setPerguntaVisual() {
        exibepergunta();
    }

    private boolean confereResposta() {
        int resp = perguntaAtual.getResposta();
        int o = Integer.parseInt(campoResposta.getText());
        return o==resp;
        
    }

    private void setPontosVisual() {
        int atual = Integer.parseInt(campoPontuacao.getText());
        setPontos(atual+1);
    }

    private void refreshListaPerguntas() {
        this.sistema.getListaDePerguntas().clear();
        this.sistema.setListaDePerguntas(new ArrayList<Pergunta>());
        getListaPerguntas();
    }
}
