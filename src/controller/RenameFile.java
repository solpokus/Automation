package controller;

import Encaps.EnRename;
import Encaps.Encapsulation;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

public class RenameFile {

//    public static void main(String[] args) {
//
//        InputStream inStream = null;
//        OutputStream outStream = null;
//        Date tgl = new Date();
//        DateFormat df = new DateFormat();
//        Encapsulation encaps = new Encapsulation();
//        encaps.setTgl(df.dateFormatDash(tgl));
//        encaps.setTgl("aia-" + encaps.getTgl() + ".xml");
//        try {
//
//            File afile = new File("aia.xml");
//            File bfile = new File(encaps.getTgl());
//
//            inStream = new FileInputStream(afile);
//            outStream = new FileOutputStream(bfile);
//
//            byte[] buffer = new byte[1024];
//
//            int length;
//            //copy the file content in bytes
//            while ((length = inStream.read(buffer)) > 0) {
//                outStream.write(buffer, 0, length);
//            }
//
//            inStream.close();
//            outStream.close();
//
//            //delete the original file
//            afile.delete();
//
//            System.out.println("File is copied successful!");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    public static void rename(){
//        EnRename encaps = new EnRename();
//        DateFormat df = new DateFormat();
//        InputStream inStream = null;
//        OutputStream outStream = null;
//        
//        encaps.setDate(new Date());
//        encaps.setTgl(df.dateFormatDash(encaps.getDate()));
//        encaps.setTgl("aia-"+ encaps.getTgl() +".xml");
//        
//        try{
//            encaps.setInputFile(new File("aia.xml"));
//            encaps.setOutputFile(new File(encaps.getTgl()));
//            
//            inStream = new FileInputStream(encaps.getInputFile());
//            outStream = new FileOutputStream(encaps.getOutputFile());
//            
//            byte[] buffer = new byte[1024];
//           while((encaps.setLength(inStream.read(buffer)))>0){
//           
//           }
//            
//        }catch{
//        }
//    }
    public static void rename() {
        InputStream inStream = null;
        OutputStream outStream = null;
        Date tgl = new Date();
        DateFormat df = new DateFormat();
        Encapsulation encaps = new Encapsulation();
        encaps.setTgl(df.dateFormatFull(tgl));
        encaps.setTgl("aia-" + encaps.getTgl() + ".xml");
        try {

            File afile = new File("aia.xml");
            File bfile = new File(encaps.getTgl());

            inStream = new FileInputStream(afile);
            outStream = new FileOutputStream(bfile);

            byte[] buffer = new byte[1024];

            int length;
            //copy the file content in bytes
            while ((length = inStream.read(buffer)) > 0) {
                outStream.write(buffer, 0, length);
            }

            inStream.close();
            outStream.close();

            //delete the original file
            afile.delete();

            //System.out.println("File is copied successful!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
