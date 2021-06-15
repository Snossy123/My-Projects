package clinic;

import com.mysql.jdbc.Connection;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Doctor extends Person implements User{
     public Connection fun () throws SQLException
    {
        connection makeconn = new connection();
            
        Connection conn = makeconn.conn();
        
        return conn;
    }
    
    //frames are used in receptionist page
    JFrame frame = new JFrame("Doctor");
    JFrame frame2 = new JFrame("Information Doctor");
    JFrame frame3 = new JFrame("view patient");
    //valiables local 
     private String user_name, user_password;
     private int doctorId; 
   /* constractor  this constructor to make a new */
    public Doctor(  String person_name, String person_address, String person_mobile, String user_name, String user_password) throws SQLException {
        super(person_name, person_address,person_mobile);
        this.user_name = user_name;
        this.user_password = user_password;
    } 
    //this constractor user to make frame to doctor
    public Doctor(String user_name, String user_password) throws SQLException {
      

      //Gui of home page of receptionist
            frame.setSize(500, 500);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocation(400, 120);
            frame.setResizable(false);
            frame.setLayout(null);
         //profile image
            JLabel profile = new JLabel(""+user_name);//create label and set text with username
            profile.setBounds(150, 30, 170, 150);//set bound of label
            ImageIcon icon = new ImageIcon("doctor.png");//image of profile
            profile.setHorizontalTextPosition(JLabel.CENTER);//position of words accordding image left or right or center
            profile.setVerticalTextPosition(JLabel.BOTTOM);//position of words accordding image top or bottom
            profile.setIconTextGap(20);//gap bettween image and text 

            Image img = icon.getImage();//convert ImageIcon to image to deal with her scale
            Image ImgScale = img.getScaledInstance(profile.getWidth(), profile.getHeight()-50, img.SCALE_SMOOTH);//set image scale 
            ImageIcon Imageicon = new ImageIcon(ImgScale);//return to original state ImageIco
            profile.setIcon(Imageicon);//set lable (profile) imgIcon of it 

         //buttons  are used in frame Receptionist 
            //Change Password
                JButton b1 = new JButton("Change Password");
                b1.setForeground(Color.MAGENTA);
                b1.setFont(new Font("Arial", Font.BOLD, 14));
                b1.setBounds(50, 400, 200, 30);
            //Logout
                JButton b2 = new JButton("Logout");
                b2.setForeground(Color.MAGENTA);
                b2.setFont(new Font("Arial", Font.BOLD, 14));
                b2.setBounds(350, 400, 100, 30);
            //Information Of Patient
                JButton b3 = new JButton("Information Of Patient");
                b3.setForeground(Color.MAGENTA);
                b3.setFont(new Font("Arial", Font.BOLD, 14));
                b3.setBounds(150, 230, 200, 30);
            //Submit
                JButton b4 = new JButton("Submit");
                b4.setForeground(Color.MAGENTA);
                b4.setFont(new Font("Arial", Font.BOLD, 14));
                b4.setBounds(50, 400, 150, 30);
            //Back
                JButton b5 = new JButton("Back");
                b5.setForeground(Color.MAGENTA);
                b5.setFont(new Font("Arial", Font.BOLD, 14));
                b5.setBounds(300, 400, 150, 30);
            //View patient
                JButton b6 = new JButton("View patient");
                b6.setForeground(Color.MAGENTA);
                b6.setFont(new Font("Arial", Font.BOLD, 14));
                b6.setBounds(150, 280, 200, 30);
            //Views
                JButton b9 = new JButton("View");
                b9.setForeground(Color.MAGENTA);
                b9.setFont(new Font("Arial", Font.BOLD, 14));
                b9.setBounds(50, 400, 150, 30);

       
        
       //acount Setting
           //change password
                b1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                 String username = JOptionPane.showInputDialog(frame, "Enter username");
                String oldpass = JOptionPane.showInputDialog(frame, "Enter Old Password");
                String newpass = JOptionPane.showInputDialog(frame, "Enter new Password");
                changepassword(username, oldpass, newpass);
            }
        });
            //logout
                b2.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    logout();
                }
            });

        



        //btn information doctor
            JLabel l1 = new JLabel("Enter Patient Id"); 
            JLabel l2 = new JLabel("Describe Medicine");
            JLabel l3 = new JLabel("Write Disease ");
            JLabel l4 = new JLabel("Need Bed");
            JLabel l5 = new JLabel("Number of Residency Days");
            JLabel l6 = new JLabel("Date");

        //btn information doctor
            JTextField t1 = new JTextField();
            JTextField t2 = new JTextField();
            JTextField t3 = new JTextField();
            JTextField t4 = new JTextField();
            JTextField t5 = new JTextField();
            JTextField t6 = new JTextField();
        //btn information doctor
            l1.setBounds(50, 50, 130, 30);
            l1.setForeground(Color.magenta);
            l1.setFont(new Font("Arial", Font.BOLD, 14));
            l2.setBounds(50, 150, 200, 30);
            l2.setForeground(Color.magenta);
            l2.setFont(new Font("Arial", Font.BOLD, 14));
            l3.setBounds(50, 100, 200, 30);
            l3.setForeground(Color.magenta);
            l3.setFont(new Font("Arial", Font.BOLD, 14));
            l4.setBounds(50, 200, 200, 30);
            l4.setForeground(Color.magenta);
            l4.setFont(new Font("Arial", Font.BOLD, 14));
            l5.setBounds(50, 250, 200, 30);
            l5.setForeground(Color.magenta);
            l5.setFont(new Font("Arial", Font.BOLD, 14));
            l6.setBounds(50, 300, 200, 30);
            l6.setForeground(Color.magenta);
            l6.setFont(new Font("Arial", Font.BOLD, 14));

        //btn information doctor
            t1.setBounds(300, 50, 160, 30);
            t1.setForeground(Color.magenta);
            t1.setFont(new Font("Arial", Font.BOLD, 16));
            t2.setBounds(300, 100, 160, 30);
            t2.setForeground(Color.magenta);
            t2.setFont(new Font("Arial", Font.BOLD, 16));
            t3.setBounds(300, 150, 160, 30);
            t3.setForeground(Color.magenta);
            t3.setFont(new Font("Arial", Font.BOLD, 16));
            t4.setBounds(300, 200, 160, 30);
            t4.setForeground(Color.magenta);
            t4.setFont(new Font("Arial", Font.BOLD, 16));
            t5.setBounds(300, 250, 160, 30);
            t5.setForeground(Color.magenta);
            t5.setFont(new Font("Arial", Font.BOLD, 16));
            t6.setBounds(300, 300, 160, 30);
            t6.setForeground(Color.magenta);
            t6.setFont(new Font("Arial", Font.BOLD, 16));


        //GUI add information doctor
            frame2.setSize(500, 500);
            frame2.setLocation(400, 120);
            frame2.setResizable(false);
            frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame2.setLayout(null);
        //ACTION add information doctor
            b3.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){

                
                //add components to frame add information   
                    frame2.add(b4);frame2.add(b5);
                    frame2.add(l1);frame2.add(l2);frame2.add(l3);frame2.add(l4);frame2.add(l5);frame2.add(l6);
                    frame2.add(t1);frame2.add(t2);frame2.add(t3);frame2.add(t4);frame2.add(t5);frame2.add(t6);
                    frame2.setVisible(true);
                    frame.setVisible(false);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                }
            });
                //btn submit
                    b4.addActionListener(new ActionListener(){
                        @Override
                       public void actionPerformed(ActionEvent e){
                            prescription presc = new prescription(Integer.parseInt(t1.getText()), t2.getText(), t3.getText(),Integer.parseInt(t4.getText()), Integer.parseInt(t5.getText()),Date.valueOf(t6.getText()));
                           info_patient(presc);
                           JOptionPane.showMessageDialog(frame2, "Successfully Inserted", "Successful", JOptionPane.INFORMATION_MESSAGE);
                             DELET_PATIENT_Wait(Integer.parseInt(t1.getText()));
                             t1.setText("");t2.setText("");t3.setText("");t4.setText("");t5.setText("");t6.setText("");
                           JOptionPane.showMessageDialog(frame2, "Successfully delete from waiting list", "Successful", JOptionPane.INFORMATION_MESSAGE);

                        }
                    });

                //btn back
                    b5.addActionListener(new ActionListener(){
                        @Override
                        public void actionPerformed(ActionEvent e){
                            t1.setText("");t2.setText("");t3.setText("");t4.setText("");t5.setText("");
                            frame2.setVisible(false);
                            frame.setVisible(true);
                        }
                    });
        



        
        //GUI view patient
            frame3.setSize(500, 500);
            frame3.setLocation(400, 120);
            frame3.setResizable(false);
            frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame3.setLayout(null);

        //btn view patient
            //tabel initial data
            JLabel labelsearch = new JLabel("Enter Your Id");
            JTextField textsearch = new JTextField();
            JTable TPat = new JTable();
            TPat.setBounds(30, 130,450 , 200);
            TPat.setModel(viewpatient());
            JScrollPane pane = new JScrollPane(TPat);
            pane.setBounds(30, 130,450 , 200);
            pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            pane.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            pane.setVisible(true);
            
            //after search
            JTable TPat2 = new JTable();
            TPat2.setBounds(30, 130,450 , 200);
            JScrollPane pane2 = new JScrollPane(TPat2);
            pane2.setBounds(30, 130,450 , 200);
            pane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            pane2.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            pane2.setVisible(false);
            
            //lebals
            labelsearch.setBounds(50, 50, 130, 30);
            labelsearch.setForeground(Color.magenta);
            labelsearch.setFont(new Font("Arial", Font.BOLD, 16));
            
            //textfield
            textsearch.setBounds(300, 50, 160, 30);
            textsearch.setForeground(Color.magenta);
            textsearch.setFont(new Font("Arial", Font.BOLD, 16));
    
            
            
        //view patient ACTION
            b6.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                //ADD COMPONENTS TO FRAME view patient
                    frame3.add(labelsearch);frame3.add(textsearch);
                    frame3.add(b9);frame3.add(b5);frame3.add(pane); frame3.add(pane2);                                       
                    frame3.setVisible(true);
                    frame.setVisible(false);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
            });
                //btn submit
                    b9.addActionListener(new ActionListener(){
                       @Override
                       public void actionPerformed(ActionEvent e){
                       if(textsearch.getText().equals(""))
                       {
                           try {
                               TPat2.setModel(viewpatient());
                           } catch (SQLException ex) {
                               Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, ex);
                           }
                               pane.setVisible(true);
                               pane2.setVisible(false);
                       }
                       else{
                           int doc_id = Integer.parseInt(textsearch.getText());
                           try {
                               TPat2.setModel(viewpatient_ofdoc(doc_id));
                           } catch (SQLException ex) {
                               Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, ex);
                           }
                               pane.setVisible(false);
                               pane2.setVisible(true);
                       }
                      }
                   });

                //btn back view patient ACTION
                    b5.setBounds(300, 400, 150, 30);
                    b5.addActionListener(new ActionListener(){
                        @Override
                        public void actionPerformed(ActionEvent e){
                            textsearch.setText("");
                            frame3.setVisible(false);
                            frame.setVisible(true);
                        }
                    });
            
            
        // ADD COMPONENTS TO FRAME dOCTOR    
            frame.add(profile);frame.add(b1);frame.add(b2);frame.add(b3);frame.add(b6); 
            frame.setVisible(true);
        
    }
  
    
    
    
    
    //functions setter & getter
            //User_name
          public void setUser_name(String user_name)
          {
                for(int i=0;i<user_name.length();++i)
              {
                  if(user_name.charAt(i)=='@' ||user_name.charAt(i)=='#' || user_name.charAt(i)=='$'
                         || user_name.charAt(i)=='%'||user_name.charAt(i)=='&' || user_name.charAt(i)=='*')
                      {
                       System.out.println("invalid input for user_name , it can't contine any special character ");
                       break;
                      }
                   else
                      {
                        this.user_name = user_name;
                      }
              }
          }


          public int getDoctorId() {
              return doctorId;
          }

          public void setDoctorId(int doctorId) {
              this.doctorId = doctorId;
          }

          public void setUser_password(String user_password) {
              this.user_password = user_password;
          }

          public String getUser_name() {
              return user_name;
          }

          public String getUser_password() {
              return user_password;
          }

          
          
          
    
