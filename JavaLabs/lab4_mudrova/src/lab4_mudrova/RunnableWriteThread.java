package lab4_mudrova;

import java.util.Random;

public class RunnableWriteThread implements Runnable{

	private SuppliableSynchronizer synchronizedShipment;
	
	public RunnableWriteThread(SuppliableSynchronizer shipment) {
		this.synchronizedShipment=shipment;
	}
	
	@Override
	public void run() {
		
		Random rand = new Random();
		for (int i=0; i<synchronizedShipment.getLength(); i++)
		{
			int num = rand.nextInt(10)+1;
			synchronizedShipment.write(num, i);
		}
		
	}

}
