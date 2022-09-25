/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author danis
 */
public class CustomerDownloadVideo extends javax.swing.JFrame {

    /**
     * Creates new form CustomerDownloadVideo
     */
    String videotodownload;

    public CustomerDownloadVideo(String videopath) {
        initComponents();
        setVisible(true);
        setSize(800, 300);
        System.out.println(""+videopath);
        this.videotodownload = videopath;

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    HttpResponse<InputStream> httpres = Unirest.get(GlobalClass.ip+ "/GetResource/" + videotodownload).asBinary();

                    if (httpres.getStatus() == 200) {
                        
                       String filename = videotodownload.substring(videopath.lastIndexOf("/")+1);
                       System.out.println("ABC =="+filename);

                        InputStream is = httpres.getBody();
                        FileOutputStream fos = new FileOutputStream("d:\\vod\\" + filename);

                        long filesize = Integer.parseInt(httpres.getHeaders().getFirst("Content-Length"));

                        byte b[] = new byte[10000];
                        int r = 0;
                        int count = 0;

                        while (true) {

                            r = is.read(b, 0, b.length);
                            fos.write(b, 0, r);

                            count = count + r;

                            int progress = (int) ((count * 100) / filesize);

                            jProgressBar1.setStringPainted(true);
                            jProgressBar1.setValue(progress);
                            jProgressBar1.setString(progress + "% Downloaded");

                            if (count == filesize) {
                                break;
                            }

                        }

                        fos.close();

                        int ans = JOptionPane.showConfirmDialog(rootPane, "Open File", "File", JOptionPane.YES_NO_OPTION);

                        if (ans == JOptionPane.YES_OPTION) {
                            File f = new File("d:\\vod\\" + filename);
                            Desktop dt = Desktop.getDesktop();
                            dt.open(f);
                        } else {

                            JOptionPane.showMessageDialog(rootPane, "Can't Download...Try Again");

                        }

                    } else {

                    }

                } catch (Exception ex) {
                    Logger.getLogger(CustomerDownloadVideo.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }).start();

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
        jProgressBar1 = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel1.setText("Downloading File ......");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(50, 50, 250, 70);
        getContentPane().add(jProgressBar1);
        jProgressBar1.setBounds(50, 150, 280, 30);

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(CustomerDownloadVideo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CustomerDownloadVideo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CustomerDownloadVideo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CustomerDownloadVideo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new CustomerDownloadVideo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JProgressBar jProgressBar1;
    // End of variables declaration//GEN-END:variables
}