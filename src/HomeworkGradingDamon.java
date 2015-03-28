
import java.io.*; 

public class HomeworkGradingDamon 
{ 
	public static void main(String args[]) { 
		String courseId = args[0];
		String hwId = args[1];
		String studentNo = args[2];
		String result = "";
		String homeworkPath = "homeworks/" + courseId + "/" + hwId;
		String studentPath = homeworkPath + "/" + studentNo;
		result = executeCMD(homeworkPath, "unzip -d " + studentNo + " " + studentNo + ".zip > log1.txt");
		System.out.println(result);
		result = executeCMD(studentPath, "ant > log2.txt");
		System.out.println(result);
		result = executeCMD(studentPath, "java " + studentNo + ".jar > log3.txt");
		System.out.println(result);
	}

	private static String executeCMD(String workingDir, String cmd) {
		String line = "";
		try {
			System.out.println(cmd);
			Process pro = Runtime.getRuntime().exec(cmd, null, new File(workingDir));
			pro.waitFor(); 
			BufferedReader reader = new BufferedReader(new InputStreamReader(pro.getInputStream())); 
			line = reader.readLine();
			while(line != null) { 
				line = reader.readLine();
			}
		} catch(IOException e1) {
			
		} catch(InterruptedException e2) {
			
		}
		return line;
	} 
} 
