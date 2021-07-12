
package jframes;

import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author james de guzman
 */
public class start_review_form extends javax.swing.JFrame {

    int xMouse;
    int yMouse;
    static Connection conn;
    static Statement stm;
    static ResultSet rs;
  
    
    public start_review_form() {
        initComponents();
        connectionDB();
        readData();
        getTotalReviewer();
    }

   void connectionDB(){
      
      try{
          
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/reviewer_system","root","1234");
        stm = conn.createStatement();
        
      }catch(Exception e){
        JOptionPane.showMessageDialog(this, "No connection "+ e);
      }
      
  }
    void readData(){ //FOR READING DATA IN THE DATABASE
        
        try{
            
            String strCommand = "SELECT subject, title, item, type, id_question FROM questions";
            rs = stm.executeQuery(strCommand);
            
            int id_container[] = new int[100]; // DECLARE AND INITIALIZE ID_CONTAINER WITH VALUE OF 100
            int ids = 0; // DECLARE AND INITIALIZE IDS WITH VALUE 0
            
            while(rs.next()){
                
                String subject = rs.getString("subject"); // GETTING THE DATA AND ASSIGN IT TO VARIABLE
                String title = rs.getString("title");
                String item = rs.getString("item");
                String type = rs.getString("type");
                int id_question = rs.getInt("id_question");
            
                TableModel model = tblChoose.getModel(); // GET THE MODEL OF THE TABLE
                
               
              
                if(tblChoose.getRowCount() <= 0){ //FIRST DATA IN DATABASE EXECUTE THIS
                                
                    Object row[] = {subject, title, item, type, id_question};
                    DefaultTableModel dmodel = (DefaultTableModel) tblChoose.getModel();
                    dmodel.addRow(row); // ADDING THE THE FIRST DATA INTO THE TABLE
                                 
                     //THIS WILL BE THE FIRST ELEMENT OF THE ID_CONTAINER
                    id_container[ids] = id_question; // GET THE FIRST ID QUESTION AND ASSIGN IT TO THE ID_CONTAINER ARRAY INDEX IDS WHICH IS 0
                    ids++; // INCREMENT 
                                 
                 }else { // IF THE TABLE HAVE ALREADY FIRST DATA THIS WILL EXECUTE
                                  
                    int same = 0;
                    int i = 0;
                                       
                    for(i = 0; i < tblChoose.getRowCount(); i++){ // GET TABLE ROWCOUNT THIS WILL COMPATE THE ID_QUESTION IN ALL ID THAT ALREADY IN THE TABLE
                                          
                        if(id_question == id_container[i]){ // COMPARING ALL THE ELEMENTS IN ID_CONTAINER IN TO NEW ID USING LOOP
                            
                        same++; // IF ONE ID IS THE SAME IN THE NEW ID, THE SAME VARIABLE INCREMENT
                        
                        } // CONDITION
                    } // LOOP 
                                      
                    i = 0; // RESET TO 0 FOR THE NEXT LOOP
                                      
                    // AFTER THE LOOP THIS WILL BE THE NEXT EXECUTE
                    if(same == 0){ // IF THE ID_QUESTION HAVE SAME VALUE IN THE TABLE, THIS WILL EXECUTE.
                    // ADD ALL DATA IN TABLE
                        Object row[] = {subject, title, item, type, id_question};
                        DefaultTableModel dmodel = (DefaultTableModel) tblChoose.getModel();
                        dmodel.addRow(row); 
                        //IF THIS EXECUTE ITS MEANS THAT THIS IS UNIQUE OR THE FIRST ID
                        id_container[ids] = id_question; // SINCE THE ID_QUESTION IS UNIQUE ADD IT TO ID_CONTAINER WITH A INDEX IDS
                        ids++; // INCREMENT IDS FOR THE NEXT LOOP
                        same = 0; // RESET TO 0 FOR THE NEXT LOOP
                    } // CONDITION
                               
                } // ELSE
                   
            } // WHILE LOOP
    
        }catch(Exception e){
            
            JOptionPane.showMessageDialog(this, "Failed to read data from database\n" + e);
            
        }
    } // READDATA FUNCTION
    
