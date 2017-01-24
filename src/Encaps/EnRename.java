package Encaps;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

public class EnRename {

    InputStream inStream = null;
    OutputStream outStream = null;
    File inputFile, outputFile;
    int length, nol;
    Date date;
    String tgl;

    public InputStream getInStream() {
        return inStream;
    }

    public void setInStream(InputStream inStream) {
        this.inStream = inStream;
    }

    public OutputStream getOutStream() {
        return outStream;
    }

    public void setOutStream(OutputStream outStream) {
        this.outStream = outStream;
    }

    public File getInputFile() {
        return inputFile;
    }

    public void setInputFile(File inputFile) {
        this.inputFile = inputFile;
    }

    public File getOutputFile() {
        return outputFile;
    }

    public void setOutputFile(File outputFile) {
        this.outputFile = outputFile;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTgl() {
        return tgl;
    }

    public void setTgl(String tgl) {
        this.tgl = tgl;
    }

    public int getNol() {
        return nol;
    }

    public void setNol(int nol) {
        this.nol = nol;
    }

}