//functions insert date from database            
    //information of patient
    public void info_patient(prescription presc){
        
        try{
        
        String insert = "INSERT INTO prescription(patient_id , describe_medicine , Bed_Needed  , date , disease , Number_Residency_Days ) VALUES (?,?,?,?,?,?)"; 
        PreparedStatement preparedStmt = fun().prepareStatement(insert);
// create the java statement
    // execute the query,
        preparedStmt.setInt(1, presc.getPatient_id() );
        preparedStmt.setString(2, presc.getDescribe_medicine() );
        preparedStmt.setInt(3, presc.getBed_needed() ); 
        preparedStmt.setDate(4, presc.getDate_pres() );
        preparedStmt.setString(5,  presc.getDisease()   );
        preparedStmt.setInt(6, presc.getNum_residency_days() );
        preparedStmt.execute();
             fun().close();
       
    }
    catch(SQLException e){
        JOptionPane.showMessageDialog(null, e);
    }
    }

    



//functions retrive date from database    
        //veiw patient inline
    public DefaultTableModel viewpatient() throws SQLException {
        
        DefaultTableModel dm = new DefaultTableModel();
        String[] arr = {"id","name","mobile","address" ,"token_id","date","doc_id"};
        
        for (int i = 0; i < arr.length; i++) {
            dm.addColumn(arr[i]);
        }
         
        String qSql = "SELECT * FROM `patient` WHERE `token_id` != 'NULL'   ";
        Statement stm = fun().createStatement();
        ResultSet rs = stm.executeQuery(qSql);
        while(rs.next())
        {
               Object [] row = {rs.getInt("id"), rs.getString("name"), rs.getString("mobile"), rs.getString("address") , rs.getString("token_id") ,rs.getDate("date") , rs.getInt("doctor_id") };
                dm.addRow(row);
        }
            
        return  dm; 
    }   
    public DefaultTableModel viewpatient_ofdoc(int doc_id) throws SQLException {
        
        DefaultTableModel dm = new DefaultTableModel();
        String[] arr = {"id","name","mobile","address" ,"token_id","date","doc_id"};
        
        for (int i = 0; i < arr.length; i++) {
            dm.addColumn(arr[i]);
        }
         
        String qSql = "SELECT * FROM patient WHERE doctor_id = '"+doc_id+"'AND token_id != 'NULL' ";
        Statement stm = fun().createStatement();
        ResultSet rs = stm.executeQuery(qSql);
        while(rs.next())
        {
               Object [] row = {rs.getInt("id"), rs.getString("name"), rs.getString("mobile"), rs.getString("address") , rs.getString("token_id") ,rs.getDate("date"), rs.getInt("doctor_id") };
                dm.addRow(row);
        }
            
        return  dm; 
    }   
   
    
    
    
