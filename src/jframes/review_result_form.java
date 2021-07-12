/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jframes;

import java.awt.Color;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import javax.swing.JOptionPane;



/**
 *
 * @author james de guzman
 */
public class review_result_form extends javax.swing.JFrame {

    int yMouse;
    int xMouse;
    static Connection conn;
    static Statement stm;
    static ResultSet rs;
    reviewer_form rf = new reviewer_form();
    String correction[];
    public static String userAnswer[];
    int reviewerId = 0;
    
    
    public review_result_form() {
        
        initComponents();
        connectionDB();
        getReviewerId();
        this.userAnswer = new String[Integer.parseInt(lblTotalQuestion.getText())];
        correctionKey();
        lblBack.setVisible(false);
       // JOptionPane.showMessageDialog(this, reviewerId);
    }
    
        public int getReviewerId(){
            return reviewerId = rf.getReviewerId();
            
        }
       

        void connectionDB(){
           
            try{
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/reviewer_system","root","1234");
                stm = conn.createStatement();
                lblTotalQuestion.setText(reviewer_form.lblItem.getText());
                
            }catch(Exception e){
                JOptionPane.showMessageDialog(this, "Please check your connection");
            }
            
        }
    
    
        void correctionKey(){
            
            correction = new String[Integer.parseInt(lblTotalQuestion.getText())]; 
       
        try{
            String strCommand = "SELECT correct_ans FROM questions WHERE id_question = " + reviewerId;
            rs = stm.executeQuery(strCommand);
            int iterate = 0;
            while(rs.next()){
                String correct = rs.getString("correct_ans");
                correction[iterate] = correct;
               iterate++;
            }
             
        }catch(Exception e){
          //  JOptionPane.showMessageDialog(this, "Error in getting answerkey " + e);
        }
    }
        
