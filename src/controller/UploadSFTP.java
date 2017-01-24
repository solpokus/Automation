package controller;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import Encaps.EncapSftp;
import Encaps.Encapsulation;
import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.Properties;

public class UploadSFTP {
    
    public void upload() {
        EncapSftp sftp = new EncapSftp();
        sftp.setSftpHost("10.10.10.27");
        sftp.setSftpPort(22);
        sftp.setSftpUser("ADi");
        sftp.setSftpPass("P@ssw0rd");
        sftp.setSftpDir("testing");
        
        sftp.setChannel(null);
        sftp.setSession(null);
        sftp.setChannelSftp(null);
        
        try {
            JSch jsch = new JSch();
            sftp.setSession(jsch.getSession(sftp.getSftpUser(), sftp.getSftpHost(), sftp.getSftpPort()));
            sftp.getSession().setPassword(sftp.getSftpPass());
            
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            sftp.getSession().setConfig(config);
            sftp.getSession().connect();
            sftp.setChannel(sftp.getSession().openChannel("sftp"));
            sftp.getChannel().connect();
            sftp.setChannelSftp((ChannelSftp) sftp.getChannel());
            sftp.getChannelSftp().cd(sftp.getSftpDir());
            //sftp.setInputFile(new File("aia.xml"));
            //add
            Date tgl = new Date();
            DateFormat df = new DateFormat();
            Encapsulation encaps = new Encapsulation();
            encaps.setTgl(df.dateFormatFull(tgl));
            encaps.setTgl("aia-" + encaps.getTgl() + ".xml");
            sftp.setInputFile(new File(encaps.getTgl()));
            //end
            sftp.getChannelSftp().put(new FileInputStream(sftp.getInputFile()), sftp.getInputFile().getName());
        } catch (Exception e) {
            System.out.println("Error Upload to SFTP : " + e.getMessage());
            e.printStackTrace();
        } finally {
            sftp.setChannel(null);
            sftp.setSession(null);
            sftp.setChannelSftp(null);
        }
    }
}
