package automation;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Automation {
    public static void main(String[] args) {
        // TODO code application logic here
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String strDate = sdf.format(dt);
        System.out.println(strDate);
    }   
}