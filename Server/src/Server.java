import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.FileReader;
import java.io.FilterWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Server {

	private static ServerSocket Listener;

	public static void main(String[] args) throws Exception {

		int clientNumber = 0;
		Scanner ipScanObj = new Scanner(System.in);
		Scanner portScanObj = new Scanner(System.in);
		
		  
		System.out.println("Enter an IP adress "); String ipAddress = ipScanObj.nextLine();
		  
		String zeroTo255 = "(\\d{1,2}|(0|1)\\" + "d{2}|2[0-4]\\d|25[0-5])"; String
		regex = zeroTo255 + "\\." + zeroTo255 + "\\." + zeroTo255 + "\\." +
		zeroTo255; Pattern p = Pattern.compile(regex); Matcher m =
		p.matcher(ipAddress);
		 
		if(!m.matches()) {
			do { System.out.println("Error, enter a valid IP address"); ipAddress = ipScanObj.nextLine(); m = p.matcher(ipAddress);
		} while(!m.matches()); }
		  
		  String serverAddress = ipAddress;
		  
		  
		  
		  System.out.println("Enter a port12 "); int port = portScanObj.nextInt();
		  if(port > 5050 || port < 5000) { do { System.out.println("Error, enter a port between 5000 and 5050 "); port = portScanObj.nextInt(); }while(port > 5050 ||
		  port < 5000); }
		  
		  int serverPort = port;

		Listener = new ServerSocket();
		Listener.setReuseAddress(true);

		InetAddress serverIP = InetAddress.getByName(serverAddress);
		Listener.bind(new InetSocketAddress(serverIP, serverPort));

		System.out.format("The server is running on %s:%d%n", serverAddress, serverPort);
		try {

			while (true) {
				new ClientHandler(Listener.accept(), clientNumber++).start();
			}
		} finally {
			Listener.close();
			ipScanObj.close();
			portScanObj.close();
		}

	}
}