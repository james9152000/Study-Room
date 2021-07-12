
package jframes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 *
 * @author james de guzman
 */
public class reviewer_form extends javax.swing.JFrame {

    int xMouse;
    int yMouse;
    static Connection conn;
    static Statement stm;
    static ResultSet rs;
    
    Random rand = new Random(); // CREATE OBJECT RAND FOR RANDOM
    
    int question = 0; //FOR QUESTION NEXT, INCREMENT TO EXECUTE NEXT QUESTION
    int counter = 1; // FOR QUESTION COUNT AND TO TERMINATE IF THERE NO QUESTION
    int QuestionCount;  // ASSIGN TO QUESTIONCOUNT THE TOTAL ROW IN TABLE, THIS IS USING QuestionCount();
    String userAnswerContainer[]; // THIS WILL BE THE CONTAINER OF THE ANSWER OF THE USER // STRING ARRAY
    
    
    public reviewer_form() {
        
        initComponents();
        connectionDB();
        lblReviewerId.setText(start_review_form.lblId.getText()); // GETTING THE ID_QUESTION IN START_REVIEW_FORM AND SET IT TO LBLREVIEWERID
        readData(); // ADD ALL QUESTION AND ANSWER TO TABLE // ANSWER IS ALREADY SHUFFLE // TABLE INVISIBLE
        QuestionCount(); // GETTING ALL QUESTION USING COUNT THE TOTAL ROW IN TABLE WHICH IS INVISIBLE TO THE USER
        lblItem.setText(QuestionCount + ""); //lblItem SET TEXT USING THE QuestionCount
        this.userAnswerContainer = new String[Integer.parseInt(lblItem.getText())]; // userAnswerContainer SET LENGTH USING THE lblItem TEXT
         startingQuestion();  // THE STARTING QUESTION SET. THIS IS RUN ONCE
         textpane(); // STYLE FOR QUESTION // CENTERING TEXT
         tblHide.setVisible(false);
    }
    
    void textpane(){ // STYLE FOR QUESTION // CENTERING TEXT
        
        //FOR CENTERING HORIZONTAL ALIGNMENT
        StyledDocument doc = txtQuestion.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
      
    }
                                                                                                        // UNUSED FUNCTION
    public String[] getAllUserAnswer(){ // THIS WILL RETURN userAnswerContainer String Array
        
        return userAnswerContainer;
        
    }
    
    public int QuestionCount(){ // GETTING ALL QUESTION USING COUNT THE TOTAL ROW IN TABLE WHICH IS INVISIBLE TO THE USER
        
        return QuestionCount = tblVisible.getRowCount(); // ASSIGN TO QUESTIONCOUNT THE TOTAL ROW IN TABLE
        
    }
    
