
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.util.HashSet;
import java.util.Set;

public class HomeworkGradingDamon 
{ 
	public static void main(String args[]) { 
		String courseId = args[0];
		String hwId = args[1];
		String studentNo = args[2];
		String homeworkPath = "homeworks/" + courseId + "/" + hwId;
		
		try {
			PrintWriter writer = new PrintWriter("grading.sh", "UTF-8");
			writer.println("unzip -o -d " + studentNo + " " + studentNo + ".zip > log1.txt");
			writer.println("cd " + studentNo);
			writer.println("ant > ../log2.txt");
			writer.println("java " + studentNo + ".jar > log3.txt");
			writer.close();
			
			Set<PosixFilePermission> perms = new HashSet<PosixFilePermission>();
			perms.add(PosixFilePermission.OWNER_READ);
	        perms.add(PosixFilePermission.OWNER_WRITE);
	        perms.add(PosixFilePermission.OWNER_EXECUTE);
	        perms.add(PosixFilePermission.GROUP_READ);
	        perms.add(PosixFilePermission.GROUP_WRITE);
	        perms.add(PosixFilePermission.GROUP_EXECUTE);
	        perms.add(PosixFilePermission.OTHERS_READ);
	        perms.add(PosixFilePermission.OTHERS_WRITE);
	        perms.add(PosixFilePermission.OTHERS_EXECUTE);
	        Files.setPosixFilePermissions(Paths.get("grading.sh"), perms);
	        
	        File file =new File("grading.sh");
	        file.renameTo(new File(homeworkPath + "/" + studentNo + ".sh"));
	        
	        Thread.sleep(1000);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		
		executeCMD(homeworkPath, "./" + studentNo + ".sh");
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
		} catch(IOException | InterruptedException e) {
			e.printStackTrace();
		}
		return line;
	} 
} 
