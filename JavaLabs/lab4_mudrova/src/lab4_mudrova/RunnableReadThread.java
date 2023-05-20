package lab4_mudrova;


public class RunnableReadThread implements Runnable {

	private SuppliableSynchronizer synchronizedShipment;

	public RunnableReadThread(SuppliableSynchronizer shipment) {
		this.synchronizedShipment = shipment;
	}

	@Override
	public void run() {

		try {
			
			for (int i = 0; i < synchronizedShipment.getLength(); i++) {
				synchronizedShipment.read(i);

			}
		} 
		catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}

}