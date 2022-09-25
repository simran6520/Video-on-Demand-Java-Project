
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Simran
 */
public class User_Home extends javax.swing.JFrame {

    /**
     * Creates new form User_Home
     */
    int x=10,y=10;
    
    int width,height;
    String username;
    public User_Home(String username) {
        initComponents();
        setVisible(true);
        
        
        Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
        width=d.width;
        height=d.height;
        setSize(width,height);
        this.username=username;
        welcome.setText("Welcome "+username);
        Design ds[]=new Design[GlobalClass.category.length];
        mainpanel.setPreferredSize(new Dimension((GlobalClass.category.length*550),1500)); //to apply temporary scroll
        menubar.setBounds(x,y,width,150);
        jScrollPane1.setBounds(x,170,width-50,height);
        for(int i=0;i<GlobalClass.category.length;i++)
        {
         ds[i]=new Design();
         ds[i].setBounds(x,y,width-100,400);
         y=y+420;
         //x=x+220;
         
         ds[i].jScrollPane1.setBounds(10,80,width-200,300);
                  ds[i].jtext.setText(GlobalClass.category[i]);

         //second panel
         try {
     // al.clear();
            HttpResponse<String> httpres=Unirest.get(GlobalClass.ip+"fetch_courses")
                    .queryString("categoryname",GlobalClass.category[i])
                    .asString();
            
            if(httpres.getStatus()==200){
            String res=httpres.getBody();
                System.out.println(""+res);
            
                StringTokenizer st=new StringTokenizer(res,"~~");
                
                int count=st.countTokens();
                         ds[i].jPanel1.setPreferredSize(new Dimension(1500,(count*420)));

                int j=0;
                 int x1=10,y1=10;
                courses_panel cp[]=new courses_panel[count];
                
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
                         
                        cp[j]=new courses_panel();
                        cp[j].setBounds(x1,y1,250,250);
                        x1=x1+270;
                        
//                        cp[j].jpic.setText((photosquare));
                        cp[j].jname.setText(coursename);
                        cp[j].jdesc.setText(desc);
                        
                        
                         try {
                        URL uri = new URL(GlobalClass.ip + "GetResource/" + photosquare);
                        System.out.println(uri);
                        BufferedImage im = ImageIO.read(uri);
                        BufferedImage im2 = GlobalClass.resizephoto(im, cp[j].jpic.getWidth(), cp[j].jpic.getHeight());
                        cp[j].jpic.setIcon(new ImageIcon(im2));

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                      
                         course_detail obj=(new course_detail(coursename, desc,photowide,photosquare,video,price));
                        ds[i].jPanel1.add(cp[j]);
                        ds[i].jPanel1.repaint();
                        
                        cp[j].addMouseListener(new MouseAdapter() {
                        
                        @Override
                        public void mouseClicked(MouseEvent e)
                        {
                        // super.mouseClicked(e);
                         //JOptionPane.showMessageDialog(rootPane,coursename);
                            
                            User_View_Courses obj2=new User_View_Courses(obj,username);
                        }
                        });
                        
                        
                         j++;
                }
              //  System.out.println(""+al.size());
                //table_obj.fireTableDataChanged();
        }
        } catch (UnirestException ex) {
            Logger.getLogger(Admin_Manage_Courses.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         
         
         
         
         
         
         
         mainpanel.add(ds[i]);
         mainpanel.repaint();
         repaint();
    }
        
        repaint();
    }

    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menubar = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        welcome = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        mainpanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        menubar.setBackground(new java.awt.Color(255, 153, 102));
        menubar.setLayout(null);

        jButton1.setText("Change Password");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        menubar.add(jButton1);
        jButton1.setBounds(330, 30, 180, 50);

        jButton2.setText("Logout");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        menubar.add(jButton2);
        jButton2.setBounds(580, 30, 150, 50);

        welcome.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        welcome.setText("jLabel1");
        menubar.add(welcome);
        welcome.setBounds(20, 30, 230, 40);

        getContentPane().add(menubar);
        menubar.setBounds(0, 0, 1030, 170);

        mainpanel.setBackground(new java.awt.Color(0, 204, 255));
        mainpanel.setLayout(null);
        jScrollPane1.setViewportView(mainpanel);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(0, 170, 1030, 470);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        User_change_Password obj=new User_change_Password(username);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        dispose();
        User_Login obj=new User_Login();
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(User_Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(User_Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(User_Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(User_Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new User_Home("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel mainpanel;
    private javax.swing.JPanel menubar;
    private javax.swing.JLabel welcome;
    // End of variables declaration//GEN-END:variables

   
}
