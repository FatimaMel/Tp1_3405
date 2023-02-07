import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class command {	
	public void createDirectory(String nameDirectory, String currentPath) throws IOException{
		
		Path path = Paths.get(currentPath + nameDirectory);
		
		if (Files.notExists(path)){
			Files.createDirectory(path);
		}
	}
	
	public String changeDirectory(String directoryName, String currentPath) throws IOException{
		if(directoryName.equals("..")){
			String[] slice = currentPath.split("/");
			System.setProperty("user.dir", slice[slice.length - 2]);
			currentPath = currentPath.substring(0, currentPath.lastIndexOf("/", currentPath.length() - 2) + 1);
		} else {			
			System.setProperty("user.dir", directoryName);
			currentPath += directoryName + "/";
		}
		return currentPath;
	}
	
	public String listCurrentDirectory(String currentPath) {
		Path dir = Paths.get(currentPath);
		String files = "";
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
			for (Path file : stream) {
				files += file.getFileName().toString() + ";";
			}
		} catch (IOException | DirectoryIteratorException x) {
			System.err.println(x);
		}
		// System.out.println(command + "tf");
		return files;
   
	}

}
