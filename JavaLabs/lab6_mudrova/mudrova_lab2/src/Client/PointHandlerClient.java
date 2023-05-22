package Client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import Server.PointHandler;
import shared_files.Point;

public class PointHandlerClient {

	public static void main(String[] args) {
		
		try (Scanner in = new Scanner(System.in)){
			
			Registry registry = LocateRegistry.getRegistry(1099);
			PointHandler stub = (PointHandler)registry.lookup("myHandler"); // поиск удаленного объекта - возвращает удаленную ссылку
			  
			int n = (int) ( Math.random() * 10 );
			System.out.println("клиент №" +n +" запущен");
			
			Point a = new Point();
			Point b = new Point();
			
			System.out.println("Задание точек.");
			System.out.println("Введите координаты точки А:");
			double x = in.nextDouble();
			in.nextLine();
			a.setX(x);
			double y = in.nextDouble();
			in.nextLine();
			a.setY(y);
			
			System.out.println("Введите координаты точки B:");
			x = in.nextDouble();
			in.nextLine();
			b.setX(x);
			y = in.nextDouble();
			in.nextLine();
			b.setY(y);
			
			System.out.println("Точка А("+a.getX()+", "+a.getY()+")");
			System.out.println("Точка B("+b.getX()+", "+b.getY()+")");
			
			boolean state = true;
			int variant = 0;
			while (state) {
				System.out.println("Выберите номер задания");
				System.out.println("1 - расчет длины отрезка между точками");
				System.out.println("2 - расчет длины окружности, центром которой является одна из точек, а радиусом – расстояние между точками");
				System.out.println("3 - расчет площади круга, центром которого является одна из точек, а радиусом – расстояние между точками");
				System.out.println("4 - расчет длины окружности, диаметром которой является расстояние между точками");
				System.out.println("5 - расчет площади круга, диаметром которого является расстояние между точками");
				System.out.println("6 - выход");
				variant =  in.nextInt();
				in.nextLine();
				switch (variant) {
				case 1: {
					double res = stub.getIntervalLength(a, b);
					System.out.printf("Длина отрезка: %.3f \n", res);
					break;
				}
				case 2: {
					double res = stub.getCircleLengthByRadius(a, b);
					System.out.printf("Длина окружности(через радиус): %.3f \n", res);
					break;
				}
				case 3: {
					double res = stub.getCircleAreaByRadius(a, b);
					System.out.printf("Площадь круга(через радиус): %.3f \n", res);
					break;
				}
				case 4: {
					double res = stub.getCircleLengthByDiameter(a, b);
					System.out.printf("Длина окружности(через диаметр): %.3f \n", res);
					break;
				}
				case 5: {
					double res = stub.getCircleAreaByDiameter(a, b);
					System.out.printf("Площадь круга(через диаметр): %.3f \n", res);
					break;
				}
				case 6: {
					state = false;
					break;
				}
				default:
					break;
				}
			}
			System.out.println("Завершение работы клиента №"+n);
		}
		catch (Exception e) {
			System.err.println("Ошибка на стороне клиента: " + e.toString());
		}
	}

}
