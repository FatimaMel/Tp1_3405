import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

<<<<<<< HEAD
public class Command {
	enum Fonctionnality{
		cd, 
		ls,
		mkdir,
		upload,
		download,
		exit
	}
=======
public class command {
>>>>>>> refs/remotes/origin/master
	
	public void createDirectory(String nameDirectory) throws IOException{
		
		Path path = Paths.get("./" + nameDirectory);
		
		if (Files.notExists(path)){
			Files.createDirectory(path);
		}
	}
	
	public void changeDirectory(String directoryName) throws IOException{
		if (directoryName.length() < 0) {
			System.out.println("No directory specified.");
		} else {
			File dir = new File(directoryName);

			if (dir.exists() && dir.isDirectory()) {
				System.setProperty("user.dir", directoryName);
			} else {
				System.out.println("Directory " + directoryName + " does not exist.");
			}
		}
	}
	
}
