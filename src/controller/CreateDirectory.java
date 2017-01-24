package controller;

import java.io.File;
import java.util.Date;

public class CreateDirectory {

    public static void main(String[] args) {
        Date tgl = new Date();
        DateFormat df = new DateFormat();
        String mStr = df.dateFormatGetMonth(tgl);
        String dStr = df.dateFormatGetDate(tgl);

//           File aFile = new File("aia.xml");
//           System.out.println("parent : "+aFile.isDirectory());
        File mFile = new File(mStr);
        File dFile = new File(mStr + "\\" + dStr);

        if (!mFile.exists()) {
            System.out.println("Creating dir : " + mFile);
            try {
                mFile.mkdir();
                System.out.println("Dir Created "+mFile);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error Creating dir : " + e.getMessage());
            }
        }
        if(!dFile.exists()){
            System.err.println("Creating Dir : "+dFile);
            try{
                dFile.mkdir();
                System.out.println("Dir Created "+dFile);
            }catch(Exception e){
                e.printStackTrace();
                System.out.println("Error Creating Dir : "+e.getMessage());
            }
        }
    }
}
