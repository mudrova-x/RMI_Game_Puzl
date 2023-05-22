package Server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class PointHandlerServer {

	public static void main(String[] args) {
		try {
			
			PointHandlerImpl obj = new PointHandlerImpl();
			PointHandler stub = (PointHandler) UnicastRemoteObject.exportObject(obj, 0);
			
			// в лекции было getRegistry (rmi реестр дб запущен до сервера) - для тестирования используем createRegistry(запуск реестра)
			Registry registry = LocateRegistry.createRegistry(1099);
			
			registry.rebind("myHandler", stub);
			
			System.out.print("Сервер запущен");
			System.out.print("\n"+stub.getPointsInfo());
			
			}
		catch(Exception e) {
			System.err.println("Server exception: " + e.toString());
		}

	}

}
