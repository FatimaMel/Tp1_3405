import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class command {
	
	public void createDirectory(String nameDirectory) throws IOException{
		
		Path path = Paths.get("./" + nameDirectory);
		
		if (Files.notExists(path)){
			Files.createDirectory(path);
		}
	}

	enum Fonctionnality{
		cd, 
		ls,
		mkdir,
		upload,
		download,
		exit
	}
	
}