    public void questRemain(){ // SET QUESTION NUMBER =>this will run if the nextQuestion() execute 
        
        lblQuestion.setText(counter + 1 +""); // USING THE COUNTER SET IT TEXT TO lblQuestion 
      
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
  
    void readData(){
        
       int id = Integer.parseInt(lblReviewerId.getText()); // GET LBLREVIEWERID TEXT AND ASSIGN IT TO ID VARIABLE
       
       try{
           
           String strCommand = "SELECT question, correct_ans, ans2, ans3, ans4 FROM questions WHERE id_question = " + id; // SELECT ALL THIS WHERE ID IS.
           rs = stm.executeQuery(strCommand);
          
           while(rs.next()){ //USING LOOP TO GET ALL DATA INTO THE DATABASE
               String question = rs.getString("question"); // GETTING AND ASSIGNING INTO NEW VARIABLE
               String correct = rs.getString("correct_ans"); 
               String ans2 = rs.getString("ans2");
               String ans3 = rs.getString("ans3");
               String ans4 = rs.getString("ans4");
              
               String answer[] = {correct, ans2, ans3, ans4}; // GET ALL ANSWER STORE IT TO ARRAY
               
               
               //RANDOMING ANSWER
               List<String> randomAns = new ArrayList<>(); // CREATE ARRAY LIST
               
               //USING LOOP GETTING ALL THE ANSWER AND ADD IT TO ARRAYLIST
              for(int i = 0; i < answer.length; i++){ // USING ANSWER LENGTH
                  
                  randomAns.add(answer[i]); // ADD IT TO RANDOMANS TO RANDOM ALL THE QUESTION
                  
              }
              
              //AND AFTER GETTING ALL THE ANSWER SHUFFLE IT
               Collections.shuffle(randomAns);
               
               //AFTER SHUFFLE THE QUESTION ADD IT TO ARRAY WITH THE QUESTION
               Object row[] = {question, randomAns.get(0), randomAns.get(1), randomAns.get(2), randomAns.get(3)};
               
               // GET TABLE MODEL
               DefaultTableModel dmodel = (DefaultTableModel) tblVisible.getModel();
               
               //ADD THE ROW WHICH IS ALREADY SHUFFLE THE ANSWER
               dmodel.addRow(row);
               
               //THIS TABLE WILL BE INVISIBLE TO USER
        
             
           }
           
       }catch(Exception e){
           
            JOptionPane.showMessageDialog(this, "Have problem please check\n" + e);
            
       }
       
   }
    
    void nextQuestion(){ // FOR NEXT QUESTION OR ROW
        
      try{  
          
        rbAns1.setSelected(true); // RESET THE SELECTED ANSWER BACK IT TO RADIO 1
        question += 1; // INCREMENT question
      
        TableModel model = tblVisible.getModel(); // GET TABLE MODEL
        
        //SET NEW TEXT TO THE QUESTION AND ANSWER
        txtQuestion.setText(model.getValueAt(question, 0).toString()); //GET QUESTION INTO THE INVISIBLE TABLE WHERE THE ROW is in the value of question
        rbAns1.setText(model.getValueAt(question, 1).toString()); // THE question IS ALWAYS INCREMENT THAT WILL NOT HAVE PROBLEM
        rbAns2.setText(model.getValueAt(question, 2).toString());
        rbAns3.setText(model.getValueAt(question, 3).toString());
        rbAns4.setText(model.getValueAt(question, 4).toString());

       questRemain();
        
      }catch(Exception e){
          //JOptionPane.showMessageDialog(this, "Problem in nextQuestion\n" + e);
      }
        
    }
    

    void userAnswer(){ // CHECK WHAT RADIO BUTTON SELECTED AND ASSIGN IT TO userAnswerContainer[question] // question INCREMENT IN nextQuestion()
        
        if(rbAns1.isSelected()){
            userAnswerContainer[question] = rbAns1.getText();
           
        }else if(rbAns2.isSelected()){
            userAnswerContainer[question] = rbAns2.getText();
            
        }else if(rbAns3.isSelected()){
             userAnswerContainer[question] = rbAns3.getText();
            
        }else {
            userAnswerContainer[question] = rbAns4.getText();
            
        }
        
    }
    
    void startingQuestion(){ // THE STARTING QUESTION SET. THIS IS RUN ONCE
        
        TableModel model = tblVisible.getModel();     //GET TABLE MODEL
        
        txtQuestion.setText(model.getValueAt(0, 0).toString()); // SET QUESTION THAT FROM IN MODEL TABLE COLUMN 0, ROW 1
        rbAns1.setText(model.getValueAt(0, 1).toString()); // SAME CODE ABOVE
        rbAns2.setText(model.getValueAt(0, 2).toString()); // SAME CODE ABOVE
        rbAns3.setText(model.getValueAt(0, 3).toString()); // SAME CODE ABOVE
        rbAns4.setText(model.getValueAt(0, 4).toString()); // SAME CODE ABOVE
        
    }
    
    public int getReviewerId(){
        int id = Integer.parseInt(lblReviewerId.getText());
        return id;
    }
   
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        panelHeader = new javax.swing.JPanel();
        lblMinimize = new javax.swing.JLabel();
        lblClose = new javax.swing.JLabel();
        lblDrag = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblReviewerId = new javax.swing.JLabel();
        rbAns1 = new javax.swing.JRadioButton();
        rbAns2 = new javax.swing.JRadioButton();
        rbAns3 = new javax.swing.JRadioButton();
        rbAns4 = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tblHide = new javax.swing.JScrollPane();
        tblVisible = new javax.swing.JTable();
        lblQuestion = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblItem = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtQuestion = new javax.swing.JTextPane();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

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

        lblReviewerId.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblReviewerId.setForeground(new java.awt.Color(255, 255, 255));
        lblReviewerId.setText("1");
        jPanel1.add(lblReviewerId, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, -1, -1));

