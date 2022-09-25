
public class Video_Details {
    int video_id;
    int lecture_no;
    String video_thumbnails;
    String duration;
    String title;

   // public Video_Details(int video_id, int lecture_no, String video_thumbnails, String duration, String title) {
        
   // }

   // Video_Details(int video_id, int lecture_no, String video_path, String video_thumbnails, String duration, String title) {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   // }

    Video_Details(int video_id, int lecture_no, String video_path, String video_thumbnails, String duration, String title) {
   
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        this.video_id = video_id;
        this.lecture_no = lecture_no;
        this.video_thumbnails = video_thumbnails;
        this.duration = duration;
        this.title = title;
    
    }
}
