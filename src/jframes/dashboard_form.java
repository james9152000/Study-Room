/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jframes;

import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
import javax.swing.table.TableModel;
/**
 *
 * @author pc
 */
public class dashboard_form extends javax.swing.JFrame {

   int xMouse;
   int yMouse;
   static Connection conn;
   static Statement stm;
   static ResultSet rs;
    
    public dashboard_form() {
        
        initComponents();
        connectionDB();
        readProfile();
        totalReviewer();
        
    }

    void connectionDB(){ // GETTING CONNECTION
        
        try{
            
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/reviewer_system", "root", "1234");
            stm = conn.createStatement();
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "NO CONNECTION " + e);
        }
        
    }
    
    
    void readProfile(){ // READ PROFILE IN THE DATABASE
       
        try{
            String strCommand = "SELECT name, age, gender, img FROM profile"; // SELECTING
            rs = stm.executeQuery(strCommand);
            while(rs.next()){
                String name = rs.getString("name"); // ASSIGNING TO LOCAL VARIABLE
                String age = rs.getString("age");
                String gender = rs.getString("gender");
                String img = rs.getString("img"); // ASSIGNING TO LOCAL VARIABLE
                
                txtName.setText(name); // DISPLAYING THE TEXT THAT FROM IN LOCAL VARIABLE
                txtAge.setText(age);
                txtGender.setText(gender);
                
                                     // GETTING IMG PATH FROM DATABASE
                Image getAbsolutePath = null; 
                ImageIcon icon = new ImageIcon(img); //ASSIGNING IMG PATH THAT FROM IN THE DATABASE
                Image image = icon.getImage().getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH); // IMAGE SIZE SETUP
                lblImage.setIcon(icon); // DISPLAYING IT
                
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Can't read data from database " + e);
        }
        
    }
    

   void totalReviewer(){ // DISPLAY TOTAL REVIEWER SAVE IN DATABASE
    
       start_review_form srf = new start_review_form(); //MAKE INSTANCE OF START_REVIEW_FORM
       int total = srf.getTotalReviewer();  // GETTING THE FUNCTION => GETTOTALREVIEWER(); THAT FROM IN START_REVIEW_FORM
       lblCountReviewer.setText(total + ""); // AND DISPLAY IT TO THE LBLCOUNTREVIEWER
       
   }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelHeader = new javax.swing.JPanel();
        lblMinimize = new javax.swing.JLabel();
        lblClose = new javax.swing.JLabel();
        lblDrag = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblStartReview = new javax.swing.JLabel();
        lblAddReviewer = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblImage = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtGender = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lblProfile = new javax.swing.JLabel();
        lblCountReviewer = new javax.swing.JLabel();
        txtName = new javax.swing.JLabel();
        txtAge = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        jPanel1.setBackground(new java.awt.Color(51, 153, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblStartReview.setBackground(new java.awt.Color(255, 255, 255));
        lblStartReview.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblStartReview.setForeground(new java.awt.Color(255, 255, 255));
        lblStartReview.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblStartReview.setText("Start Review");
        lblStartReview.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 255, 255), 2));
        lblStartReview.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblStartReviewMouseClicked(evt);
            }
        });
        jPanel1.add(lblStartReview, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 130, 300, 170));

        lblAddReviewer.setBackground(new java.awt.Color(255, 255, 255));
        lblAddReviewer.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblAddReviewer.setForeground(new java.awt.Color(255, 255, 255));
        lblAddReviewer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAddReviewer.setText("Add Reviewer");
        lblAddReviewer.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 255, 255), 2));
        lblAddReviewer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAddReviewerMouseClicked(evt);
            }
        });
        jPanel1.add(lblAddReviewer, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 320, 300, 170));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Back");
        jLabel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(966, 10, 100, 49));

        lblImage.setForeground(new java.awt.Color(255, 255, 255));
        lblImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImage.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel1.add(lblImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 140, 130));

        jLabel10.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Gender :");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, -1, -1));

        txtGender.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtGender.setForeground(new java.awt.Color(255, 255, 255));
        txtGender.setText("create");
        jPanel1.add(txtGender, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 70, -1, -1));

        jLabel12.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Age :");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 40, -1, -1));

        lblProfile.setBackground(new java.awt.Color(255, 255, 255));
        lblProfile.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblProfile.setForeground(new java.awt.Color(255, 255, 255));
        lblProfile.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblProfile.setText("Profile");
        lblProfile.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 255, 255), 2));
        lblProfile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblProfileMouseClicked(evt);
            }
        });
        jPanel1.add(lblProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 320, 300, 170));

        lblCountReviewer.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        lblCountReviewer.setForeground(new java.awt.Color(255, 255, 255));
        lblCountReviewer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCountReviewer.setText("0");
        lblCountReviewer.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 255, 255), 2));
        jPanel1.add(lblCountReviewer, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 130, 300, 170));

        txtName.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtName.setForeground(new java.awt.Color(255, 255, 255));
        txtName.setText("create");
        jPanel1.add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, -1, -1));

        txtAge.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtAge.setForeground(new java.awt.Color(255, 255, 255));
        txtAge.setText("create");
        jPanel1.add(txtAge, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 40, -1, -1));

        jLabel14.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Name :");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, -1, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Reviewer");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 140, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1080, 520));

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

    private void lblStartReviewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblStartReviewMouseClicked
        new start_review_form().setVisible(true);
        dispose();
    }//GEN-LAST:event_lblStartReviewMouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
         new main_form().setVisible(true);
         dispose();
    }//GEN-LAST:event_jLabel5MouseClicked

    private void lblAddReviewerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAddReviewerMouseClicked
        new add_reviewer_form().setVisible(true);
        dispose();
    }//GEN-LAST:event_lblAddReviewerMouseClicked

    private void lblProfileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblProfileMouseClicked
        new profile_form().setVisible(true);
        dispose();
    }//GEN-LAST:event_lblProfileMouseClicked

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
            java.util.logging.Logger.getLogger(dashboard_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dashboard_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dashboard_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dashboard_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new dashboard_form().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblAddReviewer;
    private javax.swing.JLabel lblClose;
    private javax.swing.JLabel lblCountReviewer;
    private javax.swing.JLabel lblDrag;
    private javax.swing.JLabel lblImage;
    private javax.swing.JLabel lblMinimize;
    private javax.swing.JLabel lblProfile;
    private javax.swing.JLabel lblStartReview;
    private javax.swing.JPanel panelHeader;
    private javax.swing.JLabel txtAge;
    private javax.swing.JLabel txtGender;
    private javax.swing.JLabel txtName;
    // End of variables declaration//GEN-END:variables
}
