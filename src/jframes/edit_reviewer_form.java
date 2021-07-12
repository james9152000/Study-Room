/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jframes;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;



/**
 *
 * @author James de guzman
 */
public class edit_reviewer_form extends javax.swing.JFrame {

    int xMouse;
    int yMouse;
    static Connection conn;
    static Statement stm;
    static ResultSet rs;
  // FOR AUTO ITEM COUNT
    int countItems = 0;
     
     
    public edit_reviewer_form() {
        initComponents();
        connectionDB();
       
        readData();
    }

    void connectionDB(){
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/reviewer_system", "root", "1234");
            stm = conn.createStatement();
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "No connection " + e);
        }
        
    }

    
    void readData(){
        
      String strItem = start_review_form.lblItems.getText();
      int items = Integer.parseInt(strItem);
      countItems = items; 
        
      String strId = start_review_form.lblId.getText();
      int id = Integer.parseInt(strId);
      
        try{
            
            String strCommand = "SELECT question, correct_ans, ans2, ans3, ans4 FROM questions WHERE id_question = " + id;
            rs = stm.executeQuery(strCommand);
            
            while(rs.next()){
                String question = rs.getString("question");
                String correct_answer = rs.getString("correct_ans");
                String ans2 = rs.getString("ans2");
                String ans3 = rs.getString("ans3");
                String ans4 = rs.getString("ans4");
                
                Object row[] = {question, correct_answer, ans2, ans3, ans4};
                DefaultTableModel dmodel = (DefaultTableModel) tblQuestions.getModel();
                dmodel.addRow(row);
               
             }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Can't read data from database " + e);
        }
        
    }
    
    void insertToTable(){
        countItems += 1;
        txtItem.setText(countItems + "");
        String question = txtQuestion.getText();
        String correct_answer = txtCorrectAns.getText();
        String answer2 = txtAns2.getText();
        String answer3 = txtAns3.getText();
        String answer4 = txtAns4.getText();
        
        Object row[] = {question, correct_answer, answer2, answer3, answer4};
        DefaultTableModel dmodel = (DefaultTableModel) tblQuestions.getModel();
        dmodel.addRow(row);
       
        
        txtQuestion.setText("");
        txtCorrectAns.setText("");
        txtAns2.setText("");
        txtAns3.setText("");
        txtAns4.setText("");
        
    }
    
        void removeToTable(){
        
        
        
        if(tblQuestions.getSelectedRow() >= 0){
        int selectedRow = tblQuestions.getSelectedRow();
        DefaultTableModel dmodel = (DefaultTableModel) tblQuestions.getModel();
        
        dmodel.removeRow(selectedRow);
        countItems -= 1;
        txtItem.setText(countItems + "");
        }else{
             JOptionPane.showMessageDialog(this, "No data!");
        }
        
    }
        
            void insertToDatabase(){
                
                
        TableModel model = tblQuestions.getModel();
       
       
        
       
        try{
            String strId = start_review_form.lblId.getText();
            int id = Integer.parseInt(strId);
            String strCommnad = "DELETE FROM questions WHERE id_question = " + id;
            stm.execute(strCommnad);
           
           
        }catch(Exception e){
             JOptionPane.showMessageDialog(this, "Failed to Delete " + e);
        }
      
       
        
        for(int i = 0; i < tblQuestions.getRowCount(); i++){
            
            String subject = txtSubject.getText();
            String title = txtTitle.getText();
            int item = Integer.parseInt(txtItem.getText());
            String type = txtType.getText();
            int id = Integer.parseInt(txtId.getText());
            
            String question = model.getValueAt(i, 0).toString();
            String correct_answer = model.getValueAt(i, 1).toString();
            String answer2 = model.getValueAt(i, 2).toString();
            String answer3 = model.getValueAt(i, 3).toString();
            String answer4 = model.getValueAt(i, 4).toString();
            
            
        try{
            connectionDB();
            String strCommand = "INSERT INTO questions(subject, title, item, type, id_question, correct_ans, ans2, ans3, ans4, question) VALUES('"+subject+"', '"+title+"', '"+item+"', '"+type+"', '"+id+"', '"+correct_answer+"', '"+answer2+"', '"+answer3+"', '"+answer4+"', '"+question+"')";
            stm.execute(strCommand);
            stm.close();
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Error in adding reviewer " + e);
        } // CATCH
        
        } // LOOP END
         JOptionPane.showMessageDialog(this, "Success to update reviewer");
         DefaultTableModel dmodel = (DefaultTableModel) tblQuestions.getModel();
         dmodel.setRowCount(0);
        new start_review_form().setVisible(true);
        dispose();
        
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelHeader = new javax.swing.JPanel();
        lblMinimize = new javax.swing.JLabel();
        lblClose = new javax.swing.JLabel();
        lblDrag = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtSubject = new javax.swing.JTextField();
        txtTitle = new javax.swing.JTextField();
        txtItem = new javax.swing.JTextField();
        txtType = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtQuestion = new javax.swing.JTextArea();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtAns4 = new javax.swing.JTextField();
        txtAns3 = new javax.swing.JTextField();
        txtAns2 = new javax.swing.JTextField();
        txtCorrectAns = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblQuestions = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

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

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Id : ");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Subject : ");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Title : ");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Items: ");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Type : ");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, -1));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Question : ");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, -1, -1));

        txtId.setEditable(false);
        txtId.setBackground(new java.awt.Color(51, 153, 255));
        txtId.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtId.setForeground(new java.awt.Color(255, 255, 255));
        txtId.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtId.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        txtId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtIdFocusGained(evt);
            }
        });
        jPanel1.add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 350, 30));

        txtSubject.setBackground(new java.awt.Color(51, 153, 255));
        txtSubject.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtSubject.setForeground(new java.awt.Color(255, 255, 255));
        txtSubject.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSubject.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        txtSubject.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSubjectFocusGained(evt);
            }
        });
        jPanel1.add(txtSubject, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 350, 30));

        txtTitle.setBackground(new java.awt.Color(51, 153, 255));
        txtTitle.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtTitle.setForeground(new java.awt.Color(255, 255, 255));
        txtTitle.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTitle.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        txtTitle.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTitleFocusGained(evt);
            }
        });
        jPanel1.add(txtTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, 350, 30));

        txtItem.setEditable(false);
        txtItem.setBackground(new java.awt.Color(51, 153, 255));
        txtItem.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtItem.setForeground(new java.awt.Color(255, 255, 255));
        txtItem.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtItem.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        txtItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtItemMouseClicked(evt);
            }
        });
        jPanel1.add(txtItem, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 140, 350, 30));

        txtType.setBackground(new java.awt.Color(51, 153, 255));
        txtType.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtType.setForeground(new java.awt.Color(255, 255, 255));
        txtType.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtType.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        txtType.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTypeFocusGained(evt);
            }
        });
        jPanel1.add(txtType, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 180, 350, 30));

        txtQuestion.setColumns(20);
        txtQuestion.setLineWrap(true);
        txtQuestion.setRows(5);
        jScrollPane2.setViewportView(txtQuestion);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 220, 500, 90));

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Correct Answer : ");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, -1, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Answer 2 : ");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, -1, -1));

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Answer 3 : ");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Answer 4 : ");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 450, -1, -1));

        txtAns4.setBackground(new java.awt.Color(51, 153, 255));
        txtAns4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtAns4.setForeground(new java.awt.Color(255, 255, 255));
        txtAns4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAns4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        txtAns4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtAns4FocusGained(evt);
            }
        });
        jPanel1.add(txtAns4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 440, 500, 30));

        txtAns3.setBackground(new java.awt.Color(51, 153, 255));
        txtAns3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtAns3.setForeground(new java.awt.Color(255, 255, 255));
        txtAns3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAns3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        txtAns3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtAns3FocusGained(evt);
            }
        });
        jPanel1.add(txtAns3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 400, 500, 30));

        txtAns2.setBackground(new java.awt.Color(51, 153, 255));
        txtAns2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtAns2.setForeground(new java.awt.Color(255, 255, 255));
        txtAns2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAns2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        txtAns2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtAns2FocusGained(evt);
            }
        });
        jPanel1.add(txtAns2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 360, 500, 30));

        txtCorrectAns.setBackground(new java.awt.Color(51, 153, 255));
        txtCorrectAns.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtCorrectAns.setForeground(new java.awt.Color(255, 255, 255));
        txtCorrectAns.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCorrectAns.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        txtCorrectAns.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCorrectAnsFocusGained(evt);
            }
        });
        jPanel1.add(txtCorrectAns, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 320, 500, 30));

        tblQuestions.setBackground(new java.awt.Color(204, 204, 255));
        tblQuestions.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tblQuestions.setForeground(new java.awt.Color(51, 51, 51));
        tblQuestions.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Question", "Correct Answer", "Answer 2", "Answer 3", "Answer 4"
            }
        ));
        tblQuestions.setRowHeight(30);
        tblQuestions.setRowMargin(7);
        tblQuestions.setSelectionBackground(new java.awt.Color(102, 153, 255));
        tblQuestions.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblQuestions.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblQuestionsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblQuestions);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 70, 440, 470));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Update");
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 255, 255)));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 490, 110, 40));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Add");
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 255, 255)));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 490, 110, 40));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Remove");
        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 255, 255)));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 490, 110, 40));

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
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 10, 110, 49));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1080, 550));

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

    private void txtIdFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtIdFocusGained

    }//GEN-LAST:event_txtIdFocusGained

    private void txtSubjectFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSubjectFocusGained

    }//GEN-LAST:event_txtSubjectFocusGained

    private void txtTitleFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTitleFocusGained

    }//GEN-LAST:event_txtTitleFocusGained

    private void txtItemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtItemMouseClicked
        JOptionPane.showMessageDialog(this, "This will auto detect");
    }//GEN-LAST:event_txtItemMouseClicked

    private void txtTypeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTypeFocusGained

    }//GEN-LAST:event_txtTypeFocusGained

    private void txtAns4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAns4FocusGained
  

    }//GEN-LAST:event_txtAns4FocusGained

    private void txtAns3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAns3FocusGained

      
    }//GEN-LAST:event_txtAns3FocusGained

    private void txtAns2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAns2FocusGained

   

    }//GEN-LAST:event_txtAns2FocusGained

    private void txtCorrectAnsFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCorrectAnsFocusGained



    }//GEN-LAST:event_txtCorrectAnsFocusGained

    private void tblQuestionsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblQuestionsMouseClicked
        int selectedRow = tblQuestions.getSelectedRow();
        TableModel model = tblQuestions.getModel();
        
        txtQuestion.setText(model.getValueAt(selectedRow, 0).toString());
        txtCorrectAns.setText(model.getValueAt(selectedRow, 1).toString());
        txtAns2.setText(model.getValueAt(selectedRow, 2).toString());
        txtAns3.setText(model.getValueAt(selectedRow, 3).toString());
        txtAns4.setText(model.getValueAt(selectedRow, 4).toString());
    }//GEN-LAST:event_tblQuestionsMouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
       
        
       // if(insertTrue){
        if(txtQuestion.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Please put the Question");
        }else{
            if(txtCorrectAns.getText().equals("") || txtCorrectAns.getText().equals("Correct Answer Here")){
                JOptionPane.showMessageDialog(this, "Please put the Correct Answer");
            }else{
                if(txtAns2.getText().equals("") || txtAns2.getText().equals("Answer 2 Here")){
                    JOptionPane.showMessageDialog(this, "Please put the Answer 2");
            }else{
                  if(txtAns3.getText().equals("") || txtAns3.getText().equals("Answer 3 Here")) {
                      JOptionPane.showMessageDialog(this, "Please put the Answer 3");
                  }else{
                      if(txtAns4.getText().equals("") || txtAns4.getText().equals("Answer 4 Here")){
                          JOptionPane.showMessageDialog(this, "Please put the Answer 4");
                      }else{
                           insertToTable();
                      }//ELSE
                  }// ELSE
                } //ELSE
              } // ELSE
        }// ELSE
//}// IF
        
        
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
         removeToTable();
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
             
           
        if(tblQuestions.getRowCount() <= 1){
            JOptionPane.showMessageDialog(this, "Make question atleast 2");
        }else{
             insertToDatabase();
        }
      
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        new start_review_form().setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel5MouseClicked

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
            java.util.logging.Logger.getLogger(edit_reviewer_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(edit_reviewer_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(edit_reviewer_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(edit_reviewer_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new edit_reviewer_form().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblClose;
    private javax.swing.JLabel lblDrag;
    private javax.swing.JLabel lblMinimize;
    private javax.swing.JPanel panelHeader;
    private javax.swing.JTable tblQuestions;
    private javax.swing.JTextField txtAns2;
    private javax.swing.JTextField txtAns3;
    private javax.swing.JTextField txtAns4;
    private javax.swing.JTextField txtCorrectAns;
    public static javax.swing.JTextField txtId;
    public static javax.swing.JTextField txtItem;
    private javax.swing.JTextArea txtQuestion;
    public static javax.swing.JTextField txtSubject;
    public static javax.swing.JTextField txtTitle;
    public static javax.swing.JTextField txtType;
    // End of variables declaration//GEN-END:variables
}
