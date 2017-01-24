package Encaps;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;
import java.io.File;

public class EncapSftp {

    String sftpHost, sftpUser, sftpPass, sftpDir;
    int sftpPort;
    Session session;
    Channel channel;
    ChannelSftp channelSftp;
    File inputFile;

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public ChannelSftp getChannelSftp() {
        return channelSftp;
    }

    public void setChannelSftp(ChannelSftp channelSftp) {
        this.channelSftp = channelSftp;
    }

    public String getSftpHost() {
        return sftpHost;
    }

    public void setSftpHost(String sftpHost) {
        this.sftpHost = sftpHost;
    }

    public int getSftpPort() {
        return sftpPort;
    }

    public void setSftpPort(int sftpPort) {
        this.sftpPort = sftpPort;
    }

    public String getSftpUser() {
        return sftpUser;
    }

    public void setSftpUser(String sftpUser) {
        this.sftpUser = sftpUser;
    }

    public String getSftpPass() {
        return sftpPass;
    }

    public void setSftpPass(String sftpPass) {
        this.sftpPass = sftpPass;
    }

    public String getSftpDir() {
        return sftpDir;
    }

    public void setSftpDir(String sftpDir) {
        this.sftpDir = sftpDir;
    }

    public File getInputFile() {
        return inputFile;
    }

    public void setInputFile(File inputFile) {
        this.inputFile = inputFile;
    }

}