        public  void countCorrect(){
         int correct = 0;
         int wrong = 0;
           for(int i = 0; i < Integer.parseInt(lblTotalQuestion.getText()); i++){
               
               if(userAnswer[i].equals(correction[i])){
                  correct += 1;
                   //JOptionPane.showMessageDialog(this, "CORRECT " + "userAnswer 1 " + userAnswer[i] + " correct Answer " + correction[i]);
               }else{
                  wrong += 1;
                  // JOptionPane.showMessageDialog(this, "WRONG " + "userAnswer 1 " + userAnswer[i] + " correct Answer " + correction[i]);
               }
               
           }
           
            lblCorrect.setText(correct + "");
            lblWrong.setText(wrong + "");
       
        }
        

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblBack = new javax.swing.JLabel();
        hi = new javax.swing.JLabel();
        panelHeader = new javax.swing.JPanel();
        lblMinimize = new javax.swing.JLabel();
        lblClose = new javax.swing.JLabel();
        lblDrag = new javax.swing.JLabel();
        panelMain = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblTotalQuestion = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblCorrect = new javax.swing.JLabel();
        lblWrong = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblBack.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblBack.setForeground(new java.awt.Color(255, 255, 255));
        lblBack.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBack.setText("Back");
        lblBack.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 255, 255)));
        lblBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBackMouseClicked(evt);
            }
        });
        getContentPane().add(lblBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 50, 103, 39));

        hi.setBackground(new java.awt.Color(255, 255, 255));
        hi.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        hi.setForeground(new java.awt.Color(102, 102, 102));
        hi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        hi.setText("Click me to see result");
        hi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 255, 255)));
        hi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hiMouseClicked(evt);
            }
        });
        getContentPane().add(hi, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1080, 500));

        panelHeader.setBackground(new java.awt.Color(255, 255, 255));
        panelHeader.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblMinimize.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblMinimize.setForeground(new java.awt.Color(51, 153, 255));
        lblMinimize.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMinimize.setText("-");
        lblMinimize.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 255), 2));
        lblMinimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMinimizeMouseClicked(evt);
            }
        });
        panelHeader.add(lblMinimize, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 10, 30, 20));

        lblClose.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblClose.setForeground(new java.awt.Color(51, 153, 255));
        lblClose.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblClose.setText("X");
        lblClose.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 255), 2));
        lblClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCloseMouseClicked(evt);
            }
        });
        panelHeader.add(lblClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 10, 30, 20));

        lblDrag.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDrag.setText("Drag and Drop");
        lblDrag.setToolTipText("");
        lblDrag.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                lblDragMouseDragged(evt);
            }
        });
        lblDrag.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblDragMousePressed(evt);
            }
        });
        panelHeader.add(lblDrag, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 40));

        getContentPane().add(panelHeader, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1074, 40));

        panelMain.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Wrong Answer :");

        lblTotalQuestion.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        lblTotalQuestion.setForeground(new java.awt.Color(255, 255, 255));
        lblTotalQuestion.setText("10");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Correct Answer:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Total Question :");

        lblCorrect.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        lblCorrect.setForeground(new java.awt.Color(255, 255, 255));
        lblCorrect.setText("10");

        lblWrong.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        lblWrong.setForeground(new java.awt.Color(255, 255, 255));
        lblWrong.setText("10");

        javax.swing.GroupLayout panelMainLayout = new javax.swing.GroupLayout(panelMain);
        panelMain.setLayout(panelMainLayout);
        panelMainLayout.setHorizontalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMainLayout.createSequentialGroup()
                .addContainerGap(396, Short.MAX_VALUE)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(lblWrong))
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblCorrect))
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblTotalQuestion)))
                .addGap(226, 226, 226))
        );
        panelMainLayout.setVerticalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMainLayout.createSequentialGroup()
                .addGap(114, 114, 114)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotalQuestion)
                    .addComponent(jLabel4))
                .addGap(37, 37, 37)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblCorrect))
                .addGap(43, 43, 43)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblWrong))
                .addContainerGap(160, Short.MAX_VALUE))
        );

        getContentPane().add(panelMain, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1080, 500));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lblMinimizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMinimizeMouseClicked
        this.setState(this.ICONIFIED);
    }//GEN-LAST:event_lblMinimizeMouseClicked

    private void lblCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCloseMouseClicked

        int response = JOptionPane.showConfirmDialog(this, "Are you sure to exit?", "Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(response == JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_lblCloseMouseClicked

    private void lblDragMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDragMouseDragged

        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();

        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_lblDragMouseDragged

    private void lblDragMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDragMousePressed

        yMouse = evt.getY();
        xMouse = evt.getX();
    }//GEN-LAST:event_lblDragMousePressed

    private void hiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hiMouseClicked
         
        
        countCorrect();
        panelMain.setBackground(new Color(51,153,255));
        lblBack.setVisible(true);
        hi.setVisible(false);
        //   JOptionPane.showMessageDialog(this, "userAnswer length " + userAnswer[0].length() + " correct Answer length" + correction[0].length());
        //  JOptionPane.showMessageDialog(this, "userAnswer 1 " + userAnswer[0] + " correct Answer " + correction[0]);
        //  JOptionPane.showMessageDialog(this, "userAnswer 2 " + userAnswer[1] + " correct Answer " + correction[1]);
        //  JOptionPane.showMessageDialog(this, "userAnswer 3 " + userAnswer[2] + " correct Answer " + correction[2]);
        //  JOptionPane.showMessageDialog(this,"userAnswer 4 " +  userAnswer[3] + " correct Answer " + correction[3]);
           
         
    }//GEN-LAST:event_hiMouseClicked

    private void lblBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBackMouseClicked
        new start_review_form().setVisible(true);
        dispose();
    }//GEN-LAST:event_lblBackMouseClicked

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
            java.util.logging.Logger.getLogger(review_result_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(review_result_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(review_result_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(review_result_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new review_result_form().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JLabel hi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lblBack;
    private javax.swing.JLabel lblClose;
    private javax.swing.JLabel lblCorrect;
    private javax.swing.JLabel lblDrag;
    private javax.swing.JLabel lblMinimize;
    private javax.swing.JLabel lblTotalQuestion;
    private javax.swing.JLabel lblWrong;
    private javax.swing.JPanel panelHeader;
    private javax.swing.JPanel panelMain;
    // End of variables declaration//GEN-END:variables
}
