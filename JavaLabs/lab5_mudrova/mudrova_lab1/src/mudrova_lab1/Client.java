package mudrova_lab1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
	
	public static void main(String[] args) throws Exception{
		
		// сначала запись - OutputStream, потом чтение - InputStream !
		 
	    try (
	    		Socket socket = new Socket("localhost", 1066);
	    		PrintWriter out = new PrintWriter(socket.getOutputStream());
	    		BufferedReader  in = new BufferedReader(new InputStreamReader(socket.getInputStream()))){

			 System.out.println("Клиент запущен \n");
			 
	    	 for (int i=3; i>=-1; i--) {
	    		 
	    		 	System.out.println("Делимое: " + 10);
			        System.out.println("Делитель: " + i);
			        
			        out.println(10);
			        out.println(i);
			        out.flush();
			        
			        String message = in.readLine();
			        
			        if (message.contains("Response:")){
			        	System.out.println(message);
			        	break;
			        	}
			        
			        else {
			        	System.out.println("Частное: " + message);
				        System.out.println("Остаток: " + in.readLine());
			        }
			        
			        System.out.println("-----------");
			    }
	    	 
	    	 	System.out.println("Завершение работы клиента");
	    	 	
	    	 	out.println("Response: завершение работы клиентского сокета");
		        out.flush();
		        
	       
	      } catch (IOException e){
				System.out.println( "IOException -  Ошибка доступа к данным (клиент):");  
				System.out.println( e.getMessage());  
		 }
	    	
	}
	
}
