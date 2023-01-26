import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler extends Thread {

private Socket socket;
private int clientNumber; 
private Command cmd = new Command();
public ClientHandler(Socket socket, int clientNumber) {
	this.socket = socket;
	this.clientNumber = clientNumber;
	System.out.println("New connection with client#" + clientNumber + "at" + socket);
}

public void run() {
	try {
		DataOutputStream out = new DataOutputStream(socket.getOutputStream());  
		DataInputStream in = new DataInputStream(socket.getInputStream());  
		
		Command commande = new Command();
		
		while(true) {
			String command =  in.readUTF();
			
			if (command.length() < 3) {
				System.out.println("No directory specified.");
			} else {
				File dir = new File(command.substring(3));

				if (dir.exists() && dir.isDirectory()) {
					System.setProperty("user.dir", command.substring(3));
				} else {
					System.out.println("Directory " + command.substring(3) + " does not exist.");
				}
				System.out.println(command);
			}
		}

		//out.writeUTF("Hello from server - you are client #" + clientNumber); 
		} catch (IOException e) {
		System.out.println("Error handling client #" + clientNumber + ": " + e);
		} finally {
		try {
		socket.close();
		} catch (IOException e) {
		System.out.println("Couldn't close a socket, what's going on?");}
		System.out.println("Connection with client# " + clientNumber+ " closed");}
		}

}

	

