package lab4_mudrova;

import java.util.Random;

public class WriteThread extends Thread{
	
	private Suppliable shipment;
		
	public WriteThread(Suppliable shipment) {
		this.shipment = shipment;
	}
	
	// Формат: (Write: ### to position ###)
	
	public void run()
	{
			
		Random rand = new Random();
		for (int i=0; i<shipment.getBoxesEmount(); i++)
		{
			int num = rand.nextInt(10)+1;
			shipment.setBoxsInput( num, i);
			System.out.println("Write: " + num + " to position " + i);
		}
	}
}