        rbAns1.setBackground(new java.awt.Color(51, 153, 255));
        buttonGroup1.add(rbAns1);
        rbAns1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        rbAns1.setForeground(new java.awt.Color(255, 255, 255));
        rbAns1.setSelected(true);
        rbAns1.setText("Answer 1");
        jPanel1.add(rbAns1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 350, -1, -1));

        rbAns2.setBackground(new java.awt.Color(51, 153, 255));
        buttonGroup1.add(rbAns2);
        rbAns2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        rbAns2.setForeground(new java.awt.Color(255, 255, 255));
        rbAns2.setText("Answer 2");
        jPanel1.add(rbAns2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 410, -1, -1));

        rbAns3.setBackground(new java.awt.Color(51, 153, 255));
        buttonGroup1.add(rbAns3);
        rbAns3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        rbAns3.setForeground(new java.awt.Color(255, 255, 255));
        rbAns3.setText("Answer 3");
        jPanel1.add(rbAns3, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 350, -1, -1));

        rbAns4.setBackground(new java.awt.Color(51, 153, 255));
        buttonGroup1.add(rbAns4);
        rbAns4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        rbAns4.setForeground(new java.awt.Color(255, 255, 255));
        rbAns4.setText("Answer 4");
        jPanel1.add(rbAns4, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 410, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Next");
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 255, 255)));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 470, 280, 38));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Exit");
        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 255, 255)));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 10, 100, 38));

        tblVisible.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Question", "ans1", "ans2", "ans3", "Title 5"
            }
        ));
        tblHide.setViewportView(tblVisible);

        jPanel1.add(tblHide, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 460, 70, 60));

        lblQuestion.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblQuestion.setForeground(new java.awt.Color(255, 255, 255));
        lblQuestion.setText("1");
        jPanel1.add(lblQuestion, new org.netbeans.lib.awtextra.AbsoluteConstraints(104, 11, 25, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Items");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(528, 11, -1, -1));

        lblItem.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblItem.setForeground(new java.awt.Color(255, 255, 255));
        lblItem.setText("1");
        jPanel1.add(lblItem, new org.netbeans.lib.awtextra.AbsoluteConstraints(492, 11, 30, -1));

        txtQuestion.setEditable(false);
        txtQuestion.setBorder(null);
        txtQuestion.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtQuestion.setText("asdfjlkas a;lskjfd a lsdjfj ajdfa fasdfwf");
        txtQuestion.setAutoscrolls(false);
        txtQuestion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jScrollPane3.setViewportView(txtQuestion);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 89, 700, 230));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Question:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Reviewer Id:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1080, 530));

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
       
           
        if(counter <= tblVisible.getRowCount()){ // IF THE COUNTER IS STILL LESS THAN OR EQUAL TO tblVisible TOTAL ROW THIS WILL EXECUTE
            
           userAnswer();  // FIRST GET USER ANSWER
           nextQuestion(); // GO TO NEXT QUESTION
           
            
            if(question == tblVisible.getRowCount()){ // IF question EQUAL TO tblVisible TOTAL ROW EXECUTE THIS
          
             new review_result_form().setVisible(true); // OPEN FORM
         
           for(int i = 0; i < Integer.parseInt(lblItem.getText()); i++){ //USE THE lblItem FOR CONDITION LENGTH
               
               review_result_form.userAnswer[i] = userAnswerContainer[i]; //TRANSFERING DATA FROM THIS FORM TO OTHER
               
               dispose();
           }
            
        } // CONDTION
           
           questRemain();
           counter++;
           
        }else{
            JOptionPane.showMessageDialog(this, "End of Review");
            new review_result_form().setVisible(true);
           dispose();
           
            
          
        }
                
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
         new start_review_form().setVisible(true);
         dispose();
    }//GEN-LAST:event_jLabel3MouseClicked

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
            java.util.logging.Logger.getLogger(reviewer_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(reviewer_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(reviewer_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(reviewer_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new reviewer_form().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblClose;
    private javax.swing.JLabel lblDrag;
    public static javax.swing.JLabel lblItem;
    private javax.swing.JLabel lblMinimize;
    private javax.swing.JLabel lblQuestion;
    public static javax.swing.JLabel lblReviewerId;
    private javax.swing.JPanel panelHeader;
    private javax.swing.JRadioButton rbAns1;
    private javax.swing.JRadioButton rbAns2;
    private javax.swing.JRadioButton rbAns3;
    private javax.swing.JRadioButton rbAns4;
    private javax.swing.JScrollPane tblHide;
    private javax.swing.JTable tblVisible;
    private javax.swing.JTextPane txtQuestion;
    // End of variables declaration//GEN-END:variables
}
