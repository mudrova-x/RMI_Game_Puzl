package lab4_mudrova;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) {

		int lab = 5;
		
		int variant = -1, num = 0;
		Scanner in = new Scanner(System.in);
		
		Suppliable[] shipments = new Suppliable[num];
		
		if (lab==4)
		{	
		System.out.print("Автоматическое заполнение данных или ручное?(1/2)");
		// int mode = 1;
		int mode = in.nextInt();
		in.nextLine();

		if (mode == 2)
			System.out.print("Информацию о скольки поставках вы хотите внести? ");
		num = (mode == 1) ? 5 
				: in.nextInt();
		in.nextLine();
		if (num < 1)
			num = 1;

		shipments = new Suppliable[num];

		if (mode == 1) {
			System.out.println("\nДобавить информацию о поставках:");

			System.out.println("Шоколада - 1 /Печенья - 2");
			variant = in.nextInt();
			in.nextLine();

			if (variant == 1) {

				shipments[0] = new ChocolateBoxes("C1", "Мята", new int[] { 2, 3, 4 }, 80);
				shipments[1] = new ChocolateBoxes("C2", "Апельсин", new int[] { 5, 3 }, 40);
				shipments[2] = new ChocolateBoxes("C3", "Финики", new int[] { 9, 8, 9, 8, 5 }, 50);
				shipments[3] = new ChocolateBoxes("C4", "Лесные орехи", new int[] { 3, 3 }, 325);
				shipments[4] = new ChocolateBoxes("C5", "Морская соль", new int[] { 4, 7, 8, 6 }, 98);
			} else {
				shipments[0] = new BiscuitBoxes("B1", "Карамель", new int[] { 300, 420 }, 100);
				shipments[1] = new BiscuitBoxes("B2", "Апельсин", new int[] { 100, 100, 120 }, 100);
				shipments[2] = new BiscuitBoxes("B3", "Имбирь", new int[] { 200, 150, 300 }, 300);
				shipments[3] = new BiscuitBoxes("B4", "Кокос", new int[] { 90, 90, 90, 120, 80 }, 100);
				shipments[4] = new BiscuitBoxes("B5", "Изюм", new int[] { 100, 100 }, 300);
			}

		}

		if (mode == 2) {
			System.out.println("\nДобавить информацию о поставках:");

			System.out.println("Печенья - 1 /Шоколада - 2");
			variant = in.nextInt();
			in.nextLine();
			for (int i = 0; i < shipments.length; i++) {

				if (variant == 1) {
					System.out.println("\n\nВведите поставщика:");
					String producerName = in.nextLine();
					System.out.println("Введите вкус печенья:");
					String biscuitFlavour = in.nextLine();
					System.out.println("Введите количество коробок в поставке:");
					int boxesNum = in.nextInt();
					in.nextLine();
					if (boxesNum < 0)
						num = 0;
					int[] boxesWeight = new int[boxesNum];
					for (int j = 0; j < boxesWeight.length; j++) {
						System.out.println(String.format("Введите вес упаковки в граммах № %d", j + 1));
						boxesWeight[j] = in.nextInt();
						in.nextLine();
					}
					System.out.println("Введите цену за 100 грамм (кратную 100):");
					int gramPrice = in.nextInt();
					in.nextLine();
					shipments[i] = new BiscuitBoxes(producerName, biscuitFlavour, boxesWeight, gramPrice);
					System.out.print(shipments[i]);
				}
				if (variant == 2) {
					System.out.println("\n\nВведите поставщика:");
					String producerName = in.nextLine();
					System.out.println("Введите вкус:");
					String chokolateFlavour = in.nextLine();
					System.out.println("Введите количество коробок в поставке:");
					int boxesNum = in.nextInt();
					in.nextLine();
					int[] barsEmount = new int[boxesNum];
					for (int j = 0; j < barsEmount.length; j++) {
						System.out.println(String.format("Введите количество плиток в коробке № %d", j + 1));
						barsEmount[j] = in.nextInt();
						in.nextLine();
					}
					System.out.println("Введите цену (за плитку):");
					int barPrice = in.nextInt();
					in.nextLine();
					shipments[i] = new ChocolateBoxes(producerName, chokolateFlavour, barsEmount, barPrice);
					System.out.print(shipments[i]);
				}
			}
		}
		}
		boolean key = true;
		while (key) {
			System.out.println("\n_________________________________________________________________");
			System.out.println("\nВыберите пункт меню:");
			System.out.println("1 - Цикл записи в байтовый поток и цикл чтения из байтового потока.");
			System.out.println("2 - Цикл записи в текстовый поток и цикл чтения из текстового потока .");
			System.out.println(
					"3 - Цикл записи в байтовый поток и цикл чтения из байтового потока с помощью  методов сериализации/десериализации.");
			System.out.println("4 - Цикл записи в текстовый поток с помощью текстового форматного вывода.");
			System.out.println("5 - Цикл чтения из текстового потока с помощью текстового форматного ввода (c записью в файл). ");
			System.out.println("ЛР-5_________________________________________________________________");
			System.out.println("6 - Две нити с разным приоритетом. ");
			System.out.println("7 - Две Runnable нити. ");
			System.out.println("8 - Тест оболочки, безопасной с точки зрения многопоточности. ");
			System.out.println("9 - Выход.");
			variant = in.nextInt();
			in.nextLine();
			if (lab==5 && variant!=6 && variant!=7 && variant!=8 && variant!=9)
				continue;
			switch (variant) {
			case (1): {

				FileOutputStream out = null;

				try {

					out = new FileOutputStream("shipmentsBytes.bin");
					for (int i = 0; i < shipments.length; i++) {
						StreamsHelper.outputSuppliable(shipments[i], out);

					}
				} catch (FileNotFoundException e) {
					System.out.print("Ошибка: файл не найден ");
				} catch (IOException e) {
					System.out.print("Ошибка ввода/вывода - " + e.getMessage());
				} finally {
					try {

						if (out != null)
							out.close();

					} catch (IOException e) {
						e.getMessage();
					}
				}

				System.out.print("\nУспешная запись в поток (байтовый)");
				System.out.println("\n_______________________________");

				FileInputStream inF = null;
				try {

					inF = new FileInputStream("shipmentsBytes.bin");
					for (int i = 0; i < shipments.length; i++) {
						Suppliable shipment = StreamsHelper.inputSuppliable(inF);
						System.out.print(shipment + "\n\n");
					}

				}catch (FileNotFoundException e) {
					System.out.print("Ошибка: файл не найден ");
				}catch (IOException e) {
					System.out.print("Ошибка ввода/вывода - " + e.getMessage());
				}
				catch (PriceException e) {
					System.out.print("Ошибка! - " + e.getMessage());
				}finally {
					try {

						if (inF != null)
							inF.close();

					} catch (IOException e) {
						e.getMessage();
					}
				}

				break;
			}
			case (2): {

				FileWriter out = null;

				try {

					out = new FileWriter("shipments.txt");
					for (int i = 0; i < shipments.length; i++) {
						StreamsHelper.writeSuppliable(shipments[i], out);
					}

				} catch (FileNotFoundException e) {
					System.out.print("Ошибка: файл не найден ");
				}
				catch (IOException e) {
					System.out.print("Ошибка ввода/вывода - " + e.getMessage());
				} finally {
					try {

						if (out != null)
							out.close();

					} catch (IOException e) {
						e.getMessage();
					}
				}

				System.out.print("\nУспешная запись в поток (символьный)");
				System.out.println("\n\n_______________________________");


				FileReader inF = null;
				try {

					inF = new FileReader("shipments.txt");
					for (int i = 0; i < shipments.length; i++) {
						Suppliable shipment = StreamsHelper.readSuppliable(inF);
						System.out.print(shipment + "\n\n");
					}

				}catch (FileNotFoundException e) {
					System.out.print("Ошибка: файл не найден ");
				} catch (IOException e) {
					System.out.print("Ошибка ввода/вывода - " + e.getMessage());
				} catch (PriceException e) {
					System.out.print("Ошибка! - " + e.getMessage());
				}finally {
					try {
						if (inF != null)
							inF.close();
					} catch (IOException e) {
						e.getMessage();
					}
				}
				break;

			}
			case (3): {
				FileOutputStream out = null;
				try {

					out = new FileOutputStream("sr.bin");
					for (int i = 0; i < shipments.length; i++) {
						StreamsHelper.serializeSuppliable(shipments[i], out);
					}

				}catch (FileNotFoundException e) {
					System.out.print("Ошибка: файл не найден ");
				}catch (IOException e) {
					System.out.print("Ошибка ввода/вывода - " + e.getMessage());
				}finally {
					try {
						if (out != null)
							out.close();
					} catch (IOException e) {
						e.getMessage();
					}
				}

				System.out.print("Cериализация закончена.");
				System.out.println("\n\n_______________________________");


				FileInputStream inF = null;
				try {

					inF = new FileInputStream("sr.bin");
					for (int i = 0; i < shipments.length; i++) {
						Suppliable shipment = StreamsHelper.deserializeSuppliable(inF);
						System.out.println(shipment + "\n\n");
					}
					System.out.print("\n\nДесериализация закончена\n\n");

				}catch (FileNotFoundException e) {
					System.out.print("Ошибка: файл не найден ");
				}catch (IOException e) {
					System.out.print("Ошибка ввода/вывода - " + e.getMessage());
				} catch (ClassNotFoundException e) {
					System.out.print("Некорректный тип объекта" + e.getMessage());
				}finally {
					try {
						if (inF != null)
							inF.close();
					} catch (IOException e) {
						e.getMessage();
					}
				}
				break;

			}
			case (4): {

				FileWriter out = null;

				try {

					out = new FileWriter("formatted.txt");
					for (int i = 0; i < shipments.length; i++) {
						StreamsHelper.writeFormatSuppliable(shipments[i], out);
					}
					System.out.print("Успешная запись форматированного текста в файл \"formatted.txt\"");

				} catch (FileNotFoundException e) {
					System.out.print("Ошибка: файл не найден ");
				} catch (IOException e) {
					System.out.print("Ошибка ввода/вывода - " + e.getMessage());
				} finally {
					try {
						if (out != null)
							out.close();
					} catch (IOException e) {
						e.getMessage();
					}
				}

				break;
			}
			case (5): {

				FileInputStream inF = null;
				FileWriter out = null;
				Scanner scan = null;
				
				try {

					inF = new FileInputStream("shipments.txt");
					out = new FileWriter("ex3.txt");
				    scan = new Scanner(inF);

					for (int i = 0; i < shipments.length; i++) {
						{

							Suppliable shipment = StreamsHelper.readFormatSuppliable(scan);
							StreamsHelper.writeFormatSuppliable(shipment, out);
						}

					}
					System.out.print(
							"Успешная запись форматированного текста из файла \"shipments.txt\" в файл \"ex3.txt\"");

				} catch (FileNotFoundException e) {
					System.out.print("Ошибка: файл не найден ");
				} catch (IOException e) {
					System.out.print("Ошибка ввода/вывода - " + e.getMessage());
				} catch (PriceException e) {
					System.out.print("Ошибка! - " + e.getMessage());
				}finally {
					try {
						if (scan != null)
							scan.close();
						if (out != null)
							out.close();
						if (inF != null)
							inF.close();
						
					} catch (IOException e) {
						e.getMessage();
					}
				}
				break;

			}
			case (6): {
				
				ChocolateBoxes testShipment = new ChocolateBoxes("Задание1S", "Фундук", new int[] 
								{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
								, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
								, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
								, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 80);
				
				//System.out.print(testShipment.getBoxesEmount());
				
				WriteThread wThread = new WriteThread(testShipment);
				ReadThread rThread = new ReadThread(testShipment);
				
				wThread.setPriority(Thread.MAX_PRIORITY);
				wThread.start();
				
				rThread.setPriority(Thread.MIN_PRIORITY);
				rThread.start();
				
				break;
			}
			case (7): {
				
				ChocolateBoxes testShipment = new ChocolateBoxes("Задание2", "Фундук", new int[] { 0, 0, 0, 0, 0, 0, 0, 0 }, 80);
			
				SuppliableSynchronizer synchWrite = new SuppliableSynchronizer(testShipment);
				
				
				RunnableWriteThread wThreadRunnable = new RunnableWriteThread(synchWrite);
				RunnableReadThread rThreadRunnable = new RunnableReadThread(synchWrite);
				
				
				Thread wThread = new Thread(wThreadRunnable);
				Thread rThread = new Thread(rThreadRunnable);
				
				
				wThread.setPriority(1);
				wThread.start();
				
				rThread.setPriority(10);
				rThread.start();
				
				break;
			}
			case (8): {
				
				BiscuitBoxes testShipment = new BiscuitBoxes("Задание3", "Фундук", 
						new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 100);
				
				Suppliable shell = StreamsHelper.synchronizedSuppliable(testShipment);
				TestWrite T1 = new TestWrite(shell, "T1");
				TestWrite T2 = new TestWrite(shell, "T2");
				TestWrite T3 = new TestWrite(shell, "T3");
				T1.setPriority(5);
				T3.setPriority(10);
				
				T1.start();
				T2.start();
				T3.start();
				
				break;
			}
			case (9): {
				key = false;
				break;
			}
			default:
				break;
			}
		}
		in.close();
	}
	
}	

//int boxesEmount=testShipment.getBoxesEmount();
//RunnableWriteThread wThreadRunnable = new RunnableWriteThread(synchWrite, boxesEmount);
		//	RunnableReadThread rThreadRunnable = new RunnableReadThread(synchWrite, boxesEmount);