    int getTotalReviewer(){ // GET THE TOTAL REVIEWER THAT ALREADY FILTER IN READDATA FUNCTION
        
        int rowCount = tblChoose.getRowCount(); // GET THE ROW COUNT IN TABLE
        return rowCount; // AND RETURN ITS VALUE, THIS WILL RETURN THE TOTAL ROW IN THE TABLE
        
    } // GETTOTALREVIEWER
    
    void deleteReviewer(){ // DELETE REVIEWER
        
        TableModel model = tblChoose.getModel(); // GET THE TABLE MODEL
        int selectedRow = tblChoose.getSelectedRow(); // GET SELECTED ROW
       
        
        if(selectedRow >= 0){ // CHECK IF THE USER SELECT A ROW IN THE TABLE, THE TABLE ROW START IN 0.
            // IF THE CONDITION IS TRUE EXECUTE THIS
        try{
            int id_question = Integer.parseInt(model.getValueAt(selectedRow, 4).toString()); // GET THE ID OF THE REVIEWER
            String strCommnad = "DELETE FROM questions WHERE id_question = " + id_question; // DELETE IN THE DATABASE USING THE ID THAT GET.
            stm.execute(strCommnad); // EXECUTE
            JOptionPane.showMessageDialog(this, "Success to Delete"); // POPUP IF SUCCESSFUL TO DELETE
            new start_review_form().setVisible(true); // REFRESH
            dispose();
            
        }catch(Exception e){
             // EXECUTE THIS IF THERE'S A PROBLEM IN DELETING DATA INTO THE DATABASE
             JOptionPane.showMessageDialog(this, "Failed to Delete " + e);
             
        }
        
        }else{ // EXECUTE THIS IF THE SELECTEDROW IS LESS THAN TO 0
             // THIS WILL EXECUTE IF THE USER DID NOT SELECT IN TABLE OR THE TABLE IS EMPTY
            JOptionPane.showMessageDialog(this, "Please select reviewer to delete");
            
        }
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelHeader = new javax.swing.JPanel();
        lblMinimize = new javax.swing.JLabel();
        lblClose = new javax.swing.JLabel();
        lblDrag = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblChoose = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblId = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblType = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lblSubject = new javax.swing.JLabel();
        lblTitle = new javax.swing.JLabel();
        lblItems = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

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

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Choose Reviewer");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 20, -1, -1));

        tblChoose.setBackground(new java.awt.Color(204, 204, 255));
        tblChoose.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tblChoose.setForeground(new java.awt.Color(51, 51, 51));
        tblChoose.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Subject", "Title", "Items", "Type", "Id"
            }
        ));
        tblChoose.setGridColor(new java.awt.Color(255, 0, 0));
        tblChoose.setRowHeight(30);
        tblChoose.setRowMargin(7);
        tblChoose.setSelectionBackground(new java.awt.Color(102, 153, 255));
        tblChoose.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblChoose.setShowGrid(false);
        tblChoose.getTableHeader().setReorderingAllowed(false);
        tblChoose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblChooseMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblChoose);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(286, 100, 780, 430));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Start Review");
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 255, 255)));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 195, 49));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Add Reviewer");
        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 255, 255)));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 195, 49));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Delete Reviewer");
        jLabel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 255, 255)));
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, 195, 49));

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
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(955, 10, 110, 49));

        lblId.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblId.setForeground(new java.awt.Color(255, 255, 255));
        lblId.setText("0");
        jPanel1.add(lblId, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, 170, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Type : ");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Id : ");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        lblType.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblType.setForeground(new java.awt.Color(255, 255, 255));
        lblType.setText("Ramble");
        jPanel1.add(lblType, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 220, 170, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Title : ");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Items: ");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, -1, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Subject : ");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        lblSubject.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblSubject.setForeground(new java.awt.Color(255, 255, 255));
        lblSubject.setText("No Subject");
        jPanel1.add(lblSubject, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, 170, -1));

        lblTitle.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle.setText("No Title");
        jPanel1.add(lblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 130, 170, -1));

        lblItems.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblItems.setForeground(new java.awt.Color(255, 255, 255));
        lblItems.setText("0 ");
        jPanel1.add(lblItems, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 170, 170, -1));

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Edit Reviewer");
        jLabel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 255, 255)));
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, 195, 49));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1080, 540));

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

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked


        int isValid = Integer.parseInt(lblId.getText()); // GET THE THE TEXT IN LBLID AND CONVERT IT TO INTEGER.
        
        if( isValid < 1){ // CHECK IF THE ID IS LESS THAN TO 1, IF TRUE IT MEANS THAT THE USER DID NOT SELECT ROW IN TABLE OR THE TABLE IS EMPTY.
            
            JOptionPane.showMessageDialog(this, "Please select reviewer!");
            
        }else{ // EXECUTE THIS IF THE USER SELECT IN TABLE ROW.
            
             int selectedReviewer = tblChoose.getSelectedRow(); // GET SELECTED ROW IN TABLE
             TableModel model = tblChoose.getModel(); // GET TABLE MODEL
             int reviewerId = Integer.parseInt(model.getValueAt(selectedReviewer, 4).toString()); // GET THE ID IN THE SELECTED ROW
             new reviewer_form().setVisible(true); // LOAD REVIEWER_FORM
             reviewer_form.lblReviewerId.setText(reviewerId + ""); // IN REVIEWER_FORM GET THE LBLREVIEWERID AND SET IT TEXT TO THE VALUE OF REVIEWERID
             dispose(); //EXIT FORM
            
        }// ELSE
  
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        new add_reviewer_form().setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        deleteReviewer();
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
         new dashboard_form().setVisible(true);
         dispose();
    }//GEN-LAST:event_jLabel5MouseClicked

    private void tblChooseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChooseMouseClicked
       
        // THIS CODE IS FOR AUTOMATIC SET A TEXT IN TEXTBOX THAT FROM IN TABLE ROW THAT SELECTED
        int selectedRow = tblChoose.getSelectedRow(); // GET SELECTED ROW IN TABLE
        TableModel model = tblChoose.getModel(); // GET TABLE MODEL
        lblId.setText(model.getValueAt(selectedRow, 4).toString()); // SET LBLID TEXT THAT FROM IN TABLE MODEL GET VALUE ROW AND COLUMN
        lblSubject.setText(model.getValueAt(selectedRow, 0).toString()); // SAME AS ABOVE CODE
        lblTitle.setText(model.getValueAt(selectedRow, 1).toString()); // SAME AS ABOVE CODE
        lblItems.setText(model.getValueAt(selectedRow, 2).toString()); // SAME AS ABOVE CODE
        lblType.setText(model.getValueAt(selectedRow, 3).toString()); // SAME AS ABOVE CODE
        
    }//GEN-LAST:event_tblChooseMouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
     
           // EDIT REVIEWER
        int selectedRow = tblChoose.getSelectedRow(); // GET SELECTED ROW IN TABLE
        
        if(selectedRow >= 0){ // CHECK THE SELECTEDROW IS VALID. TABLE ROW START COUNT IN 0 IT MEANS 0 IS VALID
            
            new edit_reviewer_form().setVisible(true); // OPEN EDIT_REVIEWER_FORM
            
            edit_reviewer_form.txtId.setText(lblId.getText()); // ACCESS EDIT_REVIEWER_FORM TXTID AND SET IT TEXT TO THE TEXT OF LBLID
            edit_reviewer_form.txtSubject.setText(lblSubject.getText()); // THIS IS LIKE TRANSFERING DATA
            edit_reviewer_form.txtTitle.setText(lblTitle.getText()); // FIRST IS YOU NEED TO OPEN THE FORM THAT YOU WANT TO ACCESS IT COMPONENTS
            edit_reviewer_form.txtItem.setText(lblItems.getText()); 
            edit_reviewer_form.txtType.setText(lblType.getText());
            dispose();
            
         }else{ // EXECUTE THIS IS THE USER DID NOT SELECT ANY ROW IN THE TABLE
            
             JOptionPane.showMessageDialog(this, "Please select Reviewer");
             
         }
    }//GEN-LAST:event_jLabel8MouseClicked

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
            java.util.logging.Logger.getLogger(start_review_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(start_review_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(start_review_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(start_review_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new start_review_form().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblClose;
    private javax.swing.JLabel lblDrag;
    public static javax.swing.JLabel lblId;
    public static javax.swing.JLabel lblItems;
    private javax.swing.JLabel lblMinimize;
    public static javax.swing.JLabel lblSubject;
    public static javax.swing.JLabel lblTitle;
    public static javax.swing.JLabel lblType;
    private javax.swing.JPanel panelHeader;
    public static javax.swing.JTable tblChoose;
    // End of variables declaration//GEN-END:variables
}
