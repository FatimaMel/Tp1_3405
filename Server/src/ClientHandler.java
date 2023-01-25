import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler extends Thread {

private Socket socket;
private int clientNumber; 
public ClientHandler(Socket socket, int clientNumber) {
	this.socket = socket;
	this.clientNumber = clientNumber;
	System.out.println("New connection with client#" + clientNumber + "at" + socket);
}

public void run() {
	try {
		DataOutputStream out = new DataOutputStream(socket.getOutputStream());  
		DataInputStream in = new DataInputStream(socket.getInputStream());  
		
		command commande = new command();
		
		while(true) {
			String command =  in.readUTF();
			char[] arrayCommand = new char[command.length()];
			
			for(int i = 0; i < command.length(); i++) {
				char letterOfCommand = command.charAt(i);
				arrayCommand[i]= letterOfCommand;
			}
			

			
			int sizeOfArray = command.length();
			
			char[] nameOfDirectory = new char[sizeOfArray + 2];
			
			String[] name = command.split(" ", 2);

			
			commande.createDirectory(name[1]);
		}

		//out.writeUTF("Hello from server - you are client#" + clientNumber); 
		} catch (IOException e) {
		System.out.println("Error handling client# " + clientNumber + ": " + e);
		} finally {
		try {
		socket.close();
		} catch (IOException e) {
		System.out.println("Couldn't close a socket, what's going on?");}
		System.out.println("Connection with client# " + clientNumber+ " closed");}
		}

}

	

