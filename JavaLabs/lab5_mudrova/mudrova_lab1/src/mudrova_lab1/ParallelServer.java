package mudrova_lab1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ParallelServer {
	
	
	public static void main(String[] args) throws Exception{


		try (ServerSocket serverSocket = new ServerSocket(1066)){
			 System.out.println("Сервер запущен!\n");

		while (true) {
			
				try{
					
					Socket clientSocket = serverSocket.accept();
					ServerThread childProcess = new ServerThread(clientSocket);
					childProcess.start();
					
				}catch (IOException e){
					System.out.println( "IOException -  Ошибка доступа к данным (сервер)");  
					System.out.println( e.getMessage());  
				}
		}
		
		}catch (IOException e){
			System.out.println( "Ошибка связи с портом: 1066");  
			System.out.println( e.getMessage());  
		}
		
	}
	
}
