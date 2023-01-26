import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
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
	
	
	System.out.format("Serveur lancé sur [%s:%d]", serverAddress, portResult);
	DataInputStream in = new DataInputStream(socket.getInputStream());
	DataOutputStream out = new DataOutputStream(socket.getOutputStream());  
	
	//String helloMessageFromServer = in.readUTF();
	
	//System.out.println(helloMessageFromServer);
	
	
	while(true) {
		System.out.println("Enter a command ");
		
		String commandString = reader.nextLine();
<<<<<<< HEAD
=======
		
		out.writeUTF(commandString);
>>>>>>> refs/remotes/origin/master
		
		out.writeUTF(commandString);
	}
	
	//socket.close();
	}
}
