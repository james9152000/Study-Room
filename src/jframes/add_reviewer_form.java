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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author pc
 */
public class add_reviewer_form extends javax.swing.JFrame {

    int xMouse;
    int yMouse;
    static Connection conn;
    static Statement stm;
    static ResultSet rs;
    
    // FOR AUTO ITEM COUNT
    int countItems; // COUNITEMS WILL INCREMENT OR DECREMENT BASE ON THE USER. // CONNECT COMPONENT INSERT +1 AND REMOVE -1
    
    // IF THE CURRENT ID_QUESTION HAVE SAME ID_QUESTION INTO THE DATABSE THIS WILL INCREMENT 
    int sameId = 0; // IF THIS INCREMENT TO 1 AND UP THIS WILL BE INVALID
     
    
    
    boolean focusGainEnable = false; // THIS WILL BE TRUE IF THE USER COMPLETE THE THE DETAILS ABOVE BEFORE THE QUESTION AND ANSWER
    // ONCE THE FOCUSGAINENABLE THE PLACEHOLDER WILL ENABLE 
    int placeholder1 = 0; // TXTCORRECTANS  => THE PLACEHOLDER IS ONLY ONCE TO PERFORM => IF THE USER SELECT THE PLACEHOLDER IT WILL AUTOMATIC INCREMENT TO 1;
    int placeholder2 = 0; //TXTANS2 => SAME CODE ABOVE
    int placeholder3 = 0;//TXTANS3 => SAME CODE ABOVE
    int placeholder4 = 0;//TXTANS4 => SAME CODE ABOVE
        
    //FOR INSERT BUTTON => CANT ACCESS IF THIS IS FALSE
    boolean insertTrue = false; // THIS WILL BE TRUE IF THE USER COMPLETE THE THE DETAILS ABOVE BEFORE THE QUESTION AND ANSWER
    
    //FOR REMOVE BUTTON
    boolean removeTrue = false; // THIS WILL BE TRUE IF THE USER COMPLETE THE THE DETAILS ABOVE BEFORE THE QUESTION AND ANSWER
    
    //FOR INSERTTODATABSE BUTTON
    boolean insertToDatabaseTrue = false;  // THIS WILL BE TRUE IF THE USER COMPLETE THE THE DETAILS ABOVE BEFORE THE QUESTION AND ANSWER
    
    public add_reviewer_form() {
        initComponents();
        connectionDB();
        questionSetDisable(); // SET QUESTION, ANSWER AND BUTTON DISABLE
        
    }
    
