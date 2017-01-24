package automation;

import controller.SchedulerMain;
import controller.Sending;
import controller.UploadSFTP;

public class Automation {

    public static void main(String[] args) throws Exception {
//          Sending sendi = new Sending();
//          sendi.Send();
//        sendi.createFile();
//          UploadSFTP uploadToSftp = new UploadSFTP();
//          uploadToSftp.upload();
//          System.exit(0);
            SchedulerMain sm = new SchedulerMain();
            sm.schedule();
    }
}
