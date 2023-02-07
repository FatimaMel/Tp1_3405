import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ClientHandler extends Thread {

	private Socket socket;
	private int clientNumber;

	public ClientHandler(Socket socket, int clientNumber) {
		this.socket = socket;
		this.clientNumber = clientNumber;
		System.out.println("New connection with client#" + clientNumber + " at " + socket);
	}

	public void run() {
		try {
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			DataInputStream in = new DataInputStream(socket.getInputStream());
			
			command commande = new command();
			String cmdType = "";
			String currentPath = "./root/";
			

			while (!cmdType.equals("exit") ) {
				String command = in.readUTF();

				String[] splitedCmd = command.split(" ", 2);
				cmdType = splitedCmd[0];
				
				switch (cmdType) {
				case "mkdir":
					commande.createDirectory(splitedCmd[1], currentPath);
					break;
				case "cd":
					currentPath = commande.changeDirectory(splitedCmd[1], currentPath);
					out.writeUTF(currentPath);
					break;
				case "ls":
					out.writeUTF(commande.listCurrentDirectory(currentPath));
					break;
				case "upload":
					int filsSize = in.readInt();
		            byte[] fileContent = new byte[filsSize];

		            int byteRead = 0;
		            while (byteRead < filsSize) {
		                byteRead += in.read(fileContent, byteRead, filsSize - byteRead);
		            }

		            try (FileOutputStream fos = new FileOutputStream(currentPath + "téléchargement2.jfif")) {
		                fos.write(fileContent);
		            }
					break;
				case "download":
					Path path = Paths.get(currentPath + splitedCmd[1]).toAbsolutePath();

			        byte[] data = Files.readAllBytes(path);
			        out.writeInt(data.length);
			        out.write(data, 0, data.length);
					break;
				case "exit":
					this.socket.close();
					break;
				}
			}

			// out.writeUTF("Hello from server - you are client#" + clientNumber);
		} catch (IOException e) {
			System.out.println("Error handling client# " + clientNumber + ": " + e);
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				System.out.println("Couldn't close a socket, what's going on?");
			}
			System.out.println("Connection with client# " + clientNumber + " closed");
		}
	}
}
