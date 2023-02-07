import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher; 

public class Client {
	private static Socket socket;
	public static void main(String[] args) throws Exception{
		
		Scanner reader = new Scanner(System.in);
	/**
		System.out.println("Enter an IP adress ");
		String ipAddress = reader.nextLine();
		
		String zeroTo255 = "(\\d{1,2}|(0|1)\\" + "d{2}|2[0-4]\\d|25[0-5])";
		String regex = zeroTo255 + "\\." + zeroTo255 + "\\." + zeroTo255 + "\\." + zeroTo255;
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(ipAddress);
		
		if(!m.matches()) {
			do {
				System.out.println("Error, enter a valid IP address");
				ipAddress = reader.nextLine();
				m = p.matcher(ipAddress);
			}while(!m.matches());
		}
		
		String serverAddress = ipAddress;
		
		//Scanner port = new Scanner(System.in);
		int portResult;
		
		do {
			System.out.println("Please enter a number port");
			portResult = reader.nextInt();	
			
			while(portResult < 5000 || portResult> 5050) {
				System.out.println("Le port doit etre entre 5000 et 5050");
				portResult = reader.nextInt();
			}
		}while(portResult < 5000 || portResult > 5050);
**/
	String serverAddress = "127.0.0.1";
	int portResult = 5000;
	
	socket = new Socket(serverAddress, portResult);
	
	
	System.out.format("Serveur lancé sur [%s:%d]\n", serverAddress, portResult);
	DataInputStream in = new DataInputStream(socket.getInputStream());
	DataOutputStream out = new DataOutputStream(socket.getOutputStream());  
	
	//String helloMessageFromServer = in.readUTF();
	
	//System.out.println(helloMessageFromServer);
	
	String cmdType = "";
	command cmd = new command();
	String route = "./root/";
	
	while(!cmdType.equals("exit")) {
		System.out.println("Enter a command ");
		String commandString = reader.nextLine();
		out.writeUTF(commandString);
		
		String[] splitedCmd = commandString.split(" ", 2);
		cmdType = splitedCmd[0];
		
		switch (cmdType) {
		case "mkdir":
			break;
		case "cd":
			route = in.readUTF();
			break;
		case "ls":
			String files = in.readUTF();
			String[] splitFiles = files.split(";");
			
			for(String file : splitFiles) {
				System.out.println(file);
			}
			
			break;
		case "upload":
			Path path = Paths.get(splitedCmd[1]).toAbsolutePath();

	        byte[] data = Files.readAllBytes(path);
	        out.writeInt(data.length);
	        out.write(data, 0, data.length);
			break;
		case "download":
			int filsSize = in.readInt();
            byte[] fileContent = new byte[filsSize];

            int byteRead = 0;
            while (byteRead < filsSize) {
                byteRead += in.read(fileContent, byteRead, filsSize - byteRead);
            }
            
            try (FileOutputStream fos = new FileOutputStream("téléchargement2.jfif")) {
                fos.write(fileContent);
            }
			break;
		case "exit":
			socket.close();
			break;
		}
		System.out.println("Current route is : " + route);
	}
	}
}