//functions update & delete date from database  
    private void update_pass(String username , String newpass) {
    try{
         
        String update = "UPDATE doctor SET password = ? WHERE username = ?"; 
     // create the java statement
     PreparedStatement pst = fun().prepareStatement(update);
     pst.setString(1, newpass);
     pst.setString(2, username);
     pst.executeUpdate();
     fun().close();
     }
    catch(SQLException e){
       JOptionPane.showMessageDialog(null, e);
    }
    
 }
    private void DELET_PATIENT_Wait(int pat_id){
         try{
        // create the java mysql update preparedstatement
      String query = "delete FROM waiting_list where patient_id = ?";
      PreparedStatement preparedStmt = fun().prepareStatement(query);
      preparedStmt.setInt(1, pat_id);

      // execute the java preparedstatement
      preparedStmt.executeUpdate();
      
      fun().close();
    }
    catch(SQLException e )
    {
        JOptionPane.showMessageDialog( frame2, e);
    }
    
    }
   
    
    
//user functions
     private boolean check_old_pass(String username , String oldpass)throws SQLException{
    try{
        String check = "SELECT username , password FROM passreceptionist WHERE username = '"+username+"'And' password = '"+oldpass+"'"; 
     // create the java statement
    Statement pst=  fun().createStatement();
    // execute the query,
        ResultSet result = pst.executeQuery(check);
        if(result.next())
            return true;
        else 
            return false;
    }
    catch(SQLException e){
       JOptionPane.showMessageDialog(null, e);
    }
    return false;
}
     public void changepassword(String username , String user_old_password, String user_new_password) {
            user_password = user_new_password;
            update_pass( username ,  user_new_password);
            JOptionPane.showMessageDialog(frame, "Successfully Update", "Successful", JOptionPane.INFORMATION_MESSAGE);
  }
     @Override
     public void logout() {
        frame.setVisible(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
