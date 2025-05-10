package Javaproject.src.models;
import java.util.Random;
public class RDnumber{
    public static int RandNum(){
        Random r = new Random();
        int num =r.nextInt(1000);
        return num;
    }
}