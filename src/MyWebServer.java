
import com.vmm.JHTTPServer;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyWebServer  extends JHTTPServer
{

    public MyWebServer(int port) throws IOException {
        super(port);
    }

    @Override
    public Response serve(String uri, String method, Properties header, Properties parms, Properties files)
    {
        //to handle all requests
        Response res= new Response(HTTP_OK,"text/plain","Hello from Java Server_how are you");
        
      
      
        
//return super.serve(uri, method, header, parms, files); //To change body of generated methods, choose Tools | Templates.
       
         System.out.println(""+uri);
        
        if(uri.contains("/adminlogin")){
            String name = parms.getProperty("username");
            String password = parms.getProperty("password");
            String ans="";
            
            System.out.println(""+name+"******"+password);
            try
            {
            ResultSet rs=DBLoader.executeQuery("select * from admin where username='"+name+"' and password='"+password+"'");
            if(rs.next())
            {
             ans="success";
            }
            else
            {
             ans="fail";
            }
            }
            catch(Exception e)
            {
             e.printStackTrace();
            }
            //send response success or fail
             res=new Response(HTTP_OK,"text/plain",ans);
            //http ok is status and its value is 200
        }
        
        
        else if(uri.contains("/adminchangepass"))
        {
        
         String username=parms.getProperty("username");
         String oldpass=parms.getProperty("oldpass");
         String newpass=parms.getProperty("newpass");
         String ans="";
          try
            {
            ResultSet rs=DBLoader.executeQuery("select * from admin where username='"+username+"'and password='"+oldpass+"'");
            if(rs.next())
            {
                rs.moveToCurrentRow();
                rs.updateString("password", newpass);
                rs.updateRow();
                
             ans="success";
            }
            else
            {
             ans="Invalid old password";
            }
            }
            catch(Exception e)
            {
             e.printStackTrace();
            }
            //send response success or fail
             res=new Response(HTTP_OK,"text/plain",ans);
            //http ok is status and its value is 200
        }
        
        else if(uri.contains("/addcourses"))
        {
            String ans="";
        String category=parms.getProperty("category");
        String coursename=parms.getProperty("coursename");
        String desc=parms.getProperty("desc");
        String price=parms.getProperty("price");
        System.out.println(""+category);
        
        
        String wide_pic=saveFileOnServerWithRandomName(files,parms,"wide","src/photos");
        String square_pic=saveFileOnServerWithRandomName(files,parms,"square","src/photos");
        String video=saveFileOnServerWithRandomName(files,parms,"video","src/uploads");
        
        try
        {
        ResultSet rs=DBLoader.executeQuery("select * from courses where coursename='"+coursename+"'");
        if(rs.next())
        {
        
         ans="Already Exists";

         
        }
        else
        {
        rs.moveToInsertRow();
        rs.updateString("coursename", coursename);
        rs.updateString("desc", desc);
        rs.updateInt("price",Integer.parseInt(price));
        rs.updateString("category",category);
        rs.updateString("photowide","src/photos/"+wide_pic);
        rs.updateString("photosquare","src/photos/"+square_pic);
        rs.updateString("video","src/uploads/"+video);
        rs.insertRow();
        ans="Success";
        }
        res=new Response(HTTP_OK,"text/plain",ans);
        }
        catch(Exception e)
        {
         e.printStackTrace();
        }
        
        }
        
        else if(uri.contains("/fetch_courses"))
        {
            try {
            String ans="";
         String categoryname=parms.getProperty("categoryname");
         
            System.out.println(""+categoryname);
            
            ResultSet rs=DBLoader.executeQuery("select * from courses where category='"+categoryname+"'");
            
                while(rs.next())
                {
                    String coursename=rs.getString("coursename");
                    String desc=rs.getString("desc");
                    int price=rs.getInt("price");
                    String photowide=rs.getString("photowide");
                    String photosquare=rs.getString("photosquare");
                    String video=rs.getString("video");
                    
                    System.out.println(""+coursename);
                    
                    ans+=coursename+";;"+desc+";;"+photowide+";;"+photosquare+";;"+video+";;"+price+"~~";
                }
                
                res=new Response(HTTP_OK,"text/plain",ans);
            } catch (Exception e) {
                e.printStackTrace();
            }
         
        }
        
        else if(uri.contains("/deletecourses"))
        {
            String ans="";
         String name=parms.getProperty("name");
            System.out.println(""+name);
         ResultSet rs=DBLoader.executeQuery("select * from courses where coursename='"+name+"'");
            try {
                if(rs.next())
                {
                    rs.deleteRow();
                    ans="success";
                }
                else
                {
                    ans="fail";
                }   
            
                    res=new Response(HTTP_OK,"text/plain",ans);

            } catch (SQLException ex) {
                Logger.getLogger(MyWebServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        else if(uri.contains("/user"))
        {
        
            String name = parms.getProperty("username");
            String password = parms.getProperty("password");
            String ans="";
            
            System.out.println(""+name+"******"+password);
            try
            {
            ResultSet rs=DBLoader.executeQuery("select * from users where username='"+name+"' and password='"+password+"'");
            if(rs.next())
            {
             ans="success";
            }
            else
            {
             ans="fail";
            }
            }
            catch(Exception e)
            {
             e.printStackTrace();
            }
            //send response success or fail
             res=new Response(HTTP_OK,"text/plain",ans);
            //http ok is status and its value is 200
        
        }
        
        else if(uri.contains("/signup"))
                {
                String ans="";
                String username=parms.getProperty("username");
                String email=parms.getProperty("email");
                String password=parms.getProperty("password");
                String mobileno=parms.getProperty("mobileno");
                String security_ques=parms.getProperty("security_ques");
                String security_ans=parms.getProperty("security_ans");
                
                
                try {
                ResultSet rs=DBLoader.executeQuery("select * from users where username='"+username+"'");
            
                if(rs.next())
                {
                    
                    ans="Already Exists";
                    
                }
                else
                { 
                    rs.moveToInsertRow();
                    rs.updateString("username",username);
                    rs.updateString("email",email);
                    rs.updateString("password",password);
                    rs.updateString("security_ques",security_ques);
                    rs.updateString("security_ans",security_ans);
                    rs.updateString("mobileno",mobileno);
                    rs.insertRow();
                    ans="success";
                }
                     res=new Response(HTTP_OK,"text/plain",ans);
                
            } catch (SQLException ex) {
                Logger.getLogger(MyWebServer.class.getName()).log(Level.SEVERE, null, ex);
            }
                }
        else if(uri.contains("/pass"))
        {
         String username=parms.getProperty("username");
         String oldpass=parms.getProperty("oldpass");
         String newpass=parms.getProperty("newpass");
         
                          System.out.println("old = "+oldpass+"..."+newpass+".");

         String ans="";
          try
            {
            ResultSet rs=DBLoader.executeQuery("select * from users where username='"+username+"'and password='"+oldpass+"'");
            if(rs.next())
            {
                rs.moveToCurrentRow();
                rs.updateString("password",newpass);
                rs.updateRow();
                
                 ans="success";
                 
        
            }
            else
            {
             ans="Invalid old password";
            }
            
            }
            catch(Exception e)
            {
             e.printStackTrace();
            }
            //send response success or fail
             res=new Response(HTTP_OK,"text/plain",ans);
            //http ok is status and its value is 200
        
        }
        else if(uri.contains("/videos"))
        {
        
         int lectureno=Integer.parseInt(parms.getProperty("lectureno"));
         String title=parms.getProperty("title");
         String duration=parms.getProperty("duration");
         String courses=parms.getProperty("courses");
         
         String video=saveFileOnServerWithRandomName(files,parms,"video","src/uploads");
         String video_thumb=saveFileOnServerWithRandomName(files,parms,"video_thumb","src/uploads");
         
         ResultSet rs=DBLoader.executeQuery("select * from videos");
         
            try {
                rs.moveToInsertRow();
                rs.updateInt("lecture_no", lectureno);
                rs.updateString("video_path","src/uploads/"+video);
                rs.updateString("video_thumbnails","src/uploads/"+video_thumb);
                rs.updateString("duration",duration);
                rs.updateString("title",title);
                rs.updateString("course_name",courses);
                
                rs.insertRow();
            
                
                res=new Response(HTTP_OK,"text/plain","success");
            }catch (Exception e) {
               e.printStackTrace();
            }
        }
        
        else if(uri.contains("/view_videos"))
        {
         String coursename=parms.getProperty("coursename");
         String ans="";
         
         ResultSet rs=DBLoader.executeQuery("select * from videos where course_name='"+coursename+"'");
            try {
                while(rs.next()){
                    
                    int video_id=rs.getInt("video_id");
                    int lecture_no=rs.getInt("lecture_no");
                    String video_path=rs.getString("video_path");
                    String video_thumbnails=rs.getString("video_thumbnails");
                    String duration=rs.getString("duration");
                    String title=rs.getString("title");
                    ans=ans+video_id+";;"+lecture_no+";;"+video_path+";;"+video_thumbnails+";;"+duration+";;"+title+"~~";
                    
                    
                } 
            
               res=new Response(HTTP_OK,"text/plain",ans);
            } catch (SQLException ex) {
                Logger.getLogger(MyWebServer.class.getName()).log(Level.SEVERE, null, ex);
            }
       
        
        }
     else  if(uri.contains("/GetResource"))
       {
           uri = uri.substring(1);
           uri = uri.substring(uri.indexOf("/")+1);
           System.out.println(uri+"***");
           res = sendCompleteFile(uri);
       }
        
     else if(uri.contains("/subscription"))
     {
         try {
      String course_name=parms.getProperty("course_name");
      String user_name=parms.getProperty("user_name");
      String ans="";
      
      
      ResultSet rs=DBLoader.executeQuery("select * from user_subscriptions where user_name='"+user_name+"'and course_name='"+course_name+"'");
     
      if(rs.next())
     {
      ans="Already Paid";
     }
     else
     {
          
      rs.moveToInsertRow();
          
      rs.updateString("user_name",user_name);
      rs.updateString("course_name",course_name);
      rs.insertRow();
      ans="success";
     }
      res=new Response(HTTP_OK,"text/plain",ans);
     }
          catch (SQLException ex) {
              Logger.getLogger(MyWebServer.class.getName()).log(Level.SEVERE, null, ex);
          }
     
     
     }
     else if(uri.contains(("/check")))
     {
     
         try {
      String course_name=parms.getProperty("course_name");
      String user_name=parms.getProperty("user_name");
      String ans="";
      
      
      ResultSet rs=DBLoader.executeQuery("select * from user_subscriptions where user_name='"+user_name+"'and course_name='"+course_name+"'");
     
      if(rs.next())
     {
      ans="success";
     }
     else
     {
          
      
      ans="Fails";
     }
      res=new Response(HTTP_OK,"text/plain",ans);
     }
          catch (SQLException ex) {
              Logger.getLogger(MyWebServer.class.getName()).log(Level.SEVERE, null, ex);
          }
      
     }
        
     else if (uri.contains("/StreamMedia")) {
            uri = uri.substring(1);
            uri = uri.substring(uri.indexOf("/") + 1);
            System.out.println(uri);
            res = streamFile(uri, method, header);

        }
     else if(uri.contains("/adminsignup"))
     {
     
     
                String ans="";
                String username=parms.getProperty("username");
                
                String password=parms.getProperty("password");
                String mobileno=parms.getProperty("mobileno");
                
                
                try {
                ResultSet rs=DBLoader.executeQuery("select * from admin where username='"+username+"'");
            
                if(rs.next())
                {
                    
                    ans="Admin already Exists";
                    
                }
                else
                { 
                    rs.moveToInsertRow();
                    rs.updateString("username",username);
                   
                    rs.updateString("password",password); 
                    rs.updateString("mobileno",mobileno);
                    rs.insertRow();
                    ans="Admin successfully registered";
                }
                     res=new Response(HTTP_OK,"text/plain",ans);
                
            } catch (SQLException ex) {
                Logger.getLogger(MyWebServer.class.getName()).log(Level.SEVERE, null, ex);
            }
     }
        

return res;
}
            
}
