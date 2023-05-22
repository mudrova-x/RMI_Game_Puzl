package mudrova_lab1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread extends Thread {
	
	private Socket clientSocket;
	
	public ServerThread (Socket clientSocket) {
		
		this.clientSocket=clientSocket;

	}

	public void run() {
		  
		  Thread currentThread = Thread.currentThread();
		  System.out.println("Дочерний поток запущен! - "+currentThread.getName());
		  
		  
		  try (   PrintWriter out = new PrintWriter(clientSocket.getOutputStream());
				  BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))){
			  

				if (currentThread.getName().contains("0")) {
					System.out.println(currentThread.getName()+ " sleep");
					Thread.sleep(5000);
					}
				System.out.println("-------------------------------------------------------");
				System.out.println("\n"+ currentThread.getName()+ " начал обрабатывать запрос от клиента");
				
				
				String message = in.readLine();
				
				do{
					
				int a = Integer.parseInt(message);
				int b = Integer.parseInt(in.readLine());
				System.out.println("Делимое: " +a);
				System.out.println("Делитель: "+b);
				

				if(b==0) {
					out.println("Response: ошибка! Деление на ноль невозможно. ("+currentThread.getName()+")");
					out.flush();
				}
				else {
					out.println(a/b);
					out.println(a%b);
					out.flush();
				}

				System.out.println(currentThread.getName()+ " отправил ответ клиенту");
				System.out.println("-----------");
		
				message = in.readLine();
				} while(!message.contains("Response: завершение работы")); 
				
				System.out.println(currentThread.getName()+ " - "+ message);
				System.out.println("-------------------------------------------------------");
		  	}
			catch (IOException e){
				System.out.println( "IOException -  Ошибка доступа к данным (дочерний процесс)");  
				System.out.println( e.getMessage());  
			}
		  catch (InterruptedException e){
				System.out.println( "InterruptedException (сервер)");  
			}
		  
		  finally {
			  try {
				  
				clientSocket.close();
				
			  } catch (IOException e) {
				  System.out.println( e.getMessage());  
			  }
		  }
		  
	  }
	}
