package mudrova_lab1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	
	public static void main(String[] args) throws Exception{

		
		try (ServerSocket serverSocket = new ServerSocket(1066)){
			 
			System.out.println("Сервер запущен!\n");

		while (true) {
			
			try (   Socket clientSocket = serverSocket.accept();
					PrintWriter out = new PrintWriter(clientSocket.getOutputStream());
					BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))){

				String message = in.readLine();
				
				do{
					
					int a = Integer.parseInt(message);
					int b = Integer.parseInt(in.readLine());
					System.out.println("Делимое: " +a);
					System.out.println("Делитель: "+b);
					
					if(b==0) {
						out.println("Response: ошибка! Деление на ноль невозможно.");
						out.flush();
					}
					else {
						out.println(a/b);
						out.println(a%b);
						out.flush();
					}
					
					System.out.println("Отправил ответ!");
					System.out.println("-----------");
					

					message = in.readLine();
				
					} while(!message.contains("Response: завершение работы")); 
				
				System.out.println(message);
		
			}
			catch (IOException e){
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
