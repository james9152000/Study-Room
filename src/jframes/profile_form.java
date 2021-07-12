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


/**
 *
 * @author pc
 */
public class profile_form extends javax.swing.JFrame {

    static Connection conn;
    static Statement stm;
    static ResultSet rs;
    int xMouse;
    int yMouse;
    String Imagefilename; // FOR IMAGE PATH
    int count = 0; // COUNT ID IN PROFILE DATABASE IF IT STILL 0 IT MEANS NO THAT THERE IS NO PROFILE YET
    int userId;
    
    
    public profile_form() {
        initComponents();
        connectionDB();
        haveProfile();
    }
    
    void connectionDB(){
        
       try{
           
           Class.forName("com.mysql.jdbc.Driver");
           conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/reviewer_system", "root", "1234");
           stm = conn.createStatement();
          
       }catch(Exception e){
           JOptionPane.showMessageDialog(this, "Have Connection " + e);
       }
        
    }
    
    void insert_updateProfile(){ // CREATE OR UPDATE PROFILE BASE ON THE CONDITION
        

       if(count > 0){ // IF ALREADY HAVE PROFILE EXECUTE THIS // IF COUNT IS GREATER THAN TO 0 IT MEANS THAT THE DATABASE HAVE ALREARY HAVE AN ACCOUNT
           
           String name = txtName.getText(); // GETTING THE TEXT IN TEXTBOX
           String age = txtAge.getText();
           String gender = txtGender.getText();
           
            Image getAbsolutePath = null;
            ImageIcon icon = new ImageIcon(Imagefilename);
            Image image = icon.getImage().getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH);
            lblImage.setIcon(icon);
           
            Imagefilename = Imagefilename.replace("\\","\\\\"); // USE THIS TO HAVE BACKSLASH IN IMAGE PATH IN THE DATABASE
           
           try{
               String strCommand = "UPDATE profile SET name = '"+name+"', age  = '"+age+"', gender  = '"+gender+"', img = '"+Imagefilename+"' WHERE id = " + userId;
               stm.execute(strCommand);
               JOptionPane.showMessageDialog(this, "Success to Update");
              
               new dashboard_form().setVisible(true);
               dispose();
               
           }catch(Exception e){
               JOptionPane.showMessageDialog(this, "Can't update profile " + e);
           }
           
       }else{ // IF NO PROFILE EXECUTE THIS
          
        String name = txtName.getText();
        String age = txtAge.getText();
        String gender = txtGender.getText();
        String img = Imagefilename;
        img = img.replace("\\","\\\\");
        
        try{
            String strCommand = "INSERT INTO profile(name, age, gender, img) VALUES('"+name+"', '"+age+"', '"+gender+"', '"+img+"')";
            stm.execute(strCommand);
            stm.close();
            JOptionPane.showMessageDialog(this, "Success to insert account");
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Failed to insert account" + e);
        }
       }
    } // FUNCTION END
    
   void haveProfile(){ // CHECK IF THE USER HAVE ALREADY HAVE AN ACCOUNT
       
        try{
           
            String strCommand = "SELECT id FROM profile"; // SELECT ID IN THE PROFILE DATABASE
            rs = stm.executeQuery(strCommand);
            
            while(rs.next()){ //
              
                count += 1; // IF THERE ONE ID GET IN THE DATABASE. THE COUNT GLOBAL VARIABLE WILL INCREMENT
                userId = rs.getInt("id"); // GETTING THE ID, ASSIGN IT TO USERID GLOBAL VARIABLE.
             
            }
            
            if(count > 0){ // IF COUNT IS GREATER THAN TO 0 IT MEANS THAT USER ALREADY HAVE PROFILE
                
                lblButton.setText("Update Profile"); // SET TO BUTTON
                
            try{
                String strProfile = "SELECT name, age, gender, img FROM profile";   //NOW SINCE IT HAVE ID NEXT GET ALL THE DATA AND DISPLAYING IT.
                rs = stm.executeQuery(strProfile);
                while(rs.next()){
                    String name = rs.getString("name"); // ASSIGNING IT TO LOCAL VARIABLE
                    String age = rs.getString("age");
                    String gender = rs.getString("gender");
                    Imagefilename = rs.getString("img"); // GETTING THE IMAGE PATH THAT FROM IN THE DATABASE

                    txtName.setText(name); // DISPLAYING STRING
                    txtAge.setText(age);// DISPLAYING STRING
                    txtGender.setText(gender);// DISPLAYING STRING

                    Image getAbsolutePath = null; // FOR IMAGE DISPLAYING 
                    ImageIcon icon = new ImageIcon(Imagefilename);
                    Image image = icon.getImage().getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH);
                    lblImage.setIcon(icon); // DISPLAYING IMAGE
                
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Can't read data from database " + e);
        }
        
            }else{ // IF COUNT IS EQUALS TO 0 IT MEANS THAT THERE IS NO ACCOUNT IN THE DATABASE
                
                lblButton.setText("Create Profile"); // DISPLAY IN THE BUTTON
                 
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Failed to read name " + e);
        }
      
        
   }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblImage = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lblButton = new javax.swing.JLabel();
        lblInsertButton = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        txtAge = new javax.swing.JTextField();
        txtGender = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        lblDrag = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        setType(java.awt.Window.Type.POPUP);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(51, 153, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 5));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblImage.setForeground(new java.awt.Color(255, 255, 255));
        lblImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImage.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel1.add(lblImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, 140, 130));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Name :");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 240, -1, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Age :");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 280, -1, -1));

        lblButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblButton.setForeground(new java.awt.Color(255, 255, 255));
        lblButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblButton.setText("Update Profile");
        lblButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 255, 255), 2));
        lblButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblButtonMouseClicked(evt);
            }
        });
        jPanel1.add(lblButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 380, 270, 50));

        lblInsertButton.setBackground(new java.awt.Color(255, 255, 255));
        lblInsertButton.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        lblInsertButton.setForeground(new java.awt.Color(255, 255, 255));
        lblInsertButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblInsertButton.setText("Insert Image");
        lblInsertButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 255, 255)));
        lblInsertButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblInsertButtonMouseClicked(evt);
            }
        });
        jPanel1.add(lblInsertButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 110, 90, 40));

        txtName.setBackground(new java.awt.Color(51, 153, 255));
        txtName.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtName.setForeground(new java.awt.Color(255, 255, 255));
        txtName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 255, 255)));
        jPanel1.add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 230, 270, 25));

        txtAge.setBackground(new java.awt.Color(51, 153, 255));
        txtAge.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtAge.setForeground(new java.awt.Color(255, 255, 255));
        txtAge.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 255, 255)));
        jPanel1.add(txtAge, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 270, 130, 25));

        txtGender.setBackground(new java.awt.Color(51, 153, 255));
        txtGender.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtGender.setForeground(new java.awt.Color(255, 255, 255));
        txtGender.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 255, 255)));
        jPanel1.add(txtGender, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 310, 130, 25));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Gender :");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 320, -1, -1));

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
        jPanel1.add(lblDrag, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 540, 40));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Back");
        jLabel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 255, 255)));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 10, 110, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 660, 480));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lblInsertButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblInsertButtonMouseClicked

        JFileChooser chooser = new JFileChooser(); // CREATE INSTANCE OF JFILECHOOSER
        chooser.showOpenDialog(null); // OPENING FILE DIALOG
        File f = chooser.getSelectedFile(); // GETTING THE SELECTED FILE IN THE FILE DIALOG
        Imagefilename = f.getAbsolutePath(); // AND GETTING THE IMAGE PATH
        Image getAbsolutePath = null;
        ImageIcon icon = new ImageIcon(Imagefilename);
        Image image = icon.getImage().getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH);
        lblImage.setIcon(icon);

    }//GEN-LAST:event_lblInsertButtonMouseClicked

    private void lblDragMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDragMousePressed

        yMouse = evt.getY();
        xMouse = evt.getX();
    }//GEN-LAST:event_lblDragMousePressed

    private void lblDragMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDragMouseDragged

        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();

        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_lblDragMouseDragged

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
       
        new dashboard_form().setVisible(true);
        
        dispose();
    }//GEN-LAST:event_jLabel5MouseClicked

    private void lblButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblButtonMouseClicked
        insert_updateProfile();
    }//GEN-LAST:event_lblButtonMouseClicked

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
            java.util.logging.Logger.getLogger(profile_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(profile_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(profile_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(profile_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new profile_form().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblButton;
    private javax.swing.JLabel lblDrag;
    private javax.swing.JLabel lblImage;
    public static javax.swing.JLabel lblInsertButton;
    private javax.swing.JTextField txtAge;
    private javax.swing.JTextField txtGender;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables
}
