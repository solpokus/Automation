package controller;

import java.util.Date;
import java.util.TimerTask;

public class Schedule extends TimerTask {
    Date now;

    @Override
    public void run() {
        try {
            Sending sendi = new Sending();
            sendi.Send();
            sendi.createFile();
            
            RenameFile rFile = new RenameFile();
            rFile.rename();
            
            UploadSFTP uploadToSftp = new UploadSFTP();
            uploadToSftp.upload();
            
            now = new Date();
            System.out.println("Time is :" + now);
        
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