    void connectionDB(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/reviewer_system", "root", "1234");
            stm = conn.createStatement();
           
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "No Connection " + e);
        }
        
    }
    
    void questionSetDisable(){  // SET QUESTION, ANSWER AND BUTTON DISABLE
        
        txtQuestion.setEditable(false);
        txtCorrectAns.setEditable(false);
        txtAns2.setEditable(false);
        txtAns3.setEditable(false);
        txtAns4.setEditable(false);
        lblRemove.enableInputMethods(false);
        
    }
    
        void questionSetEnable(){  // SET QUESTION, ANSWER AND BUTTON ENABLE
            
        txtQuestion.setEditable(true);
        txtCorrectAns.setEditable(true);
        txtAns2.setEditable(true);
        txtAns3.setEditable(true);
        txtAns4.setEditable(true);
        lblRemove.enableInputMethods(true);
        
    }
        
    void insertToTable(){ // INSERT DATA TO TABLE
        
        String question = txtQuestion.getText(); // GETTING THE QUESTION AND ANSWER AND ASSIGN INTO VARIABLE
        String correct_answer = txtCorrectAns.getText();
        String answer2 = txtAns2.getText();
        String answer3 = txtAns3.getText();
        String answer4 = txtAns4.getText();
        
        Object row[] = {question, correct_answer, answer2, answer3, answer4};  // NOW THE VARIABLE THAT HOLD THE DATA ADD TO ROW COLUMN
        DefaultTableModel dmodel = (DefaultTableModel) tblQuestions.getModel(); // GET TABLE MODEL
        dmodel.addRow(row); // ADD THE DATA INTO TABLE
        
        countItems += 1; // COUNTITEMS WILL INCREMENT => FOR AUTOMATIC SET TEXT INTO TXTITEMS
        txtItem.setText(countItems + ""); // AND GET THE VALUE OF COUNTITEMS AND SET IT INTO TXTITEMS
        
        txtQuestion.setText("");// RESET TEXTBOX INTO "" FOR THE NEXT PROCESS
        txtCorrectAns.setText("");
        txtAns2.setText("");
        txtAns3.setText("");
        txtAns4.setText("");
        
    }// INSERTTOTABLE
    
    void removeToTable(){ // REMOVE DATA INTO TABLE
       
        if(tblQuestions.getSelectedRow() >= 0){ // GET SELECTED ROW, THE TABLE ROW START IN 0 IT MEANS 0 IS VALID
            
        int selectedRow = tblQuestions.getSelectedRow(); //GET SELECTED ROW
        DefaultTableModel dmodel = (DefaultTableModel) tblQuestions.getModel(); // GET TABLE MODEL
        
        dmodel.removeRow(selectedRow);// REMOVE ROW WHICH IS THE ROW BASE OF THE SELECTED_ROW
        
        countItems -= 1; // COUNTITEMS DECREMENT
        txtItem.setText(countItems + ""); // SET THE TEXT OF TXTITEM USING THE COUNTITEMS
        
        }else{// THIS WILL EXECUTE IF THE USER DID NOT SELECT ANY ROW OR THE TABLE HAVE NO DATA.
            
             JOptionPane.showMessageDialog(this, "No data!");
             
        }//ELSE
        
    }// REMOVETOTABLE
    
    void insertToDatabase(){ // INSERT THE TABLE DATA INTO THE DATABSE
        
         TableModel model = tblQuestions.getModel(); // GET TABLE MODEL
        
        for(int i = 0; i < tblQuestions.getRowCount(); i++){ // USING LOOP ACCESS ALL ROW IN TABLE
            
            //IMPORTANT DETAILS
            String subject = txtSubject.getText(); // GETTING THE TEXT AND ASSIGN IN VARIABLE
            String title = txtTitle.getText();
            int item = Integer.parseInt(txtItem.getText());
            String type = txtType.getText();
            int id = Integer.parseInt(txtId.getText()); // GETTING THE TEXT AND ASSIGN IN VARIABLE
            
            //QUESTION AND ANSWER
            String question = model.getValueAt(i, 0).toString(); // GETTING THE TEXT AND ASSIGN IN VARIABLE
            String correct_answer = model.getValueAt(i, 1).toString();
            String answer2 = model.getValueAt(i, 2).toString();
            String answer3 = model.getValueAt(i, 3).toString();
            String answer4 = model.getValueAt(i, 4).toString(); // GETTING THE TEXT AND ASSIGN IN VARIABLE
            
        try{ //INSIDE THE FOR LOOP TRY TO INSERT THE DATA GET BASE ON THE INDEXT OR TABLE ROW
            
            connectionDB();// OPENING CONNECTION//                                                                                               ALL ROW WILL INSERT USING FOR LOOP
            String strCommand = "INSERT INTO questions(subject, title, item, type, id_question, correct_ans, ans2, ans3, ans4, question) VALUES('"+subject+"', '"+title+"', '"+item+"', '"+type+"', '"+id+"', '"+correct_answer+"', '"+answer2+"', '"+answer3+"', '"+answer4+"', '"+question+"')";
            stm.execute(strCommand); // EXECUTE
            stm.close(); // CLOSE CONNECTION
            
        }catch(Exception e){ // CHEKC IF THERE IS ERROR 
            
            JOptionPane.showMessageDialog(this, "Error in adding reviewer " + e);
            
        } // CATCH
        
        } // LOOP END
        
         // THIS CODE WILL EXECUTE AFTER THE FOR LOOP FINISH TO EXECUTE
        JOptionPane.showMessageDialog(this, "Success to add reviewer");
        DefaultTableModel dmodel = (DefaultTableModel) tblQuestions.getModel(); //GETTING TABLE MODEL
        dmodel.setRowCount(0); //SET ROW INTO 0 0R RESSETING IT
        new add_reviewer_form().setVisible(true); // REFRESH FORM BY OPENING SAME FORM AND DISPOSING OLD ONE.
        dispose(); // DISPOSING
        
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
        lblSet = new javax.swing.JLabel();
        txtSubject = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblQuestions = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        lblInsert = new javax.swing.JLabel();
        txtAns4 = new javax.swing.JTextField();
        txtId = new javax.swing.JTextField();
        txtTitle = new javax.swing.JTextField();
        txtItem = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtQuestion = new javax.swing.JTextArea();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtType = new javax.swing.JTextField();
        txtCorrectAns = new javax.swing.JTextField();
        txtAns2 = new javax.swing.JTextField();
        txtAns3 = new javax.swing.JTextField();
        lblInsert1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblRemove = new javax.swing.JLabel();

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
        panelHeader.add(lblMinimize, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 10, 30, 20));

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
        panelHeader.add(lblClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(1250, 10, 30, 20));

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
        panelHeader.add(lblDrag, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1210, 40));

        getContentPane().add(panelHeader, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1290, 40));

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

        lblSet.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblSet.setForeground(new java.awt.Color(255, 204, 204));
        lblSet.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSet.setText("Unset");
        lblSet.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 255, 255)));
        lblSet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSetMouseClicked(evt);
            }
        });
        jPanel1.add(lblSet, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 20, 120, 190));

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
        jScrollPane1.setViewportView(tblQuestions);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 70, 660, 460));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Answer 4 : ");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 450, -1, -1));

        lblInsert.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblInsert.setForeground(new java.awt.Color(255, 255, 255));
        lblInsert.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblInsert.setText("Insert to Database");
        lblInsert.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 255, 255)));
        lblInsert.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblInsertMouseClicked(evt);
            }
        });
        jPanel1.add(lblInsert, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 480, 160, 40));

        txtAns4.setBackground(new java.awt.Color(51, 153, 255));
        txtAns4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtAns4.setForeground(new java.awt.Color(255, 255, 255));
        txtAns4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAns4.setText("Answer 4 Here");
        txtAns4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        txtAns4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtAns4FocusGained(evt);
            }
        });
        jPanel1.add(txtAns4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 440, 500, 30));

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

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Type : ");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, -1));

        txtQuestion.setColumns(20);
        txtQuestion.setLineWrap(true);
        txtQuestion.setRows(5);
        jScrollPane2.setViewportView(txtQuestion);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 220, 500, 90));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Question : ");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, -1, -1));

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

        txtCorrectAns.setBackground(new java.awt.Color(51, 153, 255));
        txtCorrectAns.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtCorrectAns.setForeground(new java.awt.Color(255, 255, 255));
        txtCorrectAns.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCorrectAns.setText("Correct Answer Here");
        txtCorrectAns.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        txtCorrectAns.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCorrectAnsFocusGained(evt);
            }
        });
        jPanel1.add(txtCorrectAns, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 320, 500, 30));

        txtAns2.setBackground(new java.awt.Color(51, 153, 255));
        txtAns2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtAns2.setForeground(new java.awt.Color(255, 255, 255));
        txtAns2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAns2.setText("Answer 2 Here");
        txtAns2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        txtAns2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtAns2FocusGained(evt);
            }
        });
        jPanel1.add(txtAns2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 360, 500, 30));

        txtAns3.setBackground(new java.awt.Color(51, 153, 255));
        txtAns3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtAns3.setForeground(new java.awt.Color(255, 255, 255));
        txtAns3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAns3.setText("Answer 3 Here");
        txtAns3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        txtAns3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtAns3FocusGained(evt);
            }
        });
        jPanel1.add(txtAns3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 400, 500, 30));

        lblInsert1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblInsert1.setForeground(new java.awt.Color(255, 255, 255));
        lblInsert1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblInsert1.setText("Insert");
        lblInsert1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 255, 255)));
        lblInsert1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblInsert1MouseClicked(evt);
            }
        });
        jPanel1.add(lblInsert1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 480, 100, 40));

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
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 10, 120, 50));

        lblRemove.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblRemove.setForeground(new java.awt.Color(255, 255, 255));
        lblRemove.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRemove.setText("Remove");
        lblRemove.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 255, 255)));
        lblRemove.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblRemoveMouseClicked(evt);
            }
        });
        jPanel1.add(lblRemove, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 480, 100, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1290, 540));

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

    private void lblInsertMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblInsertMouseClicked

       if(insertToDatabaseTrue){
           
        if(tblQuestions.getRowCount() <= 1){
            JOptionPane.showMessageDialog(this, "Make question atleast 2");
        }else{
             insertToDatabase();
        }
       }
         
         
    }//GEN-LAST:event_lblInsertMouseClicked

    private void lblInsert1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblInsert1MouseClicked

        if(insertTrue){ //IF INSERTRUE = TRUE => EXECUTE THIS
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
        }// IF
      
    }//GEN-LAST:event_lblInsert1MouseClicked

    private void txtCorrectAnsFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCorrectAnsFocusGained
       
        if(focusGainEnable){ // IF FOCUSGAINENABLE IS TRUE EXECUTE THIS
            if(placeholder1 == 0){ // PLACEHOLDER == 0 EXECUTE THIS
                txtCorrectAns.setText("");
                placeholder1++;
            }
        }
       
    }//GEN-LAST:event_txtCorrectAnsFocusGained

    private void txtAns2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAns2FocusGained
       
        if(focusGainEnable){
            if(placeholder2 == 0){
                txtAns2.setText("");
                placeholder2++;
            }
        }
        
    }//GEN-LAST:event_txtAns2FocusGained

    private void txtAns3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAns3FocusGained
        
        if(focusGainEnable){
            if(placeholder3 == 0){
                txtAns3.setText("");
                placeholder3++;
            }
        }
    }//GEN-LAST:event_txtAns3FocusGained

    private void txtAns4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAns4FocusGained
        if(focusGainEnable){
            if(placeholder4 == 0){
                txtAns4.setText("");
                placeholder4++;
            }
        }
       
    }//GEN-LAST:event_txtAns4FocusGained

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        new start_review_form().setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel5MouseClicked

    private void lblRemoveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRemoveMouseClicked
       
        if(removeTrue){
            removeToTable();
        }
        
    }//GEN-LAST:event_lblRemoveMouseClicked

    private void lblSetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSetMouseClicked
        
        sameId = 0; // IF THIS VARIABLE INCREMENT TO 1 UP THE CURRENT ID WILL BE INVALID
        
           try{
            String strCommand = "SELECT id_question FROM questions"; // GET ALL SAVE ID_QUESTION IN TABLE QUESTION
            rs = stm.executeQuery(strCommand);
            
           while(rs.next()){ // USING WHILE LOOP GET ALL THE ID_QUESTION
               
                int id = rs.getInt("id_question"); // EVERY ID_QUESTION ITERATE WILL STORE IN ID VARIABLE
                int currentId = Integer.parseInt(txtId.getText());  // GET THE CURRENT ID => USER INPUT 
                if(currentId == id){ //CHECK IF THE CURRENT_ID IS EQUAL TO ID WHICH IS IN THE DATABASE SAVE.
                   sameId += 1; // IF THE CONDITION TRUE SAMEID WILL INCREMENT
               }
               
                // THE CURRENT_ID WILL COMPARE TO ALL ID_QUESTION INTO THE DATABASE.
           }
            
        }catch(Exception e){
            
        }
    
         //CHECK IF ALL THE TEXTBOX IS NOT EMPTY
       if(lblSet.getText().equals("Unset")){ // CHECK LBLSET TEXT
        if(txtId.getText().equals("") || txtId.getText().length() <= 0){
            JOptionPane.showMessageDialog(this, "Please input reviewer id");    // CHECK ID
        }else{ // ELSE 1
            if(txtSubject.getText().length() < 1){
                JOptionPane.showMessageDialog(this, "Please input subject");    // CHECK SUBJECT
            }else{ // ELSE 2
                if(txtTitle.getText().length() < 1){
                    JOptionPane.showMessageDialog(this, "Please input title");  // CHECK TITLE
                }else{ // ELSE 3
                    if(txtType.getText().length() < 1){
                        JOptionPane.showMessageDialog(this, "Please input reviewer type"); // CHECK TYPE
                    }else{ // ELSE 4
                       
                        if(sameId <= 0){ // CHECK IF THE SAMEID IS EQUAL TO 0 IF TRUE EXECUTE THIS
                        questionSetEnable();
                        lblSet.setText("Set");
                        lblSet.setForeground(Color.green);
                        JOptionPane.showMessageDialog(this, "Nice!! you can now create your reviewer");
                        txtId.setEditable(false);
                        txtSubject.setEditable(false);
                        txtTitle.setEditable(false);
                        txtType.setEditable(false);
                        focusGainEnable = true;
                        insertTrue = true;
                        removeTrue = true;
                        insertToDatabaseTrue = true;
                        }else{ // IF THIS EXECUTE IT MEANS THAT THE USER INPUT ID_QUESTION IS ALREADY TAKEN
                            
                            int currentId = Integer.parseInt(txtId.getText());  
                            JOptionPane.showMessageDialog(this, "The id :" + currentId + " is already used!");
                            
                        }
                    } // ELSE 4
                } // ELSE 3
            } // ELSE 2
        } // ELSE 1
       }else{// CHECK LBLSET TEXT
           
           JOptionPane.showMessageDialog(this, "Already set");
           
       }
    }//GEN-LAST:event_lblSetMouseClicked

    private void txtIdFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtIdFocusGained
      
    }//GEN-LAST:event_txtIdFocusGained

    private void txtSubjectFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSubjectFocusGained
        
    }//GEN-LAST:event_txtSubjectFocusGained

    private void txtTitleFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTitleFocusGained
       
    }//GEN-LAST:event_txtTitleFocusGained

    private void txtTypeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTypeFocusGained
       
    }//GEN-LAST:event_txtTypeFocusGained

    private void txtItemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtItemMouseClicked
        JOptionPane.showMessageDialog(this, "This will auto detect");
    }//GEN-LAST:event_txtItemMouseClicked

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
            java.util.logging.Logger.getLogger(add_reviewer_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(add_reviewer_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(add_reviewer_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(add_reviewer_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new add_reviewer_form().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblClose;
    private javax.swing.JLabel lblDrag;
    private javax.swing.JLabel lblInsert;
    private javax.swing.JLabel lblInsert1;
    private javax.swing.JLabel lblMinimize;
    private javax.swing.JLabel lblRemove;
    private javax.swing.JLabel lblSet;
    private javax.swing.JPanel panelHeader;
    private javax.swing.JTable tblQuestions;
    private javax.swing.JTextField txtAns2;
    private javax.swing.JTextField txtAns3;
    private javax.swing.JTextField txtAns4;
    private javax.swing.JTextField txtCorrectAns;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtItem;
    private javax.swing.JTextArea txtQuestion;
    private javax.swing.JTextField txtSubject;
    private javax.swing.JTextField txtTitle;
    private javax.swing.JTextField txtType;
    // End of variables declaration//GEN-END:variables
}
