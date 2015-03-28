import java.io.*;
import java.net.*;
import java.util.*;
 
public class test{
        public static void main(String a[]) throws Exception{
                Process pl = Runtime.getRuntime().exec("/bin/ls");
                String line = "";
                BufferedReader p_in = new BufferedReader(new InputStreamReader(pl.getInputStream()));
                while((line = p_in.readLine()) != null){
                        System.out.println(line);
                }
                p_in.close();
        }
}