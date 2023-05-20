package lab4_mudrova;

public class ReadThread extends Thread{
	
	private Suppliable shipment;
	
	public ReadThread(Suppliable shipment) {
		this.shipment=shipment;
	}
	
	//Формат (Read: ### from position ###)
	public void run() {
		
		for (int i=0;i<shipment.getBoxesEmount(); i++){
            System.out.println("Read: " + shipment.getBoxsInput(i) + " from position " + i);
        }
	}
}
