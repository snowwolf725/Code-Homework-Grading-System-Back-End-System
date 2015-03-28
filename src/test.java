
import java.io.*; 

public class test 
{ 
	public static void main(String args[]) 
	{ 
		try 
		{ 
			Process p=Runtime.getRuntime().exec("cmd /c C:/Users/user1/workspace/test/dos.bat"); 
			p.waitFor(); 
			BufferedReader reader=new BufferedReader(new InputStreamReader(p.getInputStream())); 
			String line=reader.readLine(); 
			while(line!=null) 
			{ 
				System.out.println(line); 
				line=reader.readLine(); 
			} 
		} 
		catch(IOException e1) {} 
		catch(InterruptedException e2) {} 
	} 
} 