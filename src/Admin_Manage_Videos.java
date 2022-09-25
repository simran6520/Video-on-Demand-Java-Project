
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Simran
 */
public class Admin_Manage_Videos extends javax.swing.JFrame {

    /**
     * Creates new form Admin_Manage_Videos
     */
    int width,height;
    ArrayList<Video_Details> al=new ArrayList<>();
    mytable table_obj=new mytable();
    JFileChooser jfc=new JFileChooser();
    File video_obj,video_thumb_obj;
    public Admin_Manage_Videos() {
        initComponents();
        Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
        
        width=d.width;
        height=d.height;
        
        setSize(width,height);
         
        setVisible(true);
         getContentPane().setBackground(new Color(51,204,255));
        
        jCombocategory.removeAllItems();
        jCombocategory.addItem("----Select Category---");
        
        for(int i=0;i<GlobalClass.category.length;i++)
        {
            jCombocategory.addItem(GlobalClass.category[i]);
            
        }
       // jCombocourses.removeAllItems();
        jCombocategory.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
              
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
             if(e.getStateChange()==ItemEvent.SELECTED)
                {
                 String category=jCombocategory.getSelectedItem().toString();
               //  JOptionPane.showMessageDialog(rootPane,category);
              fetch_category(category);
                }
            }
        });
        jTable1.setModel(table_obj);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jCombocategory = new javax.swing.JComboBox();
        jCombocourses = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jlecture_no = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jtitle = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jduration = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jvideo_path = new javax.swing.JLabel();
        jthumbnail = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jComboBoxcourses2 = new javax.swing.JComboBox();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel1.setText("Select Category");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(50, 30, 120, 50);

        jLabel2.setText("Select Course");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(47, 110, 110, 35);

        jCombocategory.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(jCombocategory);
        jCombocategory.setBounds(400, 30, 234, 45);

        jCombocourses.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(jCombocourses);
        jCombocourses.setBounds(400, 110, 234, 45);

        jLabel3.setText("Lecture No");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(50, 200, 97, 41);
        getContentPane().add(jlecture_no);
        jlecture_no.setBounds(400, 200, 234, 47);

        jLabel4.setText("Title");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(50, 280, 97, 41);
        getContentPane().add(jtitle);
        jtitle.setBounds(400, 290, 234, 41);

        jLabel5.setText("Duration");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(50, 370, 105, 40);
        getContentPane().add(jduration);
        jduration.setBounds(400, 380, 234, 40);

        jLabel6.setText("Video Path");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(50, 460, 82, 38);

        jLabel7.setText("Video Thumbnail");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(50, 540, 150, 38);
        getContentPane().add(jvideo_path);
        jvideo_path.setBounds(300, 470, 80, 30);
        getContentPane().add(jthumbnail);
        jthumbnail.setBounds(300, 540, 60, 30);

        jButton1.setText("Browse");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(510, 460, 110, 40);

        jButton2.setText("Browse");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(510, 540, 110, 40);

        jButton3.setText("Submit");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(260, 590, 140, 50);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(720, 90, 470, 480);

        jComboBoxcourses2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(jComboBoxcourses2);
        jComboBoxcourses2.setBounds(740, 20, 160, 40);

        jButton4.setText("Search");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4);
        jButton4.setBounds(960, 20, 140, 40);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
         int ans= jfc.showOpenDialog(this);
        if(ans==JFileChooser.APPROVE_OPTION)
          video_thumb_obj=jfc.getSelectedFile();
        jthumbnail.setText(video_thumb_obj.getAbsolutePath());
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int ans= jfc.showOpenDialog(this);
        if(ans==JFileChooser.APPROVE_OPTION)
          video_obj=jfc.getSelectedFile();
        jvideo_path.setText(video_obj.getAbsolutePath());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        int lecture_no=Integer.parseInt(jlecture_no.getText());
        String title=jtitle.getText();
        String duration=jduration.getText();
        
        String courses=jCombocourses.getSelectedItem().toString();
        
        try {
            HttpResponse<String> httpres=Unirest.post(GlobalClass.ip+"videos")
                    .queryString("lectureno",lecture_no)
                    .queryString("title",title)
                    .queryString("duration",duration)
                    .queryString("courses",courses)
                    .field("video",video_obj)
                    .field("video_thumb",video_thumb_obj)
                    .asString();
            
            
            if(httpres.getStatus()==200)
            {
             String res=httpres.getBody();
             JOptionPane.showMessageDialog(rootPane, res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        String courses=jComboBoxcourses2.getSelectedItem().toString();
        try {
            HttpResponse<String> httpResponse=Unirest.get(GlobalClass.ip+"view_videos")
                    .queryString("coursename",courses)
                    .asString();
            
            if(httpResponse.getStatus()==200)
            {
             String ans=httpResponse.getBody();
             
              StringTokenizer st=new StringTokenizer(ans,"~~");
              al.clear();
                while(st.hasMoreTokens())
                {
                 String res2=st.nextToken();
                 StringTokenizer st2=new StringTokenizer(res2,";;");
                        
                         
                          
                 int video_id=Integer.parseInt(st2.nextToken());
                 int lecture_no=Integer.parseInt(st2.nextToken());
                 String video_path=st2.nextToken();
                 String video_thumbnails=st2.nextToken();
                 String duration=st2.nextToken();
                 String title=st2.nextToken();
                
                 
                 al.add(new Video_Details(video_id,lecture_no,video_path, video_thumbnails,duration,title));
                        // al.add(new course_detail(coursename, desc,photowide,photosquare,video,price));
                }
                 System.out.println(""+al.size());
                
                table_obj.fireTableDataChanged();
            
            }
        } catch (UnirestException ex) {
            Logger.getLogger(Admin_Manage_Videos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(Admin_Manage_Videos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Admin_Manage_Videos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Admin_Manage_Videos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Admin_Manage_Videos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Admin_Manage_Videos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox jComboBoxcourses2;
    private javax.swing.JComboBox jCombocategory;
    private javax.swing.JComboBox jCombocourses;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jduration;
    private javax.swing.JTextField jlecture_no;
    private javax.swing.JLabel jthumbnail;
    private javax.swing.JTextField jtitle;
    private javax.swing.JLabel jvideo_path;
    // End of variables declaration//GEN-END:variables

public void fetch_category(String categoryname)
 
 {
     jCombocourses.removeAllItems();
     jComboBoxcourses2.removeAllItems();
     jCombocourses.addItem("---Select Item---");
     jComboBoxcourses2.addItem("---Select Item---");
    // al.clear();
  try {
            HttpResponse<String> httpres=Unirest.get(GlobalClass.ip+"fetch_courses")
                    .queryString("categoryname",categoryname)
                    .asString();
            
            if(httpres.getStatus()==200){
            String res=httpres.getBody();
                System.out.println(""+res);
            
                StringTokenizer st=new StringTokenizer(res,"~~");
                while(st.hasMoreTokens())
                {
                 String res2=st.nextToken();
                 StringTokenizer st2=new StringTokenizer(res2,";;");
                         
                 
                 
                 
                         String coursename=st2.nextToken();
                         String desc=st2.nextToken();
                         String photowide=st2.nextToken();
                         String photosquare=st2.nextToken();
                         String video=st2.nextToken();
                         int price=Integer.parseInt(st2.nextToken());
                         
                         System.out.println(""+coursename);
                
                 
                  jCombocourses.addItem(coursename);
                  jComboBoxcourses2.addItem(coursename);
                 
                }   
               
                         //al.add(new course_detail(coursename, desc,photowide,photosquare,video,price));
                
            
               // System.out.println(""+al.size());
                //table_obj.fireTableDataChanged();
        }
        } catch (UnirestException ex) {
            Logger.getLogger(Admin_Manage_Courses.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
 }


class mytable extends AbstractTableModel
 {
        String arr[]={"Lecture_No","Title","Duration"};
        @Override
        public int getRowCount() {
            return al.size();
        }

        @Override
        public int getColumnCount() {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         return arr.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
          //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       if(columnIndex==0)
       {
         return al.get(rowIndex).lecture_no;
       }
       else if(columnIndex==1)
       {
        return al.get(rowIndex).title;
       }
       else if(columnIndex==2)
       {
        return al.get(rowIndex).duration;
       }
       else
       {
        return "default";
       }
        }

        @Override
        public String getColumnName(int column) {
            //return super.getColumnName(column); //To change body of generated methods, choose Tools | Templates.
        
        return arr[column];
        }
         
 
 
 }
 
}
